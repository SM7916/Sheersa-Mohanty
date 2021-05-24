/**
* The hqdriver class is the main driver of ibmhq framework
* which generates doc reports along with execution
*
* @author  Rajkumar Natarajan
* @version 1.0
* @since   2017-06-07
* 
* Licensed Materials - Property of IBM
* 6949 - 66T
*@ Copyright IBM Corp. 2017 All Rights Reserved
*
*/

package com.ibmhq;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
//import com.relevantcodes.extentreports.reporter.ExtentHtmlReporter;
//import com.relevantcodes.extentreports.reporter.configuration.Theme;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.io.File;
import org.openqa.selenium.OutputType;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Sheet;
import java.io.IOException;
import py4j.GatewayServer;
import org.openqa.selenium.TakesScreenshot;
public class hqdriver {
    //public ExtentReports extent = new ExtentReports("Test_Report.html");
//    public ExtentHtmlReporter EHtmlReport;
    public ExtentReports extent;
    public ExtentTest test;
    public ExtentTest ENode;
	public static int statuscol=14,testdatausedcol=15,actualrescol=16,screenpathcol=17,dataset=1,wordStepCount=0;
	public static String outputHTMLFile;
    //public static int ifloopincrement,forloopcount,forloopstart,forloopend,currentrownumber,soapdatarow;
	public static int tcCount = 0;
	static String testtool = "SAP";
    public static int ifloopincrement=0,forloopcount,forloopstart,forloopend,currentrownumber=0,soapdatarow,row1;
        public static boolean loopstart;
        public static int startrowcount = 0;
        public static int endrowcount = 0;
        public static String excelname,excelpath,sheetName,ComporTCName="";
	public static String testdatapath,testresultpath;
	
	static String defecttool,dtURL,dtUser,dtPassword,almDomain,dtProject;
	String autodefect;
	String runid="ni";
	String testsetpath;
	String testProject;
	static String detectedBy;
	int jsonupdateCount=0;
	int jsonupdateCount1=0;
	static String mailUserName, mailPassword, MailTo, Subject;
	static int frequency;
	public static int ExecutionCount = 0,reportUpdate = 0;
	String assignedTo;
	public static String stepresult,storedata="",TempVariable,stepObject="";
	public static Sheet scriptsheet;
    	public void record() throws InterruptedException
	{
	   com.ibmhq.LaunchwithRobotCorder.robotCorder();
	}

	//public void generate_Script()
	//{
		//com.ibmhq.mainscript.generateScript();
	//}
	  public void setTestCaseName(String tcName )
     {
     ComporTCName = tcName;
     ifloopincrement=0;
     startrowcount = 0;
    endrowcount = 0;
     }
	public void create_extent(String htmlfile) throws UnknownHostException
	{
    	extent= new ExtentReports(htmlfile);
    	//extent.attachReporter(EHtmlReport);

			extent
                .addSystemInfo("Environment", "Automation Testing")
                .addSystemInfo("User Name",System.getProperty("user.name"))
                .addSystemInfo("Local Host", InetAddress.getLocalHost().getHostName())
                .addSystemInfo("IP Address", InetAddress.getLocalHost().getHostAddress());
                //loading the external xml file (i.e., extent-config.xml) which was placed under the base directory
                //You could find the xml file below. Create xml file in your project and copy past the code mentioned below
                String curr_dir= System.getProperty("user.dir");
                extent.loadConfig(new File(curr_dir+"\\Selenium\\configReport.xml"));
	}

	public void add_test_case_extent(String tcname)
	{
	test = extent.startTest(tcname);
	}


	public void log_step(String stepname,String stepactual,String status,String screenshotPath)
	{
	//test.log(LogStatus.INFO, stepname);
	//if (status.equalsIgnoreCase("pass"))
    //test.log(LogStatus.PASS, "Step Passed");
    //else
    //test.log(LogStatus.FAIL, "Step Failed");
   // ENode = test.startNode(stepname);
    if (status.equalsIgnoreCase("pass"))
    {
   // test.log(LogStatus.INFO, stepname);
   test.log(LogStatus.PASS,"Passed:"+stepname);
    // ENode.log(LogStatus.PASS, stepactual);
     }
     else
     {
   //  test.log(LogStatus.FAIL, stepname);
     test.log(LogStatus.FAIL,"Failed:"+stepname+test.addScreenCapture(screenshotPath));
    //test.log(LogStatus.FAIL,"Failed Screenshot " + test.addScreenCaptureFromBase64String(Base64StringofScreenshot));
    //test.log(LogStatus.FAIL,"Failed");
   // ENode.log(LogStatus.FAIL, stepactual);
    }

    }

     public void flush_extent()
     {
     extent.endTest(test);
     extent.flush();

     }


     public void TakeScreenshot(String imagefilepath)
     {
        try{
        TakesScreenshot ts = (TakesScreenshot) Browser.Driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File destination = new File(imagefilepath);
		FileUtils.copyFile(source,destination);
		}catch(Exception e)
		{
		System.out.println("Exception in taking screenshot for selected step in consolidate result "+ e);
		}

     }

