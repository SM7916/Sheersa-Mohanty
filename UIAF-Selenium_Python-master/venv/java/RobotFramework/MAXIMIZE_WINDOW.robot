*** Settings ***
Library   SapGuiLibrary

*** Variables ***
${connection_name}   Default

*** Test Cases ***
MAXIMIZE_WINDOW
    connect to session
    Connect To Existing Connection  ${connection_name}
    maximize window  window=0