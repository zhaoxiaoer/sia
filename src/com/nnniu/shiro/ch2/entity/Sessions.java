package com.nnniu.shiro.ch2.entity;

public class Sessions {

	private Long id;
	private String sessionId;
	private String sessionValue;
	
	public Sessions() {
		
	}
	
	public Sessions(String sessionId, String sessionValue) {
		this.sessionId = sessionId;
		this.sessionValue = sessionValue;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getSessionValue() {
		return sessionValue;
	}

	public void setSessionValue(String sessionValue) {
		this.sessionValue = sessionValue;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Sessions sessions = (Sessions) o;
		if (sessionId != null ? !sessionId.equals(sessions.sessionId) : sessions.sessionId != null)
			return false;
		
		return true;
	}
	
	@Override
	public int hashCode() {
		return sessionId != null ? sessionId.hashCode() : 0;
	}
	
	@Override
	public String toString() {
		return "Sessions{" +
				"sessionId='" + sessionId + "'" + 
				", sessionValue='" + sessionValue + "'" +
				"}";
	}
	
}
