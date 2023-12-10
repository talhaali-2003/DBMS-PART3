<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="Helper.ConnectionProvider" %>

<%
String userName = request.getParameter("userName");
String password = request.getParameter("password");

// Database login logic
try {
    Connection connection = ConnectionProvider.getConnection();
    String query = "SELECT * FROM users2 WHERE username = ? AND password = ?";
    try (PreparedStatement pstmt = connection.prepareStatement(query)) {
        pstmt.setString(1, userName);
        pstmt.setString(2, password);

        ResultSet resultSet = pstmt.executeQuery();

        if (resultSet.next()) {
            // Login successful
           response.sendRedirect("rootDashboard.jsp");
        } else {
            // Login failed
            response.sendRedirect("login.jsp?error=true");
        }
    }
} catch (Exception e) {
    e.printStackTrace();
}
%>
