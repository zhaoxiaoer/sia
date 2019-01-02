package com.nnniu.wxmp.msgandevent;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="normalevent")
public class NormalEvent extends BaseEvent {

	@Override
	public String toString() {
		return "ToUserName: " + this.getToUserName() +
				"\nFromUserName: " + this.getFromUserName() +
				"\nCreateTime: " + this.getCreateTime() +
				"\nMsgType: " + this.getMsgType() +
				"\nEvent: " + this.getEvent();
	}
	
}
