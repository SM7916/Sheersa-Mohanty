package com.ibmhq;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class GetText {
	public String execute(String type, String value, String strText) {

		String strstatus = null;
		try {
			WebElement element = Elements.find(type, value);

			if (element.isEnabled()) {
				String strtext = element.getText();
				hqdriver.storedata = strtext;
				strstatus = "Passed%%The Element: " + hqdriver.stepObject + " text value: " + strtext + "is stored";
			} else {
				strstatus = "Failed%%The Element: " + hqdriver.stepObject + " doesn't exist";
			}
		} catch (Exception e) {
			strstatus = "Failed%%Exception " + e.getMessage();
		}
		return strstatus;
	}
	
	public String containsText(String type, String value) {

		String strstatus = null;
		try {
			WebElement element = Elements.find(type, value);

			if (element.isEnabled()) {
				String strtext = element.getAttribute("value");
				System.out.println(strtext);
				if(strtext==null){
					strstatus = "Failed%%The Element: " + hqdriver.stepObject + " does not contain any text";
				}else{
					strstatus = "Passed%%The Element: " + hqdriver.stepObject + " has " + strtext;
				}
			} else {
				strstatus = "Failed%%The Element: " + hqdriver.stepObject + " is not enabled";
			}
		} catch (Exception e) {
			strstatus = "Failed%%" + e.getMessage();
		}
		return strstatus;
	}

	public String getNumbersFromString(String type, String value, String varname) {

		String strstatus = null;
		try {
			WebElement element = Elements.find(type, value);

			if (element.isEnabled()) {
				String strtext = element.getText();
				strtext = strtext.replaceAll("\\D+", "");
				hqdriver.storedata = strtext;
				strstatus = "Passed%%The Element: " + hqdriver.stepObject + " text value: " + strtext + "is stored";
			} else {
				strstatus = "Failed%%The Element: " + hqdriver.stepObject + " doesn't exist";
			}
		} catch (Exception e) {
			strstatus = "Failed%%" + e.getMessage();
		}
		return strstatus;
	}

	public String GetAttributeValue(String type, String value, String varname) {

		String strstatus = null;
		try {
			WebElement element = Elements.find(type, value);

			if (element.isEnabled()) {

				String strtext = element.getAttribute("value");
				hqdriver.storedata = strtext;
				strstatus = "Passed%%The Element: " + hqdriver.stepObject + " text value is: " + strtext;
			} else {
				strstatus = "Failed%%The Element: " + hqdriver.stepObject + " doesn't exist";
			}
		} catch (Exception e) {
			strstatus = "Failed%%" + e.getMessage();
		}
		return strstatus;
	}
	
	public String GetTextJS(String type, String value, String varname) {

		String strstatus = null;
		try {
			WebElement element = Elements.find(type, value);
			JavascriptExecutor js = (JavascriptExecutor) Browser.Driver;
			if (element.isEnabled()) {
				String strtext= js.executeScript("return arguments[0].value", element).toString();
				 //= element.getText();
				hqdriver.storedata = strtext;
				strstatus = "Passed%%The Element: " + hqdriver.stepObject + " text value: " + strtext + "is stored";
			} else {
				strstatus = "Failed%%The Element: " + hqdriver.stepObject + " doesn't exist";
			}
		} catch (Exception e) {
			strstatus = e.getMessage();
		}
		return strstatus;
	}
	
	
	public String GetTextSubstring(String type, String value, String varname) {

		String strstatus = null;
		try {
			WebElement element = Elements.find(type, value);

			if (element.isEnabled()) {
				String strtext = element.getText();
				String line = strtext;
				String regex = "\\s+";
			    String[] str = line.split(regex);
			    int i=str.length;
			    for(int j=0;j<i;j++){
			    	if(str[j].matches("[0-9]+")&& str[j].length() > 2){
			    		i=j;
			    		break;
			    	}
			    }
			    System.out.println(str[i]);
			    //int index=Integer.parseInt(Stringindex);
			    strtext=str[i];
				//Thread.sleep(1500);
				hqdriver.storedata = strtext;
				strstatus = "Passed%%The Element: " + hqdriver.stepObject + " text value: " + strtext + " is stored";
			} else {
				strstatus = "Failed%%The Element: " + hqdriver.stepObject + " doesn't exist";
			}
		} catch (Exception e) {
			strstatus = e.getMessage();
		}
		return strstatus;
	}
}
