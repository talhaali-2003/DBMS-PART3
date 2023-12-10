<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.*,javax.servlet.http.*"%>
<%@ page import="Model.Users"%>
<%@ page import="DAO.UserDAO"%>
<%@ page import="Helper.ConnectionProvider"%>



<%
String email = request.getParameter("email");
String password = request.getParameter("password");

UserDAO Dao = new UserDAO(ConnectionProvider.getConnection());
Users user = Dao.getUserByEmail(email, password);

if (user != null) {
	// Successful login
	session = request.getSession(true);

	// Store user information in the session
	session.setAttribute("user", user);

	// Redirect to a welcome page or dashboard
	response.sendRedirect("client_request.jsp");
} else {
	// Invalid credentials, redirect back to the login page with an error message
	response.sendRedirect("userlogin.jsp?error=invalid");
}
%>
