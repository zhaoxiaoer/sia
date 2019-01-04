package com.nnniu.wxmp.msgandevent;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="qrscanevent")
public class QrscanEvent extends BaseEvent {
	@XmlElement(name="EventKey")
	private int eventKey;
	@XmlElement(name="Ticket")
	private String ticket;

	public int getEventKey() {
		return eventKey;
	}
	
	public void setEventKey(int eventKey) {
		this.eventKey = eventKey;
	}

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
	
	@Override
	public String toString() {
		return "ToUserName: " + this.getToUserName() +
				"\nFromUserName: " + this.getFromUserName() +
				"\nCreateTime: " + this.getCreateTime() +
				"\nMsgType: " + this.getMsgType() +
				"\nEvent: " + this.getEvent() +
				"\nEventKey: " + this.getEventKey() +
				"\nTicket: " + this.getTicket();
	}
}
