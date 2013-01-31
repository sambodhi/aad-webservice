package com.aad.ws.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.aad.ws.dao.ApplicationDAO;
import com.aad.ws.dao.CategoryDAO;
import com.aad.ws.domain.Application;
import com.aad.ws.domain.Category;
import com.aad.ws.dto.AppCollection;
import com.aad.ws.dto.AppDetails;
import com.aad.ws.exception.InvalidAttribute;
import com.aad.ws.utils.FileUtil;

public class AppService {
	
	@Autowired
	private FileUtil util;
	
	@Autowired
	private ApplicationDAO appDao;
	
	@Autowired
	private CategoryDAO categoryDao;
	
	private String storeAppPath;
	
	private static final Logger logger = Logger.getLogger(AppService.class);
	
	public void setStoreAppPath(String storeAppPath) {
		this.storeAppPath = storeAppPath;
	}
	

	public Application storeFile(String fileName,
			InputStream uploadedInputStream, Application application) throws InvalidAttribute {
		
		Category category = categoryDao.getCategory(application.getAppCategId());
		//save to file system
		Application app = uploadFile(fileName, uploadedInputStream, category, application);
		
		//save to database
		logger.debug("Inserting new app's details to database: " + app); 
		appDao.createApplication(app);
		
		return app;
	}
	
	public Application uploadFile(String fileName,
			InputStream uploadedInputStream, Category category, Application application) throws InvalidAttribute {
		
		logger.debug("File being upload: "+ fileName + " category: " + category + " application: " + application.getAppName());
		
		String categType= null;
		if(category != null)
			categType= category.getCategType();
		
		if (StringUtils.isEmpty(categType)){
			categType = "Default";
		}
		// save it
		try {
			//util.createUserDir(uploadedFileLocation, category);
			return util.uploadFile(uploadedInputStream, storeAppPath, fileName, categType, application);
		} catch (IOException e) {
			logger.error("Error occurred while uploading file",e);
		}
		return null;
	}

	public AppDetails getAppDetails(long id) {
		logger.debug(">> getAppDetails >> id =" + id);
		List<Application> apps = appDao.getApplication(new Application(id));
		if(apps != null && apps.size() > 0){
			Application application = apps.get(0);
			return convertToAppDetails(application);
		}
		return null;
	}

	private AppDetails convertToAppDetails(Application application) {
		AppDetails appDetails = new AppDetails();
		logger.debug("Convert Application: " + application + " to AppDetails");
		Category category = categoryDao.getCategory(application.getAppCategId());
		logger.debug("Category :" + category);
		appDetails.setId(application.getAppId());
		appDetails.setCategoryName(category.getCategType());
		appDetails.setDescription(application.getDescription());
		appDetails.setName(application.getAppName());
		appDetails.setUrl(application.getUrl());
		appDetails.setIconUrl(application.getIconUrl());
		appDetails.setDeveloper(application.getDeveloprName());
		appDetails.setSize(application.getAppSize());
		logger.debug("AppDetails : " + appDetails);
		return appDetails;
	}

	public AppCollection getAppsForCategory(long id) {
		List<Application> apps = appDao.getApplicationForCategory(id);
		List<AppDetails> appsInfo = convertToAppDetailsList(apps);
		AppCollection appCol = new AppCollection();
		appCol.setApps(appsInfo);
		return appCol;
	}
	
	private List<AppDetails> convertToAppDetailsList(List<Application> applications) {
		logger.debug("Convert ApplicationList: " + applications + " to AppDetailsList");
		List<AppDetails> appDetailsList = new ArrayList<AppDetails>();
		if(applications != null){
			for(Application app: applications){
				appDetailsList.add(convertToAppDetails(app));
			}
		}
		return appDetailsList;
	}
}
