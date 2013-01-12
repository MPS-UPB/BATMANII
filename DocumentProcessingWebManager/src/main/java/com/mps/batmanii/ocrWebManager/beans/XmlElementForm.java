package com.mps.batmanii.ocrWebManager.beans;

import java.util.Vector;

/**
 * Clasa bean pentru a afisa in interfata informatiile din clasa XmlElement,
 * plus alte informatii suplimentare
 * 
 * @author comy
 * 
 */
public class XmlElementForm {
	private String name;
	private String value;
	private boolean attribute;
	private int level;
	private Vector<String> enumeration;
	private SimpleType simpleType;
	private boolean toDisplay;
	private int minOccurs;
	private int maxOccurs;

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

	public int getMinOccurs() {
		return minOccurs;
	}

	public void setMinOccurs(int minOccurs) {
		this.minOccurs = minOccurs;
	}

	public int getMaxOccurs() {
		return maxOccurs;
	}

	public void setMaxOccurs(int maxOccurs) {
		this.maxOccurs = maxOccurs;
	}

}
