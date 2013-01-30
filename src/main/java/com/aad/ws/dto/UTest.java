package com.aad.ws.dto;
import com.aad.ws.domain.Results;

public class UTest {
	long TEST_ID;
	int APP_ID;
	long TOTAL_QUESTIONS;
	String TEST_NAME;
	
	public String getTEST_NAME() {
		return TEST_NAME;
	}
	public void setTEST_NAME(String tEST_NAME) {
		TEST_NAME = tEST_NAME;
	}
	public void setTEST(Results res)
	{
		this.TEST_ID=res.gettestid();
		this.APP_ID=res.getappid();
		this.TOTAL_QUESTIONS=res.gettotalquestions();
	}
	public long getTEST_ID() {
		return TEST_ID;
	}
	public void setTEST_ID(long tEST_ID) {
		TEST_ID = tEST_ID;
	}
	public int getAPP_ID() {
		return APP_ID;
	}
	public void setAPP_ID(int aPP_ID) {
		APP_ID = aPP_ID;
	}
	public long getTOTAL_QUESTIONS() {
		return TOTAL_QUESTIONS;
	}
	public void setTOTAL_QUESTIONS(long tOTAL_QUESTIONS) {
		TOTAL_QUESTIONS = tOTAL_QUESTIONS;
	}
	@Override
	public String toString() {
		return "UTest [TEST_ID=" + TEST_ID + ", APP_ID=" + APP_ID
				+ ", TOTAL_QUESTIONS=" + TOTAL_QUESTIONS + "]";
	}
	
	
}
