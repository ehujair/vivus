package org.vivus.fileupload.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FileSelectionServlet extends HttpServlet {
	private static final long serialVersionUID = -7603648801908404968L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
			IOException {
		resp.setContentType("text/html; charset=UTF-8");
		resp.setStatus(HttpServletResponse.SC_OK);
		resp.getWriter().println("<form action=\"file\" enctype=\"multipart/form-data\" method=\"post\">");
		resp.getWriter().println("<input name=\"description\" type=\"text\" >");
		resp.getWriter().println("<input name=\"file1\" type=\"file\" >");
		resp.getWriter().println("<input type=\"submit\" value=\"submit\">");
		resp.getWriter().println("</form>");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
			IOException {
		// TODO Auto-generated method stub
		super.doPut(req, resp);
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doDelete(req, resp);
	}
}
