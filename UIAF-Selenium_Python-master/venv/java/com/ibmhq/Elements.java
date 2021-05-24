package com.ibmhq;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Elements {
     public static String find_if(String IDType,String ID){

	  //assert Browser.Driver == null: "Error browser is not opened. Use Launch URL action";
	  try {
	    WebElement foundElement = null;
	    if (ID.toUpperCase().contains("$$OBJECT$$")) {
	    	if (hqdriver.TempVariable != "")
	    		ID = ID.replace("$$OBJECT$$", hqdriver.TempVariable);

	    	//System.out.println("LocatorValue:"+LocatorValue);
	    }

	    String verifyElement = ExplicitWaitType.visibilityOfElementLocated(IDType,ID ,"10");
	    //highlightElement(Browser.Driver, foundElement);

	    if(verifyElement.contains("Passed")){
		    switch (IDType){
		      case "Class Name":
		      	foundElement = Browser.Driver.findElement(By.className(ID));
		      	break;
		      case "Css Selector":
		      	foundElement = Browser.Driver.findElement(By.cssSelector(ID));
		      	break;
		      case "ID":
		      	foundElement = Browser.Driver.findElement(By.id(ID));
		      	break;
		      case "Link Text":
		      	foundElement = Browser.Driver.findElement(By.linkText(ID));
		      	break;
		      case "XPath":
		      	foundElement = Browser.Driver.findElement(By.xpath(ID));
		      	break;
		      case "Name":
		      	foundElement = Browser.Driver.findElement(By.name(ID));
		      	break;
		      case "Partial Link Text":
		      	foundElement = Browser.Driver.findElement(By.partialLinkText(ID));
		      	break;
		      case "Tag Name":
		      	foundElement = Browser.Driver.findElement(By.tagName(ID));
		      	break;
		      default:
		        foundElement = null;
		        break;
		    }
	    }
        System.out.println(foundElement);
        if (foundElement == null)
	    return "fail";
	    else
	    return "pass";
	  }
	  catch(Exception e) {
		  //System.out.println(e.toString());
		  return "fail";
	  }
 }




	public static WebElement find(String IDType, String ID) {

		// assert Browser.Driver == null: "Error browser is not opened. Use
		// Launch URL action";
		try {
			WebElement foundElement = null;
			if (ID.toUpperCase().contains("$$OBJECT$$")) {
				if (hqdriver.TempVariable != "")
					ID = ID.replace("$$OBJECT$$", hqdriver.TempVariable);

				// System.out.println("LocatorValue:"+LocatorValue);
			}

			String verifyElement = ExplicitWaitType.visibilityOfElementLocated(IDType, ID, "10");
			// highlightElement(Browser.Driver, foundElement);

			if (verifyElement.contains("Passed")) {
				switch (IDType) {
				case "Class Name":
					foundElement = Browser.Driver.findElement(By.className(ID));
					break;
				case "Css Selector":
					foundElement = Browser.Driver.findElement(By.cssSelector(ID));
					break;
				case "ID":
					foundElement = Browser.Driver.findElement(By.id(ID));
					break;
				case "Link Text":
					foundElement = Browser.Driver.findElement(By.linkText(ID));
					break;
				case "XPath":
					foundElement = Browser.Driver.findElement(By.xpath(ID));
					break;
				case "Name":
					foundElement = Browser.Driver.findElement(By.name(ID));
					break;
				case "Partial Link Text":
					foundElement = Browser.Driver.findElement(By.partialLinkText(ID));
					break;
				case "Tag Name":
					foundElement = Browser.Driver.findElement(By.tagName(ID));
					break;
				default:
					foundElement = null;
					break;
				}
			}

			return foundElement;
		} catch (Exception e) {
			// System.out.println(e.toString());
			return null;
		}
	}

	public static void highlightElement(WebDriver driver, WebElement element) {

		String bgHighColor = "yellow";
		String borderHighStyle = "1px solid red";
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].style.border=arguments[1],arguments[0].style.background=arguments[2]", element,
				borderHighStyle, bgHighColor);

		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static List<WebElement> findAll(String IDType, String ID) {

		// assert Browser.Driver == null: "Error browser was not opened";

		List<WebElement> foundElements = null;
		switch (IDType) {
		case "Class Name":
			foundElements = Browser.Driver.findElements(By.className(ID));
			break;
		case "Css Selector":
			foundElements = Browser.Driver.findElements(By.cssSelector(ID));
			break;
		case "ID":
			foundElements = Browser.Driver.findElements(By.id(ID));
			break;
		case "Link Text":
			foundElements = Browser.Driver.findElements(By.linkText(ID));
			break;
		case "XPath":
			foundElements = Browser.Driver.findElements(By.xpath(ID));
			break;
		case "Name":
			foundElements = Browser.Driver.findElements(By.name(ID));
			break;
		case "Partial Link Text":
			foundElements = Browser.Driver.findElements(By.partialLinkText(ID));
			break;
		case "Tag Name":
			foundElements = Browser.Driver.findElements(By.tagName(ID));
			break;
		default:
			System.out.println("Invalid input:" + IDType);
			foundElements = null;
			break;
		}

		return foundElements;
	}

	/**
	 * This method returns a By which locates element by the value of given
	 * locator
	 * 
	 * @param IDType
	 *            Locator Type
	 * @param ID
	 *            Locator Value
	 * @return Success or failure message
	 */
	public static By mobilefind(String IDType, String ID) {
		try {
			if (ID.toUpperCase().contains("$$OBJECT$$")) {
				if (hqdriver.TempVariable != "")
					ID = ID.replace("$$OBJECT$$", hqdriver.TempVariable);
			}
			// System.out.println("LocatorValue:"+ID);

			switch (IDType) {
			case "Class Name":
				return By.className(ID);
			case "Css Selector":
				return By.cssSelector(ID);
			case "ID":
				return By.id(ID);
			case "Link Text":
				return By.linkText(ID);
			case "XPath":
				return By.xpath(ID);
			case "Name":
				return By.name(ID);
			case "Partial Link Text":
				return By.partialLinkText(ID);
			case "Tag Name":
				return By.tagName(ID);
			default:
				return null;
			}
		} catch (Exception e) {
			System.out.println(e.toString());
			return null;
		}
	}

}
