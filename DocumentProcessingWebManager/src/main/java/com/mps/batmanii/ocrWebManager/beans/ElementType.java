package com.mps.batmanii.ocrWebManager.beans;

/**
 * Clasa pentru elementele de tip element din schema .xsd
 * 
 * @author Bersy
 * 
 */
public class ElementType {
	private ComplexType complexType = null;
	private SimpleType simpleType = null;
	private Component elem = null;

	public ElementType() {
		super();
	}

	public ElementType(ComplexType complexType, SimpleType simpleType,
			Component elem) {
		super();
		this.complexType = complexType;
		this.simpleType = simpleType;
		this.elem = elem;
	}

	public void setElem(Component elem) {
		this.elem = elem;
	}

	public Component getElem() {
		return elem;
	}

	public void setSimpleType(SimpleType simpleType) {
		this.simpleType = simpleType;
	}

	public SimpleType getSimpleType() {
		return simpleType;
	}

	public void setComplexType(ComplexType complexType) {
		this.complexType = complexType;
	}

	public ComplexType getComplexType() {
		return complexType;
	}

	@Override
	public String toString() {
		return "ElementType [" + " elem=" + elem + "complexType=" + complexType
				+ ", simpleType=" + simpleType + "]";
	}

}
