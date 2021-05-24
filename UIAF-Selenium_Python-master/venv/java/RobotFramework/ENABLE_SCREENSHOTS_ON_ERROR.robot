*** Settings ***
Library   SapGuiLibrary

*** Variables ***
${saplogonscreen}  /app/con[0]/ses[0]/
${connection_name}   Default

*** Test Cases ***
ENABLE_SCREENSHOTS_ON_ERROR
    connect to session
    Connect To Existing Connection  ${connection_name}
    enable screenshots on error