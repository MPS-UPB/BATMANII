package com.mps.batmanii.ocrWebManager.controller;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mps.batmanii.ocrWebManager.beans.Exec;
import com.mps.batmanii.ocrWebManager.beans.ExecContainer;
import com.mps.batmanii.ocrWebManager.beans.PropertyHolder;
import com.mps.batmanii.ocrWebManager.beans.SelectedExecs;
import com.mps.batmanii.ocrWebManager.beans.XmlElement;
import com.mps.batmanii.ocrWebManager.beans.XmlElementForm;
import com.mps.batmanii.ocrWebManager.beans.XmlElementFormList;
import com.mps.batmanii.ocrWebManager.beans.XmlFile;
import com.mps.batmanii.ocrWebManager.beans.SelectedXmlFiles;
import com.mps.batmanii.ocrWebManager.beans.XsdContainer;
import com.mps.batmanii.ocrWebManager.beans.XsdFile;
import com.mps.batmanii.ocrWebManager.business.CreateXml;
import com.mps.batmanii.ocrWebManager.business.XsdToXml;

/**
 * Clasa controller pentru pagina "result.jsp"
 * 
 * @author Flavia
 * 
 */

@Controller
@RequestMapping(value = "/result")
public class ResultController {
	@Autowired
	XsdContainer xsdContainer;

	@Autowired
	PropertyHolder propertyHolder;

	@Autowired
	ExecContainer execContainer;

	@Autowired
	SelectedExecs selectedExecs;

	@Autowired
	SelectedXmlFiles selectedXmlFiles;

	private final static Logger logger = LoggerFactory
			.getLogger(OCRController.class);

	public List<XmlFile> sortXmlFiles(List<XmlFile> xmlFiles) {
		// Create a hash map
		HashMap<String, Integer> hm = new HashMap<String, Integer>();
		XmlFile temp = new XmlFile();
		// Put elements to the map
		hm.put("preprocessing", new Integer(1));
		hm.put("binarization", new Integer(2));
		hm.put("layout", new Integer(3));
		hm.put("paging", new Integer(4));
		hm.put("ocr", new Integer(5));
		hm.put("hierarchy", new Integer(6));
		hm.put("pdf-exporter", new Integer(7));

		for (int i = 0; i < xmlFiles.size() - 1; i++)
			for (int j = i + 1; j < xmlFiles.size(); j++) {
				if (hm.get(xmlFiles.get(i).getExecType()) > hm.get(xmlFiles
						.get(j).getExecType())) {
					temp = xmlFiles.get(i);
					xmlFiles.set(i, xmlFiles.get(j));
					xmlFiles.set(j, temp);
				}
			}
		return xmlFiles;
	}

