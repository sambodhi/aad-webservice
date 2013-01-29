package com.aad.ws.domain;

public class Category {

	private int categId;
	private String categType;
	
	public String getCategType() {
		return categType;
	}

	public int getCategId() {
		return categId;
	}

	public void setCategType(String categType) {
		this.categType = categType;
	}

	public void setCategId(int categId) {
		this.categId = categId;
	}

	@Override
	public String toString() {
		return "Category [categId=" + categId + ", categType=" + categType
				+ "]";
	}

}
