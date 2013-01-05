package com.mps.batmanii.ocrWebManager.beans;

import java.util.ArrayList;
import java.util.List;

public class SelectedXmlFiles {
	private List<XmlFile> xmlFiles;

	public SelectedXmlFiles() {
		super();
		this.xmlFiles = new ArrayList<XmlFile>();
	}

	public List<XmlFile> getXmlFiles() {
		return xmlFiles;
	}

	public void setXmlFiles(List<XmlFile> xmlFiles) {
		this.xmlFiles = xmlFiles;
	}

}
