package ch.specchio.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import ch.specchio.model.Attribute;
import ch.specchio.model.SearchRowBean;
import ch.specchio.util.SpecchioUtil;


@SuppressWarnings("serial")
public class SearchServlet extends HttpServlet {

	private SpecchioUtil util = new SpecchioUtil();
	
	private void handleRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		System.out.println("asdf");
		
		int numberOfRows = req.getParameter("numberOfRows") != null ? Integer.valueOf(req.getParameter("numberOfRows")) : 0;
		boolean doSearch = "true".equals(req.getParameter("doSearch"));
		
		List<SearchRowBean> searchRowBeanList = new LinkedList<>();
		
		for(int i = 0; i < numberOfRows; i++){
			
			String category = req.getParameter("category_"+i);
			if(category == null) continue; // skip categories that have been removed on the gui
			
			String attribute = req.getParameter("attribute_"+i);
			
			SearchRowBean srb = new SearchRowBean();
			srb.setAttributeList(util.getAttributeList(category));
			srb.setSelectedCategory(util.getCategory(category));
			
			Attribute attr = util.getAttribute(attribute, category);
			srb.setSelectedAttribute(attr);
			
			if(attr != null){
				srb.setUserInput1(req.getParameter("userInput1_"+i));
				srb.setUserInput2(req.getParameter("userInput2_"+i));
				
				if("drop_down".equals(attr.getDefaultStorageField())){
					srb.setDropdownPairList(util.getInputDropdownValues(attr));
				}
				else if("taxonomy_id".equals(attr.getDefaultStorageField())){
					srb.setDropdownPairList(util.getTaxonomyList(attr));
				}
			}
			
			searchRowBeanList.add(srb);
		}
		
		RequestDispatcher rd = null;
		Gson gson = new Gson();
		
		if(doSearch) {
			req.setAttribute("mdbList", util.getSearchResult(searchRowBeanList));
			//req.setAttribute("mdbList", gson.toJson(util.getSearchResult(searchRowBeanList)));
			rd = req.getRequestDispatcher("/searchResult.jsp"); // show searchResult.jsp
		}
		else {
			req.setAttribute("categoryList", gson.toJson(util.getCategoryList()));
			req.setAttribute("searchRowBeanList", gson.toJson(searchRowBeanList));
			rd = req.getRequestDispatcher("/search.jsp"); // show search.jsp
		}
		
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
