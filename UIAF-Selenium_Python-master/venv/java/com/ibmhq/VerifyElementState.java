package com.ibmhq;

import org.openqa.selenium.WebElement;

public class VerifyElementState {

	public String execute(String type, String value, String verify) {

		String strstatus = null;
		try {
			WebElement element = Elements.find(type, value);

			switch (verify) {
			case "enabled":
				if (element.isEnabled()) {
					strstatus = "Passed%%The Element: " + hqdriver.stepObject + " is enabled";
				} else {
					strstatus = "Failed%%The Element: " + hqdriver.stepObject
							+ " is NOT enabled and the current state is: " + element.isEnabled();
				}
				break;

			case "visible":
				if (element.isDisplayed()) {
					strstatus = "Passed%%The Element: " + hqdriver.stepObject + " is visible";
				} else {
					strstatus = "Failed%%The Element: " + hqdriver.stepObject + " is NOT visible and current state is: "
							+ element.isDisplayed();
				}
				break;
			}
		} catch (Exception e) {
			strstatus = "Failed%%" + e.getMessage();
		}
		return strstatus;
	}
}