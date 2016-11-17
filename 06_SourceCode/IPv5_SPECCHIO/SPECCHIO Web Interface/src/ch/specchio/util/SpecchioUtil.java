package ch.specchio.util;

import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

import ch.specchio.client.SPECCHIOClient;
import ch.specchio.client.SPECCHIOClientFactory;
import ch.specchio.client.SPECCHIOServerDescriptor;
import ch.specchio.model.Attribute;
import ch.specchio.model.Category;
import ch.specchio.model.Pair;
import ch.specchio.types.CategoryTable;
import ch.specchio.types.InstrumentDescriptor;
import ch.specchio.types.Sensor;
import ch.specchio.types.attribute;

public class SpecchioUtil {

	private final String FIRST_CATEGORY 	= "Full Text Search";
	private final String SECOND_CATEGORY 	= "Most Wanted";
	
	private final String MOST_WANTED_1 		= "Measurement Unit ID";
	private final String MOST_WANTED_2 		= "Sensor ID";
	private final String MOST_WANTED_3 		= "Instrument ID";
	
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
	
	public List<Category> getCategoryList(){
		List<Category> categoryList = new LinkedList<>();
		categoryList.add(new Category(FIRST_CATEGORY));
		categoryList.add(new Category(SECOND_CATEGORY));
		
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
	
	public List<Attribute> getAttributeList(String category){
		attribute[] attributes = {};
		
		if(FIRST_CATEGORY.equals(category)){	// Full Text Search
			List<Attribute> list = new LinkedList<>();
			list.add(new Attribute("","string_val"));
			return list;
		}
		else if(SECOND_CATEGORY.equals(category)){	// Most Wanted
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
		attributeList.add(new Attribute(MOST_WANTED_1, "drop_down"));
		attributeList.add(new Attribute(MOST_WANTED_2, "drop_down"));
		attributeList.add(new Attribute(MOST_WANTED_3, "drop_down"));
		
		return attributeList;
	}

	public List<Pair<String,String>> getInputDropdownValues(Attribute attr) {
		
		switch(attr.getName()){
			case MOST_WANTED_1:
				return getMeasurementUnitList();
			case MOST_WANTED_2:
				return getSensorList();			
			case MOST_WANTED_3:
				return getInstrumentList();
		}
		
		return null;
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

	
}
