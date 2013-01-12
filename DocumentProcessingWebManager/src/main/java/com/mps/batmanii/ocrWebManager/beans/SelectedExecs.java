package com.mps.batmanii.ocrWebManager.beans;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Clasa bean ce contine o lista cu executabilele selectate de utilizator in
 * interfata
 * 
 * @author comy
 * 
 */
public class SelectedExecs {

	private final static Logger logger = LoggerFactory
			.getLogger(SelectedExecs.class);

	private List<Exec> selectedExecs;

	public SelectedExecs() {
		super();
		this.selectedExecs = new ArrayList<Exec>();
		logger.info("Creare singleton selectedExecs");
	}

	public SelectedExecs(List<Exec> selectedExecs) {
		super();
		this.selectedExecs = selectedExecs;
	}

	public List<Exec> getSelectedExecs() {
		return selectedExecs;
	}

	public void setSelectedExecs(List<Exec> selectedExecs) {
		this.selectedExecs = selectedExecs;
	}

}
