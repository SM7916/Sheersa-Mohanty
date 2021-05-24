package com.ibmhq;

public class Exists {

	public String execute(String type, String value) throws Exception {

		String strstatus = null;
		try {

			if (Elements.find(type, value) != null) {
				strstatus = "Passed%%The Element: " + hqdriver.stepObject + " exists in the application";
			} else {
				strstatus = "Failed%%The Element: " + hqdriver.stepObject + " does not exist in the application";
			}
		} catch (Exception e) {
			strstatus = "Failed%%" + e.getMessage();
		}
		return strstatus;
	}
}
