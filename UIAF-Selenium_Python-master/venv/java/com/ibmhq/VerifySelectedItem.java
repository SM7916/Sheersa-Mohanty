package com.ibmhq;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class VerifySelectedItem {
	public String execute(String type, String value, String itemlabel, String itemvalue) {

		String strstatus = null;
		try {
			WebElement element = Elements.find(type, value);
			WebElement option = new Select(element).getFirstSelectedOption();
			String selectedLabel = option.getText();
			String selectedValue = option.getAttribute("value");

			if (itemlabel != null) {
				if (selectedLabel.equals(itemlabel)) {
					strstatus = "Passed%%The selected option: " + selectedLabel + " matched with expected option of: "
							+ itemlabel;
				} else {
					strstatus = "Failed%%The selected option: " + selectedLabel + " does not match expected option of: "
							+ itemlabel;
				}
			}

			if (itemvalue != null) {
				if (element.getText().equals(itemvalue)) {
					strstatus = "Passed%%The selected option: " + selectedValue + " does not match expected option of: "
							+ itemvalue;
				} else {
					strstatus = "Failed%%The selected option: " + selectedValue + " does not match expected option of: "
							+ itemvalue;
				}
			}

		} catch (Exception e) {
			strstatus = "Failed%%" + e.getMessage();
		}
		return strstatus;
	}
}