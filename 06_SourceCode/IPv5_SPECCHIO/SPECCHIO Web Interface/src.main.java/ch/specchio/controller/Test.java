package ch.specchio.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ch.specchio.client.SPECCHIOClient;
import ch.specchio.client.SPECCHIOClientException;
import ch.specchio.client.SPECCHIOClientFactory;
import ch.specchio.client.SPECCHIOServerDescriptor;
import ch.specchio.queries.EAVQueryConditionObject;
import ch.specchio.queries.Query;
import ch.specchio.spaces.Space;
import ch.specchio.spaces.SpectralSpace;
import ch.specchio.types.attribute;

public class Test {

	public static void main(String[] args) throws SPECCHIOClientException,
			FileNotFoundException, IOException {
		SPECCHIOClientFactory cf = SPECCHIOClientFactory.getInstance();
		List<SPECCHIOServerDescriptor> db_descriptor_list = cf
				.getAllServerDescriptors();
		SPECCHIOClient specchio_client = cf.createClient(db_descriptor_list
				.get(0));

		Query query = new Query();
		attribute attr = specchio_client.getAttributesNameHash().get(
				"File Name");
		EAVQueryConditionObject cond = new EAVQueryConditionObject(attr);
		cond.setValue("bfern.%");
		cond.setOperator("like");
		query.add_condition(cond);

		ArrayList<Integer> ids = specchio_client.getSpectrumIdsMatchingQuery(query);

		Space[] spaces = specchio_client.getSpaces(ids, "Acquisition Time");
		SpectralSpace space = (SpectralSpace) spaces[0];
		
		ids = space.getSpectrumIds();

		space = (SpectralSpace) specchio_client.loadSpace(space);

		double[][] vectors = space.getVectorsAsArray();
		double[] wvl = space.getAverageWavelengths();
		
	}
}
