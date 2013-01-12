package com.mps.batmanii.ocrWebManager.beans;

import java.util.List;

/**
 * Clasa bean pentru a retine informatiile despre un executabil
 * 
 * @author comy
 * 
 */
public class Exec {

	private String execName;
	private String fullExecName;
	private String execType;
	List<String> allExecTypes;

	public Exec(String fullExecName) {
		super();
		this.fullExecName = fullExecName;
		this.execName = fullExecName.substring(0, fullExecName.indexOf("."));
	}

	public Exec() {
	}

	public String getExecName() {
		return execName;
	}

	public void setExecName(String execName) {
		this.execName = execName;
	}

	public String getFullExecName() {
		return fullExecName;
	}

	public List<String> getAllExecTypes() {
		return allExecTypes;
	}

	public void setAllExecTypes(List<String> allExecTypes) {
		this.allExecTypes = allExecTypes;
	}

	public void setFullExecName(String fullExecName) {
		this.fullExecName = fullExecName;
	}

	public String getExecType() {
		return execType;
	}

	public void setExecType(String execType) {
		this.execType = execType;
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(execName + "<->");
		builder.append(execType + "<->");
		for (String s : allExecTypes) {
			builder.append(s + "-");
		}
		return builder.toString();
	}
}
