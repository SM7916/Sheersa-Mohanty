package com.ibmhq;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Trial {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "Selenium\\lib\\chromedriver.exe");
  	    ChromeOptions options = new ChromeOptions();	
		options.addArguments("--start-maximized");   					  
		WebDriver driver = new ChromeDriver(options);
		driver.get("https://www.smithsdetection.com/market-sectors/aviation/");
		driver.findElement(By.id("cn-accept-cookie-custom")).click();
		Thread.sleep(3000);
		WebElement air = driver.findElement(By.xpath("//span[contains(text(),'Air Cargo')]"));
		driver.findElement(By.id("USERNAME")).sendKeys("testuser");
		air.click();
		System.out.println("Done");

	}

}
