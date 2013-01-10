package com.mps.batmanii.ocrWebManager.business;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.mps.batmanii.ocrWebManager.beans.AttributeType;
import com.mps.batmanii.ocrWebManager.beans.ComplexType;
import com.mps.batmanii.ocrWebManager.beans.ElementType;
import com.mps.batmanii.ocrWebManager.beans.ExecParameter;
import com.mps.batmanii.ocrWebManager.beans.SimpleType;
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

	XsdContainer xsdContainer;

	public XsdContainer getXsdContainer() {
		return xsdContainer;
	}

	public void setXsdContainer(XsdContainer xsdContainer) {
		this.xsdContainer = xsdContainer;
	}

	ArrayList<ExecParameter> parseComplexType(ComplexType complexType,
			int level, XsdFile xsdFile) {

		ArrayList<ExecParameter> execParameters = new ArrayList<ExecParameter>();
		for (AttributeType attributeType : complexType.getAttribute()) {
			ExecParameter execParameter = new ExecParameter();
			execParameter.setLevel(level);
			execParameter.setMaxOccurs(1);
			execParameter.setMinOccurs(1);
			execParameter.setName(attributeType.getName());
			execParameter.setSimpleType(null);
			execParameter.setAttribute(true);
			// logger.info (attributeType.getType().getName());
			// logger.info (xsdFile.getSimpleTypes().toString());
			for (SimpleType simpleType : xsdFile.getSimpleTypes()) {

				if (simpleType.getName().compareTo(
						attributeType.getType().getName()) == 0) {
					execParameter.setSimpleType(simpleType);
					break;
				}
			}
			execParameters.add(execParameter);

		}
		for (ElementType elementType : complexType.getElem()) {

			if (elementType.getElem().getName().compareTo("execInfo") != 0) {
				ExecParameter execParameter = new ExecParameter();
				execParameter.setLevel(level);
				execParameter
						.setMaxOccurs(elementType.getElem().getMaxOccurs());
				execParameter
						.setMinOccurs(elementType.getElem().getMinOccurs());
				execParameter.setName(elementType.getElem().getName());
				execParameter.setSimpleType(null);
				if (elementType.getElem().getType().getName() != null)
					for (SimpleType simpleType : xsdFile.getSimpleTypes()) {
						if (simpleType.getName().compareTo(
								elementType.getElem().getType().getName()) == 0)
							execParameter.setSimpleType(simpleType);
					}
				if (elementType.getElem().getType().getName() != null)
					for (ComplexType complexType2 : xsdFile.getComplexTypes()) {
						if (complexType2
								.getComp()
								.getName()
								.compareTo(
										elementType.getElem().getType()
												.getName()) == 0) {
							execParameter.setExecParameters(parseComplexType(
									complexType2, level + 1, xsdFile));
							break;
						}
					}
				execParameters.add(execParameter);
			}
		}
		return execParameters;

	}

	// ToDo pt Bersy
	// Primeste ca parametru un obiect XsdFile si imi intoarce un obiect XmlFile
	public XmlFile getXmlFileFromXsdFile(XsdFile xsdFile) {
		XmlFile xmlFile = new XmlFile();
		ArrayList<SimpleType> listSimpleType = new ArrayList<SimpleType>(
				xsdFile.getSimpleTypes());
		// LinkedList<SimpleType>listSimpleType = (LinkedList<SimpleType>)
		// xsdFile.getSimpleTypes();
		for (int i = 0; i < listSimpleType.size(); i++) {
			if (listSimpleType.get(i).getName().compareTo("execName") == 0) {
				xmlFile.setExecName(listSimpleType.get(i).getPattern());
			}
			if (listSimpleType.get(i).getName().compareTo("execType") == 0) {
				xmlFile.setExecType(listSimpleType.get(i).getPattern());
			}

		}
		xmlFile.setRootElement(xsdFile.getElementType().getElem());
		if (xsdFile.getElementType().getComplexType() != null) {
			ComplexType complexType = xsdFile.getElementType().getComplexType();
			xmlFile.setChildrens(parseComplexType(complexType, 1, xsdFile));
		} else
			for (ComplexType complexType : xsdFile.getComplexTypes()) {
				if (xsdFile.getElementType().getElem().getType() != null)
					if (xsdFile.getElementType().getElem().getType().getName()
							.compareTo(complexType.getComp().getName()) == 0) {
						xmlFile.setChildrens(parseComplexType(complexType, 1,
								xsdFile));
					}
			}

		return xmlFile;
	}

	/**
	 * Metoda care cauta in lista de obiecte XsdFile din XsdContainer dupa
	 * execName si sa imi intoarca obiectul de tip XsdFile
	 */
	// ToDo Bersy
	public XsdFile getXsdFileByExecName(String execName) {
		ArrayList<XsdFile> listXsdFiles = new ArrayList<XsdFile>(
				xsdContainer.getXsdFiles());
		for (int i = 0; i < listXsdFiles.size(); i++) {
			ArrayList<SimpleType> listSimpleType = new ArrayList<SimpleType>(
					listXsdFiles.get(i).getSimpleTypes());
			for (int j = 0; j < listSimpleType.size(); j++) {
				if (listSimpleType.get(j).getName().compareTo("execName") == 0
						&& listSimpleType.get(j).getPattern()
								.compareTo(execName) == 0)
					return listXsdFiles.get(i);
			}
		}
		return null;
	}

	public ArrayList<XmlElement> parseExecParameter(ExecParameter execParameter) {
		ArrayList<XmlElement> xmlElements = new ArrayList<XmlElement>();
		xmlElements.add(new XmlElement(execParameter.getName(), null,
				execParameter.isAttribute(), execParameter.getLevel(),
				execParameter.getSimpleType(),execParameter.getMinOccurs(),execParameter.getMaxOccurs()));
		if (execParameter.getExecParameters() != null)
			for (ExecParameter execParameter1 : execParameter
					.getExecParameters()) {
				xmlElements.addAll(parseExecParameter(execParameter1));
			}
		return xmlElements;

	}

	// ToDo Bersy
	// SA adaugi minOccurs si maxOccurs
	public List<XmlElement> getXmlElements(XmlFile xmlFile) {

		ArrayList<XmlElement> xmlElements = new ArrayList<XmlElement>();
		xmlElements.add(new XmlElement(xmlFile.getRootElement().getName(),
				null, false, 0, null,xmlFile.getRootElement().getMinOccurs(),xmlFile.getRootElement().getMaxOccurs()));
		for (ExecParameter execParameter : xmlFile.getChildrens()) {
			xmlElements.addAll(parseExecParameter(execParameter));
		}
		return xmlElements;
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
				if (i < (xmlElements.size() - 1)) {
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
		if (element.getSimpleType() != null) {
			if (element.getSimpleType().getEnumeration().size() == 0)
				xmlElementForm.setEnumeration(new Vector<String>());
			else
				xmlElementForm.setEnumeration(element.getSimpleType()
						.getEnumeration());
		}
		xmlElementForm.setSimpleType(element.getSimpleType());
		xmlElementForm.setValue(element.getValue());
		xmlElementForm.setMinOccurs(element.getMinOccurs());
		xmlElementForm.setMaxOccurs(element.getMaxOccurs());
		return xmlElementForm;
	}

	/**
	 * din lista de xmlelementsform creaza lista de xml elements
	 */
	public List<XmlElement> createXmlElements(List<XmlElementForm> forms) {
		List<XmlElement> xmlElements = new ArrayList<XmlElement>();
		for (int i = 0; i < forms.size(); i++) {
			// logger.info(i + "");
			xmlElements.add(createElementFromForm(forms.get(i)));
		}
		return xmlElements;
	}

	public XmlElement createElementFromForm(XmlElementForm form) {
		XmlElement element = new XmlElement();
		element.setName(form.getName());
		element.setLevel(form.getLevel());
		element.setAttribute(form.getAttribute());
		SimpleType simpleType = new SimpleType();
		simpleType.setEnumeration(form.getEnumeration());
		element.setSimpleType(simpleType);
		element.setValue(form.getValue());
		element.setMaxOccurs(form.getMaxOccurs());
		element.setMinOccurs(form.getMinOccurs());
		logger.info(element.getName() + " " + element.getValue());
		return element;
	}

	public XmlFile getXmlFileFromList(String execName, List<XmlFile> files) {
		logger.info(execName + " " + files.size());
		for (XmlFile file : files) {
			if (file.getExecName().equals(execName))
				return file;
		}
		return null;
	}
}
