package com.ibmhq;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DragAndDrop {
	public String execute(String dragtype, String dragvalue, String droptype, String dropvalue) {

		String strstatus = null;
		try {
			WebElement element = Elements.find(dragtype, dragvalue);
			WebElement target = Elements.find(droptype, dropvalue);
			if (element.isEnabled()) {
				Actions action = new Actions(Browser.Driver);
				action.dragAndDrop(element, target);
				action.build();
				action.perform();
				strstatus = "Passed%%The Element is dragged and dropped successfully";
			} else {
				strstatus = "Failed%%The Element doesn't exist";
			}
		} catch (Exception e) {
			strstatus = "Failed%%" + e.getMessage();
		}
		return strstatus;
	}

	public String targetFrame(String dragtype, String dragValue, String stepData) {
		String strstatus = null;

		try {
			WebElement LocatorFrom = Elements.find(dragtype, dragValue);

			int xfrom = LocatorFrom.getLocation().x;
			int yfrom = LocatorFrom.getLocation().y;

			Actions builder = new Actions(Browser.Driver);
			builder.clickAndHold(LocatorFrom).build().perform();
			builder.moveToElement(LocatorFrom, -xfrom, -yfrom).build().perform();

			String[] data = stepData.split("\\|");
			String Frame[] = data[0].split("#");
			String FrameType = Frame[0];
			String FrameValue = Frame[1];

			WebDriverWait wait = new WebDriverWait(Browser.Driver, 5);

			if (FrameType.equalsIgnoreCase("Index")) {
				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(Integer.parseInt(FrameValue)));
			} else if (FrameType.equalsIgnoreCase("ID") || FrameType.equalsIgnoreCase("Name")) {
				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(FrameValue));
			} else {
				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(Elements.find(FrameType, FrameValue)));
			}

			String DropObj[] = data[1].split("#");
			String DropType = DropObj[0];
			String DropValue = DropObj[1];

			WebElement LocatorTo = Elements.find(DropType, DropValue);
			int xto = LocatorTo.getLocation().x;
			int yto = LocatorTo.getLocation().y;
			int width = LocatorTo.getSize().getWidth();
			int height = LocatorTo.getSize().getHeight();
			Browser.Driver.switchTo().defaultContent();

			builder.moveByOffset(xto + (width / 2), yto + (height / 2)).perform();
			builder.release().perform();
			strstatus = "Passed%%The Element is dragged and dropped successfully";
		} catch (Exception E) {
			strstatus = "Failed%%The Element is dragged and dropped successfully";
		}
		return strstatus;

	}

}