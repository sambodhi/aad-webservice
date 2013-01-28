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

	public String storeFile(String fileName,
			InputStream uploadedInputStream, Application application) {
		
		//save to database
		appDao.createApplication(application);
		
		Category category = categoryDao.getCategory(application.getAppCategId());
		//save to file system
		String outputLoc = uploadFile(fileName, uploadedInputStream, category.getCategType());
		
		logger.debug("File copied to " + outputLoc);
		return outputLoc;
	}
	
	public String uploadFile(String fileName,
			InputStream uploadedInputStream, String category) {
		
		logger.debug("File being upload: "+ fileName + " category: " + category);
		if (StringUtils.isEmpty(category)){
			category = "Default";
		}
		// save it
		try {
			//util.createUserDir(uploadedFileLocation, category);
			return util.writeToFile(uploadedInputStream, storeAppPath, fileName, category);
		} catch (IOException e) {
			logger.error("Error occurred while uploading file",e);
		}
		return null;
	}

	public AppDetails getAppDetails(int id) {
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
		appDetails.setCategoryName(category.getCategType());
		appDetails.setDescription(application.getDescription());
		appDetails.setName(application.getAppName());
		appDetails.setUrl(application.getUrl());
		logger.debug("AppDetails : " + appDetails);
		return appDetails;
	}

	public AppCollection getAppsForCategory(int id) {
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
