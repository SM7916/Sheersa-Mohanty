*** Settings ***
Library   SapGuiLibrary

*** Variables ***
${saplogonscreen}  /app/con[0]/ses[0]/
${objval}  Default
${connection_name}   Default

*** Test Cases ***
SELECT_RADIO_BUTTON
    connect to session
    Connect To Existing Connection  ${connection_name}
    select radio button  ${saplogonscreen}${objval}