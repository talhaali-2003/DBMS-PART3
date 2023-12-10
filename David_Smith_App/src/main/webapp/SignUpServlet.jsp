<%@ page import="java.sql.*"%>
<%@ page import="Helper.ConnectionProvider"%>


<%
// Get form data
String firstname = request.getParameter("firstname");
String lastname = request.getParameter("lastname");
String creditcard = request.getParameter("creditcard");
String email = request.getParameter("email");
String password = request.getParameter("password");

// Insert data into the database
try {
	Connection con = ConnectionProvider.getConnection();
	String query = "INSERT INTO Users (firstname, lastname, creditcard, email, password) VALUES (?, ?, ?, ?, ?)";
	try (PreparedStatement pstmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
		pstmt.setString(1, firstname);
		pstmt.setString(2, lastname);
		pstmt.setString(3, creditcard);
		pstmt.setString(4, email);
		pstmt.setString(5, password);
		pstmt.executeUpdate();

		// Retrieve the generated user ID
		try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
	if (generatedKeys.next()) {
		int userId = generatedKeys.getInt(1);
		out.println("User ID: " + userId);
		// You can store the userId in a session or use it as needed
	}
		}
	}
	con.close();
} catch (SQLException e) {
	e.printStackTrace();
	// Handle the exception as needed
}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<title>User Signup</title>
</head>
<body>

	<div class="container mt-5">
		<h2 class="mb-4">User Signup</h2>
		<div class="alert alert-success" role="alert">Signup successful!
			Thank you for registering.</div>
	</div>

	<!-- Bootstrap JS and jQuery -->
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

</body>
</html>
