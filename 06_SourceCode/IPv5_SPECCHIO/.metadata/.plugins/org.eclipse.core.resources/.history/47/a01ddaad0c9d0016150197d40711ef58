package ch.specchio.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Hashtable;
import java.util.List;

import ch.specchio.client.SPECCHIOClient;
import ch.specchio.client.SPECCHIOClientException;
import ch.specchio.client.SPECCHIOClientFactory;
import ch.specchio.client.SPECCHIOServerDescriptor;
import ch.specchio.queries.EAVQueryConditionObject;
import ch.specchio.queries.Query;
import ch.specchio.types.attribute;

public class Test {

	public static void main(String[] args) throws SPECCHIOClientException, FileNotFoundException, IOException {
		SPECCHIOClientFactory cf = SPECCHIOClientFactory.getInstance();
		List<SPECCHIOServerDescriptor> db_descriptor_list = cf.getAllServerDescriptors();
//		System.out.println(db_descriptor_list.get(0));
		SPECCHIOClient specchio_client = cf.createClient(db_descriptor_list.get(0));
		System.out.println(specchio_client);
		
		Query query = new Query();
		
		Hashtable<String, attribute> asdf = specchio_client.getAttributesNameHash();
		attribute attr = specchio_client.getAttributesNameHash().get("Altitude");

		EAVQueryConditionObject cond = new EAVQueryConditionObject(attr);
		cond.setValue("50.0");
		cond.setOperator(">=");
		query.add_condition(cond);
		List<Integer> ids = specchio_client.getSpectrumIdsMatchingQuery(query);
		System.out.println("hello : " + ids.size());
	}

}
