package com.mps.batmanii.ocrWebManager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Clasa controller pentru pagina "ocr.jsp"
 * 
 * @author CosminV
 * 
 */

@Controller
@RequestMapping(value = "/ocr")
public class OCRController {

	@RequestMapping("")
	public String displayOCRPage(Model model) {
		return "ocr";
	}
}
