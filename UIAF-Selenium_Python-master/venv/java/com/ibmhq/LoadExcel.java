package com.ibmhq;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.util.ZipSecureFile;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class LoadExcel {

	String strreadval;

	public Sheet loadworksheet(String filePath, String fileName, String sheetName) throws IOException {

		File file = new File(filePath + "\\" + fileName);
		FileInputStream inputStream = new FileInputStream(file);

		Workbook xlworkbook = null;
		String fileExtensionName = fileName.substring(fileName.lastIndexOf("."));
		ZipSecureFile.setMinInflateRatio(0);

		if (fileExtensionName.equals(".xlsx")) {
			xlworkbook = new XSSFWorkbook(inputStream);
		} else if (fileExtensionName.equals(".xls")) {
			xlworkbook = new HSSFWorkbook(inputStream);
		}

		Sheet xlworksheet = xlworkbook.getSheet(sheetName);
		// int rowCount =
		// xlworksheet.getLastRowNum()-xlworksheet.getFirstRowNum();
		// System.out.print(rowCount);
		return (xlworksheet);
	}

	public String readExcel(Sheet sheetName, String rowName, String colName) {
		try {
			// System.out.println("Inside the method readExcel.");
			int col_Num = -1, row_Num = -1;
			XSSFRow row = (XSSFRow) sheetName.getRow(0);
			for (int i = 0; i < row.getLastCellNum(); i++) {
				// System.out.println(colName +
				// ":"+row.getCell(i).getStringCellValue().trim());
				if (row.getCell(i).getStringCellValue().trim().equals(colName.trim())) {
					col_Num = i;
					// System.out.println(colName +
					// ":"+row.getCell(i).getStringCellValue().trim() +
					// ":"+col_Num);
					break;
				}
			}
			if (col_Num == -1)
				return "";

			int RowNum = sheetName.getLastRowNum() + 1;
			for (int i = 0; i < RowNum; i++) {
				row = (XSSFRow) sheetName.getRow(i);

				if (row.getCell(0).getStringCellValue().trim().equals(rowName.trim())) {
					row_Num = i;
					// System.out.println(rowName +
					// ":"+row.getCell(0).getStringCellValue().trim()+":"+row_Num);
					break;
				}
			}
			// System.out.println("row_Num:"+row_Num);
			if (row_Num == -1)
				return "";
			return readExcel(sheetName, row_Num + 1, col_Num);
		} catch (Exception e) {
			e.printStackTrace();
			return "Row: " + rowName + " or Column: " + colName + " does not exist in excel";
		}
	}

	public String readExcel(Sheet sheetName, int row_Num, String colName) {
		try {
			int col_Num = -1;
			XSSFRow row = (XSSFRow) sheetName.getRow(0);
			for (int i = 0; i < row.getLastCellNum(); i++) {
				// System.out.println(colName +
				// ":"+row.getCell(i).getStringCellValue().trim());
				if (row.getCell(i).getStringCellValue().trim().equals(colName.trim())) {
					col_Num = i;
					// System.out.println(colName +
					// ":"+row.getCell(i).getStringCellValue().trim() +
					// ":"+col_Num);
					break;
				}
			}
			// if (col_Num == -1 || row_Num == -1){
			// return "";
			// }else{
			// return readExcel(sheetName, row_Num, col_Num);
			// }
			// System.out.println("row_Num:"+row_Num);
			if (row_Num == -1)
				return "";
			return readExcel(sheetName, row_Num, col_Num);
		} catch (Exception e) {
			e.printStackTrace();
			return "Column: " + colName + " does not exist in excel";
		}
	}

	public String readExcel(Sheet sheetName, int rowCount, int ColCount) throws IOException {
		try {

			// System.out.println("rowCount:"+rowCount+": ColCount"+ColCount);
			// for (int i = 0; i < rowCount+1; i++) {
			DataFormatter formatter = new DataFormatter();
			Row row = sheetName.getRow(rowCount);

			// for (int j = 0; j < ColCount; j++) {
			// strreadval = row.getCell(ColCount).getStringCellValue();
			String strreadval = formatter.formatCellValue(row.getCell(ColCount));
			// }

			// }
			return strreadval;
		} catch (Exception e) {
			e.printStackTrace();
			return "Unable to fetch value from sheet: " + sheetName;
		}

	}

	public void writeExcel(String workbookname, Sheet sheetName, int rowCount, int ColCount, String StrValue)
			throws IOException {

		Workbook workbook = sheetName.getWorkbook();

		String spath = hqdriver.testdatapath + "\\" + workbookname;

		FileOutputStream out = new FileOutputStream(new File(spath));

		Row row = null;

		if (sheetName.getRow(rowCount) != null) {
			row = sheetName.getRow(rowCount);
		} else {
			row = sheetName.createRow(rowCount);
		}

		Cell cell = null;

		if (row.getCell(ColCount) != null) {
			cell = row.getCell(ColCount);
		} else {
			cell = row.createCell(ColCount);
		}
		cell.setCellValue(StrValue);

		workbook.write(out);
		out.close();

	}

	public XSSFWorkbook createreport(String fPath, String cTime) throws IOException, InvalidFormatException {

		String spath = fPath + "\\" + "IBMhq Execution Summary Report.xlsx";
		FileOutputStream out = new FileOutputStream(new File(spath));

		XSSFWorkbook workbook = new XSSFWorkbook();// workbook.setMissingCellPolicy(HSSFRow.RETURN_NULL_AND_BLANK);

		XSSFSheet summarysheet = workbook.createSheet("Summary Report");

		XSSFCellStyle style = workbook.createCellStyle();
		style.setAlignment(HorizontalAlignment.CENTER);
		style.setFillForegroundColor(IndexedColors.DARK_BLUE.index);
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		Font font = workbook.createFont();
		font.setColor(IndexedColors.WHITE.index);
		style.setFont(font);
		BorderStyle thin = BorderStyle.THIN;
		short black = IndexedColors.BLACK.getIndex();
		style.setBorderRight(thin);
		style.setRightBorderColor(black);
		style.setBorderBottom(thin);
		style.setBottomBorderColor(black);
		style.setBorderLeft(thin);
		style.setLeftBorderColor(black);
		style.setBorderTop(thin);
		style.setTopBorderColor(black);
		createBorderedStyle(style);

		XSSFRow row = summarysheet.createRow(0);

		for (int i = 0; i <= 14; i++) {
			Cell cell = row.createCell(i);
			cell.setCellStyle(style);
		}

		row.getCell(0).setCellValue("EXECUTION DATE");
		row.getCell(1).setCellValue("TESTED BROWSER");
		row.getCell(2).setCellValue("REQUIREMENT NAME");
		row.getCell(3).setCellValue("TESTSET NAME");
		row.getCell(4).setCellValue("TEST CASE NAME");
		row.getCell(5).setCellValue("EXECUTION STATUS");
		row.getCell(6).setCellValue("ITERATION NO");
		row.getCell(7).setCellValue("DEFECTS LIST");
		row.getCell(8).setCellValue("TOTAL NUMBER OF STEPS EXECUTED");
		row.getCell(9).setCellValue("STEPS PASSED");
		row.getCell(10).setCellValue("STEPS FAILED");
		row.getCell(11).setCellValue("EXECUTION TIME IN SECONDS");
		row.getCell(12).setCellValue("EXECUTION START TIME");
		row.getCell(13).setCellValue("EXECUTION END TIME");
		row.getCell(14).setCellValue("REPORT PATH");

		for (int colNum = 0; colNum < row.getLastCellNum(); colNum++)
			summarysheet.autoSizeColumn(colNum);

		XSSFSheet defectsheet = (XSSFSheet) workbook.createSheet("Defects");
		XSSFRow drow = defectsheet.createRow(0);

		for (int i = 0; i <= 16; i++) {
			Cell dcell = drow.createCell(i);
			dcell.setCellStyle(style);
		}

		drow.getCell(0).setCellValue("DETEDTED ON DATE");
		drow.getCell(1).setCellValue("DEFECT ID");
		drow.getCell(2).setCellValue("STATUS");
		drow.getCell(3).setCellValue("PROGRAM NAME");
		drow.getCell(4).setCellValue("PROJECT");
		drow.getCell(5).setCellValue("BROWSER");
		drow.getCell(6).setCellValue("REQUIREMENT NAME");
		drow.getCell(7).setCellValue("TESTSET NAME");
		drow.getCell(8).setCellValue("TESTCASE NAME");
		drow.getCell(9).setCellValue("STEP NO");
		drow.getCell(10).setCellValue("STEP DESCRIPTION");
		drow.getCell(11).setCellValue("SUMMARY");
		drow.getCell(12).setCellValue("PRIORITY");
		drow.getCell(13).setCellValue("SEVERITY");
		drow.getCell(14).setCellValue("DETECTED BY");
		drow.getCell(15).setCellValue("ASSIGNED TO");
		drow.getCell(16).setCellValue("SCREENSHOT PATH");

		for (int colNum = 0; colNum < drow.getLastCellNum(); colNum++)
			defectsheet.autoSizeColumn(colNum);

		workbook.write(out);
		out.close();
		return workbook;

	}

	public CellStyle createBorderedStyle(XSSFCellStyle style) {
		BorderStyle thin = BorderStyle.THIN;
		short black = IndexedColors.BLACK.getIndex();

		style.setBorderRight(thin);
		style.setRightBorderColor(black);
		style.setBorderBottom(thin);
		style.setBottomBorderColor(black);
		style.setBorderLeft(thin);
		style.setLeftBorderColor(black);
		style.setBorderTop(thin);
		style.setTopBorderColor(black);
		return style;
	}
}