package com.aad.ws.dto;

import java.util.List;

import com.aad.ws.domain.UserUQuestion;

public class UserUQuestionsCollection {

		List<UserUQuestion> question;

		public List<UserUQuestion> getUserUQuestion() {
			return question;
		}

		public void setUserUQuestion(List<UserUQuestion> question) {
			this.question = question;
		}
		
		
}
