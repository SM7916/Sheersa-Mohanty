package com.ibmhq;

import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class SendKeys {

	public String sendvalue(String type, String value, String key) {

		String strstatus = null;
		try {
			WebElement element = Elements.find(type, value);

			if (key != null) {
				String[] text = new String[] { key };

				if (element.isEnabled()) {
					element.sendKeys(text);
					strstatus = "Passed%%The Element: " + hqdriver.stepObject + " is set with value: " + key;
				} else {
					strstatus = "Failed%%The Element: " + hqdriver.stepObject + " doesn't exist";
				}
			}
		} catch (Exception e) {
			strstatus = "Failed%%" + e.getMessage();
		}
		return strstatus;
	}

	public String sendkey(String type, String value, String key) {

		String strstatus = null;
		try {
			WebElement element = Elements.find(type, value);

			if (element.isEnabled()) {
				if (key != null) {
					switch (key) {
					case "ALT":
						// For the Alt key.
						element.sendKeys(Keys.ALT);
						break;
					case "ARROW DOWN":
						// For the Left arrow key.
						element.sendKeys(Keys.ARROW_DOWN);
						break;
					case "ARROW LEFT":
						// For the left arrow key.
						element.sendKeys(Keys.ARROW_LEFT);
						break;
					case "ARROW RIGHT":
						// For the right arrow key.
						element.sendKeys(Keys.ARROW_RIGHT);
						break;
					case "ARROW UP":
						// For the up arrow key.
						element.sendKeys(Keys.ARROW_UP);
						break;
					case "BACKSPACE":
						// For the Backspace key.
						element.sendKeys(Keys.BACK_SPACE);
						break;
					case "CLEAR":
						// For the Clear keystroke.
						element.sendKeys(Keys.CLEAR);
						break;
					case "CONTROL":
						// For the Control key.
						element.sendKeys(Keys.CONTROL);
						break;
					case "DELETE":
						// For the Delete key.
						element.sendKeys(Keys.DELETE);
						break;
					case "DOWN":
						// For the number pad division key.
						element.sendKeys(Keys.DOWN);
						break;
					case "END":
						// For the End key.
						element.sendKeys(Keys.END);
						break;
					case "ENTER":
						// For the Enter key.
						element.sendKeys(Keys.ENTER);
						break;
					case "ESCAPE":
						// For the Escape key.
						element.sendKeys(Keys.ESCAPE);
						break;
					case "F1":
						// For the function key F1.
						element.sendKeys(Keys.F1);
						break;
					case "F10":
						// For the function key F10.
						element.sendKeys(Keys.F10);
						break;
					case "F11":
						// For the function key F11.
						element.sendKeys(Keys.F11);
						break;
					case "F12":
						// For the function key F12.
						element.sendKeys(Keys.F12);
						break;
					case "F2":
						// For the function key F2.
						element.sendKeys(Keys.F2);
						break;
					case "F3":
						// For the function key F3.
						element.sendKeys(Keys.F3);
						break;
					case "F4":
						// For the function key F4.
						element.sendKeys(Keys.F4);
						break;
					case "F5":
						// For the function key F5.
						element.sendKeys(Keys.F5);
						break;
					case "F6":
						// For the function key F6.
						element.sendKeys(Keys.F6);
						break;
					case "F7":
						// For the function key F7.
						element.sendKeys(Keys.F7);
						break;
					case "F8":
						// For the function key F8.
						element.sendKeys(Keys.F8);
						break;
					case "F9":
						// For the function key F9.
						element.sendKeys(Keys.F9);
						break;
					case "HOME":
						// For the Home key.
						element.sendKeys(Keys.HOME);
						break;
					case "INSERT":
						// For the Insert key.
						element.sendKeys(Keys.INSERT);
						break;
					case "LEFT":
						// For the left arrow key.
						element.sendKeys(Keys.LEFT);
						break;
					case "LEFT_ALT":
						// For the Alt key.
						element.sendKeys(Keys.LEFT_ALT);
						break;
					case "LEFT_CONTROL":
						// For the Control key.
						element.sendKeys(Keys.LEFT_CONTROL);
						break;
					case "LEFT_SHIFT":
						// For the Shift key.
						element.sendKeys(Keys.LEFT_SHIFT);
						break;
					case "PAGE_DOWN":
						// For the Page Down key.
						element.sendKeys(Keys.PAGE_DOWN);
						break;
					case "PAGE_UP":
						// For the Page Up key.
						element.sendKeys(Keys.PAGE_UP);
						break;
					case "TAB":
						// For the Tab key.
						element.sendKeys(Keys.TAB);
						break;
					case "UP":
						// For the up arrow key.
						element.sendKeys(Keys.UP);
						break;
					}
				}
				strstatus = "Passed%%The Element: " + hqdriver.stepObject + " is performed with key: " + key;
			} else {
				strstatus = "Failed%%The Element: " + hqdriver.stepObject + " doesn't exist";
			}
		} catch (Exception e) {
			strstatus = "Failed%%" + e.getMessage();
		}
		return strstatus;
	}
}
