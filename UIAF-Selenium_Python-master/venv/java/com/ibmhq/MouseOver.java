package com.ibmhq;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class MouseOver {

	public String execute(String type, String value) {

		String strstatus = null;
		try {
			WebElement element = Elements.find(type, value);

			if (element.isEnabled()) {
				JavascriptExecutor jsExec = (JavascriptExecutor) Browser.Driver;
				String javaScript = "var evObj = document.createEvent('MouseEvents');evObj.initEvent(\"mouseover\",true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);arguments[0].dispatchEvent(evObj);";
				jsExec.executeScript(javaScript, element);
				strstatus = "Passed%%The Element: " + hqdriver.stepObject + " got mouseover successfully";
			} else {
				strstatus = "Failed%%The Element: " + hqdriver.stepObject + "doesn't exist";
			}
		} catch (Exception e) {
			strstatus = "Failed%%" + e.getMessage();
		}
		return strstatus;

	}
}
