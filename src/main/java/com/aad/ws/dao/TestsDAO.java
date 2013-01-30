package com.aad.ws.dao;

import java.util.List;

import com.aad.ws.domain.Test;
import com.aad.ws.domain.UserTest;

public interface TestsDAO {
	public List<Test> getTestsForId(int id);
	public List<UserTest> getUserTestsForId(int id);
}
