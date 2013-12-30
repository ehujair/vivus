<%@ page import="java.io.PrintWriter"%>
<%@ page import="java.util.Date"%>
<%
	response.setCharacterEncoding("UTF-8");
	response.setContentType("text/event-stream");
	response.setHeader("Cache-Control", "no-cache");
	PrintWriter writer = response.getWriter();
	System.out.println("writing data:");
	writer.write("data: " + new Date());
	writer.flush();
%>