package com.aad.ws.dto;

public class Type {
	
	int typeId;
	String appType; 
	String appExtention;
	
	public int getTypeId() {
		return typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	public String getAppType() {
		return appType;
	}
	public void setAppType(String appType) {
		this.appType = appType;
	}
	public String getAppExtention() {
		return appExtention;
	}
	public void setAppExtention(String appExtention) {
		this.appExtention = appExtention;
	}
	
	@Override
	public String toString() {
		return "Type [typeId=" + typeId + ", appType=" + appType
				+ ", appExtention=" + appExtention + "]";
	}
	
	
}
