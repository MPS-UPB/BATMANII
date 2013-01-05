package com.mps.batmanii.ocrWebManager.beans;

import java.util.List;

public class XmlElementFormList {

	private List<XmlElementForm> xmlElements;

	public XmlElementFormList() {
		super();
	}

	public XmlElementFormList(List<XmlElementForm> xmlElements) {
		super();
		this.xmlElements = xmlElements;
	}

	public List<XmlElementForm> getXmlElements() {
		return xmlElements;
	}

	public void setXmlElements(List<XmlElementForm> xmlElements) {
		this.xmlElements = xmlElements;
	}

}
