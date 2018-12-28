package com.nnniu.wxmp.msgandevent;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="video")
public class VideoMessage extends BaseMsg {
	@XmlElement(name="MediaId")
	private String mediaId;
	@XmlElement(name="ThumbMediaId")
	private String thumbMediaId;
	public String getMediaId() {
		return mediaId;
	}
	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}
	public String getThumbMediaId() {
		return thumbMediaId;
	}
	public void setThumbMediaId(String thumbMediaId) {
		this.thumbMediaId = thumbMediaId;
	}
	
	@Override
	public String toString() {
		return "ToUserName: " + this.getToUserName() +
				"\nFromUserName: " + this.getFromUserName() +
				"\nCreateTime: " + this.getCreateTime() +
				"\nMsgType: " + this.getMsgType() +
				"\nMediaId: " + this.getMediaId() +
				"\nThumbMediaId: " + this.getThumbMediaId() +
				"\nMsgId: " + this.getMsgId();
	}
}
