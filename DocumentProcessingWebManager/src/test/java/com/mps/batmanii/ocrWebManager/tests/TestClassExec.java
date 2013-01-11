package com.mps.batmanii.ocrWebManager.tests;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import com.mps.batmanii.ocrWebManager.beans.ComplexType;
import com.mps.batmanii.ocrWebManager.beans.ElementType;
import com.mps.batmanii.ocrWebManager.beans.Exec;
import com.mps.batmanii.ocrWebManager.beans.ExecContainer;
import com.mps.batmanii.ocrWebManager.beans.GroupType;
import com.mps.batmanii.ocrWebManager.beans.PropertyHolder;
import com.mps.batmanii.ocrWebManager.beans.SimpleType;
import com.mps.batmanii.ocrWebManager.beans.XsdContainer;
import com.mps.batmanii.ocrWebManager.beans.XsdFile;
import com.mps.batmanii.ocrWebManager.business.ParserXsd;

public class TestClassExec {

	private final static Logger logger = LoggerFactory
			.getLogger(TestClassExec.class);
	ExecContainer execContainerTest;
	PropertyHolder propertyHolderTest;
	List<Exec> execs;

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
		/* for (Entry<Object, Object> e : entrySet) {
		logger.info(e.getKey().toString() + "=" + e.getValue().toString());
		} */
		for (Entry<Object, Object> e : entrySet) {
			if ((e.getKey().toString()).compareTo("config.inputSchemasFolder") == 0) {
				propertyHolderTest.setInputSchemasFolder(e.getValue().toString());
			}
			if ((e.getKey().toString()).compareTo("config.outputSchemasFolder") == 0) {
				propertyHolderTest.setOutputSchemasFolder(e.getValue().toString());
			}
			if ((e.getKey().toString()).compareTo("config.execsFolder") == 0) {
				propertyHolderTest.setExecsFolder(e.getValue().toString());
			}
		}
		
		execContainerTest = new ExecContainer();
		List<Exec> execs = execContainerTest.getExecs();
		File folder = new File(propertyHolderTest.getExecsFolder());
	
		for (File fileEntry : folder.listFiles()) {
			if (fileEntry.getName().contains(".exe")) {
				Exec exec = new Exec(fileEntry.getName());
				execs.add(exec);
			} else {
				logger.info("Fisierul " + fileEntry.getName() + " nu este un executabil");
			}
		}
		
		execContainerTest.setExecs(execs);
		/* for (int i = 0; i < execContainerTest.getExecs().size(); i++)
			logger.info("BEFORE execContainerTest " + execContainerTest.getExecs().get(i).getExecName());		
		for (int i = 0; i < execs.size(); i++)
			logger.info("BEFORE execs " + execs.get(i).getExecName()); */

		logger.info("-----------------Finish Before Test-----------------");

	}

	/* metoda de test care afiseaza lungimea listei de executabile */
	@Test
	public void Test22() {
		logger.info("-----------------Test22-----------------");
		logger.info(execContainerTest.getExecs().size() + "" + "" + "\n");		
		logger.info("-----------------Finish Test22-----------------\n");
	}
	
	/* metoda de test care afiseaza numele executabilelor */
	@Test
	public void Test23() {
		logger.info("-----------------Test23-----------------");
		
		execs = execContainerTest.getExecs();
		
		for (int i = 0; i < execs.size(); i++)
			logger.info("executabilul " + i + " " + execs.get(i).getExecName());			
	
		logger.info("-----------------Finish Test23-----------------\n");
	}
}