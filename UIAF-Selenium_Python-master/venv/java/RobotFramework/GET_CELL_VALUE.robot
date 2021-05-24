*** Settings ***
Library   SapGuiLibrary

*** Variables ***
${stepdata}  Default
${stepdata1}  Default
${stepdata2}  Default
${connection_name}  Default

*** Test Cases ***
GET_CELL_VALUE
    connect to session
    Connect To Existing Connection  ${connection_name}
    get cell value  ${stepdata}  ${stepdata1}  ${stepdata2}