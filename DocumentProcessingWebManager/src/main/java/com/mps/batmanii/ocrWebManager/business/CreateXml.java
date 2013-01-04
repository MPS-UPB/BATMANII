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

import com.mps.batmanii.ocrWebManager.beans.Component;
import com.mps.batmanii.ocrWebManager.beans.ExecParameter;
import com.mps.batmanii.ocrWebManager.beans.MyElement;
import com.mps.batmanii.ocrWebManager.beans.XmlElement;
import com.mps.batmanii.ocrWebManager.beans.XmlFile;

/**
 * 
 * @author Adela
 * 
 */

public class CreateXml {

	public int find_father(List<MyElement> elements_list, int level)
	{
		int index;
		for(index = 0; index < elements_list.size(); index++)
		{
			if(elements_list.get(index).getLevel() == level -1)
				return index;
		}
		return -1;
		
	}
	public void generateXml(String path, List<XmlElement> elements) {
		int index, s = 0;
		int size = elements.size();
		List<MyElement> elements_list = new ArrayList<MyElement>();
		
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			Document doc = docBuilder.newDocument();
			
			for(index = 0; index < size; index++)
			{
				XmlElement xmlElement = elements.get(index);
				if(xmlElement.isAttribute() == false)
				{
					Element Elem = doc.createElement(xmlElement.getName());
					MyElement newMyElement = new MyElement(Elem,xmlElement.getLevel());
					elements_list.add(newMyElement);
					s++;
				}
				else
				{
					/*add an atribute
					 *  Eg. atribute <inputFile name='input.jpg' />
					 */
					MyElement aux = elements_list.get(s);
					aux.getElement().setAttribute(xmlElement.getName(),xmlElement.getValue());
					elements_list.set(s,aux);
				}
			}
			
			// root element
			MyElement rootElem = elements_list.get(0);
			doc.appendChild(rootElem.getElement());
			size = elements_list.size();
			for(index = 1; index < size; index++)
			{
				MyElement myElement = elements_list.get(index);
				int father_index = find_father(elements_list,elements_list.get(index -1).getLevel());
				Element father = elements_list.get(father_index).getElement(); 
				father.appendChild(myElement.getElement());		
			}


			// write the content into xml file
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

	public static void main(String argv[]) {
		CreateXml createXml = new CreateXml();

		XmlFile test = new XmlFile();
		ExecParameter e1 = new ExecParameter();
		ExecParameter e2 = new ExecParameter();
		ExecParameter e3 = new ExecParameter();
		ExecParameter e3_1 = new ExecParameter();
		ExecParameter e3_2 = new ExecParameter();
		ExecParameter e3_2_1 = new ExecParameter();
		ExecParameter e3_3 = new ExecParameter();
		ExecParameter sub_e3_3 = new ExecParameter();

		Component c = new Component();
		c.setName("task");

		e1.setName("left");
		e1.setValue("10");
		e1.setMinOccurs(0);
		e1.setMaxOccurs(0);
		e1.setLevel(0);

		e2.setName("bottom");
		e2.setValue("10");
		e2.setMinOccurs(0);
		e2.setMaxOccurs(0);
		e2.setLevel(0);

		e3.setName("inputFile");
		e3.setMinOccurs(0);
		e3.setMaxOccurs(0);
		e3.setLevel(0);
		List<ExecParameter> exec = new ArrayList<ExecParameter>();
		e3_1.setName("name");
		e3_1.setValue("input.jpg");
		e3_1.setMaxOccurs(0);
		e3_1.setMinOccurs(0);
		e3_1.setLevel(0);
		exec.add(e3_1);

		e3_2.setName("bottom");
		e3_2.setValue("20");
		e3_2.setMinOccurs(0);
		e3_2.setMaxOccurs(0);
		e3_2.setLevel(1);
		List<ExecParameter> exec1 = new ArrayList<ExecParameter>();
		e3_2_1.setName("name");
		e3_2_1.setValue("input.jpg");
		e3_2_1.setMaxOccurs(0);
		e3_2_1.setMinOccurs(0);
		e3_2_1.setLevel(1);
		exec1.add(e3_2_1);
		e3_2.setExecParameters(exec1);
		exec.add(e3_2);

		e3_3.setName("left");
		e3_3.setValue("20");
		e3_3.setMinOccurs(0);
		e3_3.setMaxOccurs(0);
		e3_3.setLevel(1);

		sub_e3_3.setName("subcopil");
		sub_e3_3.setValue("30");
		sub_e3_3.setMinOccurs(0);
		sub_e3_3.setMaxOccurs(0);
		sub_e3_3.setLevel(2);
		List<ExecParameter> exec_e3_3 = new ArrayList<ExecParameter>();
		exec_e3_3.add(sub_e3_3);
		e3_3.setExecParameters(exec_e3_3);

		exec.add(e3_3);
		e3.setExecParameters(exec);

		List<ExecParameter> childrens = new ArrayList<ExecParameter>();

		childrens.add(e1);
		childrens.add(e2);
		childrens.add(e3);

		test.setChildrens(childrens);
		test.setRootElement(c);
		createXml.generateXml("D:\\file.xml", test);
	}
}
