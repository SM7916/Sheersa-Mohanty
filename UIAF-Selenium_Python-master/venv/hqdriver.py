#------------------------------------------------------------------
#--Script name - Main Driver Class of Uiaf Selenium
#--Author  - Lija Mannathara
#Description - The driver class  do the following
                                    #-execute scripts
                                    #-Updates jira
                                    #-Updates ALM
                                    #-Creates Reports
                                       #1.Extent Report
                                       #2.Word Report for each test case
                                       #3.Excel Report for each test case
                                       #4.PDF Report for each test case
                                       #5.HTML Report
                                       #Summary Excel Report

#------------------------------------------------------------------
import  requests
import errno
import os
from datetime import datetime
import time
import comtypes.client
import ActionClasses.TakeSCreenShot
import  ActionClasses.JiraManage
import  ActionClasses.WriteToParticularCellInExcel
import pandas as pd
import time
# from adodbapi.examples.xls_read import sheet
#from Demos.win32gui_menu import MainWindow
from pandas import ExcelWriter
from docx import Document
from docx.enum.text import WD_ALIGN_PARAGRAPH,WD_BREAK
from docx.shared import Inches,Pt
import numpy as np
import ActionClasses.ALMmanage as alm
from tkinter import Tk,messagebox
from openpyxl import load_workbook
import  subprocess
from py4j.java_gateway import JavaGateway
from ActionClasses.mailer import send_mail
from ActionClasses.htmlTableCreation import  HTML_with_style
from docx2pdf import convert
from ActionClasses.comparevalueifequals import comparevalue
dir_path = os.path.dirname(os.path.realpath(__file__))
import  ActionClasses.portkiller as portk
class hqDriver():
    #initialization
    resultDir=None
    df=None
    df_defect=None
    df_Summary_report=None
    df_report_iter=None
    listScreenShot=[]
    listDetailsScreenShot=[]
    runid='ni'
    jiraTestStepWrite = 0
    gateway = JavaGateway()
    app = gateway.entry_point
    compilation = 'javac -d . -cp ' + os.getcwd() + '/java/com/jar/activation-1.1.jar;' + os.getcwd() + '/java/com/jar/commons-io-2.5.jar;'+ os.getcwd() + '/java/com/jar/cglib-nodep-3.2.10.jar;' + os.getcwd() + '/java/com/jar/com.jcraft.jsch_0.1.31.jar;' + os.getcwd() + '/java/com/jar/commons-codec-1.12.jar;' + os.getcwd() + '/java/com/jar/commons-collections4-4.3.jar;' + os.getcwd() + '/java/com/jar/commons-compress-1.18.jar;' + os.getcwd() + '/java/com/jar/commons-exec-1.3.jar;' + os.getcwd() + '/java/com/jar/commons-logging-1.2.jar;' + os.getcwd() + '/java/com/jar/curvesapi-1.06.jar;' + os.getcwd() + '/java/com/jar/derby-10.14.2.0.jar;' + os.getcwd() + '/java/com/jar/gson-2.8.5.jar;' + os.getcwd() + '/java/com/jar/guava-27.0.1-jre.jar;' + os.getcwd() + '/java/com/jar/hamcrest-core-2.1.jar;' + os.getcwd() + '/java/com/jar/httpclient-4.5.3.jar;' + os.getcwd() + '/java/com/jar/httpcore-4.4.6.jar;' + os.getcwd() + '/java/com/jar/java-json.jar;' + os.getcwd() + '/java/com/jar/javax.mail-1.6.1.jar;' + os.getcwd() + '/java/com/jar/javax.mail-api-1.6.2.jar;' + os.getcwd() + '/java/com/jar/javax.servlet-api-4.0.1.jar;' + os.getcwd() + '/java/com/jar/jcommander-1.72.jar;' + os.getcwd() + '/java/com/jar/jdbc-oracle.jar;' + os.getcwd() + '/java/com/jar/jna-5.2.0.jar;' + os.getcwd() + '/java/com/jar/jna-platform-5.2.0.jar;' + os.getcwd() + '/java/com/jar/jsch-0.1.55.jar;' + os.getcwd() + '/java/com/jar/json_simple-1.1.jar;' + os.getcwd() + '/java/com/jar/junit-4.12.jar;' + os.getcwd() + '/java/com/jar/junit-jupiter-api-5.4.0.jar;' + os.getcwd() + '/java/com/jar/log4j-1.2.17.jar;' + os.getcwd() + '/java/com/jar/phantomjsdriver-1.4.4.jar;' + os.getcwd() + '/java/com/jar/poi-4.0.1.jar;' + os.getcwd() + '/java/com/jar/poi-excelant-4.0.1.jar;' + os.getcwd() + '/java/com/jar/poi-ooxml-4.0.1.jar;' + os.getcwd() + '/java/com/jar/poi-ooxml-schemas-4.0.1.jar;' + os.getcwd() + '/java/com/jar/poi-scratchpad-4.0.1.jar;' + os.getcwd() + '/java/com/jar/selenium-htmlunit-driver-2.52.0.jar;' + os.getcwd() + '/java/com/jar/selenium-java-2.53.0.jar;' + os.getcwd() + '/java/com/jar/selenium-java-3.141.59.jar;' + os.getcwd() + '/java/com/jar/selenium-server-standalone-3.141.59.jar;' + os.getcwd() + '/java/com/jar/stax-api-1.0.1.jar;' + os.getcwd() + '/java/com/jar/testng-6.14.3.jar;' + os.getcwd() + '/java/com/jar/py4j0.10.9.jar;' + os.getcwd() + '/java/com/jar/extentreports-2.40.2.jar;' + os.getcwd() + '/java/com/jar/freemarker-2.3.23.jar;' + os.getcwd() + '/java/com/jar/xmlbeans-3.0.2.jar;' + os.getcwd() + '/java/com/jar/xstream-1.3.1.jar; ' + os.getcwd() + '/java/com/ibmhq/*.java'
    javaRun = 'java -cp ".;' + os.getcwd() + '/java/com/jar/py4j0.10.9.jar";"' + os.getcwd() + '/java/com/jar/jacob.jar";"'+ os.getcwd() + '/java/com/jar/commons-io-2.5.jar";"' + os.getcwd() + '/java/com/jar/json_simple-1.1.jar";"' + os.getcwd() + '/java/com/jar/gson-2.8.5.jar";"' + os.getcwd() + '/java/com/jar/selenium-htmlunit-driver-2.52.0.jar";"' + os.getcwd() + '/java/com/jar/selenium-java-2.53.0.jar";"' + os.getcwd() + '/java/com/jar/selenium-java-3.141.59.jar";"' + os.getcwd() + '/java/com/jar/selenium-server-standalone-3.141.59.jar";"' + os.getcwd() + '/java/com/jar/poi-4.0.1.jar";"' + os.getcwd() + '/java/com/jar/commons-codec-1.12.jar";"' + os.getcwd() + '/java/com/jar/poi-excelant-4.0.1.jar";"' + os.getcwd() + '/java/com/jar/poi-ooxml-4.0.1.jar";"' + os.getcwd() + '/java/com/jar/poi-ooxml-schemas-4.0.1.jar";"' + os.getcwd() + '/java/com/jar/poi-scratchpad-4.0.1.jar";"' + os.getcwd() + '/java/com/jar/java-json.jar";"' + os.getcwd() + '/java/com/jar/extentreports-2.40.2.jar";"' + os.getcwd() + '/java/com/jar/xmlbeans-3.0.2.jar";"' + os.getcwd() + '/java/com/jar/freemarker-2.3.23.jar";"' + os.getcwd() + '/java/com/jar/commons-collections4-4.3.jar";"' + os.getcwd() + '/java/com/jar/commons-compress-1.18.jar";"' + os.getcwd() + '/java/com/jar/commons-logging-1.2.jar";"' + os.getcwd() + "/java/com/jar/commons-exec'-1.3.jar" + '"' + " com.ibmhq.hqdriver"

    #creation of Bat file
    f = open(os.getcwd() +"\\Selenium\\JavaRunnerClass.bat","w")
    f.write(compilation)
    f.write("\n")
    f.write(javaRun)
    f.close()
    javaProcess = subprocess.Popen([os.getcwd() + '\\Selenium\\JavaRunnerClass.bat'])
    host_for_report=None
    upload_to_server=None


    #Execution of scripts
    def execute():
        #portk.port_killer('25333')
        #portk.port_killer('8060')
        time.sleep(20)

        separator = "="
        keys = {}
        with open(os.getcwd() + "\\Selenium\\config.properties") as f:
            for line in f:
                if separator in line:
                    name, value = line.split(separator, 1)
                    keys[name.strip()] = value.strip()
        mailerEnableOption = keys['mailerEnable']
        stopOnFailureOption = keys['stopOnFailure']
        hqDriver.host_for_report = keys['url_for_report']
        hqDriver.upload_to_server=keys['upload_to_server']

        hqDriver.app = hqDriver.gateway.entry_point
        # path until UIAF_UI
        dir_path = os.path.dirname(os.path.realpath(__file__))

        #Kill excel and word
        c = os.system("taskkill /f /im  excel.exe")
        c = os.system("taskkill /f /im  winword.exe")

        stepforjson = []
        almts_name=''
        almts_key=''
        result_count=0
        count_defect=0
        #Result folder Creation
        hqDriver.resultDir = hqDriver.filecreation()

        #Extent Report Initialization
        extentReport=hqDriver.resultDir+"\\Extend_Test_Result.html"
        hqDriver.app.create_extent(extentReport)

        testcaseCount=0

        #Reading Execution.xlsx
        ExecutionFileName=os.getcwd() + '/Selenium/TestData/Execution.xlsx'
        if ExecutionFileName:
            try:
                #Reading defect management info
                df_defect_tool_info = pd.read_excel(ExecutionFileName, skipinitialspace=True,
                                                    sheet_name='defecttoolinfo', usecols=['Parameters', 'Data'])
                df_defect_tool_info.set_index('Parameters', inplace=True)
                df_defect_tool_info = df_defect_tool_info.replace(np.nan, '', regex=True)
                dic = df_defect_tool_info.to_dict()['Data']
                testManagementTool = dic['Test Management Tool']
                autoDefects = dic['Create Defects']
                autoDefects=str(autoDefects)
                fromMail = dic['UserName']
                toMail = dic['MailTo']
                mailSubject = dic['Subject']
                mailFreq = dic['Frequnecy']
                defectDetected=dic['Defect Detected By']
                defectAssigned=dic['Defect Assigned To']
            except Exception as e:
                messagebox.showerror('Error','Incorrect Test Management Sheet')

            mailFreq=int(mailFreq)
            if testManagementTool.lower()=='jira':
                jiraUrl=dic['JiraURL']
                jiraProj=dic['JiraProject']
                jiraUserName=dic['JiraUserName']
                jiraPassword=dic['JiraPassword']
            if testManagementTool.lower()=='alm':
                alm_url=dic['URL']
                alm_username=dic['User ID']
                alm_password=dic['password']
                alm_domain=dic['Domain']
                alm_project=dic['Project']
                alm_testset=dic['Testset path']


            ecolumnNameChoice = 'TO BE EXECUTED'
            ecolumnNameMasterSheet = 'MASTER_SHEET_EXCEL'
            fields=[ecolumnNameChoice,ecolumnNameMasterSheet]
            try:
                #Reading execution file
                df_exec=pd.read_excel(ExecutionFileName,sheet_name="Summary",skipinitialspace=True, usecols=fields,dtype=str)
            except Exception as e:
                messagebox.showerror('Error','Incorrect Execution Excel')
            execflag=0

            #Initializing headers for DataFrames
            headerForResultDF = ['TESTCASE NAME', 'STEP NO', 'TESTSTEP NAME', 'EXPECTED', 'ACTION',
                                 'OBJECTNAME', 'INPUT EXCEL', 'INPUT DATA', 'SCREENSHOT', 'RESULT',
                                 'TEST DATA USED', 'ACTUAL RESULT', 'SCREENSHOT PATH']
            hqDriver.creation_dataframe(headerForResultDF)
            headerForDefectDF = ["DETEDTED ON DATE","DEFECT ID","STATUS","PROGRAM NAME","PROJECT","BROWSER","REQUIREMENT NAME","TESTSET NAME","TESTCASE NAME","STEP NO","STEP DESCRIPTION","SUMMARY","PRIORITY","SEVERITY","DETECTED BY","ASSIGNED TO","SCREENSHOT PATH"]
            hqDriver.df_defect = pd.DataFrame(columns=headerForDefectDF)
            headerForSummaryReport = ['EXECUTION DATE', 'TESTED BROWSER',
                                      'REQUIREMENT NAME', 'TESTSET NAME',
                                      'TEST CASE NAME', 'EXECUTION STATUS', 'ITERATION NO',
                                      'DEFECTS LIST', 'TOTAL NUMBER OF STEPS EXECUTED',
                                      'STEPS PASSED', 'STEPS FAILED', 'EXECUTION TIME IN SECONDS',
                                      'EXECUTION START TIME', 'EXECUTION END TIME', 'REPORT PATH']


            hqDriver.df_Summary_report = pd.DataFrame(columns=headerForSummaryReport)
            hqDriver.df_report_iter = pd.DataFrame(columns=headerForSummaryReport)

            for i in range(0,len(df_exec.index)):

                if str(df_exec.loc[i][ecolumnNameChoice]).lower()=='yes':
                    execflag=1
                    MasterSheetName = os.getcwd() + '/Selenium/TestData/' + df_exec.loc[i][ecolumnNameMasterSheet]
                    #Reading master sheet


                    if MasterSheetName:
                       mcolumnNameChoice = 'EXECUTABLE'
                       mcolumnNameScriptSheet = 'SCRIPT EXCEL NAME'
                       tsNameheader='MODULE NAME'
                       tcNameheader='TESTCASE NAME'
                       tsKeyheader='MODULE KEY'
                       tcKeyheader='TESTCASE KEY'
                       almts_name_key_header='MODULE NAME'
                       mcolumnNameSpecificScriptSheet='TEST CASE SHEET NAME'
                       mTestDataExcel='TESTDATA EXCEL NAME'
                       mInputSheet='INPUT SHEET'
                       mOutputSheet='OUTPUT SHEET'
                       mFromRow='FromInputRowNum'
                       mIteration='ITERATION'
                       mReqname='REQUIREMENT NAME'
                       mBrowser='BROWSER'
                       mPillar = 'PILLAR'
                       mModule = 'MODULE'
                       mSubModule = 'SUB-MODULE'
                       df_master_check = pd.read_excel(MasterSheetName, skipinitialspace=True,
                                                 sheet_name='Master')
                       if mPillar in df_master_check:
                           if mModule in df_master_check:
                               if mSubModule in df_master_check:
                                   mfields = [mPillar, mModule, mSubModule, mcolumnNameChoice, mReqname,
                                              mcolumnNameScriptSheet, tsNameheader, mOutputSheet, mInputSheet,
                                              tcNameheader, tsKeyheader, tcKeyheader, mTestDataExcel,
                                              mcolumnNameSpecificScriptSheet, almts_name_key_header, mFromRow,
                                              mIteration, mBrowser]
                                   headerForSummaryReport = ['PILLAR', "MODULE", "SUB-MODULE", 'EXECUTION DATE',
                                                             'TESTED BROWSER',
                                                             'REQUIREMENT NAME', 'TESTSET NAME',
                                                             'TEST CASE NAME', 'EXECUTION STATUS', 'ITERATION NO',
                                                             'DEFECTS LIST', 'TOTAL NUMBER OF STEPS EXECUTED',
                                                             'STEPS PASSED', 'STEPS FAILED',
                                                             'EXECUTION TIME IN SECONDS',
                                                             'EXECUTION START TIME', 'EXECUTION END TIME',
                                                             'REPORT PATH']

                                   if hqDriver.df_Summary_report.empty:
                                    hqDriver.df_Summary_report = pd.DataFrame(columns=headerForSummaryReport)
                                    hqDriver.df_report_iter = pd.DataFrame(columns=headerForSummaryReport)
                       else:

                            mfields = [mcolumnNameChoice,mReqname, mcolumnNameScriptSheet,tsNameheader,mOutputSheet,mInputSheet,tcNameheader,tsKeyheader,tcKeyheader,mTestDataExcel,mcolumnNameSpecificScriptSheet,almts_name_key_header,mFromRow,mIteration,mBrowser]





                       try:
                           #Reading master file
                           df_master = pd.read_excel(MasterSheetName, skipinitialspace=True, usecols=mfields,
                                                     sheet_name='Master')
                           mrowCount = len(df_master.index)
                       except Exception as e:
                           print(e)
                           #messagebox.showerror('error','Incorrect Master Excel')
                           break



                       dict_ts=dict()
                       for nn in range(0, mrowCount):
                           dict_ts[df_master.loc[nn][tsNameheader]]=0

                       iteracount=0
                       for j in range(0,mrowCount):
                            if str(df_master.loc[j][mcolumnNameChoice]).lower()=='yes':
                                stat = "PASS"
                                failFlag = 0
                                failflagalm = 0
                               #reading details from master file
                                try:
                                    pillar=df_master.loc[j][mPillar]
                                    module=df_master.loc[j][mModule]
                                    subModule=df_master.loc[j][mSubModule]
                                except Exception as e:
                                    pass
                                browser=df_master.loc[j][mBrowser]
                                rowStartforData=int(df_master.loc[j][mFromRow])
                                iteration=df_master.loc[j][mIteration]
                                scriptSheet=df_master.loc[j][mcolumnNameSpecificScriptSheet]
                                tsKeyExists = pd.notnull(df_master.loc[j][tsKeyheader])
                                tsKey = df_master.loc[j][tsKeyheader]
                                tsName= df_master.loc[j][tsNameheader]
                                tsNameExists = pd.notnull(df_master.loc[j][tsNameheader])
                                tcKeyExists = pd.notnull(df_master.loc[j][tcKeyheader])
                                tcKey = df_master.loc[j][tcKeyheader]
                                tcNameExists = pd.notnull(df_master.loc[j][tcNameheader])
                                tcName = df_master.loc[j][tcNameheader]
                                almts_name_key=df_master.loc[j][almts_name_key_header]
                                inputSheet=df_master.loc[j][mInputSheet]
                                outputSheet=df_master.loc[j][mOutputSheet]
                                testDataExcel=os.getcwd() + '/Selenium/TestData/'+df_master.loc[j][mTestDataExcel]
                                try:
                                #reading test data excel
                                    df_testDataInput = pd.read_excel(testDataExcel, skipinitialspace=True,sheet_name=inputSheet,dtype=str)
                                except Exception as e:
                                   messagebox.showerror('Error','Incorrect Input Data sheet')
                                   break
                                try:
                                   #Updating Jira

                                   if testManagementTool.lower() == 'jira':
                                       # update scenario
                                       if str(tsKey).lower() == 'na' or (not tsKeyExists) or str(
                                               tsKey).lower() == 'nan':
                                           if dict_ts[tsName] == 0:
                                               tsKey = ActionClasses.JiraManage.UpdateScenario(tsName, jiraUserName,
                                                                                               jiraPassword, jiraUrl,
                                                                                               jiraProj)
                                               for mm in range(0, mrowCount):
                                                   if df_master.loc[mm][tsNameheader] == tsName:
                                                       ActionClasses.WriteToParticularCellInExcel.write(MasterSheetName,
                                                                                                        'Master',
                                                                                                        mm + 2, 4,
                                                                                                        tsKey)

                                                   dict_ts[df_master.loc[mm][tsNameheader]] = 1
                                       # update test case
                                       if str(tcKey).lower() == 'na' or (not tcKeyExists):
                                           tcKey = ActionClasses.JiraManage.UpdateTestcase(tcName, jiraUserName,
                                                                                           jiraPassword, jiraUrl,
                                                                                           jiraProj)
                                           hqDriver.jiraTestStepWrite = 1
                                           for mm in range(0, mrowCount):
                                               if df_master.loc[mm][tcNameheader] == tcName:
                                                   ActionClasses.WriteToParticularCellInExcel.write(MasterSheetName,
                                                                                                    'Master', mm + 2, 6,
                                                                                                    tcKey)
                                except Exception as e:
                                   messagebox.showerror('Error',"Something wrong with Jira")


                                ScriptSheetName=os.getcwd() + '/Selenium/TestData/'+df_master.loc[j][mcolumnNameScriptSheet]
                                if ScriptSheetName:
                                    sColumnActionHeader='ACTION'
                                    sObjectNameHeader='OBJECTNAME'
                                    sInputData='INPUT DATA'
                                    sScreenShot='SCREENSHOT'
                                    sStepNo='STEP NO'
                                    sTestCaseName='TESTCASE NAME'
                                    sTestStepName='TESTSTEP NAME'
                                    sExpectedResultHeader='EXPECTED'
                                    sInputExcel='INPUT EXCEL'
                                    sDifferentTestDataExcel='TESTDATA EXCEL NAME'
                                    sDifferentTestSheetName='SHEET NAME'


                                    sfields = [sColumnActionHeader,sDifferentTestSheetName,sDifferentTestDataExcel,sObjectNameHeader,sInputData,sScreenShot,sStepNo,sTestCaseName,sTestStepName,sExpectedResultHeader,sInputExcel]
                                    try:
                                    #reading script sheet
                                        df_script = pd.read_excel(ScriptSheetName, skipinitialspace=True, usecols=sfields,sheet_name=scriptSheet,dtype=str)
                                        df_script = df_script.loc[df_script[sTestCaseName] == tcName]
                                    except Exception as e:
                                      messagebox.showerror('Error','Incorrect Script Sheet')
                                      break

                                    try:
                                      #Reusable components
                                      combo = os.getcwd() + '/Selenium/TestData/'+hqDriver.get_data_from_excel_sheet(ExecutionFileName,"Summary","REUSABLE_FUNCTIONS_EXCEL",i)
                                      reusableSheetName=hqDriver.get_data_from_excel_sheet(ExecutionFileName,"Summary","REUSABLE_FUNCTIONS_SHEET",i)
                                      print(combo,reusableSheetName,i)

                                      df_compo=pd.read_excel(combo, skipinitialspace=True,dtype=str)
                                      df_script.reset_index(inplace=True, drop=True)

                                      t = 0
                                      nodataFlag = 1

                                      #creating new dataframe for script which consist of resuable component script
                                      while (t < len(df_script)):


                                          sactionName = df_script.loc[t][sColumnActionHeader]
                                          if ((str(sactionName)).lower() == 'businessscenario' )or (str(sactionName)).lower()=='business scenario':
                                            if df_script.loc[t][sTestCaseName] == tcName:
                                              print("business scenario found")
                                              print(df_script.loc[t][sTestCaseName])
                                              print(tcName)
                                              componentName = str(df_script.loc[t][sInputData])
                                              tName = str(df_script.loc[t][sTestCaseName])
                                              rslt_df = df_compo[df_compo['COMPONENT NAME'] == componentName]
                                              rslt_df.reset_index(inplace=True, drop=True)
                                              rslt_df = rslt_df.drop(['COMPONENT NAME'], axis=1)
                                              rslt_df['TESTCASE NAME'] = tcName
                                              df = hqDriver.merging_df_with_reusable_comp(df_script, rslt_df, t)

                                              df_script = df
                                          t = t + 1
                                      df_script.reset_index(inplace=True)

                                    except Exception as e:
                                    #messagebox.showerror('Error','Incorrect reusable component sheet')
                                        print(e)
                                        break

                                    #Creation of empty dataframe with only column


                                    oObjectNameHeader='OBJECT VARIABLE NAME'
                                    oObjectPropertyHeader='OBJECT_PROPERTY'
                                    oPropertyValHeader='PROPERTY_VALUE'
                                    ofields=[oObjectNameHeader,oObjectPropertyHeader,oPropertyValHeader]
                                   #Reading all objects sheet
                                    try:
                                        print('try1')
                                        allObjectSheetName=hqDriver.get_data_from_excel_sheet(MasterSheetName,"Master","OBJECT REPOSITORY SHEET NAME",j)
                                        df_script_AllObjectsSheet=pd.read_excel(ScriptSheetName, skipinitialspace=True, usecols=ofields,sheet_name=allObjectSheetName,dtype=str)
                                    except Exception as e:
                                      messagebox.showerror('Error','Incorrect Object Sheet')
                                      break
                                    if hqDriver.jiraTestStepWrite==1:
                                      hqDriver.jiraTestStepWrite=0
                                      for step in range(0,len(df_script)):
                                        if df_script.loc[step][sTestCaseName] == tcName:

                                            inputexcel = df_script.loc[step][sInputExcel]

                                            if int(iteration) == 0:
                                                inputIndex = 0

                                            else:
                                                inputIndex = 0
                                            if str(inputexcel).lower() == 'input':
                                                stepdataName = df_script.loc[step][sInputData]

                                                stepData = str(df_testDataInput.loc[inputIndex][stepdataName])
                                            elif str(inputexcel).lower() == 'output':
                                                stepdataName = df_script.loc[step][sInputData]
                                                stepData = hqDriver.get_data_from_excel_sheet(testDataExcel,
                                                                                              outputSheet, stepdataName,
                                                                                              inputIndex)

                                            else:
                                                stepdataName = str(df_script.loc[step][sInputData])
                                                stepData = str(df_script.loc[step][sInputData])
                                            expectedResult = df_script.loc[step][sExpectedResultHeader]
                                            testStepName = df_script.loc[step][sTestStepName]
                                            ActionClasses.JiraManage.updateTestStep(jiraUserName, jiraPassword, jiraUrl,
                                                                                testStepName, stepData, expectedResult,
                                                                                tcKey)
                                #to execute all steps in script
                                failedtestSteps = ''
                                stepnolist=''

                                for itera in range(int(iteration)):
                                    hqDriver.app.add_test_case_extent(tcName+'_'+str(itera))
                                    hqDriver.app.setTestCaseName(tcName)


                                    testcaseCount=testcaseCount+1
                                    hqDriver.listScreenShot=[]
                                    hqDriver.listDetailsScreenShot=[]
                                    start_time1=datetime.now().strftime("%Y-%m-%d %H-%M-%S")
                                    start_time=time.time()
                                    iterStatus='pass'
                                    if testManagementTool.lower() == 'jira':
                                          inputJsonBeforeExec = {"tests": [
                                              {"testKey": tcKey, "comment": "to be executed", "status": "TODO"}]}
                                          hqDriver.runid = ActionClasses.JiraManage.updateRun(jiraUserName,
                                                                                              jiraPassword, jiraUrl,
                                                                                              inputJsonBeforeExec)

                                    if testManagementTool.lower() == 'alm':
                                        almts_name = almts_name_key.split(',')[0]
                                        almts_key = almts_name_key.split(',')[1]
                                        responsebeforerun = alm.create_testrun(alm_url, alm_username, alm_password,
                                                                               alm_domain, alm_project, almts_key,
                                                                               tcName)

                                    no_of_steps=0
                                    no_of_failed_steps=0
                                    # Reading script sheet
                                    result_list = []
                                    end = None
                                    ifelseflag = 0
                                    skip = 0
                                    actionName1 = None
                                    k1 = None
                                    k = 0

                                    while k < len(df_script):
                                     if df_script.loc[k][sTestCaseName]==tcName:
                                      if k1!=None:
                                          k=k1
                                          k1=None
                                      try:

                                          if end+1==k:
                                              k=k+skip+1


                                      except Exception as e:
                                          pass

                                      print("k value", k + 1)


                                      if actionName1 == 'IfElseLoop':
                                            print('I am inside ifesle')
                                            ifelseflag = 1
                                            for l in range(0, len(df_script_AllObjectsSheet.index)):
                                              if objname1 == df_script_AllObjectsSheet.loc[l][oObjectNameHeader]:
                                                  objprop = df_script_AllObjectsSheet.loc[l][oObjectPropertyHeader]
                                                  objval = df_script_AllObjectsSheet.loc[l][oPropertyValHeader]
                                                  break
                                            r = hqDriver.app.findElement(objprop, objval)
                                            stepData= str(df_script.loc[k][sInputData])
                                            list = stepData.split('||')
                                            stepif = list[1].split('-')
                                            stepif1 = stepif[0]
                                            stepif2 = stepif[1]
                                            stepelse = list[2].split('-')
                                            stepelse1 = stepelse[0]
                                            stepelse2 = stepelse[1]
                                            ifrowend = (df_script.index[df_script['STEP NO'] == stepif2].tolist())[0]
                                            ifrowstart = (df_script.index[df_script['STEP NO'] == stepif1].tolist())[0]
                                            elserowstart = (df_script.index[df_script['STEP NO'] == stepelse1].tolist())[0]
                                            elserowend = (df_script.index[df_script['STEP NO'] == stepelse2].tolist())[0]
                                            if r == 'pass':
                                              try:
                                                  print('if')

                                                  k1 = ifrowstart
                                                  end = ifrowend
                                                  skip = elserowend - elserowstart
                                              except Exception as e:
                                                  actualResult = str(e)
                                            else:
                                              print('else', elserowstart)

                                              k1 = elserowstart
                                      no_of_steps=no_of_steps+1
                                      if k >= len(df_script):
                                          break
                                      inputexcel=df_script.loc[k][sInputExcel]
                                      expectedResult = df_script.loc[k][sExpectedResultHeader]
                                      testStepName = df_script.loc[k][sTestStepName]
                                      actionName=df_script.loc[k][sColumnActionHeader]
                                      try:
                                        actionName1=df_script.loc[k+1][sColumnActionHeader]
                                        objname1 = df_script.loc[k + 1][sObjectNameHeader]
                                      except Exception as e:
                                          pass
                                      objnameexists=pd.notnull(df_script.loc[k][sObjectNameHeader])
                                      objname=df_script.loc[k][sObjectNameHeader]
                                      diffExcel=df_script.loc[k][sDifferentTestDataExcel]
                                      diffExcelSheet=df_script.loc[k][sDifferentTestSheetName]
                                      if int(rowStartforData) ==1:
                                        if int(iteration) == 0:
                                            inputIndex = 0
                                        if itera >= 1:
                                            if len(df_testDataInput)==1:
                                                inputIndex=0
                                            else:

                                                inputIndex = itera
                                        else:
                                            inputIndex = 0
                                      elif int(rowStartforData)>1:
                                          if int(iteration) == 0:
                                              inputIndex = int(rowStartforData)-1
                                          if itera >= 1:
                                              inputIndex = int(rowStartforData)+itera-1
                                          else:
                                              inputIndex = int(rowStartforData)-1
                                      if str(diffExcel).lower()!='nan' and diffExcel:
                                        try:
                                          if str(diffExcelSheet).lower()!='nan'  and diffExcelSheet:
                                              try:

                                                df_diffexcel = pd.read_excel(diffExcel, skipinitialspace=True,
                                                                           sheet_name=diffExcelSheet,
                                                                           dtype=str)
                                              except Exception as e:
                                                  messagebox.showerror('Error','Incorrect Data sheet')
                                                  break

                                              stepdataName = df_script.loc[k][sInputData]


                                              stepData = str(df_diffexcel.loc[inputIndex][stepdataName])
                                        except Exception as e:
                                            nodataFlag = 0
                                            stepData = 'No Data Available'

                                            print(e)
                                            result = 'fail'
                                            actualResult = str(e)

                                      else:
                                          try:
                                            if str(inputexcel).lower() == 'input':
                                                stepdataName = df_script.loc[k][sInputData]

                                                stepData = str(df_testDataInput.loc[inputIndex][stepdataName])
                                            elif str(inputexcel).lower() == 'output':
                                                stepdataName = df_script.loc[k][sInputData]
                                                stepData = hqDriver.get_data_from_excel_sheet(testDataExcel, outputSheet,
                                                                                            stepdataName, inputIndex)

                                            else:
                                                    stepdataName = str(df_script.loc[k][sInputData])
                                                    stepData = str(df_script.loc[k][sInputData])
                                          except Exception as e:
                                              nodataFlag=0
                                              stepData='No Data Available'
                                              result = 'fail'
                                              actualResult = str(e)


                                      screenShot=df_script.loc[k][sScreenShot]
                                      stepno=df_script.loc[k][sStepNo]
                                      testCaseName=df_script.loc[k][sTestCaseName]
                                      screenShotName = testCaseName+"_"+stepno+"_" + str(actionName )+ '_Iteration_'+str(itera+1)+".png"
                                      #if there is no object name
                                      if not objnameexists:
                                        #update test step and update test run-todo
                                       if ifelseflag == 0:
                                            try:
                                                result,actualResult=hqDriver.selectAction(actionName,stepData,None,None,None,stepdataName,testDataExcel,outputSheet,browser,k,itera)
                                                if nodataFlag==0:
                                                    stepData = 'No Data Available'
                                                    result = 'fail'

                                                    nodataFlag=1

                                            except Exception as e:
                                                result='fail'
                                                actualResult=str(e)
                                       else:
                                           if r=='pass':
                                            result='pass'
                                            actualResult='Object found'
                                           elif r=='fail':
                                               result='pass'
                                               actualResult='Object not found'
                                           else :
                                               result='fail'
                                           ifelseflag=0
                                           result_list.append(result)
                                       if result.lower() == 'fail':
                                            screenShotName = stepno + "_Failed_" + testCaseName +'_Iteration_'+ str(itera+1)+".png"
                                            s = hqDriver.resultDir + "\\Reports\\"+ screenShotName
                                            hqDriver.app.TakeScreenshot(s)
                                            if not (str(screenShot).lower() == "yes"):
                                                hqDriver.listScreenShot.append(s)
                                                string1 = stepno + '#' + testStepName + '#' + expectedResult + '#' + actualResult + '#' + result
                                                hqDriver.listDetailsScreenShot.append(string1)



                                       else:
                                            s = ""

                                       hqDriver.app.log_step(testStepName, actualResult, result, s)

                                       if str(result).lower()=='fail':
                                            iterStatus='fail'
                                            no_of_failed_steps=no_of_failed_steps+1
                                            failflagalm = 1
                                            stepnolist=stepnolist+'\n'+stepno
                                            failedtestSteps = failedtestSteps + '\n' + testStepName

                                       if str(testManagementTool).lower()=="jira":
                                        if result=="pass":
                                          if stat is not "FAIL":
                                            stat="PASS"
                                          stepforjson.append({"status": "PASS","comment": "Executed"})

                                        else:
                                            failFlag = 1
                                            stat="FAIL"
                                            stepforjson.append(
                                                {"status": "FAIL", "comment": "Executed"})

                                            #failedtestSteps=failedtestSteps+'\n'+testStepName

                                        inputJsonAfterExec = {"testExecutionKey": hqDriver.runid,
                                                              "tests":
                                                                  [{"testKey": tcKey,
                                                                    "comment": "Successful execution",
                                                                    "status": stat,
                                                                    "steps": stepforjson
                                                                    }]
                                                              }

                                        hqDriver.runid=ActionClasses.JiraManage.updateRun(jiraUserName,jiraPassword,jiraUrl,inputJsonAfterExec)

                                        #ScreenShot Generation
                                       if str(screenShot).lower()=='yes':
                                            s = hqDriver.resultDir + "\\Screenshots\\" + screenShotName
                                            hqDriver.app.TakeScreenshot(s)
                                            hqDriver.listScreenShot.append(s)
                                            string1=stepno+'#'+testStepName+'#'+expectedResult+'#'+actualResult+'#'+result
                                            hqDriver.listDetailsScreenShot.append(string1)
                                       else:
                                           s=''
                                        #Writing step result to dataframe
                                       result_count=result_count+1
                                       if result.lower() == 'pass':
                                            resulttodf = 'Passed'
                                       else:
                                            resulttodf = 'Failed'
                                       hqDriver.writing_data_to_dataframe(result_count,testCaseName,stepno,testStepName,expectedResult,actionName,None,inputexcel,stepdataName,screenShot,resulttodf,stepData,actualResult,s)
                                       if stopOnFailureOption.lower() == 'yes':
                                           if str(result).lower() == 'fail':
                                                break

                                      #if there is object name
                                      else:
                                            #Fetching details of object
                                            for l in range(0,len(df_script_AllObjectsSheet.index)):
                                                if objname==df_script_AllObjectsSheet.loc[l][oObjectNameHeader]:
                                                    objprop=df_script_AllObjectsSheet.loc[l][oObjectPropertyHeader]
                                                    objval=df_script_AllObjectsSheet.loc[l][oPropertyValHeader]
                                                    break


                                            if ifelseflag == 0:
                                                        try:
                                                            result, actualResult = hqDriver.selectAction(actionName,
                                                                                                     stepData, objname,
                                                                                                     objprop, objval,
                                                                                                     stepdataName,
                                                                                                     testDataExcel,
                                                                                                     outputSheet,
                                                                                                     browser, k,itera)
                                                            if nodataFlag == 0:
                                                                stepData = 'No Data Available'
                                                                result = 'fail'

                                                                nodataFlag = 1
                                                        except Exception as e:
                                                            result='fail'
                                                            actualResult=str(e)

                                            else:
                                                        if r == 'pass':
                                                            result = 'pass'
                                                            actualResult = 'Object found'
                                                        elif r == 'fail':
                                                            result = 'pass'
                                                            actualResult = 'Object not found'
                                                        else:
                                                            result = 'fail'
                                                        ifelseflag = 0
                                                        result_list.append(result)
                                            if result.lower()=='fail':

                                                        screenShotName = stepno + "_Failed_" + testCaseName +'_Iteration_'+str(itera+1)+".png"
                                                        s = hqDriver.resultDir + "\\Reports\\" + screenShotName
                                                        hqDriver.app.TakeScreenshot(s)
                                                        if not (str(screenShot).lower()=="yes"):
                                                            hqDriver.listScreenShot.append(s)
                                                            string1 = stepno + '#' + testStepName + '#' + expectedResult + '#' + actualResult + '#' + result
                                                            hqDriver.listDetailsScreenShot.append(string1)

                                            else:
                                                        s=""

                                            hqDriver.app.log_step(testStepName, actualResult, result,s)

                                            if str(result).lower() == 'fail':
                                                        iterStatus = 'fail'
                                                        no_of_failed_steps=no_of_failed_steps+1
                                                        failflagalm = 1
                                                        stepnolist = stepnolist + '\n' + stepno
                                                        failedtestSteps= failedtestSteps+ '\n' + testStepName


                                            if testManagementTool.lower() == "jira":
                                                        if result == "pass":
                                                         if stat is not "FAIL":
                                                            stat = "PASS"
                                                            stepforjson.append(
                                                                {"status": "PASS", "comment": "Executed"})

                                                        else:
                                                            failFlag = 1
                                                            stat = "FAIL"
                                                            stepforjson.append(
                                                                {"status": "FAIL", "comment": "Executed"})

                                                           # failedtestSteps = failedtestSteps + '\n' + testStepName

                                                        inputJsonAfterExec = {"testExecutionKey": hqDriver.runid,
                                                                              "tests":
                                                                                  [{"testKey": tcKey,
                                                                                    "comment": "Successful execution",
                                                                                    "status": stat,
                                                                                    "steps": stepforjson
                                                                                    }]
                                                                              }
                                                        hqDriver.runid = ActionClasses.JiraManage.updateRun(
                                                            jiraUserName, jiraPassword, jiraUrl, inputJsonAfterExec)

                                      #ScreenShot Generation

                                            if str(screenShot).lower() == 'yes':

                                                        s = hqDriver.resultDir + "\\Screenshots\\" + screenShotName
                                                        hqDriver.app.TakeScreenshot(s)
                                                        hqDriver.listScreenShot.append(s)
                                                        string1 = stepno + '#' + testStepName + '#' + expectedResult + '#' + actualResult+'#'+result
                                                        hqDriver.listDetailsScreenShot.append(string1)
                                            else:
                                                        s=''

                                            # Writing step result to dataframe
                                            result_count=result_count+1
                                            if result.lower()=='pass':
                                                resulttodf='Passed'
                                            else:
                                                resulttodf='Failed'


                                            hqDriver.writing_data_to_dataframe(result_count,testCaseName, stepno, testStepName,expectedResult,actionName, objname,inputexcel,stepdataName,screenShot,resulttodf,stepData,actualResult,s)


                                            if stopOnFailureOption.lower()=='yes':
                                                if str(result).lower()=='fail':
                                                    break
                                      if isinstance(hqDriver.app.ifloopincrement,int):
                                         k=k+int(hqDriver.app.ifloopincrement)
                                         hqDriver.app.ifloopincrement=0
                                      if isinstance(hqDriver.app.row,int):
                                          k=hqDriver.app.row
                                      k = k + 1
                                    if testManagementTool.lower()=='alm':
                                      if responsebeforerun!=0:

                                        alm.createrunafter(alm_url, alm_username, alm_password, alm_domain, alm_project,
                                                       responsebeforerun,failflagalm,result_list)

                                    execDate = datetime.now().strftime("%d-%m-%Y")
                                    reqName = df_master.loc[j][mReqname]
                                    if not browser:
                                      browser='na'
                                    testsetName=tsName
                                    testcaseName=tcName
                                    if iterStatus=='pass':
                                        execStatus='Passed'
                                    elif iterStatus=='fail':
                                        execStatus='Failed'
                                    iterNo=itera
                                    if autoDefects.lower()=='true':
                                        if testManagementTool.lower()=='alm':
                                            if failflagalm==1:
                                                defct=alm.create_defect(alm_url, alm_username, alm_password, alm_domain, alm_project,testCaseName,failedtestSteps)
                                                count_defect = count_defect + 1
                                                #hqDriver.df_defect.loc[count_defect] = [int(defct), testCaseName]
                                                defectData = [str(execDate), defct, 'NEW', 'IBMhq Automation',
                                                              'Flight Reservation', browser, reqName, testsetName, testcaseName, stepnolist,
                                                              failedtestSteps, actualResult, '4-Medium', '2-High',defectDetected,defectAssigned, s]
                                                dfLength = len(hqDriver.df_defect)
                                                hqDriver.df_defect.loc[dfLength] = defectData

                                        if testManagementTool.lower()=='jira':
                                            if failFlag==1:
                                                defct=ActionClasses.JiraManage.CreateDefect(jiraUserName,jiraPassword,jiraUrl,jiraProj,testCaseName,failedtestSteps)
                                                count_defect=count_defect+1
                                                #hqDriver.df_defect.loc[count_defect]=[defct,testCaseName]
                                                defectData = [str(execDate), defct, 'NEW', 'IBMhq Automation',
                                                              'Flight Reservation', browser, reqName, testsetName, testcaseName,stepnolist,
                                                              failedtestSteps, actualResult, '4-Medium', '2-High',defectDetected,defectAssigned, s]
                                                dfLength = len(hqDriver.df_defect)
                                                hqDriver.df_defect.loc[dfLength] = defectData
                                                ActionClasses.JiraManage.linkJiraDefect(jiraUserName,jiraPassword,jiraUrl,defct,tcKey)
                                    hqDriver.app.flush_extent()
                                    end_time1=datetime.now().strftime("%Y-%m-%d %H:%M:%S")
                                    end_time=time.time()



                                    defect='NA'
                                    if iterStatus=='fail':
                                        if str(autoDefects).lower() == 'true':
                                            if testManagementTool.lower() == 'alm':
                                                if failflagalm == 1:
                                                    defect = int(defct)
                                            elif testManagementTool.lower() == 'jira':
                                                if failFlag == 1:
                                                    defect = defct
                                            else:
                                                #defect = count_defect
                                                count_defect = count_defect + 1
                                                defectData = [str(execDate), count_defect, 'NEW', 'IBMhq Automation',
                                                              'Flight Reservation', browser, reqName, testsetName, testcaseName, stepnolist,
                                                              failedtestSteps, actualResult, '4-Medium', '2-High',defectDetected,defectAssigned, s]
                                                dfLength = len(hqDriver.df_defect)
                                                hqDriver.df_defect.loc[dfLength] = defectData
                                                defect = count_defect



                                    passed_steps=no_of_steps-no_of_failed_steps
                                    execTime=end_time-start_time
                                    execTime=time.strftime("%M mins, %S secs", time.gmtime(execTime))

                                    data=[str(execDate),browser,reqName,testsetName,testcaseName,execStatus[0:4],str(iterNo+1),str(defect),str(no_of_steps),str(passed_steps),str(no_of_failed_steps),execTime,start_time1,end_time1,hqDriver.resultDir + '\\' + 'Testresult'+str(iterNo)+'.docx']
                                    try:
                                        data = [pillar,module,subModule,str(execDate), browser, reqName, testsetName, testcaseName,
                                                execStatus[0:4], str(iterNo + 1), str(defect), str(no_of_steps),
                                                str(passed_steps), str(no_of_failed_steps), execTime, start_time1,
                                                end_time1,
                                                hqDriver.resultDir + '\\' + 'Testresult' + str(iterNo) + '.docx']
                                    except Exception as e:
                                        pass

                                    sr_length=len(hqDriver.df_Summary_report)
                                    hqDriver.df_Summary_report.loc[sr_length] = data
                                    print("####3summary report####")
                                    print(hqDriver.df_Summary_report)
                                    hqDriver.df_report_iter.loc[0]=data
                                    iteracount = iteracount + 1
                                    reportpath=hqDriver.create_Word_Doc_Result(hqDriver.listScreenShot,hqDriver.listDetailsScreenShot,testcaseName+'_Iteration'+str(int(iterNo)+1),testcaseName,reqName,start_time1,hqDriver.df_report_iter)
                                    excelFile=hqDriver.excel_test_result_creation(testcaseName,scriptSheet)
                                    if mailerEnableOption.lower()=='yes':
                                      if mailFreq!=0:
                                        if int(testcaseCount)%mailFreq==0:

                                            htmlText = HTML_with_style(hqDriver.df_Summary_report)
                                            f = open(hqDriver.resultDir + "\\ IBMhq Execution Summary Report.html", 'w')

                                            message = htmlText

                                            f.write(message)
                                            f.close()
                                            send_mail(send_from=fromMail, send_to=toMail, subject=mailSubject,
                                                      text="Hi,\n PFB  report",
                                                      files=excelFile,
                                                      server='d23hubm6.in.ibm.com', html=htmlText, username='',
                                                      password='', isTls=True)

                                  # Creation of word doc reports



                    # Creation of excel report
                    summaryReport=hqDriver.excel_summary_report_creation()
                    print(hqDriver.df_Summary_report)

                    htmlText=HTML_with_style(hqDriver.df_Summary_report)
                    f = open(hqDriver.resultDir+"\\ IBMhq Execution Summary Report.html", 'w')

                    message = htmlText

                    f.write(message)
                    f.close()
                    if mailerEnableOption.lower() == 'yes':
                        send_mail(send_from=fromMail,send_to=toMail,subject=mailSubject,text="Hi,\n PFB Report",files1=summaryReport,files2=extentReport,server='d23hubm6.in.ibm.com',html=htmlText,username='',password='',isTls=True)


            if execflag==0:
                Tk().withdraw()
                messagebox.showinfo("Error", "Nothing to execute")

   #Method for calling Java Action Functions
    def selectAction(stepaction,stepdata,objname,objprop,objval,OutputHeader,testDataExcel,sheet,browser,k,itera):
        print(stepaction,stepdata,objname,objprop,objval,OutputHeader,testDataExcel,sheet,browser,k,itera)
        if str(stepdata)=='nan':
                stepdata=""
        try:
            if str(stepaction).lower() == 'comparevalueifequals':
                value = ActionClasses.comparevalueifequals.comparevalue.execute(stepdata, testDataExcel)
            else:

                value = hqDriver.app.selectaction(stepaction, stepdata, objname, objprop, objval,browser,k)
            result = value[0:4]
            result = result.lower()
            word = value.split(sep=';storedata;')
            wordWithPer = word[0]
            word1 = wordWithPer.split(sep='%%')
            if str(result).lower() != "null":
                actResult = word1[1]
            else:
                actResult='unknown'
            try:
                df = pd.read_excel(testDataExcel, skipinitialspace=True, sheet_name=sheet, dtype=str)
            except Exception as e:
                messagebox.showerror('Error','Incorrect Data Sheet')
            if len(word)==2:

              if str(word[1]):
                if OutputHeader not in df.columns:
                    l = len(df.columns)

                    ActionClasses.WriteToParticularCellInExcel.write(testDataExcel, sheet, 1, l + 1, OutputHeader)
                    ActionClasses.WriteToParticularCellInExcel.write(testDataExcel, sheet, itera+2, l + 1, word[1])
                else:
                    ActionClasses.WriteToParticularCellInExcel.write(testDataExcel, sheet, itera+2,
                                                                 df.columns.get_loc(OutputHeader) + 1, word[1])

        except Exception as e:

            result="unknown"

            actResult=str(e)
        return result, actResult


    #Creation of result folder
    def filecreation():
        mydir = os.getcwd()+"\\Selenium\\TestResults\\"+"Result_"+datetime.now().strftime('%Y-%m-%d_%H-%M-%S')
        try:
            os.makedirs(mydir)
            os.makedirs(mydir+"\\Reports")
            os.makedirs(mydir +"\\Screenshots")
        except OSError as e:
            if e.errno != errno.EEXIST:
                raise  # This was not a "directory exist" error..
        return mydir

    #Creation of Execution report
    def excel_test_result_creation(testcaseName,sheetname):
        excelname=hqDriver.resultDir+"\\Reports\\"+testcaseName+"_"+str(datetime.today().strftime('%Y-%m-%d-%H-%M-%S'))+".xlsx"
        hqDriver.df=hqDriver.df.replace(np.nan,' ',regex=True)
        hqDriver.df=hqDriver.df.replace('(?i)nan','',regex=True)
        hqDriver.df = hqDriver.df.replace('(?i)none', '', regex=True)

        writer = ExcelWriter(excelname)
        hqDriver.df.to_excel(writer,sheetname, index=False)
        #hqDriver.df_defect.to_excel(writer,"defect",index=False)
        workbook = writer.book
        worksheet = writer.sheets[sheetname]
        # Add a format for fail. Light red fill with dark red text.
        fail_format = workbook.add_format({'bg_color': '#FFC7CE',
                                           'font_color': '#9C0006'})
        # Add a format for pass. Green fill with dark green text.
        pass_format = workbook.add_format({'bg_color': '#C6EFCE',
                                           'font_color': '#006100'})
        link_format=workbook.add_format({'font_color': '#0000FF',
                                         'underline':  1})
        # Apply conditional formats to the cell range.
        worksheet.conditional_format('J1:J1048576', {'type': 'text',
                                               'criteria': 'containing',
                                               'value': 'Failed',
                                               'format': fail_format})
        worksheet.conditional_format('J1:J1048576', {'type': 'text',
                                               'criteria': 'containing',
                                               'value': 'Passed','format':   pass_format})
        worksheet.conditional_format('M1:M1048576', {'type': 'text',
                                                     'criteria': 'containing',
                                                     'value': 'Screenshots', 'format': link_format})
        worksheet=hqDriver.adjusting_col_width(hqDriver.df,worksheet)
        writer.save()
        return excelname

    # Creation of Summary report
    def excel_summary_report_creation():
        summaryReport=hqDriver.resultDir + "\\ IBMhq Execution Summary Report.xlsx"
        hqDriver.df_defect = hqDriver.df_defect.replace(np.nan, ' ', regex=True)
        hqDriver.df_Summary_report = hqDriver.df_Summary_report.replace(np.nan, ' ', regex=True)
        writer = ExcelWriter(hqDriver.resultDir + "\\ IBMhq Execution Summary Report.xlsx")
        hqDriver.df_Summary_report.to_excel(writer, "Summary Report", index=False)
        hqDriver.df_defect.to_excel(writer, "Defect", index=False)
        workbook = writer.book
        worksheet = writer.sheets['Summary Report']
        worksheet1=writer.sheets['Defect']
        fail_format = workbook.add_format({'bg_color': '#FFC7CE',
                                           'font_color': '#9C0006'})
        # Add a format for pass. Green fill with dark green text.
        pass_format = workbook.add_format({'bg_color': '#C6EFCE',
                                           'font_color': '#006100'})
        link_format = workbook.add_format({'font_color': '#0000FF',
                                           'underline': 1})
        # Apply conditional formats to the cell range.
        worksheet.conditional_format('F1:F1048576', {'type': 'text',
                                                     'criteria': 'containing',
                                                     'value': 'Fail',
                                                     'format': fail_format})
        worksheet.conditional_format('F1:F1048576', {'type': 'text',
                                                     'criteria': 'containing',
                                                     'value': 'Pass', 'format': pass_format})
        worksheet=hqDriver.adjusting_col_width(hqDriver.df_Summary_report,worksheet)
        worksheet1=hqDriver.adjusting_col_width(hqDriver.df_defect,worksheet1)
        writer.save()
        #hqDriver.excel_test_defect_result_creation()
        #post call:upload excel to server
        # if hqDriver.upload_to_server.lower()=='yes':
        #     url =hqDriver.host_for_report+r"/file-upload"
        #     files = {'file': open(summaryReport, 'rb')}
        #     r = requests.post(url, files=files)
        #     print(r.text.encode('utf8'))

        return summaryReport

    def excel_test_defect_result_creation():
        print("excel creation successfull:defect")
        hqDriver.df_defect = hqDriver.df_defect.replace(np.nan, ' ', regex=True)
        book = load_workbook(hqDriver.resultDir + "\\ IBMhq Execution Summary Report.xlsx")
        writer = ExcelWriter(hqDriver.resultDir + "\\ IBMhq Execution Summary Report.xlsx")
        writer.book = book
        writer.sheets = dict((ws.title, ws) for ws in book.worksheets)
        hqDriver.df_defect.to_excel(writer, "Defects", index=False)
        writer.save()

    #creation of empty dataframe with headers
    def creation_dataframe(headerList):
        print("df creation successfull")
        hqDriver.df = pd.DataFrame(columns=headerList,dtype=str)

    #Writing data to dataframe
    def writing_data_to_dataframe(row,testCaseName, stepno,testSetName,expecResult, actionName,objectName,inputExcel,stepdata,screenshot,result,teststepData,actualResult,s):
        if str(inputExcel).lower()=='nan':
            inputExcel=''
        if s:
            hqDriver.df.loc[row]=[str(testCaseName), str(stepno),str(testSetName),str(expecResult), str(actionName),str(objectName),str(inputExcel),str(stepdata),str(screenshot),str(result),teststepData,actualResult,'=HYPERLINK("'+str(s)+'", "Screenshots")']
        else:
            hqDriver.df.loc[row]=[str(testCaseName), str(stepno),str(testSetName),str(expecResult), str(actionName),str(objectName),str(inputExcel),str(stepdata),str(screenshot),str(result),teststepData,actualResult,s]

    #Creation of word result
    def create_Word_Doc_Result(screenShotList,screenShotDetails,iter,tcname,reqname,execStart,df):
        print("word doc creation successfull")

        document = Document()
        p = document.add_paragraph()
        p.paragraph_format.alignment = WD_ALIGN_PARAGRAPH.CENTER
        r = p.add_run("IBMhq Test Case Execution Report")
        r.font.size = Pt(16)
        r.bold = True
        p1=document.add_paragraph()
        p1.paragraph_format.alignment = WD_ALIGN_PARAGRAPH.LEFT
        r1 = p1.add_run("\nTest Case Name:"+str(tcname)+"\nRequirement Name: "+str(reqname)+"\nExecution Start Time:"+str(datetime.today().strftime('%Y-%m-%d'))+" "+str(execStart[11:]))
        r1.font.size = Pt(14)
        r1.bold = True
        df = df[['TOTAL NUMBER OF STEPS EXECUTED', 'STEPS PASSED','STEPS FAILED','EXECUTION STATUS',
                   'DEFECTS LIST']]
        df.columns = ['TOTAL STEPS', 'STEPS PASSED', 'STEPS FAILED', 'OVERALL STATUS', 'DEFECT LIST']

        t = document.add_table(df.shape[0] + 1, df.shape[1])
        t.style='Table Grid'



        # add the header rows.
        for j in range(df.shape[-1]):
            t.cell(0, j).text = df.columns[j]
            run = t.cell(0,j).paragraphs[0].runs[0]
            run.font.bold = True
            run.font.size=Pt(12)
            t.cell(0,j).paragraphs[0].alignment=WD_ALIGN_PARAGRAPH.CENTER


        # add the rest of the data frame
        for i in range(df.shape[0]):
            for j in range(df.shape[-1]):
                t.cell(i + 1, j).text = str(df.values[i, j])
                run = t.cell(i+1, j).paragraphs[0].runs[0]
                run.font.size = Pt(12)
                t.cell(i+1, j).paragraphs[0].alignment = WD_ALIGN_PARAGRAPH.CENTER
        p2 = document.add_paragraph()
        r = p2.add_run("\n")
        j=0
        stepno=0
        for i in screenShotList:
             screenShotDetail=(screenShotDetails[j]).split('#')
             stepno=stepno+1
             stepdesc=screenShotDetail[1]
             expResult=screenShotDetail[2]
             actResult=screenShotDetail[3]
             status=screenShotDetail[4]
             # r1 = pr.add_run(stepno+'\nStep Description: '+stepdesc+'\nExpected Result: '+expResult+'\nactual Result:'+actResult+'\nStatus:'+status)
             # r1.font.size = Pt(12)
             table = document.add_table(rows=6, cols=1)
             cell = table.cell(0, 0)
             cell.text = '\n'+'Step No:'+str(stepno)
             cell = table.cell(1, 0)
             cell.text = '\nStep Description: '+stepdesc
             cell = table.cell(2, 0)
             cell.text = '\nExpected Result: '+expResult
             cell = table.cell(3, 0)
             cell.text = '\nActual Result: '+actResult
             cell = table.cell(4, 0)
             if status=='pass':
                 status='Pass'
             elif status =='fail':
                 status='Fail'
             cell.text = '\nStatus: '+status

             cell=table.cell(5,0)
             try:
                para=cell.paragraphs[0]
                run = para.add_run()

                run.add_picture(i, width=Inches(6), height=Inches(4))
             except Exception as e:
                 print(e)
                 cell.text = "No Screenshot available"

             document.add_page_break()

             #r1.add_picture(i)  # r.add_picture('/tmp/foo.jpg')
             table.style = 'Table Grid'
             j=j+1
        docname=hqDriver.resultDir + '\\Reports\\' +iter+'.docx'
        document.save(docname)
        #pdf generation
        convert(docname, hqDriver.resultDir + '\\Reports\\'+iter+'.pdf')
        return docname

    def get_data_from_excel_sheet(excel,sheet,columnname,rowno):
        if excel:
            try:
                df = pd.read_excel(excel, skipinitialspace=True,
                                                  sheet_name=sheet,dtype=str)
            except Exception as e:
                print(e)
                messagebox.showerror('Error','Incorrect excel')
            try:
                stepData = str(df.loc[rowno][columnname])
                return stepData
            except Exception as e:
                print(e)

        else:
            print("no excel")
            pass

    def merging_df_with_reusable_comp(df_script1, df_script2, i):
        rowcount1 = len(df_script1.index)
        rowcount2 = len(df_script2.index)
        dic = df_script2.to_dict(orient='records')
        df_script1 = df_script1.drop(i)
        m = i
        new_key="OBJECTNAME"
        old_key="OBJECT VARIABLE NAME"
        for j in range(0, rowcount2):
            dic[j][new_key] = dic[j][old_key]
            del dic[j][old_key]
            line = pd.DataFrame(dic[j], index=[m])
            df_script1 = pd.concat([df_script1.iloc[:m], line, df_script1.iloc[m:]], sort='False').reset_index(
                drop=True)
            m = m + 1
            rowcount1 = len(df_script1.index)
        return df_script1

    def adjusting_col_width(df,worksheet):
        for i, col in enumerate(df.columns):

            #column_len = df[col].astype(str).str.len().max()

            column_len = len(col) + 2

            worksheet.set_column(i, i, column_len)
        return worksheet















