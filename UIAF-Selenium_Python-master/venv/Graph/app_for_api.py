from flask import Flask
import os

UPLOAD_FOLDER = os.getcwd()

app1 = Flask(__name__)
app1.secret_key = "secret key"
app1.config['UPLOAD_FOLDER'] = UPLOAD_FOLDER
app1.config['MAX_CONTENT_LENGTH'] = 16 * 1024 * 1024