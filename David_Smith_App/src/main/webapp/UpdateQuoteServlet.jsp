<%@ page import="java.util.List"%>
<%@ page import="Model.Quotes"%>
<%@ page import="DAO.QuotesDAO"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="Helper.ConnectionProvider"%>

<%
// Get the quote ID and action from the form submission
int quoteId = Integer.parseInt(request.getParameter("quoteId"));
String action = request.getParameter("action");

// Fetch the quote from the database
QuotesDAO quotesDAO = new QuotesDAO(ConnectionProvider.getConnection());
Quotes quote = quotesDAO.getQuoteById(quoteId);

// Update the quote status based on the chosen action
if ("accept".equals(action)) {
	quote.setStatus("accepted");
} else if ("reject".equals(action)) {
	quote.setStatus("rejected");
}

// Update the quote in the database
boolean quoteUpdated = quotesDAO.updateQuote(action, quoteId);
%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Update Quote Status</title>
<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>
	<div class="container mt-5">
		<h2>Update Quote Status</h2>
		<%
		if (quoteUpdated) {
		%>
		<div class="alert alert-success" role="alert">Quote status
			updated successfully!</div>
		<%
		} else {
		%>
		<div class="alert alert-danger" role="alert">Error updating
			quote status.</div>
		<%
		}
		%>
		<a href="quote_management.jsp" class="btn btn-primary">Back to
			Quote Management</a>
	</div>

	<!-- Bootstrap JS and dependencies -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
