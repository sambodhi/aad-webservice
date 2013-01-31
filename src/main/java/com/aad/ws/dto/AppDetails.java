
package com.aad.ws.dto;

public class AppDetails {

	private long id;
	private String name;
	private String url;
	private String iconUrl;
	private String categoryName;
	private String size;
	private String description;
	private String developer;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getIconUrl() {
		return iconUrl;
	}
	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}
	
	public String getDeveloper() {
		return developer;
	}
	public void setDeveloper(String developer) {
		this.developer = developer;
	}
	
	@Override
	public String toString() {
		return "AppDetails [name=" + name + ", url=" + url + ", iconUrl="
				+ iconUrl + ", categoryName=" + categoryName + ", size=" + size
				+ ", description=" + description + ", developer=" + developer
				+ "]";
	}
	
}
