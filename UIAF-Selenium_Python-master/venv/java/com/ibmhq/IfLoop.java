package com.ibmhq;

import java.io.IOException;
import org.apache.poi.ss.usermodel.Sheet;
import org.openqa.selenium.WebElement;

public class IfLoop {
	public String execute(String type,String value,String testdata) throws IOException {

      String strstatus = null;
	  try {
		  
		  String[] strsteps = testdata.split("\\|\\|");
		  String TCName = "";
		  hqdriver.ifloopincrement = 0;
		  String curr_dir= System.getProperty("user.dir");

		  String excelpath=curr_dir+"\\Selenium\\TestData";
          String excelname="CurrentExecSheet.xlsx";
          String sheetName="currentSheet";
	      Sheet sheet = new com.ibmhq.LoadExcel().loadworksheet(excelpath, excelname, sheetName);

		  if(strsteps[0].equalsIgnoreCase("Exists")){
		  System.out.println("inside if");
	  		 if(type!=null){
	  		  System.out.println("inside if");
				if((Elements.find(type,value)!=null) && Elements.find(type,value).isDisplayed() ){
				 System.out.println("inside if");
				 System.out.println("current row no in java"+hqdriver.currentrownumber);
				 System.out.println("last:"+sheet.getLastRowNum());
	 			   for(int i=hqdriver.currentrownumber;i<=sheet.getLastRowNum();i++){
	 			    System.out.println("inside for");
	 				  String stepname = new com.ibmhq.LoadExcel().readExcel(sheet, i,"STEP NO");
	 				 TCName = new com.ibmhq.LoadExcel().readExcel(sheet, i, "TESTCASE NAME");



	 				   if(stepname.equalsIgnoreCase(strsteps[1]) && TCName.equalsIgnoreCase(hqdriver.ComporTCName)){
	 				    System.out.println("inside if");
	 					  hqdriver.ifloopincrement=(i-hqdriver.currentrownumber-1);
	 					  strstatus = "Passed%%The object: "+ hqdriver.stepObject +":	" +value+" is present. Ifloopincrement has been assigned value: "+hqdriver.ifloopincrement;
	 					  break;
	 				   }
	 			   }
	 		   }else{
	 		   		  System.out.println("inside else");

	 			  for(int j=hqdriver.currentrownumber;j<=sheet.getLastRowNum();j++){
	 				  String stepname = new com.ibmhq.LoadExcel().readExcel(sheet, j,"STEP NO");
	 				  TCName = new com.ibmhq.LoadExcel().readExcel(sheet, j, "TESTCASE NAME");
	 				  if(stepname.equalsIgnoreCase(strsteps[2]) && TCName.equalsIgnoreCase(hqdriver.ComporTCName)){
	 					  hqdriver.ifloopincrement=(j-hqdriver.currentrownumber-1);
	 					  strstatus = "Passed%%The object " + hqdriver.stepObject +":	" +value+" is not present. Ifloopincrement has been assigned value: "+hqdriver.ifloopincrement;
	 					  break;
	 				  }
	 			   }
	 		   }
	  		 }
		 }else{
			 if(type!=null){
	 		   WebElement element = Elements.find(type,value);
	 		   if(element!=null && element.isDisplayed()){
	 			  String strtext = element.getText();
	 			   if(strsteps[0].equalsIgnoreCase(strtext)){
		 			   for(int i=hqdriver.currentrownumber;i<=sheet.getLastRowNum();i++){
		 				  TCName = new com.ibmhq.LoadExcel().readExcel(sheet, i, "TESTCASE NAME");
		 				  String stepname = new com.ibmhq.LoadExcel().readExcel(sheet, i,"STEP NO");
		 				   if(stepname.equalsIgnoreCase(strsteps[1]) && TCName.equalsIgnoreCase(hqdriver.ComporTCName)){
		 					  hqdriver.ifloopincrement=(i-hqdriver.currentrownumber-1);
		 					  strstatus = "Passed%%The object: "+ hqdriver.stepObject + " is present. Ifloopincrement has been assigned value: "+hqdriver.ifloopincrement;
		 					  break;
		 				   }
		 			   }
		 		   }else{
		 			  for(int j=hqdriver.currentrownumber;j<=sheet.getLastRowNum();j++){
		 				  TCName = new com.ibmhq.LoadExcel().readExcel(sheet, j, "TESTCASE NAME");
		 				  String stepname = new com.ibmhq.LoadExcel().readExcel(sheet, j,"STEP NO");
		 				  if(stepname.equalsIgnoreCase(strsteps[2])){
		 					  hqdriver.ifloopincrement=(j-hqdriver.currentrownumber-1);
		 					  strstatus = "Failed%%The object is not present. Ifloopincrement has been assigned value: "+hqdriver.ifloopincrement;
		 					  break;
		 				  }
		 			   }
		 		   }
			 	}
	  		 }else{
	  			 strstatus=executetemp(testdata);
	  		 }
  		 }
		  
		} catch(Exception e) {
			strstatus  = e.getMessage();
		}
		System.out.println("status:"+strstatus);
	  return strstatus;
  	}

	public String executetemp(String testdata) throws IOException {

		String strstatus = null;
		int iIfCond = 0, iIfTrueSteps = 1, iIfFalseSteps = 2;
		try {
			String[] strsteps = testdata.split("\\|\\|");
			hqdriver.ifloopincrement = 0;
			if (strsteps[iIfCond].contains(hqdriver.TempVariable)) {
				strstatus = ifSteps(strstatus, strsteps, iIfTrueSteps);
				System.out.println("Inside executetemp...." + strstatus);
			} else {
				strstatus = ifSteps(strstatus, strsteps, iIfFalseSteps);
				strstatus = strstatus + " - skipped - " + strsteps[iIfCond];
				System.out.println("Inside executetemp...." + strstatus);
			}
		} catch (Exception e) {
			e.printStackTrace();
			strstatus = "Failed%% Exception" + e.getMessage();
		}
		System.out.println("status:"+strstatus);
		return strstatus;
	}

	private String ifSteps(String strstatus, String[] strsteps, int stepIdx) throws IOException {
		try {
			System.out.println("condition pass");
			String TCName;
			Sheet sheet = hqdriver.scriptsheet;
			for (int i = hqdriver.currentrownumber; i <= sheet.getLastRowNum(); i++) {
				TCName = new com.ibmhq.LoadExcel().readExcel(sheet, i, "TESTCASE NAME");
				String stepname = new com.ibmhq.LoadExcel().readExcel(sheet, i, "STEP NO"); // 2
				if (stepname.equalsIgnoreCase(strsteps[stepIdx]) && TCName.equalsIgnoreCase(hqdriver.ComporTCName)) { // else
					hqdriver.ifloopincrement = (i - hqdriver.currentrownumber - 1);
					strstatus = "Passed%%The given If condition..."
							+ hqdriver.ifloopincrement;
					System.out.println("Inside ifSteps...." + strstatus);
					break;
				}
			} // -for
		} catch (Exception e) {
			strstatus = "Failed%%" + e.getMessage();
			System.out.println("Inside ifSteps...." + strstatus);
		}
		System.out.println(strstatus);
		return strstatus;
	}
}
