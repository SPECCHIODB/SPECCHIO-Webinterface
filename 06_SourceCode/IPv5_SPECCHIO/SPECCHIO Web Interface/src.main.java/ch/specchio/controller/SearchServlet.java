package ch.specchio.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ch.specchio.model.Attribute;
import ch.specchio.model.SearchRowBean;
import ch.specchio.util.SpecchioUtil;

import com.google.gson.Gson;


@SuppressWarnings("serial")
public class SearchServlet extends HttpServlet {

	private SpecchioUtil util = new SpecchioUtil();
	
	private void handleRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		int numberOfRows = req.getParameter("numberOfRows") != null ? Integer.valueOf(req.getParameter("numberOfRows")) : 0;
		String reqType = req.getParameter("reqType") != null ? req.getParameter("reqType") : "init";
		
		boolean valid = true;
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
				
				if("search".equals(reqType)) valid = validate(srb) && valid;
				
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
		
		// SHOW = get SearchResultBeanList and forward to searchResult.jsp
		if("show".equals(reqType)) {
			req.setAttribute("srbList", gson.toJson(util.getAllSearchResults(searchRowBeanList)));
			rd = req.getRequestDispatcher("/searchResult.jsp"); // show searchResult.jsp
			rd.forward(req, resp);
		}
		
		if("init".equals(reqType))
			req.setAttribute("searchResultCount", util.getSpectrumIdCount());
		else if("reload".equals(reqType)) 
			req.setAttribute("searchResultCount", req.getParameter("searchResultCount"));
		else {
			if(valid) req.setAttribute("searchResultCount", util.getSpectrumIdList(searchRowBeanList).size());
			else req.setAttribute("searchResultCount", 0);
		}

		req.setAttribute("valid", valid);
		req.setAttribute("categoryList", gson.toJson(util.getCategoryList()));
		req.setAttribute("searchRowBeanList", gson.toJson(searchRowBeanList));
		
		rd = req.getRequestDispatcher("/search.jsp"); // show search.jsp
		rd.forward(req, resp);
	}
	
	/**
	 * Checks whether the user inputs in the given SearchRowBean are valid.
	 * @return true if no validation errors were found, false otherwise.
	 */
	private boolean validate(SearchRowBean srb) {
		// Full Text Search may contain anything
		if ("Full Text Search".equals(srb.getSelectedCategory().getName())) return true;
		
		switch(srb.getSelectedAttribute().getDefaultStorageField()){
			case "string_val" :
				srb.setValidInput1(!srb.getUserInput1().isEmpty());
				srb.setErrorMessage(srb.getSelectedAttribute().getName() + " must not be empty.");
				break;
			case "int_val" :
				srb.setValidInput1(srb.getUserInput1().matches("^[0-9]+$"));
				srb.setValidInput2(srb.getUserInput2().matches("^[0-9]+$"));
				srb.setErrorMessage(srb.getSelectedAttribute().getName() + " must contain numeric values only and not be empty.");
				break;
			case "double_val" :
				srb.setValidInput1(srb.getUserInput1().matches("^[0-9]+.[0-9]+$") 
						|| srb.getUserInput1().matches("^[0-9]+$"));
				srb.setValidInput2(srb.getUserInput2().matches("^[0-9]+.[0-9]+$")
						|| srb.getUserInput2().matches("^[0-9]+$"));
				srb.setErrorMessage(srb.getSelectedAttribute().getName() + " must contain numeric or floating values only and not be empty.");
				break;
			case "datetime_val" :
				
				SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
				sdf.setLenient(false);

				try {
					//if not valid, it will throw ParseException
					sdf.parse(srb.getUserInput1());
					srb.setValidInput1(true);
				} catch (Exception e) {
					srb.setValidInput1(false);
					srb.setErrorMessage(srb.getSelectedAttribute().getName() + " must match Dateformat dd.mm.yyyy.");
				}
				
				break;
		}

		return srb.isValidInput1() && srb.isValidInput2();
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
