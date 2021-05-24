package com.ibmhq;

import org.openqa.selenium.WebElement;

public class FrameXpath {
	public String XpathConstruction(String keyword) {

		String strstatus = null;
		try {
			String xpath = "//span[contains(text()," + keyword + ")]";
			WebElement element = Elements.find("XPath", xpath);
			element.click();
			strstatus = "Passed%%XPath is constructed and clicked on the element";
		} catch (Exception e) {
			strstatus = "Failed%%" + e.getMessage();
		}
		return strstatus;
	}
}
