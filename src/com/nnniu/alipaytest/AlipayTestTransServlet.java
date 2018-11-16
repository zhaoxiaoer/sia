package com.nnniu.alipaytest;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.*;
import javax.servlet.http.*;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayFundTransToaccountTransferRequest;
import com.alipay.api.response.AlipayFundTransToaccountTransferResponse;

public class AlipayTestTransServlet extends HttpServlet {
	
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
		
		AlipayFundTransToaccountTransferRequest req = new AlipayFundTransToaccountTransferRequest();
		req.setBizContent("{" +
				"	\"out_biz_no\":" + getOutBizNo() + "," +
				"	\"payee_type\":\"ALIPAY_LOGONID\"," +
				"	\"payee_account\":\"13623823754\"," +
				"	\"amount\":\"0.03\"," +
				"	\"payer_show_name\":\"妈妈爱我公司\"," +
				"	\"payee_real_name\":\"赵庆花\"," +
				"	\"remark\":\"转账测试\"" +
				"}");
		try {
			AlipayFundTransToaccountTransferResponse resp = alipayClient.execute(req);
			if (resp.isSuccess()) {
				out.println("转账成功");
			} else {
				out.println("转账失败");
			}
		} catch (AlipayApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			out.println("转账异常: " + e.toString());
		}
	}
	
	public void destroy() {
		
	}
	
	private static String getOutBizNo() {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault());
		Date date = new Date();
		String key = format.format(date);
		
		String outTradeNo = key + (int)(Math.random() * 10000000 + 10000000);
		return outTradeNo;
	}
}
