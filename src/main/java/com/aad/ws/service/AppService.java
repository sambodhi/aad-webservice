package com.aad.ws.service;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.aad.ws.dao.ApplicationDAO;
import com.aad.ws.dao.CategoryDAO;
import com.aad.ws.domain.Application;
import com.aad.ws.domain.Category;
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

	public void storeFile(String fileName,
			InputStream uploadedInputStream, Application application) {
		
		//TODO: save to database
		appDao.createApplication(application);
		
		Category category = categoryDao.getCategory(application.getAppCategId());
		//save to file system
		String outputLoc = uploadFile(fileName, uploadedInputStream, category.getCategType());
		
		logger.debug("File copied to " + outputLoc);
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
	
}
