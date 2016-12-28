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

import ch.specchio.model.SearchResultBean;
import ch.specchio.model.SpaceDetailBean;
import ch.specchio.util.IOUtil;
import ch.specchio.util.SpecchioUtil;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@SuppressWarnings("serial")
public class DetailServlet extends HttpServlet {

	
	private void handleRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String linkedSpectrumId = req.getParameter("linkedSpectrumId");
		
		// Converting json-String back to a List of SearchResultBean
		Type listType = new TypeToken<LinkedList<SearchResultBean>>(){}.getType();
		LinkedList<SearchResultBean> srbList = new Gson().fromJson(req.getParameter("selectedSearchResultBeanList"), listType);
		
		SpecchioUtil util = new SpecchioUtil();
		Gson gson = new Gson();
		
		List<SpaceDetailBean> spaceDetailBeanList;
		if(linkedSpectrumId != null)
			spaceDetailBeanList = util.createSpaceDetailBeanList(Integer.parseInt(linkedSpectrumId));
		else 
			spaceDetailBeanList = util.createSpaceDetailBeanList(srbList);
			
		req.setAttribute("spaceDetailBeanList",gson.toJson(spaceDetailBeanList));

		RequestDispatcher rd = req.getRequestDispatcher("/detail.jsp");
		rd.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(req.getParameter("doExport") != null) {
			// Converting json-String back to a List of SearchResultBean
			Type listType = new TypeToken<LinkedList<SpaceDetailBean>>(){}.getType();
			LinkedList<SpaceDetailBean> spaceDetailBeanList = new Gson().fromJson(req.getParameter("spaceDetailBeanList"), listType);
			
			IOUtil.createCsvExportZip(resp, spaceDetailBeanList, ",");
		}
		else handleRequest(req, resp);
	}
	
}
