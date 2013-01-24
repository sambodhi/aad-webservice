package com.aad.ws.exception;

public class ExceptionInfo {

	private int errorCode = 400;
	private String errorAttribute;
	private String msg;
	
	public ExceptionInfo(int errorCode, String errorAttribute, String msg) {
		super();
		this.errorCode = errorCode;
		this.errorAttribute = errorAttribute;
		this.msg = msg;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public String getErrorAttribute() {
		return errorAttribute;
	}

	public String getMsg() {
		return msg;
	}
	
}
