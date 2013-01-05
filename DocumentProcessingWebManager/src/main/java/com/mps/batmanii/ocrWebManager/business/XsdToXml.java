package com.mps.batmanii.ocrWebManager.business;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.mps.batmanii.ocrWebManager.beans.XmlElement;
import com.mps.batmanii.ocrWebManager.beans.XmlElementForm;
import com.mps.batmanii.ocrWebManager.beans.XmlFile;
import com.mps.batmanii.ocrWebManager.beans.XsdContainer;
import com.mps.batmanii.ocrWebManager.beans.XsdFile;

/**
 * 
 * @author comy & bersy am stabilit impreuna structura claselor
 * 
 */
public class XsdToXml {

	private final static Logger logger = LoggerFactory
			.getLogger(XsdToXml.class);

	@Autowired
	XsdContainer xsdContainer;

	// ToDo pt Bersy
	// Primeste ca parametru un obiect XsdFile si imi intoarce un obiect XmlFile
	public XmlFile getXmlFileFromXsdFile(XsdFile xsdFile) {
		return null;
	}

	/**
	 * Metoda care cauta in lista de obiecte XsdFile din XsdContainer dupa
	 * execName si sa imi intoarca obiectul de tip XsdFile
	 */
	// ToDo Bersy
	public XsdFile getXsdFileByExecName(String execName) {

		return null;
	}

	// ToDo Bersy
	public List<XmlElement> getXmlElements(XmlFile xmlFile) {
		return null;
	}

	/**
	 * Din lista de xml elements creaza lista de de xmlelementsform
	 * 
	 * @param xmlElements
	 * @return
	 */
	public List<XmlElementForm> createXmlElementForms(
			List<XmlElement> xmlElements) {
		List<XmlElementForm> xmlElementForms = new ArrayList<XmlElementForm>();
		for (int i = 0; i < xmlElements.size(); i++) {
			XmlElementForm xmlElementForm = createFormFromElement(xmlElements
					.get(i));
			if (xmlElements.get(i).getAttribute() == true) {
				xmlElementForm.setToDisplay(true);
			} else {
				if ((i + 1) < (xmlElements.size() - 1)) {
					if (xmlElements.get(i + 1).getLevel() > xmlElements.get(i)
							.getLevel()) {
						xmlElementForm.setToDisplay(false);
					} else {
						xmlElementForm.setToDisplay(true);
					}
				} else {
					xmlElementForm.setToDisplay(true);
				}
			}
			xmlElementForms.add(xmlElementForm);
		}

		return xmlElementForms;
	}

	public XmlElementForm createFormFromElement(XmlElement element) {
		XmlElementForm xmlElementForm = new XmlElementForm();
		xmlElementForm.setName(element.getName());
		xmlElementForm.setLevel(element.getLevel());
		xmlElementForm.setAttribute(element.getAttribute());
		xmlElementForm.setSimpleType(element.getSimpleType());
		return xmlElementForm;
	}

	/**
	 * din lista de xmlelementsform creaza lista de xml elements
	 */
	public List<XmlElement> createXmlElements(List<XmlElementForm> forms) {
		List<XmlElement> xmlElements = new ArrayList<XmlElement>();
		for (int i = 0; i < forms.size(); i++) {
			logger.info(i + "");
			xmlElements.add(createElementFromForm(forms.get(i)));
		}
		return xmlElements;
	}

	public XmlElement createElementFromForm(XmlElementForm form) {
		XmlElement element = new XmlElement();
		element.setName(form.getName());
		element.setLevel(form.getLevel());
		element.setAttribute(form.getAttribute());
		element.setSimpleType(form.getSimpleType());
		element.setValue(form.getValue());
		logger.info(element.getName() + " " + element.getValue());
		return element;
	}
}
