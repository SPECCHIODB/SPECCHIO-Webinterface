package ch.specchio.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ch.specchio.client.SPECCHIOClient;
import ch.specchio.client.SPECCHIOClientFactory;
import ch.specchio.client.SPECCHIOServerDescriptor;
import ch.specchio.model.MetaDataBean;
import ch.specchio.queries.EAVQueryConditionObject;
import ch.specchio.queries.Query;
import ch.specchio.spaces.Space;
import ch.specchio.types.attribute;
import ch.specchio.util.SpecchioUtilOld;


@SuppressWarnings("serial")
public class SearchResultServlet extends HttpServlet {

	
	private void handleRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
//		SpecchioUtil specchioUtil = new SpecchioUtil();
//		String searchInput = req.getParameter("text");
//		
//		
//		// Connect to DB and get client
//		SPECCHIOClientFactory cf = SPECCHIOClientFactory.getInstance();
//		List<SPECCHIOServerDescriptor> db_descriptor_list = cf.getAllServerDescriptors();
//		SPECCHIOClient specchio_client = cf.createClient(db_descriptor_list.get(0));
//		
//		// Build Search Query
//		Query query = new Query();
//
//		String[] tokens = searchInput.split(" ");
//		ArrayList<Integer> ids = null;
//		
//		for(String token : tokens){
//			String[] temp = token.split("=");
//			
//			System.out.println(temp[0] + " " + temp[1]);
//			
//			attribute attr = specchio_client.getAttributesNameHash().get(specchioUtil.getAttributeForKeyword(temp[0]));
//			
//			EAVQueryConditionObject cond = new EAVQueryConditionObject(attr);
//			cond.setValue(temp[1]);
//			cond.setOperator("like");
//			query.add_condition(cond);
//			ids = specchio_client.getSpectrumIdsMatchingQuery(query);
//		}
//		
//		
////		attribute attr = specchio_client.getAttributesNameHash().get("File Name");
////		
////		EAVQueryConditionObject cond = new EAVQueryConditionObject(attr);
////		cond.setValue("bfern.%");
////		cond.setOperator("like");
////		query.add_condition(cond);
////		ArrayList<Integer> ids = specchio_client.getSpectrumIdsMatchingQuery(query);
////		System.out.println("hello : " + ids.size());
//		
////		cond = new EAVQueryConditionObject(attr);
////		cond.setValue("55.0");
////		cond.setOperator("<");
////		query.add_condition(cond);
////		ids = specchio_client.getSpectrumIdsMatchingQuery(query);
////		System.out.println("hello : " + ids.size());
//		
//		Space[] spaces = specchio_client.getSpaces(ids, "Acquisition Time");
//		System.out.println(spaces.length);
//		Space space = spaces[0];
//		ids = space.getSpectrumIds(); // get them sorted by 'Acquisition Time'
//		space = specchio_client.loadSpace(space); 
//		
//		
		MetaDataBean mdo = new MetaDataBean();
		mdo.setAcquisitionTime("time");
		mdo.setCampaignName("asdfas");
		mdo.setFileName("fillleee");
		//mdo.setInvestigator("norbert");
		List<MetaDataBean> metaDOs = new LinkedList<>();//specchioUtil.fillMetaparameterValues(specchio_client, ids);
		metaDOs.add(mdo);
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