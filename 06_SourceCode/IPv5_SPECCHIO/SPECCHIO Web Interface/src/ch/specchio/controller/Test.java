package ch.specchio.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Hashtable;
import java.util.List;

import ch.specchio.client.SPECCHIOClient;
import ch.specchio.client.SPECCHIOClientException;
import ch.specchio.client.SPECCHIOClientFactory;
import ch.specchio.client.SPECCHIOServerDescriptor;
import ch.specchio.model.Attribute;
import ch.specchio.util.SpecchioUtil;

public class Test {

	public static void main(String[] args) throws SPECCHIOClientException, FileNotFoundException, IOException {
		SPECCHIOClientFactory cf = SPECCHIOClientFactory.getInstance();
		List<SPECCHIOServerDescriptor> db_descriptor_list = cf.getAllServerDescriptors();
		SPECCHIOClient specchio_client = cf.createClient(db_descriptor_list.get(0));
		
		SpecchioUtil util = new SpecchioUtil();
		
		
		Attribute a = util.getAttribute("Sampling Environment", "Environmental Conditions");
		
		Hashtable<String, Integer> taxonomyHash = specchio_client.getTaxonomyHash(a.getId());
		for(String key : taxonomyHash.keySet()){
			System.out.println(key + " - " + taxonomyHash.get(key));
		}
		
	}

}
