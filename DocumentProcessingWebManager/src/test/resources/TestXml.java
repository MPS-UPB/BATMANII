package com.mps.batmanii.ocrWebManager.business;

import java.util.ArrayList;
import java.util.List;

import com.mps.batmanii.ocrWebManager.beans.XmlElement;


public class TestXml {
	
	CreateXml nou;
	
	public TestXml(CreateXml nou) {
		this.nou = nou;
	}
	
	/* adjust brightness test */
	public void doTestBright(CreateXml nou) {
		List<XmlElement> bright = new ArrayList<XmlElement>();
		XmlElement task_bright = new XmlElement();
		task_bright.setName("task");
		task_bright.setLevel(0);
		task_bright.setAttribute(false);
		bright.add(task_bright);
	
		XmlElement inputFile_bright = new XmlElement();
		inputFile_bright.setName("inputFile");
		inputFile_bright.setLevel(1);
		task_bright.setAttribute(false);
		bright.add(inputFile_bright);
	
		XmlElement name_in_bright = new XmlElement();
		name_in_bright.setName("name");
		name_in_bright.setValue("input_bright.jpg");
		name_in_bright.setLevel(2);
		name_in_bright.setAttribute(true);
		bright.add(name_in_bright);
	
		XmlElement outputFile_bright = new XmlElement();
		outputFile_bright.setName("outputFile");
		outputFile_bright.setLevel(1);
		outputFile_bright.setAttribute(false);
		bright.add(outputFile_bright);
	
		XmlElement name_out_bright = new XmlElement();
		name_out_bright.setName("name");
		name_out_bright.setValue("output_bright.jpg");
		name_out_bright.setLevel(2);
		name_out_bright.setAttribute(true);
		bright.add(name_out_bright);
	
		XmlElement value_bright = new XmlElement();
		value_bright.setName("value");
		value_bright.setValue("0.0");
		value_bright.setLevel(1);
		value_bright.setAttribute(false);
		bright.add(value_bright);
	
		nou.generateXml("D:\\bright.xml",bright);
	}
	
	/* adjust contrast test*/
	public void doTestContrast(CreateXml nou) {
		List<XmlElement> contrast = new ArrayList<XmlElement>();
		XmlElement task_contrast = new XmlElement();
		task_contrast.setName("task");
		task_contrast.setLevel(0);
		task_contrast.setAttribute(false);
		contrast.add(task_contrast);
	
		XmlElement inputFile_contrast = new XmlElement();
		inputFile_contrast.setName("inputFile");
		inputFile_contrast.setLevel(1);
		task_contrast.setAttribute(false);
		contrast.add(inputFile_contrast);
	
		XmlElement name_in_contrast = new XmlElement();
		name_in_contrast.setName("name");
		name_in_contrast.setValue("input_contrast.jpg");
		name_in_contrast.setLevel(2);
		name_in_contrast.setAttribute(true);
		contrast.add(name_in_contrast);
	
		XmlElement outputFile_contrast = new XmlElement();
		outputFile_contrast.setName("outputFile");
		outputFile_contrast.setLevel(1);
		outputFile_contrast.setAttribute(false);
		contrast.add(outputFile_contrast);
	
		XmlElement name_out_contrast = new XmlElement();
		name_out_contrast.setName("name");
		name_out_contrast.setValue("output_contrast.jpg");
		name_out_contrast.setLevel(2);
		name_out_contrast.setAttribute(true);
		contrast.add(name_out_contrast);
	
		XmlElement value_contrast = new XmlElement();
		value_contrast.setName("value");
		value_contrast.setValue("0.0");
		value_contrast.setLevel(1);
		value_contrast.setAttribute(false);
		contrast.add(value_contrast);
	
		nou.generateXml("D:\\contrast.xml",contrast);
	}
	
