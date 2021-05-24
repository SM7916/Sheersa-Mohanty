package com.ibmhq;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class MessageBox {
	public String PopUp() {
		String strstatus = null;
		try {
			//JavascriptExecutor js=(JavascriptExecutor)Browser.Driver;
			//(JavascriptExecutor)Browser.Driver).executeScript("alert('Hello')");
			//js.executeScript("alert('This is an alert');");
			JFrame frame = new JFrame();
			frame.setAlwaysOnTop(true);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
			frame.setVisible(false);

			JOptionPane.showMessageDialog(frame,"Press OK to continue...");
			strstatus = "Passed%%A pop is created";
		} catch (Exception e) {
			strstatus = "Failed%%" + e;
		}
		return strstatus;
	}
}
