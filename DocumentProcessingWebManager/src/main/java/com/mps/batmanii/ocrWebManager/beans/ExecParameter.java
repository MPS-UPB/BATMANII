package com.mps.batmanii.ocrWebManager.beans;

import java.util.List;

/**
 * 
 * @author comy & bersy am stabilit impreuna structura claselor
 * 
 */
public class ExecParameter {
	private String name;
	private String value;
	private int minOccurs;
	private int maxOccurs;
	private int level;// rootElement are nivelul 0, iar apoi nivelul creste
	private List<ExecParameter> execParameters;// copii sau null in caz ca nu
												// mai are

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
