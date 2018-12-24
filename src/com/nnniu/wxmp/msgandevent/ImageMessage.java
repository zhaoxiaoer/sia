package com.nnniu.wxmp.msgandevent;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="image")
public class ImageMessage extends BaseMsg {
	@XmlElement(name="PicUrl")
	private String picUrl;
	@XmlElement(name="MediaId")
	private String mediaId;
	public String getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	public String getMediaId() {
		return mediaId;
	}
	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}
	
	@Override
	public String toString() {
		return "ToUserName: " + getToUserName() +
				"\nFromUserName: " + getFromUserName() +
				"\nCreateTime: " + getCreateTime() +
				"\nMsgType: " + getMsgType() +
				"\nPicUrl: " + getPicUrl() +
				"\nMediaId: " + getMediaId() +
				"\nMsgId: " + getMsgId();
	}
}
