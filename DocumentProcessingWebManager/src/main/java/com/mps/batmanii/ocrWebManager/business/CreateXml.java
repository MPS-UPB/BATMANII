package com.mps.batmanii.ocrWebManager.business;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.mps.batmanii.ocrWebManager.beans.MyElement;
import com.mps.batmanii.ocrWebManager.beans.XmlElement;

/**
 * Clasa folosita pentru crearea de xml-uri.
 * 
 * @author adela
 * 
 */

public class CreateXml {

	/**
	 * Primeste ca parametru o lista de elemente MyElement si un level. Cauta in
	 * lista de elemente cine este parintele corespunzator elementului cu
	 * level-ul "level" si returneaza index-ul acestuia.
	 * 
	 * @param elements_list
	 * @param level
	 * @return int
	 */
	public int find_father(List<MyElement> elements_list, int level) {
		int index;
		for (index = elements_list.size() - 1; index >= 0; index--) {
			if (elements_list.get(index).getLevel() == level - 1)
				return index;
		}
		return -1;

	}

	/**
	 * Primeste ca parametru calea unde va fi generat xml-ul si o lista de
	 * elemente XmlElement pe baza careia se va construi xml-ul.
	 * 
	 * @param path
	 * @param elements
	 * @return
	 */
	public void generateXml(String path, List<XmlElement> elements) {
		int index;
		int size = elements.size();
		List<MyElement> elements_list = new ArrayList<MyElement>();

		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			Document doc = docBuilder.newDocument();
			/* root element */
			XmlElement rootXmlElem = new XmlElement();
			rootXmlElem = elements.get(0);
			Element elem = doc.createElement(rootXmlElem.getName());
			doc.appendChild(elem);

			MyElement rootElem = new MyElement(elem, rootXmlElem.getLevel());
			elements_list.add(rootElem);

			for (index = 1; index < size; index++) {
				XmlElement xmlElement = elements.get(index);
				if (xmlElement.getAttribute() == false) {
					Element Elem = doc.createElement(xmlElement.getName());
					if (xmlElement.getValue() != null) {
						Elem.appendChild(doc.createTextNode(xmlElement
								.getValue()));
					}
					MyElement newMyElement = new MyElement(Elem,
							xmlElement.getLevel());
					elements_list.add(newMyElement);
				} else {
					/* add an atribute Eg. atribute <inputFile name='input.jpg' */
					MyElement aux = elements_list.get(elements_list.size() - 1);
					aux.getElement().setAttribute(xmlElement.getName(),
							xmlElement.getValue());
					elements_list.set(elements_list.size() - 1, aux);
				}
			}
			size = elements_list.size();
			for (index = 1; index < size; index++) {
				MyElement myElement = elements_list.get(index);
				int father_index = find_father(elements_list, elements_list
						.get(index).getLevel());
				Element father = elements_list.get(father_index).getElement();
				father.appendChild(myElement.getElement());
			}

			/* write the content into xml file */
			TransformerFactory transformerFactory = TransformerFactory
					.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(path));

			transformer.transform(source, result);

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		}
	}
}