package com.ibmhq;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.codec.binary.Base64;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.ParseException;

public class jiramanage {
	public String createDefect(String summary, String description, String jirauname, String jirapwd, String jiraurl,
			String jiraproj) throws JSONException, ParseException {

		try {
			String authString = jirauname + ":" + jirapwd;
			byte[] authEncBytes = Base64.encodeBase64(authString.getBytes());
			String authStringEnc = new String(authEncBytes);
			URL url = new URL(jiraurl + "/rest/api/2/issue");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("Accept", "application/json");
			conn.setRequestProperty("Authorization", "Basic " + authStringEnc);
			String input = "{\"fields\": {\"project\":{\"key\": \"" + jiraproj + "\"},\"summary\":\"" + summary
					+ "\",\"description\":\"" + description + "\",\"issuetype\": {\"name\":\"Bug\"}}}";
			System.out.println(input);
			java.io.OutputStream os = conn.getOutputStream();
			os.write(input.getBytes());
			os.flush();

			if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			String output;
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				System.out.println(output);
			}
			conn.disconnect();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jiraproj + countJira(jirauname, jirapwd, jiraurl);
	}

	public String updateScenario(String scenario, String testcase, String scenariokey, String testcasekey,
			String mfileName, int m, String jirauname, String jirapwd, String jiraurl, String jiraproj)
			throws JSONException, ParseException {
		// scenario creation
		String key = scenariokey;
		int counter;
		int i, l;
		l = 1;

		if (scenariokey.equalsIgnoreCase("NA")) {
			counter = countJira(jirauname, jirapwd, jiraurl) + 1;
			key = jiraproj + "-" + counter;
			System.out.println("Adding...");
			try {
				String authString = jirauname + ":" + jirapwd;
				byte[] authEncBytes = Base64.encodeBase64(authString.getBytes());
				String authStringEnc = new String(authEncBytes);
				URL url = new URL(jiraurl + "/rest/api/2/issue");
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setDoOutput(true);
				conn.setRequestMethod("POST");
				conn.setRequestProperty("Content-Type", "application/json");
				conn.setRequestProperty("Accept", "application/json");
				conn.setRequestProperty("Authorization", "Basic " + authStringEnc);
				String input = "{\"fields\": {\"project\":{\"key\": \"" + jiraproj + "\"},\"summary\":\"" + scenario
						+ "\",\"description\": \"new test set for automation\",\"issuetype\": {\"name\":\"Test Set\"}}}";
				System.out.println(input);
				java.io.OutputStream os = conn.getOutputStream();
				os.write(input.getBytes());
				os.flush();

				if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
					throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
				}

				BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

				String output;
				String a="";
				System.out.println("Output from Server .... \n");
				while ((output = br.readLine()) != null) {
					System.out.println(output);
					a=a+output;
				}
				conn.disconnect();
				JSONObject jsonObject = new JSONObject(a);
				key = jsonObject.getString("key");
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return key;
	}

	public String updateTestcase(String scenario, String testcase, String scenariokey, String testcasekey,
			String mfileName, int m, String jirauname, String jirapwd, String jiraurl, String jiraproj)
			throws JSONException, ParseException {
		String key = testcasekey;
		int counter;
		// test case creation
		int k;
		k = 1;
		//javax.swing.JOptionPane.showMessageDialog(null,"updateTestcase");
		if (testcasekey.equalsIgnoreCase("NA")) {
			counter = countJira(jirauname, jirapwd, jiraurl) + 1;
			key = jiraproj + "-" + counter;
			System.out.println("Adding...");
			try {
				String authString = jirauname + ":" + jirapwd;
				byte[] authEncBytes = Base64.encodeBase64(authString.getBytes());
				String authStringEnc = new String(authEncBytes);
				URL url = new URL(jiraurl + "/rest/api/2/issue");
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setDoOutput(true);
				conn.setRequestMethod("POST");
				conn.setRequestProperty("Content-Type", "application/json");
				conn.setRequestProperty("Accept", "application/json");
				conn.setRequestProperty("Authorization", "Basic " + authStringEnc);
				String input = "{\"fields\": {\"project\":{\"key\": \"" + jiraproj + "\"},\"summary\":\"" + testcase
						+ "\",\"description\": \"example of a test\",\"issuetype\": {\"name\":\"Test\"}}}}";
				System.out.println(input);
				java.io.OutputStream os = conn.getOutputStream();
				os.write(input.getBytes());
				os.flush();

				if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
					throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
				}

				BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
String a ="";
				String output;
				System.out.println("Output from Server .... \n");
				while ((output = br.readLine()) != null) {
					System.out.println(output);
					a=a+output;
				}
				conn.disconnect();
				//javax.swing.JOptionPane.showMessageDialog(null,"result"+a);
				JSONObject jsonObject = new JSONObject(a);
				key = jsonObject.getString("key");
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return key;
	}

	public String updateTestStep(String step, String expectedresult, String testcasekey, String jirauname,
			String jirapwd, String jiraurl, String jiraproj) throws JSONException, ParseException {
		String key = testcasekey;
		String runid = "";
		int counter;
		// test case creation
		// javax.swing.JOptionPane.showMessageDialog(null,"Started");
		//javax.swing.JOptionPane.showMessageDialog(null,"updateTestStep");
		try {
			String authString = jirauname + ":" + jirapwd;
			byte[] authEncBytes = Base64.encodeBase64(authString.getBytes());
			String authStringEnc = new String(authEncBytes);
			URL url = new URL(jiraurl + "/rest/raven/1.0/api/test/" + testcasekey + "/step");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("PUT");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("Accept", "application/json");
			conn.setRequestProperty("Authorization", "Basic " + authStringEnc);
			String input = "{\"step\": \"" + step + "\",\"result\": \"" + expectedresult + "\"}";
			System.out.println(input);
			// javax.swing.JOptionPane.showMessageDialog(null,input);
			java.io.OutputStream os = conn.getOutputStream();
			os.write(input.getBytes());
			os.flush();

			if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
				// javax.swing.JOptionPane.showMessageDialog(null,conn.getResponseCode());
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());

			}

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			String output;
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				System.out.println(output);
				// javax.swing.JOptionPane.showMessageDialog(null,output);
			}

			conn.disconnect();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return runid;
	}

