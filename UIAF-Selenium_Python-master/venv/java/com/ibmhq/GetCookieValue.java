package com.ibmhq;

import org.openqa.selenium.Cookie;

public class GetCookieValue {

	public String execute(String cName) {

		String strstatus = null;
		try {
			Cookie cookie = Browser.Driver.manage().getCookieNamed(cName);

			if (cookie != null) {
				String strcookie = cookie.getValue();
				hqdriver.storedata = strcookie;
				strstatus = "Passed%%The cookie value: " + strcookie + " is stored";
			} else {
				strstatus = "Failed%%The cookie: " + cName + " doesn't found";
			}
		} catch (Exception e) {
			strstatus = "Failed%%" + e.getMessage();
		}

		return strstatus;

	}
}
