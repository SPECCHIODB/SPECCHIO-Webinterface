package ch.specchio.util;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import ch.specchio.client.SPECCHIOClient;
import ch.specchio.client.SPECCHIOClientException;
import ch.specchio.client.SPECCHIOClientFactory;
import ch.specchio.client.SPECCHIOServerDescriptor;
import ch.specchio.model.Attribute;
import ch.specchio.model.Category;
import ch.specchio.model.ChartDataBean;
import ch.specchio.model.Pair;
import ch.specchio.model.SearchResultBean;
import ch.specchio.model.SearchRowBean;
import ch.specchio.model.SpaceDetailBean;
import ch.specchio.plots.VectorStatistics;
import ch.specchio.queries.EAVQueryConditionObject;
import ch.specchio.queries.Query;
import ch.specchio.queries.QueryCondition;
import ch.specchio.queries.SpectrumQueryCondition;
import ch.specchio.spaces.Space;
import ch.specchio.spaces.SpectralSpace;
import ch.specchio.types.Campaign;
import ch.specchio.types.CategoryTable;
import ch.specchio.types.ConflictInfo;
import ch.specchio.types.ConflictStruct;
import ch.specchio.types.ConflictTable;
import ch.specchio.types.InstrumentDescriptor;
import ch.specchio.types.MatlabAdaptedArrayList;
import ch.specchio.types.MetaFile;
import ch.specchio.types.MetaParameter;
import ch.specchio.types.Sensor;
import ch.specchio.types.Spectrum;
import ch.specchio.types.attribute;

public class SpecchioUtil {

	private final String FULL_TEXT_SEARCH 		= "Full Text Search";
	private final String MOST_WANTED	 		= "Most Wanted";
	
	private final String MEASUREMENT_UNIT_ID	= "Measurement Unit ID";
	private final String SENSOR_ID		 		= "Sensor ID";
	private final String INSTRUMENT_ID	 		= "Instrument ID";
	
	private SPECCHIOClient specchio_client;
	
	public SpecchioUtil() throws Exception {
		IOUtil.loadDbConfig(); 	// load DB config file, which is needed to connect to the db.
		connectToDB();			// connect to the db.
	}
	
	/**
	 * Connect to DB and get the specchio_client
	 */
	private void connectToDB() throws SPECCHIOClientException {
		SPECCHIOClientFactory cf = SPECCHIOClientFactory.getInstance();
		List<SPECCHIOServerDescriptor> db_descriptor_list = cf.getAllServerDescriptors();
		this.specchio_client = cf.createClient(db_descriptor_list.get(0));
	}
	
	/**
	 * creates a list of all categories.
	 * used on the search.jsp for the category dropdown.
	 */
	public List<Category> getCategoryList() throws SPECCHIOClientException{
		List<Category> categoryList = new LinkedList<>();
		// special categories that are not stored inside the db
		categoryList.add(new Category(FULL_TEXT_SEARCH));	// a category for the full text search option
		categoryList.add(new Category(MOST_WANTED));		// a category for the most wanted attributes

		// Categories that shouldn't be displayed in the Category Dropdown
		List<String> ignoredCategories = new LinkedList<>();
		ignoredCategories.add("Data Links");
		ignoredCategories.add("PDFs");
		ignoredCategories.add("Pictures");
		
		// get all categories from the db and add the ones that shouldn't be ignored
		for(ch.specchio.types.Category c : specchio_client.getCategoriesInfo()) {
			if(!ignoredCategories.contains(c.name)) categoryList.add(new Category(c));
		}
		
		return categoryList;
	}
	
	/**
	 * @param name - the name of the category
	 * @return the category object for the given category name or null if no category exists for that name
	 */
	public Category getCategory(String name) throws SPECCHIOClientException {
		for(Category c : getCategoryList()){
			if(name != null && name.equals(c.getName())) return c;
		}
		return null;
	}
	
