package com.aad.ws.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.log4j.Logger;

public class FileUtil {
	
	private static final Logger logger = Logger.getLogger(FileUtil.class);

	// save uploaded file to new location
	public String writeToFile(InputStream uploadedInputStream,
			String storeAppPath, String fileName, String category) throws IOException {
		
		String uploadedFileLocation = "";
		createChildDir(storeAppPath, category);
		
		uploadedFileLocation = storeAppPath + category + "/" + fileName;
		
		OutputStream out = new FileOutputStream(uploadedFileLocation);
		int read = 0;
		byte[] bytes = new byte[1024];

		out = new FileOutputStream(new File(uploadedFileLocation ));
		while ((read = uploadedInputStream.read(bytes)) != -1) {
			out.write(bytes, 0, read);
		}
		out.flush();
		out.close();

		return uploadedFileLocation;
	}
	
	
	public void createChildDir(final String parent, final String dirName) throws IOException {
		
		logger.debug("parent folder: " + parent);
		logger.debug("directory to to be created in parent dirName: " + dirName);
	    final File parentDir = new File(parent);
	    final File dir = new File(parentDir, dirName);
	    if (!dir.exists() && !dir.mkdirs()) {
	        throw new IOException("Unable to create " + dir.getAbsolutePath());
	    }
	}
	
}
