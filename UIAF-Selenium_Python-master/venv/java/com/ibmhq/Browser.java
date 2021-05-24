package com.ibmhq;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Browser{
	
	public static WebDriver Driver;
	public static String MainWinHandle = null;
	String Browser_Type = null;
	
	public String execute(String Browser_Type,String URL) throws Exception {
		//System.out.println(Browser_Type);
		String strstatus = null;
		
		try {
			if (Browser_Type.equalsIgnoreCase("Firefox")){
			  System.setProperty("webdriver.gecko.driver", "Selenium\\lib\\geckodriver.exe");
			  FirefoxOptions firefoxOptions = new FirefoxOptions();
			  firefoxOptions.setCapability("marionette", true);
			  Driver = new FirefoxDriver(firefoxOptions);
		      Driver.manage().window().maximize();
		    }
		    else if (Browser_Type.equalsIgnoreCase("Chrome")){
	    	  System.setProperty("webdriver.chrome.driver", "Selenium\\lib\\chromedriver.exe");
	    	  ChromeOptions options = new ChromeOptions();	
   			  options.addArguments("--start-maximized");   					  
   			  Driver = new ChromeDriver(options);
		    }else if (Browser_Type.equalsIgnoreCase("Edge")){
	    	  System.setProperty("webdriver.chrome.driver","Selenium\\lib\\New folder\\chromedriver.exe");
	    	  Driver = new ChromeDriver();
	    	  Driver.manage().window().maximize();
			}
		    else if (Browser_Type.equalsIgnoreCase("Explorer")){
	    	  System.setProperty("webdriver.ie.driver", "Selenium\\lib\\IEDriverServer.exe");
	    	  DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
	    	  //InternetExplorerOptions IEOptions = new InternetExplorerOptions();
	    	  //IEOptions.setCapability("nativeEvents", false);
	    	  //IEOptions.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
		      caps.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);
		      caps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		      InternetExplorerOptions options = new InternetExplorerOptions();
		      options.merge(caps);
		      Driver = new InternetExplorerDriver(options);
		      Driver.manage().window().maximize();
		    }
		    else if (Browser_Type.equalsIgnoreCase("HeadlessFirefox")){
				  System.setProperty("webdriver.gecko.driver", "Selenium\\lib\\geckodriver.exe");
				  FirefoxOptions firefoxOptions = new FirefoxOptions();
				  firefoxOptions.setCapability("marionette", true);
				  
				  FirefoxBinary firefoxBinary = new FirefoxBinary();
				  firefoxBinary.addCommandLineOptions("--headless");
			      firefoxOptions.setBinary(firefoxBinary);
				  
				  Driver = new FirefoxDriver(firefoxOptions);
			      Driver.manage().window().maximize();
			    }
		    else if (Browser_Type.equalsIgnoreCase("HeadlessChrome")){
		    	  System.setProperty("webdriver.chrome.driver", "Selenium\\lib\\chromedriver.exe");
		    	  ChromeOptions options = new ChromeOptions();	
		    	  options.addArguments("window-size-1400,800");
		  		  options.addArguments("headless"); 			  
	   			  Driver = new ChromeDriver(options);
		    }
			if (URL != null){
		      if (URL.startsWith("http://") || URL.startsWith("https://")){
		        Driver.get(URL);
		      }
		      else{
		        Driver.get("http://"+ URL);
		      }
		    }
			
		    MainWinHandle = Driver.getWindowHandle();
		    
		    if(Browser.Driver != null){
		    	strstatus = "Passed%%The URL: " + URL + " is launched in the browser: "+ Browser_Type +" successfully";
		    }else{
		    	strstatus = "Failed%%The URL: " + URL + " is NOT launched in the browser: "+ Browser_Type;
		    }
		} catch(Exception e) {
			System.out.println(e.getMessage());
			strstatus = "Failed%%" + e.getMessage();
		}
		return strstatus;
	}
	
}