	/**
	 * @param category - the name of the category
	 * @return a list of all attributes of the given category
	 */
	public List<Attribute> getAttributeList(String category) throws SPECCHIOClientException {
		
		// for Full Text Search we return an empty attribute. 
		// the attribute dropdown on the search.jsp will be disabled for Full Text Search.
		if(FULL_TEXT_SEARCH.equals(category)){
			List<Attribute> list = new LinkedList<>();
			list.add(new Attribute("","string_val"));
			return list;
		}
		// the most wanted attributes
		else if(MOST_WANTED.equals(category)){
			return getMostWantedAttributes();
		}
		else {
			// get all attributes for the category
			attribute[] attributes = specchio_client.getAttributesForCategory(category);
			
			// create an Attribute object for each attribute object (WrapperClass)
			List<Attribute> attributeList = new LinkedList<>();
			for(attribute a : attributes) attributeList.add(new Attribute(a));
			
			// measurement unit id should be added to the General Category
			if("General".equals(category)){
				attributeList.add(new Attribute(MEASUREMENT_UNIT_ID, "drop_down"));
				Collections.sort(attributeList); // sort list in ascending order
			}
			// sensor id and instrument id should be added to Instrument Category
			else if("Instrument".equals(category)) {
				attributeList.add(new Attribute(SENSOR_ID, "drop_down"));
				attributeList.add(new Attribute(INSTRUMENT_ID, "drop_down"));
				Collections.sort(attributeList); // sort list in ascending order
			}

			return attributeList;
		}
		
	}
	
	/**
	 * returns an Attribute Object for the given attribute name and category name
	 * or the first attribute for that category if no such attribute exists inside
	 * the given category
	 */
	public Attribute getAttribute(String attribute, String category) throws SPECCHIOClientException {
		if(attribute != null) {
			for(Attribute a : getAttributeList(category)){
				if(attribute.equals(a.getName())) 
					return a;
			}
		}
		return getFirstAttribute(category);
	}

	/**
	 * returns the first attribute for the given category
	 */
	private Attribute getFirstAttribute(String category){
		return getAttributeList(category).isEmpty() ? null : getAttributeList(category).get(0);
	}
	
	/**
	 * contains the most wanted attributes which will be displayed under the category 
	 * Most Wanted on the search.jsp
	 */
	private List<Attribute> getMostWantedAttributes(){
		List<Attribute> attributeList = new LinkedList<>();
		attributeList.add(new Attribute(MEASUREMENT_UNIT_ID, "drop_down"));
		attributeList.add(new Attribute(SENSOR_ID, "drop_down"));
		attributeList.add(new Attribute(INSTRUMENT_ID, "drop_down"));
		
		return attributeList;
	}

	/**
	 * returns a list of pairs used for the dropdown user inputs on the search.jsp.
	 * the pairs contain the value of an attribute and the display name for the dropdown.
	 */
	public List<Pair<String,String>> getInputDropdownValues(Attribute attr) throws SPECCHIOClientException {
		List<Pair<String,String>> list = new LinkedList<Pair<String,String>>();
		
		if("taxonomy_id".equals(attr.getDefaultStorageField())){
			list = getTaxonomyList(attr);
		}
		else {
			switch(attr.getName()){
				case MEASUREMENT_UNIT_ID:
					list = getMeasurementUnitList();
					break;
				case SENSOR_ID:
					list = getSensorList();
					break;
				case INSTRUMENT_ID:
					list = getInstrumentList();
					break;
			}
		}
		
		return list;
	}
	
	/**
	 * the dropdown pair list for measurement unit id
	 */
	private List<Pair<String,String>> getMeasurementUnitList() throws SPECCHIOClientException {
		List<Pair<String,String>> list = new LinkedList<>();
		CategoryTable categoryValues = specchio_client.getMetadataCategoriesForIdAccess("measurement_unit");
		for(Integer key : categoryValues.getHashtable().keySet()){
			list.add(new Pair<String,String>(key+"",categoryValues.getHashtable().get(key)));
		}
		return list;
	}
	
	/**
	 * the dropdown pair list for sensor id
	 */
	private List<Pair<String,String>> getSensorList() throws SPECCHIOClientException {
		List<Pair<String,String>> list = new LinkedList<>();
		for(Sensor s : specchio_client.getSensors()){
			list.add(new Pair<String,String>(s.getSensorId()+"",s.getName().toString()));
		}
		return list;
	}
	
	/**
	 * the dropdown pair list for instrument id
	 */
	private List<Pair<String,String>> getInstrumentList() throws SPECCHIOClientException {
		List<Pair<String,String>> list = new LinkedList<>();
		for(InstrumentDescriptor desc : specchio_client.getInstrumentDescriptors()){
			list.add(new Pair<String,String>(desc.getInstrumentId()+"", desc.getInstrumentName()));
		}
		return list;
	}
	
