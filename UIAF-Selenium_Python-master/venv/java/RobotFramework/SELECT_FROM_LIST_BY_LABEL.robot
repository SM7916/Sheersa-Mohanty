*** Settings ***
Library   SapGuiLibrary

*** Variables ***
${saplogonscreen}  /app/con[0]/ses[0]/
${stepdata}  Default
${objval}  Default
${connection_name}   Default

*** Test Cases ***
SELECT_FROM_LIST_BY_LABEL
    connect to session
    Connect To Existing Connection  ${connection_name}
    select from list by label  ${saplogonscreen}${objval}  ${stepdata}