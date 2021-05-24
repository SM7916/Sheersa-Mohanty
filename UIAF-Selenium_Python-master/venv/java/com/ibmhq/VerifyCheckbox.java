package com.ibmhq;

import org.openqa.selenium.WebElement;

public class VerifyCheckbox {

	public String execute(String type, String value, boolean IsChecked) {

		String strstatus = null;
		try {
			WebElement element = Elements.find(type, value);

			if (element.isSelected() == IsChecked) {
				strstatus = "Passed%%The Checkbox: " + hqdriver.stepObject + " is selected";
			} else {
				strstatus = "Failed%%The Checkbox: " + hqdriver.stepObject + " is NOT selected";
			}
		} catch (Exception e) {
			strstatus = "Failed%% Exception" + e.getMessage();
		}
		return strstatus;
	}
}