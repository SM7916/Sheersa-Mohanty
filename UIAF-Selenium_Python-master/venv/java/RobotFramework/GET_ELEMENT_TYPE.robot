*** Settings ***
Library   SapGuiLibrary

*** Variables ***
${saplogonscreen}  /app/con[0]/ses[0]/
${objval}  Default
${connection_name}   Default

*** Test Cases ***
GET_ELEMENT_TYPE
    connect to session
    Connect To Existing Connection  ${connection_name}
    get element type  ${saplogonscreen}${objval}