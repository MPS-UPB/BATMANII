package com.mps.batmanii.ocrWebManager.beans;

public class Exec {

	private String execName;
	private String fullExecName;
	private String execType;

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
		return execName + "<-> " + execType;
	}
}
