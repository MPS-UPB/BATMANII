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
import com.mps.batmanii.ocrWebManager.beans.GroupType;
import com.mps.batmanii.ocrWebManager.beans.PropertyHolder;
import com.mps.batmanii.ocrWebManager.beans.SimpleType;
import com.mps.batmanii.ocrWebManager.beans.XsdContainer;
import com.mps.batmanii.ocrWebManager.beans.XsdFile;
import com.mps.batmanii.ocrWebManager.business.ParserXsd;

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

		/* clasa care continele fisierele xsd parsate */
		xsdContainerTest = new XsdContainer();
		List<XsdFile> xsdFiles = xsdContainerTest.getXsdFiles();
		File folder = new File(propertyHolderTest.getInputSchemasFolder());
		
		for (File fileEntry : folder.listFiles()) {
			//logger.info(fileEntry.getAbsolutePath());
			ParserXsd parserXsd = new ParserXsd();
			XsdFile xsdFile = null;
			try {
				xsdFile = parserXsd.parse(fileEntry.getAbsolutePath());
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			xsdFiles.add(xsdFile);
		}

		logger.info("-----------------Finish Before Test-----------------");

	}

	/* metoda de test care afiseaza lungimea listei de scheme parsate */
	@Test
	public void Test1() {
		logger.info("-----------------Test1-----------------");
		logger.info(xsdContainerTest.getXsdFiles().size() + "" + "" + "\n");		
		logger.info("-----------------Finish Test1-----------------\n");
	}
	
	/* metode de test afisari fisiere Xsd parsate */
	
	/* metoda de test afisare adjust brightness.xsd */
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
		}
		else
			logger.info("NO SIMPLE TYPES\n");
		
		logger.info("COMPLEX TYPES\n");
		if (xsdFiles.get(0).getComplexTypes() != null) {
			List<ComplexType> complexType = xsdFiles.get(0).getComplexTypes();
			for (int i = 0; i < complexType.size(); i++)
				logger.info(complexType.get(i).toString());
			logger.info("\n");
		}
		else
			logger.info("NO COMPLEX TYPES\n");
		
		logger.info("ELEMENT TYPES\n");
		if (xsdFiles.get(0).getElementType() != null) {
			ElementType elementType = xsdFiles.get(0).getElementType();
			logger.info(elementType.toString() + "\n");
		}
		else
			logger.info("NO ELEMENT TYPES\n");
		
		logger.info("GROUP TYPES\n");
		if (xsdFiles.get(0).getGroupTypes() != null) {
			List<GroupType> groupType = xsdFiles.get(0).getGroupTypes();
			for (int i = 0; i < groupType.size(); i++)
				logger.info(groupType.get(i).toString());
			logger.info("\n");
		}
		else
			logger.info("NO GROUP TYPES\n");
		
		logger.info("-----------------Finish Test2-----------------\n");
	}
	
	/* metoda de test afisare adjust contrast.xsd */
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
		}
		else
			logger.info("NO SIMPLE TYPES\n");
		
		logger.info("COMPLEX TYPES\n");
		if (xsdFiles.get(1).getComplexTypes() != null) {
			List<ComplexType> complexType = xsdFiles.get(1).getComplexTypes();
			for (int i = 0; i < complexType.size(); i++)
				logger.info(complexType.get(i).toString());
			logger.info("\n");
		}
		else
			logger.info("NO COMPLEX TYPES\n");
		
		logger.info("ELEMENT TYPES\n");
		if (xsdFiles.get(1).getElementType() != null) {
			ElementType elementType = xsdFiles.get(1).getElementType();
			logger.info(elementType.toString() + "\n");
		}
		else
			logger.info("NO ELEMENT TYPES\n");
		
		logger.info("GROUP TYPES\n");
		if (xsdFiles.get(1).getGroupTypes() != null) {
			List<GroupType> groupType = xsdFiles.get(1).getGroupTypes();
			for (int i = 0; i < groupType.size(); i++)
				logger.info(groupType.get(i).toString());
			logger.info("\n");
		}
		else
			logger.info("NO GROUP TYPES\n");
		
		logger.info("-----------------Finish Test3-----------------\n");
	}
	
	/* metoda de test afisare convert_pdf.xsd */
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
		}
		else
			logger.info("NO SIMPLE TYPES\n");
		
		logger.info("COMPLEX TYPES\n");
		if (xsdFiles.get(2).getComplexTypes() != null) {
			List<ComplexType> complexType = xsdFiles.get(2).getComplexTypes();
			for (int i = 0; i < complexType.size(); i++)
				logger.info(complexType.get(i).toString());
			logger.info("\n");
		}
		else
			logger.info("NO COMPLEX TYPES\n");
		
		logger.info("ELEMENT TYPES\n");
		if (xsdFiles.get(2).getElementType() != null) {
			ElementType elementType = xsdFiles.get(2).getElementType();
			logger.info(elementType.toString() + "\n");
		}
		else
			logger.info("NO ELEMENT TYPES\n");
		
		logger.info("GROUP TYPES\n");
		if (xsdFiles.get(2).getGroupTypes() != null) {
			List<GroupType> groupType = xsdFiles.get(2).getGroupTypes();
			for (int i = 0; i < groupType.size(); i++)
				logger.info(groupType.get(i).toString());
			logger.info("\n");
		}
		else
			logger.info("NO GROUP TYPES\n");
		
		logger.info("-----------------Finish Test4-----------------\n");
	}
	
	/* metoda de test afisare crop.xsd */
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
		}
		else
			logger.info("NO SIMPLE TYPES\n");
		
		logger.info("COMPLEX TYPES\n");
		if (xsdFiles.get(3).getComplexTypes() != null) {
			List<ComplexType> complexType = xsdFiles.get(3).getComplexTypes();
			for (int i = 0; i < complexType.size(); i++)
				logger.info(complexType.get(i).toString());
			logger.info("\n");
		}
		else
			logger.info("NO COMPLEX TYPES\n");
		
		logger.info("ELEMENT TYPES\n");
		if (xsdFiles.get(3).getElementType() != null) {
			ElementType elementType = xsdFiles.get(3).getElementType();
			logger.info(elementType.toString() + "\n");
		}
		else
			logger.info("NO ELEMENT TYPES\n");
		
		logger.info("GROUP TYPES\n");
		if (xsdFiles.get(3).getGroupTypes() != null) {
			List<GroupType> groupType = xsdFiles.get(3).getGroupTypes();
			for (int i = 0; i < groupType.size(); i++)
				logger.info(groupType.get(i).toString());
			logger.info("\n");
		}
		else
			logger.info("NO GROUP TYPES\n");
		
		logger.info("-----------------Finish Test5-----------------\n");
	}
	
	/* metoda de test afisare deskew.xsd */
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
		}
		else
			logger.info("NO SIMPLE TYPES\n");
		
		logger.info("COMPLEX TYPES\n");
		if (xsdFiles.get(4).getComplexTypes() != null) {
			List<ComplexType> complexType = xsdFiles.get(4).getComplexTypes();
			for (int i = 0; i < complexType.size(); i++)
				logger.info(complexType.get(i).toString());
			logger.info("\n");
		}
		else
			logger.info("NO COMPLEX TYPES\n");
		
		logger.info("ELEMENT TYPES\n");
		if (xsdFiles.get(4).getElementType() != null) {
			ElementType elementType = xsdFiles.get(4).getElementType();
			logger.info(elementType.toString() + "\n");
		}
		else
			logger.info("NO ELEMENT TYPES\n");
		
		logger.info("GROUP TYPES\n");
		if (xsdFiles.get(4).getGroupTypes() != null) {
			List<GroupType> groupType = xsdFiles.get(4).getGroupTypes();
			for (int i = 0; i < groupType.size(); i++)
				logger.info(groupType.get(i).toString());
			logger.info("\n");
		}
		else
			logger.info("NO GROUP TYPES\n");
		
		logger.info("-----------------Finish Test6-----------------\n");
	}
	
	/* metoda de test afisare hierarchy_analysis.xsd */
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
		}
		else
			logger.info("NO SIMPLE TYPES\n");
		
		logger.info("COMPLEX TYPES\n");
		if (xsdFiles.get(5).getComplexTypes() != null) {
			List<ComplexType> complexType = xsdFiles.get(5).getComplexTypes();
			for (int i = 0; i < complexType.size(); i++)
				logger.info(complexType.get(i).toString());
			logger.info("\n");
		}
		else
			logger.info("NO COMPLEX TYPES\n");
		
		logger.info("ELEMENT TYPES\n");
		if (xsdFiles.get(5).getElementType() != null) {
			ElementType elementType = xsdFiles.get(5).getElementType();
			logger.info(elementType.toString() + "\n");
		}
		else
			logger.info("NO ELEMENT TYPES\n");
		
		logger.info("GROUP TYPES\n");
		if (xsdFiles.get(5).getGroupTypes() != null) {
			List<GroupType> groupType = xsdFiles.get(5).getGroupTypes();
			for (int i = 0; i < groupType.size(); i++)
				logger.info(groupType.get(i).toString());
			logger.info("\n");
		}
		else
			logger.info("NO GROUP TYPES\n");
		
		logger.info("-----------------Finish Test7-----------------\n");
	}	
	
	/* metoda de test afisare layout_analysis.xsd */
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
		}
		else
			logger.info("NO SIMPLE TYPES\n");
		
		logger.info("COMPLEX TYPES\n");
		if (xsdFiles.get(6).getComplexTypes() != null) {
			List<ComplexType> complexType = xsdFiles.get(6).getComplexTypes();
			for (int i = 0; i < complexType.size(); i++)
				logger.info(complexType.get(i).toString());
			logger.info("\n");
		}
		else
			logger.info("NO COMPLEX TYPES\n");
		
		logger.info("ELEMENT TYPES\n");
		if (xsdFiles.get(6).getElementType() != null) {
			ElementType elementType = xsdFiles.get(6).getElementType();
			logger.info(elementType.toString() + "\n");
		}
		else
			logger.info("NO ELEMENT TYPES\n");
		
		logger.info("GROUP TYPES\n");
		if (xsdFiles.get(6).getGroupTypes() != null) {
			List<GroupType> groupType = xsdFiles.get(6).getGroupTypes();
			for (int i = 0; i < groupType.size(); i++)
				logger.info(groupType.get(i).toString());
			logger.info("\n");
		}
		else
			logger.info("NO GROUP TYPES\n");
		
		logger.info("-----------------Finish Test8-----------------\n");
	}
	
	/* metoda de test afisare otsu.xsd */
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
		}
		else
			logger.info("NO SIMPLE TYPES\n");
		
		logger.info("COMPLEX TYPES\n");
		if (xsdFiles.get(7).getComplexTypes() != null) {
			List<ComplexType> complexType = xsdFiles.get(7).getComplexTypes();
			for (int i = 0; i < complexType.size(); i++)
				logger.info(complexType.get(i).toString());
			logger.info("\n");
		}
		else
			logger.info("NO COMPLEX TYPES\n");
		
		logger.info("ELEMENT TYPES\n");
		if (xsdFiles.get(7).getElementType() != null) {
			ElementType elementType = xsdFiles.get(7).getElementType();
			logger.info(elementType.toString() + "\n");
		}
		else
			logger.info("NO ELEMENT TYPES\n");
		
		logger.info("GROUP TYPES\n");
		if (xsdFiles.get(7).getGroupTypes() != null) {
			List<GroupType> groupType = xsdFiles.get(7).getGroupTypes();
			for (int i = 0; i < groupType.size(); i++)
				logger.info(groupType.get(i).toString());
			logger.info("\n");
		}
		else
			logger.info("NO GROUP TYPES\n");
		
		logger.info("-----------------Finish Test9-----------------\n");
	}
	
	/* metoda de test afisare rotate.xsd */
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
		}
		else
			logger.info("NO SIMPLE TYPES\n");
		
		logger.info("COMPLEX TYPES\n");
		if (xsdFiles.get(8).getComplexTypes() != null) {
			List<ComplexType> complexType = xsdFiles.get(8).getComplexTypes();
			for (int i = 0; i < complexType.size(); i++)
				logger.info(complexType.get(i).toString());
			logger.info("\n");
		}
		else
			logger.info("NO COMPLEX TYPES\n");
		
		logger.info("ELEMENT TYPES\n");
		if (xsdFiles.get(8).getElementType() != null) {
			ElementType elementType = xsdFiles.get(8).getElementType();
			logger.info(elementType.toString() + "\n");
		}
		else
			logger.info("NO ELEMENT TYPES\n");
		
		logger.info("GROUP TYPES\n");
		if (xsdFiles.get(8).getGroupTypes() != null) {
			List<GroupType> groupType = xsdFiles.get(8).getGroupTypes();
			for (int i = 0; i < groupType.size(); i++)
				logger.info(groupType.get(i).toString());
			logger.info("\n");
		}
		else
			logger.info("NO GROUP TYPES\n");
		
		logger.info("-----------------Finish Test10-----------------\n");
	}
	
	/* metoda de test afisare tesseract.xsd */
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
		}
		else
			logger.info("NO SIMPLE TYPES\n");
		
		logger.info("COMPLEX TYPES\n");
		if (xsdFiles.get(9).getComplexTypes() != null) {
			List<ComplexType> complexType = xsdFiles.get(9).getComplexTypes();
			for (int i = 0; i < complexType.size(); i++)
				logger.info(complexType.get(i).toString());
			logger.info("\n");
		}
		else
			logger.info("NO COMPLEX TYPES\n");
		
		logger.info("ELEMENT TYPES\n");
		if (xsdFiles.get(9).getElementType() != null) {
			ElementType elementType = xsdFiles.get(9).getElementType();
			logger.info(elementType.toString() + "\n");
		}
		else
			logger.info("NO ELEMENT TYPES\n");
		
		logger.info("GROUP TYPES\n");
		if (xsdFiles.get(9).getGroupTypes() != null) {
			List<GroupType> groupType = xsdFiles.get(9).getGroupTypes();
			for (int i = 0; i < groupType.size(); i++)
				logger.info(groupType.get(i).toString());
			logger.info("\n");
		}
		else
			logger.info("NO GROUP TYPES\n");
		
		logger.info("-----------------Finish Test11-----------------\n");
	}
	
}
