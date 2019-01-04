package com.nnniu.wxmp.msgandevent;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="qrsubevent")
public class QrsubEvent extends BaseEvent {
	@XmlElement(name="EventKey")
	private String eventKey;
	@XmlElement(name="Ticket")
	private String ticket;

	public String getEventKey() {
		return eventKey;
	}
	
	public void setEventKey(String eventKey) {
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
