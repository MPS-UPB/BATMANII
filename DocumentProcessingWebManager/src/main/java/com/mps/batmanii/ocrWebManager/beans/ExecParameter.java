package com.mps.batmanii.ocrWebManager.beans;

import java.util.List;

/**
 * Clasa bean pentru a retine informatiile in mod recursiv ce se vor regasi
 * intr-un fisier xml
 * 
 * @author comy
 * @author bersy
 */
public class ExecParameter {
	private String name;
	private String value;
	private int minOccurs;
	private int maxOccurs;
	private boolean isAttribute = false;

	private int level;/* rootElement are nivelul 0, iar apoi nivelul creste */
	private List<ExecParameter> execParameters;/*
												 * copii sau null in caz ca nu
												 * mai are
												 */
	private SimpleType simpleType;

	public boolean isAttribute() {
		return isAttribute;
	}

	public void setAttribute(boolean isAttribute) {
		this.isAttribute = isAttribute;
	}

	@Override
	public String toString() {
		return "ExecParameter [name=" + name + ", value=" + value
				+ ", minOccurs=" + minOccurs + ", maxOccurs=" + maxOccurs
				+ ", isAttribute=" + isAttribute + ", level=" + level
				+ ", execParameters=" + execParameters + ", simpleType="
				+ simpleType + "]";
	}

	public SimpleType getSimpleType() {
		return simpleType;
	}

	public void setSimpleType(SimpleType simpleType) {
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

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public List<ExecParameter> getExecParameters() {
		return execParameters;
	}

	public void setExecParameters(List<ExecParameter> execParameters) {
		this.execParameters = execParameters;
	}

	public ExecParameter() {
		super();
	}

}
