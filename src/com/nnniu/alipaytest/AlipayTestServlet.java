package com.nnniu.alipaytest;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.*;
import javax.servlet.http.*;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;

public class AlipayTestServlet extends HttpServlet {
    
    private AlipayClient alipayClient;
    
	public void init() throws ServletException {
		alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do",
				AlipayTestConfig.APPID, AlipayTestConfig.APP_PRIVATE_KEY, "json", 
				AlipayTestConfig.CHARSET, AlipayTestConfig.ALIPAY_PUBLIC_KEY, "RSA");
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		response.setContentType("text/plain");
		
		AlipayTradeAppPayRequest alipayTradeAppPayRequest = new AlipayTradeAppPayRequest();
		AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
		model.setBody("测试订单，订单金额为0.02元");
		model.setSubject("支付测试");
		model.setOutTradeNo(getOutTradeNo());
		model.setTimeoutExpress("30m");
		model.setTotalAmount("0.02");
		model.setProductCode("QUICK_MSECURITY_PAY");
		alipayTradeAppPayRequest.setBizModel(model);
		alipayTradeAppPayRequest.setNotifyUrl(AlipayTestConfig.ALIPAY_NOTIFY_URL);
		
		String orderString = "";
		try {
			AlipayTradeAppPayResponse alipayTradeAppPayResponse = alipayClient.sdkExecute(alipayTradeAppPayRequest);
			orderString = alipayTradeAppPayResponse.getBody();
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}
		
		PrintWriter out = response.getWriter();
		out.print(orderString);
	}
	
	public void destroy() {
		
	}
	
	private static String getOutTradeNo() {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault());
		Date date = new Date();
		String key = format.format(date);
		
		String outTradeNo = key + (int)(Math.random() * 10000000 + 10000000);
		return outTradeNo;
	}
}
