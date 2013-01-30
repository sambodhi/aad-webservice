package com.aad.ws.dto;

import com.aad.ws.domain.Analytic;
import com.aad.ws.domain.Results;

public class QuestionTest {
	long TEST_ID;
	int QUESTION_ID;
	

	
	@Override
	public String toString() {
		return "QuestionTest [TEST_ID=" + TEST_ID + ", QUESION_ID="
				+ QUESTION_ID + "]";
	}
	public QuestionTest(Results res, Analytic analytics)
	{
		this.TEST_ID=res.gettestid();
		this.QUESTION_ID= analytics.getquestionid();
		
	}
	public void setQuestionTest(Results res, Analytic analytics)
	{
		this.TEST_ID=res.gettestid();
		this.QUESTION_ID= analytics.getquestionid();
		
	}
	public long getTEST_ID() {
		return TEST_ID;
	}
	public void setTEST_ID(long tEST_ID) {
		TEST_ID = tEST_ID;
	}
	public int getQUESTION_ID() {
		return QUESTION_ID;
	}
	public void setQUESION_ID(int qUESTION_ID) {
		QUESTION_ID = qUESTION_ID;
	}
	

}
