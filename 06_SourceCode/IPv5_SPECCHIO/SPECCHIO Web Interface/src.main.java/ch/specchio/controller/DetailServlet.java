package ch.specchio.controller;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ch.specchio.client.SPECCHIOClientException;
import ch.specchio.model.SearchResultBean;
import ch.specchio.model.SpaceDetailBean;
import ch.specchio.util.IOUtil;
import ch.specchio.util.SpecchioUtil;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@SuppressWarnings("serial")
public class DetailServlet extends HttpServlet {

	
	private void handleRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// create a new SpecchioUtil object. it allows all interactions with the db.
		SpecchioUtil util = null;
		try {
			util = new SpecchioUtil();
		} catch (Exception e) { // if the application is not able to connect to the DB show error page
			showErrorPage("Could not connect to database", "Please try again later or contact the system administrator.", req, resp);
		}
		
		// if the user wants to see a linked spectrum this will contain the spectrum id
		String linkedSpectrumId = req.getParameter("linkedSpectrumId");
		
		// Converting json-String back to a List of SearchResultBean
		Type listType = new TypeToken<LinkedList<SearchResultBean>>(){}.getType();
		LinkedList<SearchResultBean> srbList = new Gson().fromJson(req.getParameter("selectedSearchResultBeanList"), listType);
		
		Gson gson = new Gson(); // used to transform java objects to JSON
		
		List<SpaceDetailBean> spaceDetailBeanList; // contains all spaces with their spectrum information
		try {
			if(linkedSpectrumId != null) // show the selected linked spectrum
				spaceDetailBeanList = util.createSpaceDetailBeanList(Integer.parseInt(linkedSpectrumId));
			else // show all spectra inside the SearchResultBeanList
				spaceDetailBeanList = util.createSpaceDetailBeanList(srbList);
		
			req.setAttribute("spaceDetailBeanList",gson.toJson(spaceDetailBeanList));
	
			RequestDispatcher rd = req.getRequestDispatcher("/detail.jsp"); // show detail.jsp
			rd.forward(req, resp);
		} 
		catch(SPECCHIOClientException e){ // if the API throws an Exception we show the error page
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
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// ZIP Export of Detail View Data
		if(req.getParameter("doExport") != null) {
			// Converting json-String back to a List of SearchResultBean
			Type listType = new TypeToken<LinkedList<SpaceDetailBean>>(){}.getType();
			LinkedList<SpaceDetailBean> spaceDetailBeanList = new Gson().fromJson(req.getParameter("spaceDetailBeanList"), listType);
			
			// prepare response for file download
			resp.setContentType("application/zip");
			resp.setHeader("Content-Disposition", "attachment; filename=\"specchio_csv_export.zip\"");
			
			try { // create ZIP
				IOUtil.createCsvExportZip(resp, spaceDetailBeanList, ",");
			} catch (Exception e) {
				// if the ZIP File couldn't be created, an error message will be displayed
				// once the user tries to open the file
			}
		}
		// else handle the normal request (show detail)
		else handleRequest(req, resp);
	}
	
}