	/* convert pdf test */
		public void doTestConvert(CreateXml nou) {
		List<XmlElement> convert = new ArrayList<XmlElement>();
		XmlElement task_convert = new XmlElement();
		task_convert.setName("task");
		task_convert.setLevel(0);
		task_convert.setAttribute(false);
		convert.add(task_convert);
	
		XmlElement inputFile_convert = new XmlElement();
		inputFile_convert.setName("inputFile");
		inputFile_convert.setLevel(1);
		task_convert.setAttribute(false);
		convert.add(inputFile_convert);
	
		XmlElement name_in_convert = new XmlElement();
		name_in_convert.setName("name");
		name_in_convert.setValue("input_convert.jpg");
		name_in_convert.setLevel(2);
		name_in_convert.setAttribute(true);
		convert.add(name_in_convert);
	
		XmlElement outputFile_convert = new XmlElement();
		outputFile_convert.setName("outputFile");
		outputFile_convert.setLevel(1);
		outputFile_convert.setAttribute(false);
		convert.add(outputFile_convert);
	
		XmlElement name_out_convert = new XmlElement();
		name_out_convert.setName("name");
		name_out_convert.setValue("output_convert.jpg");
		name_out_convert.setLevel(2);
		name_out_convert.setAttribute(true);
		convert.add(name_out_convert);
	
		nou.generateXml("D:\\convert.xml",convert);
	}

	/* crop test */
	public void doTestCrop(CreateXml nou) {
		List<XmlElement> crop = new ArrayList<XmlElement>();
		XmlElement task_crop = new XmlElement();
		task_crop.setName("task");
		task_crop.setLevel(0);
		task_crop.setAttribute(false);
		crop.add(task_crop);
	
		XmlElement inputFile_crop = new XmlElement();
		inputFile_crop.setName("inputFile");
		inputFile_crop.setLevel(1);
		task_crop.setAttribute(false);
		crop.add(inputFile_crop);
	
		XmlElement name_in_crop = new XmlElement();
		name_in_crop.setName("name");
		name_in_crop.setValue("input_crop.jpg");
		name_in_crop.setLevel(2);
		name_in_crop.setAttribute(true);
		crop.add(name_in_crop);
	
		XmlElement outputFile_crop = new XmlElement();
		outputFile_crop.setName("outputFile");
		outputFile_crop.setLevel(1);
		outputFile_crop.setAttribute(false);
		crop.add(outputFile_crop);
	
		XmlElement name_out_crop = new XmlElement();
		name_out_crop.setName("name");
		name_out_crop.setValue("output_crop.jpg");
		name_out_crop.setLevel(2);
		name_out_crop.setAttribute(true);
		crop.add(name_out_crop);
	
		XmlElement top = new XmlElement();
		top.setName("top");
		top.setValue("0.0");
		top.setLevel(1);
		top.setAttribute(false);
		crop.add(top);
	
		XmlElement bottom = new XmlElement();
		bottom.setName("bottom");
		bottom.setValue("0.0");
		bottom.setLevel(1);
		bottom.setAttribute(false);
		crop.add(bottom);
	
		XmlElement left = new XmlElement();
		left.setName("left");
		left.setValue("0.0");
		left.setLevel(1);
		left.setAttribute(false);
		crop.add(left);
	
		XmlElement right = new XmlElement();
		right.setName("right");
		right.setValue("0.0");
		right.setLevel(1);
		right.setAttribute(false);
		crop.add(right);
	
		nou.generateXml("D:\\crop.xml",crop);
	}

	/* deskew test*/
	public void doTestDeskew(CreateXml nou) {
		List<XmlElement> deskew = new ArrayList<XmlElement>();
		XmlElement task_deskew = new XmlElement();
		task_deskew.setName("task");
		task_deskew.setLevel(0);
		task_deskew.setAttribute(false);
		deskew.add(task_deskew);
	
		XmlElement inputFile_deskew = new XmlElement();
		inputFile_deskew.setName("inputFile");
		inputFile_deskew.setLevel(1);
		task_deskew.setAttribute(false);
		deskew.add(inputFile_deskew);
	
		XmlElement name_in_deskew = new XmlElement();
		name_in_deskew.setName("name");
		name_in_deskew.setValue("input_deskew.jpg");
		name_in_deskew.setLevel(2);
		name_in_deskew.setAttribute(true);
		deskew.add(name_in_deskew);
	
		XmlElement outputFile_deskew = new XmlElement();
		outputFile_deskew.setName("outputFile");
		outputFile_deskew.setLevel(1);
		outputFile_deskew.setAttribute(false);
		deskew.add(outputFile_deskew);
	
		XmlElement name_out_deskew = new XmlElement();
		name_out_deskew.setName("name");
		name_out_deskew.setValue("output_deskew.jpg");
		name_out_deskew.setLevel(2);
		name_out_deskew.setAttribute(true);
		deskew.add(name_out_deskew);
	
		nou.generateXml("D:\\deskew.xml",deskew);
	}

