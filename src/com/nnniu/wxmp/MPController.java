package com.nnniu.wxmp;

import java.io.IOException;
import java.io.StringReader;
import java.net.URLEncoder;
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
import javax.validation.Valid;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.alibaba.fastjson.JSONObject;
import com.nnniu.wxmp.msgandevent.CommonXML;
import com.nnniu.wxmp.msgandevent.ImageMessage;
import com.nnniu.wxmp.msgandevent.TextMessage;
import com.nnniu.wxmp.msgandevent.VideoMessage;
import com.nnniu.wxmp.msgandevent.VoiceMessage;

// @RestController 等价于 @Controller + @ResponseBody
@Controller
public class MPController {
	
	@Autowired
	private Jaxb2Marshaller jaxb2Marshaller;
	
	// 全局AccessToken
	private String accessToken;
	private int expiresIn;
	
	// 通过构造函数，手动触发AccessToken的第一次获取
	public MPController() {
		accessTokenRefresh();
	}
	
	// 服务器配置时，服务器地址的验证
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
		
//		System.out.println("echostr: " + echostr);
		return echostr;
	}
	
	// 被动接收用户消息和事件，并自动回复
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
		
		// 小视频，链接消息，不知怎么测试
		// 根据 MsgType 字段替换根标签
		if (body.indexOf("<MsgType><![CDATA[text]]></MsgType>") != -1) {
			body = body.replace("<xml>", "<text>").replace("</xml>", "</text>");
		} else if (body.indexOf("<MsgType><![CDATA[image]]></MsgType>") != -1) {
			body = body.replace("<xml>", "<image>").replace("</xml>", "</image>");
		} else if (body.indexOf("<MsgType><![CDATA[voice]]></MsgType>") != -1) {
			body = body.replace("<xml>", "<voice>").replace("</xml>", "</voice>");
		} else if (body.indexOf("<MsgType><![CDATA[video]]></MsgType>") != -1) {
			body = body.replace("<xml>", "<video>").replace("</xml>", "</video>");
		} else {
			return "success";
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
	
	// 公众号回复图文消息时的测试地址
	@RequestMapping(method=RequestMethod.GET, value="/wxtestUrl", produces="text/html; charset=UTF-8")
	public ModelAndView msgUrl(HttpServletRequest request, String code, String state) throws IOException {
		// 打印HTTP
		printHttpServletRequest(request);
		
		// 重定向
		if ((code == null) || (code.equals(""))) {
			String redirectUri = "http://" + "39.107.64.191" + request.getRequestURI();
			String totalUri = "https://open.weixin.qq.com/connect/oauth2/authorize?"
					+ "appid=" + MPConfig.APPID + "&"
					+ "redirect_uri=" + URLEncoder.encode(redirectUri) + "&"
					+ "response_type=code&scope=snsapi_base&state=123#wechat_redirect";
			System.out.println("totalUri: " + totalUri);
			return new ModelAndView(new RedirectView(totalUri));
		}
		
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet("https://api.weixin.qq.com/sns/oauth2/access_token?"
				+ "appid=" + MPConfig.APPID + "&"
				+ "secret=" + MPConfig.APPSECRET + "&"
				+ "code=" + code + "&"
				+ "grant_type=authorization_code");
		RequestConfig requestConfig = RequestConfig.custom()
				.setConnectTimeout(5000)
				.setConnectionRequestTimeout(5000)
				.setRedirectsEnabled(false)
				.build();
		httpGet.setConfig(requestConfig);
		CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
		int resCode = httpResponse.getStatusLine().getStatusCode();
		String openId = "";
		if (resCode == HttpStatus.SC_OK) {
			HttpEntity httpEntity = httpResponse.getEntity();
			String entityStr = EntityUtils.toString(httpEntity);
			System.out.println("entityStr: " + entityStr);
			JSONObject json = JSONObject.parseObject(entityStr);
			String accessToken2 = json.getString("access_token");
			int expiresIn2 = json.getIntValue("expires_in");
			String refreshToken = json.getString("refresh_token");
			openId = json.getString("openid");
			String scope = json.getString("scope");
		} else {
			System.out.println("Get OpenId response code: " + resCode);
		}
		
		ModelAndView modelAndView = new ModelAndView();
		User2 user2 = new User2();
		user2.setOpenId(openId);
		modelAndView.setViewName("wxUserForm");
		modelAndView.addObject("user2", user2);
		
		return modelAndView;
	}
	
	@RequestMapping(method=RequestMethod.POST, value="wXUserResult", produces="text/html; charset=UTF-8")
	public ModelAndView processWXUser(@Valid User2 user2, BindingResult bindingResult) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("user2", user2);
		
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("wxUserForm");
		} else {
			// TODO 验证手机号是否正确
			
			modelAndView.setViewName("wxUserResult");
		}
		
		return modelAndView;
	}
	
	/*
	 * 定时刷新access_token
	 */
	@Scheduled(cron="0 0/55 1/2 * * *")
	public void accessTokenRefresh() {
//		System.out.println("accessTokenRefresh...");
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="
				+ MPConfig.APPID + "&secret=" + MPConfig.APPSECRET);
		RequestConfig requestConfig = RequestConfig.custom()
				.setConnectTimeout(5000) // 连接超时时间
				.setConnectionRequestTimeout(5000) // 请求超时时间
				.setRedirectsEnabled(false) // 禁止重定向
				.build();
		httpGet.setConfig(requestConfig);
		try {
			CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
			int resCode = httpResponse.getStatusLine().getStatusCode();
			if (resCode == HttpStatus.SC_OK) {
				HttpEntity httpEntity = httpResponse.getEntity();
				String entityStr = EntityUtils.toString(httpEntity);
				System.out.println("entityStr: " + entityStr);
				JSONObject json = JSONObject.parseObject(entityStr);
				accessToken = json.getString("access_token");
				expiresIn = json.getIntValue("expires_in");
//				System.out.println("accessToken: " + accessToken);
//				System.out.println("expiresIn: " + expiresIn);
			} else {
				System.out.println("Get Access Token response code: " + resCode);
			}
		} catch (IOException e) {
			System.out.println("Get Access Token IOException!");
		} finally {
			try {
				httpClient.close();
			} catch (IOException e) {
				
			}
		}
	}
	
	private boolean checkSignature(String signature, String timestamp, String nonce) {
		if ((signature == null || signature.equals("")) 
				|| (timestamp == null || timestamp.equals("")) 
				|| (nonce == null || nonce.equals(""))) {
			return false;
		}
		
		Map<String, String> m = buildMap(timestamp, nonce);
		String sign = getSign(m);
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
	
	/*private String replyMessage(CommonXML commonXML) {
		// 公共信息
		StringBuilder sb = new StringBuilder();
		sb.append("<xml>");
		sb.append("<ToUserName><![CDATA[" + commonXML.getToUserName() + "]]></ToUserName>");
		sb.append("<FromUserName><![CDATA[" + commonXML.getFromUserName() + "]]></FromUserName>");
		sb.append("<CreateTime>" + commonXML.getCreateTime() + "</CreateTime>");
		sb.append("<MsgType><![CDATA[" + commonXML.getMsgType() + "]]></MsgType>");
		
		if (commonXML.getMsgType().equals("text")) {
			TextMessage textMessage = (TextMessage) commonXML;
			sb.append("<Content><![CDATA[" + textMessage.getContent() + "<a href=\"www.mamaloveme.com\">www.mamaloveme.com</a>" + "]]></Content>");
		} else if (commonXML.getMsgType().equals("image")) {
			ImageMessage imageMessage = (ImageMessage) commonXML;
			sb.append("<Image><MediaId><![CDATA[" + imageMessage.getMediaId() + "]]></MediaId></Image>");
		} else if (commonXML.getMsgType().equals("voice")) {
			VoiceMessage voiceMessage = (VoiceMessage) commonXML;
			sb.append("<Voice><MediaId><![CDATA[" + voiceMessage.getMediaId() + "]]></MediaId></Voice>");
		} else if (commonXML.getMsgType().equals("video")) {
			// 目前回复视频消息出现错误，原因未知
			VideoMessage videoMessage = (VideoMessage) commonXML;
			sb.append("<Video><MediaId><![CDATA[" + videoMessage.getMediaId() + "]]></MediaId>");
			sb.append("<Title><![CDATA[title]]></Title>");
			sb.append("<Description><![CDATA[description]]></Description></Video>");
		}
		
		sb.append("</xml>");
		return sb.toString();
	}*/
	
	private String replyMessage(CommonXML commonXML) {
		// 公共信息
		StringBuilder sb = new StringBuilder();
		sb.append("<xml>");
		sb.append("<ToUserName><![CDATA[" + commonXML.getToUserName() + "]]></ToUserName>");
		sb.append("<FromUserName><![CDATA[" + commonXML.getFromUserName() + "]]></FromUserName>");
		sb.append("<CreateTime>" + commonXML.getCreateTime() + "</CreateTime>");
		sb.append("<MsgType><![CDATA[news]]></MsgType>");
		sb.append("<ArticleCount>1</ArticleCount>");
		sb.append("<Articles><item>");
		
		sb.append("<Title><![CDATA[title1]]></Title>");
		sb.append("<Description><![CDATA[description1]]></Description>");
		sb.append("<PicUrl><![CDATA[http://mmbiz.qpic.cn/mmbiz_jpg/ibz3ZOnlLU3YGkNMorGUD9ia8n5GP0YRc52h1G73TfqB11ovS747fysf05ZVukTVGHJicvtZkS0Y104oWojHZbuSQ/0]]></PicUrl>");
		sb.append("<Url><![CDATA[http://39.107.64.191/sia/wxtestUrl]]></Url>");
		
		sb.append("</item></Articles>");
		sb.append("</xml>");
		return sb.toString();
	}
	
	private void printHttpServletRequest(HttpServletRequest request) {
		System.out.println(request.getMethod() + " " + request.getRequestURI() + 
				"?" + request.getQueryString() + " " + request.getProtocol());
		Enumeration<String> keys = request.getHeaderNames();
		while (keys.hasMoreElements()) {
			String key = keys.nextElement();
			String value = request.getHeader(key);
			System.out.println(key + ": " + value);
		}
		System.out.println("");
	}
}
