package com.mps.batmanii.ocrWebManager.beans;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.xml.sax.SAXException;

import com.mps.batmanii.ocrWebManager.business.ParserXsd;
import com.mps.batmanii.ocrWebManager.business.XsdToXml;

/**
 * Clasa bean ce contine toate obiectele de tip XsdFile, corespunzatoare
 * fiecarei scheme xsd
 * 
 * @author comy
 * 
 */
public class XsdContainer {

	private final static Logger logger = LoggerFactory
			.getLogger(XsdContainer.class);

	@Autowired
	private PropertyHolder propertyHolder;

	private List<XsdFile> xsdFiles;

	public XsdContainer() {
		logger.info("Creare singleton ContainerXsd");
		xsdFiles = new ArrayList<XsdFile>();
	}

	/**
	 * Metoda apelata dupa construirea obiectului; citeste fisierele .xsd din
	 * directorul de scheme, realizeaza parsarea pentru fiecare dintre acestea,
	 * creand cate un nou obiect de tipul XsdFile si le adauga pe toate acestea
	 * in lista xsdFiles
	 * 
	 * @throws SAXException
	 * @throws IOException
	 */
	@PostConstruct
	public void postConstruct() throws SAXException, IOException {
		logger.info("In postconstruct XsdContainer");
		File folder = new File(propertyHolder.getInputSchemasFolder());
		for (File fileEntry : folder.listFiles()) {
			ParserXsd parserXsd = new ParserXsd();
			XsdFile xsdFile = parserXsd.parse(fileEntry.getAbsolutePath());
			xsdFiles.add(xsdFile);
		}
		// testare
		XsdToXml xsdToXml = new XsdToXml();
		XmlFile xmlFile = xsdToXml.getXmlFileFromXsdFile(xsdFiles.get(0));
		logger.info(xmlFile.toString());
		List<XmlElement> xmlElements = xsdToXml.getXmlElements(xmlFile);
		logger.info("mata");
		for (XmlElement element : xmlElements) {
			logger.info(element.toString());
		}
	}

	public List<XsdFile> getXsdFiles() {
		return xsdFiles;
	}

	public String toString() {
		return "Container XSD ce contine " + xsdFiles.size()
				+ " parsari de fisiere xsd";
	}
}
