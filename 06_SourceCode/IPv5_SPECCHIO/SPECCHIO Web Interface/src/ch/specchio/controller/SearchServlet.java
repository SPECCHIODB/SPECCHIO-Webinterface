package ch.specchio.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ch.specchio.model.Attribute;
import ch.specchio.util.SpecchioUtil;


@SuppressWarnings("serial")
public class SearchServlet extends HttpServlet {

	
	private void handleRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String category = req.getParameter("category") != null ? req.getParameter("category") : getServletConfig().getInitParameter("category");
		String attribute = req.getParameter("attribute");
		
		SpecchioUtil util = new SpecchioUtil();
		
		
		req.setAttribute("categoryList", util.getCategoryList());
		req.setAttribute("attributeList", util.getAttributeList(category));
		
		req.setAttribute("selectedCategory", util.getCategory(category));
		
		Attribute attr = util.getAttribute(attribute, category);
		req.setAttribute("selectedAttribute", attr);
		
		if(attr != null){
			if("drop_down".equals(attr.getDefaultStorageField())){
				req.setAttribute("inputDropdownValues", util.getInputDropdownValues(attr));
			}
			else if("taxonomy_id".equals(attr.getDefaultStorageField())){
				req.setAttribute("inputDropdownValues", util.getTaxonomyList(attr));
			}
		}
		
		
		// show search.jsp
		RequestDispatcher rd = req.getRequestDispatcher("/search.jsp");
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
