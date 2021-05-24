package com.ibmhq;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ValidateTableCell {

	public String execute(String type, String value, String ItemValue) {

		String strstatus = null;
		try {
			String celltext = null;

			List<WebElement> rows_table = Browser.Driver.findElements(By.tagName("tr"));
			int rows_count = rows_table.size();

			for (int row = 0; row < rows_count; row++) {
				List<WebElement> Columns_row = rows_table.get(row).findElements(By.tagName("td"));
				int columns_count = Columns_row.size();
				for (int column = 0; column < columns_count; column++) {
					celltext = Columns_row.get(column).getText();
				}
			}
			if (celltext.contentEquals(ItemValue)) {
				strstatus = "Passed%%The Table cell value: " + celltext + "is matched with the expected value: "
						+ ItemValue;
			} else {
				strstatus = "Failed%%The Table cell value: " + celltext + "is NOT matched with the expected value: "
						+ ItemValue;
			}
		} catch (Exception e) {
			strstatus = "Failed%%" + e.getMessage();
		}
		return strstatus;
	}

	public String validateDefinedTableData(String type, String value, String ItemValue) {

		String strstatus = null;
		try {
			String celltext = null;
			System.out.println("value=" + value);
			WebElement mytable = Elements.find(type, value);

			List<WebElement> rows_table = mytable.findElements(By.tagName("tr"));
			int rows_count = rows_table.size();
			System.out.println("row=" + rows_count);

			for (int row = 0; row < rows_count; row++) {
				List<WebElement> Columns_row = rows_table.get(row).findElements(By.tagName("td"));
				int columns_count = Columns_row.size();
				System.out.println("Number of cells In Row " + row + " are " + columns_count);

				for (int column = 0; column < columns_count; column++) {
					String celtext = Columns_row.get(column).getText();
					System.out.println(
							"Cell Value Of row number " + row + " and column number " + column + " Is " + celtext);
					if (celtext.contains(ItemValue)) {
						strstatus = "Passed";
					}
				}
			}

			if (strstatus.equals("Passed")) {
				strstatus = "Passed%%The Table cell has value: " + celltext + "is matched with the expected value: "
						+ ItemValue;
			} else {
				strstatus = "Failed%%The Table cell value: " + celltext + "is NOT matched with the expected value: "
						+ ItemValue;
			}

		} catch (Exception e) {
			strstatus = "Failed%%" + e.getMessage();
		}
		return strstatus;
	}

}
