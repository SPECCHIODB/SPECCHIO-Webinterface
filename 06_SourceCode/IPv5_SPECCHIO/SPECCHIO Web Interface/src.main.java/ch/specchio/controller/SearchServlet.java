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

import ch.specchio.client.SPECCHIOClientException;
import ch.specchio.model.Attribute;
import ch.specchio.model.SearchRowBean;
import ch.specchio.util.SpecchioUtil;

import com.google.gson.Gson;

@SuppressWarnings("serial")
public class SearchServlet extends HttpServlet {

	private void handleRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// create a new SpecchioUtil object. it allows all interactions with the db.
		SpecchioUtil util = null;
		try {
			util = new SpecchioUtil();
		} catch (Exception e) { // if the application is not able to connect to the DB show error page
			showErrorPage("Could not connect to database", "Please try again later or contact the system administrator.", req, resp);
		}
		
		// the number of rows that have been created on the search view. initially it will be 0.
		int numberOfRows = req.getParameter("numberOfRows") != null ? Integer.valueOf(req.getParameter("numberOfRows")) : 0;
		// the request type used to differentiate between requests.
		String reqType = req.getParameter("reqType") != null ? req.getParameter("reqType") : "init";
		
		// validity of the user input
		boolean valid = true;
		// create a new list of SearchRowBean's
		List<SearchRowBean> searchRowBeanList = new LinkedList<>();
		
