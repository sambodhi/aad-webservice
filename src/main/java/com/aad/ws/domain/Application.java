package com.aad.ws.domain;

public class Application {
	
	private long appId;
	private long appCategId;
	private long appTypeId;
	private String appName;
	private String appSize;
	private String developrName;
	private String description;
	private String url;
	private String iconUrl;
	
	public long getAppId() {
		return appId;
	}
	public void setAppId(long appId) {
		this.appId = appId;
	}
	public long getAppCategId() {
		return appCategId;
	}
	public void setAppCategId(long appCategId) {
		this.appCategId = appCategId;
	}
	public long getAppTypeId() {
		return appTypeId;
	}
	public void setAppTypeId(long appTypeId) {
		this.appTypeId = appTypeId;
	}
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public String getAppSize() {
		return appSize;
	}
	public void setAppSize(String appSize) {
		this.appSize = appSize;
	}
	public String getDeveloprName() {
		return developrName;
	}
	public void setDeveloprName(String developrName) {
		this.developrName = developrName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getIconUrl() {
		return iconUrl;
	}
	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}
	public Application(long appId, long appCategId, long appTypeId,
			String appName, String appSize, String developrName,
			String description) {
		super();
		this.appId = appId;
		this.appCategId = appCategId;
		this.appTypeId = appTypeId;
		this.appName = appName;
		this.appSize = appSize;
		this.developrName = developrName;
		this.description = description;
	}
	
	public Application(long appId) {
		super();
		this.appId = appId;
	}
		
	public Application() {
	}
	
	@Override
	public String toString() {
		return "Application [appId=" + appId + ", appCategId=" + appCategId
				+ ", appTypeId=" + appTypeId + ", appName=" + appName
				+ ", appSize=" + appSize + ", developrName=" + developrName
				+ ", description=" + description +  ", iconUrl=" + iconUrl +"]";
	}
	
	
}
