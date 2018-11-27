package com.nnniu.wxpaytest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.nnniu.alipaytest.AlipayTestConfig;

public class WXpayTestNotifyServlet extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		System.out.println("Method: " + request.getMethod());
		System.out.println("Content-Type: " + request.getContentType());
		
		String line = "";
		BufferedReader reader = request.getReader();
		while ((line = reader.readLine()) != null) {
			System.out.println(line);
		}
		
		PrintWriter out = response.getWriter();
		out.print("<xml>\n" + 
				"  <return_code><![CDATA[SUCCESS]]></return_code>\n" + 
				"  <return_msg><![CDATA[OK]]></return_msg>\n" + 
				"</xml>");
	}
	
}
