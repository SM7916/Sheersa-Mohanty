*** Settings ***
Documentation    Suite description
Library   SapGuiLibrary

*** Variables ***
${saplogonscreen}  /app/con[0]/ses[0]/
${objval}  Default
${connection_name}  Default

*** Test Cases ***
CLICK TOOLBAR BUTTON
    connect to session
    Connect To Existing Connection  ${connection_name}
    click toolbar button  ${saplogonscreen}${objval}