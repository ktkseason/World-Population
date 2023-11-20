package main;

import java.sql.*;

public class Check {

	public boolean checkMenuOption(String input) {
		return switch (input) {
			case "1", "2", "3", "4", "5" -> true;
			default -> {
				System.out.println("Enter only number between 1 and 5.");
				yield false;
			}
		};
	}
	public boolean checkReportOption(String input) {
		return switch (input) {
			case "1", "2" -> true;
			default -> {
				System.out.println("Enter only number 1 or 2.");
				yield false;
			}
		};
	}
	public boolean checkYear(String input) {
		return switch (input) {
			case "2020", "2021", "2022", "2023" -> true;
			default -> {
				System.out.println("Please choose year 2020, 2021, 2022 or 2023.");
				yield false;
			}
		};
	}
	public boolean checkISO(String input) {
		Connection conn = DBConnect.getConn();
		try {
			PreparedStatement selectStatement = conn.prepareStatement("SELECT iso_name FROM population");
			ResultSet resultSet = selectStatement.executeQuery();

			while (resultSet.next()) {
				if(resultSet.getString("iso_name").equalsIgnoreCase(input)) {
					return true;
				}
			}
			resultSet.close();
			selectStatement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Please enter correct ISO name in three letters.");
		return false;
	}
	public boolean checkColumn(String input) throws Exception {
		Connection conn = DBConnect.getConn();
		try {
			PreparedStatement selectStatement = conn.prepareStatement("SELECT * FROM population");
			ResultSet resultSet = selectStatement.executeQuery();
			ResultSetMetaData rsMetaData = resultSet.getMetaData();
			int count = rsMetaData.getColumnCount();

			for (int i = 1; i <= count; i++) {
				if(rsMetaData.getColumnName(i).equalsIgnoreCase(input)) {
					throw new Exception("========================================================\n" +
										"                Column is already added.                \n" +
										"========================================================");
				}
			}
			resultSet.close();
			selectStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
}
