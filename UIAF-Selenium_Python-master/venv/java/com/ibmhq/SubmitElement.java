package com.ibmhq;

import org.openqa.selenium.WebElement;

public class SubmitElement {

	public String execute(String type, String value) {

		String strstatus = null;
		try {
			WebElement element = Elements.find(type, value);

			if (element.isEnabled()) {
				element.submit();
				strstatus = "Passed%%The Element: " + hqdriver.stepObject + " is submitted";
			} else {
				strstatus = "Failed%%The Element: " + hqdriver.stepObject + " doesn't exist";
			}
		} catch (Exception e) {
			strstatus = "Failed%%" + e.getMessage();
		}
		return strstatus;
	}
}