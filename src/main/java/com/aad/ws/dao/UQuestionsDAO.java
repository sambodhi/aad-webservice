package com.aad.ws.dao;

import java.util.List;

import com.aad.ws.domain.UQuestion;
import com.aad.ws.domain.UserUQuestion;

public interface UQuestionsDAO {

	List<UQuestion> getUQuestionsForId(int id);
	List<UserUQuestion> getUserUQuestionsForId(int id);

}
