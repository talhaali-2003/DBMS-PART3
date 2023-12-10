<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="DAO.BillsDAO"%>
<%@ page import="Model.Bills"%>
<%@ page import="java.util.List"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="Helper.ConnectionProvider"%>
<%@ page import="Model.Users"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Manage Bills</title>
<!-- Include your CSS stylesheets here -->
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>
	<div>
		<h2>Manage Bills</h2>

		<!-- Display all bills -->
		<table border="1">
			<thead>
				<tr>
					<th>ID</th>
					<th>Order ID</th>
					<th>Price</th>
					<th>Discount</th>
					<th>Balance</th>
					<th>Status</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody>
				<%
				BillsDAO billsDAO = new BillsDAO(ConnectionProvider.getConnection());
				List<Bills> allBills = billsDAO.getAllBills(); // 0 or any specific order ID
				for (Bills bill : allBills) {
				%>
				<tr>
					<td><%=bill.getId()%></td>
					<td><%=bill.getOrderid()%></td>
					<td><%=bill.getPrice()%></td>
					<td><%=bill.getDiscount()%></td>
					<td><%=bill.getBalance()%></td>
					<td><%=bill.getStatus()%></td>
					<td> 
						| <a href="processBill.jsp?id=<%=bill.getId()%>&action=cancel">Cancel</a></td>
				</tr>
				<%
				}
				if (allBills.isEmpty()) {
				%>
				<tr>
					<td colspan="7">No bills available.</td>
				</tr>
				<%
				}
				%>
			</tbody>
		</table>
	</div>
</body>
</html>
