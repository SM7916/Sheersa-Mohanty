package com.ibmhq;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ExplicitWaitType {

	@SuppressWarnings("unused")
	public static String presenceOfElementLocated(String type, String value, String wtime) {

		String strstatus = null;
		try {

			WebElement myDynamicElement = (new WebDriverWait(Browser.Driver, Integer.valueOf(wtime)))
					.until(ExpectedConditions.presenceOfElementLocated(com.ibmhq.Locators.find(type, value)));
			strstatus = "Passed%%The object is located: ";

		}

		catch (Exception e) {
			strstatus = "Failed%%" + e.getMessage();
			System.out.println("Locator: " + type + "   value: " + value + " Not Found");
		}
		return strstatus;
	}

	@SuppressWarnings("unused")
	public static String elementToBeClickable(String type, String value, String wtime) {

		String strstatus = null;
		try {

			WebElement myDynamicElement = (new WebDriverWait(Browser.Driver, Integer.valueOf(wtime)))
					.until(ExpectedConditions.elementToBeClickable(com.ibmhq.Locators.find(type, value)));

		}

		catch (Exception e) {
			strstatus = "Failed%%" + e.getMessage();
		}
		return strstatus;
	}

	@SuppressWarnings("unused")
	public static String visibilityOfElementLocated(String type, String value, String wtime) {
		String strstatus = null;
		int waitTime = Integer.valueOf(wtime);
		WebDriverWait wait = new WebDriverWait(Browser.Driver, waitTime);
		try {
			WebElement myDynamicElement = wait
					.until(ExpectedConditions.visibilityOfElementLocated(com.ibmhq.Locators.find(type, value)));
			strstatus = "Passed%%The object is located: ";

		}

		catch (Exception e) {
			strstatus = "Failed%%" + e.getMessage();
		}
		return strstatus;
	}

}