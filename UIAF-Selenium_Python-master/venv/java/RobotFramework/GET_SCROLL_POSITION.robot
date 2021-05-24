*** Settings ***
Library   SapGuiLibrary

*** Variables ***
${saplogonscreen}  /app/con[0]/ses[0]/
${stepdata}  Default
${objval}  Default
${connection_name}   Default

*** Test Cases ***
GET_SCROLL_POSITION
    connect to session
    Connect To Existing Connection  ${connection_name}
    get scroll position  ${saplogonscreen}${objval}