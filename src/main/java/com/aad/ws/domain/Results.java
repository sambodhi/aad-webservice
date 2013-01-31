package com.aad.ws.domain;

import java.util.List;

public class Results {
	
	

		long totalquestions;
		double totalmark;
		long quesattended;
		long overallscoreobtained;
		String difficulty;
		int appid;
		String teststarttime;
		long testid;
		long totaltime;
		List<Analytic> analytics;

		public long gettotalquestions() {
			return totalquestions;
		}
		public void settotalquestions(long totalquestions) {
			this.totalquestions = totalquestions;
		}
		public double gettotalmark() {
			return totalmark;
		}
		public void settotalmark(double totalmark) {
			this.totalmark = totalmark;
		}
		public long getquesattended() {
			return quesattended;
		}
		public void setquesattended(long quesattended) {
			this.quesattended = quesattended;
		}
		public long getoverallscoreobtained() {
			return overallscoreobtained;
		}
		public void setoverallscoreobtained(long overallscoreobtained) {
			this.overallscoreobtained = overallscoreobtained;
		}
		public String getdifficulty() {
			return difficulty;
		}
		public void setdifficulty(String difficulty) {
			this.difficulty = difficulty;
		}
		public int getappid() {
			return appid;
		}
		public void setappid(int appid) {
			this.appid = appid;
		}
		public String getteststarttime() {
			return teststarttime;
		}
		public void setteststarttime(String teststarttime) {
			this.teststarttime = teststarttime;
		}
		public long gettestid() {
			return testid;
		}
		public void settestid(long testid) {
			this.testid = testid;
		}
		public long gettotaltime() {
			return totaltime;
		}
		public void settotaltime(long totaltime) {
			this.totaltime = totaltime;
		}
		public List<Analytic> getquestions() {
			return analytics;
		}
		public void setquestions(List<Analytic> analytics) {
			this.analytics = analytics;
		}
		
		
		public List<Analytic> getanalytics() {
			return analytics;
		}
		public void setanalytics(List<Analytic> analytics) {
			this.analytics = analytics;
		}
		/*
		HashMap<String,Integer> selectedAnswer=new HashMap<String,Integer>();
		// loops through the whole Hashmap and retirves the value for each of the keys
		for (String key : selectedAnswer.keySet()) {
			int value=selectedAnswer.get(key);
		}
		*/
		@Override
		public String toString() {
			return "Results [totalquestions=" + totalquestions + ", totalmark="
					+ totalmark + ", quesattended=" + quesattended
					+ ", overallscoreobtained=" + overallscoreobtained
					+ ", difficulty=" + difficulty + ", appid=" + appid
					+ ", teststarttime=" + teststarttime + ", testid=" + testid
					+ ", totaltime=" + totaltime + ", questions=" + analytics
					+ "]";
		}
		
		
	}
