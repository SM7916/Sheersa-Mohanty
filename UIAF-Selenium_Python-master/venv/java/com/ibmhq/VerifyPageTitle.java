package com.ibmhq;

public class VerifyPageTitle {
	public String execute(String title) {

		String strstatus = null;
		try {
			if (title.equals(Browser.Driver.getTitle())) {
				strstatus = "Passed%%The page title " + Browser.Driver.getTitle()
						+ " is matched with expected title of: " + title;
			} else {
				strstatus = "Failed%%The page title " + Browser.Driver.getTitle()
						+ " does not match expected title of: " + title;
			}
		} catch (Exception e) {
			strstatus = "Failed%%" + e.getMessage();
		}
		return strstatus;
	}
}