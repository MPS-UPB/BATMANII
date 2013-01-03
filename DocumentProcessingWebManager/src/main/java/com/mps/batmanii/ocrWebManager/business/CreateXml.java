package com.mps.batmanii.ocrWebManager.business;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.mps.batmanii.ocrWebManager.beans.Component;
import com.mps.batmanii.ocrWebManager.beans.ExecParameter;
import com.mps.batmanii.ocrWebManager.beans.XmlFile;

/**
 * 
 * @author Adela
 * 
 */

public class CreateXml {

	public void verifyChildren(Document doc, Element rootElem,
			ExecParameter father) {
		int index;
		Element elem = doc.createElement(father.getName());
		// Eg. <left> 10 </left>
		if (father.getExecParameters() == null) {
			Element element = doc.createElement(father.getName());
			element.appendChild(doc.createTextNode(father.getValue()));
			rootElem.appendChild(element);
		} else {
			for (index = 0; index < father.getExecParameters().size(); index++) {
				ExecParameter children = father.getExecParameters().get(index);

				// Eg. atribute <inputFile name='input.jpg' />
				if (father.getLevel() == children.getLevel()) {
					elem.setAttribute(children.getName(), children.getValue());
				}
				// subchildren
				else {
					verifyChildren(doc, elem, children);
				}
				rootElem.appendChild(elem);
			}
		}

	}

	public void generateXml(String path, XmlFile xml) {
		Component rootElement = xml.getRootElement();
		List<ExecParameter> childrens = xml.getChildrens();
		int i;
		try {

			DocumentBuilderFactory docFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			// root element
			Document doc = docBuilder.newDocument();
			Element rootElem = doc.createElement(rootElement.getName());
			doc.appendChild(rootElem);

			for (i = 0; i < childrens.size(); i++)
				verifyChildren(doc, rootElem, childrens.get(i));

			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory
					.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(path));

			transformer.transform(source, result);

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		}
	}

	public static void main(String argv[]) {
		CreateXml createXml = new CreateXml();

		XmlFile test = new XmlFile();
		ExecParameter e1 = new ExecParameter();
		ExecParameter e2 = new ExecParameter();
		ExecParameter e3 = new ExecParameter();
		ExecParameter e3_1 = new ExecParameter();
		ExecParameter e3_2 = new ExecParameter();
		ExecParameter e3_2_1 = new ExecParameter();
		ExecParameter e3_3 = new ExecParameter();
		ExecParameter sub_e3_3 = new ExecParameter();

		Component c = new Component();
		c.setName("task");

		e1.setName("left");
		e1.setValue("10");
		e1.setMinOccurs(0);
		e1.setMaxOccurs(0);
		e1.setLevel(0);

		e2.setName("bottom");
		e2.setValue("10");
		e2.setMinOccurs(0);
		e2.setMaxOccurs(0);
		e2.setLevel(0);

		e3.setName("inputFile");
		e3.setMinOccurs(0);
		e3.setMaxOccurs(0);
		e3.setLevel(0);
		List<ExecParameter> exec = new ArrayList<ExecParameter>();
		e3_1.setName("name");
		e3_1.setValue("input.jpg");
		e3_1.setMaxOccurs(0);
		e3_1.setMinOccurs(0);
		e3_1.setLevel(0);
		exec.add(e3_1);

		e3_2.setName("bottom");
		e3_2.setValue("20");
		e3_2.setMinOccurs(0);
		e3_2.setMaxOccurs(0);
		e3_2.setLevel(1);
		List<ExecParameter> exec1 = new ArrayList<ExecParameter>();
		e3_2_1.setName("name");
		e3_2_1.setValue("input.jpg");
		e3_2_1.setMaxOccurs(0);
		e3_2_1.setMinOccurs(0);
		e3_2_1.setLevel(1);
		exec1.add(e3_2_1);
		e3_2.setExecParameters(exec1);
		exec.add(e3_2);

		e3_3.setName("left");
		e3_3.setValue("20");
		e3_3.setMinOccurs(0);
		e3_3.setMaxOccurs(0);
		e3_3.setLevel(1);

		sub_e3_3.setName("subcopil");
		sub_e3_3.setValue("30");
		sub_e3_3.setMinOccurs(0);
		sub_e3_3.setMaxOccurs(0);
		sub_e3_3.setLevel(2);
		List<ExecParameter> exec_e3_3 = new ArrayList<ExecParameter>();
		exec_e3_3.add(sub_e3_3);
		e3_3.setExecParameters(exec_e3_3);

		exec.add(e3_3);
		e3.setExecParameters(exec);

		List<ExecParameter> childrens = new ArrayList<ExecParameter>();

		childrens.add(e1);
		childrens.add(e2);
		childrens.add(e3);

		test.setChildrens(childrens);
		test.setRootElement(c);
		createXml.generateXml("D:\\file.xml", test);
	}
}
