*** Settings ***
Library   SapGuiLibrary

*** Variables ***
${saplogonscreen}  /app/con[0]/ses[0]/wnd[0]
${stepdata}  Default

****** Test Cases ***
OPENCONN
    connect to session
    Open Connection  ${stepdata}
    #maximize window window=0

