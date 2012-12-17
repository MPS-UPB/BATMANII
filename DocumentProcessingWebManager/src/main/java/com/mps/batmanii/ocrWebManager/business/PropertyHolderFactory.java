package com.mps.batmanii.ocrWebManager.business;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.mps.batmanii.ocrWebManager.beans.PropertyHolder;
import com.mps.batmanii.ocrWebManager.controller.IndexController;

/**
 * Factory pattern pentru a prelua bean-ul ce contine folderele folosite de
 * aplicatie, preluate din fisierul "config.properties". De asemenea, daca
 * folderul pentru upload-ul imaginilor de catre user nu exista, acesta va fi
 * creat automat. Se foloseste si functia de logging a aplicatiei
 * 
 * @author CosminV
 * 
 */
@Service
public class PropertyHolderFactory {

	Logger logger = LoggerFactory.getLogger(IndexController.class);

	/**
	 * Factory method
	 * 
	 * @return propertyHolder PropertyHolder
	 */
	public static PropertyHolder getPropertyHolder() {
		ClassPathXmlApplicationContext appContext = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		BeanFactory factory = (BeanFactory) appContext;
		PropertyHolder propertyHolder = (PropertyHolder) factory
				.getBean("propertyHolder");
		new PropertyHolderFactory().createFolder(propertyHolder
				.getUploadedImagesFolder());
		return propertyHolder;
	}

	/**
	 * Creare folder
	 * 
	 * @param folderName
	 *            String - numele folderului
	 */
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