	@RequestMapping("")
	public String display(Model model) {
		//redenumeste variabilele sa fie de forma fisiereXml fara "_".
		List<XmlFile> fisiere_xml = selectedXmlFiles.getXmlFiles();
		fisiere_xml = sortXmlFiles(fisiere_xml);
		selectedXmlFiles.setXmlFiles(fisiere_xml);

		for (int i = 0; i < fisiere_xml.size(); i++) {
			System.out.println("i= " + i + " "
					+ fisiere_xml.get(i).getExecType());
		}
		String output_file_value = new String("");
		/* setez cale input si output pentru fisierele xml */
		for (int index_xml = 0; index_xml < fisiere_xml.size(); index_xml++) {
			System.out.println("index_xml= " + index_xml);
			for (int i = 0; i < fisiere_xml.get(0).getXmlElements().size(); i++) {
				if (index_xml == 0) {
					if (fisiere_xml.get(index_xml).getXmlElements().get(i)
							.getName().equals("inputFile")) {
						fisiere_xml
								.get(index_xml)
								.getXmlElements()
								.get(i + 1)
								.setValue(
										propertyHolder
												.getUploadedImagesFolder()
												+ fisiere_xml.get(index_xml)
														.getXmlElements()
														.get(i + 1).getValue());
						System.out.println("inputfile: "
								+ fisiere_xml.get(index_xml).getXmlElements()
										.get(i + 1).getName()
								+ " "
								+ fisiere_xml.get(index_xml).getXmlElements()
										.get(i + 1).getValue());
					}
					if (fisiere_xml.get(index_xml).getXmlElements().get(i)
							.getName().equals("outputFile")) {
						fisiere_xml
								.get(index_xml)
								.getXmlElements()
								.get(i + 1)
								.setValue(
										propertyHolder.getResults()
												+ fisiere_xml.get(index_xml)
														.getXmlElements()
														.get(i + 1).getValue());
						output_file_value = fisiere_xml.get(index_xml)
								.getXmlElements().get(i + 1).getValue();
						System.out.println("outputfile: "
								+ fisiere_xml.get(index_xml).getXmlElements()
										.get(i + 1).getName()
								+ " "
								+ fisiere_xml.get(index_xml).getXmlElements()
										.get(i + 1).getValue());
						break;
					}
				} else {
					if (fisiere_xml.get(index_xml).getXmlElements().get(i)
							.getName().equals("inputFile")) {
						fisiere_xml.get(index_xml).getXmlElements().get(i + 1)
								.setValue(output_file_value);
						System.out.println("inputfile: "
								+ fisiere_xml.get(index_xml).getXmlElements()
										.get(i + 1).getName()
								+ " "
								+ fisiere_xml.get(index_xml).getXmlElements()
										.get(i + 1).getValue());
					}
					if (fisiere_xml.get(index_xml).getXmlElements().get(i)
							.getName().equals("outputFile")) {
						fisiere_xml
								.get(index_xml)
								.getXmlElements()
								.get(i + 1)
								.setValue(
										propertyHolder.getResults()
												+ fisiere_xml.get(index_xml)
														.getXmlElements()
														.get(i + 1).getValue());
						output_file_value = fisiere_xml.get(index_xml)
								.getXmlElements().get(i + 1).getValue();
						System.out.println("outputfile: "
								+ fisiere_xml.get(index_xml).getXmlElements()
										.get(i + 1).getName()
								+ " "
								+ fisiere_xml.get(index_xml).getXmlElements()
										.get(i + 1).getValue());
						break;
					}
				}
			}
		}
		selectedXmlFiles.setXmlFiles(fisiere_xml);

		for (int index_xml = 0; index_xml < fisiere_xml.size(); index_xml++) {
			/* creare xml */
			CreateXml nou = new CreateXml();

			nou.generateXml(
					propertyHolder.getXmlFolder()
							+ fisiere_xml
									.get(index_xml)
									.getExecName()
									.substring(
											0,
											fisiere_xml.get(index_xml)
													.getExecName().indexOf('.'))
							+ ".xml", fisiere_xml.get(index_xml)
							.getXmlElements());

			/* rularea executabilului */
			Runtime r = Runtime.getRuntime();
			Process p = null;
			try {
				String[] cmd = {
						propertyHolder.getExecsFolder()
								+ fisiere_xml.get(index_xml).getExecName(),
						propertyHolder.getXmlFolder()
								+ fisiere_xml
										.get(index_xml)
										.getExecName()
										.substring(
												0,
												fisiere_xml.get(index_xml)
														.getExecName()
														.indexOf('.')) + ".xml" };
				for (int i = 0; i < 2; i++)
					System.out.println("cmd= " + cmd[i]);
				p = r.exec(cmd);
				p.waitFor();
				InputStream in = p.getInputStream();
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				int c = -1;
				while ((c = in.read()) != -1) {
					baos.write(c);
				}
				String response = new String(baos.toByteArray());
				System.out.println("Response From Exe : " + response);
			} catch (Exception e) {
				System.out.println(e);
				e.printStackTrace();
			}
		}

		selectedXmlFiles.setXmlFiles(new ArrayList<XmlFile>());
		// aici si selectedExecs reinitializat pentru flavia
		return "result";
	}

}
