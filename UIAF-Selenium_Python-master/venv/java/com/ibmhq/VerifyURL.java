package com.ibmhq;

public class VerifyURL {
	public String execute(String value) {

		String strstatus = null;
		try {
			String strtitle = Browser.Driver.getCurrentUrl();

			if (strtitle.contains(value)) {
				strstatus = "Passed%%The expected URL: " + value + " is matched with the URL displayed: "
						+ Browser.Driver.getCurrentUrl();
			} else {
				strstatus = "Failed%%The expected URL: " + value + " does not match URL displayed: "
						+ Browser.Driver.getCurrentUrl();
			}
		} catch (Exception e) {
			strstatus = "Failed%%" + e.getMessage();
		}
		return strstatus;
	}
}