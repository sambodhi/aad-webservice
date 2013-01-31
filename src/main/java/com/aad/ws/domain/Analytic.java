package com.aad.ws.domain;


public class Analytic {
	double mark;
	String finalanswer;
	long time;
	String questiontype;
	String correctanswer;
	int questionid;
	int clicks;
	double totalmark;

	public double getmark() {
		return mark ;
	}
	public void setmark (double Mark) {
		this.mark =  Mark;
	}
	public String getfinalanswer() {
		return finalanswer ;
	}
	public void setfinalanswer (String FinalAnswer) {
		this.finalanswer = FinalAnswer ;
	}
	public long gettime() {
		return time;
	}
	public void settime(long Time) {
		this.time = Time;
	}
	public String getcorrectanswer() {
		return correctanswer ;
	}
	public void setcorrectanswer (String CorrectAnswer) {
		this.correctanswer = CorrectAnswer ;
	}
	public String getquestiontype() {
		return questiontype ;
	}
	public void setquestiontype (String QuestionType) {
		this.questiontype = QuestionType ;
	}
	public int getquestionid() {
		return questionid ;
	}
	public void setquestionid (int QuestionId) {
		this.questionid = QuestionId ;
	}
	public int getclicks() {
		return clicks ;
	}
	public void setclicks (int Clicks) {
		this.clicks = Clicks ;
	}
	public double gettotalmark() {
		return totalmark;
	}
	public void settotalmark(double totalmark) {
		this.totalmark = totalmark;
	}
	
}
