
import smtplib,ssl
from email.mime.multipart import MIMEMultipart
from email.mime.base import MIMEBase
from email.mime.text import MIMEText
from email.utils import formatdate
from email import encoders

def send_mail(send_from,send_to,subject,text,server,username='',password='',isTls=True):
  try:
    msg = MIMEMultipart()
    msg['From'] = send_from
    msg['To'] = send_to
    msg['Date'] = formatdate(localtime = True)
    msg['Subject'] = subject
    msg.attach(MIMEText(text))
    #context = ssl.SSLContext(ssl.PROTOCOL_SSLv3)
    #SSL connection only working on Python 3+
    smtp = smtplib.SMTP(server)


    d=smtp.sendmail(send_from, send_to, msg.as_string())
    smtp.quit()
    print("mail sent successfully")
  except Exception as e:
      print(e)
  send_mail("lmannath@in.ibm.com","lmannath@in.ibm.com","HI","hello","d23hubm6.in.ibm.com",'lmannath@in.ibm.com',"NewYear2020@12345",isTls=True)
