package com.mps.batmanii.ocrWebManager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Clasa controller pentru pagina "result.jsp"
 * 
 * @author CosminV
 * 
 */

@Controller
@RequestMapping(value = "/result")
public class ResultController {

	@RequestMapping("")
	public String displayOCRPage(Model model) {
		return "result";
	}
}
