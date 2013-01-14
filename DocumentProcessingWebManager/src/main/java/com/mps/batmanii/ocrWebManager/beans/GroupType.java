package com.mps.batmanii.ocrWebManager.beans;

import java.util.LinkedList;

/**
 * Clasa pentru elementele de tip group din schema .xsd
 * 
 * @author Bersy
 * 
 */
public class GroupType {
	LinkedList<ElementType> elem = null;

	public GroupType(LinkedList<ElementType> elem) {
		super();
		this.elem = elem;
	}

	public LinkedList<ElementType> getElem() {
		return elem;
	}

	public void setElem(LinkedList<ElementType> elem) {
		this.elem = elem;
	}

	@Override
	public String toString() {
		return "Group [elem=" + elem + "]";
	}

}
