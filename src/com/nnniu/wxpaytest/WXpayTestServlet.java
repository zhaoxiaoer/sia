package com.nnniu.wxpaytest;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 * 微信订单生成
 * 
 * @author zhaoqinghua
 *
 */
public class WXpayTestServlet extends HttpServlet {
	
	public void init() throws ServletException {
		
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		response.setContentType("application/xml");
		PrintWriter out = response.getWriter();
		
		String nonce_str = createRandomString(32);
		String spbill_create_ip = getRealIP(request);
		String out_trade_no = getOutTradeNo();
		Map<String, String> params = buildUnifiedOrderParamMap(nonce_str, spbill_create_ip, out_trade_no);
		String sign = getSign(params);
		String unifiedOrder = buildSignedXML(params, sign);
		System.out.println("unifiedOrder: " + unifiedOrder);
		
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost post = new HttpPost("https://api.mch.weixin.qq.com/pay/unifiedorder");
		StringEntity entity = new StringEntity(unifiedOrder);
		post.setEntity(entity);
		post.setHeader("Content-Type", "application/xml;charset=utf-8");
		CloseableHttpResponse resp = httpClient.execute(post);
		HttpEntity entity2 = resp.getEntity();
		String entiStr = EntityUtils.toString(entity2);
		System.out.println("entiStr: " + entiStr);
		// 关流
		EntityUtils.consume(entity2);
		resp.close();
		
		try {
			Document document = DocumentHelper.parseText(entiStr);
			Element root = document.getRootElement();
			if ((root.valueOf("return_code").equals("SUCCESS")) 
					&& (root.valueOf("result_code").equals("SUCCESS"))) {
				String prepayId = root.valueOf("prepay_id");
				String nonce_str2 = createRandomString(32);
				Map<String, String> params2 = buildAppPayParamMap(nonce_str2, prepayId);
				String sign2 = getSign(params2);
				String appPay = buildSignedXML(params2, sign2);
				System.out.println("appPay: " + appPay);
				
				out.print(appPay);
				return;
			}
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		out.println("error");
	}
	
	public void destroy() {
		
	}
	
	private String createRandomString(int length) {
		StringBuffer sb = new StringBuffer();
		String seedStr = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		for (int i = 0; i < length; i++) {
			sb.append(seedStr.charAt((int) (Math.random() * 62)));
		}
		return sb.toString();
	}
	
	private String getRealIP(HttpServletRequest request) {
		String ip = request.getRemoteAddr();
		String ipcopy = ip;
		if (ip == null || ip.equals("127.0.0.1") || ip.startsWith("192.168.")
				|| ip.startsWith("172.16.") || ip.startsWith("10.")) {
			ip = request.getHeader("X-Real-IP");
			if (ip == null) {
				ip = ipcopy;
			}
		}
		
		return ip;
	}
	
	private String getOutTradeNo() {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault());
		Date date = new Date();
		String key = format.format(date);
		
		String outTradeNo = key + (int)(Math.random() * 10000000 + 10000000);
		return outTradeNo;
	}
	
	private Map<String, String> buildUnifiedOrderParamMap(String nonceStr, String realIP, String outTradeNo) throws UnsupportedEncodingException {
		Map<String, String> keyValues = new HashMap<String, String>();
		keyValues.put("appid", WXpayTestConfig.APPID);
		keyValues.put("mch_id", WXpayTestConfig.MCHID);
		keyValues.put("nonce_str", nonceStr);
		keyValues.put("body", nonceStr);
//		keyValues.put("detail", "支付测试"); // 无法输入中文
//		keyValues.put("attach", "zhao 支付测试");
		keyValues.put("out_trade_no", outTradeNo);
		keyValues.put("total_fee", Integer.toString(2));
//		keyValues.put("spbill_create_ip", realIP);
		keyValues.put("spbill_create_ip", "125.46.217.7");
		keyValues.put("notify_url", WXpayTestConfig.WXPAY_NOTIFY_URL);
		keyValues.put("trade_type", "APP");
		
		return keyValues;
	}
	
	private String getSign(Map<String, String> map) {
		List<String> keys = new ArrayList<String>(map.keySet());
		// key排序
		Collections.sort(keys);
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < keys.size(); i++) {
			String key = keys.get(i);
			String value = map.get(key);
			sb.append(key + "=" + value + "&");
		}
		
		sb.append("key=" + WXpayTestConfig.APP_KEY);
		System.out.println("total: " + sb.toString());
		return MD5Util.MD5Encode(sb.toString(), "utf-8").toUpperCase();
	}
	
	
	
	private String buildSignedXML(Map<String, String> map, String sign) {
		List<String> keys = new ArrayList<String>(map.keySet());
		
		StringBuilder sb = new StringBuilder();
		sb.append("<xml>");
		for (int i = 0; i < keys.size(); i++) {
			String key = keys.get(i);
			String value = map.get(key);
			sb.append("<" + key + ">" + value + "</" + key + ">");
		}
		sb.append("<sign>" + sign + "</sign>");
		sb.append("</xml>");
		
		return sb.toString();
	}
	
	private Map<String, String> buildAppPayParamMap(String nonceStr, String prepayId) {
		Map<String, String> keyValues = new HashMap<String, String>();
		keyValues.put("appid", WXpayTestConfig.APPID);
		keyValues.put("partnerid", WXpayTestConfig.MCHID);
		keyValues.put("prepayid", prepayId);
		keyValues.put("package", "Sign=WXPay");
		keyValues.put("noncestr", nonceStr);
		keyValues.put("timestamp", Integer.toString((int) (System.currentTimeMillis() / 1000)));
		
		return keyValues;
	}
}
