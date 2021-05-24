*** Settings ***
Documentation     A test suite with a single test for New Tab
...               Created by hats' Robotcorder
Library           Selenium2Library    timeout=10

*** Variables ***
${BROWSER}    chrome
${SLEEP}    3

*** Test Cases ***
New Tab test
    Open Browser    chrome://newtab/    ${BROWSER}
    Input Text    //input[@name="username"]    anittala@in.ibm.com
    Input Text    //input[@name="password"]    ***
    Click Element    //div[@class="contentContainer"]
    Input Text    //input[@name="username"]    konkocho@in.ibm.com
    Click Element    //div[@class="contentContainer"]
    Input Text    //input[@name="password"]    ***
    Click Element    //button[@id="signin"]
    Click Element    //path[@class="svg-icon02"]
    Click Link    //a[@id="zr0:0:zt0:0:ttTree:6::di"]
    Click Link    //a[@id="zr0:0:zt0:0:ttTree:14:cmdTskDetailsM3"]
    Click Element    //iframe[@id="zr0:0:zt0:0:tfr:1:fr:1:rExt:0:ifPipChartView::f"]
    Click Element    //iframe[@id="zr0:0:zt0:0:tfr:1:fr:1:red:0:slickFrame50776::f"]
    Click Element    //div[@title="Row MISC_ANN_PLEDGE Column Dec MTD FY21 ACCOUNT_4thREP_FIX_CUR:-1000"]
    Input Text    //input[@class="editor-text"]    -2000
    Click Element    xpath=(//div[@title="Row  Column:This cell is read-only"])[3]
    Click Element    //button[@id="zr0:0:zt0:0:tfr:1:cbs"]
    Click Element    //button[@id="zr0:0:zt0:0:tfr:1:cbGrid"]
    Click Element    //iframe[@id="zr0:0:zt0:0:tfr:1:fr:1:rExt:0:ifPipChartView::f"]
    Click Element    //iframe[@id="zr0:0:zt0:0:tfr:1:fr:1:red:0:slickFrame50776::f"]
    Click Link    //a[@id="zr0:0:zt0:0:ttTree:14:cmdTskDetailsM3"]
    Click Element    //iframe[@id="zr0:0:zt0:0:tfr:1:fr:1:rExt:0:ifPipChartView::f"]
    Click Element    //iframe[@id="zr0:0:zt0:0:tfr:1:fr:1:red:0:slickFrame50776::f"]
    Click Element    //button[@id="zr0:0:zt0:0:tfr:1:cbGrid"]
    Click Element    //iframe[@id="zr0:0:zt0:0:tfr:1:fr:1:rExt:0:ifPipChartView::f"]
    Click Element    //iframe[@id="zr0:0:zt0:0:tfr:1:fr:1:rExt:0:ifPipChartView::f"]
    Click Element    //iframe[@id="zr0:0:zt0:0:tfr:1:fr:1:red:0:slickFrame50776::f"]
    Click Element    //path[@class="svg-outline"]
    Click Element    //svg[@id="cil12::icon"]
    Click Link    //a[@id="ap1:i15:4:i12:0:cl5"]
    Click Element    //button[@id="pt_d1::ok"]
    Click Element    //button[@name="Confirm"]
    Click Link    //a[@id="RedirectLink"]

    Close Browser