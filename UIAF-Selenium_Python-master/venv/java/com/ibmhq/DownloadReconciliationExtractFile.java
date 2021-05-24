package com.ibmhq;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import java.util.Vector;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.ProxySOCKS5;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import com.jcraft.jsch.SftpProgressMonitor;

public class DownloadReconciliationExtractFile {

	static Logger log = LogManager.getLogger(FileCopierOverNetwork.class.getName());

	final static SftpProgressMonitor monitor = new SftpProgressMonitor() {
		public void init(final int op, final String source, final String target, final long max) {
			System.out.println("sftp start uploading file from:" + source + " to:" + target);
		}

		public boolean count(final long count) {
			System.out.println("sftp sending bytes: " + count);
			return true;
		}

		public void end() {
			System.out.println("sftp uploading is done.");
		}
	};

	String strstatus = null;

	@SuppressWarnings("rawtypes")
	public String getFile(String FTPDetails) throws JSchException, SftpException {
		try {
			String Details = "";
			if (FTPDetails.contains("#")) {

				Details = FTPDetails;
				System.out.println(Details);

				String proxyIP = Details.split("#")[0];
				String proxyport = Details.split("#")[1];
				int pport = Integer.parseInt(proxyport);
				String hostname = Details.split("#")[2];
				String portInitial = Details.split("#")[3];
				int port = Integer.parseInt(portInitial);
				String username = Details.split("#")[4];
				String password = Details.split("#")[5];
				String copyFrom = Details.split("#")[6];
				String copyTo = Details.split("#")[7];
				String batchcode = Details.split("#")[8];
				String DNSP = Details.split("#")[9];
				String FRMP = Details.split("#")[10];
				String ThreadCount = Details.split("#")[11];
				String BatchBusinessDate = Details.split("#")[12]; // its in
																	// MM-dd-yyyy
				// In Batch Control, Enter Batch Code

				Browser.Driver.findElement(By.name("BATCH_CD")).sendKeys(batchcode);
				Thread.sleep(5000);
				Browser.Driver.findElement(By.name("BATCH_THREAD_NBR")).click();

				// Enter Batch Thread Count
				Browser.Driver.findElement(By.name("BATCH_THREAD_CNT")).sendKeys(ThreadCount);
				Thread.sleep(5000);
				// Enter Batch Business Date
				Browser.Driver.findElement(By.name("PROCESS_DT")).sendKeys(BatchBusinessDate);

				Browser.Driver.switchTo().frame("BJP");
				// Update File Path
				Browser.Driver.findElement(By.id("BJP:1$BATCH_PARM_VAL")).click();
				Browser.Driver.findElement(By.id("BJP:1$BATCH_PARM_VAL")).clear();
				Browser.Driver.findElement(By.id("BJP:1$BATCH_PARM_VAL")).sendKeys("/localCCB/CTPST02/data_out/work/");
				Browser.Driver.findElement(By.id("BJP:2$BATCH_PARM_VAL")).click();
				Thread.sleep(10000);
				Browser.Driver.switchTo().defaultContent().switchTo().frame("main");
				// Click Save Button
				Browser.Driver.findElement(By.id("IM_SAVE")).click();
				Thread.sleep(20000);
				// Click Refresh Button
				Browser.Driver.findElement(By.id("IM_REFRESH")).click();
				Thread.sleep(20000);
				// Click Refresh Button
				Browser.Driver.findElement(By.id("IM_REFRESH")).click();
				Thread.sleep(20000);
				// Click Batch Context Menu
				Browser.Driver.switchTo().frame("tabPage");
				Browser.Driver.findElement(By.id("IM_Main_batCtx")).click();
				Thread.sleep(10000);
				// Click Go To Batch Run Tree Menu
				Browser.Driver.switchTo().defaultContent();
				Browser.Driver.switchTo().frame("main");
				Browser.Driver.findElement(By.xpath("//span[contains(text(),'Go To Batch Run Tree')]")).click();
				Thread.sleep(15000);
				// Retrieve Batch Run Number in Batch Run Tree

				String parentWindow = Browser.Driver.getWindowHandle();
				Set<String> handles = Browser.Driver.getWindowHandles();
				for (String windowHandle : handles) {
					if (!windowHandle.equals(parentWindow)) {
						Browser.Driver.switchTo().window(windowHandle);
						// <!--Perform your operation here for new window-->
						// Click Batch Number Header to get the latest Batch Run
						// #
						Browser.Driver.switchTo().frame("dataframe");
						Browser.Driver.findElement(By.id("L_BATCH_NBR")).click();
						Thread.sleep(5000);
						Browser.Driver.findElement(By.id("L_BATCH_NBR")).click();
						Thread.sleep(5000);
						// Click the latest row
						Browser.Driver.findElement(By.xpath("//tbody[@id='dataTableBody']/tr[1]/td[1]")).click();
						Thread.sleep(25000);

						System.out.println("before main");

						Browser.Driver.switchTo().window(parentWindow); // cntrl
																		// to
																		// parent
																		// window
					}
				}

				System.out.println("after parentWindow");

				Browser.Driver.switchTo().frame("main");
				Browser.Driver.switchTo().frame("tabPage");
				Thread.sleep(3000);
				System.out.println("after tabPage");
				String BatchNumber = Browser.Driver.findElement(By.name("BATCH_NBR")).getText();
				System.out.println("Batch #:" + BatchNumber);

				System.out.println("Initiate getting file from Linux Server...");
				JSch jsch = new JSch();
				Session session = null;
				System.out.println("Trying to connect.....");
				session = jsch.getSession(username, hostname, port);
				// session.setConfig("StrictHostKeyChecking", "no");
				// <<<<<<< (Added SOCKS Proxy Settings) by @Jong Aguasin
				// 11-10-2018 >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
				session.setProxy(new ProxySOCKS5(proxyIP, pport));
				// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

				session.setPassword(password);
				session.connect();
				System.out.println("is server connected? " + session.isConnected());
				Thread.sleep(5000);
				Channel channel = session.openChannel("sftp");
				channel.connect();
				ChannelSftp sftpChannel = (ChannelSftp) channel;
				System.out.println("Before entering into the path" + copyFrom);
				try {
					sftpChannel.cd(copyFrom);
					System.out.println("Inside the folder " + copyFrom);
					Vector filelist = sftpChannel.ls(copyFrom);
					// create SimpleDateFormat object with source string date
					// format

					SimpleDateFormat sdfSource = new SimpleDateFormat("dd-MM-yyyy");

					// parse the string into Date object
					Date date = sdfSource.parse(BatchBusinessDate);

					// create SimpleDateFormat object with desired date format
					SimpleDateFormat sdfDestination = new SimpleDateFormat("yyyy-MM-dd");

					// parse the date into another format
					BatchBusinessDate = sdfDestination.format(date);

					System.out.println("Date is converted from dd-MM-yyyy format to yyyy-MM-dd");
					System.out.println("Converted date is : " + BatchBusinessDate);
					String BBDate = BatchBusinessDate.replaceAll("-", "");

					String fileNamePattern = "";
					String fileNameformat = "CustomerDataReconciliation-%s-%s-%s(\\d{4})-%s-\\d{3}.xml";

					fileNamePattern = String.format(fileNameformat, DNSP, FRMP, BBDate, BatchNumber);

					System.out.println("After appending date file name = " + fileNamePattern);

					for (int i = 0; i < filelist.size(); i++) {
						System.out.println(filelist.get(i).toString());
						System.out.println("fILEPattern to Matched = " + fileNamePattern);
						String serverFileName = filelist.get(i).toString();
						// System.out.println(serverFileName);
						String str = new String(serverFileName);
						int indexnum = str.lastIndexOf(" ") + 1;
						// String array1[]= str.split(" ");
						String temp = str.substring(indexnum);
						System.out.println(temp);

						if (temp.matches(fileNamePattern)) {
							System.out.println("file is found");

							sftpChannel.get(copyFrom + temp, copyTo + temp, monitor, ChannelSftp.OVERWRITE);
							System.out.println("file is found at: " + copyFrom + temp + copyTo + temp);

						} else {
							System.out.println("Filename did not match...");

						}
					}
					sftpChannel.exit();
					session.disconnect();
				} finally {

					System.out.println("Finished getting file from Linux Server...");
				}
			}

		} catch (Exception e) {
			strstatus = "Failed%%" + e.getMessage();
			System.out.println(strstatus);
		}
		System.out.println("Done !!");
		return strstatus;
	}
}