	/**
	 * the dropdown pair list for attributes with default storage field = taxonomy
	 */
	private List<Pair<String,String>> getTaxonomyList(Attribute attr) throws SPECCHIOClientException {
		List<Pair<String,String>> list = new LinkedList<>();
		Hashtable<String, Integer> taxonomyHash = specchio_client.getTaxonomyHash(attr.getId());
		for(String name : taxonomyHash.keySet()){
			list.add(new Pair<String, String>(taxonomyHash.get(name)+"", name));
		}
		return list;
	}

	/**
	 * returns a List of all spectrum id's that were found for the given search criteria.
	 * @param searchRowBeanList - contains the search criteria of each row from the search view
	 */
	public ArrayList<Integer> getSpectrumIdList(List<SearchRowBean> searchRowBeanList) throws SPECCHIOClientException {
		ArrayList<Integer> ids = new ArrayList<>();
		
		if(searchRowBeanList == null || searchRowBeanList.isEmpty()) return ids;	// return an empty list if searchRowBeanList is null or empty
		
		// this list contains a list for each search row with the full text search category selected
		// each list contains all spectrum id's that matched the full text search of that row
		List<List<Integer>> fullTextSearchResultList = new LinkedList<>();
		
		// this list contains a query condition for each search row that wasnt a full text search
		List<QueryCondition> queryConditionList = new LinkedList<>();
		
		for(SearchRowBean srb : searchRowBeanList){	// iterate over all SearchRowBean's
			if(srb != null && srb.getSelectedCategory() != null){
				// for full text search we get all spectrum id's that matched and add them to the list
				if(FULL_TEXT_SEARCH.equals(srb.getSelectedCategory().getName())){	
					fullTextSearchResultList.add(doFullTextSearch(srb));
				}
				// for all other rows we create a QueryCondition with the user input for that row and add it to the list
				else if(srb.getSelectedAttribute() != null){
					Map<String, String> spectrumQueryConditionMap = createSpectrumQueryConditionMap();
					
					// attributes that need a spectrum query condition
					if(spectrumQueryConditionMap.containsKey(srb.getSelectedAttribute().getName()))
						queryConditionList.add(createSpectrumQueryCondition(srb, spectrumQueryConditionMap.get(srb.getSelectedAttribute().getName())));
					// all other attributes need an eav query condition
					else queryConditionList.addAll(createEAVQueryCondition(srb));
				}
			}
		}
		
		// if there only was a fulltext search we can just return the spectrum id's for that fulltext search
		if(fullTextSearchResultList.size() == 1 && queryConditionList.isEmpty())
			ids = (ArrayList<Integer>) fullTextSearchResultList.get(0);
		else {  // else we have to combine all conditions together and return the id's matching those conditions
			Query query = new Query("spectrum"); 
			query.addColumn("spectrum_id");
			query.setQueryType(Query.SELECT_QUERY); 
			
			// Always do FullTextSearch first
			for(List<Integer> idList : fullTextSearchResultList){
				if(idList == null || idList.isEmpty()) return ids;
				
				QueryCondition cond = createFullTextSearchCondition(query, idList);
				query.add_join("spectrum", cond);
				query.add_condition(cond);
			}
			// add all conditions
			for(QueryCondition cond : queryConditionList){
				query.add_condition(cond);
			}
			ids = specchio_client.getSpectrumIdsMatchingQuery(query);
		}
		
		return ids;
	}
	
	/**
	 * contains all attributes for which a spectrum query condition is necessary.
	 * key = attribute name, value = name for spectrum query condition
	 * ex.: Measurement Unit Id - measurement_unit_id
	 */
	private Map<String, String> createSpectrumQueryConditionMap(){
		Map<String, String> spectrumQueryConditionMap = new HashMap<>();
		spectrumQueryConditionMap.put(MEASUREMENT_UNIT_ID, "measurement_unit_id");
		spectrumQueryConditionMap.put(SENSOR_ID, "sensor_id");
		spectrumQueryConditionMap.put(INSTRUMENT_ID, "instrument_id");
		return spectrumQueryConditionMap;
	}
	
	/**
	 * returns a list of all SearchResultBean's for the given SearchRowBean's
	 */
	public List<SearchResultBean> getAllSearchResults(List<SearchRowBean> searchRowBeanList) throws SPECCHIOClientException {
		return getAllSearchResults(getSpectrumIdList(searchRowBeanList));
	}
	
