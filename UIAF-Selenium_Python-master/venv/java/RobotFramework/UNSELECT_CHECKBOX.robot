*** Settings ***
Library   SapGuiLibrary

*** Variables ***
${saplogonscreen}  /app/con[0]/ses[0]/
${objval}  Default
${connection_name}   Default

*** Test Cases ***
UNSELECT_CHECKBOX
    connect to session
    Connect To Existing Connection  ${connection_name}
    unselect checkbox  ${saplogonscreen}${objval}