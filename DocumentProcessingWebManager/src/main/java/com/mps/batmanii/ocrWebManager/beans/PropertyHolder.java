package com.mps.batmanii.ocrWebManager.beans;

/**
 * Bean class in care sunt retinute folderele folosite de aplicatie, pentru
 * schemele de input\output, executabile, imagini uploadate, fisiere xml de
 * input si rezultate
 * 
 * @author CosminV
 * @author Flavia
 */
public class PropertyHolder {

	private String inputSchemasFolder;
	private String outputSchemasFolder;
	private String execsFolder;
	private String uploadedImagesFolder;
	private String results;
	private String xmlFolder;

	/**
	 * Zero constructor
	 */
	public PropertyHolder() {
		super();
	}

	/**
	 * Setteri si getteri
	 */

	public String getInputSchemasFolder() {
		return inputSchemasFolder;
	}

	public void setInputSchemasFolder(String inputSchemasFolder) {
		this.inputSchemasFolder = inputSchemasFolder;
	}

	public String getOutputSchemasFolder() {
		return outputSchemasFolder;
	}

	public void setOutputSchemasFolder(String outputSchemasFolder) {
		this.outputSchemasFolder = outputSchemasFolder;
	}

	public String getExecsFolder() {
		return execsFolder;
	}

	public void setExecsFolder(String execsFolder) {
		this.execsFolder = execsFolder;
	}

	public String getXmlFolder() {
		return xmlFolder;
	}

	public void setXmlFolder(String xmlFolder) {
		this.xmlFolder = xmlFolder;
	}

	public String getUploadedImagesFolder() {
		return uploadedImagesFolder;
	}

	public void setUploadedImagesFolder(String uploadedImagesFolder) {
		this.uploadedImagesFolder = uploadedImagesFolder;
	}

	public String getResults() {
		return results;
	}

	public void setResults(String results) {
		this.results = results;
	}

}
