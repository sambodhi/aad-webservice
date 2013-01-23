package com.aad.ws.service;

import java.io.IOException;
import java.io.InputStream;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.aad.ws.dao.ApplicationDAO;
import com.aad.ws.domain.Application;
import com.aad.ws.utils.FileUtil;

public class AppService {
	
	@Autowired
	private FileUtil util;
	
	@Autowired
	private ApplicationDAO appDao;
	
	private static final Logger logger = Logger.getLogger(AppService.class);
	
	public void storeFile(String fileName,
			InputStream uploadedInputStream, Application application) {
		
		//TODO: save to database
		appDao.createApplication(application);
		
		//save to file system
		String outputLoc = uploadFile(fileName, uploadedInputStream);
		
		logger.debug("File copied to " + outputLoc);
	}
	
	public String uploadFile(String fileName,
			InputStream uploadedInputStream) {
		
		logger.debug("File being upload: "+ fileName);
		String uploadedFileLocation = "/Users/macpro/Documents/UCL/AADGroupWS/"
			+ fileName;
		logger.debug("at location: "+ uploadedFileLocation);
		// save it
		try {
			//createUserDir()
			util.writeToFile(uploadedInputStream, uploadedFileLocation);
		} catch (IOException e) {
			logger.error("Error occurred while uploading file",e);
		}
	
		return uploadedFileLocation;
	}
	
	
	
	

}
