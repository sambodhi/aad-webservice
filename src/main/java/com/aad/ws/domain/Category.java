package com.aad.ws.domain;

public class Category {

	private long categId;
	private String categType;
	
	public String getCategType() {
		return categType;
	}

	public long getCategId() {
		return categId;
	}

	public void setCategType(String categType) {
		this.categType = categType;
	}

	public void setCategId(long categId) {
		this.categId = categId;
	}

	@Override
	public String toString() {
		return "Category [categId=" + categId + ", categType=" + categType
				+ "]";
	}

}
