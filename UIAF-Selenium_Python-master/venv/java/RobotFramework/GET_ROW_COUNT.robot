*** Settings ***
Library   SapGuiLibrary

*** Variables ***
${saplogonscreen}  /app/con[0]/ses[0]/
${stepdata}  Default
${objval}  Default
${connection_name}   Default

*** Test Cases ***
GET_ROW_COUNT
    connect to session
    Connect To Existing Connection  ${connection_name}
    get row count  ${stepdata}