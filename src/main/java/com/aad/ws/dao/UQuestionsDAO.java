package com.aad.ws.dao;

import java.util.List;

import com.aad.ws.domain.UQuestion;
import com.aad.ws.domain.UserUQuestion;

public interface UQuestionsDAO {

	List<UQuestion> getUQuestionsForId(long id);
	List<UserUQuestion> getUserUQuestionsForId(long id);

}
