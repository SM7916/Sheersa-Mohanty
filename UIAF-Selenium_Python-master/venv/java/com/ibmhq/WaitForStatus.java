package com.ibmhq;

import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebElement;

public class WaitForStatus {
	public String execute(String type, String value, String testdata) {

		String strstatus = null;
		try {

			WebElement element = Elements.find(type, value);
			if (element != null) {
				String StatusText = element.getText();
				System.out.println(StatusText);
				for (int i = 0; i < 15; i++) {
					element = Elements.find(type, value);
					if (element.getText().equalsIgnoreCase(testdata)) {
						strstatus = "Passed%%The Status: " + testdata + " is present";
						break;
					} else {
						Browser.Driver.navigate().refresh();
						Thread.sleep(3000);
						if (isAlertPresent()) {
							Alert alert = Browser.Driver.switchTo().alert();
							alert.accept();
						}
						Thread.sleep(60000);
					}
					if (i == 14) {
						strstatus = "Failed%%The status is: " + testdata + " not present";
					}
				}
			} else {
				strstatus = "Failed%%The element doesn't exist";
			}

		} catch (Exception e) {
			strstatus = "Failed%%" + e.getMessage();
		}
		return strstatus;
	}

	public static boolean isAlertPresent() {
		try {
			Browser.Driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException ex) {
			return false;
		}
	}
}
