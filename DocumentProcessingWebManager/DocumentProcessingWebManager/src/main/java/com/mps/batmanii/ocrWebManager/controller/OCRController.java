package com.mps.batmanii.ocrWebManager.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mps.batmanii.ocrWebManager.beans.XsdContainer;

/**
 * Clasa controller pentru pagina "ocr.jsp"
 * 
 * @author CosminV
 * 
 */

@Controller
@RequestMapping(value = "/ocr")
public class OCRController {

	@Autowired
	XsdContainer xsdContainer;

	private final static Logger logger = LoggerFactory
			.getLogger(OCRController.class);

	@RequestMapping("")
	public String displayOCRPage(Model model) {
		logger.info(xsdContainer.toString());
		List<String> list = new ArrayList<String>();
		list.add("preprocessing");
		list.add("binarization");
		model.addAttribute("list", list);
		return "ocr";
	}
}
