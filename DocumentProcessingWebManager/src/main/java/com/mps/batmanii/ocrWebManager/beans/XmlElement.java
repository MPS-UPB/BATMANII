package com.mps.batmanii.ocrWebManager.beans;

/**
 * 
 * @author Adela
 * 
 */

public class XmlElement {
	private String name;
	private String value;
	private boolean attribute;
	private int level;
	private SimpleType simpleType;
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

}