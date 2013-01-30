package com.aad.ws.dto;

import com.aad.ws.domain.Analytic;

public class Question {
	int QUESTION_ID;
	String CORRECT_ANSWER;
	String TYPE;
	

	@Override
	public String toString() {
		return "Question [QUESTION_ID=" + QUESTION_ID + ", CORRECT_ANSWER="
				+ CORRECT_ANSWER + ", TYPE=" + TYPE + "]";
	}

	public Question(Analytic analytics){
		this.QUESTION_ID=analytics.getquestionid();
		this.CORRECT_ANSWER= analytics.getcorrectanswer();
		this.TYPE= analytics.getquestiontype();
	}
	
	public void setQuesion(Analytic analytics)
	{
		this.QUESTION_ID=analytics.getquestionid();
		this.CORRECT_ANSWER= analytics.getcorrectanswer();
		this.TYPE= analytics.getquestiontype();
	}

	public int getQUESTION_ID() {
		return QUESTION_ID;
	}

	public void setQUESTION_ID(int qUESTION_ID) {
		QUESTION_ID = qUESTION_ID;
	}

	public String getCORRECT_ANSWER() {
		return CORRECT_ANSWER;
	}

	public void setCORRECT_ANSWER(String cORRECT_ANSWER) {
		CORRECT_ANSWER = cORRECT_ANSWER;
	}

	public String getTYPE() {
		return TYPE;
	}

	public void setTYPE(String tYPE) {
		TYPE = tYPE;
	}
	
}
