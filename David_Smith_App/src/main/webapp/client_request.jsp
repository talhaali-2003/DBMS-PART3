<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="Model.Quotes"%>
<%@ page import="DAO.QuotesDAO"%>
<%@ page import="Helper.ConnectionProvider"%>
<%@ page import="Model.Users"%>

<%
// Check if the user is logged in
Users user = (Users) session.getAttribute("user");
if (user == null) {
	// If the user is not logged in, redirect to the login page
	response.sendRedirect("userlogin.jsp");
}
%>

<!-- client_request.jsp -->
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Client's Request for Quote</title>
<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>

	<%@include file="clientNavbar.jsp"%>
	<div class="container mt-5">
		<h2>Submit Request for Quote</h2>
		<form action="ClientRequestServlet.jsp" method="post">
			<!-- Display user information -->
			<p>
				Welcome,
				<%=user.getFirstname()%>
				<%=user.getLastname()%></p>
			<p>
				Email:
				<%=user.getEmail()%></p>

			<!-- Additional fields for quote request -->
			<div class="mb-3">
				<label for="initialPrice" class="form-label">Initial Price:</label>
				<input type="text" class="form-control" name="initialPrice" required>
			</div>

			<div class="mb-3">
				<label for="timeWindow" class="form-label">Time Window:</label> <input
					type="date" class="form-control" name="timeWindow" required>
			</div>

			<div class="mb-3">
				<label for="note" class="form-label">Note:</label>
				<textarea class="form-control" name="note" rows="4"></textarea>
			</div>

			<button type="submit" class="btn btn-primary">Register a
				Quote</button>
		</form>
	</div>

	<!-- Bootstrap JS and dependencies -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
