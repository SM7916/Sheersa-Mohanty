package com.ibmhq;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.JavascriptExecutor;

public class SetFocus {

	public String execute(String type, String value) {

		String strstatus = null;
		try {
			WebElement element = Elements.find(type, value);

			if (element.isEnabled()) {
				if (element.getTagName() == "input") {
					element.sendKeys("");
				} else {
					new Actions(Browser.Driver).moveToElement(element).perform();
				}
				strstatus = "Passed%%The Element: " + hqdriver.stepObject + " is set focus on";
			} else {
				strstatus = "Failed%%The Element: " + hqdriver.stepObject + " doesn't exist";
			}
		} catch (Exception e) {
			strstatus = "Failed%%" + e.getMessage();
		}
		return strstatus;
	}
	public String scrollToTop() {
        String strstatus = null;
        JavascriptExecutor je = (JavascriptExecutor) Browser.Driver;
        try {
            je.executeScript("window.scrollTo(0, -document.body.scrollHeight)");
            strstatus = "Passed%% The Page is been scroll to top";
        } catch (Exception e) {
            strstatus = "Failed%% : " + e.getMessage();
        }
        return strstatus;
    }
}
