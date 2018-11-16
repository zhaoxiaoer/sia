package com.nnniu.alipaytest;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.*;
import javax.servlet.http.*;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradeRefundResponse;

/**
 * 支付宝退款测试
 * 
 * @author zhaoqinghua
 *
 */
public class AlipayTestRefundServlet extends HttpServlet {
	
	private AlipayClient alipayClient;
	
	public void init() throws ServletException {
		alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do",
				AlipayTestConfig.APPID, AlipayTestConfig.APP_PRIVATE_KEY, "json",
				AlipayTestConfig.CHARSET, AlipayTestConfig.ALIPAY_PUBLIC_KEY, "RSA");
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		response.setContentType("text/plain");
		PrintWriter out = response.getWriter();
		
		String out_trade_no = request.getParameter("out_trade_no");
		String trade_no = request.getParameter("trade_no");
		String refund_amount = request.getParameter("refund_amount");
		System.out.println("out_trade_no: " + out_trade_no +
				", trade_no: " + trade_no +
				", refund_amount: " + refund_amount);
		if (((out_trade_no == null) || (out_trade_no.equals("")))
				|| ((trade_no == null) || (trade_no.equals("")))
				|| ((refund_amount == null) || (refund_amount.equals("")))) {
			out.println("参数错误");
			return;
		}
		
		AlipayTradeRefundRequest alipayTradeRefundRequest = new AlipayTradeRefundRequest();
		alipayTradeRefundRequest.setBizContent("{" +
				"\"out_trade_no\":" + out_trade_no + "," +
				"\"trade_no\":" + trade_no + "," +
				"\"refund_amount\":" + refund_amount + "," +
				"\"refund_currency\":\"CNY\"," +
				"\"refund_reason\":\"退款测试\"," +
				"\"operator_id\":\"zqh001\"" +
				"}");
		try {
			AlipayTradeRefundResponse alipayTradeRefundResponse = alipayClient.execute(alipayTradeRefundRequest);
			if (alipayTradeRefundResponse.isSuccess()) {
				out.println("退款成功");
			} else {
				out.println("退款失败:" + alipayTradeRefundResponse.getMsg());
			}
		} catch (AlipayApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			out.println("执行异常");
		}
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		response.setContentType("text/plain");
		PrintWriter out = response.getWriter();
		
		StringBuffer sb = new StringBuffer();
		String line = "";
		try {
			BufferedReader reader = request.getReader();
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
			
			JSONObject json = JSONObject.parseObject(sb.toString());
			String out_trade_no = json.getString("out_trade_no");
			String trade_no = json.getString("trade_no");
			String refund_amount = json.getString("refund_amount");
			System.out.println("out_trade_no: " + out_trade_no +
					", trade_no: " + trade_no +
					", refund_amount: " + refund_amount);
			if (((out_trade_no == null) || (out_trade_no.equals("")))
					|| ((trade_no == null) || (trade_no.equals("")))
					|| ((refund_amount == null) || (refund_amount.equals("")))) {
				out.println("参数错误");
				return;
			}
			
			AlipayTradeRefundRequest alipayTradeRefundRequest = new AlipayTradeRefundRequest();
			alipayTradeRefundRequest.setBizContent("{" +
					"\"out_trade_no\":" + out_trade_no + "," +
					"\"trade_no\":" + trade_no + "," +
					"\"refund_amount\":" + refund_amount + "," +
					"\"refund_currency\":\"CNY\"," +
					"\"refund_reason\":\"退款测试\"," +
					"\"operator_id\":\"zqh001\"" +
					"}");
			
			AlipayTradeRefundResponse alipayTradeRefundResponse = alipayClient.execute(alipayTradeRefundRequest);
			if (alipayTradeRefundResponse.isSuccess()) {
				out.println("退款成功");
			} else {
				out.println("退款失败:" + alipayTradeRefundResponse.getMsg());
			}			
			
		} catch (AlipayApiException e) {
			e.printStackTrace();
			out.println("执行异常");
		} catch (Exception e) {
			e.printStackTrace();
			out.println("参数错误");
			return;
		}
	}
	
	public void destroy() {
		
	}
	
}
