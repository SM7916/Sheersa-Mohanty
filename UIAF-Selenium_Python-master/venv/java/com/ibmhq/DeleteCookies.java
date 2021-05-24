package com.ibmhq;

public class DeleteCookies {

	public String execute(String CookieName) {

		String strstatus = null;
		try {
			if (CookieName == "ALL") {
				Browser.Driver.manage().deleteAllCookies();
			} else {
				Browser.Driver.manage().deleteCookieNamed(CookieName);
			}

			strstatus = "Passed%%The cookie name: " + CookieName + " is deleted successfully";

		} catch (Exception e) {
			strstatus = "Failed%%" + e.getMessage();
		}
		return strstatus;
	}
}
