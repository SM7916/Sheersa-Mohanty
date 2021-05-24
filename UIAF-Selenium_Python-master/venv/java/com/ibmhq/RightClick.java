package com.ibmhq;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

public class RightClick {
	public String execute(String type, String value) {

		String strstatus = null;
		try {
			WebElement element = Elements.find(type, value);
			//RightClick object= PageFactory.initElements(Browser.Driver, RightClick.class);
			if (element.isEnabled()) {
				//Actions action = new Actions(Browser.Driver);
				//action.contextClick(element).perform();
				
				JavascriptExecutor js = (JavascriptExecutor) Browser.Driver;

				String javaScript = "var evt = document.createEvent('MouseEvents');"
				                + "var RIGHT_CLICK_BUTTON_CODE = 2;"
				                + "evt.initMouseEvent('contextmenu', true, true, window, 1, 0, 0, 0, 0, false, false, false, false, RIGHT_CLICK_BUTTON_CODE, null);"
				                + "arguments[0].dispatchEvent(evt)";

				js.executeScript(javaScript, element);
				
				strstatus = "Passed%%The Element: " + hqdriver.stepObject + " is right clicked";
			} else {
				strstatus = "Failed%%The Element: " + hqdriver.stepObject + " doesn't exist";
			}
		} catch (Exception e) {
			strstatus = "Failed%%" + e;
		}
		return strstatus;
	}
}
