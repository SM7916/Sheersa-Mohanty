*** Settings ***
Library   SapGuiLibrary

*** Variables ***
${saplogonscreen}  /app/con[0]/ses[0]/
${stepdata}  Default
${objval}  Default
${connection_name}  Default
#${xpath} = ${saplogonscreen} + ${objval}

*** Test Cases ***
SETTEXT
    connect to session
    Connect To Existing Connection  ${connection_name}
    Input Text  ${saplogonscreen}${objval}  ${stepdata}