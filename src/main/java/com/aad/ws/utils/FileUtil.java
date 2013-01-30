package com.aad.ws.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.StringTokenizer;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.aad.ws.domain.Application;
import com.aad.ws.exception.InvalidAttribute;

public class FileUtil {
	
	private static final Logger logger = Logger.getLogger(FileUtil.class);

	private String possibleAppArchiveExts;
	private String possibleAppIconExts;
	private String downloadAppUrl;
	
	public void setDownloadAppUrl(String downloadAppUrl) {
		this.downloadAppUrl = downloadAppUrl;
	}
	
	public void setPossibleAppArchiveExts(String possibleAppArchiveExts) {
		this.possibleAppArchiveExts = possibleAppArchiveExts;
	}

	public void setPossibleAppIconExts(String possibleAppIconExts) {
		this.possibleAppIconExts = possibleAppIconExts;
	}

	// save uploaded file to new location
	public Application uploadFile(InputStream uploadedInputStream,
			String storeAppPath, String fileName, String category, Application app) throws IOException, InvalidAttribute {
		
		String uploadFilePath = "";
		//Create category folder
		String categFolderPath = createChildDir(storeAppPath, category);
		logger.debug("Category Folder created at: " + categFolderPath);
		String appName = app.getAppName();
		//Create Application name folder
		String appFolderPath = createChildDir(categFolderPath , appName );
		uploadFilePath = appFolderPath + "/" + fileName;
		
		//Copy the file
		uploadFilePath = writeToFile(uploadedInputStream, uploadFilePath);
		
		//Unzip
		unzipFile(uploadFilePath, appFolderPath);
		
		//ValidateFileContents
		String exeFile = getExeFile(appFolderPath);
		String iconFile = getIconFile(appFolderPath);
		logger.debug("exeFile :" + exeFile);
		logger.debug("iconFile :" + iconFile);
		
		//Validate if zip has all the required files
		if(StringUtils.isEmpty(exeFile))
			throw new InvalidAttribute("file exe", "app executable file missing");
		if(StringUtils.isEmpty(iconFile))
			throw new InvalidAttribute("file icon", "app icon file missing");
		
		app.setUrl(downloadAppUrl + category + "/" + appName + "/" +exeFile);
		app.setIconUrl(downloadAppUrl + category + "/" + appName + "/" + iconFile);
		
		return app;
	}
	
	/*
	 * Gets exe file present in the zip. 
	 */
	private String getExeFile(String appFolderPath) {
		String[] archiveFiles = listFile(appFolderPath, possibleAppArchiveExts);
		if(archiveFiles == null || archiveFiles.length == 0){
			return null;
		}
		logger.debug("archiveFiles : " + archiveFiles.length);
		return archiveFiles[0];
	}
	
	/*
	 * Gets icon/image file present in the zip
	 */
	private String getIconFile(String appFolderPath) {
		String[] iconFiles = listFile(appFolderPath, possibleAppIconExts);
		
		if(iconFiles == null ||  iconFiles.length == 0){
			return null;
		}
		logger.debug("iconFiles : " + iconFiles.length);
		return iconFiles[0];
	}
	
	public String writeToFile(InputStream uploadedInputStream,
			String uploadFilePath) throws IOException {
		
		OutputStream out = new FileOutputStream(uploadFilePath);
		int read = 0;
		byte[] bytes = new byte[1024];

		out = new FileOutputStream(new File(uploadFilePath ));
		while ((read = uploadedInputStream.read(bytes)) != -1) {
			out.write(bytes, 0, read);
		}
		out.flush();
		out.close();
		
		return uploadFilePath;
	}
	
	/*
	 * Create a dir inside a given dir
	 */
	private String createChildDir(final String parent, final String dirName) throws IOException {
		
		logger.debug("parent folder: " + parent);
		logger.debug("directory to to be created in parent dirName: " + dirName);
	    final File parentDir = new File(parent);
	    final File dir = new File(parentDir, dirName);
	    if (!dir.exists() && !dir.mkdirs()) {
	        throw new IOException("Unable to create " + dir.getAbsolutePath());
	    }
	    return dir.getAbsolutePath();
	}
	
	/*
	 * Unzips the file and stores its content in the given destination folder
	 */
	private void unzipFile(String filename, String dest) throws IOException{
		logger.debug("File to be unzipped: " + filename);
		try {
			ZipFile zipFile = new ZipFile(filename);
			Enumeration<?> enu = zipFile.entries();
			while (enu.hasMoreElements()) {
				ZipEntry zipEntry = (ZipEntry) enu.nextElement();

				String name = zipEntry.getName();
				long size = zipEntry.getSize();
				long compressedSize = zipEntry.getCompressedSize();
				System.out.printf("name: %-20s | size: %6d | compressed size: %6d\n", 
						name, size, compressedSize);

				File file = new File(dest + "/" +name);
				if (name.endsWith("/")) {
					file.mkdirs();
					continue;
				}

				File parent = file.getParentFile();
				if (parent != null) {
					parent.mkdirs();
				}

				InputStream is = zipFile.getInputStream(zipEntry);
				FileOutputStream fos = new FileOutputStream(file);
				byte[] bytes = new byte[1024];
				int length;
				while ((length = is.read(bytes)) >= 0) {
					fos.write(bytes, 0, length);
				}
				is.close();
				fos.close();
			}
			zipFile.close();
		} catch (IOException e) {
			logger.error("Error occurred while unzipping the file", e);
			throw e;
		}
	}
	
	/*
	 * Returns list of files in the given folder with given extentions
	 */
	private String[] listFile(String folder, String exts) {

		logger.debug("List file with ext : " + exts + " in folder " + folder);
        GenericExtFilter filter = new GenericExtFilter(exts);
        File dir = new File(folder);

        if(dir.isDirectory()==false){
        	logger.error("Directory does not exists : " + folder);
            return null;
        }
        // list out all the file name and filter by the extension
        String[] list = dir.list(filter);
        if (list.length == 0) {
        	logger.error("No files end with : " + exts + "!!");
            return null;
        }
        return list;
    }

    public class GenericExtFilter implements FilenameFilter {

        private String exts;

        public GenericExtFilter(String exts) {
            this.exts = exts;
        }

        public boolean accept(File dir, String name) {
        	logger.debug("Check if file name "+ name + " has extention: "+ exts);
        	StringTokenizer st = new StringTokenizer(exts);
        	while (st.hasMoreElements()) {
        		String ext = (String) st.nextElement();
        		if (name.endsWith(ext)){
        			return true;
        		}
        	}
        	return false;
        }
    }
}
