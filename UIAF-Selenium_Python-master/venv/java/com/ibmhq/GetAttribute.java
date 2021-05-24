package com.ibmhq;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class GetAttribute {

	public String SelectSeat() {

		String strstatus = null;
		try {

			List<WebElement> availableSeats = Browser.Driver.findElements(By.xpath("//img[@alt='Even More Space']"));
			System.out.println(availableSeats.size());
			availableSeats.get(1).click();
			strstatus = "Passed%%The Element: " + hqdriver.stepObject + " seat is selected";
		} catch (Exception e) {
			strstatus = "Failed%%The Element: " + hqdriver.stepObject + "seat is not selected";

		}
		return null;

	}
}
