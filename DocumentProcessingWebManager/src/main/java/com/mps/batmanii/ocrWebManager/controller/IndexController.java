package com.mps.batmanii.ocrWebManager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.mps.batmanii.ocrWebManager.beans.PropertyHolder;
import com.mps.batmanii.ocrWebManager.business.PropertyHolderFactory;

/**
 * Clasa controller pentru pagina "index.jsp"
 * 
 * @author CosminV
 * 
 */

@Controller
@RequestMapping(value = "")
public class IndexController {

	@RequestMapping(value = "/")
	public String displayIndexPage(Model model) {
		PropertyHolder propertyHolder = PropertyHolderFactory
				.getPropertyHolder();
		model.addAttribute("propertyHolder", propertyHolder);
		return "index";
	}

}
