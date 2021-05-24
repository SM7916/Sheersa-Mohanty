package com.ibmhq;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GetDBFirstColRecord {
	String strstatus = null;
	List<String> data = new ArrayList<String>();

	public String execute(String dbDetails) throws ClassNotFoundException, SQLException, Exception {

		String Details = "";

		if (dbDetails.contains("#")) {

			Details = dbDetails;
			System.out.println(Details);

			String dbUrl = Details.split("#")[0];
			String uname = Details.split("#")[1];
			String password = Details.split("#")[2];
			String query = Details.split("#")[3];
			String firstColValue = Details.split("#")[4];

			// Load oracle jdbc driver
			Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
			// Create Connection to DB
			Connection con = DriverManager.getConnection(dbUrl, uname, password);

			if (con != null) {
				System.out.println("Connected");
			}
			// Create Statement Object
			Statement stmt = con.createStatement();

			try {
				// Execute the SQL Query. Store results in ResultSet
				ResultSet rs = stmt.executeQuery(query);
				rs.next();

				String strtext = rs.getString(1);
				rs.beforeFirst();
				hqdriver.storedata = strtext;
				System.out.println(strtext);
				firstColValue = strtext;

				strstatus = "Passed%%Expected text: " + hqdriver.stepObject + " text value: " + firstColValue
						+ "is stored";
				return firstColValue;
			} catch (SQLException e) {
				strstatus = "Failed%%" + e.getMessage();
			}

		}
		return strstatus;
	}

}
