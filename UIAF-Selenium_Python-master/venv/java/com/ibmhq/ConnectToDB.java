package com.ibmhq;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ConnectToDB {

	List<String> data = new ArrayList<String>();

	public String execute(String dbDetails) throws ClassNotFoundException, SQLException, Exception {
		BufferedWriter out = null;
		String strstatus = null;
		String Details = "";

		if (dbDetails.contains("#")) {

			Details = dbDetails;
			System.out.println(Details);

			String dbUrl = Details.split("#")[0];
			String uname = Details.split("#")[1];
			String password = Details.split("#")[2];
			String query = Details.split("#")[3];
			String path = Details.split("#")[4];

			// Load oracle jdbc driver
			Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
			// Create Connection to DB
			Connection con = DriverManager.getConnection(dbUrl, uname, password);

			if (con != null) {
				System.out.println("Connected");
			}
			// Create Statement Object
			Statement stmt = con.createStatement();

			// Execute the SQL Query. Store results in ResultSet
			ResultSet rs = stmt.executeQuery(query);
			// Get the column count using MetaData
			ResultSetMetaData rsmd = rs.getMetaData();
			int ColCount = rsmd.getColumnCount();

			File file = new File(path);
			out = new BufferedWriter(new FileWriter(file, true));
			// While Loop to iterate through all data and print results

			while (rs.next()) {
				for (int i = 0; i <= ColCount; i++) {
					String values = rs.getString(i);
					data.add(values);
					System.out.println(values);
					writeToFile(data, path);
					System.out.println("Out put is written to text file");
				}

				/*
				 * String FirstColumn = rs.getString(1); String NextColumn =
				 * rs.getString(2); System.
				 * out.println(FirstColumn+"  "+NextColumn);
				 * data.add(FirstColumn + " " + NextColumn ); writeToFile(data,
				 * path); System.out.println("Out put is written to text file");
				 */
			}
			// closing DB Connection
			con.close();

		}
		return strstatus;
	}

	private static void writeToFile(java.util.List<String> list, String path) {

		BufferedWriter out = null;
		try {
			File file = new File(path);
			out = new BufferedWriter(new FileWriter(file, true));
			for (String s : list) {
				out.write(s);
				out.newLine();

			}
			out.close();
		} catch (IOException e) {
		}
	}
}
