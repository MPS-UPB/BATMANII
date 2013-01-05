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

	public XmlElement() {
		super();
	}

	@Override
	public String toString() {
		return "XmlElement [name=" + name + ", value=" + value + ", attribute="
				+ attribute + ", level=" + level + ", simpleType=" + simpleType
				+ "]";
	}

	public XmlElement(String name, String value, boolean attribute, int level,
			SimpleType simpleType) {
		
		this.name = name;
		this.value = value;
		this.attribute = attribute;
		this.level = level;
		this.simpleType = simpleType;
	}

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