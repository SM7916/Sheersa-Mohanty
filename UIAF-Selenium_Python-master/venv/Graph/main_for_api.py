import os
import urllib.request
from Graph.app_for_api import app1
from flask import Flask, request, redirect, jsonify
from werkzeug.utils import secure_filename
from Graph.config_read import configRead

ALLOWED_EXTENSIONS = set(['xlsx', 'xls'])

def allowed_file(filename):
	return '.' in filename and filename.rsplit('.', 1)[1].lower() in ALLOWED_EXTENSIONS

@app1.route('/file-upload', methods=['POST'])
def upload_file():
	# check if the post request has the file part
	if 'file' not in request.files:
		resp = jsonify({'message' : 'No file part in the request'})
		resp.status_code = 400
		return resp
	file = request.files['file']
	if file.filename == '':
		resp = jsonify({'message' : 'No file selected for uploading'})
		resp.status_code = 400
		return resp
	if file and allowed_file(file.filename):
		filename = secure_filename(file.filename)
		file.save(os.path.join(app1.config['UPLOAD_FOLDER'], "Graph\\"+filename))
		print("#############################33")
		print(os.path.join(app1.config['UPLOAD_FOLDER'], "Graph\\"+filename))
		resp = jsonify({'message' : 'File successfully uploaded'})
		resp.status_code = 201
		return resp
	else:
		resp = jsonify({'message' : 'Allowed file types are xlsx and xls'})
		resp.status_code = 400
		return resp

# if __name__ == "__main__":
#     app.run(port='8052')
def appRun():
	Host=configRead('host')
	Port = configRead('port_for_fileupload')
	app1.run(host=Host,port=Port)
