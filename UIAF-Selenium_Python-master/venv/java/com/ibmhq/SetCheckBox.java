package com.ibmhq;
import org.openqa.selenium.Keys;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;



public class SetCheckBox {

	public String execute(String type, String value, boolean state) {

		String strstatus = null;
		try {
			WebElement element = Elements.find(type, value);


			if (element.isEnabled()) {
				if (state == true) {
					if (element.isSelected() ==false) {
						//element.sendKeys(Keys.SPACE);
						element.click();   // ABHIRAM 08/11/2020 Code added for SetCheckbox issue
						strstatus = "Passed%%The Checkbox : " + hqdriver.stepObject + " is selected";
					}
				} else {
					if (element.isSelected() == true) {
						element.sendKeys(Keys.SPACE);
						//element.click();  // ABHIRAM 08/11/2020 Code added for SetCheckbox issue
						strstatus = "Passed%%The Checkbox : " + hqdriver.stepObject + " is deselected";
					}
					else
					{
					strstatus = "Passed%%The Checkbox : " + hqdriver.stepObject + " is already deselected";

					}

				}
			} else {
				strstatus = "Failed%%The Element: " + hqdriver.stepObject + " doesn't exist";
			}
		} catch (Exception e) {
			strstatus = "Failed%%" + e.getMessage();
		}
		return strstatus;
	}

    // Added this custom function for handling the Setcheckbox which is failing.Abhiram 09072020

   public String executeAlt(String type, String value, boolean state) {

		String strstatus = null;
		Boolean checkedStatus = null;
		try {

			WebElement element = Elements.find(type, value);
			String attributeStatus = element.getAttribute("checked");
			if (attributeStatus == null){
				checkedStatus = false;
		        System.out.print("Attribute does not exist, so not checked");
		    }
		    else if (attributeStatus.equals("")){
		    	checkedStatus = true;
		        System.out.print("Attribute is empty string, so checked");
		    }
			System.out.println("State given is " + state + " element status is " + checkedStatus);
			if (!(checkedStatus == state)){
				element.click();
				System.out.println("Element " + element + " is clicked...");
			}
			strstatus = "Passed%% Done";
		} catch (Exception e) {
			strstatus = "Failed%%" + e.getMessage();
		}
		return strstatus;
	}

	public String SetRadioButton(String type, String value) {

		String strstatus = null;
		try {
			WebElement element = Elements.find(type, value);

			if (element.isEnabled()) {
				
					if (element.isSelected() == false) {
						element.click();
						strstatus = "Passed%%The Checkbox : " + hqdriver.stepObject + " is selected";
					}
				 else {
					if (element.isSelected() == true) {
						//element.sendKeys(Keys.SPACE);
						strstatus = "Passed%%The Checkbox : " + hqdriver.stepObject + " is deselected";
					}

				}
			} else {
				strstatus = "Failed%%The Element: " + hqdriver.stepObject + " doesn't exist";
			}
		} catch (Exception e) {
			strstatus = e.getMessage();
		}
		return strstatus;
	}
}
