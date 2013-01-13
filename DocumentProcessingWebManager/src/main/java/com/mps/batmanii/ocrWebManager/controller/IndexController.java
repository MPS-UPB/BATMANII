package com.mps.batmanii.ocrWebManager.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mps.batmanii.ocrWebManager.beans.ExecContainer;
import com.mps.batmanii.ocrWebManager.beans.PropertyHolder;
import com.mps.batmanii.ocrWebManager.beans.XsdContainer;

import com.mps.batmanii.ocrWebManager.beans.UploadItem;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.ServletConfig;
import java.io.FileOutputStream;

import java.io.InputStream;
import java.io.OutputStream;
import javax.servlet.http.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * Clasa controller pentru pagina "index.jsp"
 * 
 * @author Flavia
 * 
 */

@Controller
@RequestMapping(value = "")
public class IndexController {

	@Autowired
	XsdContainer xsdContainer;

	@Autowired
	PropertyHolder propertyHolder;

	@Autowired
	ExecContainer execContainer;

	private final static Logger logger = LoggerFactory
			.getLogger(OCRController.class);

	private String uploadFolderPath;
	ServletConfig config;

	public String getUploadFolderPath() {
		return uploadFolderPath;
	}

	public void setUploadFolderPath(String uploadFolderPath) {
		this.uploadFolderPath = uploadFolderPath;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String displayFileUploadFormPage(Model model) {
		model.addAttribute(new UploadItem());
		return "index";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String create(UploadItem uploadItem, BindingResult result,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		if (result.hasErrors()) {
			for (ObjectError error : result.getAllErrors()) {
				logger.info("Error: " + error.getCode() + " - "
						+ error.getDefaultMessage());
			}
			return "index";
		}

		logger.info("-------------------------------------------");
		logger.info("Test upload: "
				+ uploadItem.getFileData().getOriginalFilename());
		logger.info("-------------------------------------------");
		try {
			MultipartFile file = uploadItem.getFileData();
			String fileName = null;
			InputStream inputStream = null;
			OutputStream outputStream = null;
			if (file.getSize() > 0) {
				inputStream = file.getInputStream();
				if (file.getSize() > 600000) {
					logger.info("File Size:::" + file.getSize());
					return "index";
				}
				logger.info("size::" + file.getSize());
				fileName = propertyHolder.getUploadedImagesFolder()
						+ file.getOriginalFilename();
				outputStream = new FileOutputStream(fileName);
				logger.info("fileName:" + file.getOriginalFilename());

				int readBytes = 0;
				byte[] buffer = new byte[10000];
				while ((readBytes = inputStream.read(buffer, 0, 10000)) != -1) {
					outputStream.write(buffer, 0, readBytes);
				}
				outputStream.close();
				inputStream.close();
			}

			session.setAttribute("uploadFile", file.getOriginalFilename());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "index";
	}

}
