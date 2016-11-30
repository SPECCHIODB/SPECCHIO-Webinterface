package ch.specchio.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import antlr.collections.List;
import ch.specchio.model.MetaDataBean;
import ch.specchio.util.SpecchioUtil;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.LinkedList;

@SuppressWarnings("serial")
public class SearchResultServlet extends HttpServlet {

	
	private void handleRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// Converting json-String back to a List of MetaDataBeans
		Type listType = new TypeToken<LinkedList<MetaDataBean>>(){}.getType();
		LinkedList<MetaDataBean> mdbList = new Gson().fromJson(req.getParameter("selectedMetaDataBeanList"), listType);
		
		System.out.println(mdbList.size());
		
		SpecchioUtil util = new SpecchioUtil();
		
		if(mdbList.size() == 1){
			
		}
		
		
		req.setAttribute("metaDataBeanList", new Gson().toJson(mdbList));
		
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