	/* hierarchy test*/
	public void doTestHierarchy(CreateXml nou) {
		List<XmlElement> hierarchy = new ArrayList<XmlElement>();
		XmlElement task_hierarchy = new XmlElement();
		task_hierarchy.setName("task");
		task_hierarchy.setLevel(0);
		task_hierarchy.setAttribute(false);
		hierarchy.add(task_hierarchy);
	
		XmlElement inputFile_hierarchy = new XmlElement();
		inputFile_hierarchy.setName("inputFile");
		inputFile_hierarchy.setLevel(1);
		task_hierarchy.setAttribute(false);
		hierarchy.add(inputFile_hierarchy);
	
		XmlElement name_in_hierarchy = new XmlElement();
		name_in_hierarchy.setName("name");
		name_in_hierarchy.setValue("input_hierarchy.jpg");
		name_in_hierarchy.setLevel(2);
		name_in_hierarchy.setAttribute(true);
		hierarchy.add(name_in_hierarchy);
	
		XmlElement outputFile_hierarchy = new XmlElement();
		outputFile_hierarchy.setName("outputFile");
		outputFile_hierarchy.setLevel(1);
		outputFile_hierarchy.setAttribute(false);
		hierarchy.add(outputFile_hierarchy);
	
		XmlElement name_out_hierarchy = new XmlElement();
		name_out_hierarchy.setName("name");
		name_out_hierarchy.setValue("output_hierarchy.jpg");
		name_out_hierarchy.setLevel(2);
		name_out_hierarchy.setAttribute(true);
		hierarchy.add(name_out_hierarchy);
	
		nou.generateXml("D:\\hierarchy.xml",hierarchy);
	}

	/* layout test*/
	public void doTestLayout(CreateXml nou) {
		List<XmlElement> layout = new ArrayList<XmlElement>();
		XmlElement task_layout = new XmlElement();
		task_layout.setName("task");
		task_layout.setLevel(0);
		task_layout.setAttribute(false);
		layout.add(task_layout);
	
		XmlElement inputFile_layout = new XmlElement();
		inputFile_layout.setName("inputFile");
		inputFile_layout.setLevel(1);
		task_layout.setAttribute(false);
		layout.add(inputFile_layout);
	
		XmlElement name_in_layout = new XmlElement();
		name_in_layout.setName("name");
		name_in_layout.setValue("input_layout.jpg");
		name_in_layout.setLevel(2);
		name_in_layout.setAttribute(true);
		layout.add(name_in_layout);
	
		XmlElement outputFile_layout = new XmlElement();
		outputFile_layout.setName("outputFile");
		outputFile_layout.setLevel(1);
		outputFile_layout.setAttribute(false);
		layout.add(outputFile_layout);
	
		XmlElement name_out_layout = new XmlElement();
		name_out_layout.setName("name");
		name_out_layout.setValue("output_layout.jpg");
		name_out_layout.setLevel(2);
		name_out_layout.setAttribute(true);
		layout.add(name_out_layout);
	
		nou.generateXml("D:\\layout.xml",layout);
	}
	
	/* rotate test */
	public void doTestRotate(CreateXml nou) {
		List<XmlElement> rotate = new ArrayList<XmlElement>();
		XmlElement task = new XmlElement();
		task.setName("task");
		task.setLevel(0);
		task.setAttribute(false);
		rotate.add(task);
	
		XmlElement inputFile = new XmlElement();
		inputFile.setName("inputFile");
		inputFile.setLevel(1);
		task.setAttribute(false);
		rotate.add(inputFile);
	
		XmlElement name_in = new XmlElement();
		name_in.setName("name");
		name_in.setValue("input.jpg");
		name_in.setLevel(2);
		name_in.setAttribute(true);
		rotate.add(name_in);
	
		XmlElement outputFile = new XmlElement();
		outputFile.setName("outputFile");
		outputFile.setLevel(1);
		outputFile.setAttribute(false);
		rotate.add(outputFile);
	
		XmlElement name_out = new XmlElement();
		name_out.setName("name");
		name_out.setValue("output.jpg");
		name_out.setLevel(2);
		name_out.setAttribute(true);
		rotate.add(name_out);
	
		XmlElement angle = new XmlElement();
		angle.setName("angle");
		angle.setValue("0.0");
		angle.setLevel(1);
		angle.setAttribute(false);
		rotate.add(angle);
	
		nou.generateXml("D:\\rotate.xml",rotate);
	}
	
