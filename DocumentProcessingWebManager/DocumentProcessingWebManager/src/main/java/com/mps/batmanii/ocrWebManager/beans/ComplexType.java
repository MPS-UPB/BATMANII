package com.mps.batmanii.ocrWebManager.beans;

import java.util.LinkedList;

public class ComplexType {
	private LinkedList<AttributeType> attribute = null;
	private LinkedList<ElementType> elem = null;
	private LinkedList<GroupType> group = null;
	private Component comp = null;
	boolean sequence = false;
	boolean extension = false;
	boolean complexContent = false;

	public ComplexType(LinkedList<AttributeType> attribute, Component comp) {
		super();
		this.attribute = attribute;
		this.elem = new LinkedList<ElementType>();
		this.group = new LinkedList<GroupType>();
		this.comp = comp;
	}

	public ComplexType() {
		elem = new LinkedList<ElementType>();
		// TODO Auto-generated constructor stub
	}

	public ComplexType(LinkedList<AttributeType> attributes) {
		elem = new LinkedList<ElementType>();
		this.attribute = attributes;
		// TODO Auto-generated constructor stub
	}

	public LinkedList<GroupType> getGroup() {
		return group;
	}

	public void setGroup(LinkedList<GroupType> group) {
		this.group = group;
	}

	public void setElem(LinkedList<ElementType> elem) {
		this.elem = elem;
	}

	public LinkedList<ElementType> getElem() {
		return elem;
	}

	public void setAttribute(LinkedList<AttributeType> attribute) {
		this.attribute = attribute;
	}

	public LinkedList<AttributeType> getAttribute() {
		return attribute;
	}

	public void setComp(Component comp) {
		this.comp = comp;
	}

	public Component getComp() {
		return comp;
	}

	@Override
	public String toString() {
		return "ComplexType [" + " comp=" + comp + "attribute=" + attribute
				+ ", elem=" + elem + ", group = " + group + ", sequence="
				+ sequence + ", extension=" + extension + ", complexContent="
				+ complexContent + "]";
	}

}
