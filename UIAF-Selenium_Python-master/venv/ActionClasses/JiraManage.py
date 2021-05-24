from atlassian import Jira
from ActionClasses import jiraUtil
def CreateDefect(jirauname, jirapwd, jiraurl,jiraPro,summary,desc):
 try:
    jira = Jira(
    url=jiraurl,
    username=jirauname,
    password=jirapwd)
    Summary='New defect for '+summary
    Desc='Failed steps:\n'+desc
    isuue=jira.issue_create(fields={
    'project': {'key':jiraPro},
    'issuetype': {
        "name": "Bug"
    },
    'summary':Summary,
    'description':Desc,
    })
    print("Defect created successfully:",isuue['key'])

    return isuue['key']

 except Exception as e:
    print(e)

def UpdateScenario( scenario, jirauname, jirapwd, jiraurl, jiraproj):
   try:
      jira = Jira(
         url=jiraurl,
         username=jirauname,
         password=jirapwd)

      issue=jira.issue_create(fields={
         'project': {'key': jiraproj},
         'summary':scenario,
         'description':"new test for automation",
         'issuetype': {
            "name": "Test Set"
         },

      })
      scenariokey=issue["key"]
      print("Scenario created successfully:",scenariokey)
      return scenariokey
   except Exception as e:
      print(e)


def UpdateTestcase( testcase, jirauname, jirapwd, jiraurl, jiraproj):
   try:
      jira = Jira(
         url=jiraurl,
         username=jirauname,
         password=jirapwd)

      issue = jira.issue_create(fields={
         'project': {'key': jiraproj},
         'summary': testcase,
         'description': "example of a test",
         'issuetype': {
            "name": "Test"
         },

      })
      testCasekey = issue["key"]
      print("Test case created successfully:",testCasekey)
      return testCasekey
   except Exception as e:
      print(e)



def  updateTestStep(username,password,baseURL,testStep,stepData,expStepResult,TestId):
   try:
      jiraUtil.UpdateTestStep(username,password,baseURL,testStep,stepData,expStepResult,TestId)
      print("Test step updated successfully")
   except Exception as e:
       print(e)


def updateRun(username,password,baseURL,InputJson):
   try:
      runid=jiraUtil.updateRun(username,password,baseURL,InputJson)
      print("Run created successfully:",runid)
      return runid
   except Exception as e:
      print(e)


def linkJiraDefect(username,password,baseURL,defect_id,tcId):
    try:

      jira = Jira(
         url=baseURL,
         username=username,
         password=password)
      inn=jira.issue(defect_id)
      out=jira.issue(tcId)
      data={
            "type": {"name": "Blocks" },
            "inwardIssue": { "key": defect_id},
            "outwardIssue": {"key": tcId}
            }
      jira.create_issue_link(data)
      print("Linked successfully")

    except Exception as e:
       print(e)

