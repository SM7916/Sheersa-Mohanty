package com.ibmhq;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class SelectItem {
	public String execute(String type, String value, String ItemType, String Item) {

		String strstatus = null;

		try {
			WebElement element = Elements.find(type, value);

			if (element.isEnabled()) {
				if (ItemType == "Visible Text") {
					new Select(element).selectByVisibleText(Item);
				} else if (ItemType == "Value") {
					new Select(element).selectByValue(Item);
				} else if (ItemType == "Index") {
					int index = Integer.valueOf(Item);
					new Select(element).selectByIndex(index);
				}
				strstatus = "Passed%%The Element: " + hqdriver.stepObject + " is selected with value: " + Item;
			} else {
				strstatus = "Failed%%The Element: " + hqdriver.stepObject + " doesn't exist";
			}
		} catch (Exception e) {
			strstatus = "Failed%%" + e.getMessage();
		}
		return strstatus;
	}

	public String SelectDropdown(String type, String value, String Item) {

		String strstatus = null;

		try {
			WebElement element = Elements.find(type, value);

			Select itemList = new Select(element);
			List<WebElement> dropdown = itemList.getOptions();

			try {
				new Select(element).selectByValue(Item);
			} catch (Exception ex) {
				System.out.println("element failed to select by value so selecting by text");
				new Select(element).selectByVisibleText(Item);
			}
			strstatus = "Passed%%The Element: " + hqdriver.stepObject + " is selected with: "
					+ element.getAttribute("value");
		}

		catch (Exception e) {
			// strstatus = "Failed%%The Element: " + hqdriver.stepObject + "
			// doesn't exist" + e.getMessage();
		}
		return strstatus;
	}
}