package com.mps.batmanii.ocrWebManager.beans;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * Clasa pentru retinerea unui fisier uploadat
 * 
 * @author Flavia
 * 
 */
public class UploadItem {
	private String name;
	private CommonsMultipartFile fileData;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CommonsMultipartFile getFileData() {
		return fileData;
	}

	public void setFileData(CommonsMultipartFile fileData) {
		this.fileData = fileData;
	}
}