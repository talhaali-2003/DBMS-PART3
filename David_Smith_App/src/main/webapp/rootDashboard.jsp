<!-- dashboard.jsp -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="DAO.QuotesDAO"%>
<%@ page import="DAO.BillsDAO"%>
<%@ page import="Model.Quotes"%>
<%@ page import="Model.Bills"%>
<%@ page import="DAO.QuotesDAO"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="Helper.ConnectionProvider"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Root User Dashboard</title>
<!-- Include Bootstrap CSS -->
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>
	<div class="container mt-5">
		<h2>David Smith Dashboard</h2>

		<!-- Display most recent quote -->
		<div class="mt-4">
			<h3>Most Recent Quote</h3>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>ID</th>
						<th>Contractor ID</th>
						<th>Client ID</th>
						<th>Price</th>
						<th>Schedule Start</th>
						<th>Schedule End</th>
						<th>Note</th>
						<th>Status</th>
					</tr>
				</thead>
				<tbody>
					<%
					QuotesDAO quotesDAO = new QuotesDAO(ConnectionProvider.getConnection());
					Quotes recentQuote = quotesDAO.getMostRecentQuoteFromDatabase();
					if (recentQuote != null) {
					%>
					<tr>
						<td><%=recentQuote.getId()%></td>
						<td><%=recentQuote.getContractorid()%></td>
						<td><%=recentQuote.getClientid()%></td>
						<td><%=recentQuote.getPrice()%></td>
						<td><%=recentQuote.getSchedulestart()%></td>
						<td><%=recentQuote.getScheduleend()%></td>
						<td><%=recentQuote.getNote()%></td>
						<td><%=recentQuote.getStatus()%></td>
					</tr>
					<%
					} else {
					%>
					<tr>
						<td colspan="8">No recent quotes.</td>
					</tr>
					<%
					}
					%>
				</tbody>
			</table>
		</div>

		<!-- Display most recent bill -->
		<div class="mt-4">
			<h3>Most Recent Bill</h3>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>ID</th>
						<th>Order ID</th>
						<th>Price</th>
						<th>Discount</th>
						<th>Balance</th>
						<th>Status</th>
					</tr>
				</thead>
				<tbody>
					<%
					BillsDAO billsDAO = new BillsDAO(ConnectionProvider.getConnection());
					Bills recentBill = billsDAO.getMostRecentBillFromDatabase();
					if (recentBill != null) {
					%>
					<tr>
						<td><%=recentBill.getId()%></td>
						<td><%=recentBill.getOrderid()%></td>
						<td><%=recentBill.getPrice()%></td>
						<td><%=recentBill.getDiscount()%></td>
						<td><%=recentBill.getBalance()%></td>
						<td><%=recentBill.getStatus()%></td>
					</tr>
					<%
					} else {
					%>
					<tr>
						<td colspan="6">No recent bills.</td>
					</tr>
					<%
					}
					%>
				</tbody>
			</table>
		</div>

		<!-- Add links or buttons for different sections -->
		<div class="mt-4">
			<a href="incomingRequests.jsp" class="btn btn-primary">Incoming
				Requests</a> 
                    <a href="manageOrders.jsp" class="btn btn-primary">Manage
				Orders</a>
                    <a href="manageBills.jsp" class="btn btn-primary">Manage
				Bills</a>
                    <a href="specifiedfunctionalities.jsp" class="btn btn-primary">Specified
				functionalities</a>
		</div>
	</div>



	<!-- Include Bootstrap JS and jQuery -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
