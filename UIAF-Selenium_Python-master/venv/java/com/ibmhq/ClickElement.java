package com.ibmhq;

import java.awt.Robot;
//Added Toolkit and datatransfer
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.KeyEvent;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ClickElement {

	public String execute(String type, String value) {

		String strstatus = null;


		try {
			WebElement element = Elements.find(type, value);
			Thread.sleep(2000);
			WebDriverWait wait = new WebDriverWait(Browser.Driver, 30);
			wait.until(ExpectedConditions.visibilityOf(element));
			wait.until(ExpectedConditions.elementToBeClickable(element));
			if (element != null) {
				element.click();
				strstatus = "Passed%%The Element: " + hqdriver.stepObject + " is clicked";
			} else {
				strstatus = "Failed%%The Element: " + hqdriver.stepObject + " doesn't exist";
			}

		} catch (Exception e) {
			strstatus = "Failed%%" + e.getMessage();

		}


		return strstatus;

	}

	public String executeJS(String type, String value) {

		String strstatus = null;

		try {

			WebElement element = Elements.find(type, value);

			if (element != null) {
				JavascriptExecutor myExecutor = ((JavascriptExecutor) Browser.Driver);
				myExecutor.executeScript("arguments[0].click();", element);
				strstatus = "Passed%%The Element: " + hqdriver.stepObject + " is clicked";
			} else {
				strstatus = "Failed%%The Element: " + hqdriver.stepObject + " doesn't exist";
			}

		} catch (Exception e) {
			strstatus = "Failed%%" + e.getMessage();
			System.out.println(strstatus);
		}
		return strstatus;
	}

	public String executeRobo(String type, String value, String key) {
		String strstatus = null;
		try {
			WebElement element = Elements.find(type, value);
			if (element != null) {
				Robot r = new Robot();
				int TAB_NO = Integer.parseInt(key);
				for (int i = 0; i < TAB_NO; i++) {
					r.keyPress(KeyEvent.VK_TAB);
				}
				r.keyPress(KeyEvent.VK_ENTER);
				strstatus = "Passed%%The Element: " + hqdriver.stepObject + " is clicked";
			} else {
				strstatus = "Failed%%The Element: " + hqdriver.stepObject + " doesn't exist";
			}

		} catch (Exception e) {
			strstatus = "Failed%%" + e.getMessage();
			System.out.println(strstatus);
		}
		return strstatus;
	}

	public String executeRoboeENTER(String type, String value) {
		String strstatus = null;
		try {
			WebElement element = Elements.find(type, value);
			if (element != null) {
				Robot r = new Robot();
				new Actions(Browser.Driver).moveToElement(element).click().build().perform();
				new Actions(Browser.Driver).release().build().perform();

				r.keyPress(KeyEvent.VK_ENTER);
				strstatus = "Passed%%The Element: " + hqdriver.stepObject + " is clicked";
			} else {
				strstatus = "Failed%%The Element: " + hqdriver.stepObject + " doesn't exist";
			}

		} catch (Exception e) {
			strstatus = "Failed%%" + e.getMessage();
			System.out.println(strstatus);
		}
		return strstatus;
	}

	public String executeRoboENTER() {
		String strstatus = null;
		try {
				Robot r = new Robot();
				r.keyPress(KeyEvent.VK_ENTER);
				strstatus = "Passed%%The Element: " + hqdriver.stepObject + " is clicked";
		} catch (Exception e) {
			strstatus = "Failed%%The Element: " + hqdriver.stepObject + " doesn't exist" + e.getMessage();
		}
		return strstatus;
	}

	public String executeRoboTAB(String key) {
		String strstatus = null;
		try {
			{
				Robot r = new Robot();
				int TAB_NO = Integer.parseInt(key);
				for (int i = 0; i < TAB_NO; i++) {
					r.keyPress(KeyEvent.VK_TAB);
					Thread.sleep(2000);
				}
				Thread.sleep(2000);

				Actions actions = new Actions(Browser.Driver);
				actions.keyDown(Keys.ENTER);
				actions.perform();
				strstatus = "Passed%%The Element: " + hqdriver.stepObject + " is clicked";
			}

		} catch (Exception e) {
			strstatus = "Failed%%The Element: " + hqdriver.stepObject + " doesn't exist" + e.getMessage();
		}
		return strstatus;
	}

	public String JavascriptExecutorElementClick(String type, String value) {

		// System.out.println("Inside JavascriptExecutorClick.
		// type:"+type+",value:"+value);
		String strstatus = null;
		try {
			((JavascriptExecutor) Browser.Driver).executeScript("arguments[0].scrollIntoView(true);",
					Browser.Driver.findElement(Elements.mobilefind(type, value)));
			JavascriptExecutor ex = (JavascriptExecutor) Browser.Driver;
			ex.executeScript("arguments[0].click();", Browser.Driver.findElement(Elements.mobilefind(type, value)));

			strstatus = "Passed%%The Element: " + hqdriver.stepObject + " is clicked";
		} catch (Exception e) {
			strstatus = "Failed%%" + e.getMessage();
			System.out.println("Failed%%Exception in the method - JavascriptExecutorClick:" + e.toString());
		}
		return strstatus;
	}

	// Dec 27/28 Added the Custom code for file upload

	public String executeuploadFile(String stepData) {

		String strstatus = null;
		try {


			setFilePathData(stepData);
			Robot robot = new Robot();

		  	robot.keyPress(KeyEvent.VK_CONTROL);
		    robot.keyPress(KeyEvent.VK_V);
		    //robot.delay(3000);// ANITTALA 08/04/2020 code Added  for File upload
		    robot.keyRelease(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			//robot.delay(3000);// ANITTALA 08/04/2020 code Added  for File upload
			//robot.keyPress(KeyEvent.VK_TAB);// ANITTALA 08/04/2020 code Added  for File upload
			//robot.keyRelease(KeyEvent.VK_TAB);// ANITTALA 08/04/2020 code Added  for File upload
			//robot.delay(3000);// ANITTALA 08/04/2020 code Added  for File upload
            //robot.keyPress(KeyEvent.VK_TAB);// ANITTALA 08/04/2020 code Added  for File upload
			//robot.keyRelease(KeyEvent.VK_TAB);// ANITTALA 08/04/2020 code Added  for File upload

            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);

            // ANITTALA 08/04/2020 code Added  for File upload
            //Runtime.getRuntime().exec("C:\\Users\\AbhiramaManoharNitta\\Documents\\FileUpload.exe"); // ANITTALA 08/04/2020 code added for File upload

			strstatus = "Passed%%The Element: " + hqdriver.stepObject + " is clicked";
		} catch (Exception e) {
			strstatus = "Failed%%" + e.getMessage();
			System.out.println("Failed%%Exception in the method - executeuploadFile:" + e.toString());
		}
		return strstatus;
	}

	public void setFilePathData(String path) {
		StringSelection selObj = new StringSelection(path);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selObj, null);

	}

	// *** Jan -3 Code for Notepad edit
	public String executenotepad(String stepData) {

		String strstatus = null;
		try {
			setFilePathData(stepData);
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);

			robot.setAutoDelay(1500);

			robot.keyPress(KeyEvent.VK_ALT);
			robot.keyRelease(KeyEvent.VK_F4);
			robot.keyRelease(KeyEvent.VK_F4);
			robot.keyPress(KeyEvent.VK_ALT);

			robot.setAutoDelay(1500);

			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);

			strstatus = "Passed%%The Element: " + hqdriver.stepObject + " is clicked";
		} catch (Exception e) {
			strstatus = "Failed%%" + e.getMessage();
			System.out.println("Failed%%Exception in the method - executenotepad:" + e.toString());
		}
		return strstatus;
	}

	// Jan 7 Verify notepad

	public String verifynotepad(String stepData) {

		String strstatus = null;
		try {
			Robot robot = new Robot();
			robot.setAutoDelay(1500);
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_A);
			robot.keyPress(KeyEvent.VK_C);
			robot.keyRelease(KeyEvent.VK_C);
			robot.keyRelease(KeyEvent.VK_A);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.setAutoDelay(1500);
			Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
			Transferable data = clipboard.getContents(DataFlavor.stringFlavor);
			String strString = (String) data.getTransferData(DataFlavor.stringFlavor);

			if (strString.contains(stepData))
				strstatus = "Passed%%Expected Text : " + stepData + hqdriver.stepObject + " was found in " + strString;
			else
				strstatus = "Failed%%The content: " + stepData + hqdriver.stepObject + " is missing in " + strString;
		} catch (Exception e) {
			strstatus = "Failed%%" + e.getMessage();
			System.out.println("Failed%%Exception in the method - verifynotepad:" + e.toString());
		}
		return strstatus;
	}

}
