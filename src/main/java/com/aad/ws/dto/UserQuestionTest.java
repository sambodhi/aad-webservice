package com.aad.ws.dto;
import com.aad.ws.domain.Analytic;
import com.aad.ws.domain.Results;

public class UserQuestionTest {
	long TEST_ID;
	long USER_SESSION_ID;
	int QUESTION_ID;
	long TIME;
	int NUMBER_OF_CLICKS;
	String FINAL_ANSWER;
	double MARK;
	
	@Override
	public String toString() {
		return "UserQuestionTest [TEST_ID=" + TEST_ID + ", USER_SESSION_ID="
				+ USER_SESSION_ID + ", QUESTION_ID=" + QUESTION_ID + ", TIME="
				+ TIME + ", NUMBER_OF_CLICKS=" + NUMBER_OF_CLICKS
				+ ", FINAL_ANSWER=" + FINAL_ANSWER + ", MARK=" + MARK + "]";
	}

	public UserQuestionTest (Results res, Analytic analytics, long USER_SESSION_ID)
	{
		this.TEST_ID=res.gettestid();
		this.USER_SESSION_ID=USER_SESSION_ID;
		this.QUESTION_ID=analytics.getquestionid();
		this.TIME=analytics.gettime();
		this.NUMBER_OF_CLICKS=analytics.getclicks();
		this.FINAL_ANSWER=analytics.getfinalanswer();
		this.MARK=analytics.getmark();
	}
	
	public void setUserQuesionTest(Results res, Analytic analytics, long USER_SESSION_ID)
	{
		this.TEST_ID=res.gettestid();
		this.USER_SESSION_ID=USER_SESSION_ID;
		this.QUESTION_ID=analytics.getquestionid();
		this.TIME=analytics.gettime();
		this.NUMBER_OF_CLICKS=analytics.getclicks();
		this.FINAL_ANSWER=analytics.getfinalanswer();
		this.MARK=analytics.getmark();
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

	public int getQUESTION_ID() {
		return QUESTION_ID;
	}

	public void setQUESTION_ID(int qUESTION_ID) {
		QUESTION_ID = qUESTION_ID;
	}

	public long getTIME() {
		return TIME;
	}

	public void setTIME(long tIME) {
		TIME = tIME;
	}

	public int getNUMBER_OF_CLICKS() {
		return NUMBER_OF_CLICKS;
	}

	public void setNUMBER_OF_CLICKS(int nUMBER_OF_CLICKS) {
		NUMBER_OF_CLICKS = nUMBER_OF_CLICKS;
	}

	public String getFINAL_ANSWER() {
		return FINAL_ANSWER;
	}

	public void setFINAL_ANSWER(String fINAL_ANSWER) {
		FINAL_ANSWER = fINAL_ANSWER;
	}

	public double getMARK() {
		return MARK;
	}

	public void setMARK(double mARK) {
		MARK = mARK;
	}
	
}
