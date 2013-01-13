package com.mps.batmanii.ocrWebManager.business;

import java.io.File;
import java.io.IOException;

import javax.annotation.PostConstruct;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


import com.mps.batmanii.ocrWebManager.beans.PropertyHolder;
import com.mps.batmanii.ocrWebManager.controller.IndexController;


/**
 * Clasa folosita pentru crearea de foldere
 * 
 * @author andrei
 * 
 */

public class FoldersCreator {

	/**
	 *Creaaza toate cele 6 foldere daca nu exista deja
	 *
	 */
	@Autowired
	private PropertyHolder propertyHolder;
	
	Logger logger = LoggerFactory.getLogger(IndexController.class);
	
	public FoldersCreator() {
		logger.info("Creare singleton FoldersCreator");
	}
	
	@PostConstruct
	public void postConstruct() throws IOException {
		createFolder(propertyHolder.getInputSchemasFolder());
		createFolder(propertyHolder.getOutputSchemasFolder());
		createFolder(propertyHolder.getExecsFolder());
		createFolder(propertyHolder.getUploadedImagesFolder());
		createFolder(propertyHolder.getResults());
		createFolder(propertyHolder.getXmlFolder());
	
	}
	
	public void createFolder(String folderName) {
		File folder = new File(folderName);
		if (!folder.exists()) {
			folder.mkdir();
			logger.info("Folder " + folderName + " creat.");
		} else {
			logger.info("Folderul " + folderName + " deja exista.");
		}
	}
	
}