package ch.specchio.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ch.specchio.client.SPECCHIOClient;
import ch.specchio.client.SPECCHIOClientFactory;
import ch.specchio.client.SPECCHIOServerDescriptor;
import ch.specchio.model.MetaDataObject;
import ch.specchio.queries.EAVQueryConditionObject;
import ch.specchio.queries.Query;
import ch.specchio.spaces.SpectralSpace;
import ch.specchio.types.Campaign;
import ch.specchio.types.Category;
import ch.specchio.types.CategoryTable;
import ch.specchio.types.Institute;
import ch.specchio.types.InstrumentDescriptor;
import ch.specchio.types.Sensor;
import ch.specchio.types.Spectrum;
import ch.specchio.types.User;
import ch.specchio.types.attribute;
import ch.specchio.util.SpecchioUtil;


@SuppressWarnings("serial")
public class SearchResultServlet extends HttpServlet {

	
	private void handleRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		SpecchioUtil specchioUtil = new SpecchioUtil();
		String searchInput = req.getParameter("text");
		
		
		// Connect to DB and get client
		SPECCHIOClientFactory cf = SPECCHIOClientFactory.getInstance();
		List<SPECCHIOServerDescriptor> db_descriptor_list = cf.getAllServerDescriptors();
		SPECCHIOClient specchio_client = cf.createClient(db_descriptor_list.get(0));
		
		
		// FOR SEARCH DROPDOWNS
		
		// categories and their attributes
		for(Category c : specchio_client.getCategoriesInfo()){
			attribute[] attributes = specchio_client.getAttributesForCategory(c.name);
			attribute a = attributes[0];
			a.getName();
		}
		
		// measurement unit
		CategoryTable categoryValues = specchio_client.getMetadataCategoriesForIdAccess("measurement_unit");
		categoryValues.get(0);
		
		// Special case Instrument
		InstrumentDescriptor[] descriptors = specchio_client.getInstrumentDescriptors();
		InstrumentDescriptor desc = descriptors[0];
		desc.getInstrumentName(); // anzeigen im dropdown
		desc.getInstrumentId(); // zum suchen wie auf folie letzte seite
		
		// Special cases sensor
		Sensor[] sensors = specchio_client.getSensors();
		Sensor s = sensors[0];
		s.getName(); // anzeigen im dropdown
		s.getSensorId(); // zum suchen wie auf folie letzte seite
		
		
		// für taxonomy_id
		Hashtable<String, Integer> taxonomyHash = specchio_client.getTaxonomyHash(0);
		
		// ---
		
		// Build Search Query
		Query query = new Query();

		String[] tokens = searchInput.split(" ");
		ArrayList<Integer> ids = null;
		
		for(String token : tokens){
			String[] temp = token.split("=");
			
			System.out.println(temp[0] + " " + temp[1]);
			
			attribute attr = specchio_client.getAttributesNameHash().get(specchioUtil.getAttributeForKeyword(temp[0]));
			attr.getDefaultStorageField(); // gibt string_val, taxonomy_id etc zurück
			attr.getId();
			
			EAVQueryConditionObject cond = new EAVQueryConditionObject(attr);
			cond.setValue(temp[1]);
			cond.setOperator("like");
			query.add_condition(cond);
			ids = specchio_client.getSpectrumIdsMatchingQuery(query);
		}
		
		
		// CAMPAIGN NAME 
		Spectrum spectrum = specchio_client.getSpectrum(ids.get(0), false);
		System.out.println("----------------------------");
		System.out.println(spectrum.getCampaignId());
		Campaign campaign = specchio_client.getCampaign(spectrum.getCampaignId());
		System.out.println(campaign.getName());
		System.out.println(campaign.getInvestigator()); // use this investigator if other is null
		System.out.println("----------------------------");
		
		// INSTITUTE
		User user = new User();
		Institute in = user.getInstitute();
		in.getInstituteName();
		
		
		
//		attribute attr = specchio_client.getAttributesNameHash().get("File Name");
//		
//		EAVQueryConditionObject cond = new EAVQueryConditionObject(attr);
//		cond.setValue("bfern.%");
//		cond.setOperator("like");
//		query.add_condition(cond);
//		ArrayList<Integer> ids = specchio_client.getSpectrumIdsMatchingQuery(query);
//		System.out.println("hello : " + ids.size());
		
//		cond = new EAVQueryConditionObject(attr);
//		cond.setValue("55.0");
//		cond.setOperator("<");
//		query.add_condition(cond);
//		ids = specchio_client.getSpectrumIdsMatchingQuery(query);
//		System.out.println("hello : " + ids.size());
		
		SpectralSpace[] spaces = (SpectralSpace[]) specchio_client.getSpaces(ids, "Acquisition Time");
		System.out.println(spaces.length);
		SpectralSpace space = spaces[0];
		ids = space.getSpectrumIds(); // get them sorted by 'Acquisition Time'
		space = (SpectralSpace) specchio_client.loadSpace(space); 
		
		
		double[][] vectors = space.getVectorsAsArray(); 
		double[] wvl= space.getAverageWavelengths(); // TODO: Gibt es nicht?
		
		
		List<MetaDataObject> metaDOs = specchioUtil.fillMetaparameterValues(specchio_client, ids);
		req.setAttribute("metaDOs", metaDOs);
		
		
		RequestDispatcher rd = req.getRequestDispatcher("/searchResult.jsp");
		rd.forward(req, resp);
		
//		String nextJSP = req.getContextPath() + "/test.jsp";
//		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
//		dispatcher.forward(req,resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		handleRequest(req, resp);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		handleRequest(req, resp);
	}
	
}
