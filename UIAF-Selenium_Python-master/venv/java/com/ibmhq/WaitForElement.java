package com.ibmhq;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.NoSuchElementException;

/*
public class WaitForElement {
  public String execute(String type,String value,int wtime) {

	  String strstatus = null;
	  try {
	    int count = wtime;
	    while(count >= 0){
	      List<WebElement> elements = Elements.findAll(type,value);
	      if(elements.size() > 0) break;
	      count--;
	    }
	    
	    if(count <= 0){
	      assert false: "Element was not found in " + wtime + " seconds.";
	    }
	    
		if (count > 0) {
			strstatus = "Passed%%The Element was found in " + (wtime-count) + " seconds.";
		}else {
			strstatus = "Failed%%The Element was NOT found in " + wtime + " seconds.";
		}
	  } catch(Exception e) {
		  strstatus  = e.getMessage();
	  }
	return strstatus;
  }
}
*/

public class WaitForElement extends ExplicitWaitType {
	public String execute(String type, String value, int wtime) {

		String strstatus = null;
		try {
			int count = wtime;
			while (count >= 0) {
				List<WebElement> elements = Elements.findAll(type, value);
				if (elements.size() > 0)
					break;
				count--;
			}

			if (count <= 0) {
				assert false : "Element was not found in " + wtime + " seconds.";
			}

			if (count > 0) {
				strstatus = "Passed%%The Element was found in " + (wtime - count) + " seconds.";
			} else {
				strstatus = "Failed%%The Element was NOT found in " + wtime + " seconds.";
			}
		} catch (Exception e) {
			strstatus = "Failed%%" + e.getMessage();
		}
		return strstatus;
	}

	public String waitForDuration(int wait) {

		String strstatus = null;
		try {
			wait = wait * 1000;
			Thread.sleep(wait);
			strstatus = "Passed%%Wait for duration " + wait + "secs";
		} catch (InterruptedException e) {
			strstatus = "Failed%%Unaable to wait";
			e.printStackTrace();
		}
		return strstatus;
	}

	public String waitForFrameExistenceAndSwitchToIt(String type, String value) throws InterruptedException {

		String strstatus = null;
		WebElement element = Elements.find(type, value);

		WebDriverWait wait = new WebDriverWait(Browser.Driver, 20);

		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(element));
		strstatus = "Passed%%Wait for Frame to be available and switch to it";
		return strstatus;
	}

	// Added for Unknown error -Jan 14

	public String WaitElementClick(String type, String value, String wtime) {

		String strstatus = null;
		int wait = Integer.parseInt(wtime);
		try {

			int count = wait * 1000;
			WebDriverWait mynwait = (new WebDriverWait(Browser.Driver, count));
			mynwait.until(ExpectedConditions.elementToBeClickable(com.ibmhq.Locators.find(type, value)));

			strstatus = "Passed%%The Element was found in within " + wait + "seconds.";
		} catch (Exception e) {
			strstatus = "Failed%%Unable to find the element after waiting for: " + wait + "seconds." + e.getMessage();
		}
		return strstatus;
	}

	// Added code for Fluentwait -Jan14

	public String WaitForValueToBePopulated(String type, String value, String wtime) {

		String strstatus = null;
		int waitValue = Integer.parseInt(wtime);
		try {

			FluentWait<WebDriver> wait = new FluentWait<WebDriver>(Browser.Driver)
					.withTimeout(Duration.ofSeconds(waitValue)).pollingEvery(Duration.ofSeconds(5))
					.ignoring(NoSuchElementException.class);
			wait.until(new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver d) {
					Boolean Result = false;
					WebElement element1 = d.findElement(com.ibmhq.Locators.find(type, value));
					String value = element1.getAttribute("value");
					if (value.length() != 0 && !value.equals("0")) {
						Result = true;
					}
					return Result;
				}
			});

			strstatus = "Passed%%The Element was found in within " + wait + "seconds.";
		} catch (Exception e) {
			strstatus = "Failed%%Unable to find the element after waiting for: " + waitValue + "seconds." + e.getMessage();
		}
		return strstatus;
	}

}