package com.aad.ws.dto;

public class UserSession {
//	java.sql.Timestamp timestamp;
	long userSessionId;
	String tempStr;
	
	/*	public java.sql.Timestamp getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(java.sql.Timestamp timestamp) {
		this.timestamp = timestamp;
	}*/
	
	@Override
	public String toString() {
		return "UserSession [userSessionId=" + userSessionId + ", tempStr="
				+ tempStr + "]";
	}
	
	public long getUserSessionId() {
		return userSessionId;
	}

	public String getTempStr() {
		return tempStr;
	}
	public void setTempStr(String tempStr) {
		this.tempStr = tempStr;
	}
	public void setUserSessionId(long userSessionId) {
		this.userSessionId = userSessionId;
	}
	
	
}