	public String updateRun(String tckey, String status, String jirauname, String jirapwd, String jiraurl,
			String inputJSONforJira, String jiraproj) throws IOException, JSONException {
		String runid = "";
		String authString_3 = jirauname + ":" + jirapwd;
		byte[] authEncBytes_3 = Base64.encodeBase64(authString_3.getBytes());
		String authStringEnc_3 = new String(authEncBytes_3);
		URL url_3 = new URL(jiraurl + "/rest/raven/1.0/import/execution");
		HttpURLConnection conn_3 = (HttpURLConnection) url_3.openConnection();
		conn_3.setDoOutput(true);
		conn_3.setRequestMethod("POST");
		conn_3.setRequestProperty("Content-Type", "application/json");
		conn_3.setRequestProperty("Accept", "application/json");
		conn_3.setRequestProperty("Authorization", "Basic " + authStringEnc_3);
		String input = inputJSONforJira;
		System.out.println(input);
		java.io.OutputStream os = conn_3.getOutputStream();
		os.write(input.getBytes());
		os.flush();

		if (conn_3.getResponseCode() != HttpURLConnection.HTTP_OK) {
			// javax.swing.JOptionPane.showMessageDialog(null,"500");
			throw new RuntimeException("Failed : HTTP error code : " + conn_3.getResponseCode());
		}
		// javax.swing.JOptionPane.showMessageDialog(null,"200");
		String a = "";
		String getSth = "";
		BufferedReader br_3 = new BufferedReader(new InputStreamReader((conn_3.getInputStream())));

		String output;
		// System.out.println("Output from Server .... \n");
		while ((output = br_3.readLine()) != null) {
			// System.out.println(output);
			a = a + output;
		}
		JSONObject jsonObject = new JSONObject(a);
		JSONObject jsonObject1 = jsonObject.getJSONObject("testExecIssue");
		runid = jsonObject1.getString("key");
		//// javax.swing.JOptionPane.showMessageDialog(null,runid);
		return runid;
	}

