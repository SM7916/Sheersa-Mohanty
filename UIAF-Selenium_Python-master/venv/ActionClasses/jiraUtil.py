
from base64 import b64encode
from json import dumps
import requests
import  math


def UpdateTestStep(username,password,baseURL,testStep,stepData,stepResult,TestId):
    createTestStepURL = baseURL + "/rest/raven/1.0/api/test/"+TestId+"/step/"
    u=username+":"+password
    u1=u.encode('ascii')
    userAndPass = b64encode(u1).decode("ascii")
    headers = {"Authorization":"Basic %s" % userAndPass, "Content-Type": "application/json"}
    try:
        if(math.isnan(float(stepData))):
            stepData="No Data Required"
    except:
        pass

    newTestStepValues ={
    "step": testStep,
    "data": stepData,
    "result": stepResult
}
    r = requests.put(createTestStepURL, data=dumps(newTestStepValues), headers=headers,)



def updateRun (username,password,baseURL,InputJson):
    runURL=baseURL+"/rest/raven/1.0/import/execution"
    u = username+":"+password
    u1 = u.encode('ascii')
    userAndPass = b64encode(u1).decode("ascii")
    headers = {"Authorization": "Basic %s" % userAndPass, "Content-Type": "application/json"}
    r=requests.post(runURL,data=dumps(InputJson),headers=headers)
    r=r.json()
    return(r['testExecIssue']['key'])


