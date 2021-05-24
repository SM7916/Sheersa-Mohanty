*** Settings ***
Library   SapGuiLibrary

*** Variables ***
${saplogonscreen}  /app/con[0]/ses[0]/
${connection_name}   Default

*** Test Cases ***
CONNECT_TO_EXISTING_CONNECTION
    connect to session
    Connect To Existing Connection  ${connection_name}