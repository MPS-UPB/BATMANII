package com.mps.batmanii.ocrWebManager.beans;

import java.util.Vector;

import com.sun.xml.xsom.XSType;

public class SimpleType {
	private String name = null;
	private XSType base = null;
	private Vector<String> enumeration = new Vector<String>();
	// private String maxValue = null;
	// private String minValue = null;
	private String length = null;
	private String maxLength = null;
	private String minLength = null;
	private String pattern = null;
	private String totalDigits = null;
	private String maxInclusive = null;
	private String minInclusive = null;
	private String minExclusive = null;
	private String maxExclusive = null;

	public String getMaxInclusive() {
		return maxInclusive;
	}

	public void setMaxInclusive(String maxInclusive) {
		this.maxInclusive = maxInclusive;
	}

	public String getMinInclusive() {
		return minInclusive;
	}

	public void setMinInclusive(String minInclusive) {
		this.minInclusive = minInclusive;
	}

	public String getMinExclusive() {
		return minExclusive;
	}

	public void setMinExclusive(String minExclusive) {
		this.minExclusive = minExclusive;
	}

	public String getMaxExclusive() {
		return maxExclusive;
	}

	public void setMaxExclusive(String maxExclusive) {
		this.maxExclusive = maxExclusive;
	}

	public SimpleType(String name, XSType base, Vector<String> enumeration,
			String length, String maxLength, String minLength, String pattern,
			String totalDigits, String maxInclusive, String minInclusive,
			String minExclusive, String maxExclusive) {
		super();
		this.name = name;
		this.base = base;
		this.enumeration = enumeration;
		this.length = length;
		this.maxLength = maxLength;
		this.minLength = minLength;
		this.pattern = pattern;
		this.totalDigits = totalDigits;
		this.maxInclusive = maxInclusive;
		this.minInclusive = minInclusive;
		this.minExclusive = minExclusive;
		this.maxExclusive = maxExclusive;
	}

	public SimpleType() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public XSType getBase() {
		return base;
	}

	public void setBase(XSType xsType) {
		this.base = xsType;
	}

	public Vector<String> getEnumeration() {
		return enumeration;
	}

	public void setEnumeration(Vector<String> enumeration) {
		this.enumeration = enumeration;
	}

	/*
	 * public String getMaxValue() { return maxValue; }
	 * 
	 * 
	 * public void setMaxValue(String maxValue) { this.maxValue = maxValue; }
	 * 
	 * 
	 * public String getMinValue() { return minValue; }
	 * 
	 * 
	 * public void setMinValue(String minValue) { this.minValue = minValue; }
	 */

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public String getMaxLength() {
		return maxLength;
	}

	public void setMaxLength(String maxLength) {
		this.maxLength = maxLength;
	}

	public String getMinLength() {
		return minLength;
	}

	public void setMinLength(String minLength) {
		this.minLength = minLength;
	}

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	public String getTotalDigits() {
		return totalDigits;
	}

	public void setTotalDigits(String totalDigits) {
		this.totalDigits = totalDigits;
	}

	@Override
	public String toString() {
		return "SimpleType [name=" + name + ", base=" + base + ", enumeration="
				+ enumeration + ", length=" + length + ", maxLength="
				+ maxLength + ", minLength=" + minLength + ", pattern="
				+ pattern + ", totalDigits=" + totalDigits + ", maxInclusive="
				+ maxInclusive + ", minInclusive=" + minInclusive
				+ ", minExclusive=" + minExclusive + ", maxExclusive="
				+ maxExclusive + "]";
	}

}
