package com.ibmhq;

import java.awt.Robot;
import java.awt.event.KeyEvent;

public class FileDownloadIE {

	public String execute() {

		String strstatus = null;

		try {
			Robot robot = new Robot();
			// Simulate key Events
			robot.keyPress(KeyEvent.VK_TAB);
			robot.keyRelease(KeyEvent.VK_TAB);
			robot.keyRelease(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);

			strstatus = "Passed%%The file is saved successfully";
		} catch (Exception e) {
			strstatus = "Failed%%" + e.getMessage();
		}
		return strstatus;
	}

}
