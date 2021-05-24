package com.ibmhq;

import java.util.List;
import org.openqa.selenium.WebElement;

public class SelectList {

	public String execute(String type, String value, String ItemValue) {

		String strstatus = null;


		      try {
                    List<WebElement> list = Elements.findAll(type, value);
                    int size = list.size();

                    if (size >= 0) {
                        for (int i = 0; i < size; i++) {
                            String sValue = list.get(i).getText();
                            if (sValue.equalsIgnoreCase(ItemValue)) {

                                list.get(i).click();
                                //clicked = true;//added by Abhiram
                                break;   // added by abhiram 07/28/2020
                            }
                        }
                        strstatus = "Passed%%The element: " + hqdriver.stepObject + " is selected with list value: "+ ItemValue;
                    } else {
                        strstatus = "Failed%%The element: " + hqdriver.stepObject + " list is empty";
                    }
                } catch (Exception e) {
                    strstatus = "Failed%%" + e.getMessage();

                }


		return strstatus;
	}



	public String executeSingle(String type, String value, String ItemValue) {

		String strstatus = null;


		      try {
                    List<WebElement> list = Elements.findAll(type, value);
                    int size = list.size();

                    if (size >= 0) {
                        for (int i = 0; i < size; i++) {
                            String sValue = list.get(i).getText();
                            if (sValue.equalsIgnoreCase(ItemValue)) {

                                list.get(i).click();

                                break;   // added by abhiram 07/28/2020
                            }
                        }
                        strstatus = "Passed%%The element: " + hqdriver.stepObject + " is selected with list value: "+ ItemValue;
                    } else {
                        strstatus = "Failed%%The element: " + hqdriver.stepObject + " list is empty";
                    }
                } catch (Exception e) {
                    strstatus = "Failed%%" + e.getMessage();

                }


		return strstatus;
	}
}

