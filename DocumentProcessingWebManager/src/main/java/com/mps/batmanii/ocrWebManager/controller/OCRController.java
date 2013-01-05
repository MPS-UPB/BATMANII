package com.mps.batmanii.ocrWebManager.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mps.batmanii.ocrWebManager.beans.Exec;
import com.mps.batmanii.ocrWebManager.beans.ExecContainer;
import com.mps.batmanii.ocrWebManager.beans.PropertyHolder;
import com.mps.batmanii.ocrWebManager.beans.SelectedExecs;
import com.mps.batmanii.ocrWebManager.beans.XmlElement;
import com.mps.batmanii.ocrWebManager.beans.XmlElementForm;
import com.mps.batmanii.ocrWebManager.beans.XmlElementFormList;
import com.mps.batmanii.ocrWebManager.beans.XmlFile;
import com.mps.batmanii.ocrWebManager.beans.SelectedXmlFiles;
import com.mps.batmanii.ocrWebManager.beans.XsdContainer;
import com.mps.batmanii.ocrWebManager.beans.XsdFile;
import com.mps.batmanii.ocrWebManager.business.Manager;
import com.mps.batmanii.ocrWebManager.business.XsdToXml;

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

	@Autowired
	PropertyHolder propertyHolder;

	@Autowired
	ExecContainer execContainer;

	@Autowired
	SelectedExecs selectedExecs;

	@Autowired
	SelectedXmlFiles selectedXmlFiles;

	private final static Logger logger = LoggerFactory
			.getLogger(OCRController.class);

	/**
	 * Metoda care afiseaza pagina pentru selectia executabilelor in functie de
	 * tipul acestora. Se pune pe model pentru a fi afisate o lista cu
	 * executabilele care e luata din memorie(lista execs din ExecContainer),
	 * precum si o lista cu executabilele deja selectate(lista selectedExecs din
	 * SelectedExecs)
	 * 
	 * @param model
	 * @return - String - numele view-ului ce va fi afisat
	 */
	@RequestMapping("")
	public String displayOCRPage(Model model) {

		logger.info("Se acceseaza pagina ocr.jsp.Executabilele existente sunt:");
		for (Exec exec : execContainer.getExecs()) {
			logger.info(exec.toString());
		}
		logger.info("Executabilele deja selectate sunt:");
		for (Exec exec : selectedExecs.getSelectedExecs()) {
			logger.info(exec.toString());
		}
		/**
		 * Se adauga pe model listele corespunzatoare
		 * */
		model.addAttribute("execs", execContainer.getExecs());
		model.addAttribute("selectedExecs", selectedExecs.getSelectedExecs());

		return "ocr";
	}

	/**
	 * Intoarce pagina pentru a completa parametrii pentru un executabil
	 * selectat in pagina anterioara
	 * 
	 * @param model
	 * @param execName
	 *            - numele executabilui(fara extensie, extensia trebuie alipita)
	 * @return
	 */
	@RequestMapping("/parameter")
	public String insertParameters(Model model, String execName, String execType) {
		logger.info("Se adauga executabilul " + execName + " de tipul "
				+ execType);
		// Se adauga executabilul in lista de executabile deja selectate
		Exec exec = new Exec();
		exec.setExecName(execName);
		exec.setFullExecName(execName.concat(".exe"));
		exec.setExecType(execType);
		List<Exec> alreadySelectedExecs = selectedExecs.getSelectedExecs();
		alreadySelectedExecs.add(exec);
		selectedExecs.setSelectedExecs(alreadySelectedExecs);

		// Se cauta obiectul XsdFile din container dupa execName
		XsdToXml xsdToXml = new XsdToXml();
		XsdFile xsdFile = xsdToXml.getXsdFileByExecName(execName);
		// Se creaza un obiect de tipul XmlFile din obiectul XsdFile
		XmlFile xmlFile = xsdToXml.getXmlFileFromXsdFile(xsdFile);
		// construiesc eu un obiect XmlFile
		// Se adauga acest obiect in lista de selectedXmlFiles
		List<XmlFile> existingXmlFiles = selectedXmlFiles.getXmlFiles();

		Manager manager = new Manager();
		xmlFile = manager.createMockXmlFile();
		existingXmlFiles.add(xmlFile);
		selectedXmlFiles.setXmlFiles(existingXmlFiles);
		// Din obiectul XmlFile generez lista de XmlElement
		List<XmlElement> xmlElements = xsdToXml.getXmlElements(xmlFile);
		// creez eu mock xmlElements
		xmlElements = manager.createMockXmlElements();
		// din lista de xmlelements se construieste o lista de xmlelementsform
		List<XmlElementForm> createXmlElementForms = xsdToXml
				.createXmlElementForms(xmlElements);
		XmlElementFormList xmlElementFormList = new XmlElementFormList(
				createXmlElementForms);

		for (XmlElementForm form : xmlElementFormList.getXmlElements()) {
			logger.info(form.getName() + " " + form.getToDisplay());
		}

		// Se pune pe model un obiect de tipul xmlElementFormList, construit
		// din lista obtinuta mai sus si numele executabilului
		model.addAttribute("execName", execName);
		model.addAttribute("list", xmlElementFormList);

		return "parameter";
	}

	@RequestMapping("/doSave")
	public String save(XmlElementFormList formList, Model model) {
		// primesc obiectele
		XsdToXml xsdToXml = new XsdToXml();
		List<XmlElement> xmlElements = xsdToXml.createXmlElements(formList
				.getXmlElements());
		List<XmlFile> xmlFiles = selectedXmlFiles.getXmlFiles();
		XmlFile xmlFile = xmlFiles.get(xmlFiles.size() - 1);
		xmlFile.setXmlElements(xmlElements);
		xmlFiles.set(xmlFiles.size() - 1, xmlFile);
		selectedXmlFiles.setXmlFiles(xmlFiles);
		logger.info("S-au salvat urmatoarele  date:");
		XmlFile xmlFileSaved = selectedXmlFiles.getXmlFiles().get(
				selectedXmlFiles.getXmlFiles().size() - 1);
		logger.info(xmlFileSaved.getExecName());
		logger.info(xmlFileSaved.getExecType());
		logger.info(xmlFileSaved.getRootElement().getName());
		List<XmlElement> xmlElementsSaved = xmlFileSaved.getXmlElements();
		for (XmlElement element : xmlElementsSaved) {
			logger.info(element.getName() + ":" + element.getValue() + " "
					+ element.getLevel() + " " + element.getAttribute());
		}
		return "redirect:/ocr";
	}
}
