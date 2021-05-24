*** Settings ***
Library   SapGuiLibrary

*** Variables ***
${connection_name}   Default

*** Test Cases ***
CONNECT_TO_SESSION
    connect to session