package com.ibmhq;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class VerifyText {

	public String execute(String type, String value, String text) {

		String strstatus = null;
		try {
			WebElement element = Elements.find(type, value);
			if (element.getText().contains(text)) {
				strstatus = "Passed%%Expected text: " + text + " matched with text on the UI: " + element.getText();
			} else {
				strstatus = "Failed%%Error expected text: " + text + " does not match text on the UI: "
						+ element.getText();
			}
		} catch (Exception e) {
			strstatus = "Failed%%" + e.getMessage();
		}
		return strstatus;
	}

	public String xmlParser() {
		String strstatus = null;
		String OrderId = null;
		String OrderNumberMessage = Browser.Driver.getPageSource();
		try {

			Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder()
					.parse(new InputSource(new StringReader(OrderNumberMessage)));

			NodeList OrderNo = doc.getElementsByTagName("Order");
			if (OrderNo.getLength() > 0) {
				Element ele = (Element) OrderNo.item(0);
				OrderId = ele.getAttribute("OrderNo");
				System.out.println(OrderId);

				hqdriver.storedata = OrderId;
				strstatus = "Passed%% " + OrderId;
			} else {
				strstatus = "Failed%%" + OrderId;
			}

		} catch (Exception e) {

		}
		return strstatus;
	}

	// Added Dec24/18 for compare text --custom code

	public String VerifyAttributeValue(String type, String value, String text) {
		String strstatus = null;
		try {
			WebElement element = Elements.find(type, value);

			if (element.getAttribute("value").contains(text)) {
				strstatus = "Passed%%Expected text: " + text + " matched with text on the UI: "
						+ element.getAttribute("value");
			} else {
				strstatus = "Failed%%Error expected text: " + text + " does not match text on the UI: "
						+ element.getAttribute("value");
			}
		} catch (Exception e) {
			strstatus = "Failed%%" + e.getMessage();
		}
		return strstatus;
	}
public String VerifyTextJS(String type, String value, String text) {

		String strstatus = null;
		try {
			WebElement element = Elements.find(type, value);
			JavascriptExecutor js = (JavascriptExecutor) Browser.Driver;
			if(js.executeScript("return arguments[0].value", element).toString().equals(text)){
				strstatus = "Passed%%Expected text: " + text + " matched with text on the UI: " + js.executeScript("return arguments[0].value", element).toString();
			} else {
				strstatus = "Failed%%Error expected text: " + text + " does not match text on the UI: "
						+ js.executeScript("return arguments[0].value", element).toString();
			}
		} catch (Exception e) {
			strstatus = e.getMessage();
		}
		return strstatus;
	}
}
