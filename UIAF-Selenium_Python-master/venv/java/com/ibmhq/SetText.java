package com.ibmhq;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class SetText {
	public String execute(String type, String value, String text) {

		String strstatus = null;
		try {
			WebElement element = Elements.find(type, value);
			if (element != null) {
				element.clear();
				element.sendKeys(text);

				strstatus = "Passed%%The Element: " + hqdriver.stepObject + " is set with text: " + text;
			} else {
				strstatus = "Failed%%The Element: " + hqdriver.stepObject + " doesn't exist";
			}
		} catch (Exception e) {
			strstatus = "Failed%%" + e.getMessage();
		}
		return strstatus;
	}

	public String executeclear(String type, String value, String text) {

		String strstatus = null;
		try {
			WebElement element = Elements.find(type, value);
			if (element.isEnabled()) {
				Actions navigator = new Actions(Browser.Driver);
				navigator.click(element).sendKeys(Keys.END).keyDown(Keys.SHIFT).sendKeys(Keys.HOME).keyUp(Keys.SHIFT)
						.sendKeys(Keys.BACK_SPACE).sendKeys(text).perform();
				strstatus = "Passed%%The Element: " + hqdriver.stepObject + " is set with text: " + text;
			} else {
				strstatus = "Failed%%The Element: " + hqdriver.stepObject + " doesn't exist";
			}
		} catch (Exception e) {
			strstatus = "Failed%%" + e.getMessage();
		}
		return strstatus;
	}

	public String SetTextActions(String type, String value, String text) {
		String strstatus = null;
		try {
			Thread.sleep(1000);
			WebElement element = Elements.find(type, value);
			if (element.isEnabled()) {
				// Actions navigator = new Actions(Browser.Driver);
				// navigator.moveToElement(element).build().perform();
				// Thread.sleep(1000);
				// navigator.click(element).keyDown(Keys.SHIFT).sendKeys(Keys.HOME).keyUp(Keys.SHIFT).sendKeys(Keys.BACK_SPACE).sendKeys(text).sendKeys(Keys.ENTER).build().perform();
				// //
				// navigator.click(element).sendKeys(text).sendKeys(Keys.ENTER).perform();
				JavascriptExecutor myExecutor = ((JavascriptExecutor) Browser.Driver);
				System.out.println("Writing: " + text);
				myExecutor.executeScript("arguments[0].value='" + text + "';", element);

				strstatus = "Passed%%The Element: " + hqdriver.stepObject + " is set with text: " + text;

			} else {
				strstatus = "Failed%%The Element: " + hqdriver.stepObject + " doesn't exist";
			}
		} catch (Exception e) {
			strstatus = "Failed%%" + e.getMessage();
		}
		return strstatus;
	}
	// ***** Added for Time stamp and random/unique

	public String settextusingtimestamp(String type, String value, String text) {

		String strstatus = null;
		try {
			WebElement element = Elements.find(type, value);

			if (element != null) {
				element.clear();
				String timeStamp = new SimpleDateFormat("ddHHmm").format(new Date());
				String textWithTimeStamp = text + timeStamp;
				element.sendKeys(textWithTimeStamp);
				strstatus = "Passed%%The Element: " + hqdriver.stepObject + " is set with text: " + text;
			} else {
				strstatus = "Failed%%The Element: " + hqdriver.stepObject + " doesn't exist";
			}
		} catch (Exception e) {
			strstatus = "Failed%%" + e.getMessage();
		}
		return strstatus;
	}
	
	public String SetPayNumber(String type, String value, String text) {

		String strstatus = null;
		try {
			WebElement element = Elements.find(type, value);

			if (element != null) {
				element.clear();
				String line = text;
				String regex = "[^\\d]+";
			    String[] str = line.split(regex);
			    System.out.println(str[1]);
				element.sendKeys(str[1]);
				Thread.sleep(1500);
				strstatus = "Passed%%The Element: " + hqdriver.stepObject + " is set with text: " + str[1];
			} else {
				strstatus = "Failed%%The Element: " + hqdriver.stepObject + " doesn't exist";
			}
		} catch (Exception e) {
			strstatus = e.getMessage();
		}
		return strstatus;
	}
	
	
	public String SetPONumber(String type, String value, String text) {

		String strstatus = null;
		try {
			WebElement element = Elements.find(type, value);

			if (element != null) {
				element.clear();
				String line = text;
				String regex = "\\s+";
			    String[] str = line.split(regex);
			    System.out.println(str.length);
			    for(String str1: str){
			    	System.out.println(str1);
			    }
			    System.out.println(str[4]);
				element.sendKeys(str[4]);
				Thread.sleep(1500);
				strstatus = "Passed%%The Element: " + hqdriver.stepObject + " is set with text: " + str[4];
			} else {
				strstatus = "Failed%%The Element: " + hqdriver.stepObject + " doesn't exist";
			}
		} catch (Exception e) {
			strstatus = e.getMessage();
		}
		return strstatus;
	}

}