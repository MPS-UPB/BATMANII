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
	 * metoda de test afisare adjust brightness.xsd
	 * 
	 */
	@Test
	public void Test2() {
		logger.info("-----------------Test2-----------------");

		xsdFiles = xsdContainerTest.getXsdFiles();

		logger.info(xsdFiles.get(0).getFileName() + "\n");

		logger.info("SIMPLE TYPES\n");
		if (xsdFiles.get(0).getSimpleTypes() != null) {
			List<SimpleType> simpleType = xsdFiles.get(0).getSimpleTypes();
			for (int i = 0; i < simpleType.size(); i++)
				logger.info(simpleType.get(i).toString());
			logger.info("\n");
		} else
			logger.info("NO SIMPLE TYPES\n");

		logger.info("COMPLEX TYPES\n");
		if (xsdFiles.get(0).getComplexTypes() != null) {
			List<ComplexType> complexType = xsdFiles.get(0).getComplexTypes();
			for (int i = 0; i < complexType.size(); i++)
				logger.info(complexType.get(i).toString());
			logger.info("\n");
		} else
			logger.info("NO COMPLEX TYPES\n");

		logger.info("ELEMENT TYPES\n");
		if (xsdFiles.get(0).getElementType() != null) {
			ElementType elementType = xsdFiles.get(0).getElementType();
			logger.info(elementType.toString() + "\n");
		} else
			logger.info("NO ELEMENT TYPES\n");

		logger.info("GROUP TYPES\n");
		if (xsdFiles.get(0).getGroupTypes() != null) {
			List<GroupType> groupType = xsdFiles.get(0).getGroupTypes();
			for (int i = 0; i < groupType.size(); i++)
				logger.info(groupType.get(i).toString());
			logger.info("\n");
		} else
			logger.info("NO GROUP TYPES\n");

		logger.info("-----------------Finish Test2-----------------\n");
	}

	/**
	 * 
	 * metoda de test afisare adjust contrast.xsd
	 * 
	 */
	@Test
	public void Test3() {
		logger.info("-----------------Test3-----------------");

		xsdFiles = xsdContainerTest.getXsdFiles();

		logger.info(xsdFiles.get(1).getFileName() + "\n");

		logger.info("SIMPLE TYPES\n");
		if (xsdFiles.get(1).getSimpleTypes() != null) {
			List<SimpleType> simpleType = xsdFiles.get(1).getSimpleTypes();
			for (int i = 0; i < simpleType.size(); i++)
				logger.info(simpleType.get(i).toString());
			logger.info("\n");
		} else
			logger.info("NO SIMPLE TYPES\n");

		logger.info("COMPLEX TYPES\n");
		if (xsdFiles.get(1).getComplexTypes() != null) {
			List<ComplexType> complexType = xsdFiles.get(1).getComplexTypes();
			for (int i = 0; i < complexType.size(); i++)
				logger.info(complexType.get(i).toString());
			logger.info("\n");
		} else
			logger.info("NO COMPLEX TYPES\n");

		logger.info("ELEMENT TYPES\n");
		if (xsdFiles.get(1).getElementType() != null) {
			ElementType elementType = xsdFiles.get(1).getElementType();
			logger.info(elementType.toString() + "\n");
		} else
			logger.info("NO ELEMENT TYPES\n");

		logger.info("GROUP TYPES\n");
		if (xsdFiles.get(1).getGroupTypes() != null) {
			List<GroupType> groupType = xsdFiles.get(1).getGroupTypes();
			for (int i = 0; i < groupType.size(); i++)
				logger.info(groupType.get(i).toString());
			logger.info("\n");
		} else
			logger.info("NO GROUP TYPES\n");

		logger.info("-----------------Finish Test3-----------------\n");
	}

	/**
	 * 
	 * metoda de test afisare convert_pdf.xsd
	 * 
	 */
	@Test
	public void Test4() {
		logger.info("-----------------Test4-----------------");

		xsdFiles = xsdContainerTest.getXsdFiles();

		logger.info(xsdFiles.get(2).getFileName() + "\n");

		logger.info("SIMPLE TYPES\n");
		if (xsdFiles.get(2).getSimpleTypes() != null) {
			List<SimpleType> simpleType = xsdFiles.get(2).getSimpleTypes();
			for (int i = 0; i < simpleType.size(); i++)
				logger.info(simpleType.get(i).toString());
			logger.info("\n");
		} else
			logger.info("NO SIMPLE TYPES\n");

		logger.info("COMPLEX TYPES\n");
		if (xsdFiles.get(2).getComplexTypes() != null) {
			List<ComplexType> complexType = xsdFiles.get(2).getComplexTypes();
			for (int i = 0; i < complexType.size(); i++)
				logger.info(complexType.get(i).toString());
			logger.info("\n");
		} else
			logger.info("NO COMPLEX TYPES\n");

		logger.info("ELEMENT TYPES\n");
		if (xsdFiles.get(2).getElementType() != null) {
			ElementType elementType = xsdFiles.get(2).getElementType();
			logger.info(elementType.toString() + "\n");
		} else
			logger.info("NO ELEMENT TYPES\n");

		logger.info("GROUP TYPES\n");
		if (xsdFiles.get(2).getGroupTypes() != null) {
			List<GroupType> groupType = xsdFiles.get(2).getGroupTypes();
			for (int i = 0; i < groupType.size(); i++)
				logger.info(groupType.get(i).toString());
			logger.info("\n");
		} else
			logger.info("NO GROUP TYPES\n");

		logger.info("-----------------Finish Test4-----------------\n");
	}

	/**
	 * 
	 * metoda de test afisare crop.xsd
	 * 
	 */
	@Test
	public void Test5() {
		logger.info("-----------------Test5-----------------");

		xsdFiles = xsdContainerTest.getXsdFiles();

		logger.info(xsdFiles.get(3).getFileName() + "\n");

		logger.info("SIMPLE TYPES\n");
		if (xsdFiles.get(3).getSimpleTypes() != null) {
			List<SimpleType> simpleType = xsdFiles.get(3).getSimpleTypes();
			for (int i = 0; i < simpleType.size(); i++)
				logger.info(simpleType.get(i).toString());
			logger.info("\n");
		} else
			logger.info("NO SIMPLE TYPES\n");

		logger.info("COMPLEX TYPES\n");
		if (xsdFiles.get(3).getComplexTypes() != null) {
			List<ComplexType> complexType = xsdFiles.get(3).getComplexTypes();
			for (int i = 0; i < complexType.size(); i++)
				logger.info(complexType.get(i).toString());
			logger.info("\n");
		} else
			logger.info("NO COMPLEX TYPES\n");

		logger.info("ELEMENT TYPES\n");
		if (xsdFiles.get(3).getElementType() != null) {
			ElementType elementType = xsdFiles.get(3).getElementType();
			logger.info(elementType.toString() + "\n");
		} else
			logger.info("NO ELEMENT TYPES\n");

		logger.info("GROUP TYPES\n");
		if (xsdFiles.get(3).getGroupTypes() != null) {
			List<GroupType> groupType = xsdFiles.get(3).getGroupTypes();
			for (int i = 0; i < groupType.size(); i++)
				logger.info(groupType.get(i).toString());
			logger.info("\n");
		} else
			logger.info("NO GROUP TYPES\n");

		logger.info("-----------------Finish Test5-----------------\n");
	}

	/**
	 * 
	 * metoda de test afisare deskew.xsd
	 * 
	 */
	@Test
	public void Test6() {
		logger.info("-----------------Test6-----------------");

		xsdFiles = xsdContainerTest.getXsdFiles();

		logger.info(xsdFiles.get(4).getFileName() + "\n");

		logger.info("SIMPLE TYPES\n");
		if (xsdFiles.get(4).getSimpleTypes() != null) {
			List<SimpleType> simpleType = xsdFiles.get(4).getSimpleTypes();
			for (int i = 0; i < simpleType.size(); i++)
				logger.info(simpleType.get(i).toString());
			logger.info("\n");
		} else
			logger.info("NO SIMPLE TYPES\n");

		logger.info("COMPLEX TYPES\n");
		if (xsdFiles.get(4).getComplexTypes() != null) {
			List<ComplexType> complexType = xsdFiles.get(4).getComplexTypes();
			for (int i = 0; i < complexType.size(); i++)
				logger.info(complexType.get(i).toString());
			logger.info("\n");
		} else
			logger.info("NO COMPLEX TYPES\n");

		logger.info("ELEMENT TYPES\n");
		if (xsdFiles.get(4).getElementType() != null) {
			ElementType elementType = xsdFiles.get(4).getElementType();
			logger.info(elementType.toString() + "\n");
		} else
			logger.info("NO ELEMENT TYPES\n");

		logger.info("GROUP TYPES\n");
		if (xsdFiles.get(4).getGroupTypes() != null) {
			List<GroupType> groupType = xsdFiles.get(4).getGroupTypes();
			for (int i = 0; i < groupType.size(); i++)
				logger.info(groupType.get(i).toString());
			logger.info("\n");
		} else
			logger.info("NO GROUP TYPES\n");

		logger.info("-----------------Finish Test6-----------------\n");
	}

	/**
	 * 
	 * metoda de test afisare hierarchy_analysis.xsd
	 * 
	 */
	@Test
	public void Test7() {
		logger.info("-----------------Test7-----------------");

		xsdFiles = xsdContainerTest.getXsdFiles();

		logger.info(xsdFiles.get(5).getFileName() + "\n");

		logger.info("SIMPLE TYPES\n");
		if (xsdFiles.get(5).getSimpleTypes() != null) {
			List<SimpleType> simpleType = xsdFiles.get(5).getSimpleTypes();
			for (int i = 0; i < simpleType.size(); i++)
				logger.info(simpleType.get(i).toString());
			logger.info("\n");
		} else
			logger.info("NO SIMPLE TYPES\n");

		logger.info("COMPLEX TYPES\n");
		if (xsdFiles.get(5).getComplexTypes() != null) {
			List<ComplexType> complexType = xsdFiles.get(5).getComplexTypes();
			for (int i = 0; i < complexType.size(); i++)
				logger.info(complexType.get(i).toString());
			logger.info("\n");
		} else
			logger.info("NO COMPLEX TYPES\n");

		logger.info("ELEMENT TYPES\n");
		if (xsdFiles.get(5).getElementType() != null) {
			ElementType elementType = xsdFiles.get(5).getElementType();
			logger.info(elementType.toString() + "\n");
		} else
			logger.info("NO ELEMENT TYPES\n");

		logger.info("GROUP TYPES\n");
		if (xsdFiles.get(5).getGroupTypes() != null) {
			List<GroupType> groupType = xsdFiles.get(5).getGroupTypes();
			for (int i = 0; i < groupType.size(); i++)
				logger.info(groupType.get(i).toString());
			logger.info("\n");
		} else
			logger.info("NO GROUP TYPES\n");

		logger.info("-----------------Finish Test7-----------------\n");
	}

	/**
	 * 
	 * metoda de test afisare layout_analysis.xsd
	 * 
	 */
	@Test
	public void Test8() {
		logger.info("-----------------Test8-----------------");

		xsdFiles = xsdContainerTest.getXsdFiles();

		logger.info(xsdFiles.get(6).getFileName() + "\n");

		logger.info("SIMPLE TYPES\n");
		if (xsdFiles.get(6).getSimpleTypes() != null) {
			List<SimpleType> simpleType = xsdFiles.get(6).getSimpleTypes();
			for (int i = 0; i < simpleType.size(); i++)
				logger.info(simpleType.get(i).toString());
			logger.info("\n");
		} else
			logger.info("NO SIMPLE TYPES\n");

		logger.info("COMPLEX TYPES\n");
		if (xsdFiles.get(6).getComplexTypes() != null) {
			List<ComplexType> complexType = xsdFiles.get(6).getComplexTypes();
			for (int i = 0; i < complexType.size(); i++)
				logger.info(complexType.get(i).toString());
			logger.info("\n");
		} else
			logger.info("NO COMPLEX TYPES\n");

		logger.info("ELEMENT TYPES\n");
		if (xsdFiles.get(6).getElementType() != null) {
			ElementType elementType = xsdFiles.get(6).getElementType();
			logger.info(elementType.toString() + "\n");
		} else
			logger.info("NO ELEMENT TYPES\n");

		logger.info("GROUP TYPES\n");
		if (xsdFiles.get(6).getGroupTypes() != null) {
			List<GroupType> groupType = xsdFiles.get(6).getGroupTypes();
			for (int i = 0; i < groupType.size(); i++)
				logger.info(groupType.get(i).toString());
			logger.info("\n");
		} else
			logger.info("NO GROUP TYPES\n");

		logger.info("-----------------Finish Test8-----------------\n");
	}

	/**
	 * 
	 * metoda de test afisare otsu.xsd
	 * 
	 */
	@Test
	public void Test9() {
		logger.info("-----------------Test9-----------------");

		xsdFiles = xsdContainerTest.getXsdFiles();

		logger.info(xsdFiles.get(7).getFileName() + "\n");

		logger.info("SIMPLE TYPES\n");
		if (xsdFiles.get(7).getSimpleTypes() != null) {
			List<SimpleType> simpleType = xsdFiles.get(7).getSimpleTypes();
			for (int i = 0; i < simpleType.size(); i++)
				logger.info(simpleType.get(i).toString());
			logger.info("\n");
		} else
			logger.info("NO SIMPLE TYPES\n");

		logger.info("COMPLEX TYPES\n");
		if (xsdFiles.get(7).getComplexTypes() != null) {
			List<ComplexType> complexType = xsdFiles.get(7).getComplexTypes();
			for (int i = 0; i < complexType.size(); i++)
				logger.info(complexType.get(i).toString());
			logger.info("\n");
		} else
			logger.info("NO COMPLEX TYPES\n");

		logger.info("ELEMENT TYPES\n");
		if (xsdFiles.get(7).getElementType() != null) {
			ElementType elementType = xsdFiles.get(7).getElementType();
			logger.info(elementType.toString() + "\n");
		} else
			logger.info("NO ELEMENT TYPES\n");

		logger.info("GROUP TYPES\n");
		if (xsdFiles.get(7).getGroupTypes() != null) {
			List<GroupType> groupType = xsdFiles.get(7).getGroupTypes();
			for (int i = 0; i < groupType.size(); i++)
				logger.info(groupType.get(i).toString());
			logger.info("\n");
		} else
			logger.info("NO GROUP TYPES\n");

		logger.info("-----------------Finish Test9-----------------\n");
	}

	/**
	 * 
	 * metoda de test afisare rotate.xsd
	 * 
	 */
	@Test
	public void Test10() {
		logger.info("-----------------Test10-----------------");

		xsdFiles = xsdContainerTest.getXsdFiles();

		logger.info(xsdFiles.get(8).getFileName() + "\n");

		logger.info("SIMPLE TYPES\n");
		if (xsdFiles.get(8).getSimpleTypes() != null) {
			List<SimpleType> simpleType = xsdFiles.get(8).getSimpleTypes();
			for (int i = 0; i < simpleType.size(); i++)
				logger.info(simpleType.get(i).toString());
			logger.info("\n");
		} else
			logger.info("NO SIMPLE TYPES\n");

		logger.info("COMPLEX TYPES\n");
		if (xsdFiles.get(8).getComplexTypes() != null) {
			List<ComplexType> complexType = xsdFiles.get(8).getComplexTypes();
			for (int i = 0; i < complexType.size(); i++)
				logger.info(complexType.get(i).toString());
			logger.info("\n");
		} else
			logger.info("NO COMPLEX TYPES\n");

		logger.info("ELEMENT TYPES\n");
		if (xsdFiles.get(8).getElementType() != null) {
			ElementType elementType = xsdFiles.get(8).getElementType();
			logger.info(elementType.toString() + "\n");
		} else
			logger.info("NO ELEMENT TYPES\n");

		logger.info("GROUP TYPES\n");
		if (xsdFiles.get(8).getGroupTypes() != null) {
			List<GroupType> groupType = xsdFiles.get(8).getGroupTypes();
			for (int i = 0; i < groupType.size(); i++)
				logger.info(groupType.get(i).toString());
			logger.info("\n");
		} else
			logger.info("NO GROUP TYPES\n");

		logger.info("-----------------Finish Test10-----------------\n");
	}

	/**
	 * 
	 * metoda de test afisare tesseract.xsd
	 * 
	 */
	@Test
	public void Test11() {
		logger.info("-----------------Test11-----------------");

		xsdFiles = xsdContainerTest.getXsdFiles();

		logger.info(xsdFiles.get(9).getFileName() + "\n");

		logger.info("SIMPLE TYPES\n");
		if (xsdFiles.get(9).getSimpleTypes() != null) {
			List<SimpleType> simpleType = xsdFiles.get(9).getSimpleTypes();
			for (int i = 0; i < simpleType.size(); i++)
				logger.info(simpleType.get(i).toString());
			logger.info("\n");
		} else
			logger.info("NO SIMPLE TYPES\n");

		logger.info("COMPLEX TYPES\n");
		if (xsdFiles.get(9).getComplexTypes() != null) {
			List<ComplexType> complexType = xsdFiles.get(9).getComplexTypes();
			for (int i = 0; i < complexType.size(); i++)
				logger.info(complexType.get(i).toString());
			logger.info("\n");
		} else
			logger.info("NO COMPLEX TYPES\n");

		logger.info("ELEMENT TYPES\n");
		if (xsdFiles.get(9).getElementType() != null) {
			ElementType elementType = xsdFiles.get(9).getElementType();
			logger.info(elementType.toString() + "\n");
		} else
			logger.info("NO ELEMENT TYPES\n");

		logger.info("GROUP TYPES\n");
		if (xsdFiles.get(9).getGroupTypes() != null) {
			List<GroupType> groupType = xsdFiles.get(9).getGroupTypes();
			for (int i = 0; i < groupType.size(); i++)
				logger.info(groupType.get(i).toString());
			logger.info("\n");
		} else
			logger.info("NO GROUP TYPES\n");

		logger.info("-----------------Finish Test11-----------------\n");
	}

	/**
	 * 
	 * metoda de test afisare bad1.xsd
	 * 
	 */
	/*
	 * @Test public void Test24() {
	 * logger.info("-----------------Test24-----------------");
	 * 
	 * xsdFiles = xsdContainerTest.getXsdFiles();
	 * 
	 * logger.info(xsdFiles.get(10).getFileName() + "\n");
	 * 
	 * logger.info("SIMPLE TYPES\n"); if (xsdFiles.get(10).getSimpleTypes() !=
	 * null) { List<SimpleType> simpleType = xsdFiles.get(10).getSimpleTypes();
	 * for (int i = 0; i < simpleType.size(); i++)
	 * logger.info(simpleType.get(i).toString()); logger.info("\n"); } else
	 * logger.info("NO SIMPLE TYPES\n");
	 * 
	 * logger.info("COMPLEX TYPES\n"); if (xsdFiles.get(10).getComplexTypes() !=
	 * null) { List<ComplexType> complexType =
	 * xsdFiles.get(10).getComplexTypes(); for (int i = 0; i <
	 * complexType.size(); i++) logger.info(complexType.get(i).toString());
	 * logger.info("\n"); } else logger.info("NO COMPLEX TYPES\n");
	 * 
	 * logger.info("ELEMENT TYPES\n"); if (xsdFiles.get(10).getElementType() !=
	 * null) { ElementType elementType = xsdFiles.get(10).getElementType();
	 * logger.info(elementType.toString() + "\n"); } else
	 * logger.info("NO ELEMENT TYPES\n");
	 * 
	 * logger.info("GROUP TYPES\n"); if (xsdFiles.get(10).getGroupTypes() !=
	 * null) { List<GroupType> groupType = xsdFiles.get(10).getGroupTypes(); for
	 * (int i = 0; i < groupType.size(); i++)
	 * logger.info(groupType.get(i).toString()); logger.info("\n"); } else
	 * logger.info("NO GROUP TYPES\n");
	 * 
	 * logger.info("-----------------Finish Test24-----------------\n"); }
	 */

	/**
	 * 
	 * metoda de test afisare bad2.xsd
	 * 
	 */
	/*
	 * @Test public void Test25() {
	 * logger.info("-----------------Test25-----------------");
	 * 
	 * xsdFiles = xsdContainerTest.getXsdFiles();
	 * 
	 * logger.info(xsdFiles.get(11).getFileName() + "\n");
	 * 
	 * logger.info("SIMPLE TYPES\n"); if (xsdFiles.get(11).getSimpleTypes() !=
	 * null) { List<SimpleType> simpleType = xsdFiles.get(11).getSimpleTypes();
	 * for (int i = 0; i < simpleType.size(); i++)
	 * logger.info(simpleType.get(i).toString()); logger.info("\n"); } else
	 * logger.info("NO SIMPLE TYPES\n");
	 * 
	 * logger.info("COMPLEX TYPES\n"); if (xsdFiles.get(11).getComplexTypes() !=
	 * null) { List<ComplexType> complexType =
	 * xsdFiles.get(11).getComplexTypes(); for (int i = 0; i <
	 * complexType.size(); i++) logger.info(complexType.get(i).toString());
	 * logger.info("\n"); } else logger.info("NO COMPLEX TYPES\n");
	 * 
	 * logger.info("ELEMENT TYPES\n"); if (xsdFiles.get(11).getElementType() !=
	 * null) { ElementType elementType = xsdFiles.get(11).getElementType();
	 * logger.info(elementType.toString() + "\n"); } else
	 * logger.info("NO ELEMENT TYPES\n");
	 * 
	 * logger.info("GROUP TYPES\n"); if (xsdFiles.get(11).getGroupTypes() !=
	 * null) { List<GroupType> groupType = xsdFiles.get(11).getGroupTypes(); for
	 * (int i = 0; i < groupType.size(); i++)
	 * logger.info(groupType.get(i).toString()); logger.info("\n"); } else
	 * logger.info("NO GROUP TYPES\n");
	 * 
	 * logger.info("-----------------Finish Test25-----------------\n"); }
	 */

	/**
	 * 
	 * metoda de test afisare bad3.xsd
	 * 
	 */
	/*
	 * @Test public void Test26() {
	 * logger.info("-----------------Test26-----------------");
	 * 
	 * xsdFiles = xsdContainerTest.getXsdFiles();
	 * 
	 * logger.info(xsdFiles.get(12).getFileName() + "\n");
	 * 
	 * logger.info("SIMPLE TYPES\n"); if (xsdFiles.get(12).getSimpleTypes() !=
	 * null) { List<SimpleType> simpleType = xsdFiles.get(12).getSimpleTypes();
	 * for (int i = 0; i < simpleType.size(); i++)
	 * logger.info(simpleType.get(i).toString()); logger.info("\n"); } else
	 * logger.info("NO SIMPLE TYPES\n");
	 * 
	 * logger.info("COMPLEX TYPES\n"); if (xsdFiles.get(12).getComplexTypes() !=
	 * null) { List<ComplexType> complexType =
	 * xsdFiles.get(12).getComplexTypes(); for (int i = 0; i <
	 * complexType.size(); i++) logger.info(complexType.get(i).toString());
	 * logger.info("\n"); } else logger.info("NO COMPLEX TYPES\n");
	 * 
	 * logger.info("ELEMENT TYPES\n"); if (xsdFiles.get(12).getElementType() !=
	 * null) { ElementType elementType = xsdFiles.get(12).getElementType();
	 * logger.info(elementType.toString() + "\n"); } else
	 * logger.info("NO ELEMENT TYPES\n");
	 * 
	 * logger.info("GROUP TYPES\n"); if (xsdFiles.get(12).getGroupTypes() !=
	 * null) { List<GroupType> groupType = xsdFiles.get(12).getGroupTypes(); for
	 * (int i = 0; i < groupType.size(); i++)
	 * logger.info(groupType.get(i).toString()); logger.info("\n"); } else
	 * logger.info("NO GROUP TYPES\n");
	 * 
	 * logger.info("-----------------Finish Test26-----------------\n"); }
	 */

	/**
	 * 
	 * metoda de test afisare bad4.xsd
	 * 
	 */
	/*
	 * @Test public void Test27() {
	 * logger.info("-----------------Test27-----------------");
	 * 
	 * xsdFiles = xsdContainerTest.getXsdFiles();
	 * 
	 * logger.info(xsdFiles.get(13).getFileName() + "\n");
	 * 
	 * logger.info("SIMPLE TYPES\n"); if (xsdFiles.get(13).getSimpleTypes() !=
	 * null) { List<SimpleType> simpleType = xsdFiles.get(13).getSimpleTypes();
	 * for (int i = 0; i < simpleType.size(); i++)
	 * logger.info(simpleType.get(i).toString()); logger.info("\n"); } else
	 * logger.info("NO SIMPLE TYPES\n");
	 * 
	 * logger.info("COMPLEX TYPES\n"); if (xsdFiles.get(13).getComplexTypes() !=
	 * null) { List<ComplexType> complexType =
	 * xsdFiles.get(13).getComplexTypes(); for (int i = 0; i <
	 * complexType.size(); i++) logger.info(complexType.get(i).toString());
	 * logger.info("\n"); } else logger.info("NO COMPLEX TYPES\n");
	 * 
	 * logger.info("ELEMENT TYPES\n"); if (xsdFiles.get(13).getElementType() !=
	 * null) { ElementType elementType = xsdFiles.get(13).getElementType();
	 * logger.info(elementType.toString() + "\n"); } else
	 * logger.info("NO ELEMENT TYPES\n");
	 * 
	 * logger.info("GROUP TYPES\n"); if (xsdFiles.get(13).getGroupTypes() !=
	 * null) { List<GroupType> groupType = xsdFiles.get(13).getGroupTypes(); for
	 * (int i = 0; i < groupType.size(); i++)
	 * logger.info(groupType.get(i).toString()); logger.info("\n"); } else
	 * logger.info("NO GROUP TYPES\n");
	 * 
	 * logger.info("-----------------Finish Test27-----------------\n"); }
	 */

	/**
	 * 
	 * metoda de test afisare bad5.xsd
	 * 
	 */
	/*
	 * @Test public void Test28() {
	 * logger.info("-----------------Test28-----------------");
	 * 
	 * xsdFiles = xsdContainerTest.getXsdFiles();
	 * 
	 * logger.info(xsdFiles.get(14).getFileName() + "\n");
	 * 
	 * logger.info("SIMPLE TYPES\n"); if (xsdFiles.get(14).getSimpleTypes() !=
	 * null) { List<SimpleType> simpleType = xsdFiles.get(14).getSimpleTypes();
	 * for (int i = 0; i < simpleType.size(); i++)
	 * logger.info(simpleType.get(i).toString()); logger.info("\n"); } else
	 * logger.info("NO SIMPLE TYPES\n");
	 * 
	 * logger.info("COMPLEX TYPES\n"); if (xsdFiles.get(14).getComplexTypes() !=
	 * null) { List<ComplexType> complexType =
	 * xsdFiles.get(14).getComplexTypes(); for (int i = 0; i <
	 * complexType.size(); i++) logger.info(complexType.get(i).toString());
	 * logger.info("\n"); } else logger.info("NO COMPLEX TYPES\n");
	 * 
	 * logger.info("ELEMENT TYPES\n"); if (xsdFiles.get(14).getElementType() !=
	 * null) { ElementType elementType = xsdFiles.get(14).getElementType();
	 * logger.info(elementType.toString() + "\n"); } else
	 * logger.info("NO ELEMENT TYPES\n");
	 * 
	 * logger.info("GROUP TYPES\n"); if (xsdFiles.get(14).getGroupTypes() !=
	 * null) { List<GroupType> groupType = xsdFiles.get(14).getGroupTypes(); for
	 * (int i = 0; i < groupType.size(); i++)
	 * logger.info(groupType.get(i).toString()); logger.info("\n"); } else
	 * logger.info("NO GROUP TYPES\n");
	 * 
	 * logger.info("-----------------Finish Test28-----------------\n"); }
	 */

	/**
	 * 
	 * metoda de test afisare bad6.xsd
	 * 
	 */
	/*
	 * @Test public void Test29() {
	 * logger.info("-----------------Test29-----------------");
	 * 
	 * xsdFiles = xsdContainerTest.getXsdFiles();
	 * 
	 * logger.info(xsdFiles.get(15).getFileName() + "\n");
	 * 
	 * logger.info("SIMPLE TYPES\n"); if (xsdFiles.get(15).getSimpleTypes() !=
	 * null) { List<SimpleType> simpleType = xsdFiles.get(15).getSimpleTypes();
	 * for (int i = 0; i < simpleType.size(); i++)
	 * logger.info(simpleType.get(i).toString()); logger.info("\n"); } else
	 * logger.info("NO SIMPLE TYPES\n");
	 * 
	 * logger.info("COMPLEX TYPES\n"); if (xsdFiles.get(15).getComplexTypes() !=
	 * null) { List<ComplexType> complexType =
	 * xsdFiles.get(15).getComplexTypes(); for (int i = 0; i <
	 * complexType.size(); i++) logger.info(complexType.get(i).toString());
	 * logger.info("\n"); } else logger.info("NO COMPLEX TYPES\n");
	 * 
	 * logger.info("ELEMENT TYPES\n"); if (xsdFiles.get(15).getElementType() !=
	 * null) { ElementType elementType = xsdFiles.get(15).getElementType();
	 * logger.info(elementType.toString() + "\n"); } else
	 * logger.info("NO ELEMENT TYPES\n");
	 * 
	 * logger.info("GROUP TYPES\n"); if (xsdFiles.get(15).getGroupTypes() !=
	 * null) { List<GroupType> groupType = xsdFiles.get(15).getGroupTypes(); for
	 * (int i = 0; i < groupType.size(); i++)
	 * logger.info(groupType.get(i).toString()); logger.info("\n"); } else
	 * logger.info("NO GROUP TYPES\n");
	 * 
	 * logger.info("-----------------Finish Test29-----------------\n"); }
	 */

	/**
	 * 
	 * metoda de test afisare bad7.xsd
	 * 
	 */
	/*
	 * @Test public void Test30() {
	 * logger.info("-----------------Test30-----------------");
	 * 
	 * xsdFiles = xsdContainerTest.getXsdFiles();
	 * 
	 * logger.info(xsdFiles.get(16).getFileName() + "\n");
	 * 
	 * logger.info("SIMPLE TYPES\n"); if (xsdFiles.get(16).getSimpleTypes() !=
	 * null) { List<SimpleType> simpleType = xsdFiles.get(16).getSimpleTypes();
	 * for (int i = 0; i < simpleType.size(); i++)
	 * logger.info(simpleType.get(i).toString()); logger.info("\n"); } else
	 * logger.info("NO SIMPLE TYPES\n");
	 * 
	 * logger.info("COMPLEX TYPES\n"); if (xsdFiles.get(16).getComplexTypes() !=
	 * null) { List<ComplexType> complexType =
	 * xsdFiles.get(16).getComplexTypes(); for (int i = 0; i <
	 * complexType.size(); i++) logger.info(complexType.get(i).toString());
	 * logger.info("\n"); } else logger.info("NO COMPLEX TYPES\n");
	 * 
	 * logger.info("ELEMENT TYPES\n"); if (xsdFiles.get(16).getElementType() !=
	 * null) { ElementType elementType = xsdFiles.get(16).getElementType();
	 * logger.info(elementType.toString() + "\n"); } else
	 * logger.info("NO ELEMENT TYPES\n");
	 * 
	 * logger.info("GROUP TYPES\n"); if (xsdFiles.get(16).getGroupTypes() !=
	 * null) { List<GroupType> groupType = xsdFiles.get(16).getGroupTypes(); for
	 * (int i = 0; i < groupType.size(); i++)
	 * logger.info(groupType.get(i).toString()); logger.info("\n"); } else
	 * logger.info("NO GROUP TYPES\n");
	 * 
	 * logger.info("-----------------Finish Test30-----------------\n"); }
	 */

	/**
	 * 
	 * metoda de test afisare bad8.xsd
	 * 
	 */
	/*
	 * @Test public void Test31() {
	 * logger.info("-----------------Test31-----------------");
	 * 
	 * xsdFiles = xsdContainerTest.getXsdFiles();
	 * 
	 * logger.info(xsdFiles.get(17).getFileName() + "\n");
	 * 
	 * logger.info("SIMPLE TYPES\n"); if (xsdFiles.get(17).getSimpleTypes() !=
	 * null) { List<SimpleType> simpleType = xsdFiles.get(17).getSimpleTypes();
	 * for (int i = 0; i < simpleType.size(); i++)
	 * logger.info(simpleType.get(i).toString()); logger.info("\n"); } else
	 * logger.info("NO SIMPLE TYPES\n");
	 * 
	 * logger.info("COMPLEX TYPES\n"); if (xsdFiles.get(17).getComplexTypes() !=
	 * null) { List<ComplexType> complexType =
	 * xsdFiles.get(17).getComplexTypes(); for (int i = 0; i <
	 * complexType.size(); i++) logger.info(complexType.get(i).toString());
	 * logger.info("\n"); } else logger.info("NO COMPLEX TYPES\n");
	 * 
	 * logger.info("ELEMENT TYPES\n"); if (xsdFiles.get(17).getElementType() !=
	 * null) { ElementType elementType = xsdFiles.get(17).getElementType();
	 * logger.info(elementType.toString() + "\n"); } else
	 * logger.info("NO ELEMENT TYPES\n");
	 * 
	 * logger.info("GROUP TYPES\n"); if (xsdFiles.get(17).getGroupTypes() !=
	 * null) { List<GroupType> groupType = xsdFiles.get(17).getGroupTypes(); for
	 * (int i = 0; i < groupType.size(); i++)
	 * logger.info(groupType.get(i).toString()); logger.info("\n"); } else
	 * logger.info("NO GROUP TYPES\n");
	 * 
	 * logger.info("-----------------Finish Test31-----------------\n"); }
	 */

	/**
	 * 
	 * metoda de test afisare bad9.xsd
	 * 
	 */
	/*
	 * @Test public void Test32() {
	 * logger.info("-----------------Test32-----------------");
	 * 
	 * xsdFiles = xsdContainerTest.getXsdFiles();
	 * 
	 * logger.info(xsdFiles.get(18).getFileName() + "\n");
	 * 
	 * logger.info("SIMPLE TYPES\n"); if (xsdFiles.get(18).getSimpleTypes() !=
	 * null) { List<SimpleType> simpleType = xsdFiles.get(18).getSimpleTypes();
	 * for (int i = 0; i < simpleType.size(); i++)
	 * logger.info(simpleType.get(i).toString()); logger.info("\n"); } else
	 * logger.info("NO SIMPLE TYPES\n");
	 * 
	 * logger.info("COMPLEX TYPES\n"); if (xsdFiles.get(18).getComplexTypes() !=
	 * null) { List<ComplexType> complexType =
	 * xsdFiles.get(18).getComplexTypes(); for (int i = 0; i <
	 * complexType.size(); i++) logger.info(complexType.get(i).toString());
	 * logger.info("\n"); } else logger.info("NO COMPLEX TYPES\n");
	 * 
	 * logger.info("ELEMENT TYPES\n"); if (xsdFiles.get(18).getElementType() !=
	 * null) { ElementType elementType = xsdFiles.get(18).getElementType();
	 * logger.info(elementType.toString() + "\n"); } else
	 * logger.info("NO ELEMENT TYPES\n");
	 * 
	 * logger.info("GROUP TYPES\n"); if (xsdFiles.get(18).getGroupTypes() !=
	 * null) { List<GroupType> groupType = xsdFiles.get(18).getGroupTypes(); for
	 * (int i = 0; i < groupType.size(); i++)
	 * logger.info(groupType.get(i).toString()); logger.info("\n"); } else
	 * logger.info("NO GROUP TYPES\n");
	 * 
	 * logger.info("-----------------Finish Test32-----------------\n"); }
	 */

	/**
	 * 
	 * metoda de test afisare bad10.xsd
	 * 
	 */
	/*
	 * @Test public void Test33() {
	 * logger.info("-----------------Test33-----------------");
	 * 
	 * xsdFiles = xsdContainerTest.getXsdFiles();
	 * 
	 * logger.info(xsdFiles.get(19).getFileName() + "\n");
	 * 
	 * logger.info("SIMPLE TYPES\n"); if (xsdFiles.get(19).getSimpleTypes() !=
	 * null) { List<SimpleType> simpleType = xsdFiles.get(19).getSimpleTypes();
	 * for (int i = 0; i < simpleType.size(); i++)
	 * logger.info(simpleType.get(i).toString()); logger.info("\n"); } else
	 * logger.info("NO SIMPLE TYPES\n");
	 * 
	 * logger.info("COMPLEX TYPES\n"); if (xsdFiles.get(19).getComplexTypes() !=
	 * null) { List<ComplexType> complexType =
	 * xsdFiles.get(19).getComplexTypes(); for (int i = 0; i <
	 * complexType.size(); i++) logger.info(complexType.get(i).toString());
	 * logger.info("\n"); } else logger.info("NO COMPLEX TYPES\n");
	 * 
	 * logger.info("ELEMENT TYPES\n"); if (xsdFiles.get(19).getElementType() !=
	 * null) { ElementType elementType = xsdFiles.get(19).getElementType();
	 * logger.info(elementType.toString() + "\n"); } else
	 * logger.info("NO ELEMENT TYPES\n");
	 * 
	 * logger.info("GROUP TYPES\n"); if (xsdFiles.get(19).getGroupTypes() !=
	 * null) { List<GroupType> groupType = xsdFiles.get(19).getGroupTypes(); for
	 * (int i = 0; i < groupType.size(); i++)
	 * logger.info(groupType.get(i).toString()); logger.info("\n"); } else
	 * logger.info("NO GROUP TYPES\n");
	 * 
	 * logger.info("-----------------Finish Test33-----------------\n"); }
	 */

	/**
	 * 
	 * metoda de test afisare bad11.xsd
	 * 
	 */
	/*
	 * @Test public void Test34() {
	 * logger.info("-----------------Test34-----------------");
	 * 
	 * xsdFiles = xsdContainerTest.getXsdFiles();
	 * 
	 * logger.info(xsdFiles.get(20).getFileName() + "\n");
	 * 
	 * logger.info("SIMPLE TYPES\n"); if (xsdFiles.get(20).getSimpleTypes() !=
	 * null) { List<SimpleType> simpleType = xsdFiles.get(20).getSimpleTypes();
	 * for (int i = 0; i < simpleType.size(); i++)
	 * logger.info(simpleType.get(i).toString()); logger.info("\n"); } else
	 * logger.info("NO SIMPLE TYPES\n");
	 * 
	 * logger.info("COMPLEX TYPES\n"); if (xsdFiles.get(20).getComplexTypes() !=
	 * null) { List<ComplexType> complexType =
	 * xsdFiles.get(20).getComplexTypes(); for (int i = 0; i <
	 * complexType.size(); i++) logger.info(complexType.get(i).toString());
	 * logger.info("\n"); } else logger.info("NO COMPLEX TYPES\n");
	 * 
	 * logger.info("ELEMENT TYPES\n"); if (xsdFiles.get(20).getElementType() !=
	 * null) { ElementType elementType = xsdFiles.get(20).getElementType();
	 * logger.info(elementType.toString() + "\n"); } else
	 * logger.info("NO ELEMENT TYPES\n");
	 * 
	 * logger.info("GROUP TYPES\n"); if (xsdFiles.get(20).getGroupTypes() !=
	 * null) { List<GroupType> groupType = xsdFiles.get(20).getGroupTypes(); for
	 * (int i = 0; i < groupType.size(); i++)
	 * logger.info(groupType.get(i).toString()); logger.info("\n"); } else
	 * logger.info("NO GROUP TYPES\n");
	 * 
	 * logger.info("-----------------Finish Test34-----------------\n"); }
	 */

	/**
	 * 
	 * metoda de test afisare bad12.xsd
	 * 
	 */
	/*
	 * @Test public void Test35() {
	 * logger.info("-----------------Test35-----------------");
	 * 
	 * xsdFiles = xsdContainerTest.getXsdFiles();
	 * 
	 * logger.info(xsdFiles.get(21).getFileName() + "\n");
	 * 
	 * logger.info("SIMPLE TYPES\n"); if (xsdFiles.get(21).getSimpleTypes() !=
	 * null) { List<SimpleType> simpleType = xsdFiles.get(21).getSimpleTypes();
	 * for (int i = 0; i < simpleType.size(); i++)
	 * logger.info(simpleType.get(i).toString()); logger.info("\n"); } else
	 * logger.info("NO SIMPLE TYPES\n");
	 * 
	 * logger.info("COMPLEX TYPES\n"); if (xsdFiles.get(21).getComplexTypes() !=
	 * null) { List<ComplexType> complexType =
	 * xsdFiles.get(21).getComplexTypes(); for (int i = 0; i <
	 * complexType.size(); i++) logger.info(complexType.get(i).toString());
	 * logger.info("\n"); } else logger.info("NO COMPLEX TYPES\n");
	 * 
	 * logger.info("ELEMENT TYPES\n"); if (xsdFiles.get(21).getElementType() !=
	 * null) { ElementType elementType = xsdFiles.get(21).getElementType();
	 * logger.info(elementType.toString() + "\n"); } else
	 * logger.info("NO ELEMENT TYPES\n");
	 * 
	 * logger.info("GROUP TYPES\n"); if (xsdFiles.get(21).getGroupTypes() !=
	 * null) { List<GroupType> groupType = xsdFiles.get(21).getGroupTypes(); for
	 * (int i = 0; i < groupType.size(); i++)
	 * logger.info(groupType.get(i).toString()); logger.info("\n"); } else
	 * logger.info("NO GROUP TYPES\n");
	 * 
	 * logger.info("-----------------Finish Test35-----------------\n"); }
	 */
}