*** Settings ***
Library   SapGuiLibrary

*** Variables ***
${saplogonscreen}  /app/con[0]/ses[0]/
${stepdata}  Default
${objval}  Default
${connection_name}   Default

*** Test Cases ***
ELEMENT_SHOULD_BE_PRESENT
    connect to session
    Connect To Existing Connection  ${connection_name}
    element should be present  ${saplogonscreen}${objval}  message=None