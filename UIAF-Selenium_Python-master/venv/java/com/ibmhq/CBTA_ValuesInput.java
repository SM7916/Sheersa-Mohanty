package com.ibmhq;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class CBTA_ValuesInput {
	int counter;
	
	public String enterData(Sheet objdatainputSheet) throws AWTException {

		String strstatus = null;
		try {
			XSSFRow row = (XSSFRow) objdatainputSheet.getRow(0);
			for (int i = 1; i < row.getLastCellNum(); i++) {
				String Executable = new com.ibmhq.LoadExcel().readExcel(objdatainputSheet, i, "Executable");
				System.out.println("Executable is " + Executable);
				if (Executable.equalsIgnoreCase("VA01")) {
					try {
						Thread.sleep(2000);
						if (i > 10) {
							// Click on comment, then click down arrow to make
							// new row visible
							Add_Row(i);
						}

						Enter_type(i);

						Enter_Name(i, objdatainputSheet);

						Enter_Comment(i, objdatainputSheet);

						Enter_FirstValue(i, objdatainputSheet);

						String Second_Usage = new com.ibmhq.LoadExcel().readExcel(objdatainputSheet, i, "Parameter2-Usage");
						System.out.println(Second_Usage);
						if (!(Second_Usage.equalsIgnoreCase("N/A"))) {
							Enter_SecondValue(i, objdatainputSheet);
						}

						// WebDriverWait wait = new WebDriverWait(Browser.Driver, 50);
					} catch (Exception s) {
						System.out.println(s);
					}
				}
			}

			Save();
			strstatus = "Passed%%Executed Successfully";
		} catch (Exception e) {
			strstatus = "Failed%%" + e.getMessage();
		}
		return strstatus;
	}

	public void Enter_type(int i) throws InterruptedException {
		// Type
		counter = 1;
		String GetText_Temp="";
		do {
			try{
				String Type_XPathValue = "//tr[@rr='" + i + "']/td[2]/table/tbody/tr/td[2]";
				System.out.println(i + " Type: " + Type_XPathValue);
				WebElement type = Elements.find("XPath", Type_XPathValue);
				Thread.sleep(2000);
				type.click();
				Thread.sleep(2000);
				System.out.println("Element type clicked");
				Robot ro = new Robot();
				ro.keyPress(KeyEvent.VK_DOWN);
				Thread.sleep(2000);
				ro.keyPress(KeyEvent.VK_ENTER);
				Thread.sleep(2000);
				System.out.println("Down enter performed");
	
				WebElement type_Comp = Elements.find("XPath", "//tr[@rr='" + i + "']/td[2]/table/tbody/tr/td/input");
				System.out.println("Found input type...");
				GetText_Temp = type_Comp.getAttribute("value");
				System.out.println("Type value is " + GetText_Temp);
				counter++;
				System.out.println(counter);
			}catch(Exception TypeException){
				System.out.println("Exception in Type field...");
			}
		} while ((counter > 5) && !(GetText_Temp.isEmpty()));
	}

	public void Enter_Name(int i, Sheet objdatainputSheet) throws InterruptedException {
		// Name
		String Name_GetText="";
		counter = 1;
		do{
			try{
				String Name_XV = "//tr[@rr='" + i + "']//td[@cc='2']";
				System.out.println(i + " Name: " + Name_XV);
				Elements.find("XPath", Name_XV).click();
				Thread.sleep(2000);
		
				String NameInput_XV = "(//input[@ct='CBS'][@class='lsField__input'])[1]";
				// Elements.find("XPath", NameInput_XV).sendKeys("CBTA_GUI_SETTEXT");
				String Name_Data = new com.ibmhq.LoadExcel().readExcel(objdatainputSheet, i, "CBTA-TSStep-Name");
				Elements.find("XPath", NameInput_XV).sendKeys(Name_Data);
				Elements.find("XPath", NameInput_XV).sendKeys(Keys.ENTER);
				Thread.sleep(2000);
				Name_GetText = Elements.find("XPath", "//tr[@rr='" + i + "']//td[@cc='2']/a/span").getText();
				System.out.println(Name_GetText);
				counter++;
			}catch(Exception NameException){
				System.out.println("Exception in Name field...");
			}
		}while((counter > 5) && Name_GetText.isEmpty());
	}

	public void Enter_Comment(int i, Sheet objdatainputSheet) throws InterruptedException {
		// Comment
		String Comment_GetText="";
		counter = 1;
		do{
			try{
				String Comment_XV = "//tr[@rr='" + i + "']//td[@cc='3']";
				System.out.println(i + " Comment: " + Comment_XV);
				Elements.find("XPath", Comment_XV).click();
				Thread.sleep(2000);
		
				String CommentInput_XV = "(//input[@class='lsField__input'][@ct='CBS'])[1]";
				String Comment_Data = new com.ibmhq.LoadExcel().readExcel(objdatainputSheet, i, "Comment");
				Elements.find("XPath", CommentInput_XV).sendKeys(Comment_Data);
				Comment_GetText = Elements.find("XPath", CommentInput_XV).getText();
				System.out.println(Comment_GetText);
				counter++;
			}catch(Exception CommentException){
				System.out.println("Exception in Comment field...");
			}
		}while((counter > 5) && Comment_GetText.isEmpty());
	}

	public void Enter_FirstValue(int i, Sheet objdatainputSheet) throws InterruptedException {
		for (int j = 1; j < 3; j++) {
			if (j == 1) {
				// Value
				try {
					String Value_XV = "//tr[@rr='" + j + "'][@class='lsCondensed urST5SelColUiSingle']/td[6]";
					System.out.println("j: " + j + Value_XV);
					Elements.find("XPath", Value_XV).click();
					Thread.sleep(2000);

					String ValueInput_XV = "//tr[@rr='" + j
							+ "'][@class='lsCondensed urST5SelColUiSingle'][@sst='0']/td[@cc='5']/span//input[@ct='CBS'][@ti='0'][@class='lsField__input']";
					String First_Value = new com.ibmhq.LoadExcel().readExcel(objdatainputSheet, i,
							"CBTA-Parameter1-Value");
					System.out.println("j: " + j + ValueInput_XV);

					Elements.find("XPath", "//input[@value='[Standard View]']").click();
					Thread.sleep(2000);
					// wait.until(ExpectedConditions.elementToBeClickable(By.xpath(ValueInput_XV)));
					Thread.sleep(2000);
					Actions action = new Actions(Browser.Driver);
					WebElement a = Browser.Driver.findElement(By.xpath(ValueInput_XV));
					action.moveToElement(a).click().perform();
					action.sendKeys(a, First_Value).perform();
					a.sendKeys(Keys.ENTER);
					Thread.sleep(2000);
				} catch (Exception v) {
					System.out.println("In j1 Exception " + v);
				}
			}
		}
	}

	public void Enter_SecondValue(int i, Sheet objdatainputSheet) throws InterruptedException, AWTException {

		try {
			// Value
			String Value_XV = "//tr[@rr='2'][@class='lsCondensed urST5SelColUiSingle']/td[6]";
			System.out.println("j: " + Value_XV);
			// wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Value_XV)));
			Elements.find("XPath", Value_XV).click();
			Thread.sleep(2000);

			String ValueInput_XV = "//tr[@rr='2'][@class='lsCondensed urST5SelColUiSingle'][@sst='0']/td[@cc='5']/span//input[@ct='CBS'][@ti='0'][@class='lsField__input']";
			String Second_Value = new com.ibmhq.LoadExcel().readExcel(objdatainputSheet, i, "Parameter2-Value");
			System.out.println("j: " + ValueInput_XV);

			Elements.find("XPath", "//input[@value='[Standard View]']").click();
			Thread.sleep(2000);
			// wait.until(ExpectedConditions.elementToBeClickable(By.xpath(ValueInput_XV)));
			Thread.sleep(2000);
			Actions action = new Actions(Browser.Driver);
			WebElement a = Browser.Driver.findElement(By.xpath(ValueInput_XV));
			action.moveToElement(a).click().perform();
			action.sendKeys(a, Second_Value).perform();
			a.sendKeys(Keys.ENTER);
			Thread.sleep(2000);

		} catch (Exception vc) {
			System.out.println("In j2 Exception " + vc);
		}

		// Usage
		Thread.sleep(2000);
		Elements.find("XPath", "//input[@value='[Standard View]']").click();
		Thread.sleep(2000);
		String Usage_XV = "//tr[@rr='2'][@class='lsCondensed urST5SelColUiSingle'][@sst='0']/td[2]/table/tbody/tr/td/span";
		WebElement Usage = Elements.find("XPath", Usage_XV);
		Usage.click();
		Thread.sleep(2000);

		// Elements.find("XPath", "//tr[@rr='"+ j +"'][@class='lsCondensed
		// urST5SelColUiSingle'][@sst='0']/td[2]/table/tbody/tr/td[2]/span").click();;

		Thread.sleep(2000);
		Robot ro = new Robot();
		ro.keyPress(KeyEvent.VK_UP);
		Thread.sleep(2000);
		ro.keyPress(KeyEvent.VK_ENTER);
		Thread.sleep(2000);
	}

	public void Save() throws InterruptedException {
		Browser.Driver.switchTo().defaultContent();
		Browser.Driver.switchTo().frame("URLSPW-0");
		Thread.sleep(2000);
		Elements.find("XPath", "//span[contains(text(),'Save')]//parent::span//parent::div").click();
		Thread.sleep(2000);
	}
	
	public void Add_Row(int i) throws InterruptedException, AWTException{
		String Comment_XV = "//tr[@rr='" + (i - 1) + "']//td[@cc='3']";
		System.out.println(i + " Comment: " + Comment_XV);
		Elements.find("XPath", Comment_XV).click();
		Thread.sleep(2000);
		
		Robot ro = new Robot();
		ro.keyPress(KeyEvent.VK_DOWN);
		Thread.sleep(2000);
		// ro.keyPress(KeyEvent.VK_ENTER);
		Thread.sleep(2000);
	}
	
}
