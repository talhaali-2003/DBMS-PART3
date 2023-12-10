package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Model.ClientStatistics;
import Model.Quotes;
import Model.Users;


public class SpecifiedFunctionalitiesDAO {

	private Connection con;

	public SpecifiedFunctionalitiesDAO(Connection con) {
		this.con = con;
	}

	// Method to get big clients
	public List<Users> getBigClients() {
		List<Users> bigClients = new ArrayList<>();

		try {
			// Define your SQL query based on the criteria for big clients
			// Example: Retrieve clients with a credit card limit greater than 5000
			String query = "SELECT * FROM Users WHERE creditcard IS NOT NULL AND LENGTH(creditcard) > 5000";

			try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
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

	// Method to get easy clients
	public List<Users> getEasyClients() {
		List<Users> easyClients = new ArrayList<>();

		try {
			// Define your SQL query based on the criteria for easy clients
			// Example: Retrieve clients with a credit card limit less than or equal to 1000
			String query = "SELECT * FROM Users WHERE creditcard IS NOT NULL AND LENGTH(creditcard) <= 1000";

			try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
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

	// Method to get one tree quotes
	public List<Quotes> getOneTreeQuotes() {
		List<Quotes> oneTreeQuotes = new ArrayList<>();

		try {
			// Define your SQL query for one tree quotes
			// Example: Retrieve quotes with only one tree
			String query = "SELECT q.* FROM Quotes q LEFT JOIN Trees t ON q.id = t.quoteid GROUP BY q.id HAVING COUNT(t.id) = 1";

			try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
				while (rs.next()) {
					Quotes quote = new Quotes();
					quote.setId(rs.getInt("id"));
					quote.setContractorid(rs.getInt("contractorid"));
					quote.setClientid(rs.getInt("clientid"));
					quote.setPrice(rs.getDouble("price"));
					quote.setSchedulestart(rs.getDate("schedulestart"));
					quote.setScheduleend(rs.getDate("scheduleend"));
					quote.setNote(rs.getString("note"));
					quote.setStatus(rs.getString("status"));

					oneTreeQuotes.add(quote);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return oneTreeQuotes;
	}

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

			try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
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
// Method to get good clients (paid bills within 24 hours)
// Method to get good clients
public List<Users> getGoodClients() {
    List<Users> goodClients = new ArrayList<>();

    try {
        // Define your SQL query based on the criteria for good clients
        // Assuming the payment is considered timely if made within 24 hours of bill generation
        String query = "SELECT u.id, u.firstname, u.lastname, u.creditcard, u.email "
                + "FROM users u "
                + "JOIN orders o ON o.clientid = u.id "
                + "JOIN bills b ON b.orderid = o.id "
                + "WHERE TIMESTAMPDIFF(HOUR, b.generated_at, NOW()) <= 24";

        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Users user = new Users();
                user.setId(rs.getInt("id"));
                user.setFirstname(rs.getString("firstname"));
                user.setLastname(rs.getString("lastname"));
                user.setCreditcard(rs.getString("creditcard"));
                user.setEmail(rs.getString("email"));

                goodClients.add(user);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return goodClients;
}

// Method to get the total number of clients
public int getTotalClients() {
    int totalClients = 0;

    try {
        String query = "SELECT COUNT(*) FROM Users";
        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                totalClients = rs.getInt(1);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return totalClients;
}

// Method to get the total number of quotes
public int getTotalQuotes() {
    int totalQuotes = 0;

    try {
        String query = "SELECT COUNT(*) FROM Quotes";
        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                totalQuotes = rs.getInt(1);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return totalQuotes;
}

}
