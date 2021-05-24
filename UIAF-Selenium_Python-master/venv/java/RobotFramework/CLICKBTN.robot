*** Settings ***
Library   SapGuiLibrary

*** Variables ***
${saplogonscreen}  /app/con[0]/ses[0]/
${objval}   Default
${connection_name}  Default

*** Test Cases ***
CLICKBTN
    connect to session
    Connect To Existing Connection  ${connection_name}
    click element  ${saplogonscreen}${objval}