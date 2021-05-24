*** Settings ***
Library   SapGuiLibrary

*** Variables ***
${saplogonscreen}  /app/con[0]/ses[0]/
${connection_name}   Default

*** Test Cases ***
DISABLE_SCREENSHOTS_ON_ERROR
    connect to session
    Connect To Existing Connection  ${connection_name}
    disable screenshots on error