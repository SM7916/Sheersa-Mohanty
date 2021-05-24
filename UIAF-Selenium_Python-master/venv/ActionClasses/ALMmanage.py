from requests.auth import HTTPBasicAuth
from requests import Session
from xml.etree.ElementTree import Element, SubElement, tostring
from datetime import date
import json
# AUTH_END_POINT = ALM_URL + "authentication-point/authenticate"
# QC_SESSION_END_POINT = ALM_URL + "rest/site-session"
#QC_LOGOUT_END_POINT = ALM_URL + "authentication-point/logout"
# ALM_MIDPOINT = ALM_URL + "rest/domains/" + ALM_DOMAIN + "/projects/"

def generate_xml_data(inputdata):
    root = Element('Entity')
    root.set('Type', inputdata['Type'])
    inputdata.pop('Type')
    childs = SubElement(root, 'Fields')
    for key, value in inputdata.items():
        child1 = SubElement(childs, 'Field')
        child1.set('Name', key)
        child2 = SubElement(child1, 'Value')
        child2.text = value
    return tostring(root)

def create_defect(ALM_URL,ALM_USER_NAME,ALM_PASSWORD,ALM_DOMAIN,ALM_PROJECT,testCaseName,desc):
  session = Session()
  QC_LOGOUT_END_POINT = ALM_URL + "/authentication-point/logout"
  try:

    session.verify = False
    auth = session.post(ALM_URL + "/authentication-point/authenticate",
                    auth=HTTPBasicAuth(ALM_USER_NAME, ALM_PASSWORD))
    site_session = session.post(ALM_URL + "/rest/site-session")
    to=date.today()
    session.headers.update({'Accept': 'application/json',
                                    'Content-Type': 'application/xml'})
    defect = dict()
    defect['Type'] = 'defect'
    defect['name'] = testCaseName
    defect['severity'] = '1-Low'
    defect['priority'] = '1-Low'
    defect['detected-by'] = ALM_USER_NAME
    defect['creation-time'] = str(to)
    defect['description']="Failed Steps are"+'\n'+desc
    defect_payload = generate_xml_data(defect)
    print(ALM_URL + "/rest/domains/"+ALM_DOMAIN+"/projects/"+ALM_PROJECT+"/defects")

    deft=session.post(ALM_URL + "/rest/domains/"+ALM_DOMAIN+"/projects/"+ALM_PROJECT+"/defects",data=defect_payload)
    print(deft.text)
    a = json.loads(deft.text)
    for i in (a['Fields']):
        if i['Name']=='id':
            id=i['values'][0]['value']

    print("ALM defect created successfully")

  finally:
    if session:
            res = session.post(QC_LOGOUT_END_POINT)
  return id

def create_testrun(ALM_URL,ALM_USER_NAME,ALM_PASSWORD,ALM_DOMAIN,ALM_PROJECT,ALM_TESTSET_ID,tsname) :
    session = Session()
    QC_LOGOUT_END_POINT = ALM_URL + "/authentication-point/logout"
    try:

        session.verify = False
        auth = session.post(ALM_URL + "/authentication-point/authenticate",
                            auth=HTTPBasicAuth(ALM_USER_NAME, ALM_PASSWORD))
        print(auth)
        site_session = session.post(ALM_URL + "/rest/site-session")
        session.headers.update({'Accept': 'application/json',
                                'Content-Type': 'application/xml'})
        resp=session.get(ALM_URL+"/rest/domains/"+ALM_DOMAIN+"/projects/"+ALM_PROJECT+"/test-instances?query={cycle-id["+ALM_TESTSET_ID+"]}")
        print(ALM_URL+"/rest/domains/"+ALM_DOMAIN+"/projects/"+ALM_PROJECT+"/test-instances?query={cycle-id["+ALM_TESTSET_ID+"]}")
        print(resp.text)
        a=json.loads(resp.text)

        for l in range(len(a['entities'])):
            for i in a['entities'][l]['Fields']:

                if i['Name']=='name':
                    tname=i['values'][0]['value']
                    tname=tname.split('[')[0]

                    tname=tname[0:len(tname)-1]
                    print(tname,tsname)
                    if tname == tsname:
                        for j in  a['entities'][l]['Fields']:
                            if j['Name'] == 'id':
                                 test_inst_id = j['values'][0]['value']
                            if j['Name'] == 'cycle-id':
                                cyc_id = j['values'][0]['value']
                            if j['Name'] == 'test-id':
                                test_id = j['values'][0]['value']
                        session.headers.update({'Accept': 'application/json',
                                                'Content-Type': 'application/xml'})
                        runbefore = dict()
                        runbefore['Type'] = 'run'
                        runbefore['name'] = 'test12345'
                        runbefore['test-instance'] = '1'
                        runbefore['testcycl-id'] = test_inst_id
                        runbefore['cycle-id'] = cyc_id
                        runbefore['test-id'] = test_id
                        runbefore['subtype-id'] = 'hp.qc.run.MANUAL'
                        runbefore['status'] = 'Not Completed'
                        runbefore['owner'] = 'pritha'
                        run_payload = generate_xml_data(runbefore)
                        resbefore = session.post(
                            ALM_URL + "/rest/domains/" + ALM_DOMAIN + "/projects/" + ALM_PROJECT + "/runs",
                            data=run_payload)
                        print("Test run created successfully")
                        return resbefore.text

        print("No test case.Test run creation failed.")
        return 0

    finally:
        if session:
            res = session.post(QC_LOGOUT_END_POINT)