		try { 
		
			for(int i = 0; i < numberOfRows; i++){ 						// iterate over all rows
				
				String category = req.getParameter("category_"+i); 		// get selected category name of current row
				if(category == null) continue; 							// skip categories that have been removed on the GUI
				
				String attribute = req.getParameter("attribute_"+i); 	// get selected attribute name of current row
				
				SearchRowBean srb = new SearchRowBean(); 				// create new SearchRowBean
				srb.setAttributeList(util.getAttributeList(category)); 	// set AttributeList for selected category name
				srb.setSelectedCategory(util.getCategory(category)); 	// set selected category object
				
				Attribute attr = util.getAttribute(attribute, category); // get attribute object from API
				srb.setSelectedAttribute(attr);							 // set selected attribute object
				
				if(attr != null){ // if an attribute has been selected we need to check the user inputs
					srb.setUserInput1(req.getParameter("userInput1_"+i));	// set the user input1
					srb.setUserInput2(req.getParameter("userInput2_"+i));	// set the user input2
					
					if("search".equals(reqType)) valid = validate(srb) && valid;	// validate user inputs
					
					// some attributes have a dropdown as user input 
					if("drop_down".equals(attr.getDefaultStorageField()) || "taxonomy_id".equals(attr.getDefaultStorageField()))
						srb.setDropdownPairList(util.getInputDropdownValues(attr));
				}
				// add SearchRowBean to the list
				searchRowBeanList.add(srb);
			}
			
			RequestDispatcher rd = null;
			Gson gson = new Gson(); // needed to transform java objects to JSON
			
			// SHOW = get SearchResultBeanList and forward to searchResult.jsp
			if("show".equals(reqType)) {
				req.setAttribute("srbList", gson.toJson(util.getAllSearchResults(searchRowBeanList)));
				rd = req.getRequestDispatcher("/searchResult.jsp"); // show searchResult.jsp
				rd.forward(req, resp);
			}
			else {
				// initial call of the page - count all spectra available and display them
				if("init".equals(reqType)) 
					req.setAttribute("searchResultCount", util.getSpectrumIdCount());
				// reload is called when category or attribute changes on the GUI
				else if("reload".equals(reqType)) 
					req.setAttribute("searchResultCount", 0);
				// SEARCH - searching for results
				else {
					// if the user input is valid we search for spectra in the db
					if(valid) req.setAttribute("searchResultCount", util.getSpectrumIdList(searchRowBeanList).size());
					// else we do not search and an error message will be displayed on the GUI
					else req.setAttribute("searchResultCount", 0);
				}
		
				req.setAttribute("valid", valid);	// true if user input was valid, false otherwise
				req.setAttribute("categoryList", gson.toJson(util.getCategoryList()));	// list of all categories for category dropdwon
				req.setAttribute("searchRowBeanList", gson.toJson(searchRowBeanList));	// the list of all SearchRowBean's
				
				rd = req.getRequestDispatcher("/search.jsp"); // show search.jsp
				rd.forward(req, resp);
			}
		} 
		catch(Exception e) {
			showErrorPage("There was a problem while processing your request.", "Please try again later or contact the system administrator.", req, resp);
		}
	}
	
	/**
	 * shows the error.jsp
	 */
	private void showErrorPage(String errorTitle, String errorMessage, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		RequestDispatcher rd = req.getRequestDispatcher("/error.jsp");
		req.setAttribute("errorTitle", errorTitle);
		req.setAttribute("errorMessage", errorMessage);
		rd.forward(req, resp);
	}
	
	/**
	 * Checks whether the user inputs in the given SearchRowBean are valid.
	 * @return true if no validation errors were found, false otherwise.
	 */
	private boolean validate(SearchRowBean srb) throws Exception {
		// Full Text Search may contain anything
		if ("Full Text Search".equals(srb.getSelectedCategory().getName())) return true;
		
		switch(srb.getSelectedAttribute().getDefaultStorageField()){
			case "string_val" : // string values may contain anything but must not be empty
				srb.setValidInput1(!srb.getUserInput1().isEmpty());
				srb.setErrorMessage(srb.getSelectedAttribute().getName() + " must not be empty.");
				break;
			case "int_val" :
				srb.setValidInput1(srb.getUserInput1().matches("^[0-9]+$"));
				srb.setValidInput2(srb.getUserInput2().matches("^[0-9]+$"));
				srb.setErrorMessage(srb.getSelectedAttribute().getName() + " must contain numeric values >= 0 only and not be empty.");
				break;
			case "double_val" :
				// location can also contain negative values (altitude, longitude, latitude)
				if("Location".equals(srb.getSelectedCategory().getName())){
					srb.setValidInput1(srb.getUserInput1().matches("^[-]{0,1}[0-9]+.[0-9]+$") 
							|| srb.getUserInput1().matches("^[-]{0,1}[0-9]+$"));
					srb.setValidInput2(srb.getUserInput2().matches("^[-]{0,1}[0-9]+.[0-9]+$")
							|| srb.getUserInput2().matches("^[-]{0,1}[0-9]+$"));
					srb.setErrorMessage(srb.getSelectedAttribute().getName() + " must contain numeric or floating values only and not be empty.");
				}
				else {
					srb.setValidInput1(srb.getUserInput1().matches("^[0-9]+.[0-9]+$") 
							|| srb.getUserInput1().matches("^[0-9]+$"));
					srb.setValidInput2(srb.getUserInput2().matches("^[0-9]+.[0-9]+$")
							|| srb.getUserInput2().matches("^[0-9]+$"));
					srb.setErrorMessage(srb.getSelectedAttribute().getName() + " must contain numeric or floating values >= 0 only and not be empty.");
				}
				
				break;
			case "datetime_val" :
				// user input must match this pattern
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");	
				sdf.setLenient(false);

				try { //if not valid, it will throw ParseException
					sdf.parse(srb.getUserInput1());
					srb.setValidInput1(true);
				} catch (Exception e) {
					srb.setValidInput1(false);
					srb.setErrorMessage(srb.getSelectedAttribute().getName() + " must match Dateformat yyyy-mm-dd.");
				}
				
				try { //if not valid, it will throw ParseException
					sdf.parse(srb.getUserInput2());
					srb.setValidInput2(true);
				} catch (Exception e) {
					srb.setValidInput2(false);
					srb.setErrorMessage(srb.getSelectedAttribute().getName() + " must match Dateformat yyyy-mm-dd.");
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
