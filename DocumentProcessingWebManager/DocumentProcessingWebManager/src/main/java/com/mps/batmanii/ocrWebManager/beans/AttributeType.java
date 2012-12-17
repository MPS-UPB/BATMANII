package com.mps.batmanii.ocrWebManager.beans;

import com.sun.xml.xsom.XSSimpleType;
import com.sun.xml.xsom.XmlString;

public class AttributeType {
	private String name = null;
	private XSSimpleType type = null;
	private XmlString defaulValue;
	private XmlString fixedValue;
	private String use = null;

	public AttributeType(String name, XSSimpleType type, XmlString xmlString,
			XmlString xmlString2, String use) {
		super();
		this.name = name;
		this.type = type;
		this.defaulValue = xmlString;
		this.fixedValue = xmlString2;
		this.use = use;
	}

	public AttributeType() {
		super();
	}

	public AttributeType(String name, XSSimpleType xsSimpleType, String use) {
		super();
		this.name = name;
		this.type = xsSimpleType;
		this.use = use;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public XSSimpleType getType() {
		return type;
	}

	public void setType(XSSimpleType type) {
		this.type = type;
	}

	public String getUse() {
		return use;
	}

	public void setUse(String use) {
		this.use = use;
	}

	public void setDefaulValue(XmlString defaulValue) {
		this.defaulValue = defaulValue;
	}

	public XmlString getDefaulValue() {
		return defaulValue;
	}

	public void setFixedValue(XmlString fixedValue) {
		this.fixedValue = fixedValue;
	}

	public XmlString getFixedValue() {
		return fixedValue;
	}

	@Override
	public String toString() {
		return "AttributeType [name=" + name + ", type=" + type
				+ ", defaulValue=" + defaulValue + ", fixedValue=" + fixedValue
				+ ", use=" + use + "]";
	}

}
