package com.mps.batmanii.ocrWebManager.beans;

import java.util.List;

/**
 * Clasa bean in care sunt retinute informatiile dintr-un fisier .xsd
 * 
 * @author comy
 * 
 */
public class XsdFile {

	private String fileName;
	private List<SimpleType> simpleTypes;
	private List<ComplexType> complexTypes;
	private List<GroupType> groupTypes;
	private ElementType elementType;

	public XsdFile() {
		super();
	}

	public XsdFile(String fileName) {
		super();
		this.fileName = fileName;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public List<SimpleType> getSimpleTypes() {
		return simpleTypes;
	}

	public void setSimpleTypes(List<SimpleType> simpleTypes) {
		this.simpleTypes = simpleTypes;
	}

	public List<ComplexType> getComplexTypes() {
		return complexTypes;
	}

	public void setComplexTypes(List<ComplexType> complexTypes) {
		this.complexTypes = complexTypes;
	}

	public List<GroupType> getGroupTypes() {
		return groupTypes;
	}

	public void setGroupTypes(List<GroupType> groupTypes) {
		this.groupTypes = groupTypes;
	}

	public ElementType getElementType() {
		return elementType;
	}

	public void setElementType(ElementType elementType) {
		this.elementType = elementType;
	}

}
