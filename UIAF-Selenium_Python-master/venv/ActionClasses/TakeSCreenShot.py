import pyautogui

class TakeScreenShot():
    def execute(path,screenshotname):
        try:
             screenshot_name =path+"\\"+screenshotname
            # b.driver.save_screenshot(screenshot_name)


             myScreenshot = pyautogui.screenshot()
             myScreenshot.save(screenshot_name)
        except Exception as e:
            print(str(e))
        return  screenshot_name