	/**
	 * returns a list of all SearchResultBean's for the given spectrum id's
	 */
	public List<SearchResultBean> getAllSearchResults(ArrayList<Integer> ids) throws SPECCHIOClientException {
		List<SearchResultBean> srbList = new LinkedList<>();
		
		if(ids == null || ids.isEmpty()) return srbList; // return an empty list if ids are null or empty
		
		// get the spectrum id's sorted by 'Acquisition Time'
		Space[] spaces = getSpaces(ids, "Acquisition Time");
		for(Space s : spaces){
			SpectralSpace space = (SpectralSpace) s;
			ids = space.getSpectrumIds(); 
			// create a SearchResultBean for each id and add them to the list
			srbList.addAll(createSearchResultBeanList(ids));
		}
		
		return srbList;
	}
	
	/**
	 * returns an array of Space objects for the given spectrum id's and sorted by 
	 * the given attribute name
	 */
	public Space[] getSpaces(ArrayList<Integer> ids, String sortByAttribute) throws SPECCHIOClientException {
		return specchio_client.getSpaces((ArrayList<Integer>) ids, sortByAttribute);
	}
	
	/**
	 * returns the vectors of the given space
	 */
	public double[][] getVectors(SpectralSpace space) throws SPECCHIOClientException {
		space = (SpectralSpace) specchio_client.loadSpace(space);
		return space.getVectorsAsArray();
	}
	
	/**
	 * returns the wavelength of the given space
	 */
	public double[] getWavelength(SpectralSpace space) throws SPECCHIOClientException {
		space = (SpectralSpace) specchio_client.loadSpace(space);
		return space.getAverageWavelengths();
	}
	
	/**
	 * returns a list containing a SearchResultBean for each given spectrum id.
	 */
	private List<SearchResultBean> createSearchResultBeanList(List<Integer> ids) throws SPECCHIOClientException {
		List<SearchResultBean> srbList = new LinkedList<>();
		
		for (Integer id : ids){
			srbList.add(new SearchResultBean(id));
		}
		
		// these attributes are displayed on the search result page
		List<String> resultTableAttributes = new ArrayList<String>();
		resultTableAttributes.add("Acquisition Time");
		resultTableAttributes.add("Investigator");
		resultTableAttributes.add("File Name");
		resultTableAttributes.add("Latitude");
		resultTableAttributes.add("Longitude");
		
		// fill meta parameter's for the above defined attributes
		fillMetaParameters(resultTableAttributes, ids, srbList);
		
		
		// some values need to be modified
		for(SearchResultBean srb : srbList){
			// change format of Acquisition Time for each bean
			srb.setAcquisitionTime(changeDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",
					"yyyy-MM-dd HH:mm:ss",srb.getAcquisitionTime()));
			
			// as wished by customer, the sign for longitude should always be changed
			// therefore we multiply by -1
			try{
				double lng = Double.valueOf(srb.getLongitude());
				srb.setLongitude((-1) * lng + "");
			}catch(Exception e){ /* use the value that came from api */ }
		}
		
		// fill meta parameter's for special cases - Campaign Name, User, Name & Institute
		fillMetaParameterSpecialCases(ids, srbList);
		
