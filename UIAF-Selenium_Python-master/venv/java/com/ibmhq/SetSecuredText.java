package com.ibmhq;

import java.util.Base64;

import org.openqa.selenium.WebElement;

public class SetSecuredText {

	public String execute(String type, String value, String text) {

		String strstatus = null;
		String decodePassword = text;
		try {
			WebElement element = Elements.find(type, value);

			if (element != null) {
				String strtext = new String(Base64.getDecoder().decode(decodePassword.getBytes()));
				element.clear();
				element.sendKeys(strtext);
				strstatus = "Passed%%The Element: " + hqdriver.stepObject + " is set with text: " + text;
			} else {
				strstatus = "Failed%%The Element: " + hqdriver.stepObject + " doesn't exist";
			}
		} catch (Exception e) {
			strstatus = "Failed%%" + e.getMessage();
		}
		return strstatus;
	}

}
