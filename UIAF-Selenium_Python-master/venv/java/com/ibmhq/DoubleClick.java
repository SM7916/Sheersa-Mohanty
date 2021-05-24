package com.ibmhq;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class DoubleClick {

  public String execute(String type,String value) {
	  
    String strstatus = null;
	try {
	    WebElement element = Elements.find(type,value);
	    
	    if(element.isEnabled()){
		    Actions action = new Actions(Browser.Driver);
		  	action.doubleClick(element);
		  	action.perform();
	    	strstatus = "Passed%%The Element: " + hqdriver.stepObject + " is double clicked";
	    }else{
	    	strstatus = "Failed%%The Element: " + hqdriver.stepObject + " doesn't exist";
	    }
	    
	} catch(Exception e) {
		strstatus  = "Failed%%" + e.getMessage();
	}
	return strstatus;
	
  }
}