	public void linkJiraDefect(String tckey, String defectid, String jirauname, String jirapwd, String jiraurl,
			String jiraproj) throws IOException {
		// javax.swing.JOptionPane.showMessageDialog(null,"called");
		String authString_3 = jirauname + ":" + jirapwd;
		byte[] authEncBytes_3 = Base64.encodeBase64(authString_3.getBytes());
		String authStringEnc_3 = new String(authEncBytes_3);
		URL url_3 = new URL(jiraurl + "/rest/api/2/issueLink");
		HttpURLConnection conn_3 = (HttpURLConnection) url_3.openConnection();
		conn_3.setDoOutput(true);
		conn_3.setRequestMethod("POST");
		conn_3.setRequestProperty("Content-Type", "application/json");
		conn_3.setRequestProperty("Accept", "application/json");
		conn_3.setRequestProperty("Authorization", "Basic " + authStringEnc_3);
		String input = "{\"type\": { \"name\": \"Blocks\"},\"inwardIssue\": {\"key\": \"" + defectid
				+ "\"},\"outwardIssue\": {\"key\": \"" + tckey + "\"}}";
		System.out.println(input);
		// javax.swing.JOptionPane.showMessageDialog(null,input);
		java.io.OutputStream os = conn_3.getOutputStream();
		os.write(input.getBytes());
		os.flush();

		if (conn_3.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
			throw new RuntimeException("Failed : HTTP error code : " + conn_3.getResponseCode());
		}

		BufferedReader br_3 = new BufferedReader(new InputStreamReader((conn_3.getInputStream())));

		String output_3;
		System.out.println("Output from Server .... \n");
		while ((output_3 = br_3.readLine()) != null) {
			System.out.println(output_3);
		}
	}

	public void updateAsso(String tsKey, String tcKey, String jirauname, String jirapwd, String jiraurl)
			throws JSONException, IOException {
		int ii;
		String authString_3 = jirauname + ":" + jirapwd;
		byte[] authEncBytes_3 = Base64.encodeBase64(authString_3.getBytes());
		String authStringEnc_3 = new String(authEncBytes_3);
		URL url_3 = new URL(jiraurl + "/rest/raven/1.0/api/testset/JIR-" + tsKey + "/test");
		HttpURLConnection conn_3 = (HttpURLConnection) url_3.openConnection();
		conn_3.setDoOutput(true);
		conn_3.setRequestMethod("POST");
		conn_3.setRequestProperty("Content-Type", "application/json");
		conn_3.setRequestProperty("Accept", "application/json");
		conn_3.setRequestProperty("Authorization", "Basic " + authStringEnc_3);
		String input = "{\"add\": [\"JIR-" + tcKey + "\"],\"remove\": []}";
		System.out.println(input);
		java.io.OutputStream os = conn_3.getOutputStream();
		os.write(input.getBytes());
		os.flush();

		if (conn_3.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
			throw new RuntimeException("Failed : HTTP error code : " + conn_3.getResponseCode());
		}

		BufferedReader br_3 = new BufferedReader(new InputStreamReader((conn_3.getInputStream())));

		String output_3;
		System.out.println("Output from Server .... \n");
		while ((output_3 = br_3.readLine()) != null) {
			System.out.println(output_3);
		}
	}

