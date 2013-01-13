package com.mps.batmanii.ocrWebManager.tests;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map.Entry;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import com.mps.batmanii.ocrWebManager.beans.Exec;
import com.mps.batmanii.ocrWebManager.beans.ExecContainer;
import com.mps.batmanii.ocrWebManager.beans.PropertyHolder;
import com.mps.batmanii.ocrWebManager.beans.SimpleType;
import com.mps.batmanii.ocrWebManager.beans.XsdContainer;
import com.mps.batmanii.ocrWebManager.beans.XsdFile;
import com.mps.batmanii.ocrWebManager.business.ParserXsd;

/**
 * 
 * clasa de test pentru fisiere .exe
 * 
 */

public class TestClassExec {

	private final static Logger logger = LoggerFactory
			.getLogger(TestClassExec.class);
	ExecContainer execContainerTest;
	PropertyHolder propertyHolderTest;
	List<Exec> execs;
	XsdContainer xsdContainerTest;

	/**
	 * 
	 * metoda care cauta in containerul de fisiere .xsd parsate dupa numele
	 * executabilului si intoarce tipul executabilului sub forma de string
	 * 
	 */
	public String getExecTypeByExecName(String execName) {
		ArrayList<XsdFile> listXsdFiles = new ArrayList<XsdFile>(
				xsdContainerTest.getXsdFiles());
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

	@Before
	public void before() throws IOException {
		logger.info("-----------------Before Test-----------------");

		/* clasa pentru foldere */
		Properties prop = new Properties();
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		try {
			InputStream stream = loader
					.getResourceAsStream("config.properties");
			prop.load(stream);
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		logger.info("prop ok");
		propertyHolderTest = new PropertyHolder();

		Set<Entry<Object, Object>> entrySet = prop.entrySet();
		for (Entry<Object, Object> e : entrySet) {
			if ((e.getKey().toString()).compareTo("config.inputSchemasFolder") == 0) {
				propertyHolderTest.setInputSchemasFolder(e.getValue()
						.toString());
			}
			if ((e.getKey().toString()).compareTo("config.outputSchemasFolder") == 0) {
				propertyHolderTest.setOutputSchemasFolder(e.getValue()
						.toString());
			}
			if ((e.getKey().toString()).compareTo("config.execsFolder") == 0) {
				propertyHolderTest.setExecsFolder(e.getValue().toString());
			}
		}

		/* clasa care continele fisierele xsd parsate */
		xsdContainerTest = new XsdContainer();
		List<XsdFile> xsdFiles = xsdContainerTest.getXsdFiles();
		File folder_xsd = new File(propertyHolderTest.getInputSchemasFolder());
		
		for (File fileEntry : folder_xsd.listFiles()) {
			ParserXsd parserXsd = new ParserXsd();
			XsdFile xsdFile = null;
			try {
				xsdFile = parserXsd.parse(fileEntry.getAbsolutePath());
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			xsdFiles.add(xsdFile);
		}

		/* clasa pentru executabile */
		execContainerTest = new ExecContainer();
		List<Exec> execs = execContainerTest.getExecs();
		File folder = new File(propertyHolderTest.getExecsFolder());
		
		for (File fileEntry : folder.listFiles()) {
			if (fileEntry.getName().contains(".exe")) {
				Exec exec = new Exec(fileEntry.getName());
				String execType = getExecTypeByExecName(fileEntry.getName());
				String[] split = execType.split(",");
				List<String> allExecTypes = new ArrayList<String>();
				for (int j = 0; j < split.length; j++) {
					allExecTypes.add(split[j]);
					allExecTypes.add(execType);
				}
				exec.setExecType(execType);
				exec.setAllExecTypes(allExecTypes);
				execs.add(exec);
			} else {
				logger.info("Fisierul " + fileEntry.getName()
						+ " nu este un executabil");
			}
		}
		execContainerTest.setExecs(execs);

		logger.info("-----------------Finish Before Test-----------------");
	}

	/**
	 * 
	 * metoda de test care afiseaza lungimea listei de executabile
	 * 
	 */
	@Test
	public void Test22() {
		logger.info("-----------------Test22-----------------");
		logger.info(execContainerTest.getExecs().size() + "" + "" + "\n");
		logger.info("-----------------Finish Test22-----------------\n");
	}

	/**
	 * 
	 * metoda de test care afiseaza numele executabilelor si tipurile lor
	 * 
	 */
	@Test
	public void Test23() {
		logger.info("-----------------Test23-----------------");

		execs = execContainerTest.getExecs();

		for (int i = 0; i < execs.size(); i++)
			logger.info("Executabilul " + i + ": " + execs.get(i).getExecName() 
					+ " " + execs.get(i).getExecType());

		logger.info("-----------------Finish Test23-----------------\n");
	}

}