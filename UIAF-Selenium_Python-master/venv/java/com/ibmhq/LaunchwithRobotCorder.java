package com.ibmhq;

import java.io.File;
import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class LaunchwithRobotCorder {

	public static String robotCorder() throws InterruptedException {

		String downloadFilepath = "Selenium\\TestData";
		String strstatus = null;
		System.setProperty("webdriver.chrome.driver", "Selenium\\lib\\chromedriver.exe");
		WebDriver driver = null;
		ChromeOptions options = new ChromeOptions();
		options.addExtensions(new File("Selenium\\ext\\Robotcorder\\robotcorder.crx"));
		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
		chromePrefs.put("profile.default_content_settings.popups", 0);
		chromePrefs.put("download.default_directory", downloadFilepath);
		options.setExperimentalOption("prefs", chromePrefs);
		// cap.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR,
		// UnexpectedAlertBehaviour.IGNORE);
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.navigate().to("chrome-extension://ifiilbfgcemdapeibjfohnfpfmfblmpd/src/popup.html");
		// driver.get("https://qa1shop.cat.com/wcs-static/getMyCookie.html");
		// Thread.sleep(2000);
		// driver.findElement(By.xpath("//button[@onclick='promptFunction()']")).click();
		// Alert alert=driver.switchTo().alert();
		// driver.switchTo().alert().sendKeys("Yellow");
		// alert.accept();
		// Thread.sleep(1000);
		// alert.accept();
		// System.out.println(alert.getText());
		return strstatus;
	}

}
