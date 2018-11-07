package com.nnniu.springtest;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.*;
import javax.servlet.http.*;

public class MyServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest requst, HttpServletResponse response) 
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("MyServlet");
	}
	
}
