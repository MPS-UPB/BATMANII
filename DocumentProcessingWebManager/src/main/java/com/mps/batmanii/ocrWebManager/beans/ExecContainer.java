package com.mps.batmanii.ocrWebManager.beans;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class ExecContainer {
	private final static Logger logger = LoggerFactory
			.getLogger(ExecContainer.class);

	@Autowired
	XsdContainer xsdContainer;

	@Autowired
	PropertyHolder propertyHolder;

	private List<Exec> execs;

	public ExecContainer() {
		logger.info("Creare singleton ExecContainer");
		execs = new ArrayList<Exec>();
	}

	@PostConstruct
	public void postConstruct() {
		logger.info("In postconstruct ExecContainer");

		File folder = new File(propertyHolder.getExecsFolder());
		for (File fileEntry : folder.listFiles()) {
			Exec exec = new Exec(fileEntry.getName());
			exec.setExecType(getExecTypeByExecName(fileEntry.getName()));
			execs.add(exec);
		}
	}

	public List<Exec> getExecs() {
		return execs;
	}

	public void setExecs(List<Exec> execs) {
		this.execs = execs;
	}

	// ToDo Bersy - momentan metoda e hardcodata de comy
	/**
	 * Metoda care cauta in containerul de fisiere .xsd parsate dupa numele
	 * executabilului si intoarce tipul executabilului sub forma de string
	 * 
	 * @param execName
	 * @return
	 */
	public String getExecTypeByExecName(String execName) {
		if (execName.contains("crop"))
			return "preprocessing";
		if (execName.contains("hierarchy"))
			return "hierarchy";
		if (execName.contains("layout"))
			return "layout";
		if (execName.contains("tesseract"))
			return "ocr";
		return "default";
	}

}
