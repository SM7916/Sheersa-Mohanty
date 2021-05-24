package com.ibmhq;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class ValidateText {

	public String TextEquals(String type, String value, String text) {

		String strstatus = null;
		try {
			WebElement element = Elements.find(type, value);
			Assert.assertEquals(element.getText(), text);
			strstatus = "Passed%%Expected text: " + text + " matched with text on the UI: " + element.getText();
		} catch (Exception e) {
			strstatus = "Failed%%" + e.getMessage();
		}
		return strstatus;
	}

}
