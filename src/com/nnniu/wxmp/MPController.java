package com.nnniu.wxmp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

// @RestController 等价于 @Controller + @ResponseBody
@Controller
public class MPController {
	
	@RequestMapping(value="/wx", produces="text/plain; charset=UTF-8")
	@ResponseBody
	public String checkServer(HttpServletRequest request, 
			String signature, String timestamp, String nonce, String echostr) {
		// 打印HTTP
		System.out.println(request.getMethod() + " " + request.getRequestURI() + 
				" " + request.getProtocol() + "?" + request.getQueryString());
		Enumeration<String> keys = request.getHeaderNames();
		while (keys.hasMoreElements()) {
			String key = keys.nextElement();
			String value = request.getHeader(key);
			System.out.println(key + ": " + value);
		}
		try {
			ServletInputStream servletInputStream = request.getInputStream();
			StringBuilder stringBuilder = new StringBuilder();
			byte[] buffer = new byte[10240];
			int lens = -1;
			while ((lens = servletInputStream.read(buffer)) > 0) {
				stringBuilder.append(new String(buffer, 0, lens));
			}
			System.out.println(stringBuilder.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if ((signature == null || signature.equals("")) 
				|| (timestamp == null || timestamp.equals("")) 
				|| (nonce == null || nonce.equals("")) 
				|| (echostr == null || echostr.equals(""))) {
			return "error";
		}
		
		Map<String, String> m = buildMap(timestamp, nonce);
		String sign = getSign2(m);
		System.out.println("sign: " + sign);
		System.out.println("sig2: " + signature);
		if (!sign.equals(signature)) {
			return "error";
		}
		
		return echostr;
		
//		ModelAndView modelAndView = new ModelAndView();
//		modelAndView.setViewName("wxmp");
//		modelAndView.addObject("message", "1111");
		
//		return modelAndView;
	}
	
	private Map<String, String> buildMap(String timestamp, String nonce) {
		Map<String, String> keyValues = new HashMap<String, String>();
		keyValues.put("timestamp", timestamp);
		keyValues.put("nonce", nonce);
		keyValues.put("token", MPConfig.TOKEN);
		return keyValues;
	}
	
	private String getSign(Map<String, String> map) {
		Set<String> s = map.keySet();
		List<String> l = new ArrayList<String>(s);
		Collections.sort(l);
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < l.size(); i++) {
			String key = l.get(i);
			String value = map.get(key);
			sb.append(key + "=" + value);
		}
		System.out.println("str: " + sb.toString());
		return DigestUtils.sha1Hex(sb.toString().getBytes());
	}
	
	private String getSign2(Map<String, String> map) {
		Collection<String> col = map.values();
		List<String> list = new ArrayList<String>(col);
		Collections.sort(list);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < list.size(); i++) {
			sb.append(list.get(i));
		}
		System.out.println("str: " + sb.toString());
		return DigestUtils.sha1Hex(sb.toString().getBytes());
	}
	
}
