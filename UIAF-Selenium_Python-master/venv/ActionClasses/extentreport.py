from jpype import *

classpath = """extentreports-2.40.2.jar;freemarker-2.3.23.jar"""
startJVM(getDefaultJVMPath(), "-Djava.class.path=%s" % classpath)
ExtentReports = JClass('com.relevantcodes.extentreports.ExtentReports')
ExtentTest = JClass('com.relevantcodes.extentreports.ExtentTest')
LogStatus = JClass('com.relevantcodes.extentreports.LogStatus')
extent = ExtentReports("Test_Report.html")

test = extent.startTest("Test Case 1", "Sample description")
test.log(LogStatus.INFO, "This step shows usage of log(logStatus, details)")
test.log(LogStatus.PASS, "Step Passed")
extent.flush()

test = extent.startTest("Test Case 2", "Sample description")
test.log(LogStatus.INFO, "This step shows usage of log(logStatus, details)")
test.log(LogStatus.PASS, "")
test.log(LogStatus.FAIL, "Step Passed")
extent.flush()

extent.endTest(test)
extent.flush()
shutdownJVM()