package com.mps.batmanii.ocrWebManager.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mps.batmanii.ocrWebManager.beans.Exec;
import com.mps.batmanii.ocrWebManager.beans.ExecContainer;
import com.mps.batmanii.ocrWebManager.beans.SelectedExecs;

@Service
public class DWRService {

	@Autowired
	SelectedExecs selectedExecs;

	@Autowired
	ExecContainer execContainer;

	private final static Logger logger = LoggerFactory
			.getLogger(DWRService.class);

	public DWRService() {
	}

	public String verifySelected(String execName) {
		Exec thisExec = getByExecName(execName);
		List<Exec> execs = selectedExecs.getSelectedExecs();
		for (Exec exec : execs) {
			if (exec.getExecName().equals(execName)) {
				logger.info("Executable "
						+ execName
						+ " has already been selected!Modify parameters by selecting it from the 'Selected Executables' section.");
				return "Executable "
						+ execName
						+ " has already been selected!Modify parameters by selecting it from the 'Selected Executables' section.";
			} else {
				logger.info("Se verifica execTypes");
				List<String> allExecTypes = exec.getAllExecTypes();
				logger.info("Print l1");
				logger.info("----------------------------------");
				printList(allExecTypes);
				List<String> allExecTypesThis = thisExec.getAllExecTypes();
				logger.info("Print l2");
				logger.info("----------------------------------");
				printList(allExecTypesThis);
				Collections.sort(allExecTypes, new CustomComparator());
				Collections.sort(allExecTypesThis, new CustomComparator());
				logger.info("Print l1 sorted");
				logger.info("----------------------------------");
				printList(allExecTypes);
				logger.info("Print l2 sorted");
				logger.info("----------------------------------");
				printList(allExecTypesThis);
				boolean equals = allExecTypes.equals(allExecTypesThis);
				if (equals == true) {
					logger.info("Executable " + execName
							+ " can not be selected!");
					return "Executable "
							+ execName
							+ " can not be selected!There has already been selected executable "
							+ exec.getExecName()
							+ " which has the same functions!";
				}
			}
		}
		logger.info("Acest executabil este selectat pentru prima data!");
		return "Ok";
	}

	public Exec getByExecName(String execName) {
		List<Exec> execs = execContainer.getExecs();
		for (Exec exec : execs) {
			if (exec.getExecName().equals(execName))
				return exec;
		}
		return null;
	}

	public boolean compareLists(List<String> l1, List<String> l2) {
		return false;
	}

	public void printList(List<String> l) {
		for (int i = 0; i < l.size(); i++) {
			logger.info(l.get(i));
		}
		logger.info("----------------------------------");
	}

