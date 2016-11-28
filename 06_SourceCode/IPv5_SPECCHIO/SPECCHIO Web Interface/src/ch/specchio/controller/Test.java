package ch.specchio.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import ch.specchio.client.SPECCHIOClient;
import ch.specchio.client.SPECCHIOClientException;
import ch.specchio.client.SPECCHIOClientFactory;
import ch.specchio.client.SPECCHIOServerDescriptor;
import ch.specchio.model.MetaDataBean;
import ch.specchio.queries.EAVQueryConditionObject;
import ch.specchio.queries.Query;
import ch.specchio.spaces.Space;
import ch.specchio.spaces.SpectralSpace;
import ch.specchio.types.attribute;
import ch.specchio.util.SpecchioUtil;

public class Test {

	public static void main(String[] args) throws SPECCHIOClientException, FileNotFoundException, IOException {
		SPECCHIOClientFactory cf = SPECCHIOClientFactory.getInstance();
		List<SPECCHIOServerDescriptor> db_descriptor_list = cf.getAllServerDescriptors();
		SPECCHIOClient specchio_client = cf.createClient(db_descriptor_list.get(0));
		
		SpecchioUtil util = new SpecchioUtil();
		
		
		Hashtable<String, attribute> asdf = specchio_client.getAttributesNameHash();
		
		for(String key : asdf.keySet()){
			System.out.println(key);
		}
		System.out.println(asdf.size());
		
//		Attribute a = util.getAttribute("Sampling Environment", "Environmental Conditions");
//		
//		Hashtable<String, Integer> taxonomyHash = specchio_client.getTaxonomyHash(a.getId());
//		for(String key : taxonomyHash.keySet()){
//			System.out.println(key + " - " + taxonomyHash.get(key));
//		}
		
		
		
		Query query = new Query("spectrum"); 
		query.setQueryType(Query.SELECT_QUERY); 
		
		EAVQueryConditionObject cond = new EAVQueryConditionObject(util.getAttribute("Altitude", "Location").getAttribute());
		cond.setValue("50.0");
		cond.setOperator(">=");
		query.add_condition(cond);
		
		cond = new EAVQueryConditionObject(util.getAttribute("Altitude", "Location").getAttribute());
		cond.setValue("55.0");
		cond.setOperator("<");
		query.add_condition(cond);
		
//		SpectrumQueryCondition cond2 = new SpectrumQueryCondition("spectrum","measurement_unit_id"); 
//		cond2.setValue("8"); 
//		cond2.setOperator("="); 
//		query.add_condition(cond2); 
		
//		ArrayList<Integer> ids = specchio_client.getSpectrumIdsMatchingQuery(query);
//		System.out.println(ids.size());
//		
//		
//		
//		Space[] spaces = specchio_client.getSpaces(ids, "Acquisition Time");
//		System.out.println(spaces.length);
//		SpectralSpace space = (SpectralSpace) spaces[0];
//		ids = space.getSpectrumIds(); // get them sorted by 'Acquisition Time'
//		space = (SpectralSpace) specchio_client.loadSpace(space); 
//		System.out.println(ids.size());
		
		Class[] paramString = new Class[1];
		paramString[0] = String.class;
		
		MetaDataBean mdb = new MetaDataBean();
		
		try {
			Class c = Class.forName("ch.specchio.model.MetaDataBean");
			//Object obj = c.newInstance();
			
			
			Method method = c.getDeclaredMethod("setAcquisitionTime", paramString);
			method.invoke(mdb, "test");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(mdb.getAcquisitionTime());
		
	}

}
