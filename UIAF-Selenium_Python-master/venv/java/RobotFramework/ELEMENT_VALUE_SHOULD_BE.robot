*** Settings ***
Library   SapGuiLibrary

*** Variables ***
${saplogonscreen}  /app/con[0]/ses[0]/
${stepdata}  Default
${objval}  Default
${connection_name}   Default

*** Test Cases ***
ELEMENT_VALUE_SHOULD_BE
    connect to session
    Connect To Existing Connection  ${connection_name}
    element value should be  ${saplogonscreen}${objval}  ${stepdata}  message=None