package com.mps.batmanii.ocrWebManager.tests;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
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
import com.mps.batmanii.ocrWebManager.beans.GroupType;
import com.mps.batmanii.ocrWebManager.beans.PropertyHolder;
import com.mps.batmanii.ocrWebManager.beans.SimpleType;
import com.mps.batmanii.ocrWebManager.beans.XsdContainer;
import com.mps.batmanii.ocrWebManager.beans.XsdFile;
import com.mps.batmanii.ocrWebManager.business.ParserXsd;

/**
 * 
 * clasa de test parsare Xsd
 * 
 */

public class TestClassXsd {

	private final static Logger logger = LoggerFactory
			.getLogger(TestClassXsd.class);
	XsdContainer xsdContainerTest;
	PropertyHolder propertyHolderTest;
	List<XsdFile> xsdFiles;

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
		File folder = new File(propertyHolderTest.getInputSchemasFolder());

		for (File fileEntry : folder.listFiles()) {
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

		logger.info("-----------------Finish Before Test-----------------");

	}

	/**
	 * 
	 * metoda de test care afiseaza lungimea listei de scheme parsate
	 * 
	 */
	@Test
	public void Test1() {
		
		logger.info("-----------------Test1-----------------");
		
		logger.info(xsdContainerTest.getXsdFiles().size() + "" + "" + "\n");
		
		logger.info("-----------------Finish Test1-----------------\n");
		
	}

	/**
	 * 
	 * metoda de test care afiseaza fisierele xsd parsate
	 * 
	 */
	@Test
	public void Test2() {
		
		logger.info("-----------------Test2-----------------");

		xsdFiles = xsdContainerTest.getXsdFiles();

		for (int i = 0; i < xsdFiles.size(); i++) {
			
			logger.info(xsdFiles.get(i).getFileName() + "\n");

			logger.info("SIMPLE TYPES\n");
			if (xsdFiles.get(i).getSimpleTypes() != null) {
				List<SimpleType> simpleType = xsdFiles.get(i).getSimpleTypes();
				for (int j = 0; j < simpleType.size(); j++)
					logger.info(simpleType.get(j).toString());
				logger.info("\n");
			} else
				logger.info("NO SIMPLE TYPES\n");

			logger.info("COMPLEX TYPES\n");
			if (xsdFiles.get(i).getComplexTypes() != null) {
				List<ComplexType> complexType = xsdFiles.get(i).getComplexTypes();
				for (int j = 0; j < complexType.size(); j++)
					logger.info(complexType.get(j).toString());
				logger.info("\n");
			} else
				logger.info("NO COMPLEX TYPES\n");

			logger.info("ELEMENT TYPES\n");
			if (xsdFiles.get(i).getElementType() != null) {
				ElementType elementType = xsdFiles.get(i).getElementType();
				logger.info(elementType.toString() + "\n");
			} else
				logger.info("NO ELEMENT TYPES\n");

			logger.info("GROUP TYPES\n");
			if (xsdFiles.get(i).getGroupTypes() != null) {
				List<GroupType> groupType = xsdFiles.get(i).getGroupTypes();
				for (int j = 0; j < groupType.size(); j++)
					logger.info(groupType.get(j).toString());
				logger.info("\n");
			} else
				logger.info("NO GROUP TYPES\n");
			
		}
		
		logger.info("-----------------Finish Test2-----------------\n");
	}
	
}