package com.ibmhq;

import java.awt.Robot;
import java.awt.event.KeyEvent;

public class CloseBrowser {

	public String closeBrowser() {

		String strstatus = null;

		try {
			if (Browser.Driver != null)
				Browser.Driver.close();
			strstatus = "Passed%%The browser is closed successfully";
		} catch (Exception e) {
			strstatus = "Failed%%" + e.getMessage();
		}
		return strstatus;
	}

	public String quitBrowser() {

		String strstatus = null;

		try {
			if (Browser.Driver != null)
				Browser.Driver.quit();
			strstatus = "Passed%%The browser is closed successfully";
		} catch (Exception e) {
			strstatus = "Failed%%" + e.getMessage();
		}
		return strstatus;
	}

	public String closeCurrentWindow() {

		String strstatus = null;

		try {

			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_ALT);
			robot.keyPress(KeyEvent.VK_F4);
			robot.keyRelease(KeyEvent.VK_ALT);
			robot.keyPress(KeyEvent.VK_F4);

			strstatus = "Passed%%The current browser is closed successfully";
		} catch (Exception e) {
			strstatus = "Failed%%" + e.getMessage();
		}
		return strstatus;

	}
}