	public static void jirahandle(String jirauname, String jirapwd, String jiraurl)
			throws JSONException, ParseException, IOException {
		int i, j, k, l;
		k = 1;
		l = 1;
		InputStream ExcelFileToRead = new FileInputStream("C:\\Master_WEB_cucumber.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(ExcelFileToRead);
		System.out.println(wb.getSheetAt(0).getLastRowNum());
		// System.out.println("Sheetvalue:
		// "+wb.getSheetAt(0).getRow(2).getCell(4).toString());
		for (j = 1; j < wb.getSheetAt(0).getLastRowNum(); j++) {
			k = 1;
			for (i = 1; i < countJira(jirauname, jirapwd, jiraurl); i++) {
				System.out.println(countJira(jirauname, jirapwd, jiraurl));
				System.out.println("Sheetvalue: " + wb.getSheetAt(0).getRow(j).getCell(3).toString());
				JSONObject obj1;
				try {
					String authString = jirauname + ":" + jirapwd;
					byte[] authEncBytes = Base64.encodeBase64(authString.getBytes());
					String authStringEnc = new String(authEncBytes);
					URL url = new URL(jiraurl + "/rest/api/2/issue/JIR-" + i);
					System.out.println("i=" + i);
					HttpURLConnection conn = (HttpURLConnection) url.openConnection();
					conn.setRequestMethod("GET");
					conn.setRequestProperty("Accept", "application/json");
					conn.setRequestProperty("Authorization", "Basic " + authStringEnc);

					String a1 = "";

					if (conn.getResponseCode() != 200) {
						throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
					}

					BufferedReader br1 = new BufferedReader(new InputStreamReader((conn.getInputStream())));

					String output;
					System.out.println("Testcase creation .... \n");
					while ((output = br1.readLine()) != null) {
						// System.out.println(output);
						a1 = a1 + output;
					}
					String summry = "";
					JSONObject jsonObject = new JSONObject(a1);
					obj1 = jsonObject.getJSONObject("fields");
					summry = obj1.getString("summary");
					System.out.println(summry);
					conn.disconnect();
					if (wb.getSheetAt(0).getRow(j).getCell(3).toString().equals(summry)) {
						k = 0;
						System.out.println("macth j=" + j);
					}
				} catch (MalformedURLException e) {
					e.printStackTrace();

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (k == 1) {
				System.out.println("Adding...");
				try {
					String authString = jirauname + ":" + jirapwd;
					byte[] authEncBytes = Base64.encodeBase64(authString.getBytes());
					String authStringEnc = new String(authEncBytes);
					URL url = new URL(jiraurl + "/rest/api/2/issue");
					HttpURLConnection conn = (HttpURLConnection) url.openConnection();
					conn.setDoOutput(true);
					conn.setRequestMethod("POST");
					conn.setRequestProperty("Content-Type", "application/json");
					conn.setRequestProperty("Accept", "application/json");
					conn.setRequestProperty("Authorization", "Basic " + authStringEnc);
					String input = "{\"fields\": {\"project\":{\"key\": \"JIR\"},\"summary\":\""
							+ wb.getSheetAt(0).getRow(j).getCell(3).toString()
							+ "\",\"description\": \"example of cucumber test\",\"issuetype\": {\"name\":\"Test\"},\"customfield_10007\":{\"value\":\"Cucumber\"},\"customfield_10008\":{\"value\": \"Scenario\"},\"customfield_10009\": \"Given I have a calculator\\nWhen I press 1\\nAnd I press +\\nAnd I press 2\\nAnd I press =\\nThen I should see 3\"}}}";
					System.out.println(input);
					java.io.OutputStream os = conn.getOutputStream();
					os.write(input.getBytes());
					os.flush();

					if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
						throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
					}

					BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

					String output;
					System.out.println("Output from Server .... \n");
					while ((output = br.readLine()) != null) {
						System.out.println(output);
					}
					conn.disconnect();
				} catch (MalformedURLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		// TEST SET CREATION

		System.out.println(wb.getSheetAt(0).getLastRowNum());
		// System.out.println("Sheetvalue:
		// "+wb.getSheetAt(0).getRow(2).getCell(4).toString());
		for (j = 1; j < wb.getSheetAt(0).getLastRowNum(); j++) {
			System.out.println("j=" + j);
			l = 1;
			for (i = 1; i < countJira(jirauname, jirapwd, jiraurl); i++) {
				System.out.println(countJira(jirauname, jirapwd, jiraurl));
				System.out.println("Sheetvalue: " + wb.getSheetAt(0).getRow(j).getCell(2).toString());
				JSONObject obj1;
				try {
					String authString = jirauname + ":" + jirapwd;
					byte[] authEncBytes = Base64.encodeBase64(authString.getBytes());
					String authStringEnc = new String(authEncBytes);
					URL url = new URL(jiraurl + "/rest/api/2/issue/JIR-" + i);

					HttpURLConnection conn = (HttpURLConnection) url.openConnection();
					conn.setRequestMethod("GET");
					conn.setRequestProperty("Accept", "application/json");
					conn.setRequestProperty("Authorization", "Basic " + authStringEnc);

					String a1 = "";

					if (conn.getResponseCode() != 200) {
						throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
					}

					BufferedReader br1 = new BufferedReader(new InputStreamReader((conn.getInputStream())));

					String output;
					System.out.println("Testcase creation .... \n");
					while ((output = br1.readLine()) != null) {
						// System.out.println(output);
						a1 = a1 + output;
					}
					String summry = "";
					JSONObject jsonObject = new JSONObject(a1);
					obj1 = jsonObject.getJSONObject("fields");
					summry = obj1.getString("summary");
					System.out.println(summry);
					conn.disconnect();
					if (wb.getSheetAt(0).getRow(j).getCell(2).toString().equals(summry)) {
						l = 0;
						System.out.println("macth j=" + j);
					}
				} catch (MalformedURLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (l == 1) {
				System.out.println("Adding...");
				try {
					String authString = jirauname + ":" + jirapwd;
					byte[] authEncBytes = Base64.encodeBase64(authString.getBytes());
					String authStringEnc = new String(authEncBytes);
					URL url = new URL(jiraurl + "/rest/api/2/issue");
					HttpURLConnection conn = (HttpURLConnection) url.openConnection();
					conn.setDoOutput(true);
					conn.setRequestMethod("POST");
					conn.setRequestProperty("Content-Type", "application/json");
					conn.setRequestProperty("Accept", "application/json");
					conn.setRequestProperty("Authorization", "Basic " + authStringEnc);
					String input = "{\"fields\": {\"project\":{\"key\": \"JIR\"},\"summary\":\""
							+ wb.getSheetAt(0).getRow(j).getCell(2).toString()
							+ "\",\"description\": \"example of cucumber test set\",\"issuetype\": {\"name\":\"Test Set\"}}}";
					System.out.println(input);
					java.io.OutputStream os = conn.getOutputStream();
					os.write(input.getBytes());
					os.flush();

					if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
						throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
					}

					BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

					String output;
					System.out.println("Output from Server .... \n");
					while ((output = br.readLine()) != null) {
						System.out.println(output);
					}
					conn.disconnect();
				} catch (MalformedURLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		// ASSO
		for (j = 1; j < wb.getSheetAt(0).getLastRowNum(); j++) {
			for (i = 1; i < countJira(jirauname, jirapwd, jiraurl); i++) {
				System.out.println(countJira(jirauname, jirapwd, jiraurl));
				System.out.println("Sheetvalue: " + wb.getSheetAt(0).getRow(j).getCell(2).toString());
				JSONObject obj1;
				try {
					String authString = jirauname + ":" + jirapwd;
					byte[] authEncBytes = Base64.encodeBase64(authString.getBytes());
					String authStringEnc = new String(authEncBytes);
					URL url = new URL(jiraurl + "/rest/api/2/issue/JIR-" + i);
					System.out.println("i=" + i);
					HttpURLConnection conn = (HttpURLConnection) url.openConnection();
					conn.setRequestMethod("GET");
					conn.setRequestProperty("Accept", "application/json");
					conn.setRequestProperty("Authorization", "Basic " + authStringEnc);

					String a1 = "";

					if (conn.getResponseCode() != 200) {
						throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
					}

					BufferedReader br1 = new BufferedReader(new InputStreamReader((conn.getInputStream())));

					String output;
					System.out.println("Testcase creation .... \n");
					while ((output = br1.readLine()) != null) {
						// System.out.println(output);
						a1 = a1 + output;
					}
					String summry = "";
					JSONObject jsonObject = new JSONObject(a1);
					obj1 = jsonObject.getJSONObject("fields");
					summry = obj1.getString("summary");
					System.out.println(summry);
					conn.disconnect();
					if (wb.getSheetAt(0).getRow(j).getCell(2).toString().equals(summry)) {
						for (i = 1; i < countJira(jirauname, jirapwd, jiraurl); i++) {
							System.out.println(countJira(jirauname, jirapwd, jiraurl));
							System.out.println("Sheetvalue: " + wb.getSheetAt(0).getRow(j).getCell(2).toString());
							JSONObject obj_2;
							try {
								String authString_2 = jirauname + ":" + jirapwd;
								byte[] authEncBytes_2 = Base64.encodeBase64(authString_2.getBytes());
								String authStringEnc_2 = new String(authEncBytes_2);
								URL url_2 = new URL(jiraurl + "/rest/api/2/issue/JIR-" + i);
								System.out.println("i=" + i);
								HttpURLConnection conn_2 = (HttpURLConnection) url_2.openConnection();
								conn_2.setRequestMethod("GET");
								conn_2.setRequestProperty("Accept", "application/json");
								conn_2.setRequestProperty("Authorization", "Basic " + authStringEnc_2);

								String a_2 = "";

								if (conn_2.getResponseCode() != 200) {
									throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
								}

								BufferedReader br_2 = new BufferedReader(
										new InputStreamReader((conn.getInputStream())));

								String output_2;
								System.out.println("Testcase creation .... \n");
								while ((output_2 = br_2.readLine()) != null) {
									// System.out.println(output);
									a_2 = a_2 + output_2;
								}
								String summry_2 = "";
								JSONObject jsonObject_2 = new JSONObject(a1);
								obj_2 = jsonObject_2.getJSONObject("fields");
								summry_2 = obj_2.getString("summary");
								System.out.println(summry_2);
								conn.disconnect();
								if (wb.getSheetAt(0).getRow(j).getCell(3).toString().equals(summry_2)) {
									try {
										String authString_3 = jirauname + ":" + jirapwd;
										byte[] authEncBytes_3 = Base64.encodeBase64(authString_3.getBytes());
										String authStringEnc_3 = new String(authEncBytes_3);
										URL url_3 = new URL(jiraurl + "/rest/raven/1.0/api/testset/JIR-" + i + "/test");
										HttpURLConnection conn_3 = (HttpURLConnection) url_3.openConnection();
										conn_3.setDoOutput(true);
										conn_3.setRequestMethod("POST");
										conn_3.setRequestProperty("Content-Type", "application/json");
										conn_3.setRequestProperty("Accept", "application/json");
										conn_3.setRequestProperty("Authorization", "Basic " + authStringEnc_3);
										String input = "{\"add\": [\"JIR-" + k + "\"],\"remove\": []}";
										System.out.println(input);
										java.io.OutputStream os = conn_3.getOutputStream();
										os.write(input.getBytes());
										os.flush();

										if (conn_3.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
											throw new RuntimeException(
													"Failed : HTTP error code : " + conn_3.getResponseCode());
										}

										BufferedReader br_3 = new BufferedReader(
												new InputStreamReader((conn_3.getInputStream())));

										String output_3;
										System.out.println("Output from Server .... \n");
										while ((output_3 = br_3.readLine()) != null) {
											System.out.println(output_3);
										}
										conn.disconnect();
									} catch (MalformedURLException e) {
										e.printStackTrace();
									} catch (IOException e) {
										e.printStackTrace();
									}
								}
							} catch (MalformedURLException e) {
								e.printStackTrace();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					}
				} catch (MalformedURLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static int countJira(String jirauname, String jirapwd, String jiraurl) throws JSONException, ParseException {
		// javax.swing.JOptionPane.showMessageDialog(null,"url"+jiraurl);
		// javax.swing.JOptionPane.showMessageDialog(null,"uname"+jirauname);
		// javax.swing.JOptionPane.showMessageDialog(null,"pwd"+jirapwd);
		int getSth = 0;
		try {
			String authString = jirauname + ":" + jirapwd;
			byte[] authEncBytes = Base64.encodeBase64(authString.getBytes());
			String authStringEnc = new String(authEncBytes);
			URL url = new URL(jiraurl + "/rest/api/2/search?jql=project=JIR");

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			conn.setRequestProperty("Authorization", "Basic " + authStringEnc);

			String a = "";

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			String output;
			// System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				// System.out.println(output);
				a = a + output;
			}
			JSONObject jsonObject = new JSONObject(a);
			getSth = jsonObject.getInt("total");
			// System.out.println(getSth);
			conn.disconnect();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return getSth;
	}
}