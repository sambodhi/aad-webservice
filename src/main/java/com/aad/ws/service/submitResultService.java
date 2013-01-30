package com.aad.ws.service;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.aad.ws.dao.ApplicationDAO;
import com.aad.ws.dao.ResultDAO;
import com.aad.ws.domain.Results;
import com.aad.ws.resource.ResultResource;


public class submitResultService {
	
	@Autowired
	private ResultDAO resultDao;
	
	private static final Logger logger = Logger.getLogger(submitResultService.class);
	public Results subResults (Results res)
	{
		res=resultDao.submitResult(res);
		return res;
	}
}
