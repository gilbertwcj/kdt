package com.gilbert.kdt;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class CopyOfPoiReader {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		InputStream in = new FileInputStream(
				"D:/eclipse-SDK-4.2/workspace/kdt/SeleniumGenerator.xlsx");
		try {

			Workbook wb = WorkbookFactory.create(in);
			Sheet case01Sheet = wb.getSheetAt(1);

			int testDataLineNum = 0;

			for (int i = 0; i < case01Sheet.getLastRowNum(); i++) {
				try {
					if ("数据".equals(case01Sheet.getRow(i).getCell(0)
							.getStringCellValue())) {
						testDataLineNum = i + 1;
					}
				} catch (NullPointerException e) {
					continue;
				}
			}

			HashMap<String, List<String>> dataMap = new HashMap<String, List<String>>();
			Row dataTitleRow = case01Sheet.getRow(testDataLineNum);
			int dataFirstCellNum = 2;
			int dataLastCellNum = dataTitleRow.getLastCellNum();
			String[] dataTitles = new String[dataLastCellNum - dataFirstCellNum
					+ 1];
			for (int i = dataFirstCellNum; i < dataLastCellNum; i++) {
				Cell c = dataTitleRow.getCell(i);
				dataMap.put(c.getStringCellValue(), new ArrayList<String>());
				dataTitles[i - 2] = c.getStringCellValue();
			}
			testDataLineNum++;

			boolean dataFlg = true;

			while (dataFlg) {
				Row row = case01Sheet.getRow(testDataLineNum);
				if (row != null) {
					for (int i = dataFirstCellNum; i < dataLastCellNum; i++) {
						dataMap.get(dataTitles[i - 2]).add(
								row.getCell(i).getStringCellValue());
					}
					testDataLineNum++;
				} else {
					dataFlg = false;
				}
			}

			int testOpLineNum = 0;

			for (int i = 0; i < case01Sheet.getLastRowNum(); i++) {
				try {
					if ("操作内容".equals(case01Sheet.getRow(i).getCell(0)
							.getStringCellValue())) {
						testOpLineNum = i + 2;
					}
				} catch (NullPointerException e) {
					continue;
				}
			}

			boolean opFlg = true;

			while (opFlg) {
				Row row = case01Sheet.getRow(testOpLineNum);
				if (row != null) {
					if ("launch".equals(row.getCell(2).getStringCellValue())) {
						System.out.println("driver.get("
								+ row.getCell(3).getStringCellValue() + ");");
					} else if ("perform".equals(row.getCell(2)
							.getStringCellValue())) {
						System.out.println(row.getCell(4).getStringCellValue());
						System.out.println(row.getCell(5).getStringCellValue());
						System.out.println(row.getCell(3).getStringCellValue());
					}

					testOpLineNum++;
				} else {
					opFlg = false;
				}
			}

			int end = 0;

			/*
			 * Sheet settingSheet = wb.getSheetAt(0); String driverName =
			 * settingSheet.getRow(3).getCell(2).getStringCellValue(); String
			 * driverExePath = ""; switch (driverName.charAt(0)) { case 'F':
			 * driverExePath =
			 * settingSheet.getRow(4).getCell(2).getStringCellValue(); break;
			 * 
			 * case 'C': driverExePath =
			 * settingSheet.getRow(5).getCell(2).getStringCellValue(); break;
			 * default: break; }
			 * 
			 * System.out.println(driverName);
			 * System.out.println(driverExePath);
			 * 
			 * Sheet caseSheet = wb.getSheetAt(1);
			 * 
			 * for(int i=0 ; i<caseSheet.getLastRowNum();i++){ try{
			 * System.out.println
			 * (caseSheet.getRow(i).getCell(0).getStringCellValue()); } catch
			 * (NullPointerException e){ continue; } }
			 */
		} finally {
			in.close();
		}

	}
}
