package com.aad.ws.dao;

import java.util.List;

import com.aad.ws.domain.Application;

public interface ApplicationDAO {
	Application createApplication(Application application);
	List<Application> getApplication(Application value);
	Application updateApplication(Application application);
	List<Application> getApplicationForCategory(long id);
}
