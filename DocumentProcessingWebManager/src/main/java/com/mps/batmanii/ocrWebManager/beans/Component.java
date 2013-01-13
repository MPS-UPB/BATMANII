package com.mps.batmanii.ocrWebManager.beans;

import com.sun.xml.xsom.XSType;

public class Component {
	private String name = null;
	private XSType type = null;
	private int minOccurs;
	private int maxOccurs;

	public Component() {
	}

	public Component(String name, XSType xsType, int i, int j) {
		super();
		this.name = name;
		this.type = xsType;
		this.minOccurs = i;
		this.maxOccurs = j;
	}

	public XSType getType() {
		return type;
	}

	public void setType(XSType type) {
		this.type = type;
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

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "Component [name=" + name + ", type=" + type + ", minOccurs="
				+ minOccurs + ", maxOccurs=" + maxOccurs + "]";
	}

}
