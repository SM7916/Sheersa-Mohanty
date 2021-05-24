*** Settings ***
Library   SapGuiLibrary

*** Variables ***
${saplogonscreen}  /app/con[0]/ses[0]/
${stepdata}  Default
${objval}  Default
${connection_name}   Default

*** Test Cases ***
SELECT_TABLE_COLUMN
    connect to session
    Connect To Existing Connection  ${connection_name}
    select table column  ${saplogonscreen}${objval}  ${stepdata}