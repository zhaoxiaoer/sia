package com.nnniu.wxmp.msgandevent;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="voice")
public class VoiceMessage extends BaseMsg {
	@XmlElement(name="MediaId")
	private String mediaId;
	@XmlElement(name="Format")
	private String format;
	@XmlElement(name="Recognition")
	private String recognition;
	public String getMediaId() {
		return mediaId;
	}
	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	public String getRecognition() {
		return recognition;
	}
	public void setRecognition(String recognition) {
		this.recognition = recognition;
	}
	
	@Override
	public String toString() {
		return "ToUserName: " + this.getToUserName() +
				"\nFromUserName: " + this.getFromUserName() +
				"\nCreateTime: " + this.getCreateTime() +
				"\nMsgType: " + this.getMsgType() +
				"\nMediaId: " + this.getMediaId() +
				"\nFormat: " + this.getFormat() +
				"\nRecognition: " + this.getRecognition() +
				"\nMsgId: " + this.getMsgId();
	}
}
