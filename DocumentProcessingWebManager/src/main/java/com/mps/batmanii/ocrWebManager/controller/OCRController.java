package com.mps.batmanii.ocrWebManager.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.xml.sax.SAXException;

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
	public String displayOCRPage(Model model, HttpSession session,
			HttpServletRequest request, HttpServletResponse response) {

		logger.info("Se acceseaza pagina ocr.jsp.Executabilele existente sunt:");
		for (Exec exec : execContainer.getExecs()) {
			logger.info(exec.toString());
		}
		logger.info("Executabilele deja selectate sunt:");
		for (Exec exec : selectedExecs.getSelectedExecs()) {
			logger.info(exec.toString());
		}
		logger.info("Fisierele xml de input deja selectate sunt:");
		for (XmlFile xmlFile : selectedXmlFiles.getXmlFiles()) {
			logger.info(xmlFile.getExecName());
		}
		String execName = (String) session.getAttribute("execName");
		String execType = (String) session.getAttribute("execType");
		logger.info("De pe sesiune: " + execName + " " + execType);

		/**
		 * Se adauga pe model listele corespunzatoare
		 * */
		model.addAttribute("execs", execContainer.getExecs());
		model.addAttribute("selectedExecs", selectedExecs.getSelectedExecs());
		List<String> selExecs = new ArrayList<String>();
		for (Exec exec : selectedExecs.getSelectedExecs()) {
			selExecs.add(exec.getExecType());
		}
		request.setAttribute("selExecs", selExecs);
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
	public String insertParameters(Model model, String execName,
			String execType, HttpSession session) {
		logger.info("Se acceseaza pagina pentru introducerea parametrilor pentru "
				+ execName + " de tipul " + execType);

		XsdToXml xsdToXml = new XsdToXml();

		Exec exec = new Exec();
		exec.setExecName(execName);
		exec.setFullExecName(execName.concat(".exe"));
		exec.setExecType(execType);
		for (Exec exec2 : execContainer.getExecs()) {
			if (exec2.getExecName().equals(execName))
				exec.setAllExecTypes(exec2.getAllExecTypes());
		}
		List<Exec> alreadySelectedExecs = selectedExecs.getSelectedExecs();
		boolean isSel = false;
		for (Exec ex : alreadySelectedExecs) {
			if (ex.getExecName().equals(execName))
				isSel = true;
		}

		xsdToXml.setXsdContainer(xsdContainer);

		XmlFile xmlFile = null;

		List<XmlElement> xmlElements;

		if (isSel == true) {
			logger.info("Executabilul " + execName + " a fost deja selectat");
			xmlFile = xsdToXml.getXmlFileFromList(execName.concat(".exe"),
					selectedXmlFiles.getXmlFiles());
			xmlElements = xmlFile.getXmlElements();
		} else {
			logger.info("Executabilul " + execName
					+ " este selectat pentru prima data");
			XsdFile xsdFile = xsdToXml.getXsdFileByExecName(execName
					.concat(".exe"));
			// Se creaza un obiect de tipul XmlFile din obiectul XsdFile
			xmlFile = xsdToXml.getXmlFileFromXsdFile(xsdFile);
			xmlFile.setExecName(exec.getExecName());
			xmlFile.setAllExecTypes(exec.getAllExecTypes());
			xmlElements = xsdToXml.getXmlElements(xmlFile);

		}
		logger.info("XmlElements");
		for (XmlElement element : xmlElements) {
			logger.info(element.getName() + " " + element.getValue() + " "
					+ element.getAttribute() + " " + element.getLevel());
		}
		// din lista de xmlelements se construieste o lista de xmlelementsform
		List<XmlElementForm> createXmlElementForms = xsdToXml
				.createXmlElementForms(xmlElements);
		XmlElementFormList xmlElementFormList = new XmlElementFormList(
				createXmlElementForms);
		logger.info("XmlElementsForm");
		for (XmlElementForm form : xmlElementFormList.getXmlElements()) {
			logger.info(form.getName() + " " + form.getValue() + " "
					+ form.getLevel() + " " + form.getToDisplay());
		}

		// Se pune pe model un obiect de tipul xmlElementFormList, construit
		// din lista obtinuta mai sus si numele executabilului
		session.setAttribute("execName", execName);
		session.setAttribute("execType", execType);
		model.addAttribute("execName", execName);
		model.addAttribute("list", xmlElementFormList);

		return "parameter";
	}

	@RequestMapping("/doSave")
	public String save(XmlElementFormList formList, Model model,
			HttpSession session) {
		logger.info("Salvare");
		// primesc obiectele
		String execName = (String) session.getAttribute("execName");
		String execType = (String) session.getAttribute("execType");
		logger.info("De pe sesiune:" + execName + " " + execType);
		session.setAttribute("execName", null);
		session.setAttribute("execType", null);

		Exec exec = new Exec();
		exec.setExecName(execName);
		exec.setFullExecName(execName.concat(".exe"));
		exec.setExecType(execType);
		for (Exec exec2 : execContainer.getExecs()) {
			if (exec2.getExecName().equals(execName))
				exec.setAllExecTypes(exec2.getAllExecTypes());
		}

		List<Exec> alreadySelectedExecs = selectedExecs.getSelectedExecs();
		boolean isSel = false;
		for (Exec ex : alreadySelectedExecs) {
			if (ex.getExecName().equals(execName))
				isSel = true;
		}

		XsdToXml xsdToXml = new XsdToXml();
		xsdToXml.setXsdContainer(xsdContainer);

		XmlFile xmlFile = null;

		if (isSel == true) {
			xmlFile = xsdToXml.getXmlFileFromList(execName.concat(".exe"),
					selectedXmlFiles.getXmlFiles());
		} else {
			alreadySelectedExecs.add(exec);
			selectedExecs.setSelectedExecs(alreadySelectedExecs);
			XsdFile xsdFile = xsdToXml.getXsdFileByExecName(execName
					.concat(".exe"));
			// Se creaza un obiect de tipul XmlFile din obiectul XsdFile
			xmlFile = xsdToXml.getXmlFileFromXsdFile(xsdFile);
			xmlFile.setExecType(exec.getExecType());
			xmlFile.setAllExecTypes(exec.getAllExecTypes());
		}

		List<XmlElement> xmlElements = xsdToXml.createXmlElements(formList
				.getXmlElements());
		xmlFile.setXmlElements(xmlElements);
		if (isSel == true) {
			int pozXml = 0;
			List<XmlFile> existingXmlFiles = selectedXmlFiles.getXmlFiles();
			for (int i = 0; i < existingXmlFiles.size(); i++) {
				if (existingXmlFiles.get(i).getExecName()
						.equals(execName.concat(".exe"))) {
					pozXml = i;
				}
			}
			existingXmlFiles.set(pozXml, xmlFile);
			selectedXmlFiles.setXmlFiles(existingXmlFiles);
			logger.info("S-au salvat urmatoarele  date:");
			XmlFile xmlFileSaved = selectedXmlFiles.getXmlFiles().get(pozXml);
			logger.info(xmlFileSaved.getExecName());
			logger.info(xmlFileSaved.getExecType());
			StringBuilder builder = new StringBuilder();
			for (String s : xmlFileSaved.getAllExecTypes()) {
				builder.append(s + "-");
			}
			logger.info(builder.toString());
			logger.info(xmlFileSaved.getRootElement().getName());
			List<XmlElement> xmlElementsSaved = xmlFileSaved.getXmlElements();
			for (XmlElement element : xmlElementsSaved) {
				logger.info(element.getName() + ":" + element.getValue() + " "
						+ element.getLevel() + " " + element.getAttribute()
						+ " min " + element.getMinOccurs() + " max "
						+ element.getMaxOccurs());
			}
		} else {
			List<XmlFile> existingXmlFiles = selectedXmlFiles.getXmlFiles();
			existingXmlFiles.add(xmlFile);
			selectedXmlFiles.setXmlFiles(existingXmlFiles);
			logger.info("S-au salvat urmatoarele  date:");
			XmlFile xmlFileSaved = selectedXmlFiles.getXmlFiles().get(
					selectedXmlFiles.getXmlFiles().size() - 1);
			logger.info(xmlFileSaved.getExecName());
			logger.info(xmlFileSaved.getExecType());
			StringBuilder builder = new StringBuilder();
			for (String s : xmlFileSaved.getAllExecTypes()) {
				builder.append(s + "-");
			}
			logger.info(builder.toString());
			logger.info(xmlFileSaved.getRootElement().getName());
			List<XmlElement> xmlElementsSaved = xmlFileSaved.getXmlElements();
			for (XmlElement element : xmlElementsSaved) {
				logger.info(element.getName() + ":" + element.getValue() + " "
						+ element.getLevel() + " " + element.getAttribute()
						+ " min " + element.getMinOccurs() + " max "
						+ element.getMaxOccurs());
			}
		}
		sort();
		return "redirect:/ocr";
	}

	@RequestMapping("/cancel")
	public String cancel(String execName, Model model, HttpSession session) {
		logger.info("Cancel " + execName);
		String execNameSession = (String) session.getAttribute("execName");
		String execTypeSession = (String) session.getAttribute("execType");
		logger.info("De pe sesiune:" + execNameSession + " " + execTypeSession);
		session.setAttribute("execName", null);
		session.setAttribute("execType", null);
		return "redirect:/ocr";
	}

	@RequestMapping("/delete")
	public String delete(String execName, String execType, Model model,
			HttpSession session) {
		logger.info("Delete " + execName);
		String execNameSession = (String) session.getAttribute("execName");
		String execTypeSession = (String) session.getAttribute("execType");
		logger.info("De pe sesiune:" + execNameSession + " " + execTypeSession);
		session.setAttribute("execName", null);
		session.setAttribute("execType", null);

		List<Exec> alreadySelectedExecs = selectedExecs.getSelectedExecs();
		int pozExec = 0;
		for (int i = 0; i < alreadySelectedExecs.size(); i++) {
			if (alreadySelectedExecs.get(i).getExecName().equals(execName)) {
				pozExec = i;
			}
		}
		alreadySelectedExecs.remove(pozExec);
		selectedExecs.setSelectedExecs(alreadySelectedExecs);

		List<XmlFile> existingXmlFiles = selectedXmlFiles.getXmlFiles();
		int pozXml = 0;
		for (int i = 0; i < existingXmlFiles.size(); i++) {
			if (existingXmlFiles.get(i).getExecName()
					.equals(execName.concat(".exe"))) {
				pozXml = i;
			}
		}
		existingXmlFiles.remove(pozXml);
		selectedXmlFiles.setXmlFiles(existingXmlFiles);

		return "redirect:/ocr";
	}
	
	@RequestMapping("/reinitialize")
	public String reinitialize(Model model, HttpSession session) throws SAXException, IOException {
		


		execContainer.restart();
		xsdContainer.restart();
			
		
		
		return "redirect:/ocr";
	}	

	public void sort() {
		Map<Integer, List<Exec>> mapExecs = new HashMap<Integer, List<Exec>>();
		Map<Integer, List<XmlFile>> mapXmlFiles = new HashMap<Integer, List<XmlFile>>();
		for (int i = 1; i <= 7; i++) {
			mapExecs.put(i, null);
		}
		List<Exec> execs = selectedExecs.getSelectedExecs();
		List<XmlFile> xmlFiles = selectedXmlFiles.getXmlFiles();
		if (execs.size() > 0) {
			for (int i = 0; i < execs.size(); i++) {
				int which = 0;
				if (execs.get(i).getExecType().equals("preprocessing")) {
					which = 1;
				}
				if (execs.get(i).getExecType().equals("binarization")) {
					which = 2;
				}
				if (execs.get(i).getExecType().equals("layout")) {
					which = 3;
				}
				if (execs.get(i).getExecType().equals("paging")) {
					which = 4;
				}
				if (execs.get(i).getExecType().equals("ocr")) {
					which = 5;
				}
				if (execs.get(i).getExecType().equals("hierarchy")) {
					which = 6;
				}
				if (execs.get(i).getExecType().equals("pdf-exporter")) {
					which = 7;
				}
				List<Exec> list = mapExecs.get(which);
				if (list == null) {
					list = new ArrayList<Exec>();
				}
				list.add(execs.get(i));
				mapExecs.put(which, list);
				List<XmlFile> list1 = mapXmlFiles.get(which);
				if (list1 == null) {
					list1 = new ArrayList<XmlFile>();
				}
				list1.add(xmlFiles.get(i));
				mapXmlFiles.put(which, list1);
			}
		}
		List<Exec> newSelectedExecs = new ArrayList<Exec>();
		Set<Entry<Integer, List<Exec>>> entrySet = mapExecs.entrySet();
		for (Entry<Integer, List<Exec>> e : entrySet) {
			List<Exec> value = e.getValue();
			if (value != null && value.size() > 0) {
				for (int index = 0; index < value.size(); index++) {
					newSelectedExecs.add(value.get(index));
				}
			}
		}
		selectedExecs.setSelectedExecs(newSelectedExecs);
		List<XmlFile> newSelectedXmlFiles = new ArrayList<XmlFile>();
		Set<Entry<Integer, List<XmlFile>>> entrySet2 = mapXmlFiles.entrySet();
		for (Entry<Integer, List<XmlFile>> e : entrySet2) {
			List<XmlFile> value = e.getValue();
			if (value != null && value.size() > 0) {
				for (int index = 0; index < value.size(); index++) {
					newSelectedXmlFiles.add(value.get(index));
				}
			}
		}
		selectedXmlFiles.setXmlFiles(newSelectedXmlFiles);
	}
}
