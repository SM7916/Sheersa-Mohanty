package com.ibmhq;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SwitchToFrame {

	/*
	 * public String execute(String Frames) {
	 * 
	 * String strstatus = null; try { String arrFrames[] = null; if (Frames
	 * !="") { if (Frames.contains("#")) arrFrames = Frames.split("#"); else
	 * {arrFrames = new String[1]; arrFrames[0]=Frames;}
	 * 
	 * Browser.Driver.switchTo().defaultContent(); for(int
	 * i=0;i<arrFrames.length;i++) { if(arrFrames[i].chars().allMatch(
	 * Character::isDigit ))
	 * Browser.Driver.switchTo().frame(Integer.parseInt(arrFrames[i])); else
	 * Browser.Driver.switchTo().frame(arrFrames[i]); strstatus =
	 * "Passed$$The driver is switched to Frame: " + arrFrames[i]; } } else
	 * strstatus =
	 * "Failed%%The input is blank. Hence, unable to switch to frames.";
	 * 
	 * /* if(Browser.Driver != null){ if(name != null){
	 * Browser.Driver.switchTo().frame(name); strstatus =
	 * "Passed$$The browser is switched to Frame: " + name; } else if(index >=
	 * 0){ Browser.Driver.switchTo().frame(index); strstatus =
	 * "Passed%%The browser is switched to Frame: " + index; } }else{ strstatus
	 * = "Failed%%The browser doesn't exist"; } return strstatus; }
	 * catch(Exception e) { return e.getMessage(); }
	 * 
	 * }
	 */

	public String execute(String name, int index) {

		String strstatus = null;
		WebDriverWait wait = new WebDriverWait(Browser.Driver, 10);
		try {
			if (Browser.Driver != null) {
				if (name != null && index == -1 && !name.isEmpty()) {
					// Browser.Driver.switchTo().defaultContent();
					wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(name));
					// Browser.Driver.switchTo().frame(name);
					strstatus = "Passed%%The browser is switched to Frame: " + name;
				} else if (index >= 0) {
					// Browser.Driver.switchTo().defaultContent();
					wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(index));
					JavascriptExecutor jsExecutor = (JavascriptExecutor)Browser.Driver;
					String currentFrame = (String) jsExecutor.executeScript("return self.name");
					System.out.println(" I am in " + currentFrame);
					// Browser.Driver.switchTo().frame(index);
					Thread.sleep(1000);
					strstatus = "Passed%%The browser is switched to Frame: " + index;
				} else {
					Browser.Driver.switchTo().defaultContent();
					strstatus = "Passed%%The browser is switched to Frame: " + index;
				}
			} else {
				strstatus = "Failed%%The browser doesn't exist";
			}
		} catch (Exception e) {
			strstatus = "Failed%%" + e.getMessage();
			// strstatus = "Passed%%The browser is switched to Frame: ";
		}
		return strstatus;
	}

	public String switchToFrameByxpath(String type, String value) {

		String strstatus = null;
		// WebDriverWait wait=new WebDriverWait(Browser.Driver,5);
		try {

			// wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(name));
			WebElement element = Elements.find(type, value);
			Browser.Driver.switchTo().frame(element);
			strstatus = "Passed%%The browser is switched to Frame ";
		} catch (Exception e) {
			strstatus = "Failed%%" + e.getMessage();
			// strstatus = "Passed%%The browser is switched to Frame: ";
		}
		return strstatus;
	}
	
	public String DefaultCont() {

		try{
		String strstatus = null;
						Browser.Driver.switchTo().defaultContent();
				
					strstatus = "Passed$$The driver is switched to Default Context ";
			
			return strstatus;
		} catch (Exception e) {
			return e.getMessage();
		}

	}

}