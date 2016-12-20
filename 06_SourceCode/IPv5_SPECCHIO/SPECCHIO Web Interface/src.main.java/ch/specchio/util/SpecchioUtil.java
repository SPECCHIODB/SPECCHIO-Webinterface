package ch.specchio.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Method;
import java.math.RoundingMode;
import java.net.URISyntaxException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import ch.specchio.client.SPECCHIOClient;
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
import ch.specchio.spaces.MeasurementUnit;
import ch.specchio.spaces.Space;
import ch.specchio.spaces.SpectralSpace;
import ch.specchio.types.Campaign;
import ch.specchio.types.CategoryTable;
import ch.specchio.types.ConflictInfo;
import ch.specchio.types.ConflictTable;
import ch.specchio.types.InstrumentDescriptor;
import ch.specchio.types.MatlabAdaptedArrayList;
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
	
	private final int DISPLAYED_SEARCH_RESULTS 	= 5;
	
	private SPECCHIOClient specchio_client;
	
	public SpecchioUtil() {
		
		try {
			DbConfigUtil.loadDbConfig();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		connectToDB();
	}
	
	/**
	 * Connect to DB and get the specchio_client
	 */
	private void connectToDB(){
		SPECCHIOClientFactory cf = SPECCHIOClientFactory.getInstance();
		List<SPECCHIOServerDescriptor> db_descriptor_list = cf.getAllServerDescriptors();
		this.specchio_client = cf.createClient(db_descriptor_list.get(0));
	}
	
	private Map<String, String> createSpectrumQueryConditionMap(){
		Map<String, String> spectrumQueryConditionMap = new HashMap<>();
		spectrumQueryConditionMap.put(MEASUREMENT_UNIT_ID, "measurement_unit_id");
		spectrumQueryConditionMap.put(SENSOR_ID, "sensor_id");
		spectrumQueryConditionMap.put(INSTRUMENT_ID, "instrument_id");
		return spectrumQueryConditionMap;
	}
	
	public List<Category> getCategoryList(){
		List<Category> categoryList = new LinkedList<>();
		categoryList.add(new Category(FULL_TEXT_SEARCH));
		categoryList.add(new Category(MOST_WANTED));

		// Categories that shouldn't be displayed in the Category Dropdown
		List<String> ignoredCategories = new LinkedList<>();
		ignoredCategories.add("Data Links");
		ignoredCategories.add("PDFs");
		ignoredCategories.add("Pictures");
		
		for(ch.specchio.types.Category c : specchio_client.getCategoriesInfo()) {
			if(!ignoredCategories.contains(c.name)) categoryList.add(new Category(c));
		}
		
		return categoryList;
	}
	
	public Category getCategory(String name){
		for(Category c : getCategoryList()){
			if(name != null && name.equals(c.getName())) return c;
		}
		return null;
	}
	
	private Map<String, List<Pair<String,String>>> createCategoryAttributesMap(){
		Map<String, List<Pair<String,String>>> categoryAttributesMap = new HashMap<>();

		for(Category c : getCategoryList()){
			List<Pair<String,String>> attributeNameList = new LinkedList<>();
			for(Attribute a : getAttributeList(c)){
				attributeNameList.add(new Pair<String, String>(a.getName(), getFieldName(a.getName())));
			}
			categoryAttributesMap.put(c.getName(), attributeNameList);
		}
		
		return categoryAttributesMap;
	}
	
	public List<Attribute> getAttributeList(String category){
		attribute[] attributes = {};
		
		if(FULL_TEXT_SEARCH.equals(category)){
			List<Attribute> list = new LinkedList<>();
			list.add(new Attribute("","string_val"));
			return list;
		}
		else if(MOST_WANTED.equals(category)){
			return getMostWantedAttributes();
		}
		else attributes = specchio_client.getAttributesForCategory(category);
		
		List<Attribute> attributeList = new LinkedList<>();
		for(attribute a : attributes) attributeList.add(new Attribute(a));
		
		if("General".equals(category)){
			attributeList.add(new Attribute(MEASUREMENT_UNIT_ID, "drop_down"));
			Collections.sort(attributeList);
		}
		else if("Instrument".equals(category)) {
			attributeList.add(new Attribute(SENSOR_ID, "drop_down"));
			attributeList.add(new Attribute(INSTRUMENT_ID, "drop_down"));
			Collections.sort(attributeList);
		}

		return attributeList;
	}
	
	public List<Attribute> getAttributeList(Category category){
		return getAttributeList(category.getName());
	}
	
	public Attribute getAttribute(String attribute, String category){
		if(attribute != null) {
			for(Attribute a : getAttributeList(category)){
				if(attribute.equals(a.getName())) 
					return a;
			}
		}
		return getFirstAttribute(category);
	}
	
	public Attribute getFirstAttribute(String category){
		return getAttributeList(category).isEmpty() ? null : getAttributeList(category).get(0);
	}
	
	private List<Attribute> getMostWantedAttributes(){
		List<Attribute> attributeList = new LinkedList<>();
		attributeList.add(new Attribute(MEASUREMENT_UNIT_ID, "drop_down"));
		attributeList.add(new Attribute(SENSOR_ID, "drop_down"));
		attributeList.add(new Attribute(INSTRUMENT_ID, "drop_down"));
		
		return attributeList;
	}

	public List<Pair<String,String>> getInputDropdownValues(Attribute attr) {
		List<Pair<String,String>> list = new LinkedList<Pair<String,String>>();
			
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
		
		return list;
	}
	
	private List<Pair<String,String>> getMeasurementUnitList(){
		List<Pair<String,String>> list = new LinkedList<>();
		CategoryTable categoryValues = specchio_client.getMetadataCategoriesForIdAccess("measurement_unit");
		for(Integer key : categoryValues.getHashtable().keySet()){
			list.add(new Pair<String,String>(key+"",categoryValues.getHashtable().get(key)));
		}
		return list;
	}
	
	private List<Pair<String,String>> getSensorList(){
		List<Pair<String,String>> list = new LinkedList<>();
		for(Sensor s : specchio_client.getSensors()){
			list.add(new Pair<String,String>(s.getSensorId()+"",s.getName().toString()));
		}
		return list;
	}
	
	private List<Pair<String,String>> getInstrumentList(){
		List<Pair<String,String>> list = new LinkedList<>();
		for(InstrumentDescriptor desc : specchio_client.getInstrumentDescriptors()){
			list.add(new Pair<String,String>(desc.getInstrumentId()+"", desc.getInstrumentName()));
		}
		return list;
	}

	public List<Pair<String,String>> getTaxonomyList(Attribute attr){
		List<Pair<String,String>> list = new LinkedList<>();
		Hashtable<String, Integer> taxonomyHash = specchio_client.getTaxonomyHash(attr.getId());
		for(String name : taxonomyHash.keySet()){
			list.add(new Pair<String, String>(taxonomyHash.get(name)+"", name));
		}
		return list;
	}

	public ArrayList<Integer> getSpectrumIdList(List<SearchRowBean> searchRowBeanList){
		ArrayList<Integer> ids = new ArrayList<>();
		
		if(searchRowBeanList == null || searchRowBeanList.isEmpty()) return ids;
		
		List<List<Integer>> fullTextSearchResultList = new LinkedList<>();
		List<QueryCondition> queryConditionList = new LinkedList<>();
		
		for(SearchRowBean srb : searchRowBeanList){
			
			if(srb != null && srb.getSelectedCategory() != null){
				if(FULL_TEXT_SEARCH.equals(srb.getSelectedCategory().getName())){
					fullTextSearchResultList.add(doFullTextSearch(srb));
				}
				else if(srb.getSelectedAttribute() != null){
					Map<String, String> spectrumQueryConditionMap = createSpectrumQueryConditionMap();
					if(spectrumQueryConditionMap.containsKey(srb.getSelectedAttribute())){
						queryConditionList.add(createSpectrumQueryCondition(srb, spectrumQueryConditionMap.get(srb.getSelectedAttribute())));
					}
					else{
						queryConditionList.addAll(createEAVQueryCondition(srb));
					}
				}
			}
			
		}
		
		if(fullTextSearchResultList.size() == 1 && queryConditionList.isEmpty()){
			ids = (ArrayList<Integer>) fullTextSearchResultList.get(0);
		}
		else {
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
			
			for(QueryCondition cond : queryConditionList){
				query.add_condition(cond);
			}
			ids = specchio_client.getSpectrumIdsMatchingQuery(query);
		}
		
		return ids;
	}
	
	public List<SearchResultBean> getSearchResults(int page, List<Integer> spectrumIdList){
		
		ArrayList<Integer> subList = new ArrayList<>();
		int startIndex = page * DISPLAYED_SEARCH_RESULTS;
		int endIndex = startIndex + DISPLAYED_SEARCH_RESULTS;
		
		for(int i = startIndex; i < endIndex; i++){
			if(i < spectrumIdList.size())
				subList.add(spectrumIdList.get(i));
			else break;
		}
		
		return getAllSearchResults(subList);
	}
	
	public List<SearchResultBean> getAllSearchResults(List<SearchRowBean> searchRowBeanList){
		return getAllSearchResults(getSpectrumIdList(searchRowBeanList));
	}
	
	public List<SearchResultBean> getAllSearchResults(ArrayList<Integer> ids){
		List<SearchResultBean> srbList = new LinkedList<>();
		
		if(ids == null || ids.isEmpty()) return srbList;
		
		Space[] spaces = getSpaces(ids, "Acquisition Time");
		for(Space s : spaces){
			SpectralSpace space = (SpectralSpace) s;
			ids = space.getSpectrumIds(); // get them sorted by 'Acquisition Time'

			srbList.addAll(createSearchResultBeanList(ids));
		}
		
		return srbList;
	}
	
	public ArrayList<Integer> extractSpectrumIds(List<SearchResultBean> srbList){
		
		ArrayList<Integer> ids = new ArrayList<>();
		for(SearchResultBean srb : srbList){
			ids.add(srb.getId());
		}
		
		return ids;
	}
	
	public Space[] getSpaces(ArrayList<Integer> ids, String sortBy){
		return specchio_client.getSpaces((ArrayList<Integer>) ids, sortBy);
	}
	
	public double[][] getVectors(SpectralSpace space){
		space = (SpectralSpace) specchio_client.loadSpace(space);
		return space.getVectorsAsArray();
	}
	
	public double[] getWavelength(SpectralSpace space){
		space = (SpectralSpace) specchio_client.loadSpace(space);
		return space.getAverageWavelengths();
	}
	
	private List<SearchResultBean> createSearchResultBeanList(List<Integer> ids){
		List<SearchResultBean> srbList = new LinkedList<>();
		
		for (Integer id : ids){
			srbList.add(new SearchResultBean(id));
		}
		 
		fillMetaParameter("Acquisition Time", getSetterName("Acquisition Time"), ids, srbList);
		fillMetaParameter("Investigator", getSetterName("Investigator"), ids, srbList);
		fillMetaParameter("File Name", getSetterName("File Name"), ids, srbList);
		
		// Campaign Name, User, Name & Institute
		fillMetaParameterSpecialCases(ids, srbList);
		
		return srbList;
	}
	
	
	private String getSetterName(String attributeName){
		return "set" + replaceCharactersFromAttributeName(attributeName);
	}
	
	private String getFieldName(String attributeName){
		String fieldName = replaceCharactersFromAttributeName(attributeName);
		if(!fieldName.isEmpty()){
			fieldName = fieldName.substring(0,1).toLowerCase() + fieldName.substring(1);
		}
		return fieldName;
	}
	
	private String replaceCharactersFromAttributeName(String attributeName){
		if(attributeName == null) return "";
		attributeName = attributeName.replace(" ", "");
		attributeName = attributeName.replace("%", "");
		attributeName = attributeName.replace("(", "");
		attributeName = attributeName.replace(")", "");
		attributeName = attributeName.replace(".", "");
		attributeName = attributeName.replace("-", "_");
		attributeName = attributeName.replace("/", "_");
		return attributeName;
	}
	
	private void fillMetaParameterSpecialCases(List<Integer> ids, List<SearchResultBean> srbList){
		
		List<Object> commonList = specchio_client.getMetaparameterValues((ArrayList<Integer>) ids, "Common");
		List<Object> latinList = specchio_client.getMetaparameterValues((ArrayList<Integer>) ids, "Latin");
		
		for(int i = 0; i < ids.size(); i++){
			Spectrum spectrum = specchio_client.getSpectrum(ids.get(i), false);
			Campaign campaign = specchio_client.getCampaign(spectrum.getCampaignId());
			SearchResultBean srb = srbList.get(i);
			
			Object common = commonList.get(i);
			Object latin = latinList.get(i);
			
			String name = "";
			name += common == null ? "" : common.toString();
			name += latin == null ? "" : name.isEmpty() ? latin : "(" + latin + ")";
			srb.setName(name);	// Name
			
			if(campaign != null) { 
			srb.setCampaignName(campaign.getName()); // Campaign Name
				if(campaign.getUser() != null){
					if(srb.getInvestigator() == null || srb.getInvestigator().isEmpty()){
						String fn = campaign.getUser().getFirstName();
						String ln = campaign.getUser().getLastName();
						String inv = 	fn != null && !fn.isEmpty() ? fn + " " : "" +
										ln != null && !ln.isEmpty() ? ln : "";
						srb.setInvestigator(inv); // User
					}
					
					if(campaign.getUser().getInstitute() != null){
						srb.setInstitute(campaign.getUser().getInstitute().getInstituteName()); // Institute
					}
				}
			}
		}
		
	}
	
	private void fillMetaParameter(String metaType, String methodName, List<Integer> ids, List<SearchResultBean> srbList) {
		MatlabAdaptedArrayList<Object> resultList = specchio_client.getMetaparameterValues((ArrayList<Integer>) ids, metaType);
		
		Class[] paramString = new Class[1];
		paramString[0] = String.class;
		
		for(int i = 0; i < resultList.size(); i++){
			Object o = resultList.get(i);
			try { 
				Class<?> c = Class.forName("ch.specchio.model.SearchResultBean");
				Method method = c.getDeclaredMethod(methodName, paramString);
				method.invoke(srbList.get(i), o != null ? o.toString() : "");
			} catch (Exception e) { }
		}
	}

	private List<QueryCondition> createEAVQueryCondition(SearchRowBean srb) {
		attribute attr = srb.getSelectedAttribute().getAttribute();
		List<QueryCondition> condList = new LinkedList<>();
		EAVQueryConditionObject cond = null;
		
		String dsf = srb.getSelectedAttribute().getDefaultStorageField();
		
		if("string_val".equals(dsf) || "datetime_val".equals(dsf)){
			cond = new EAVQueryConditionObject(attr);
			cond.setValue(srb.getUserInput1());
			cond.setOperator("like");
			condList.add(cond);
		}
		else if("int_val".equals(dsf) || "double_val".equals(dsf)){
			cond = new EAVQueryConditionObject(attr);
			cond.setValue(srb.getUserInput1());
			cond.setOperator(">=");
			condList.add(cond);
			
			cond = new EAVQueryConditionObject(attr);
			cond.setValue(srb.getUserInput2());
			cond.setOperator("<=");
			condList.add(cond);
		}
		else if("taxonomy_id".equals(dsf)){
			cond = new EAVQueryConditionObject(attr);
			cond.setValue(srb.getUserInput1());
			cond.setOperator("=");
			condList.add(cond);
		}
		
		return condList;
	}

	private QueryCondition createSpectrumQueryCondition(SearchRowBean srb, String idType) {
		SpectrumQueryCondition cond = new SpectrumQueryCondition("spectrum", idType); 
		cond.setValue(srb.getUserInput1()); 
		cond.setOperator("="); 
		return cond;
	}
	
	private QueryCondition createFullTextSearchCondition(Query query, List<Integer> ids){
		SpectrumQueryCondition cond = new SpectrumQueryCondition("spectrum", "spectrum_id"); 
		cond.setValue(ids);
		cond.setOperator("in");
		return cond;
	}
	
	/**
	 * returns the number of spectrum ids in the db
	 */
	public int getSpectrumIdCount() {
		// to get all ids we do a fulltextsearch for empty string
		// maybe later there will be an api method to get the id count
		return getAllSpectrumIds() != null ? getAllSpectrumIds().size() : 0;
	}
	
	/**
	 * returns a List of all spectrum ids in the db
	 */
	public List<Integer> getAllSpectrumIds(){
		// to get all ids we do a fulltextsearch for empty string
		// maybe later there will be an api method to get the id count
		return specchio_client.getSpectrumIdsMatchingFullTextSearch("");
	}

	private List<Integer> doFullTextSearch(SearchRowBean srb) {
		List<Integer> idList = specchio_client.getSpectrumIdsMatchingFullTextSearch(srb.getUserInput1());
		
		return idList != null ? idList : new ArrayList<Integer>();
	}

	public Map<String, List<Pair<String,String>>> getCategoryAttributesMap() {
		return createCategoryAttributesMap();
	}
	
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
	
	public Map<String, List<Pair<String,String>>> getCategoryAttributeMap(List<Integer> idList){
		
		Map<String, List<Pair<String,String>>> map = new HashMap<>();
		if(idList == null || idList.isEmpty()) return map;
		
		// need to get the first spectrum so that we can display non-conflicting values
		Spectrum s = specchio_client.getSpectrum(idList.get(0), false);
		
		// One Spectrum
		if(idList.size() == 1){
			
			for(MetaParameter mp : s.getMetadata().getEntries()){
				
				if(map.get(mp.getCategoryName()) == null) {
					map.put(mp.getCategoryName(), new LinkedList<Pair<String,String>>());
				}
				map.get(mp.getCategoryName()).add(new Pair<>(mp.getAttributeName(), mp.getValue().toString()));
				
			}
			
		}
		// Multiple Spectra
		else {
			
			// add EAV parameters including their conflict status
			ConflictTable eav_conflict_stati = specchio_client.getEavMetadataConflicts((ArrayList<Integer>) idList);

			for(MetaParameter mp : s.getMetadata().getEntries()){
				
				if(map.get(mp.getCategoryName()) == null) {
					map.put(mp.getCategoryName(), new LinkedList<Pair<String,String>>());
				}
				
				ConflictInfo  conflict = eav_conflict_stati.get(mp.getAttributeId());
				int status = conflict.getConflictData(mp.getEavId()).getStatus();
				
				// no conflict
				if(status == 3) {
					map.get(mp.getCategoryName()).add(new Pair<>(mp.getAttributeName(), mp.getValue().toString()));
				}
				else {
					map.get(mp.getCategoryName()).add(new Pair<>(mp.getAttributeName(), "Multiple Values"));
				}
				
			}
			
		}
		
		return map;
	}
	
	public List<SpaceDetailBean> createSpaceDetailBeanList(List<SearchResultBean> srbList) {
		return createSpaceDetailBeanList(extractSpectrumIds(srbList));
	}
	
	public List<SpaceDetailBean> createSpaceDetailBeanList(int spectrumId){
		ArrayList<Integer> idList = new ArrayList<>();
		idList.add(spectrumId);
		return createSpaceDetailBeanList(idList);
	}

	public List<SpaceDetailBean> createSpaceDetailBeanList(ArrayList<Integer> spectrumIdList){
		
		List<SpaceDetailBean> sdbList = new LinkedList<>();
		Space[] spaces = getSpaces(spectrumIdList, "Acquisition Time");
		
		for(int i = 0; i < spaces.length; i++){
			
			Space space = spaces[i]; 
			
			ChartDataBean wavelength = new ChartDataBean("wavelength", getWavelength((SpectralSpace) space));
			
			List<ChartDataBean> vectorList = new LinkedList<>();
			MatlabAdaptedArrayList<Object> resultList = specchio_client.getMetaparameterValues(space.getSpectrumIds(), "File Name");
			
			double[][] vectors = getVectors((SpectralSpace) space);
			for(int ii = 0; ii < vectors.length; ii++){
				vectorList.add(new ChartDataBean(resultList.get(ii).toString(),vectors[ii]));
			}
			
			
			String measurementUnit = ((SpectralSpace) space).getMeasurementUnit().getUnitName();
			double maxY = 0;
			if("Reflectance".equals(measurementUnit)){
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
//						DecimalFormat df = new DecimalFormat("#,###################");
//						df.setRoundingMode(RoundingMode.CEILING);
//						maxY = Double.valueOf(df.format(stats.mean()+1*stats.standardDeviation()));
						maxY = stats.mean()+1*stats.standardDeviation();
					}
				}
			}
			if(maxY == 0) maxY = getHighest(vectors);
			
			sdbList.add(new SpaceDetailBean(space.getSpaceTypeName() + " " + (i+1), measurementUnit, wavelength, vectorList, getCategoryAttributeMap(space.getSpectrumIds()), space.getSpectrumIds(), maxY));
			
			sdbList.add(new SpaceDetailBean(space.getSpaceTypeName() + " " + (i+1), "gugus", wavelength, vectorList, getCategoryAttributeMap(space.getSpectrumIds()), space.getSpectrumIds(), maxY));
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
