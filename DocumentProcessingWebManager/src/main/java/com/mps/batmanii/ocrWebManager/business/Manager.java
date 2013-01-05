package com.mps.batmanii.ocrWebManager.business;

import java.util.ArrayList;
import java.util.List;

import com.mps.batmanii.ocrWebManager.beans.Component;
import com.mps.batmanii.ocrWebManager.beans.ExecParameter;
import com.mps.batmanii.ocrWebManager.beans.XmlElement;
import com.mps.batmanii.ocrWebManager.beans.XmlFile;

public class Manager {

	public XmlFile createMockXmlFile() {
		XmlFile xmlFile = new XmlFile();
		xmlFile.setExecName("crop.exe");
		xmlFile.setExecType("support,crop");
		xmlFile.setRootElement(new Component("task", null, 1, 1));

		List<ExecParameter> childrens = new ArrayList<ExecParameter>();

		ExecParameter inputFile = buildExecParameter("inputFile");
		ExecParameter attr = buildExecParameter("name");
		List<ExecParameter> execParameters = new ArrayList<ExecParameter>();
		execParameters.add(attr);
		inputFile.setExecParameters(execParameters);
		childrens.add(inputFile);

		ExecParameter outputFile = buildExecParameter("outputFile");
		ExecParameter attr1 = buildExecParameter("name");
		List<ExecParameter> execParameters1 = new ArrayList<ExecParameter>();
		execParameters1.add(attr1);
		ExecParameter attr2 = buildExecParameter("name2");
		execParameters1.add(attr2);

		outputFile.setExecParameters(execParameters1);
		childrens.add(outputFile);

		childrens.add(buildExecParameter("top"));
		childrens.add(buildExecParameter("bottom"));
		childrens.add(buildExecParameter("left"));
		childrens.add(buildExecParameter("right"));

		xmlFile.setChildrens(childrens);
		return xmlFile;
	}

	public ExecParameter buildExecParameter(String name) {
		ExecParameter execParameter = new ExecParameter();
		execParameter.setLevel(1);
		execParameter.setMinOccurs(1);
		execParameter.setMaxOccurs(1);
		execParameter.setName(name);
		execParameter.setExecParameters(null);
		return execParameter;
	}

	public List<XmlElement> createMockXmlElements() {
		List<XmlElement> elements = new ArrayList<XmlElement>();
		XmlElement root = new XmlElement();
		root.setName("task");
		root.setLevel(0);
		root.setAttribute(false);
		// xmlElementselements.add(root);
		elements.add(createElement("inputFile", 1, false));
		elements.add(createElement("name", 2, true));
		elements.add(createElement("outputFile", 1, false));
		elements.add(createElement("name", 2, true));
		elements.add(createElement("top", 1, false));
		elements.add(createElement("bottom", 1, false));
		return elements;
	}

	public XmlElement createElement(String name, int level, boolean attr) {
		XmlElement element = new XmlElement();
		element.setName(name);
		element.setLevel(level);
		element.setAttribute(attr);
		return element;

	}

}
