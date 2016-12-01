package ch.specchio.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import ch.specchio.client.SPECCHIOClient;
import ch.specchio.client.SPECCHIOClientException;
import ch.specchio.client.SPECCHIOClientFactory;
import ch.specchio.client.SPECCHIOServerDescriptor;
import ch.specchio.queries.Query;
import ch.specchio.queries.SpectrumQueryCondition;

public class Test {

	public static void main(String[] args) throws SPECCHIOClientException, FileNotFoundException, IOException {
		SPECCHIOClientFactory cf = SPECCHIOClientFactory.getInstance();
		List<SPECCHIOServerDescriptor> db_descriptor_list = cf.getAllServerDescriptors();
		SPECCHIOClient specchio_client = cf.createClient(db_descriptor_list.get(0));
		
		List<Integer> ids = specchio_client.getSpectrumIdsMatchingFullTextSearch("bfern.%");
		
		
		Query query = new Query("spectrum"); 
		query.addColumn("spectrum_id");
		query.setQueryType(Query.SELECT_QUERY); 
		SpectrumQueryCondition cond = new SpectrumQueryCondition("spectrum", "spectrum_id"); 
		cond.setValue(ids); 
		cond.setOperator("in");
		query.add_condition(cond);
		query.add_join("spectrum", cond);
		ids = specchio_client.getSpectrumIdsMatchingQuery(query);
		
		System.out.println(ids.size());
	}

}
