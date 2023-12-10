package Servlete;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Helper.ConnectionProvider;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Database login logic
        try (Connection connection = ConnectionProvider.getConnection()) {
            String query = "SELECT * FROM Users_ WHERE email=? AND password=?";
            try (PreparedStatement pstmt = connection.prepareStatement(query)) {
                pstmt.setString(1, email);
                pstmt.setString(2, password);

                ResultSet resultSet = pstmt.executeQuery();

                if (resultSet.next()) {
                    // Login successful
                    HttpSession session = request.getSession();
                    session.setAttribute("email", email);

                    response.sendRedirect("dashboard.jsp");
                } else {
                    // Login failed
                    response.sendRedirect("login.jsp?error=true");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
