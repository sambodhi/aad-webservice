package com.aad.ws.domain;

public class Application {
	
	private int appId;
	private int appCategId;
	private int appTypeId;
	private String appName;
	private String appSize;
	private String developrName;
	private String description;
	private String url;
	private String iconUrl;
	
	public int getAppId() {
		return appId;
	}
	public void setAppId(int appId) {
		this.appId = appId;
	}
	public int getAppCategId() {
		return appCategId;
	}
	public void setAppCategId(int appCategId) {
		this.appCategId = appCategId;
	}
	public int getAppTypeId() {
		return appTypeId;
	}
	public void setAppTypeId(int appTypeId) {
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
	public Application(int appId, int appCategId, int appTypeId,
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
	
	public Application(int appId) {
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
