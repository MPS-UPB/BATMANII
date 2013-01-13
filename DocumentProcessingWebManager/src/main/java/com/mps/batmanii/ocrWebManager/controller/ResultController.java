package com.mps.batmanii.ocrWebManager.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

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
import com.mps.batmanii.ocrWebManager.beans.XmlFile;
import com.mps.batmanii.ocrWebManager.beans.SelectedXmlFiles;
import com.mps.batmanii.ocrWebManager.beans.XsdContainer;
import com.mps.batmanii.ocrWebManager.business.CreateXml;

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

	/*
	 * functie care returneaza lista de fisiere din folderul specificat de
	 * stringul path
	 */
	public Vector<String> listFile(String path) {
		Vector<String> folderFiles = new Vector<String>();
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();

		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				folderFiles.add(listOfFiles[i].getName());
			}
		}
		return folderFiles;
	}

	@RequestMapping("")
	public String display(Model model) {
		List<XmlFile> fisiereXml = new ArrayList<XmlFile>();
		fisiereXml = selectedXmlFiles.getXmlFiles();
		Vector<String> listaFisiere = listFile(propertyHolder
				.getUploadedImagesFolder());
		Vector<String> outputEtapaPrecedenta = new Vector<String>();
		int ok = 0;
		int tip = 0;

		/* pentru fiecare executabil selectat */
		for (int index_xml = 0; index_xml < fisiereXml.size(); index_xml++) {
			if (fisiereXml.get(index_xml).getExecType()
					.contentEquals("binarization")
					|| fisiereXml.get(index_xml).getExecType()
							.contentEquals("preprocesssing")) {
				tip = 0; /* returneaza poza */
			} else {
				if (fisiereXml.get(index_xml).getExecType()
						.contentEquals("pdf-exporter")) {
					tip = 2; /* returneaza pdf */
				} else {
					tip = 1; /* returneaza xml */
				}
			}

			if (index_xml == 0) {
				/* pentru fiecare imagine din fileUploadFolder */
				for (int fileCounter = 0; fileCounter < listaFisiere.size(); fileCounter++) {
					for (int i = 0; i < fisiereXml.get(index_xml)
							.getXmlElements().size(); i++) {
						if (fisiereXml.get(index_xml).getXmlElements().get(i)
								.getName().equals("inputFile")) {
							fisiereXml
									.get(index_xml)
									.getXmlElements()
									.get(i + 1)
									.setValue(
											propertyHolder
													.getUploadedImagesFolder()
													+ listaFisiere
															.get(fileCounter));
						}
						if (fisiereXml.get(index_xml).getXmlElements().get(i)
								.getName().equals("outputFile")) {
							String output = propertyHolder.getResults()
									+ listaFisiere.get(fileCounter).substring(
											0,
											listaFisiere.get(fileCounter)
													.indexOf("."))
									+ "_"
									+ fisiereXml.get(index_xml).getExecType()
									+ "_"
									+ fisiereXml
											.get(index_xml)
											.getExecName()
											.substring(
													0,
													fisiereXml.get(index_xml)
															.getExecName()
															.indexOf('.'))
									+ "_" + "output";
							if (tip == 0) {
								output = output
										+ listaFisiere.get(fileCounter)
												.substring(
														listaFisiere.get(
																fileCounter)
																.lastIndexOf(
																		"."));
							}
							if (tip == 1) {
								output = output + ".xml";
							}
							fisiereXml.get(index_xml).getXmlElements()
									.get(i + 1).setValue(output);
							outputEtapaPrecedenta.add(output);
							break;
						}
					}

					/* creare xml */
					CreateXml nou = new CreateXml();
					String newXmlFile = propertyHolder.getXmlFolder()
							+ fisiereXml.get(index_xml).getExecType()
							+ "_"
							+ fisiereXml
									.get(index_xml)
									.getExecName()
									.substring(
											0,
											fisiereXml.get(index_xml)
													.getExecName().indexOf('.'))
							+ "_"
							+ listaFisiere.get(fileCounter).substring(0,
									listaFisiere.get(fileCounter).indexOf("."))
							+ ".xml";
					nou.generateXml(newXmlFile, fisiereXml.get(index_xml)
							.getXmlElements());

					/* rularea executabilului */
					Runtime r = Runtime.getRuntime();
					Process p = null;
					try {
						String[] cmd = {
								propertyHolder.getExecsFolder()
										+ fisiereXml.get(index_xml)
												.getExecName(), newXmlFile };
						for (int i = 0; i < 2; i++)
							logger.info("cmd= " + cmd[i]);
						p = r.exec(cmd);
						p.waitFor();
						InputStream in = p.getInputStream();
						ByteArrayOutputStream baos = new ByteArrayOutputStream();
						int c = -1;
						while ((c = in.read()) != -1) {
							baos.write(c);
						}
						String response = new String(baos.toByteArray());
						logger.info("Response From Exe : " + response);
					} catch (Exception e) {
						System.out.println(e);
						e.printStackTrace();
					}
				}
			} else {
				for (int i = 0; i < fisiereXml.get(index_xml).getXmlElements()
						.size(); i++) {
					if (fisiereXml.get(index_xml).getXmlElements().get(i)
							.getName().equals("inputFile")) {
						if (fisiereXml.get(index_xml).getXmlElements().get(i)
								.getMaxOccurs() != -1) {
							ok = 1;
							break;
						} else {
							ok = 0;
						}
					}
				}

				if (ok == 1) {
					/* pentru fiecare rezultat de la pasul anterior */
					for (int fileCounter = 0; fileCounter < outputEtapaPrecedenta
							.size(); fileCounter++) {
						for (int i = 0; i < fisiereXml.get(index_xml)
								.getXmlElements().size(); i++) {
							if (fisiereXml.get(index_xml).getXmlElements()
									.get(i).getName().equals("inputFile")) {
								fisiereXml
										.get(index_xml)
										.getXmlElements()
										.get(i + 1)
										.setValue(
												outputEtapaPrecedenta
														.get(fileCounter));
							}
							if (fisiereXml.get(index_xml).getXmlElements()
									.get(i).getName().equals("outputFile")) {
								String output = propertyHolder.getResults()
										+ listaFisiere.get(fileCounter)
												.substring(
														0,
														listaFisiere.get(
																fileCounter)
																.indexOf("."))
										+ "_"
										+ fisiereXml.get(index_xml)
												.getExecType()
										+ "_"
										+ fisiereXml
												.get(index_xml)
												.getExecName()
												.substring(
														0,
														fisiereXml
																.get(index_xml)
																.getExecName()
																.indexOf('.'))
										+ "_" + "output";
								if (tip == 0) {
									output = output
											+ "."
											+ listaFisiere
													.get(fileCounter)
													.substring(
															listaFisiere
																	.get(fileCounter)
																	.lastIndexOf(
																			"."));
								}
								if (tip == 1) {
									output = output + ".xml";
								}
								fisiereXml.get(index_xml).getXmlElements()
										.get(i + 1).setValue(output);
								outputEtapaPrecedenta.set(fileCounter, output);
								break;
							}
						}

						/* creare xml */
						CreateXml nou = new CreateXml();
						String newXmlFile = propertyHolder.getXmlFolder()
								+ fisiereXml.get(index_xml).getExecType()
								+ "_"
								+ fisiereXml
										.get(index_xml)
										.getExecName()
										.substring(
												0,
												fisiereXml.get(index_xml)
														.getExecName()
														.indexOf('.'))
								+ "_"
								+ listaFisiere.get(fileCounter).substring(
										0,
										listaFisiere.get(fileCounter).indexOf(
												".")) + ".xml";
						nou.generateXml(newXmlFile, fisiereXml.get(index_xml)
								.getXmlElements());

						/* rularea executabilului */
						Runtime r = Runtime.getRuntime();
						Process p = null;
						try {
							String[] cmd = {
									propertyHolder.getExecsFolder()
											+ fisiereXml.get(index_xml)
													.getExecName(), newXmlFile };
							for (int i = 0; i < 2; i++)
								logger.info("cmd= " + cmd[i]);
							p = r.exec(cmd);
							p.waitFor();
							InputStream in = p.getInputStream();
							ByteArrayOutputStream baos = new ByteArrayOutputStream();
							int c = -1;
							while ((c = in.read()) != -1) {
								baos.write(c);
							}
							String response = new String(baos.toByteArray());
							logger.info("Response From Exe : " + response);
						} catch (Exception e) {
							System.out.println(e);
							e.printStackTrace();
						}
					}
				} else {
					for (int i = 0; i < fisiereXml.get(index_xml)
							.getXmlElements().size(); i++) {
						if (fisiereXml.get(index_xml).getXmlElements().get(i)
								.getName().equals("inputFile")) {
							fisiereXml.get(index_xml).getXmlElements()
									.get(i + 1)
									.setValue(outputEtapaPrecedenta.get(0));

							/* pentru fiecare rezultat de la pasul anterior */
							for (int fileCounter = 1; fileCounter < outputEtapaPrecedenta
									.size(); fileCounter++) {
								XmlElement nouXmlElem1 = new XmlElement(
										fisiereXml.get(index_xml)
												.getXmlElements().get(i)
												.getName(), fisiereXml
												.get(index_xml)
												.getXmlElements().get(i)
												.getValue(), fisiereXml
												.get(index_xml)
												.getXmlElements().get(i)
												.getAttribute(), fisiereXml
												.get(index_xml)
												.getXmlElements().get(i)
												.getLevel(), fisiereXml
												.get(index_xml)
												.getXmlElements().get(i)
												.getSimpleType(), fisiereXml
												.get(index_xml)
												.getXmlElements().get(i)
												.getMinOccurs(), fisiereXml
												.get(index_xml)
												.getXmlElements().get(i)
												.getMaxOccurs());
								XmlElement nouXmlElem2 = new XmlElement(
										fisiereXml.get(index_xml)
												.getXmlElements().get(i + 1)
												.getName(), fisiereXml
												.get(index_xml)
												.getXmlElements().get(i + 1)
												.getValue(), fisiereXml
												.get(index_xml)
												.getXmlElements().get(i + 1)
												.getAttribute(), fisiereXml
												.get(index_xml)
												.getXmlElements().get(i + 1)
												.getLevel(), fisiereXml
												.get(index_xml)
												.getXmlElements().get(i + 1)
												.getSimpleType(), fisiereXml
												.get(index_xml)
												.getXmlElements().get(i + 1)
												.getMinOccurs(), fisiereXml
												.get(index_xml)
												.getXmlElements().get(i + 1)
												.getMaxOccurs());

								nouXmlElem2.setValue(outputEtapaPrecedenta
										.get(fileCounter));

								fisiereXml
										.get(index_xml)
										.getXmlElements()
										.add(fileCounter + i * fileCounter + 1,
												nouXmlElem1);
								fisiereXml
										.get(index_xml)
										.getXmlElements()
										.add(fileCounter + i * fileCounter + 2,
												nouXmlElem2);
							}
							i = i + (outputEtapaPrecedenta.size() - 1) * 2 + 1;
						}

						if (fisiereXml.get(index_xml).getXmlElements().get(i)
								.getName().equals("outputFile")) {
							String output = propertyHolder.getResults()
									+ fisiereXml.get(index_xml).getExecType()
									+ "_"
									+ fisiereXml
											.get(index_xml)
											.getExecName()
											.substring(
													0,
													fisiereXml.get(index_xml)
															.getExecName()
															.indexOf('.'))
									+ "_" + "output";

							if (tip == 1) {
								output = output + ".xml";
							}
							fisiereXml.get(index_xml).getXmlElements()
									.get(i + 1).setValue(output);
							outputEtapaPrecedenta.clear();
							outputEtapaPrecedenta.add(output);
							break;
						}
					}

					/* creare xml */
					CreateXml nou = new CreateXml();
					String newXmlFile = propertyHolder.getXmlFolder()
							+ fisiereXml.get(index_xml).getExecType()
							+ "_"
							+ fisiereXml
									.get(index_xml)
									.getExecName()
									.substring(
											0,
											fisiereXml.get(index_xml)
													.getExecName().indexOf('.'))
							+ ".xml";
					nou.generateXml(newXmlFile, fisiereXml.get(index_xml)
							.getXmlElements());

					/* rularea executabilului */
					Runtime r = Runtime.getRuntime();
					Process p = null;
					try {
						String[] cmd = {
								propertyHolder.getExecsFolder()
										+ fisiereXml.get(index_xml)
												.getExecName(), newXmlFile };
						for (int i = 0; i < 2; i++)
							logger.info("cmd= " + cmd[i]);
						p = r.exec(cmd);
						p.waitFor();
						InputStream in = p.getInputStream();
						ByteArrayOutputStream baos = new ByteArrayOutputStream();
						int c = -1;
						while ((c = in.read()) != -1) {
							baos.write(c);
						}
						String response = new String(baos.toByteArray());
						logger.info("Response From Exe : " + response);
					} catch (Exception e) {
						System.out.println(e);
						e.printStackTrace();
					}
				}
			}
		}

		selectedXmlFiles.setXmlFiles(new ArrayList<XmlFile>());
		selectedExecs.setSelectedExecs(new ArrayList<Exec>());

		return "result";
	}

}
