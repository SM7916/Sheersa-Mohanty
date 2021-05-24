*** Settings ***
Library   SapGuiLibrary

*** Variables ***
${stepdata}  Default
${stepdata1}  Default
${stepdata2}  Default
${connection_name}  Default

*** Test Cases ***
SELECT_NODE_LINK
    connect to session
    Connect To Existing Connection  ${connection_name}
    select node link  ${stepdata}  ${stepdata1}  ${stepdata2}