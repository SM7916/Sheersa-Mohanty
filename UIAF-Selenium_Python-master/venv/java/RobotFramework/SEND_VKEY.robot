*** Settings ***
Library   SapGuiLibrary

*** Variables ***
${stepdata}  Default
${objval}  Default
${connection_name}   Default

*** Test Cases ***
SEND_VKEY
    connect to session
    Connect To Existing Connection  ${connection_name}
    send vkey  ${stepdata}  window=0