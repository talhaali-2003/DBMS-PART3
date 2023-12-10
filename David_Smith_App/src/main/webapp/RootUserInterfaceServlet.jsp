<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ page import="Helper.ConnectionProvider"%>

<%
String username = request.getParameter("username");
String password = request.getParameter("password");

// Declare methods using <%!
%>
<%!
boolean validateCredentials(String username, String password) {
		// Check if the provided username and password are correct
		return username.equals("root") && password.equals("pass1234");
	}

	void deleteExistingTables() {
		// Implement logic to delete existing tables
		// You may use DROP TABLE IF EXISTS statement

		try (Connection connection = ConnectionProvider.getConnection();
				Statement statement = connection.createStatement()) {

			// Example: Drop each table if it exists
			String[] tableNames = { "Users", "Quotes", "Trees", "QuotesMessages", "Orders", "Bills", "BillsMessages" };

			for (String tableName : tableNames) {
				String dropTableQuery = "DROP TABLE IF EXISTS " + tableName;
				statement.executeUpdate(dropTableQuery);
				System.out.println("Dropped table: " + tableName);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	void createTables() {
		// Implement logic to create necessary tables
		// You may use CREATE TABLE statements

		try (Connection connection = ConnectionProvider.getConnection();
				Statement statement = connection.createStatement()) {

			// Example: Create Users table
			String createUsersTableQuery = "CREATE TABLE IF NOT EXISTS Users (" + "id INTEGER PRIMARY KEY,"
					+ "firstname VARCHAR(50)," + "lastname VARCHAR(50)," + "creditcard CHAR(16),"
					+ "email VARCHAR(50) UNIQUE" + ")";
			statement.executeUpdate(createUsersTableQuery);
			System.out.println("Created Users table");

			// Repeat the above process for other tables (Quotes, Trees, QuotesMessages,
			// Orders, Bills, BillsMessages)

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	void insertData() {
		try (Connection connection = ConnectionProvider.getConnection();
				Statement statement = connection.createStatement()) {

			// Example data for user insertion
			for (int i = 1; i <= 10; i++) {
				String insertUsersDataQuery = "INSERT INTO Users VALUES (" + i + ", 'FirstName" + i + "', 'LastName" + i
						+ "', 'CreditCard" + i + "', 'email" + i + "@example.com')";
				statement.executeUpdate(insertUsersDataQuery);
			}

			// Repeat the above process for other tables
			for (String tableName : new String[] { "Quotes", "Trees", "QuotesMessages", "Orders", "Bills",
					"BillsMessages" }) {
				for (int i = 1; i <= 10; i++) {
					String insertDataQuery = "INSERT INTO " + tableName + " VALUES (" + i + ", ...)";
					// Complete the values based on the structure of each table
					statement.executeUpdate(insertDataQuery);
				}
			}

			System.out.println("Inserted data into all tables");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}%>
<%
// Main logic using <%
if (validateCredentials(username, password)) {
	// Call methods to delete existing tables, create necessary tables, and insert data
	deleteExistingTables();
	createTables();
	insertData();
%>
<!DOCTYPE html>
<html>
<head>
<title>Database Initialized Successfully!</title>
</head>
<body>
	<h2>Database Initialized Successfully!</h2>
</body>
</html>
<%
} else {
%>

<html>
<head>
<title>Invalid Credentials</title>
</head>
<body>
	<h2>Invalid username or password!</h2>
</body>
</html>
<%
}
%>
