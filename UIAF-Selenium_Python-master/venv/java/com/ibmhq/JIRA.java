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

public class JIRA {

	public static void main() throws JSONException, ParseException, IOException {
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
			for (i = 1; i < countJira(); i++)

			{
				System.out.println(countJira());
				System.out.println("Sheetvalue: " + wb.getSheetAt(0).getRow(j).getCell(3).toString());
				JSONObject obj1;
				try {
					String authString = "soumen" + ":" + "jiratest";
					byte[] authEncBytes = Base64.encodeBase64(authString.getBytes());
					String authStringEnc = new String(authEncBytes);
					URL url = new URL("http://9.182.90.142:8888/rest/api/2/issue/RDEV-" + i);
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
					String authString = "soumen" + ":" + "jiratest";
					byte[] authEncBytes = Base64.encodeBase64(authString.getBytes());
					String authStringEnc = new String(authEncBytes);
					URL url = new URL("http://9.182.90.142:8888/rest/api/2/issue");
					HttpURLConnection conn = (HttpURLConnection) url.openConnection();
					conn.setDoOutput(true);
					conn.setRequestMethod("POST");
					conn.setRequestProperty("Content-Type", "application/json");
					conn.setRequestProperty("Accept", "application/json");
					conn.setRequestProperty("Authorization", "Basic " + authStringEnc);
					String input = "{\"fields\": {\"project\":{\"key\": \"RDEV\"},\"summary\":\""
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
			for (i = 1; i < countJira(); i++)

			{
				System.out.println(countJira());
				System.out.println("Sheetvalue: " + wb.getSheetAt(0).getRow(j).getCell(2).toString());
				JSONObject obj1;
				try {
					String authString = "soumen" + ":" + "jiratest";
					byte[] authEncBytes = Base64.encodeBase64(authString.getBytes());
					String authStringEnc = new String(authEncBytes);
					URL url = new URL("http://9.182.90.142:8888/rest/api/2/issue/RDEV-" + i);

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
					String authString = "soumen" + ":" + "jiratest";
					byte[] authEncBytes = Base64.encodeBase64(authString.getBytes());
					String authStringEnc = new String(authEncBytes);
					URL url = new URL("http://9.182.90.142:8888/rest/api/2/issue");
					HttpURLConnection conn = (HttpURLConnection) url.openConnection();
					conn.setDoOutput(true);
					conn.setRequestMethod("POST");
					conn.setRequestProperty("Content-Type", "application/json");
					conn.setRequestProperty("Accept", "application/json");
					conn.setRequestProperty("Authorization", "Basic " + authStringEnc);
					String input = "{\"fields\": {\"project\":{\"key\": \"RDEV\"},\"summary\":\""
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

			for (i = 1; i < countJira(); i++)

			{
				System.out.println(countJira());
				System.out.println("Sheetvalue: " + wb.getSheetAt(0).getRow(j).getCell(2).toString());
				JSONObject obj1;
				try {
					String authString = "soumen" + ":" + "jiratest";
					byte[] authEncBytes = Base64.encodeBase64(authString.getBytes());
					String authStringEnc = new String(authEncBytes);
					URL url = new URL("http://9.182.90.142:8888/rest/api/2/issue/RDEV-" + i);
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
						for (i = 1; i < countJira(); i++)

						{
							System.out.println(countJira());
							System.out.println("Sheetvalue: " + wb.getSheetAt(0).getRow(j).getCell(2).toString());
							JSONObject obj_2;
							try {
								String authString_2 = "soumen" + ":" + "jiratest";
								byte[] authEncBytes_2 = Base64.encodeBase64(authString_2.getBytes());
								String authStringEnc_2 = new String(authEncBytes_2);
								URL url_2 = new URL("http://9.182.90.142:8888/rest/api/2/issue/RDEV-" + i);
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
										String authString_3 = "soumen" + ":" + "jiratest";
										byte[] authEncBytes_3 = Base64.encodeBase64(authString_3.getBytes());
										String authStringEnc_3 = new String(authEncBytes_3);
										URL url_3 = new URL("http://9.182.90.142:8888/rest/raven/1.0/api/testset/RDEV-"
												+ i + "/test");
										HttpURLConnection conn_3 = (HttpURLConnection) url_3.openConnection();
										conn_3.setDoOutput(true);
										conn_3.setRequestMethod("POST");
										conn_3.setRequestProperty("Content-Type", "application/json");
										conn_3.setRequestProperty("Accept", "application/json");
										conn_3.setRequestProperty("Authorization", "Basic " + authStringEnc_3);
										String input = "{\"add\": [\"RDEV-" + k + "\"],\"remove\": []}";
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

	public static int countJira() throws JSONException, ParseException {
		int getSth = 0;
		try {
			String authString = "soumen" + ":" + "jiratest";
			byte[] authEncBytes = Base64.encodeBase64(authString.getBytes());
			String authStringEnc = new String(authEncBytes);
			URL url = new URL("http://9.182.90.142:8888/rest/api/2/search?jql=project=RDEV");
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
