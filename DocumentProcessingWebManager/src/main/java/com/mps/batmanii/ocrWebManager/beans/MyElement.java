package com.mps.batmanii.ocrWebManager.beans;

import org.w3c.dom.Element;

public class MyElement {
	private Element element;
	private int level;
	
	public MyElement(Element element, int level) {
		super();
		this.element = element;
		this.level = level;
	}
	
	public Element getElement() {
		return element;
	}
	public void setElement(Element element) {
		this.element = element;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	
}
