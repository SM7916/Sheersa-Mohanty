package com.ibmhq;

import java.io.IOException;

import org.apache.poi.ss.usermodel.Sheet;
import org.openqa.selenium.WebElement;

public class ForLoop {	 public String execute(String type,String value,String testdata) throws IOException {

    String strstatus = null;
	  try {
		  String curr_dir= System.getProperty("user.dir");

		  String excelpath=curr_dir+"\\Selenium\\TestData";
          String excelname="CurrentExecSheet.xlsx";
          String sheetName="currentSheet";
          Sheet sheet = new com.ibmhq.LoadExcel().loadworksheet(excelpath, excelname, sheetName);
		 // hqdriver.loopstart=true;
		  hqdriver.forloopcount = 0;
		  hqdriver.forloopstart = 0;
		  hqdriver.forloopend = 0;
		  String[] strsteps = testdata.split("\\|\\|");
		  hqdriver.forloopcount = Integer.valueOf(strsteps[3])-1;

		  if(strsteps[0].equalsIgnoreCase("Exists") || strsteps[0].equalsIgnoreCase("")){
	  		 if(type!=null){
	  			if(Elements.find(type,value)!=null){
	 			   for(int i=hqdriver.currentrownumber;i<=sheet.getLastRowNum();i++){
	 				  String stepname = new com.ibmhq.LoadExcel().readExcel(sheet, i,"STEP NO");
	 				   if(stepname.equalsIgnoreCase(strsteps[1])){
	 					  hqdriver.forloopstart=(i-hqdriver.currentrownumber-1);
	 					  break;
	 				   }
	 			   }
	 			  for(int j=hqdriver.currentrownumber;j<=sheet.getLastRowNum();j++){
	 				  String stepname = new com.ibmhq.LoadExcel().readExcel(sheet, j,"STEP NO");
	 				   if(stepname.equalsIgnoreCase(strsteps[2])){
	 					  hqdriver.forloopend=(j-hqdriver.currentrownumber-1);
	 					  break;
	 				   }
	 			   }
	 			 strstatus = "Passed%%The Element: " + hqdriver.stepObject + " Exists";
	 		   }else{
	 			  strstatus = "Failed%%The Element: " + hqdriver.stepObject + " does not Exist";
	 		   }
	  		 }
		 }else{
			 if(type!=null){
	 		   WebElement element = Elements.find(type,value);
	 		   if(element!=null){
	 			  String strtext = element.getText();
	 			   if(strsteps[0].equalsIgnoreCase(strtext)){
		 			   for(int i=hqdriver.currentrownumber;i<=sheet.getLastRowNum();i++){
		 				  String stepname = new com.ibmhq.LoadExcel().readExcel(sheet, i,"STEP NO");
		 				   if(stepname.equalsIgnoreCase(strsteps[1])){
		 					  hqdriver.forloopstart=(i-hqdriver.currentrownumber-1);
		 					  break;
		 				   }
		 			   }
		 			   for(int j=hqdriver.currentrownumber;j<=sheet.getLastRowNum();j++){
		 				  String stepname = new com.ibmhq.LoadExcel().readExcel(sheet, j,"STEP NO");
		 				   if(stepname.equalsIgnoreCase(strsteps[2])){
		 					  hqdriver.forloopend=(j-hqdriver.currentrownumber-1);
		 					  break;
		 				   }
		 			   }
		 			  strstatus = "Passed%%The Element: " + hqdriver.stepObject + " value is matched";
		 		   }else{
		 			  strstatus = "Failed%%The Element: " + hqdriver.stepObject + " value is not matched";
		 		   }
			 	}
	  		 }
		 }
		  
		} catch(Exception e) {
			//strstatus  = e.getMessage();
		}
	  return strstatus;
	 
	}	
}
