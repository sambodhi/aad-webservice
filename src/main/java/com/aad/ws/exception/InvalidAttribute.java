package com.aad.ws.exception;

public class InvalidAttribute extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5629789706177299444L;
	
	private int errorCode = 400;
	private String errorAttribute;
	
	public int getErrorCode() {
		return errorCode;
	}
	
	public String getErrorAttribute() {
		return errorAttribute;
	}

	public InvalidAttribute() {
		super();
	}
	
	public InvalidAttribute(final String errorAttribute, final String msg) {
		super(msg);
		this.errorAttribute = errorAttribute;
	}
}
