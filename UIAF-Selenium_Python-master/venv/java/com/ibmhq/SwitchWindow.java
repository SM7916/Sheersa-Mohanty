package com.ibmhq;

import java.io.File;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver.Window;

public class SwitchWindow {

	public hqdriver maindriver;

	boolean wselected;

	/*
	 * public String execute(String name) {
	 * 
	 * String strstatus = null; try { int iTimeout = 20;
	 * 
	 * if(name == "Default Window"){
	 * Browser.Driver.switchTo().window(Browser.MainWinHandle); wselected =
	 * true; }
	 * 
	 * while(iTimeout > 0){ for (String
	 * handle:Browser.Driver.getWindowHandles()){
	 * 
	 * Browser.Driver.switchTo().window(handle); if (Browser.Driver.getTitle()
	 * == name){ wselected = true; } } iTimeout--; }
	 * 
	 * if(wselected = true){ strstatus = "Passed%%Switched to the Window: "+
	 * name; }else{ strstatus = "Failed%%Window: "+ name +" does not exist."; }
	 * 
	 * } catch(Exception e) { strstatus = e.getMessage(); } return strstatus; }
	 */
	public String execute(String name) {

		String strstatus = null;
		try {
			int iTimeout = 20;
			Thread.sleep(1000);

			if (name.equalsIgnoreCase("Default Window")) {
				Browser.Driver.switchTo().window(Browser.MainWinHandle);
				wselected = true;
				Browser.Driver.manage().window().maximize();
			}

			if (!wselected) {
				while (iTimeout > 0) {
					for (String handle : Browser.Driver.getWindowHandles()) {
						System.out.println(Browser.Driver.getCurrentUrl());
						Browser.Driver.switchTo().window(handle);
						if (Browser.Driver.getTitle().equalsIgnoreCase(name)) {
							wselected = true;
							Browser.Driver.manage().window().maximize();
							iTimeout = 0;
							break;
						}
					}
					iTimeout--;
				}
			}

			if (wselected = true) {
				//vibrate(Browser.Driver.manage().window());
				strstatus = "Passed%%Switched to the Window: " + name;
			} else {
				strstatus = "Failed%%Window: " + name + " does not exist.";
			}

		} catch (Exception e) {
			strstatus = "Failed%%" + e.getMessage();
		}
		return strstatus;
	}

	public String getCurrentWindowHandle() {
		String strstatus = "";
		try {
			String currentHandle = Browser.Driver.getWindowHandle();
			hqdriver.storedata = currentHandle;
			strstatus = "Passed%%" + currentHandle;
		} catch (Exception e) {
			strstatus = "Failed%%" + e.getMessage();
		}
		return strstatus;
	}

	public String SwitchWindowHandle(String winhandle) {
		String strstatus = "";
		try {
			Browser.Driver.switchTo().window(winhandle);
			strstatus = "Passed%% Switched to " + Browser.Driver.getCurrentUrl();
		} catch (Exception e) {
			strstatus = "Failed%%" + e.getMessage();
		}
		return strstatus;
	}

	public void vibrate(Window win) {
		int VIBRATION_LENGTH = 3;
		int VIBRATION_VELOCITY = 3;
		int TH_SLEEP_TIME = 3;
		try {
			final int originalX = win.getPosition().x;
			final int originalY = win.getPosition().y;
			for (int i = 0; i < VIBRATION_LENGTH; i++) {
				Thread.sleep(TH_SLEEP_TIME);
				win.setPosition(new Point(originalX, originalY + VIBRATION_VELOCITY));
				Thread.sleep(TH_SLEEP_TIME);
				win.setPosition(new Point(originalX, originalY - VIBRATION_VELOCITY));
				Thread.sleep(TH_SLEEP_TIME);
				win.setPosition(new Point(originalX + VIBRATION_VELOCITY, originalY));
				Thread.sleep(TH_SLEEP_TIME);
				win.setPosition(new Point(originalX, originalY));
			}
		} catch (Exception err) {
			err.printStackTrace();
		}
	}

	public String SwitchToURLContains(String urlString) {
		String strstatus = "";
		try {

			Set<String> openWin = Browser.Driver.getWindowHandles();

			for (String handle : openWin) {

				Browser.Driver.switchTo().window(handle);
				String handleURL = Browser.Driver.getCurrentUrl();
				System.out.println(handleURL);

				if (handleURL.contains(urlString)) {
					Browser.Driver.manage().window().maximize();
					strstatus = "Passed%% Successfully switched";
					break;

				}
				Thread.sleep(3000);
				Browser.Driver.close();
			}
		} catch (Exception e) {
			strstatus = "Failed%%" + e.getMessage();
		}
		return strstatus;
	}

