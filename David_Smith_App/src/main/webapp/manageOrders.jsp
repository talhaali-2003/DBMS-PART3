<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="DAO.OrdersDAO"%>
<%@ page import="Model.Orders"%>
<%@ page import="java.util.List"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="Helper.ConnectionProvider"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Manage Orders</title>
<!-- Include Bootstrap CSS -->
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>
	<div class="container mt-5">
		<h2>Manage Orders</h2>

		<!-- Display orders from the database -->
		<%
		OrdersDAO ordersDAO = new OrdersDAO(ConnectionProvider.getConnection());
		List<Orders> orderList = ordersDAO.getAllOrders();

		if (!orderList.isEmpty()) {
		%>
		<table class="table table-bordered mt-4">
			<thead>
				<tr>
					<th>ID</th>
					<th>Quote ID</th>
					<th>Price</th>
					<th>Schedule Start</th>
					<th>Schedule End</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody>
				<%
				for (Orders order : orderList) {
				%>
				<tr>
					<td><%=order.getId()%></td>
					<td><%=order.getQuoteid()%></td>
					<td><%=order.getPrice()%></td>
					<td><%=order.getSchedulestart()%></td>
					<td><%=order.getScheduleend()%></td>
					<td><a href="deleteOrder.jsp?id=<%=order.getId()%>"
						class="btn btn-danger btn-sm">Delete</a></td>
				</tr>
				<%
				}
				%>
			</tbody>
		</table>
		<%
		} else {
		%>
		<p>No orders found in the database.</p>
		<%
		}
		%>

		<!-- Add a link or button to go back to the dashboard or another page -->
		<div class="mt-4">
			<a href="rootDashboard.jsp" class="btn btn-primary">Back to
				Dashboard</a>
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
