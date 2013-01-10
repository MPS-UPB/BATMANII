package com.mps.batmanii.ocrWebManager.beans;

import java.util.Vector;

public class XmlElementForm {
	private String name;
	private String value;
	private boolean attribute;
	private int level;
	private Vector<String> enumeration;
	private SimpleType simpleType;
	private boolean toDisplay;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public boolean getAttribute() {
		return attribute;
	}

	public void setAttribute(boolean attribute) {
		this.attribute = attribute;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public SimpleType getSimpleType() {
		return simpleType;
	}

	public void setSimpleType(SimpleType simpleType) {
		this.simpleType = simpleType;
	}

	public boolean getToDisplay() {
		return toDisplay;
	}

	public void setToDisplay(boolean toDisplay) {
		this.toDisplay = toDisplay;
	}

	public Vector<String> getEnumeration() {
		return enumeration;
	}

	public void setEnumeration(Vector<String> enumeration) {
		this.enumeration = enumeration;
	}

}