	public String SwitchToMainURLContains(String urlString) {
		String strstatus = "";
		try {

			Set<String> openWin = Browser.Driver.getWindowHandles();

			for (String handle : openWin) {

				Browser.Driver.switchTo().window(handle);
				String handleURL = Browser.Driver.getCurrentUrl();
				System.out.println(handleURL);

				if (handleURL.contains(urlString)) {
					Browser.Driver.manage().window().maximize();
					strstatus = "Passed%% Successfully switched";
					break;

				}

			}
		} catch (Exception e) {
			strstatus = "Failed%%" + e.getMessage();
		}
		return strstatus;
	}

	public String SwitchWithTitleContains(String name) {

		String strstatus = null;
		try {
			int iTimeout = 20;
			Thread.sleep(1000);
			while (iTimeout > 0) {
				if (name.equalsIgnoreCase("Default Window")) {
					Browser.Driver.switchTo().window(Browser.MainWinHandle);
					wselected = true;
				}

				if (!wselected) {

					for (String handle : Browser.Driver.getWindowHandles()) {

						Browser.Driver.switchTo().window(handle);
						System.out.println("URL is " + Browser.Driver.getCurrentUrl());
						if (Browser.Driver.getTitle().contains(name)) {
							wselected = true;
							Browser.Driver.manage().window().maximize();
							iTimeout = 0;
							break;
						}
					}
					iTimeout--;
				}
				Browser.Driver.close();
			}

			if (wselected = true) {
				strstatus = "Passed%%Switched to the Window: " + name;
			} else {
				strstatus = "Failed%%Window: " + name + " does not exist.";
			}

		} catch (Exception e) {
			strstatus = "Failed%%" + e.getMessage();
		}
		return strstatus;
	}

	public String switchChildWindow(String name) {

		String strstatus = null;
		/*
		 * String parent=Browser.Driver.getWindowHandle();
		 * System.out.println(parent);
		 */
		Set<String> s1 = Browser.Driver.getWindowHandles();
		Iterator<String> I1 = s1.iterator();
		String parentWindow = I1.next();
		String child_window = I1.next();

		Browser.Driver.switchTo().window(child_window);
		String childURL = Browser.Driver.switchTo().window(child_window).getCurrentUrl();
		System.out.println(childURL);
		if (childURL.contains(name)) {

			Browser.Driver.manage().window().maximize();
			strstatus = "Passed%% Switched to " + child_window;
			// Browser.Driver.close();
		} else {
			strstatus = "Failed%% Switched to " + child_window;
		}

		return strstatus;
	}

	public String closeChildWindow(String name) throws InterruptedException {

		String strstatus = null;
		String Parent = Browser.Driver.getWindowHandle();
		System.out.println(Parent);
		Set<String> s1 = Browser.Driver.getWindowHandles();
		Iterator<String> I1 = s1.iterator();
		String parentWindow = I1.next();
		String child_window = I1.next();

		// Browser.Driver.switchTo().window(child_window);
		// System.out.println(Browser.Driver.switchTo().window(child_window).getCurrentUrl());
		if (Browser.Driver.getCurrentUrl().contains(name)) {
			Browser.Driver.close();
			Thread.sleep(1000);

			System.out.println(Browser.Driver.switchTo().window(parentWindow).getCurrentUrl());
			strstatus = "Passed%% Switched to " + parentWindow;
		}

		else {
			strstatus = "Failed%% Switched to " + parentWindow;
		}

		return strstatus;
	}

	public String switchParentWindow() {
		String strstatus = "";
		try {

			String parent = Browser.Driver.getWindowHandle();
			System.out.println(parent);
			Set<String> s1 = Browser.Driver.getWindowHandles();
			Iterator<String> I1 = s1.iterator();
			String parentWindow = I1.next();
			String child_window = I1.next();

			Browser.Driver.switchTo().window(child_window);
			Browser.Driver.manage().window().maximize();
			System.out.println(Browser.Driver.switchTo().window(child_window).getTitle());
			Thread.sleep(2000);
			File srcFile = ((TakesScreenshot) Browser.Driver).getScreenshotAs(OutputType.FILE);

			FileUtils.copyFile(srcFile, new File(
					"C:\\UIAF_Se_Appium_3.3\\UIAF_Se_Appium_3.3\\automationUI\\Selenium\\TestResults\\tests.jpg"));
			Browser.Driver.close();

			Thread.sleep(5000);
			Browser.Driver.switchTo().window(parentWindow);

			strstatus = "Passed%% Switched to " + parent;

		} catch (Exception ex) {
			System.out.println("Exception");
		}
		return strstatus;

	}
}
