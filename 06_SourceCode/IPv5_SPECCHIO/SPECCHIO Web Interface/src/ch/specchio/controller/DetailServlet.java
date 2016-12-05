package ch.specchio.controller;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Enumeration;
import java.util.LinkedList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ch.specchio.model.SearchResultBean;
import ch.specchio.types.ConflictTable;
import ch.specchio.types.Spectrum;
import ch.specchio.util.SpecchioUtil;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@SuppressWarnings("serial")
public class DetailServlet extends HttpServlet {

	
	private void handleRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// Converting json-String back to a List of SearchResultBean
		Type listType = new TypeToken<LinkedList<SearchResultBean>>(){}.getType();
		System.out.println(req.getParameter("selectedSearchResultBeanList"));
		LinkedList<SearchResultBean> srbList = new Gson().fromJson(req.getParameter("selectedSearchResultBeanList"), listType);
		
		SpecchioUtil util = new SpecchioUtil();
		
		if(srbList.size() == 1){
			
			
			
		}
		
		
		
		
		
//		// need to get the first spectrum so that we can display non-conflicting values
//		Spectrum s = specchio_client.getSpectrum(ids.get(0), false);
//
//		// add EAV parameters including their conflict status
//		ConflictTable eav_conflict_stati = specchio_client.getEavMetadataConflicts(ids);
//		Enumeration<String> conflicts = eav_conflict_stati.conflicts();
		
		
		req.setAttribute("metaDataBeanList", new Gson().toJson(srbList));
		req.setAttribute("categoryAttributesMap", new Gson().toJson(util.getCategoryAttributesMap()));
		
		RequestDispatcher rd = req.getRequestDispatcher("/detail.jsp");
		rd.forward(req, resp);
		
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