	public String selectaction(String stepAction,String stepData,String objName,String objProp,String objVal,String sBrowser,int row) throws Throwable {
	        storedata="";
	        stepObject=objName;
	        System.out.println("##########################################################33");

		try {
			//if(testtool.equalsIgnoreCase("selenium")){
				switch (stepAction){
					case "AcceptAlert":
						int waitTime=Integer.parseInt(stepData);
					    stepresult = new com.ibmhq.HandleAlert().acceptAlert(waitTime);
					    break;
			
					case "AddValuetoTempVariable":
						objVal = stepData;
						stepresult = new com.ibmhq.TestUtil().AddValuetoTempVariable(stepData);
						break;
					case "customizedURL":
						stepresult = new com.ibmhq.BrowserNavigate().customizedURL(stepData);
					break;
					case "ConnectToDB":
						stepresult = new com.ibmhq.ConnectToDB().execute(stepData);
						break;	
					case "GetDBFirstColRecord":
						stepresult = new com.ibmhq.GetDBFirstColRecord().execute(stepData);
						break;
                        case "LaunchApplication":
						stepresult = new com.ibmhq.Browser().execute(sBrowser, stepData);
						break;
					case "BrowserNavigation":
						String[] navInfo = stepData.split("\\|\\|");
						stepresult = new com.ibmhq.BrowserNavigate().execute(navInfo[0], navInfo[1]);
						break;
					case "CaptureTextfromAlert":
						stepresult = new com.ibmhq.HandleAlert().CaptureTextfromAlert();
						break;
					case "ClickElement":
						stepresult = new com.ibmhq.ClickElement().execute(objProp, objVal);
						break;
					case "ClickElementUsingJS":
						stepresult = new com.ibmhq.ClickElement().executeJS(objProp, objVal);
						break;
					case "ClickElementUsingTABRobo":
						stepresult = new com.ibmhq.ClickElement().executeRobo(objProp, objVal,stepData);
						break;
					case "JavascriptExecutorElementClick":
						stepresult = new com.ibmhq.ClickElement().JavascriptExecutorElementClick(objProp,objVal);
						break; 
					case "RoboENTEROnElement":
						stepresult = new com.ibmhq.ClickElement().executeRoboeENTER(objProp, objVal);
						break;
					case "RoboENTER":
						stepresult = new com.ibmhq.ClickElement().executeRoboENTER();
						break;
					case "CloseBrowser":
						stepresult = new com.ibmhq.CloseBrowser().quitBrowser();
						break;
					case "CloseWindow":
						stepresult = new com.ibmhq.CloseBrowser().closeBrowser();
						break;
					case "DeleteCookies":
						stepresult = new com.ibmhq.DeleteCookies().execute(stepData);
						break;
					case "DismissAlert":
						stepresult = new com.ibmhq.HandleAlert().DismissAlert();
						break;
					case "DoubleClick":
						stepresult = new com.ibmhq.DoubleClick().execute(objProp, objVal);
						break;
					case "DragAndDrop":
						String[] dropObj = stepData.split("\\|\\|");
						stepresult = new com.ibmhq.DragAndDrop().execute(objProp, objVal, dropObj[0], dropObj[1]);
						break;
					case "DragAndDropToTgtFrame":
						stepresult = new com.ibmhq.DragAndDrop().targetFrame(objProp, objVal, stepData);
						break;
					case "Exists":
						stepresult = new com.ibmhq.Exists().execute(objProp, objVal);
						break;
					case "ExplicitWaitType":
						stepresult = ExplicitWaitType.presenceOfElementLocated(objProp, objVal, stepData);
						break;

					case "GetCookieValue":
						stepresult = new com.ibmhq.GetCookieValue().execute(stepData);
						break;
					case "GetNumbersOfTextElement":
					     stepresult = new com.ibmhq.GetText().getNumbersFromString(objProp, objVal, stepData);
					     break;
					case "GetText":
						stepresult = new com.ibmhq.GetText().execute(objProp, objVal, stepData);
						break;
						case "GetTextJS":
						stepresult = new com.ibmhq.GetText().GetTextJS(objProp, objVal, stepData);//added by neha
						break;
					case "GetTextSubstring":
						stepresult = new com.ibmhq.GetText().GetTextSubstring(objProp, objVal, stepData);//added by neha
						break;
					case "GetAttributeValue":
						stepresult = new com.ibmhq.GetText().GetAttributeValue(objProp, objVal, stepData);
						break;
					case "HandleAjax":
						stepresult = new com.ibmhq.HandleAjax().waitToDissapearLoader(objProp, objVal, stepData);
						break;
					case "IfLoop":
						stepresult = new com.ibmhq.IfLoop().execute(objProp, objVal, stepData);
						break;
					case "MouseOver":
						stepresult = new com.ibmhq.MouseOver().execute(objProp, objVal);
						break;
					case "RightClick":
						stepresult = new com.ibmhq.RightClick().execute(objProp, objVal);
						break;
					case "SaveFileIE":
						stepresult = new com.ibmhq.FileDownloadIE().execute();
						break;
					case "SelectItemByValue":
						stepresult = new com.ibmhq.SelectItem().execute(objProp, objVal, "Value", stepData);
						break;
					case "SelectItemByVisibleText":
						stepresult = new com.ibmhq.SelectItem().execute(objProp, objVal, "Visible Text", stepData);
						break;
					case "SelectItemByIndex":
						stepresult = new com.ibmhq.SelectItem().execute(objProp, objVal, "Index", stepData);
						break;
					case "SelectList":
						stepresult = new com.ibmhq.SelectList().execute(objProp, objVal, stepData);
						break;
                    case "SingleSelectList":
						stepresult = new com.ibmhq.SelectList().executeSingle(objProp, objVal, stepData);
						break;
					case "SendKey":
						stepresult = new com.ibmhq.SendKeys().sendkey(objProp, objVal, stepData);
						break;
					case "SendValue":
						stepresult = new com.ibmhq.SendKeys().sendvalue(objProp, objVal, stepData);
						break;
					case "AlertSendkeys":
						stepresult = new com.ibmhq.HandleAlert().AlertSendkeys(stepData);
					     break;
					case "SetCheckBox":
						boolean setchbox = Boolean.parseBoolean(stepData);
						stepresult = new com.ibmhq.SetCheckBox().execute(objProp, objVal, setchbox);
						break;
					case "SetCheckBoxAlt":
						boolean setchbox1 = Boolean.parseBoolean(stepData);
						stepresult = new com.ibmhq.SetCheckBox().executeAlt(objProp, objVal, setchbox1);
						break;
					case "SetRadioButton":
						//boolean setrdbtn = Boolean.parseBoolean(stepData);
						stepresult = new com.ibmhq.SetCheckBox().SetRadioButton(objProp, objVal);
						break;
					case "SetFocus":
						stepresult = new com.ibmhq.SetFocus().execute(objProp, objVal);
						break;
					case "SetText":
						stepresult = new com.ibmhq.SetText().execute(objProp, objVal, stepData);
						break;
					case "SetTextAfterClear":
					     stepresult = new com.ibmhq.SetText().executeclear(objProp, objVal, stepData);
					     break;
					case "SetTextUsingJS":
					     stepresult = new com.ibmhq.SetText().SetTextActions(objProp, objVal, stepData);
					     break;
					case "SetSecuredText":
						stepresult = new com.ibmhq.SetSecuredText().execute(objProp, objVal, stepData);
						break; 
					case "SetPayNumber":
						stepresult = new com.ibmhq.SetText().SetPayNumber(objProp, objVal, stepData);//added by neha
						break;
					case "SetPONumber":
						stepresult = new com.ibmhq.SetText().SetPONumber(objProp, objVal, stepData);//added by neha
						break;
					
					case "SetDelay":
						int delay = Integer.parseInt(stepData);
						stepresult = new com.ibmhq.SetTimeOut().delay(delay);
						break;
					case "UploadFileChangPermission":
					     stepresult = new com.ibmhq.FileCopierOverNetwork().putFile(stepData);
					     break;
					case "DownloadFile":
					     stepresult = new com.ibmhq.DownloadServerFile().getFile(stepData);
					     break;
					case "SetTimeOut":
						int timeout = Integer.parseInt(stepData);
						stepresult = new com.ibmhq.SetTimeOut().execute(timeout);
						break;
					case "SubmitElement":
						stepresult = new com.ibmhq.SubmitElement().execute(objProp, objVal);
						break;
					case "SwitchToFrame":
						String frameName = stepData;
					    int frameIndex=-1;
					    if (frameName.matches("[0-9]+")) {
					    frameIndex = Integer.parseInt(stepData);}
					    stepresult = new com.ibmhq.SwitchToFrame().execute(frameName, frameIndex);
					    break;
					case "SwitchToDefaultContent":
						Browser.Driver.switchTo().defaultContent();
						stepresult = "Passed%% Switched to default content";
						break;
					case "SwitchToDefaultCont":
						stepresult = new com.ibmhq.SwitchToFrame().DefaultCont();
						break;
					case "switchToFrameByxpath":
						stepresult = new com.ibmhq.SwitchToFrame().switchToFrameByxpath(objProp, objVal);
						break;
					case "SwitchToFramesbyName":
						String frameNames = stepData;
						 stepresult = new com.ibmhq.SwitchToFrames().SwitchtoSubFramesbyName(frameNames);
						 break;
					case "SwitchToFramesbyIndex":
						String frameindex = stepData;
						 stepresult = new com.ibmhq.SwitchToFrames().SwitchtoSubFramesbyIndex(frameindex);
						 break;
					
					case "SwitchWindow":
						stepresult = new com.ibmhq.SwitchWindow().execute(stepData);
						break;

					case "ValidateTableCell":
						stepresult = new com.ibmhq.ValidateTableCell().execute(objProp, objVal, stepData);
						break;
					case "ValidateText":
						stepresult = new com.ibmhq.ValidateText().TextEquals(objProp, objVal, stepData);
						break;
					case "VerifyAlertMsg":
					     String[] data = stepData.split("#");
					     int wTime=Integer.parseInt(data[0]);
					     String alertMsg=data[1];
					     stepresult = new com.ibmhq.HandleAlert().verifyAlertMsg(wTime,alertMsg);
					     break;
					case "VerifyCheckbox":
						boolean chbox = Boolean.parseBoolean(stepData);
						stepresult = new com.ibmhq.VerifyCheckbox().execute(objProp, objVal, chbox);
						break;
					case "VerifyElementState":
						stepresult = new com.ibmhq.VerifyElementState().execute(objProp, objVal, stepData);
						break;
					case "VerifyPageTitle":
						stepresult = new com.ibmhq.VerifyPageTitle().execute(stepData);
						break;
					case "VerifySelectedItem":
						String[] veryItem = stepData.split("\\|\\|");
						stepresult = new com.ibmhq.VerifySelectedItem().execute(objProp, objVal, veryItem[0], veryItem[1]);
						break;
					case "VerifyText":
						stepresult = new com.ibmhq.VerifyText().execute(objProp, objVal, stepData);
						break;
					case "VerifyTextJS":
						stepresult = new com.ibmhq.VerifyText().VerifyTextJS(objProp, objVal, stepData);//addded by neha
						break;
				    case "Pop-up":
					    stepresult = new com.ibmhq.MessageBox().PopUp();
					    break;
					case "ValidateAlertMessage":
						stepresult = new com.ibmhq.HandleAlert().ValidateAlertMessage(stepData);
						break;
					case "VerifyURL":
						stepresult = new com.ibmhq.VerifyURL().execute(stepData);
						break;
					case "VerifyAttributeValue":
                        stepresult = new com.ibmhq.VerifyText().VerifyAttributeValue(objProp, objVal, stepData);
                        break;
					case "VerifyAttribute":
						String[] attrInfo = stepData.split("\\|\\|");
						// String[] attrInfo = stepData.split(",");
						stepresult = new com.ibmhq.VerifyAttribute().execute(objProp, objVal, attrInfo[0], attrInfo[1]);//not present in new framework
						break;
					case "GetAttribute":
						String[] attrInfo1 = stepData.split("\\|\\|");
						// String[] attrInfo = stepData.split(",");
						stepresult = new com.ibmhq.VerifyAttribute().GetAttribute(objProp, objVal, attrInfo1[0], attrInfo1[1]);
						break;
						//******Added Dec 27/18 for Execute file upload custom code 
					case "ExecuteUploadFile":
						stepresult = new com.ibmhq.ClickElement().executeuploadFile(stepData);
						System.out.println("stepData:"+stepData);
						break;
						//******Added  03/Jan for Edit the notepad file  custom code 
					case "ExecuteNotepad":
						stepresult = new com.ibmhq.ClickElement().executeuploadFile(stepData);
						System.out.println("stepData:"+stepData);
						break;
						//***   Dec 30 for setting up the Time stamp
					case "SetTextUsingTS":
						stepresult = new com.ibmhq.SetText().settextusingtimestamp(objProp, objVal, stepData);
						break;
						//****Added Jan 07 for Verify Notepad 			
					case "VerifyNotepad":
						stepresult = new com.ibmhq.ClickElement().verifynotepad(stepData);
						break;
						//***Added Jan 14 for unknown error 
						
					case "WaitForElementClick":
						stepresult = new com.ibmhq.WaitForElement().WaitElementClick(objProp, objVal, stepData);
						break;
						
						//Added changes request for Tapas -Jan 14

					case "WaitForValueToBePopulated":
					      stepresult = new com.ibmhq.WaitForElement().WaitForValueToBePopulated(objProp, objVal, stepData);
					     break;						
					case "validateDefinedTableData":
						stepresult = new com.ibmhq.ValidateTableCell().validateDefinedTableData(objProp, objVal, stepData);
						break;
					case "WaitForDuration":
					     int wait = Integer.parseInt(stepData);
					     stepresult = new com.ibmhq.WaitForElement().waitForDuration(wait);
					     break;
					case "WaitForElement":
						int secs = Integer.parseInt(stepData);
						stepresult = new com.ibmhq.WaitForElement().execute(objProp, objVal, secs);
						break;
					case "WaitForFrameToBeAvailableAndSwitchToIt":
						stepresult = new com.ibmhq.WaitForElement().waitForFrameExistenceAndSwitchToIt(objProp, objVal);
						break;
					case "xmlParser":
						stepresult = new com.ibmhq.VerifyText().xmlParser();
					break;	
					case "GetCurrentWindowHandle":
						stepresult = new com.ibmhq.SwitchWindow().getCurrentWindowHandle();
						break;
					case "SwitchWindowHandle":
						stepresult = new com.ibmhq.SwitchWindow().SwitchWindowHandle(stepData);
						break;
					case "WaitForStatus":
						stepresult = new com.ibmhq.WaitForStatus().execute(objProp, objVal, stepData);
						break;
					case "validateXML":
						stepresult = new com.ibmhq.ValidateXML().validator(stepData);
						break;
					case "SwitchToParentFrame":
						Browser.Driver.switchTo().parentFrame();
						stepresult = "Passed%% Switched to Parent Frame";
						break;
					case    "DownloadLetterExtractFileFromServer":
						stepresult = new com.ibmhq.DownloadLetterExtractFileFromServer().getFile(stepData);
							break;
					case "DownloadReconciliationExtractFile":
						stepresult = new com.ibmhq.DownloadReconciliationExtractFile().getFile(stepData);
						break;
					case "DownloadConsessionValidationFile":
						stepresult = new com.ibmhq.DownloadConsessionValidationFile().getFile(stepData);
						break;
					case "DownloadNBVfilesFromServer":
						stepresult = new com.ibmhq.DownloadNBVfilesFromServer().getFile(stepData);
						break;
					case "SwitchWindowContainsTittle":
						stepresult = new com.ibmhq.SwitchWindow().SwitchWithTitleContains(stepData);
						break;
					case "SwitchWithURL":
						stepresult = new com.ibmhq.SwitchWindow().SwitchToURLContains(stepData);
						break;
					case "ScrollToTop":
                        stepresult = new com.ibmhq.SetFocus().scrollToTop();
                        break;
					default:
						stepresult = "Failed%%Invalid option:" + stepAction;
						break;
				}

		} catch (IOException e3) {
			e3.printStackTrace();
		}

		return stepresult+";storedata;"+storedata;
		
	}
	public String findElement(String type, String id)
     {
     String r;
     String res;
     r=new com.ibmhq. Elements().find_if(type,id);
     if (r=="pass")
       res="pass";
      else
      res="fail";
     return res;
     }
	/*
	public String readstepdata(int n,String stepInput,int dinputRow,Sheet objscriptSheet,Sheet objdatainputSheet,Sheet objdataoutputSheet) throws IOException {
		
		String stepData = null;
		try{
			//Read the data for the test step
			String colHeader,datacolumn;
			//Row dRow;
			//DataFormatter formatter = new DataFormatter();
			datacolumn = new com.ibmhq.LoadExcel().readExcel(objscriptSheet, n,"INPUT DATA");
			stepData = new com.ibmhq.LoadExcel().readExcel(objscriptSheet, n,"INPUT DATA");
			if(datacolumn != null){
				if(stepInput == ""){
					stepData = new com.ibmhq.LoadExcel().readExcel(objscriptSheet, n,"INPUT DATA");
				}else if(stepInput.equalsIgnoreCase("input")){
					colHeader = new com.ibmhq.LoadExcel().readExcel(objscriptSheet, n,"INPUT DATA");
					stepData = new com.ibmhq.LoadExcel().readExcel(objdatainputSheet, dinputRow, colHeader);
					if(stepData=="") stepData = null;
				}else if(stepInput.equalsIgnoreCase("output")){
					colHeader = new com.ibmhq.LoadExcel().readExcel(objscriptSheet, n,"INPUT DATA");
					stepData = new com.ibmhq.LoadExcel().readExcel(objdataoutputSheet, dinputRow, colHeader);
					if(stepData=="") stepData = null;
				}else if(stepInput.equalsIgnoreCase("inputfromotherexcel")){
					String sedfileName = new com.ibmhq.LoadExcel().readExcel(objscriptSheet, n,"TESTDATA EXCEL NAME");
					String edsheetName = new com.ibmhq.LoadExcel().readExcel(objscriptSheet, n,"SHEET NAME");
					if(edsheetName!="") {
						Sheet extdataSheet = new com.ibmhq.LoadExcel().loadworksheet(testdatapath, sedfileName, edsheetName);
						colHeader = new com.ibmhq.LoadExcel().readExcel(objscriptSheet, n,"INPUT DATA");
						if (extdataSheet!=null)
							stepData = new com.ibmhq.LoadExcel().readExcel(extdataSheet, tcName, colHeader);
							if(stepData=="") stepData = null;
						else {
							System.out.println("Test Data Sheet is not available with name:"+edsheetName);
							stepData = null;
						}
						extdataSheet.getWorkbook().close();
					}else {
						System.out.println("Test Data Sheet name is blank.");
						stepData = null;
					}
				}
				
			}else{
				stepData = null;
			}
			
		} catch (IOException e3) {
			e3.printStackTrace();
		}
		
		if(stepData==null) stepData = "data missing";
		return stepData;
		
	}
	
public void writestepdata(int n,String stepInput,int dinputRow,Sheet objscriptSheet,String workbookname,Sheet objdataoutputSheet) throws IOException {
		
		try{
			//Read the data for the test step
			DataFormatter formatter = new DataFormatter();
			String datacolumn = new com.ibmhq.LoadExcel().readExcel(objscriptSheet, n,"INPUT DATA");
			
			if(datacolumn != null){
				
				int noOfColumns = 0;
				Row dRow = objdataoutputSheet.getRow(0);
				
				if (dRow != null) {
					noOfColumns = objdataoutputSheet.getRow(0).getPhysicalNumberOfCells();
					
					boolean isexist = false;
					for(int dr=0;dr<=noOfColumns;dr++){
						String dataColHeader = formatter.formatCellValue(dRow.getCell(dr));
						
						if(dataColHeader.equals(datacolumn)){
							isexist = true;
						}
					}
					
					if(isexist == false){
						new com.ibmhq.LoadExcel().writeExcel(workbookname,objdataoutputSheet,0,noOfColumns,datacolumn);
					}
					
				}else {
					new com.ibmhq.LoadExcel().writeExcel(workbookname,objdataoutputSheet,0,0,datacolumn);
				}
				
				dRow = objdataoutputSheet.getRow(0);
				noOfColumns = objdataoutputSheet.getRow(0).getPhysicalNumberOfCells();
				
				for(int p=0;p<=noOfColumns;p++){
					String dataColHeader = formatter.formatCellValue(dRow.getCell(p));
					if(dataColHeader.equals(datacolumn)){
    					new com.ibmhq.LoadExcel().writeExcel(workbookname,objdataoutputSheet,dinputRow+(iteration-1),p,storedata);
    					break;
					}
				}
			}

		} catch (IOException e3) {
			e3.printStackTrace();
		}
					
	}
			
	public void consolidateresult(String scriptTCname,String filepath,Sheet updatesheet,int rownum) throws Exception {
		
		try{
			if(stepresult!=null){
				if(stepresult.contains("%%")){
					String[] strressplit = stepresult.split("%%");
					stepstatus = strressplit[0];
					stepactual = strressplit[1];
				}else {
					stepstatus = "Failed";
					stepactual = stepresult;
				}
		
				String imagefilename = stepNo + "_" + scriptTCname + ".jpg";
				String imagefilepath = sfilepath + "\\" + imagefilename;
				
				String errorfilename = stepNo + "_" + "Failed_" + scriptTCname + ".jpg";
				String errorfilepath = rfilepath + "\\" + errorfilename;
				
				if(defecttool.equalsIgnoreCase("alm")){
		    		stepFactory = run.getStepFactory();
    			}
				
				if(defecttool.equalsIgnoreCase("jira")){
					if(runid.equalsIgnoreCase("ni")){
						String tmpinput = "{\"tests\" : [{\"testKey\" :\""+tcKey+"\", \"comment\" : \"Successful execution\",\"status\" :\""+"TODO"+"\"}]}";
			    		runid = new com.ibmhq.jiramanage().updateRun(tcKey,"TODO",jirauname,jirapwd,jiraurl,tmpinput,jiraproj);
			    		//javax.swing.JOptionPane.showMessageDialog(null,"runid: "+runid);
					}
    			}
				
				if(stepstatus.equalsIgnoreCase("passed")){
					passedsteps = passedsteps +1;
					if(defecttool.equalsIgnoreCase("alm")){
						step = stepFactory.addItem();
			    		step.setStepName(stepNo);
			    		step.setStatus(StatusAs.PASSED);
			    		step.setDescription(stepExpected);
			    		step.setExpected(stepExpected);
			    		step.setActual(stepactual);
			    		step.setStatus(StatusAs.PASSED);
			    		step.post();
					}else if(defecttool.equalsIgnoreCase("jira")){
						if(jsonupdateCount==0){
							stepsStatusforJira=stepsStatusforJira+"{\"status\": \"PASS\",\"comment\": \"Executed\"}";
							jsonupdateCount=jsonupdateCount+1;
							inputJSONforJira = "{\"testExecutionKey\":\""+runid+"\",\"tests\" : [{\"testKey\" :\""+tcKey+"\", \"comment\" : \"Successful execution\",\"status\" :\""+"PASS"+"\","+stepsStatusforJira+wrapUpSteps+wrapUpWhole;
		    				//javax.swing.JOptionPane.showMessageDialog(null,"pass status:"+inputJSONforJira);
		    				String tmp = new com.ibmhq.jiramanage().updateRun(tcKey,"PASS",jirauname,jirapwd,jiraurl,inputJSONforJira,jiraproj);
						}
						else if(jsonupdateCount1>0){
							stepsStatusforJira=stepsStatusforJira+",{\"status\": \"PASS\",\"comment\": \"Executed\"}";
							inputJSONforJira = "{\"testExecutionKey\":\""+runid+"\",\"tests\" : [{\"testKey\" :\""+tcKey+"\", \"comment\" : \"Successful execution\",\"status\" :\""+"PASS"+"\","+stepsStatusforJira+wrapUpSteps+wrapUpWhole;
		    				//javax.swing.JOptionPane.showMessageDialog(null,"Pass status:"+inputJSONforJira);
		    				String tmp = new com.ibmhq.jiramanage().updateRun(tcKey,"PASS",jirauname,jirapwd,jiraurl,inputJSONforJira,jiraproj);
						}
						else{
							jsonupdateCount1=jsonupdateCount1+1;
						}
					//	new com.ibmhq.jiramanage().updateRun(tcKey,"PASS",jirauname,jirapwd,jiraurl,jiraproj);
					//	javax.swing.JOptionPane.showMessageDialog(null,"adding a new pass: "+stepsStatusforJira);
					}
				}else if (stepstatus.equalsIgnoreCase("failed")){
					
					if(defecttool.equalsIgnoreCase("jira"))
					{
						if(jsonupdateCount==0){
							stepsStatusforJira=stepsStatusforJira+"{\"status\": \"FAIL\",\"comment\": \"Executed\"}";
							jsonupdateCount=jsonupdateCount+1;
							inputJSONforJira = "{\"testExecutionKey\":\""+runid+"\",\"tests\" : [{\"testKey\" :\""+tcKey+"\", \"comment\" : \"Successful execution\",\"status\" :\""+"FAIL"+"\","+stepsStatusforJira+wrapUpSteps+wrapUpWhole;
		    				//javax.swing.JOptionPane.showMessageDialog(null,"fail status:"+inputJSONforJira);
		    				String tmp = new com.ibmhq.jiramanage().updateRun(tcKey,"FAIL",jirauname,jirapwd,jiraurl,inputJSONforJira,jiraproj);
						}
						else if(jsonupdateCount1>0){
							stepsStatusforJira=stepsStatusforJira+",{\"status\": \"FAIL\",\"comment\": \"Executed\"}";
							inputJSONforJira = "{\"testExecutionKey\":\""+runid+"\",\"tests\" : [{\"testKey\" :\""+tcKey+"\", \"comment\" : \"Successful execution\",\"status\" :\""+"FAIL"+"\","+stepsStatusforJira+wrapUpSteps+wrapUpWhole;
		    				//javax.swing.JOptionPane.showMessageDialog(null,"Fail status:"+inputJSONforJira);
		    				String tmp = new com.ibmhq.jiramanage().updateRun(tcKey,"FAIL",jirauname,jirapwd,jiraurl,inputJSONforJira,jiraproj);
						}
						else{
							jsonupdateCount1=jsonupdateCount1+1;
						}
						//javax.swing.JOptionPane.showMessageDialog(null,"adding a new fail: "+stepsStatusforJira);
						int i=0;
						System.out.println("New defect for TestCase "+scriptTCname + "Steps failed: "+stepresult.split("\n")[0].toString().split("%%")[1].toString()+ jirauname+ jirapwd + jiraurl + jiraproj);
						defectid= new com.ibmhq.jiramanage().createDefect("New defect for TestCase "+scriptTCname, "Steps failed: "+stepresult.split("\n")[0].toString().split("%%")[1].toString(),jirauname,jirapwd,jiraurl,jiraproj);
						//javax.swing.JOptionPane.showMessageDialog(null,defectid+" "+tcKey);
						//new com.ibmhq.jiramanage().updateRun(tcKey,"FAIL",jirauname,jirapwd,jiraurl,jiraproj);
						//javax.swing.JOptionPane.showMessageDialog(null,i);
						new com.ibmhq.jiramanage().linkJiraDefect(tcKey, defectid,jirauname,jirapwd,jiraurl,jiraproj);
						
					}
					
					failedsteps = failedsteps + 1;
					//Create defect for the failed step
//					File errimage = ((TakesScreenshot)Browser.Driver).getScreenshotAs(OutputType.FILE);
//					FileUtils.copyFile(errimage, new File(errorfilepath));
					BufferedImage image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
				    ImageIO.write(image, "jpg", new File(imagefilepath));
					defectid = new com.ibmhq.CreateDefect().newDefect(sReport, autodefect,imagefilepath,detectedBy,cTime);
					if(defecttool.equalsIgnoreCase("alm")){
						step = stepFactory.addItem();
			    		step.setStepName(stepNo);
			    		step.setStatus(StatusAs.PASSED);
			    		step.setDescription(stepExpected);
			    		step.setExpected(stepExpected);
			    		step.setActual(stepactual);
			    		step.setStatus(StatusAs.FAILED);
			    		step.post();
			    		run.getAttachments().addItem(imagefilepath);
						run.post();
	    			}
				}
				
				//Boolean tkscrnshot = false;
				//Store screenshot for the selected step
				if (stepAction.equalsIgnoreCase("CloseBrowser") || rstepAction.equalsIgnoreCase("CloseBrowser")){
					System.out.println("Browser closed");
				}else {
					if(screenshot.compareToIgnoreCase("yes")>=0 || rscreenshot.compareToIgnoreCase("yes")>=0){
							try{
								BufferedImage image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
							    ImageIO.write(image, "jpg", new File(imagefilepath));
								//if(!isAlertPresent()){
//									TakesScreenshot ts = (TakesScreenshot) Browser.Driver;
//								    File source = ts.getScreenshotAs(OutputType.FILE);
//								    File destination = new File(imagefilepath);
//								    FileUtils.copyFile(source,destination);
								//}
							}catch(Exception e){
								System.out.println("Exception in taking screenshot for selected step in consolidate result "+ e);
							}
					    
						if(defecttool.equalsIgnoreCase("alm")){
				    		run.getAttachments().addItem(imagefilepath);
							run.post();
		    			}
					}
				}
							
				if(excelreport.equalsIgnoreCase("yes")){
					Workbook workbook = updatesheet.getWorkbook();
					FileOutputStream out = new FileOutputStream(new File(filepath));
					
					CreationHelper createHelper = workbook.getCreationHelper();
		            CellStyle hlink_style = workbook.createCellStyle();
		            Font hlink_font = workbook.createFont();
		            hlink_font.setUnderline(Font.U_SINGLE);
		            hlink_font.setColor(IndexedColors.BLUE.getIndex());
		            hlink_style.setFont(hlink_font);					
					
	            	if(updatesheet.getRow(rownum).getCell(statuscol)!=null){
	            		dataset=dataset+1;
	            		statuscol=statuscol+4;testdatausedcol=testdatausedcol+4;actualrescol=actualrescol+4;screenpathcol=screenpathcol+4;
	            	}
	            	
					updatesheet.getRow(0).createCell(statuscol).setCellValue("STATUS "+dataset);
					updatesheet.getRow(0).createCell(testdatausedcol).setCellValue("TEST DATA USED "+dataset);
					updatesheet.getRow(0).createCell(actualrescol).setCellValue("ACTUAL RESULT "+dataset);
					updatesheet.getRow(0).createCell(screenpathcol).setCellValue("SCREENSHOT PATH "+dataset);
					
					updatesheet.getRow(rownum).createCell(statuscol).setCellValue(stepstatus);
					
					CellStyle rsstyle = workbook.createCellStyle();
					if (stepstatus.equalsIgnoreCase("passed")){
						rsstyle.setFillForegroundColor(IndexedColors.BRIGHT_GREEN.index);
						rsstyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
						updatesheet.getRow(rownum).getCell(statuscol).setCellStyle(rsstyle);
			
			    	} else if(stepstatus.equalsIgnoreCase("Failed")) {
			    		rsstyle.setFillForegroundColor(IndexedColors.RED.index);
					    rsstyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
					    updatesheet.getRow(rownum).getCell(statuscol).setCellStyle(rsstyle);
				    }
					
					updatesheet.getRow(rownum).createCell(testdatausedcol).setCellValue(tsdata);
					updatesheet.getRow(rownum).createCell(actualrescol).setCellValue(stepactual);
					if(screenshot.compareToIgnoreCase("yes")>=0){
			            Cell cell = updatesheet.getRow(rownum).createCell(screenpathcol);
			            cell.setCellValue("Screenshot");
			            
			            Hyperlink link = createHelper.createHyperlink(HyperlinkType.FILE);
			            File imgFile = new File(imagefilepath);
			            String relativeURI = imgFile.toURI().toString();
			            link.setAddress(relativeURI);
			            cell.setHyperlink(link);
			            cell.setCellStyle(hlink_style);
						//updatesheet.getRow(rownum).createCell(17).setCellValue(imagefilepath);
					}
					updatesheet.autoSizeColumn(statuscol);
					updatesheet.autoSizeColumn(testdatausedcol);
					updatesheet.autoSizeColumn(actualrescol);
					updatesheet.autoSizeColumn(screenpathcol);
					
					workbook.write(out);
				    out.close();
				}
			}else{
				passedsteps = passedsteps +1;
			}
		} catch (IOException | HeadlessException e3) {
			setupAfterSuite();
			e3.printStackTrace();
		}
	}
	
	
	
	public void createwordreport() throws FileNotFoundException {
		
		try {
			if (wordreport.equalsIgnoreCase("yes")) {
				String TCdocName = tcName + "_Iteration" + iteration + ".docx";
				TCdocpath = rfilepath + "\\" + TCdocName;
				TCdocwrite = new FileOutputStream(new File(TCdocpath));
				TCdoc = new XWPFDocument();
				XWPFParagraph cellpara = TCdoc.createParagraph();
				XWPFRun rundoc = cellpara.createRun();
				
				rundoc.setFontSize(20);
				rundoc.setBold(true);
				rundoc.addBreak();
				rundoc.setText("IBMhq Test Case Execution Report");
				rundoc.addBreak();
				rundoc.setText("Test Case Name: "+ tcName);
				rundoc.addBreak();
				rundoc.setText("Requirement Name: "+ reqName);
				rundoc.addBreak();
				//rundoc.setText("Date of Execution: "+ new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss.SSS").format(new Date()));
				rundoc.setText("Execution Start Time: "+ new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss.SSS").format(sDate));
				rundoc.addBreak();
				rundoc.addBreak();
				rundoc.addBreak();
				docstatusTable = TCdoc.createTable(2, 5);
				docstatusTable.getRow(0).getCell(0).setText("Total Steps");
				docstatusTable.getRow(0).getCell(1).setText("Steps Passed");
				docstatusTable.getRow(0).getCell(2).setText("Steps Failed");
				docstatusTable.getRow(0).getCell(3).setText("Overall Status");
				docstatusTable.getRow(0).getCell(4).setText("Defect ID (If any)");
			}else {}
		} catch (IOException e3) {
			e3.printStackTrace();
		}
	}
	
	public void updatewordreport(String InputSource) throws IOException, InvalidFormatException {
		
		try {
			if (wordreport.equalsIgnoreCase("yes")) {
				
				//**Adding for customise the word report -Jan 11
				 
				 if (screenshot.equalsIgnoreCase("yes")  || rscreenshot.equalsIgnoreCase("yes")|| failedsteps > 0) {
					wordStepCount = wordStepCount + 1;
			 
					XWPFParagraph statuscellpara = TCdoc.createParagraph();
					XWPFRun statusrun = statuscellpara.createRun();
					statusrun.addBreak(BreakType.PAGE);
					
					XWPFTable docTable = TCdoc.createTable();
					XWPFTableRow tableRowOne = docTable.getRow(0);
					XWPFTableRow tableRowTwo = docTable.createRow();
					XWPFTableRow tableRowThree = docTable.createRow();
					XWPFTableRow tableRowFour = docTable.createRow();
					XWPFTableRow tableRowFive = docTable.createRow();
					XWPFTableRow tableRowSix = docTable.createRow();
					
					if(InputSource.equalsIgnoreCase("Main")){
						tableRowOne.getCell(0).setText("Step Number: " + wordStepCount);
						tableRowTwo.getCell(0).setText("Step Description: " + stepName);
						tableRowThree.getCell(0).setText("Expected Result: " + stepExpected);
						tableRowFour.getCell(0).setText("Actual Result: " + stepactual);
						tableRowFive.getCell(0).setText("Status: " + stepstatus);
					}else{
						tableRowOne.getCell(0).setText("Step Number: " + wordStepCount);
						tableRowTwo.getCell(0).setText("Step Description: " + rstepName);
						tableRowThree.getCell(0).setText("Expected Result: " + rstepExpected);
						tableRowFour.getCell(0).setText("Actual Result: " + stepactual);
						tableRowFive.getCell(0).setText("Status: " + stepstatus);
					}
					
					if (stepAction.equalsIgnoreCase("CloseBrowser") || rstepAction.equalsIgnoreCase("CloseBrowser")){
						System.out.println("Browser closed");
					}else {
						try{
							String tempimagepath = testresultpath + "\\temp.jpg";
							BufferedImage image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
						    ImageIO.write(image, "jpg", new File(tempimagepath));
							tableRowSix.getCell(0).addParagraph().createRun().addPicture(new FileInputStream(tempimagepath), Document.PICTURE_TYPE_JPEG,"temp.jpg",Units.toEMU(500), Units.toEMU(250));
							//if(!isAlertPresent()){
//								String tempimagepath = testresultpath + "\\temp.jpeg";
//								TakesScreenshot ts = (TakesScreenshot) Browser.Driver;
//								File source = ts.getScreenshotAs(OutputType.FILE);
//								File destination = new File(tempimagepath);
//								FileUtils.copyFile(source,destination);
//								if(ts != null){
//									tableRowSix.getCell(0).addParagraph().createRun().addPicture(new FileInputStream(tempimagepath), Document.PICTURE_TYPE_JPEG,"temp.jpeg",Units.toEMU(450), Units.toEMU(250));
//								}
						}
						catch(Exception e){
							System.out.println("Exception in taking screenshot in word update "+ e);
						}
					}
					
					stepNo = "";
					stepName = "";
					stepExpected = "";
					stepactual = "";
					stepstatus = "";
					screenshot = "";
					defectcount = defectid + ";";
					rstepNo = ""; 
					rstepName = ""; 
					rstepExpected = ""; 
					rscreenshot = "";
					 }
				 }else {}
		} catch (HeadlessException e3) {
			e3.printStackTrace();
		}
	}
	
	public void closewordreport() throws IOException, ALMServiceException {
		try {
			if (wordreport.equalsIgnoreCase("yes")) {
				docstatusTable.getRow(1).getCell(0).setText(String.valueOf(wordStepCount));
				if (fSteps.equalsIgnoreCase("0")){
					docstatusTable.getRow(1).getCell(1).setText(String.valueOf(wordStepCount));
					docstatusTable.getRow(1).getCell(2).setText("0");
					docstatusTable.getRow(1).getCell(3).setText("Passed");
				}else{
					docstatusTable.getRow(1).getCell(1).setText(String.valueOf(wordStepCount-1));
					docstatusTable.getRow(1).getCell(2).setText("1");
					docstatusTable.getRow(1).getCell(3).setText("Failed");
				}
				docstatusTable.getRow(1).getCell(4).setText(defectcount);
				TCdoc.write(TCdocwrite);
				TCdocwrite.close();
				if(defecttool.equalsIgnoreCase("alm")){
					run.getAttachments().addItem(TCdocpath);
					run.post();
				}
			}else {}
			if (fSteps.equalsIgnoreCase("0")){
				if(defecttool.equalsIgnoreCase("alm")){
					run.setStatus(StatusAs.PASSED);
					
					run.post();
				}
			}else{
				if(defecttool.equalsIgnoreCase("alm")){
					run.setStatus(StatusAs.FAILED);
					run.post();
				}
			}
		} catch (IOException e3) {
			e3.printStackTrace();
		}
	}

	public void updatesummaryreport() throws IOException {
		
		try {
			ExecutionCount = ExecutionCount + 1; 
			Sheet summarysheet = sReport.getSheet("Summary Report");
	    	int rCount = summarysheet.getLastRowNum() + 1;
	    	String executionStatus=null;
	    	Row nrow = summarysheet.createRow(rCount);
	    	XSSFCellStyle sStyle = sReport.createCellStyle();
	    	
		    for(int q=0;q<=14;q++){
		    	Cell cell = nrow.createCell(q);
		    	cell.setCellStyle(new com.ibmhq.LoadExcel().createBorderedStyle(sStyle));
		    }
		    
		    XSSFCellStyle rstyle = sReport.createCellStyle();
	        BorderStyle thin = BorderStyle.THIN;
	        short black = IndexedColors.BLACK.getIndex();
		    rstyle.setBorderRight(thin);
		    rstyle.setRightBorderColor(black);
		    rstyle.setBorderBottom(thin);
		    rstyle.setBottomBorderColor(black);
		    rstyle.setBorderLeft(thin);
		    rstyle.setLeftBorderColor(black);
		    rstyle.setBorderTop(thin);
		    rstyle.setTopBorderColor(black);
		    //Date sDate = new Date();
		    String sDateformat = eeTime.toString();
		    nrow.getCell(0).setCellValue(sDateformat);
		    nrow.getCell(1).setCellValue(sBrowser);
		    nrow.getCell(2).setCellValue(reqName);
		    nrow.getCell(3).setCellValue(tsName);
		    nrow.getCell(4).setCellValue(tcName);
		    
		    if (fSteps.equalsIgnoreCase("0")){
	    		nrow.getCell(5).setCellValue("Passed");
			    rstyle.setFillForegroundColor(IndexedColors.BRIGHT_GREEN.index);
			    rstyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			    nrow.getCell(5).setCellStyle(rstyle);
			    executionStatus="Passed";
	    	} else {
		    	nrow.getCell(5).setCellValue("Failed");
			    rstyle.setFillForegroundColor(IndexedColors.RED.index);
			    rstyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			    nrow.getCell(5).setCellStyle(rstyle);
			    executionStatus="Failed";
			    stepactual = stepresult;
		    }
		    nrow.getCell(6).setCellValue(iteration);
		    nrow.getCell(7).setCellValue(defectcount);
		    nrow.getCell(8).setCellValue(tSteps);
		    nrow.getCell(9).setCellValue(pSteps);
		    nrow.getCell(10).setCellValue(fSteps);
		    long difference = eTime-sTime;
		    String milisec = String.format("%d mins, %d secs",TimeUnit.NANOSECONDS.toMinutes(difference),TimeUnit.NANOSECONDS.toSeconds(difference) - TimeUnit.MINUTES.toSeconds(TimeUnit.NANOSECONDS.toMinutes(difference)));
		    nrow.getCell(11).setCellValue(milisec);
		    nrow.getCell(12).setCellValue(esTime);
		    nrow.getCell(13).setCellValue(eeTime);
		    nrow.getCell(14).setCellValue(TCdocpath);
		    
		    for(int colNum = 0; colNum<nrow.getLastCellNum();colNum++)  summarysheet.autoSizeColumn(colNum);
		    //String spath = strResFolder + "\\"+ "IBMhq Execution Summary Report_" + cTime + ".xlsx";
		    String spath = strResFolder + "\\"+ "IBMhq Execution Summary Report"+ ".xlsx";
		    FileOutputStream out = new FileOutputStream(new File(spath));
		    sReport.write(out);
		    out.close();
		    writeToHTML(sDateformat,iteration,milisec,executionStatus,stepactual);
		    
		    if(frequency != 0)
			{
		    	if(ExecutionCount % frequency == 0)
		    	{
		    		sendReportByMail();
		    	}
			} 
		    
		} catch (IOException | MessagingException e3) {
			e3.printStackTrace();
		}
	}
	public void createHTMLResult()
			throws IOException {
		try {
			if (htmlPath.equalsIgnoreCase("yes")) {
				
				outputHTMLFile = strResFolder + "\\" + "IBMhq_Execution_Summary_Report" + ".html";
				
				File fhtml = new File(outputHTMLFile);
				BufferedWriter html = new BufferedWriter(new FileWriter(fhtml));
				
				// HTML REPORT CODE
				html.write("<html>\n<head>\n<style>\n" + 
						"table, th, td {\n" + 
						"   border: 1px solid black; border-collapse: collapse;\n" +
						
						"}\n" + 
						"</style>\n"
						+ "<body>" + "\t<table border=\"1\" class=\"table\" width=\"100%\" style=\"width:100%\"><tr>" +
		
						"<tr  bgcolor=#98AFC7>" + "<FONT COLOR=\"0000CC\">"
						+ "<th  align=\"center\"  width=\"10%\">EXECUTION DATE</th>"
						+ "<th  align=\"center\"  width=\"10%\">TEST CASE NAME </th>"
						+ "<th  align=\"center\"  width=\"10%\">EXECUTION STATUS </th>"
						+ "<th  align=\"center\"  width=\"10%\">TOTAL STEPS IN TESTCASE </th>"
						+ "<th  align=\"center\"  width=\"10%\">STEPS PASSED </th>"
						+ "<th  align=\"center\"  width=\"10%\">EXECUTION TIME IN SECONDS </th>"
						+ "<th  align=\"center\"  width=\"10%\">EXECUTION START TIME </th>"
						+ "<th  align=\"center\"  width=\"10%\">EXECUTION END TIME </th>"
						+ "<th  align=\"center\"  width=\"10%\">DEFECT SUMMARY </th>"
						+ "</table>");
				html.close();
			}else {}
		} catch (IOException e3) {
			e3.printStackTrace();
		}
	
	}
	
	private static void writeToHTML(String sDateformat,int iteration,String ms,String exeStatus,String stepactual)
	{

		try {
			boolean append = true;

			FileWriter fout1 = new FileWriter(outputHTMLFile, append);
			BufferedWriter fbw1 = new BufferedWriter(fout1);

			String gstatus = null;
			if (stepstatus.equalsIgnoreCase("fail || pass")) {
				gstatus = "<FONT COLOR=\"#006400\"><b>" + stepstatus.toUpperCase() + "</b></FONT>";
			} else {
				gstatus = "<FONT COLOR=\"#B22222\"><b>" + stepstatus.toUpperCase() + "</b></FONT>";
			}

			fbw1.write("<!DOCTYPE html><head><style>\n" + 
					"table, th, td {\n" + 
					" border: 1px solid black; border-collapse: collapse;\n" + 
					"}\n" + 
					"</style>"
					+ "<body>" + "<table border=\"1\" class=\"table\" style=\"width:100%\"><tr>" +
		
				"<td align=\"center\" width=\"10%\">" + sDateformat + "</td>" + 
				"<td align=\"center\" width=\"10%\">" + tcName + "</td>" + 
				"<td align=\"center\" width=\"10%\">" + exeStatus + "</td>" +
				"<td align=\"center\" width=\"10%\">" + tSteps + "</td>" + 
				"<td align=\"center\" width=\"10%\">" + pSteps + "</td>" + 
				"<td align=\"center\" width=\"10%\">" + ms + "</td>" + 
				"<td align=\"center\" width=\"10%\">" + esTime + "</td>" + 
				"<td align=\"center\" width=\"10%\">" + eeTime + "</td>" + 
				"<td align=\"center\" width=\"10%\">" + stepactual + "</td>" +
				"</font></table></body></html>");

			fbw1.close();

			// stepCount++;

		} catch (Exception e) {
			// System.out.println("Error: " + e.getMessage());
		}
	}
	
	
	public static void sendReportByMail(String... to) throws IOException, MessagingException 
	{
		if (mailreport.equalsIgnoreCase("yes")) { 
		String mailto = MailTo;
        String from = mailUserName;

        final String username = mailUserName;            
        final String password = mailPassword;
        String host = "d23hubm6.in.ibm.com";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.from", mailUserName);
        //props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "25");

        // Get the Session object.
        Session session = Session.getInstance(props, new javax.mail.Authenticator()
        {
            protected PasswordAuthentication getPasswordAuthentication()
            {
            	return new PasswordAuthentication(username, password);
            }
        });

        try {

        	Message message2 = new MimeMessage(session);
            // Set From: header field of the header.
            message2.setFrom(new InternetAddress(from));
            // Set To: header field of the header.
            message2.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mailto));
            // Set Subject: header field
            message2.setSubject("Test Email for Automation Execution");
            BufferedReader reader = new BufferedReader(new FileReader(strResFolder + "\\"+"IBMhq_Execution_Summary_Report" + ".html"));
            StringBuilder stringBuilder = new StringBuilder();
            String line = null;
            String ls = System.getProperty("line.separator");
            while ((line = reader.readLine()) != null) {
            	stringBuilder.append(line);
            	stringBuilder.append(ls);
            }
            // delete the last new line separator
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            reader.close();

            String content = stringBuilder.toString();  
            MimeBodyPart textBodyPart = new MimeBodyPart();
            textBodyPart.setContent(content, "text/html");
            // Now set the actual message
            MimeBodyPart attachmentBodyPart= new MimeBodyPart();
            FileDataSource source = new FileDataSource(strResFolder + "\\"+ "IBMhq Execution Summary Report"+ ".xlsx"); 
            attachmentBodyPart.setDataHandler(new DataHandler(source));
            attachmentBodyPart.setFileName(strResFolder + "\\"+ "IBMhq Execution Summary Report"+".xlsx"); 

            Multipart multipart = new MimeMultipart();  
            multipart.addBodyPart(textBodyPart);  
            multipart.addBodyPart(attachmentBodyPart);
            message2.setContent(multipart);

            // Send message
            Transport.send(message2);
            System.out.println("Sent message successfully....");    

        }
        catch (MessagingException e) { throw new RuntimeException(e); }
		}
	}*/
	
	public static void main(String[] args) {
	    hqdriver app = new hqdriver();
	    // app is now the gateway.entry_point
	    py4j.GatewayServer server = new py4j.GatewayServer(app);
	    
	    server.start();
	    //server.shutdown();
	    
	  }
}