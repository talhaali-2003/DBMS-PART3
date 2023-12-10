package Servlete;

import java.io.IOException;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Helper.ConnectionProvider;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userName = request.getParameter("userName");
		String userRole = request.getParameter("userRole");
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		// Database insert logic
		try (Connection connection = ConnectionProvider.getConnection()) {
			String query = "INSERT INTO Users_ (username, userrole, email, password) VALUES (?, ?, ?, ?)";
			try (PreparedStatement pstmt = connection.prepareStatement(query)) {
				pstmt.setString(1, userName);
				pstmt.setString(2, userRole);
				pstmt.setString(3, email);
				pstmt.setString(4, password);

				int rowsAffected = pstmt.executeUpdate();

				if (rowsAffected > 0) {
					// Registration successful
					response.setContentType("text/html");
					PrintWriter out = response.getWriter();
					out.println("<html><body>");
					out.println("<h2>Registration Successful!</h2>");
					out.println("</body></html>");
				} else {
					// Registration failed
					response.sendRedirect("registration.jsp?error=true");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
