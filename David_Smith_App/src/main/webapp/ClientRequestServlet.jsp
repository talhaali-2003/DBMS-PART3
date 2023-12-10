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

// Get form data
double initialPrice = Double.parseDouble(request.getParameter("initialPrice"));
String timeWindow = request.getParameter("timeWindow");
String note = request.getParameter("note");

// Get current date and time for schedulestart
java.util.Date schedulestart = new java.util.Date();

// Create a Quotes object with the form data
Quotes quote = new Quotes();
quote.setContractorid(user.getId()); // Assuming contractor ID is the user's ID
quote.setClientid(user.getId()); // Assuming client ID is the user's ID
quote.setPrice(initialPrice);

java.util.Date scheduleend = new java.util.Date(); // Default value or retrieve from form
quote.setScheduleend(scheduleend);

quote.setSchedulestart(schedulestart);
quote.setTimeWindow(timeWindow);
quote.setNote(note);
quote.setStatus("pending"); // Default status

// Create a QuotesDAO instance and call the createQuote method
QuotesDAO quotesDAO = new QuotesDAO(ConnectionProvider.getConnection());
boolean quoteCreated = quotesDAO.createQuote(quote);

if (quoteCreated) {
%>

<h2>Quot Submiteed Succesfully</h2>


<%
} else {
// If there is an issue, you might want to redirect to an error page
response.sendRedirect("quote_error.jsp");
}
%>
