package com.ibmhq;

import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HandleAlert {

	/**
	 * This method would handle the alert by sending keys in the textbox
	 * 
	 * @return Success or Failure message
	 */
	public String AlertSendkeys(String inputstring) {
		String strstatus = null;
		try {
			WebDriverWait wait = new WebDriverWait(Browser.Driver, 60);
			wait.until(ExpectedConditions.alertIsPresent());
			Alert alert = Browser.Driver.switchTo().alert();
			alert.sendKeys(inputstring);
			;
			strstatus = "Passed%%The alert has been handled by clicking on Ok button";
		} catch (Exception e) {
			System.out.println("Couldnt find the alert");
			strstatus = "Failed%%" + e.getMessage();
		}
		return strstatus;
	}

	/**
	 * This method would handle the alert by clicking on Ok button
	 * 
	 * @return Success or Failure message
	 */
	public String AcceptAlert() {
		String strstatus = null;
		try {
			WebDriverWait wait = new WebDriverWait(Browser.Driver, 60);
			wait.until(ExpectedConditions.alertIsPresent());
			Alert alert = Browser.Driver.switchTo().alert();
			alert.accept();
			strstatus = "Passed%%The alert has been handled by clicking on Ok button";
		} catch (Exception e) {
			System.out.println("Couldnt find the alert");
			strstatus = "Failed%%" + e.getMessage();
		}
		return strstatus;
	}

	/**
	 * This method would handle the alert by clicking on Cancel button
	 * 
	 * @return Success or Failure message
	 */
	public String DismissAlert() {
		String strstatus = null;
		try {
			WebDriverWait wait = new WebDriverWait(Browser.Driver, 60);
			wait.until(ExpectedConditions.alertIsPresent());
			Alert alert = Browser.Driver.switchTo().alert();
			alert.dismiss();
			strstatus = "Passed%%The alert is handled by clicking on Cancel button";
		} catch (Exception e) {
			System.out.println("Couldnt find the alert");
			strstatus = "Failed%%" + e.getMessage();
		}
		return strstatus;
	}

	/**
	 * This method will validate the text displayed in the alert
	 * 
	 * @return Success or Failure message
	 */
	public String CaptureTextfromAlert() {
		String strstatus = null;
		try {
			WebDriverWait wait = new WebDriverWait(Browser.Driver, 60);
			wait.until(ExpectedConditions.alertIsPresent());
			Alert alert = Browser.Driver.switchTo().alert();
			hqdriver.storedata = alert.getText();
			strstatus = "Passed%%The text for the alert is stored";
		} catch (Exception e) {
			System.out.println("Couldnt find the alert");
			strstatus = "Failed%%" + e.getMessage();
		}
		return strstatus;
	}

	public String acceptAlert(int waitTime) {
		String strstatus = null;
		try {
			if (Browser.Driver != null) {
				WebDriverWait wait = new WebDriverWait(Browser.Driver, waitTime);
				wait.until(ExpectedConditions.alertIsPresent()).accept();
				// Browser.Driver.switchTo().alert().accept();
				strstatus = "Passed%%Alert Accepted";
			} else {
				strstatus = "Failed%%The Alert doesn't exist";
			}
		} catch (Exception e) {
			// strstatus = "Passed%%Alert with text:**
			// "+Browser.Driver.switchTo().alert().getText()+ " ** Accepted";
			strstatus = "Failed%%" + e.getMessage();
		}
		return strstatus;
	}

	public String verifyAlertMsg(int wTime, String alertMsg) {
		String strstatus = null;
		try {
			if (Browser.Driver != null) {
				WebDriverWait wait = new WebDriverWait(Browser.Driver, wTime);
				Alert alert = wait.until(ExpectedConditions.alertIsPresent());
				if (alert.getText().equalsIgnoreCase(alertMsg)) {
					strstatus = "Passed%%Alert Accepted";
				} else {
					strstatus = "Failed%%The Alert doesn't exist";
				}
			} else {
				strstatus = "Failed%%The Alert doesn't exist";
			}
		} catch (Exception e) {
			// strstatus = "Passed%%Alert with text:**
			// "+Browser.Driver.switchTo().alert().getText()+ " ** Accepted";
			strstatus = "Failed%%" + e.getMessage();
		}
		return strstatus;
	}

	// Gets the message from alert and compares with the expected string
	public String ValidateAlertMessage(String alertMsg) {
		String strstatus = null;
		try {
			if (Browser.Driver != null) {
				WebDriverWait wait = new WebDriverWait(Browser.Driver, 30);
				Alert alert = wait.until(ExpectedConditions.alertIsPresent());
				String AlertTextMsg = alert.getText().replaceAll("[\\n\\r]", "");
				if (AlertTextMsg.contentEquals(alertMsg)) {
					strstatus = "Passed%%Alert Accepted";
				} else {
					strstatus = "Failed%%The Alert doesn't exist";
				}
				alert.accept();

			} else {
				strstatus = "Failed%%The Alert doesn't exist";
			}
		} catch (Exception e) {

			strstatus = "Failed%%" + e.getMessage();
		}
		return strstatus;
	}

}