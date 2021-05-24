package com.ibmhq;

public class SetTimeOut {
	public String execute(int secs) {

		String strstatus = null;
		try {
			Browser.Driver.manage().timeouts().implicitlyWait(secs, java.util.concurrent.TimeUnit.SECONDS);
			strstatus = "Passed%%The browser had waited for: " + secs + " seconds";
			return strstatus;
		} catch (Exception e) {
			strstatus = "Failed%%" + e.getMessage();
		}
		return strstatus;
	}
	
	public String delay(int secs) {
		String strstatus = null;
		try {
			java.util.concurrent.TimeUnit.SECONDS.sleep(secs);
			strstatus = "Passed%%The browser had waited (hard wait) for: " + secs + " seconds";
			return strstatus;
		} catch (Exception e) {
			strstatus = e.getMessage();
		}
		return strstatus;
	}
}