def createrunafter(ALM_URL,ALM_USER_NAME,ALM_PASSWORD,ALM_DOMAIN,ALM_PROJECT,respon,flag,result_list):
    session = Session()
    QC_LOGOUT_END_POINT = ALM_URL + "/authentication-point/logout"
    try:
        session.verify = False
        auth = session.post(ALM_URL + "/authentication-point/authenticate",
                            auth=HTTPBasicAuth(ALM_USER_NAME, ALM_PASSWORD))
        site_session = session.post(ALM_URL + "/rest/site-session")
        to = date.today()
        session.headers.update({'Accept': 'application/json',
                                'Content-Type': 'application/xml'})
        b = json.loads(respon)
        for i in b['Fields']:
            if i['Name'] == 'id':
                test_inst_id1 = i['values'][0]['value']
            if i['Name'] == 'test-config-id':
                test_config_id1 = i['values'][0]['value']
            if i['Name'] == 'testcycl-id':
                test_cycle_id1 = i['values'][0]['value']
            if i['Name'] == 'cycle-id':
                cycle_id1 = i['values'][0]['value']
            if i['Name'] == 'test-id':
                test_id1 = i['values'][0]['value']
        runafter = dict()
        to = date.today()
        runafter['Type'] = 'run'
        runafter['test-instance'] = '1'
        runafter['execution-date'] = str(to)
        runafter['ver-stamp'] = '1'
        runafter['test-config-id'] = test_config_id1
        runafter['name'] = 'run'
        runafter['has-linkage'] = 'N'
        runafter['testcycl-id'] = test_cycle_id1
        runafter['cycle-id'] = cycle_id1
        runafter['host'] = 'DESKTOP-8A2R27U'
        if flag==0:
            runafter['status'] = 'Passed'
        else:
            runafter['status']='Failed'
        runafter['test-id'] = test_id1
        runafter['subtype-id'] = 'hp.qc.run.MANUAL'
        runafter['draft'] = 'N'
        runafter['duration'] = '0'
        runafter['owner'] = 'pritha'
        runafter_payload = generate_xml_data(runafter)

        resafter = session.put(
            ALM_URL + "/rest/domains/" + ALM_DOMAIN + "/projects/" + ALM_PROJECT + "/runs/" + test_inst_id1,
            data=runafter_payload)
        steps=session.get(ALM_URL+"/rest/domains/" + ALM_DOMAIN + "/projects/" + ALM_PROJECT + "/runs/" + test_inst_id1+'/run-steps/')
        steps=steps.json()
        print(steps)
        for i in range(len(result_list)):
            if result_list[i].lower()=='pass':
                result='Passed'
            else:
                result='Failed'
            steps['entities'][i]['Fields'][27]['values'][0]['value']=result
            stepid=steps['entities'][i]['Fields'][12]['values'][0]['value']
            js=steps['entities'][i]


            js=json.dumps(js)
            session.headers.update({'Accept': 'application/json',
                                 'Content-Type': 'application/json'})
            respstep=session.put(ALM_URL+"/rest/domains/" + ALM_DOMAIN + "/projects/" + ALM_PROJECT + "/runs/" + test_inst_id1+'/run-steps/'+stepid,data=js)
            print("step "+str(i)+" updated successfully",respstep.status_code)
        print("Test run Completed.Status:",runafter['status'])

    finally:
        if session:
            res = session.post(QC_LOGOUT_END_POINT)

# ALM_URL ="http://inmbzp4181.in.dst.ibm.com:8888/qcbin"
# ALM_USER_NAME = "pritha"
# ALM_PASSWORD = "testing"
# ALM_DOMAIN = "STEF"
# ALM_PROJECT="STEF_DEMO_ALM"
# ALM_TESTSET_ID="805"
# create_defect(ALM_URL,ALM_USER_NAME,ALM_PASSWORD,ALM_DOMAIN,ALM_PROJECT,"testcase","descccc")
# r=create_testrun(ALM_URL,ALM_USER_NAME,ALM_PASSWORD,ALM_DOMAIN,ALM_PROJECT,ALM_TESTSET_ID)
# flag=0
# createrunafter(ALM_URL,ALM_USER_NAME,ALM_PASSWORD,ALM_DOMAIN,ALM_PROJECT,r,flag)

#responsebeforerun=create_testrun("http://inmbzp4181.in.dst.ibm.com:8888/qcbin/","pritha","testing","STEF","STEF_DEMO_MAY30",805,tcName)
#createrunafter(alm_url, alm_username, alm_password, alm_domain, alm_project,
#                                                       responsebeforerun,failflagalm)