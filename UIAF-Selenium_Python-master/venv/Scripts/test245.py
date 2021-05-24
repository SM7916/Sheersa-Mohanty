import requests
from requests.auth import HTTPBasicAuth
from xml.etree.ElementTree import Element, SubElement, tostring, parse
from datetime import date

ALM_USER_NAME = "pritha"
ALM_PASSWORD = "testing"
ALM_DOMAIN = "STEF"
ALM_PROJECT="STEF_DEMO_MAY30"
#
ALM_URL ="http://inmbzp4181.in.dst.ibm.com:8888/qcbin/"
# AUTH_END_POINT = ALM_URL + "authentication-point/authenticate"
# QC_SESSION_END_POINT = ALM_URL + "rest/site-session"
QC_LOGOUT_END_POINT = ALM_URL + "authentication-point/logout"
# ALM_MIDPOINT = ALM_URL + "rest/domains/" + ALM_DOMAIN + "/projects/"
#
def generate_xml_data(inputdata):
    '''
        Function    :   generateXMLData
        Description :   Generate an xml string
        Parameters  :   Dictionary of variable
    '''
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

# def createdefect():
#     '''
#         alm defect
#     '''
#     alm_session = requests.Session()
#     # Login
#     try:
#         res = alm_session.post(AUTH_END_POINT, auth=HTTPBasicAuth(
#             ALM_USER_NAME, ALM_PASSWORD))
#
#         if res.status_code == 200:
#             print( "ALM: Logged in")
#
#         print(res.cookies)
#
#         alm_session.post(QC_SESSION_END_POINT)
#         alm_session.headers.update({'Accept': 'application/json',
#                                     'Content-Type': 'application/xml'})
#         # Get all the projects
#         res = alm_session.get(ALM_MIDPOINT)
#
        # # Post a New Defect
        # defect = dict()
        # defect['Type'] = 'defect'
        # defect['name'] = 'StackOverflow'
        # defect['user-10'] = 'Content'  # User defined field
        # defect['severity'] = '1-Low'
        # defect['priority'] = '1-Low'
        # defect['detected-by'] = 'userid'
        # defect['creation-time'] = '2017-11-13'
        #
        # # Call the generic method to build the xml data
        # defect_payload = generate_xml_data(defect)
#
#
#         response = alm_session.post(ALM_MIDPOINT + "content/defects", data=defect_payload)
#
#         print (response.status_code)
#
#
#     finally:
#         if alm_session:
#             res = alm_session.post(QC_LOGOUT_END_POINT)
#             if res.status_code == 200:
#                 print ("ALM: Logged out")
#
# if __name__ == "__main__":
#     try:
#         createdefect()
#     except (KeyboardInterrupt, SystemExit):
#         print ("done with errors")

ALM_URL ="http://inmbzp4181.in.dst.ibm.com:8888/qcbin/"
QC_LOGOUT_END_POINT = ALM_URL + "authentication-point/logout"
session = requests.Session()
session.verify = False

auth = session.post(ALM_URL + "authentication-point/authenticate",
                    auth=HTTPBasicAuth(ALM_USER_NAME, ALM_PASSWORD))
print("Authentication ", auth, auth.text, session.cookies)

site_session = session.post(ALM_URL + "rest/site-session")
print("Session ", site_session, site_session.text, session.cookies)

check = session.get(ALM_URL + "rest/is-authenticated")
print("Check ", check, check.text)





# Enforce JSON output

to=date.today()
session.headers.update({'Accept': 'application/json',
                                    'Content-Type': 'application/xml'})
#projects = session.get(hpqc_server + "rest/domains/"+ALM_DOMAIN+"/projects")
# Post a New Defect
defect = dict()
defect['Type'] = 'defect'
defect['name'] = 'StackOverflow'
defect['severity'] = '1-Low'
defect['priority'] = '1-Low'
defect['detected-by'] = 'pritha'
defect['creation-time'] = str(to)


defect_payload = generate_xml_data(defect)


deft=session.post(ALM_URL + "rest/domains/"+ALM_DOMAIN+"/projects/"+ALM_PROJECT+"/defects",data=defect_payload)




if session:
            res = session.post(QC_LOGOUT_END_POINT)
            if res.status_code == 200:
                print ("ALM: Logged out")





