package com.ibmhq;

public class RunAutoIT {
	public String TriggerExe() {
		String strstatus = null;
		try {
			Runtime rt = Runtime.getRuntime() ;     
			Process p = rt.exec(System.getProperty("user.dir") + "\\Selenium\\TestData\\a.exe") ;  
			strstatus = "Passed%%The AutoIt is triggered";
		} catch (Exception e) {
			strstatus = "Failed%%The AutoIt script failed " + e.getMessage();
		}
		return strstatus;
	}
}
