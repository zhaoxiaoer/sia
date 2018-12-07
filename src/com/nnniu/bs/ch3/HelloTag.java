package com.nnniu.bs.ch3;

import java.io.IOException;
import java.io.StringWriter;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class HelloTag extends SimpleTagSupport {
	
	private String msg;
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	private StringWriter sw = new StringWriter();
	
	public void doTag() throws JspException, IOException {
		getJspBody().invoke(sw);
		
		JspWriter out = getJspContext().getOut();
		out.println("Hello msg: " + msg + ", body: " + sw.toString());
	}
	
}
