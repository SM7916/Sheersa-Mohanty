# import smtplib
#
#
# SERVER = "d23hubm6.in.ibm.com"
#
# def mail(fromMail,toMail,sub):
#     FROM = fromMail
#     TO = [toMail]
#
#     SUBJECT = sub
#
#     TEXT = "This message was sent via sendmail."
#
#     # Prepare actual message
#     BODY = '\n'.join([
#         'To: %s' % TO[0],
#         'From: %s' % FROM,
#         'Subject:%s' % SUBJECT,
#         '',
#         TEXT
#
#     ])
#
#
#     # Send the mail
#     server = smtplib.SMTP(SERVER)
#     server.sendmail(FROM, TO, BODY)
#     server.quit()
#
#


import smtplib,ssl
from email.mime.multipart import MIMEMultipart
from email.mime.base import MIMEBase
from email.mime.text import MIMEText
from email.utils import formatdate
from email import encoders

def send_mail(send_from,send_to,subject,text,files1,files2,server,html,username='',password='',isTls=True):
  try:
    msg = MIMEMultipart()
    msg['From'] = send_from
    msg['To'] = send_to
    msg['Date'] = formatdate(localtime = True)
    msg['Subject'] = subject
    msg.attach(MIMEText(text))

    part = MIMEBase('application', "octet-stream")
    #part.set_payload(open("WorkBook3.xlsx", "rb").read())
    part.set_payload(open(files1, "rb").read())
    encoders.encode_base64(part)
    file=files1.split('\\')

    part.add_header('Content-Disposition', 'attachment; filename='+file[-1])
    msg.attach(part)
    part1 = MIMEBase('application', "octet-stream")
    # part.set_payload(open("WorkBook3.xlsx", "rb").read())
    part1.set_payload(open(files2, "rb").read())
    encoders.encode_base64(part1)
    file = files2.split('\\')

    part1.add_header('Content-Disposition', 'attachment; filename=' + file[-1])
    msg.attach(part1)

    htmlText=html
    part2=MIMEText(htmlText,'html')
    msg.attach(part2)

    #context = ssl.SSLContext(ssl.PROTOCOL_SSLv3)
    #SSL connection only working on Python 3+
    smtp = smtplib.SMTP(server)


    d=smtp.sendmail(send_from, send_to, msg.as_string())
    smtp.quit()
    print("mail sent successfully")
  except Exception as e:
      print(e)

