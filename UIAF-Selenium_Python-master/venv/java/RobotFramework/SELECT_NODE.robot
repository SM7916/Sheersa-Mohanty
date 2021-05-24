*** Settings ***
Library   SapGuiLibrary

*** Variables ***
${stepdata}  Default
${stepdata1}  Default
${stepdata2}  Default
${connection_name}   Default

*** Test Cases ***
SELECT_NODE
    connect to session
    Connect To Existing Connection  ${connection_name}
    select node  ${stepdata}  ${stepdata1}  expand=${stepdata2}