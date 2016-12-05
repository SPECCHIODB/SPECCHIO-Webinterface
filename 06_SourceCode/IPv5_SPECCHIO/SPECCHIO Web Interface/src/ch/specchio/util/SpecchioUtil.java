package ch.specchio.util;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import ch.specchio.client.SPECCHIOClient;
import ch.specchio.client.SPECCHIOClientFactory;
import ch.specchio.client.SPECCHIOServerDescriptor;
import ch.specchio.model.Attribute;
import ch.specchio.model.Category;
import ch.specchio.model.MetaDataBean;
import ch.specchio.model.Pair;
import ch.specchio.model.SearchResultBean;
import ch.specchio.model.SearchRowBean;
import ch.specchio.queries.EAVQueryConditionObject;
import ch.specchio.queries.Query;
import ch.specchio.queries.QueryCondition;
import ch.specchio.queries.SpectrumQueryCondition;
import ch.specchio.spaces.Space;
import ch.specchio.spaces.SpectralSpace;
import ch.specchio.types.Campaign;
import ch.specchio.types.CategoryTable;
import ch.specchio.types.ConflictTable;
import ch.specchio.types.InstrumentDescriptor;
import ch.specchio.types.MatlabAdaptedArrayList;
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
	
	public SpecchioUtil() {
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
		
		for(ch.specchio.types.Category c : specchio_client.getCategoriesInfo())
			categoryList.add(new Category(c));
		
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

	public List<SearchResultBean> getSearchResultList(List<SearchRowBean> searchRowBeanList){
		List<SearchResultBean> srbList = new LinkedList<>();
		
		if(searchRowBeanList == null || searchRowBeanList.isEmpty()) return srbList;
		
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
		
		List<Integer> ids = null;
		if(fullTextSearchResultList.size() == 1 && queryConditionList.isEmpty()){
			ids = fullTextSearchResultList.get(0);
		}
		else {
			Query query = new Query("spectrum"); 
			query.addColumn("spectrum_id");
			query.setQueryType(Query.SELECT_QUERY); 
			
			// Always do FullTextSearch first
			for(List<Integer> idList : fullTextSearchResultList){
				QueryCondition cond = createFullTextSearchCondition(query, idList);
				query.add_join("spectrum", cond);
				query.add_condition(cond);
			}
			
			for(QueryCondition cond : queryConditionList){
				query.add_condition(cond);
			}
			ids = specchio_client.getSpectrumIdsMatchingQuery(query);
		}
		
		Space[] spaces = specchio_client.getSpaces((ArrayList<Integer>) ids, "Acquisition Time");
		for(Space s : spaces){
			SpectralSpace space = (SpectralSpace) s;
			ids = space.getSpectrumIds(); // get them sorted by 'Acquisition Time'
			space = (SpectralSpace) specchio_client.loadSpace(space);
			
			
			srbList.addAll(createSearchResultBeanList(ids, space));
		}
		
		return srbList;
	}
	
//	public List<SearchResultBean> getSearchResult(List<SearchRowBean> searchRowBeanList) {
//		List<SearchResultBean> srbList = new LinkedList<>();
//		List<Integer> ids = new LinkedList<>();
//		Query query = new Query("spectrum"); 
//		query.addColumn("spectrum_id");
//		query.setQueryType(Query.SELECT_QUERY); 
//		boolean fulltextsearch = false;
//		
//		for(SearchRowBean srb : searchRowBeanList){
//			
//			if(FIRST_CATEGORY.equals(srb.getSelectedCategory().getName())){ // Full Text Search
//				ids = doFullTextSearch(srb); // TODO: kann fts in kombination mit anderen auftreten?
//				fulltextsearch = true;
//				break;
//			}
//			else if(SECOND_CATEGORY.equals(srb.getSelectedCategory().getName())){ // Most Wanted
//				addSpectrumQueryCondition(query, srb);
//			}
//			else { // Other Categories
//				addEAVQueryCondition(query, srb);
//			}
//			Spectrum s = new Spectrum();
//		}
//		if (!fulltextsearch) ids = specchio_client.getSpectrumIdsMatchingQuery(query);
//		
//		System.out.println(ids.size());
//		
//		Space[] spaces = specchio_client.getSpaces((ArrayList<Integer>) ids, "Acquisition Time");
//		for(Space s : spaces){
//			SpectralSpace space = (SpectralSpace) s;
//			ids = space.getSpectrumIds(); // get them sorted by 'Acquisition Time'
//			space = (SpectralSpace) specchio_client.loadSpace(space);
//			srbList.addAll(createSearchResultBeanList(ids, space));
//		}
//		
//		return srbList;
//	}
	
//	private List<MetaDataBean> createMetaDataBeanList(List<Integer> ids, SpectralSpace space){
//		List<MetaDataBean> mdbList = new LinkedList<>();
//		
//		for (Integer id : ids){
////			srbList.add(new SearchResultBean(space));
//			srbList.add(new SearchResultBean(id));
//		}
//		
//		for(Category c : getCategoryList()){
//			for(Attribute a : getAttributeList(c)){
//				
//			}
//		}
//		
//		 
//		fillMetaParameter("Acquisition Time", getSetterName("Acquisition Time"), ids, srbList);
//		fillMetaParameter("Investigator", getSetterName("Investigator"), ids, srbList);
//		fillMetaParameter("File Name", getSetterName("File Name"), ids, srbList);
//		
//		// Campaign Name, User, Name & Institute
//		fillMetaParameterSpecialCases(ids, srbList);
//		
//		return srbList;
//	}

	private List<SearchResultBean> createSearchResultBeanList(List<Integer> ids, SpectralSpace space){
		List<SearchResultBean> srbList = new LinkedList<>();
		
		for (Integer id : ids){
//			srbList.add(new SearchResultBean(space));
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
		else{
			System.err.println(dsf);
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

	private List<Integer> doFullTextSearch(SearchRowBean srb) {
		return specchio_client.getSpectrumIdsMatchingFullTextSearch(srb.getUserInput1());
	}

	public Map<String, List<Pair<String,String>>> getCategoryAttributesMap() {
		return createCategoryAttributesMap();
	}
	
//	private void asdf(){
//		
//		// need to get the first spectrum so that we can display non-conflicting values
//		Spectrum s = specchio_client.getSpectrum(ids.get(0), false);
//
//		// add EAV parameters including their conflict status
//		ConflictTable eav_conflict_stati = specchio_client.getEavMetadataConflicts(ids);
//		Enumeration<String> conflicts = eav_conflict_stati.conflicts();
//		
//	}
	
}
