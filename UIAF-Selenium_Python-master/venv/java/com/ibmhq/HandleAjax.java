package com.ibmhq;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HandleAjax {

	public String waitToDissapearLoader(String IDType, String ID, String wtime) throws Exception {
		String strstatus = null;
		int initialLoad = 2;
		WebDriverWait waita = new WebDriverWait(Browser.Driver, initialLoad);
		WebDriverWait wait = new WebDriverWait(Browser.Driver, Integer.valueOf(wtime));
		try {
			try {
				waita.until(ExpectedConditions.visibilityOfElementLocated(com.ibmhq.Locators.find(IDType, ID)));
				wait.until(ExpectedConditions.invisibilityOfElementLocated(com.ibmhq.Locators.find(IDType, ID)));
			} catch (Exception E) {
				wait.until(ExpectedConditions.invisibilityOfElementLocated(com.ibmhq.Locators.find(IDType, ID)));
				Thread.sleep(2000);
			}
			strstatus = "Passed%%The Element: " + hqdriver.stepObject + " dissapeared";
		} catch (Exception e) {
			strstatus = "Failed%%" + e.getMessage();
		}
		return strstatus;
	}

}
