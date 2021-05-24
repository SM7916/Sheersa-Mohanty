*** Settings ***
Library   SapGuiLibrary

*** Variables ***
${saplogonscreen}  /app/con[0]/ses[0]/
${stepdata}  Default
${objval}  Default
${connection_name}   Default

*** Test Cases ***
SELECT_CONTEXT_MENU_ITEM
    connect to session
    Connect To Existing Connection  ${connection_name}
    select context menu item  ${saplogonscreen}${objval}  ${stepdata}