*** Settings ***
Library   SapGuiLibrary

*** Variables ***
${connection_name}   Default

*** Test Cases ***
TAKE_SCREENSHOT
    connect to session
    Connect To Existing Connection  ${connection_name}
    take screenshot  screenshot_name=sap-screenshot