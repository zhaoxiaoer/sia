package com.nnniu.wxmp.msgandevent;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="text")
public class TextMessage extends BaseMsg {
	@XmlElement(name="Content")
	private String content;
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	@Override
	public String toString() {
		return "ToUserName: " + getToUserName() + 
				"\nFromUserName: " + getFromUserName() +
				"\nCreateTime: " + getCreateTime() +
				"\nMsgType: " + getMsgType() +
				"\nContent: " + getContent() +
				"\nMsgId: " + getMsgId();
	}
}
