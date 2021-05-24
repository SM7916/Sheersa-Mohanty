*** Settings ***
Library   SapGuiLibrary

*** Variables ***
${saplogonscreen}  /app/con[0]/ses[0]/
${connection_name}  Default
${stepdata}    Default

*** Test Cases ***
RunTransaction
    connect to session
    Connect To Existing Connection  ${connection_name}
    run transaction  ${stepdata}