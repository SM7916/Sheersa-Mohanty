package com.ibmhq;

import org.openqa.selenium.WebElement;

public class VerifyAttribute {

  public String execute(String type,String value,String attribute,String propvalue) {

	  String strstatus = null;
	  try {
	    WebElement element = Elements.find(type,value);
   	    
	    if(propvalue.equals(element.getAttribute(attribute))){
	    	strstatus = "Passed%%The value "+ element.getAttribute(propvalue)+" of the attribute: "+ attribute +" is matched with the expected value of: "+ propvalue;
	    }else{
	    	strstatus = "Failed%%The value "+ element.getAttribute(propvalue)+" of the attribute: "+ attribute +" does not match expected value of: "+ propvalue;
	    }
	  } catch(Exception e) {
		  strstatus  = "Failed%%" + e.getMessage();
	  }
	return strstatus;
  }

public String GetAttribute(String type, String value, String attribute, String varname) {

		String strstatus = null;
		try {
			WebElement element = Elements.find(type, value);

			if (element.isEnabled()) {
				String strtext = element.getAttribute(attribute);
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
}
