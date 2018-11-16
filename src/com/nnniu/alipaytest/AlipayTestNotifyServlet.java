package com.nnniu.alipaytest;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.*;
import javax.servlet.http.*;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;

/**
 * 支付宝付款异步通知
 * 
 * @author zhaoqinghua
 *
 */
public class AlipayTestNotifyServlet extends HttpServlet{
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		System.out.println("Method: " + request.getMethod());
		System.out.println("Content-Type: " + request.getContentType());
		
		Map<String, String> params = new HashMap<String, String>();
		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i] 
						: valueStr + values[i] + ",";
			}
			System.out.println(name + ":" + valueStr);
			params.put(name,  valueStr);
		}
		try {
			boolean flag = AlipaySignature.rsaCheckV1(params, AlipayTestConfig.ALIPAY_PUBLIC_KEY, 
					AlipayTestConfig.CHARSET, "RSA");
			System.out.println("flag: " + flag);
		} catch (AlipayApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		PrintWriter out = response.getWriter();
		out.print("success");
	}
	
}
