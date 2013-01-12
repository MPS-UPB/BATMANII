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
			if (fileEntry.getName().contains(".exe")) {
				Exec exec = new Exec(fileEntry.getName());
				String execType = getExecTypeByExecName(fileEntry.getName());
				String[] split = execType.split(",");
				logger.info(split.length + "");
				List<String> allExecTypes = new ArrayList<String>();
				for (int i = 0; i < split.length; i++) {
					logger.info(split[i]);
					allExecTypes.add(split[i]);
				}
				exec.setExecType(execType);
				exec.setAllExecTypes(allExecTypes);
				logger.info("Executabil:" + exec.getExecName() + " "
						+ exec.getExecType());
				execs.add(exec);
			} else {
				logger.info("Fisierul " + fileEntry.getName()
						+ " nu este un executabil");
			}
		}
	}

	public void restart() {
		logger.info("In postconstruct ExecContainer");
		execs = new ArrayList<Exec>();
		File folder = new File(propertyHolder.getExecsFolder());
		for (File fileEntry : folder.listFiles()) {
			if (fileEntry.getName().contains(".exe")) {
				Exec exec = new Exec(fileEntry.getName());
				String execType = getExecTypeByExecName(fileEntry.getName());
				String[] split = execType.split(",");
				logger.info(split.length + "");
				List<String> allExecTypes = new ArrayList<String>();
				for (int i = 0; i < split.length; i++) {
					logger.info(split[i]);
					allExecTypes.add(split[i]);
				}
				exec.setExecType(execType);
				exec.setAllExecTypes(allExecTypes);
				logger.info("Executabil:" + exec.getExecName() + " "
						+ exec.getExecType());
				execs.add(exec);
			} else {
				logger.info("Fisierul " + fileEntry.getName()
						+ " nu este un executabil");
			}
		}
	}
	
	public List<Exec> getExecs() {
		return execs;
	}

	public void setExecs(List<Exec> execs) {
		this.execs = execs;
	}

	/**
	 * Metoda care cauta in containerul de fisiere .xsd parsate dupa numele
	 * executabilului si intoarce tipul executabilului sub forma de string
	 * 
	 * @param execName
	 * @return
	 */
	public String getExecTypeByExecName(String execName) {
		ArrayList<XsdFile> listXsdFiles = new ArrayList<XsdFile>(
				xsdContainer.getXsdFiles());
		for (int i = 0; i < listXsdFiles.size(); i++) {
			ArrayList<SimpleType> listSimpleType = new ArrayList<SimpleType>(
					listXsdFiles.get(i).getSimpleTypes());
			for (int j = 0; j < listSimpleType.size(); j++) {
				if (listSimpleType.get(j).getName().compareTo("execName") == 0
						&& listSimpleType.get(j).getPattern()
								.compareTo(execName) == 0) {
					for (int k = 0; k < listSimpleType.size(); k++) {
						if (listSimpleType.get(k).getName()
								.compareTo("execType") == 0) {
							return listSimpleType.get(k).getPattern();
						}
					}
				}

			}
		}
		return "unknown";
	}

}
