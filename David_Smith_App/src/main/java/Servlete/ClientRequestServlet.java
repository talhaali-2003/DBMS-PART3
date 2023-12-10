// ClientRequestServlet.java
package Servlete;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Helper.ConnectionProvider;

@WebServlet("/ClientRequestServlet")
public class ClientRequestServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String clientName = request.getParameter("clientName");
        String clientAddress = request.getParameter("clientAddress");
        String clientEmail = request.getParameter("clientEmail");
        String treeDetails = request.getParameter("treeDetails");

        // Perform any validation or additional processing if needed

        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            // Get a database connection
            con = ConnectionProvider.getConnection();

            // Prepare the SQL statement
            String query = "INSERT INTO Users (firstname, lastname, email) VALUES (?, ?, ?)";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, clientName);
            pstmt.setString(2, clientAddress);
            pstmt.setString(3, clientEmail);

            // Execute the query
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                // Registration successful
                response.sendRedirect("success.jsp"); // Redirect to a success page
            } else {
                // Registration failed
                response.sendRedirect("failure.jsp"); // Redirect to a failure page
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle database-related errors
            response.sendRedirect("error.jsp"); // Redirect to an error page
        } finally {
            // Close resources
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