		return srbList;
	}
	
	
	/**
	 * changes the format of the given date
	 * @param fromFormat = oldFormat of date
	 * @param toFormat = desired new format of date
	 * @param date = date string
	 */
	private String changeDateFormat(String fromFormat, String toFormat, String date){
		try{
			// format "2000-07-14T13:15:15:111Z" -> "2000-07-14 13:15:15"
			SimpleDateFormat fFormat = new SimpleDateFormat(fromFormat);
			SimpleDateFormat tFormat = new SimpleDateFormat(toFormat);
			Date d = fFormat.parse(date);
			return tFormat.format(d);
		}catch(Exception e){
			// leave date as it is
			return date;
		}
	}
	 
	/**
	 * returns a list with the attribute id of each given attribute name
	 */
	private ArrayList<Integer> getAttributeIds(List<String> attributeNames){
		ArrayList<Integer> attributeIds = new ArrayList<Integer>();
		for(String attributeName : attributeNames){
			if(getAttributeId(attributeName) != -1)
				attributeIds.add(getAttributeId(attributeName));
		}
		return attributeIds;
	}
	
	
	/**
	 * returns the attribute id of the attribute with the given name
	 */
	private int getAttributeId(String attributeName){
		int attributeId = -1;
		
		if(specchio_client.getAttributesNameHash().get(attributeName) != null)
			attributeId = specchio_client.getAttributesNameHash().get(attributeName).getId();
		
		return attributeId;
	}
	
	/**
	 * returns the name of the Setter-Method for the given attributeName
	 */
	private String getSetterName(String attributeName){
		if(attributeName == null) return "";
		
		// characters that need to be replaced or removed
		attributeName = attributeName.replace(" ", "");
		attributeName = attributeName.replace("%", "");
		attributeName = attributeName.replace("(", "");
		attributeName = attributeName.replace(")", "");
		attributeName = attributeName.replace(".", "");
		attributeName = attributeName.replace("-", "_");
		attributeName = attributeName.replace("/", "_");
		
		return "set" + attributeName;
	}
	
	/**
	 * Gets the values of the given attribute names for each spectrum id. 
	 * Then fills the values inside the SearchResultBeans by using
	 * reflection to call the setter methods.
	 */
	private void fillMetaParameters(List<String> attributeNames, List<Integer> spectrumIds, List<SearchResultBean> srbList) throws SPECCHIOClientException {
		// list containing a list with the values of each given attributeName for each spectrum id
		ArrayList<ArrayList<MetaParameter>> resultList = specchio_client.getMetaparameters((ArrayList<Integer>)spectrumIds, getAttributeIds(attributeNames));
		if(resultList == null) return;
		
		Class[] paramString = new Class[1];
		paramString[0] = String.class;
		
		for(int i = 0; i < resultList.size(); i++){
			String attributeName = attributeNames.get(i);
			
			for(int j = 0; j < resultList.get(i).size(); j++){
				MetaParameter mp = resultList.get(i).get(j);
				try { 
					Class<?> c = Class.forName("ch.specchio.model.SearchResultBean");
					Method method = c.getDeclaredMethod(getSetterName(attributeName), paramString);
					method.invoke(srbList.get(j), mp != null && mp.getValue() != null 
							? mp.getValue().toString() : "");
				} catch (Exception e) { }
			}
		}
	}
	
	/**
	 * Fills the meta data for all attributes that don't just use the value received from
	 * the getMetaparameterValues() method.
	 * Those are currently: Campaign Name, User, Name and Institute
	 */
	private void fillMetaParameterSpecialCases(List<Integer> ids, List<SearchResultBean> srbList) throws SPECCHIOClientException {

		// Getting common and latin names for each id
		for(int i=0; i <ids.size(); i++) {
			
			// List of 1 element, i to be passed to specchio_client methods
			ArrayList<Integer> i_list = new ArrayList<Integer>();
				i_list.add(i);
			
			List<Object> commonList = specchio_client.getMetaparameterValues((ArrayList<Integer>) i_list, "Common");
			List<Object> latinList = specchio_client.getMetaparameterValues((ArrayList<Integer>) i_list, "Latin");
						
			Object common = "";
			
			// Not every id has a name match to common and latin - blank string if no name
			if(commonList.isEmpty()) {
				common = "";	
			}
			
			else {
				common = commonList.get(0);				
			}
			
			Object latin = "";
			
			if(latinList.isEmpty()) {
				latin = "";	
			}
			
			else {
				latin = latinList.get(0);				
			}
			
			// Assigning final name based on whether common and latin match exists
			String name = "";
			name += common == null ? "" : common.toString();
			name += latin == "" ? "" : name == "" ? latin : " (" + latin + ")";
			
			Spectrum spectrum = specchio_client.getSpectrum(ids.get(i), false);			// get the spectrum for the current id
			Campaign campaign = specchio_client.getCampaign(spectrum.getCampaignId());	// get the campaign of the current spectrum
			SearchResultBean srb = srbList.get(i);										// get the current SearchResultBean

			srb.setName(name);
			
			if(campaign != null) { 
				srb.setCampaignName(campaign.getName()); // Campaign Name
				if(campaign.getUser() != null){
					// if the investigator from getMetaparameterValues() is empty we use the information of the user
					if(srb.getInvestigator() == null || srb.getInvestigator().isEmpty()){
						String fn = campaign.getUser().getFirstName();	// firstname
						String ln = campaign.getUser().getLastName();	// lastname
						String inv = 	fn != null && !fn.isEmpty() ? fn + " " : "" +
										ln != null && !ln.isEmpty() ? ln : "";
						// Investigator - is either 'firstname', 'lastname' or 'firstname lastname'
						srb.setInvestigator(inv.trim()); // trim to remove whitespaces at the beginning and end 
					}
					
					if(campaign.getUser().getInstitute() != null){
						srb.setInstitute(campaign.getUser().getInstitute().getInstituteName()); // Institute
					}
				}
			}
		}
		
	}

	
	/**
	 * creates a eav query condition for the given SearchRowBean
	 */
	private List<QueryCondition> createEAVQueryCondition(SearchRowBean srb) {
		attribute attr = srb.getSelectedAttribute().getAttribute();
		List<QueryCondition> condList = new LinkedList<>();
		EAVQueryConditionObject cond = null;
		
		String dsf = srb.getSelectedAttribute().getDefaultStorageField();
		
		// string values only have one user input and use the operator 'like'
		if("string_val".equals(dsf)){
			cond = new EAVQueryConditionObject(attr);
			cond.setValue(srb.getUserInput1());
			cond.setOperator("like");
			condList.add(cond);
		}
		// int, double and datetime values have two user inputs (from and to) and use the operators '>=' and '<='
		else if("int_val".equals(dsf) || "double_val".equals(dsf) || "datetime_val".equals(dsf)){
			cond = new EAVQueryConditionObject(attr);
			cond.setValue(srb.getUserInput1());
			cond.setOperator(">=");
			condList.add(cond);
			
			cond = new EAVQueryConditionObject(attr);
			cond.setValue(srb.getUserInput2());
			cond.setOperator("<=");
			condList.add(cond);
		}
		// taxonomy values only have one user input and use the operator '='
		else if("taxonomy_id".equals(dsf)){
			cond = new EAVQueryConditionObject(attr);
			cond.setValue(srb.getUserInput1());
			cond.setOperator("=");
			condList.add(cond);
		}
		
		return condList;
	}

	/**
	 * creates a spectrum query condition
	 */
	private QueryCondition createSpectrumQueryCondition(SearchRowBean srb, String idType) {
		SpectrumQueryCondition cond = new SpectrumQueryCondition("spectrum", idType); 
		cond.setValue(srb.getUserInput1()); 
		cond.setOperator("="); 
		return cond;
	}
	
	/**
	 * creates a full text search query condition
	 */
	private QueryCondition createFullTextSearchCondition(Query query, List<Integer> ids){
		SpectrumQueryCondition cond = new SpectrumQueryCondition("spectrum", "spectrum_id"); 
		cond.setValue(ids);
		cond.setOperator("in");
		return cond;
	}
	
	/**
	 * returns the number of spectrum ids in the db
	 */
	public int getSpectrumIdCount() throws SPECCHIOClientException {
		return specchio_client.getSpectrumCountInDB();
	}
	
	/**
	 * returns all spectrum id's of the spectra matching the full text search
	 */
	private List<Integer> doFullTextSearch(SearchRowBean srb) throws SPECCHIOClientException {
		if(srb == null) return new ArrayList<Integer>();
		List<Integer> idList = specchio_client.getSpectrumIdsMatchingFullTextSearch(srb.getUserInput1());
		return idList != null ? idList : new ArrayList<Integer>();
	}

	/**
	 * This method is used for the csv export of the detail.jsp.
	 * It takes a list of spectrum id's as parameter and creates a map.
	 * The map contains: key = attribute name & value = a list of the value of each spectrum for that attribute.
	 */
	public Map<String, List<String>> getAttributeValueListMap(ArrayList<Integer> idList){
		
		Map<String, List<String>> attributeValueListMap = new HashMap<String, List<String>>();
		List<Map<String, String>> attributeMapList = new LinkedList<>();
		
		for(int i = 0; i < idList.size(); i++){
			
			// get categoryAttributeMap for an id
			Map<String, List<Pair<String, String>>> temp = getCategoryAttributeMap(idList.subList(i, i+1));
			
			// collect all attribute names and values from each category and store them in attributeMapList
			Map<String, String> attributeMap = new HashMap<>();
			for(List<Pair<String, String>> list : temp.values()){
				for(Pair<String, String> p : list){
					attributeMap.put(p.getFirst(), p.getSecond());
				}
			}
			attributeMapList.add(attributeMap);
			
			// add an empty map entry for each attribute that exists for an id
			// this is necessary because not every spectrum has the same attributes filled
			for(Map<String, String> attrMap : attributeMapList){
				for(String key : attrMap.keySet()){
					if(attributeValueListMap.get(key) == null){
						attributeValueListMap.put(key, new LinkedList<String>());
					}
				}
			}
			
		}
		
		// for every attributeName that existed in at least one attribute,
		// we add the value of each attribute or "" if an attribute name
		// doesn't exist in the an attribute
		for(String attributeName : attributeValueListMap.keySet()){
			for(Map<String, String> attributeMap : attributeMapList){
				
				String attributeValue = attributeMap.get(attributeName);
				attributeValueListMap.get(attributeName).add(attributeValue == null ? "" : attributeValue);
				
			}
		}
		
		return attributeValueListMap;
	}
	
	/**
	 * returns a map with KEY = category name and VALUE = a list of attribute pairs 
	 * containing the attribute name and the attribute value.
	 */
	public Map<String, List<Pair<String,String>>> getCategoryAttributeMap(List<Integer> idList) throws SPECCHIOClientException {
		
		Map<String, List<Pair<String,String>>> map = new HashMap<>();
		if(idList == null || idList.isEmpty()) return map; // return an empty Map if idList is null or empty
		
		// need to get the first spectrum so that we can display non-conflicting values
		Spectrum s = specchio_client.getSpectrum(idList.get(0), false);
		
		// One Spectrum
		if(idList.size() == 1){
			
			int fileCount = 0; // count of metaFiles for this Spectrum
			for(MetaParameter mp : s.getMetadata().getEntries()){ // iterate over the metaparameters
				
				// create a new map entry for the current category if there isn't one already
				if(map.get(mp.getCategoryName()) == null) {
					map.put(mp.getCategoryName(), new LinkedList<Pair<String,String>>());
				}
				
				// get the metaparameter value or "" if it is null
				String value = mp.getValue() != null ? mp.getValue().toString() : "";
				
				// for PDF's and Pictures we create a file. the value will be the filename.
				if("PDFs".equals(mp.getCategoryName()) || "Pictures".equals(mp.getCategoryName())){
					MetaFile mp_file = (MetaFile) mp; // get MetaFile
					try { 
						// create the filename for the current file with the correct extension (ex. pdf)
						String filename = "metaFile" + fileCount + mp_file.getDefaultFilenameExtension();
						value = IOUtil.createTempFile(filename, mp_file); // create file
						fileCount++;
					} catch(Exception e) { // if the file cannot be created we use the metaparameter value
						value = mp.getValue().toString();
					}
				}
				// as wished by customer, the sign for longitude should always be changed
				// therefore we multiply by -1
				else if("Longitude".equals(mp.getAttributeName())){
					try {
						value = (-1) * Double.valueOf(mp.getValue().toString()) + "";
					}
					catch(Exception e){/* use the metaparameter value that came from api */}
				}
				// change format of date values
				else if("Acquisition Time".equals(mp.getAttributeName()) || 
						"Loading Time".equals(mp.getAttributeName()) || 
						"Sample Collection Date".equals(mp.getAttributeName())){
					value = changeDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'","yyyy-MM-dd HH:mm:ss", value);
				}
				// add a new pair with attribute name and attribute value to the list inside the map
				map.get(mp.getCategoryName()).add(new Pair<>(mp.getAttributeName(), value));
			}
			
		}
		// Multiple Spectra
		else {
			
			// add EAV parameters including their conflict status
			ConflictTable eav_conflict_stati = specchio_client.getEavMetadataConflicts(1, (ArrayList<Integer>) idList);

			for(MetaParameter mp : s.getMetadata().getEntries()){ // iterate over the metaparameters
				
				// create a new map entry for the current category if there isn't one already
				if(map.get(mp.getCategoryName()) == null) {
					map.put(mp.getCategoryName(), new LinkedList<Pair<String,String>>());
				}
				
				// get the conflict status
				ConflictInfo  conflict = eav_conflict_stati.get(mp.getAttributeId());

				ConflictStruct cs = conflict.getConflictData(mp.getEavId());
				int status = cs != null ? cs.getStatus() : 0;
				
				
				if(status == 3) {	// no conflict -> we can use the metaparameter value
					map.get(mp.getCategoryName()).add(new Pair<>(mp.getAttributeName(), mp.getValue().toString()));
				}
				else {	// conflict -> there are multiple values
					map.get(mp.getCategoryName()).add(new Pair<>(mp.getAttributeName(), "Multiple Values"));
				}
				
			}
			
		}
		
		return map;
	}
	
	/**
	 * returns a list containing a SpaceDetailBean for each given SearchResultBean
	 */
	public List<SpaceDetailBean> createSpaceDetailBeanList(List<SearchResultBean> srbList) throws SPECCHIOClientException {
		return createSpaceDetailBeanList(extractSpectrumIds(srbList));
	}
	
	/**
	 * returns a list of the spectrum id's of the SearchResultBean's
	 */
	private ArrayList<Integer> extractSpectrumIds(List<SearchResultBean> srbList){
		ArrayList<Integer> ids = new ArrayList<>();
		for(SearchResultBean srb : srbList)
			ids.add(srb.getId());
		return ids;
	}

	
	/**
	 * returns a list containing a SpaceDetailBean for the given spectrum id
	 */
	public List<SpaceDetailBean> createSpaceDetailBeanList(int spectrumId) throws SPECCHIOClientException {
		ArrayList<Integer> idList = new ArrayList<>();
		idList.add(spectrumId);
		return createSpaceDetailBeanList(idList);
	}

	/**
	 * returns a list containing a SpaceDetailBean for each given spectrum id
	 */
	private List<SpaceDetailBean> createSpaceDetailBeanList(ArrayList<Integer> spectrumIdList) throws SPECCHIOClientException {
		
		List<SpaceDetailBean> sdbList = new LinkedList<>();
		Space[] spaces = getSpaces(spectrumIdList, "Acquisition Time"); // sorted by Acquisition Time
		
		for(int i = 0; i < spaces.length; i++){ // iterate over spaces
			
			Space space = spaces[i]; // get current space
			
			// Wavelength
			ChartDataBean wavelength = new ChartDataBean("wavelength", getWavelength((SpectralSpace) space));
			
			// Vectors
			List<ChartDataBean> vectorList = new LinkedList<>();
			// get filenames of each spectrum
			MatlabAdaptedArrayList<Object> resultList = specchio_client.getMetaparameterValues(space.getSpectrumIds(), "File Name");
			double[][] vectors = getVectors((SpectralSpace) space);
			for(int ii = 0; ii < vectors.length; ii++){ // iterate over vectors
				// add a new ChartDataBean containing the filename (description on chart) and all vector values
				vectorList.add(new ChartDataBean(resultList.get(ii).toString(),vectors[ii]));
			}
			
			// Latitude & Longitude for Map on detail.jsp
			MatlabAdaptedArrayList<Object> lats = specchio_client.getMetaparameterValues(spectrumIdList, "Latitude");
			MatlabAdaptedArrayList<Object> longs = specchio_client.getMetaparameterValues(spectrumIdList, "Longitude");
			
			// List containing pairs of latide and longitude
			List<Pair<Double, Double>> latLongList = new ArrayList<>();
			if(lats.size() == longs.size()) { // only if both are the same size
				for(int ii = 0; ii < lats.size(); ii++){
					Object lat = lats.get(ii); 	// latitude
					Object lng = longs.get(ii); // longitude
					
					if(lat != null && lng != null){
						try{ // create a new latLong Pair
							
							// as wished by customer, all longitudes have to be multiplied by -1
							double invertedLng = (-1) * Double.parseDouble(lng.toString());
							
							latLongList.add(new Pair<Double, Double>(
									Double.parseDouble(lat.toString()), 
									invertedLng));
						} catch (NumberFormatException e) {
							// don't add anything to the list 
						}
					}
				}
			}

			// Get the maxY for the spectralchart on the details.jsp
			String unitName = ((SpectralSpace) space).getMeasurementUnit().getUnitName();
			double maxY = 0;
			if("Reflectance".equals(unitName)){ // for Reflectance we calculate the maxY
				// do statistics for VNIR
				double vis_nir_start = 300;
				double vis_nir_end = 1300;

				int start_ind = ((SpectralSpace) space).get_index_of_band(vis_nir_start);
				int end_ind = ((SpectralSpace) space).get_index_of_band(vis_nir_end);

				if (start_ind >= 0 & end_ind > 0) {

					VectorStatistics stats = new VectorStatistics();

					ArrayList<double[]> vectorArray = new ArrayList<>();
					for(double[] arr : vectors) vectorArray.add(arr);
					stats.calc_stats(vectorArray, start_ind, end_ind);

					if (stats.standardDeviation() > 0){
						maxY = stats.mean()+1*stats.standardDeviation();
					}
				}
			}
			// Otherwise we pick the highest vector as the maxY
			if(maxY == 0) maxY = getHighest(vectors);
			
			// add a new SpaceDetailBean to the List
			sdbList.add(new SpaceDetailBean(space.getSpaceTypeName() + " " + (i+1), unitName, wavelength, vectorList, getCategoryAttributeMap(space.getSpectrumIds()), space.getSpectrumIds(), latLongList, maxY));
		}
		
		return sdbList;
	}
	
	/**
	 * returns the highest double value inside the double[][]
	 */
	private double getHighest(double[][] arrays){
		double max = 0;
		for(double[] array : arrays){
			for(double d : array){
				if(d > max) max = d;
			}
		}
		return max;
	}
	
}
