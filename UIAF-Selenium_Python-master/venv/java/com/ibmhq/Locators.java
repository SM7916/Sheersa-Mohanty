package com.ibmhq;

import org.openqa.selenium.By;

public class Locators {

	public static By find(String IDType, String ID) {

		// assert Browser.Driver == null: "Error browser is not opened. Use
		// Launch URL action";

		By foundElement = null;

		switch (IDType) {
		case "Class Name":
			foundElement = By.className(ID);
			break;
		case "Css Selector":
			foundElement = By.cssSelector(ID);
			break;
		case "ID":
			foundElement = By.id(ID);
			break;
		case "Link Text":
			foundElement = By.linkText(ID);
			break;
		case "XPath":
			foundElement = By.xpath(ID);
			break;
		case "Name":
			foundElement = By.name(ID);
			break;
		case "Partial Link Text":
			foundElement = By.partialLinkText(ID);
			break;
		case "Tag Name":
			foundElement = By.tagName(ID);
			break;
		default:
			foundElement = By.id(ID);
		}

		return foundElement;

	}
}
