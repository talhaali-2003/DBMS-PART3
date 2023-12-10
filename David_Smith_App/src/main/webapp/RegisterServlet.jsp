<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
	<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="Helper.ConnectionProvider" %>




<%
String userName = request.getParameter("userName");
String userRole = request.getParameter("userRole");
String email = request.getParameter("email");
String password = request.getParameter("password");

// Database insert logic
try {
	Connection connection = ConnectionProvider.getConnection();
	String query = "INSERT INTO users2 (username, userrole, email, password) VALUES (?, ?, ?, ?)";
	try (PreparedStatement pstmt = connection.prepareStatement(query)) {
		pstmt.setString(1, userName);
		pstmt.setString(2, userRole);
		pstmt.setString(3, email);
		pstmt.setString(4, password);

		int rowsAffected = pstmt.executeUpdate();

		if (rowsAffected > 0) {
	// Registration successful
	response.setContentType("text/html");
 
	out.println("<html><body>");
	out.println("<h2>Registration Successful!</h2>");
	out.println("</body></html>");
		} else {
	// Registration failed
	response.sendRedirect("registration.jsp?error=true");
		}
	}
} catch (Exception e) {
	e.printStackTrace();
}
%>
