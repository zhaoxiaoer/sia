package com.nnniu.wxmp;

import java.io.IOException;
import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nnniu.wxmp.msgandevent.CommonXML;
import com.nnniu.wxmp.msgandevent.ImageMessage;
import com.nnniu.wxmp.msgandevent.TextMessage;
import com.nnniu.wxmp.msgandevent.VoiceMessage;

// @RestController 等价于 @Controller + @ResponseBody
@Controller
public class MPController {
	
	@Autowired
	private Jaxb2Marshaller jaxb2Marshaller;
	
	@RequestMapping(method=RequestMethod.GET, value="/wx", produces="text/plain; charset=UTF-8")
	@ResponseBody
	public String checkServer(HttpServletRequest request, 
			String signature, String timestamp, String nonce, String echostr) {
		// 打印HTTP
		System.out.println(request.getMethod() + " " + request.getRequestURI() + 
				"?" + request.getQueryString() +
				" " + request.getProtocol());
		Enumeration<String> keys = request.getHeaderNames();
		while (keys.hasMoreElements()) {
			String key = keys.nextElement();
			String value = request.getHeader(key);
			System.out.println(key + ": " + value);
		}
		System.out.println("");
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
		
		if (!checkSignature(signature, timestamp, nonce)) {
			return "error";
		}
		
		return echostr;
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/wx", produces="application/xml; charset=UTF-8")
	@ResponseBody
	public String handleMessage(HttpServletRequest request, String signature, 
			long timestamp, String nonce, String openid, @RequestBody String body) {
		if (!checkSignature(signature, timestamp + "", nonce)) {
			return "error";
		}
		if (body == null || body.equals("")) {
			return "error";
		}
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(": " + simpleDateFormat.format(new Date(timestamp * 1000)));
//		System.out.println("origin body: " + body);
		
		// 根据 MsgType 字段替换根标签
		if (body.indexOf("<MsgType><![CDATA[text]]></MsgType>") != -1) {
			body = body.replace("<xml>", "<text>").replace("</xml>", "</text>");
		} else if (body.indexOf("<MsgType><![CDATA[image]]></MsgType>") != -1) {
			body = body.replace("<xml>", "<image>").replace("</xml>", "</image>");
		} else if (body.indexOf("<MsgType><![CDATA[voice]]></MsgType>") != -1) {
			body = body.replace("<xml>", "<voice>").replace("</xml>", "</voice>");
		} else {
			return "error";
		}
		System.out.println("body: " + body);
		
		CommonXML message = (CommonXML) jaxb2Marshaller.unmarshal(
				new StreamSource(new StringReader(body)));
		System.out.println("message: " + message);
				
		// 回复
		String to = message.getFromUserName();
		message.setFromUserName(message.getToUserName());
		message.setToUserName(to);
		// 手动组装回复XML
		String replyStr = replyMessage(message);
		System.out.println("out: " + replyStr);
		return replyStr;
	}
	
	private boolean checkSignature(String signature, String timestamp, String nonce) {
		if ((signature == null || signature.equals("")) 
				|| (timestamp == null || timestamp.equals("")) 
				|| (nonce == null || nonce.equals(""))) {
			return false;
		}
		
		Map<String, String> m = buildMap(timestamp, nonce);
		String sign = getSign2(m);
//		System.out.println("sign: " + sign);
//		System.out.println("sig2: " + signature);
		if (!sign.equals(signature)) {
			return false;
		}
		
		return true;
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
//		System.out.println("str: " + sb.toString());
		return DigestUtils.sha1Hex(sb.toString().getBytes());
	}
	
	private String replyMessage(CommonXML commonXML) {
		// 公共信息
		StringBuilder sb = new StringBuilder();
		sb.append("<xml>");
		sb.append("<ToUserName><![CDATA[" + commonXML.getToUserName() + "]]></ToUserName>");
		sb.append("<FromUserName><![CDATA[" + commonXML.getFromUserName() + "]]></FromUserName>");
		sb.append("<CreateTime>" + commonXML.getCreateTime() + "</CreateTime>");
		sb.append("<MsgType><![CDATA[" + commonXML.getMsgType() + "]]></MsgType>");
		
		if (commonXML.getMsgType().equals("text")) {
			TextMessage textMessage = (TextMessage) commonXML;
			sb.append("<Content><![CDATA[" + textMessage.getContent() + "]]></Content>");
		} else if (commonXML.getMsgType().equals("image")) {
			ImageMessage imageMessage = (ImageMessage) commonXML;
			sb.append("<Image><MediaId><![CDATA[" + imageMessage.getMediaId() + "]]></MediaId></Image>");
		} else {
			VoiceMessage voiceMessage = (VoiceMessage) commonXML;
			sb.append("<Voice><MediaId><![CDATA[" + voiceMessage.getMediaId() + "]]></MediaId></Voice>");
		}
		
		sb.append("</xml>");
		return sb.toString();
	}
}