	/* otsu test*/
	public void doTestOtsu(CreateXml nou) {
		List<XmlElement> otsu = new ArrayList<XmlElement>();
		XmlElement task_otsu = new XmlElement();
		task_otsu.setName("task");
		task_otsu.setLevel(0);
		task_otsu.setAttribute(false);
		otsu.add(task_otsu);
	
		XmlElement inputFile_otsu = new XmlElement();
		inputFile_otsu.setName("inputFile");
		inputFile_otsu.setLevel(1);
		task_otsu.setAttribute(false);
		otsu.add(inputFile_otsu);
	
		XmlElement name_in_otsu = new XmlElement();
		name_in_otsu.setName("name");
		name_in_otsu.setValue("input_otsu.jpg");
		name_in_otsu.setLevel(2);
		name_in_otsu.setAttribute(true);
		otsu.add(name_in_otsu);
	
		XmlElement outputFile_otsu = new XmlElement();
		outputFile_otsu.setName("outputFile");
		outputFile_otsu.setLevel(1);
		outputFile_otsu.setAttribute(false);
		otsu.add(outputFile_otsu);
	
		XmlElement name_out_otsu = new XmlElement();
		name_out_otsu.setName("name");
		name_out_otsu.setValue("output_otsu.jpg");
		name_out_otsu.setLevel(2);
		name_out_otsu.setAttribute(true);
		otsu.add(name_out_otsu);
	
		nou.generateXml("D:\\otsu.xml",otsu);
	}


	/* tesseract ocr wrraper test */
	public void doTestTesseract(CreateXml nou) {
		List<XmlElement> tesseract = new ArrayList<XmlElement>();
		XmlElement task_tesseract = new XmlElement();
		task_tesseract.setName("task");
		task_tesseract.setLevel(0);
		task_tesseract.setAttribute(false);
		tesseract.add(task_tesseract);
	
		XmlElement inputFile_tesseract = new XmlElement();
		inputFile_tesseract.setName("inputFile");
		inputFile_tesseract.setLevel(1);
		task_tesseract.setAttribute(false);
		tesseract.add(inputFile_tesseract);
	
		XmlElement name_in_tesseract = new XmlElement();
		name_in_tesseract.setName("name");
		name_in_tesseract.setValue("input_tesseract.jpg");
		name_in_tesseract.setLevel(2);
		name_in_tesseract.setAttribute(true);
		tesseract.add(name_in_tesseract);
	
		XmlElement outputFile_tesseract = new XmlElement();
		outputFile_tesseract.setName("outputFile");
		outputFile_tesseract.setLevel(1);
		outputFile_tesseract.setAttribute(false);
		tesseract.add(outputFile_tesseract);
	
		XmlElement name_out_tesseract = new XmlElement();
		name_out_tesseract.setName("name");
		name_out_tesseract.setValue("output_tesseract.jpg");
		name_out_tesseract.setLevel(2);
		name_out_tesseract.setAttribute(true);
		tesseract.add(name_out_tesseract);
	
		XmlElement processRectangle = new XmlElement();
		processRectangle.setName("processRectangle");
		processRectangle.setLevel(1);
		processRectangle.setAttribute(false);
		tesseract.add(processRectangle);
	
		XmlElement bottom_tesseract = new XmlElement();
		bottom_tesseract.setName("bottom");
		bottom_tesseract.setValue("0");
		bottom_tesseract.setLevel(2);
		bottom_tesseract.setAttribute(true);
		tesseract.add(bottom_tesseract);
	
		XmlElement direction = new XmlElement();
		direction.setName("direction");
		direction.setValue("ascending");
		direction.setLevel(2);
		direction.setAttribute(true);
		tesseract.add(direction);
	
		XmlElement left_tesseract = new XmlElement();
		left_tesseract.setName("left");
		left_tesseract.setValue("0");
		left_tesseract.setLevel(2);
		left_tesseract.setAttribute(true);
		tesseract.add(left_tesseract);
	
		XmlElement right_tesseract = new XmlElement();
		right_tesseract.setName("right");
		right_tesseract.setValue("0");
		right_tesseract.setLevel(2);
		right_tesseract.setAttribute(true);
		tesseract.add(right_tesseract);
	
		XmlElement top_tesseract = new XmlElement();
		top_tesseract.setName("top");
		top_tesseract.setValue("0");
		top_tesseract.setLevel(2);
		top_tesseract.setAttribute(true);
		tesseract.add(top_tesseract);
		
		nou.generateXml("D:\\tesseract.xml",tesseract);
	}
}

