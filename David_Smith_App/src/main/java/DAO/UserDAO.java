package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Model.ClientStatistics;
import Model.Users;

public class UserDAO {

    private Connection con;

    public UserDAO(Connection con) {
        this.con = con;
    }

    // Create a new user
    public boolean createUser(Users user) {
        try {
            String query = "INSERT INTO Users (firstname, lastname, creditcard, email) VALUES (?, ?, ?, ?)";
            try ( PreparedStatement ps = con.prepareStatement(query)) {
                ps.setString(1, user.getFirstname());
                ps.setString(2, user.getLastname());
                ps.setString(3, user.getCreditcard());
                ps.setString(4, user.getEmail());

                int rowsAffected = ps.executeUpdate();

                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Retrieve a user by email
    public Users getUserByEmail(String email, String password) {
        Users user = null;
        try {
            String query = "SELECT * FROM Users WHERE email=?  AND password=?";
            try ( PreparedStatement ps = con.prepareStatement(query)) {
                ps.setString(1, email);
                ps.setString(2, password);

                try ( ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        user = new Users();
                        user.setId(rs.getInt("id"));
                        user.setFirstname(rs.getString("firstname"));
                        user.setLastname(rs.getString("lastname"));
                        user.setCreditcard(rs.getString("creditcard"));
                        user.setEmail(rs.getString("email"));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    // Update user details
    public boolean updateUser(Users user) {
        try {
            String query = "UPDATE Users SET firstname=?, lastname=?, creditcard=? WHERE email=?";
            try ( PreparedStatement ps = con.prepareStatement(query)) {
                ps.setString(1, user.getFirstname());
                ps.setString(2, user.getLastname());
                ps.setString(3, user.getCreditcard());
                ps.setString(4, user.getEmail());

                int rowsAffected = ps.executeUpdate();

                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Delete a user by email
    public boolean deleteUserByEmail(String email) {
        try {
            String query = "DELETE FROM Users WHERE email=?";
            try ( PreparedStatement ps = con.prepareStatement(query)) {
                ps.setString(1, email);

                int rowsAffected = ps.executeUpdate();

                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Retrieve all users
    public List<Users> getAllUsers() {
        List<Users> userList = new ArrayList<>();
        try {
            String query = "SELECT * FROM Users";
            try ( Statement stmt = con.createStatement();  ResultSet rs = stmt.executeQuery(query)) {
                while (rs.next()) {
                    Users user = new Users();
                    user.setId(rs.getInt("id"));
                    user.setFirstname(rs.getString("firstname"));
                    user.setLastname(rs.getString("lastname"));
                    user.setCreditcard(rs.getString("creditcard"));
                    user.setEmail(rs.getString("email"));

                    userList.add(user);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }
    ////////

    // Method to get statistics for each client
    public List<ClientStatistics> getClientStatistics() {
        List<ClientStatistics> clientStatistics = new ArrayList<>();

        try {
            // Define your SQL query for client statistics
            // Example: Retrieve client statistics with the number of trees, total due, and
            // total paid
            String query = "SELECT c.id, c.firstname, c.lastname, COUNT(t.id) AS tree_count, "
                    + "SUM(b.price) AS total_due, SUM(b.balance) AS total_paid " + "FROM Users c "
                    + "LEFT JOIN Quotes q ON q.clientid = c.id " + "LEFT JOIN Trees t ON t.quoteid = q.id "
                    + "LEFT JOIN Orders o ON o.quoteid = q.id " + "LEFT JOIN Bills b ON b.orderid = o.id "
                    + "GROUP BY c.id";

            try ( Statement stmt = con.createStatement();  ResultSet rs = stmt.executeQuery(query)) {
                while (rs.next()) {
                    ClientStatistics statistics = new ClientStatistics();
                    statistics.setClientId(rs.getInt("id"));
                    statistics.setFirstName(rs.getString("firstname"));
                    statistics.setLastName(rs.getString("lastname"));
                    statistics.setTreeCount(rs.getInt("tree_count"));
                    statistics.setTotalDue(rs.getDouble("total_due"));
                    statistics.setTotalPaid(rs.getDouble("total_paid"));

                    clientStatistics.add(statistics);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clientStatistics;
    }

// Method to get easy clients
    public List<Users> getEasyClients() {
        List<Users> easyClients = new ArrayList<>();

        try {
            // Define your SQL query based on the criteria for easy clients
            // Assuming the status 'accept' indicates the client accepted the quote
            String query = "SELECT u.id, u.firstname, u.lastname, u.creditcard, u.email "
                    + "FROM Users u "
                    + "JOIN Quotes q ON u.id = q.clientid "
                    + "WHERE q.status = 'accept'";

            try ( Statement stmt = con.createStatement();  ResultSet rs = stmt.executeQuery(query)) {
                while (rs.next()) {
                    Users user = new Users();
                    user.setId(rs.getInt("id"));
                    user.setFirstname(rs.getString("firstname"));
                    user.setLastname(rs.getString("lastname"));
                    user.setCreditcard(rs.getString("creditcard"));
                    user.setEmail(rs.getString("email"));

                    easyClients.add(user);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return easyClients;
    }

    // Method to get big clients
    // Method to get big clients based on the number of trees cut
    public List<Users> getBigClients() {
        List<Users> bigClients = new ArrayList<>();

        try {
            // Define your SQL query based on the number of trees cut
            String query = "SELECT u.id, u.firstname, u.lastname, u.creditcard, u.email, COUNT(t.id) as treeCount "
                    + "FROM Users u "
                    + "JOIN Trees t ON u.id = t.quoteid "
                    + "GROUP BY u.id "
                    + "ORDER BY treeCount DESC";

            try ( Statement stmt = con.createStatement();  ResultSet rs = stmt.executeQuery(query)) {
                while (rs.next()) {
                    Users user = new Users();
                    user.setId(rs.getInt("id"));
                    user.setFirstname(rs.getString("firstname"));
                    user.setLastname(rs.getString("lastname"));
                    user.setCreditcard(rs.getString("creditcard"));
                    user.setEmail(rs.getString("email"));

                    bigClients.add(user);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bigClients;
    }

}
