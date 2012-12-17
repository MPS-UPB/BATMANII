package com.mps.batmanii.ocrWebManager.beans;

/**
 * Bean class in care sunt retinute folderele folosite de aplicatie, pentru
 * schemele de input\output, executabile si imagini uploadate
 * 
 * @author CosminV
 * 
 */
public class PropertyHolder {

	private String inputSchemasFolder;
	private String outputSchemasFolder;
	private String execsFolder;
	private String uploadedImagesFolder;

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

	public String getUploadedImagesFolder() {
		return uploadedImagesFolder;
	}

	public void setUploadedImagesFolder(String uploadedImagesFolder) {
		this.uploadedImagesFolder = uploadedImagesFolder;
	}

}
