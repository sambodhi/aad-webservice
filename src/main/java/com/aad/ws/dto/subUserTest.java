package com.aad.ws.dto;
import com.aad.ws.domain.Results;

public class subUserTest {
	long TEST_ID;
	long USER_SESSION_ID;
	double SCORE;
	long OUT_OF;
	long TIME;
	long QUES_ATTENDED;
	String DIFFICULTY;
	String START_TIME;
	long TOTAL_QUESTIONS;
	
	
	public subUserTest (Results res, long user_sessionId)
	{
		this.TEST_ID=res.gettestid();
		this.USER_SESSION_ID=user_sessionId;
		this.SCORE=res.gettotalmark();
		this.OUT_OF=res.gettotalquestions();
		this.TIME=res.gettotaltime();
		this.QUES_ATTENDED=res.getquesattended();
		this.DIFFICULTY=res.getdifficulty();
		this.START_TIME=res.getteststarttime();
		this.TOTAL_QUESTIONS=res.gettotalquestions();
	}
	public long getTEST_ID() {
		return TEST_ID;
	}
	public void setTEST_ID(long tEST_ID) {
		TEST_ID = tEST_ID;
	}
	public long getUSER_SESSION_ID() {
		return USER_SESSION_ID;
	}
	public void setUSER_SESSION_ID(long uSER_SESSION_ID) {
		USER_SESSION_ID = uSER_SESSION_ID;
	}
	public double getSCORE() {
		return SCORE;
	}
	public void setSCORE(double sCORE) {
		SCORE = sCORE;
	}
	public long getOUT_OF() {
		return OUT_OF;
	}
	public void setOUT_OF(long oUT_OF) {
		OUT_OF = oUT_OF;
	}
	public long getTIME() {
		return TIME;
	}
	public void setTIME(long tIME) {
		TIME = tIME;
	}
	public long getQUES_ATTENDED() {
		return QUES_ATTENDED;
	}
	public void setQUES_ATTENDED(long qUES_ATTENDED) {
		QUES_ATTENDED = qUES_ATTENDED;
	}
	public String getDIFFICULTY() {
		return DIFFICULTY;
	}
	public void setDIFFICULTY(String dIFFICULTY) {
		DIFFICULTY = dIFFICULTY;
	}
	public String getSTART_TIME() {
		return START_TIME;
	}
	public void setSTART_TIME(String sTART_TIME) {
		START_TIME = sTART_TIME;
	}
	public long getTOTAL_QUESTIONS() {
		return TOTAL_QUESTIONS;
	}
	public void setTOTAL_QUESTIONS(long tOTAL_QUESTIONS) {
		TOTAL_QUESTIONS = tOTAL_QUESTIONS;
	}
	@Override
	public String toString() {
		return "UserTest [TEST_ID=" + TEST_ID + ", USER_SESSION_ID="
				+ USER_SESSION_ID + ", SCORE=" + SCORE + ", OUT_OF=" + OUT_OF
				+ ", TIME=" + TIME + ", QUES_ATTENDED=" + QUES_ATTENDED
				+ ", DIFFICULTY=" + DIFFICULTY + ", START_TIME=" + START_TIME
				+ ", TOTAL_QUESTIONS=" + TOTAL_QUESTIONS + "]";
	}
	

}