	public String verifySubmit() {

		Map<Integer, List<Exec>> map = new HashMap<Integer, List<Exec>>();
		for (int i = 1; i <= 7; i++) {
			map.put(i, null);
		}
		List<Exec> execs = selectedExecs.getSelectedExecs();
		if (execs.size() == 0)
			return "You have not selected any executables!";

		for (Exec exec : execs) {
			int which = 0;
			if (exec.getExecType().equals("preprocessing")) {
				which = 1;
			}
			if (exec.getExecType().equals("binarization")) {
				which = 2;
			}
			if (exec.getExecType().equals("layout")) {
				which = 3;
			}
			if (exec.getExecType().equals("paging")) {
				which = 4;
			}
			if (exec.getExecType().equals("ocr")) {
				which = 5;
			}
			if (exec.getExecType().equals("hierarchy")) {
				which = 6;
			}
			if (exec.getExecType().equals("pdf-exporter")) {
				which = 7;
			}
			List<Exec> list = map.get(which);
			if (list == null) {
				list = new ArrayList<Exec>();
			}
			list.add(exec);
			map.put(which, list);
		}
		printMap(map);

		int min = 8;
		int max = 0;
		List<Integer> whichOnes = new ArrayList<Integer>(7);
		logger.info(whichOnes.size() + "size");
		for (Map.Entry<Integer, List<Exec>> entry : map.entrySet()) {
			List<Exec> value = entry.getValue();
			if (value != null) {
				min = Math.min(min, entry.getKey());
				max = Math.max(max, entry.getKey());
				logger.info((entry.getKey() - 1) + "");
				whichOnes.add(entry.getKey() - 1, 1);
			} else {
				whichOnes.add(entry.getKey() - 1, 0);
			}
		}
		logger.info("Min:" + min + " Max:" + max);
		for (int i = 0; i < 7; i++) {
			logger.info((i + 1) + " " + whichOnes.get(i));
		}
		if (min > 2) {
			logger.info("Primul executabil selectat e mai mare de nivelul 2");
			return "You must begin the processing from the level preprocessing or binarization!Please select the appropriate executable!";
		}
		String result = "";
		if (min == max) {
			result = "Ok";
			logger.info("Selectate doar executabile de tipul " + min);
		} else {
			if (min == (max - 1)) {
				result = "Ok";
				logger.info("Selectate doar executabilele de doua tipuri consecutive "
						+ min + " si " + max);
			} else {
				boolean ok = true;
				for (int i = min + 1; i <= max - 1; i++) {
					if (whichOnes.get(i - 1) == 0) {
						logger.info(i + " neselectat");
						ok = false;
						result += "Type " + getType(i) + " not selected!";
					}
				}
				if (ok == true)
					result = "Ok";
			}
		}
		return result;
	}

	public String getType(Integer poz) {
		String result;
		switch (poz) {
		case 1:
			result = "preprocessing";
			break;
		case 2:
			result = "binarization";
			break;
		case 3:
			result = "layout";
			break;
		case 4:
			result = "paging";
			break;
		case 5:
			result = "ocr";
			break;
		case 6:
			result = "hierarchy";
			break;
		case 7:
			result = "pdf-exporter";
			break;
		default:
			result = "";
			break;
		}
		return result;
	}

	public void printMap(Map<Integer, List<Exec>> map) {
		logger.info("Print map-----------------------");
		for (Map.Entry<Integer, List<Exec>> entry : map.entrySet()) {
			logger.info("Tipul " + entry.getKey());
			List<Exec> value = entry.getValue();
			if (value != null)
				for (Exec exec : value) {
					logger.info(exec.getExecName() + "-" + exec.getExecType());
				}
		}

		logger.info("End print map-----------------------");
	}

	public static void main(String args[]) {
		DWRService dwrService = new DWRService();
		List<Exec> mySelectedExecs = new ArrayList<Exec>();
		mySelectedExecs.add(dwrService.buildExec("rotate", "preprocessing"));
		mySelectedExecs.add(dwrService.buildExec("crop", "preprocessing"));
		// mySelectedExecs.add(dwrService.buildExec("otsu", "binarization"));
		// mySelectedExecs.add(dwrService.buildExec("otsu1", "binarization"));
		mySelectedExecs.add(dwrService.buildExec("full_layout", "layout"));
		// mySelectedExecs.add(dwrService.buildExec("paging", "paging"));
		// mySelectedExecs.add(dwrService.buildExec("tesseract", "ocr"));
		// mySelectedExecs
		// .add(dwrService.buildExec("full_hierarchy", "hierarchy"));
		// mySelectedExecs.add(dwrService.buildExec("pdf", "pdf-exporter"));
		dwrService.selectedExecs = new SelectedExecs();
		dwrService.selectedExecs.setSelectedExecs(mySelectedExecs);
		logger.info("Rezultat: " + dwrService.verifySubmit());
	}

	public Exec buildExec(String execName, String execType) {
		Exec exec = new Exec();
		exec.setExecName(execName);
		exec.setExecType(execType);
		return exec;
	}
}

class CustomComparator implements Comparator<String> {
	@Override
	public int compare(String o1, String o2) {
		return o1.compareTo(o2);
	}
}
