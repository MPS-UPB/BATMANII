package com.mps.batmanii.ocrWebManager.beans;

import java.util.List;

/**
 * 
 * @author comy & bersy am stabilit impreuna structura claselor
 * 
 */
public class XmlFile {

	private String execType;
	private String execName;
	private List<String> allExecTypes;
	private Component rootElement;

	@Override
	public String toString() {
		return "XmlFile [execType=" + execType + ", execName=" + execName
				+ ", rootElement=" + rootElement + ", childrens=" + childrens
				+ ", xmlElements=" + xmlElements + "]";
	}

	private List<ExecParameter> childrens;
	private List<XmlElement> xmlElements;

	public XmlFile() {
		super();
	}

	public String getExecType() {
		return execType;
	}

	public void setExecType(String execType) {
		this.execType = execType;
	}

	public String getExecName() {
		return execName;
	}

	public void setExecName(String execName) {
		this.execName = execName;
	}

	public Component getRootElement() {
		return rootElement;
	}

	public void setRootElement(Component rootElement) {
		this.rootElement = rootElement;
	}

	public List<ExecParameter> getChildrens() {
		return childrens;
	}

	public void setChildrens(List<ExecParameter> childrens) {
		this.childrens = childrens;
	}

	public List<XmlElement> getXmlElements() {
		return xmlElements;
	}

	public void setXmlElements(List<XmlElement> xmlElements) {
		this.xmlElements = xmlElements;
	}

	public List<String> getAllExecTypes() {
		return allExecTypes;
	}

	public void setAllExecTypes(List<String> allExecTypes) {
		this.allExecTypes = allExecTypes;
	}

}
