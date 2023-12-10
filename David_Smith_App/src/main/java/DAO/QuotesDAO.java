package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Helper.ConnectionProvider;
import Model.Quotes;

public class QuotesDAO {

	private Connection con;

	public QuotesDAO(Connection con) {
		this.con = con;
	}
	

	
	// Create a new quote
	public boolean createQuote(Quotes quote) {
		try {
			String query = "INSERT INTO Quotes (contractorid, clientid, price, schedulestart, scheduleend) VALUES (?, ?, ?, ?, ?)";
			try (PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
				ps.setInt(1, quote.getContractorid());
				ps.setInt(2, quote.getClientid());
				ps.setDouble(3, quote.getPrice());
				ps.setDate(4, new java.sql.Date(quote.getSchedulestart().getTime()));
				ps.setDate(5, new java.sql.Date(quote.getScheduleend().getTime()));

				int rowsAffected = ps.executeUpdate();

				if (rowsAffected > 0) {
					// Retrieve the auto-generated key (quote id)
					ResultSet rs = ps.getGeneratedKeys();
					if (rs.next()) {
						quote.setId(rs.getInt(1));
					}
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public Quotes getMostRecentQuoteFromDatabase() {
		try {
			String query = "SELECT * FROM Quotes ORDER BY id DESC LIMIT 1";
			try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
				if (rs.next()) {
					Quotes recentQuote = new Quotes();
					recentQuote.setId(rs.getInt("id"));
					recentQuote.setContractorid(rs.getInt("contractorid"));
					recentQuote.setClientid(rs.getInt("clientid"));
					recentQuote.setPrice(rs.getDouble("price"));
					recentQuote.setSchedulestart(rs.getDate("schedulestart"));
					recentQuote.setScheduleend(rs.getDate("scheduleend"));
					recentQuote.setNote(rs.getString("note"));
					recentQuote.setStatus(rs.getString("status"));
					return recentQuote;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	// Retrieve a quote by id
	public Quotes getQuoteById(int id) {
		Quotes quote = null;
		try {
			String query = "SELECT * FROM Quotes WHERE id=?";
			try (PreparedStatement ps = con.prepareStatement(query)) {
				ps.setInt(1, id);

				try (ResultSet rs = ps.executeQuery()) {
					if (rs.next()) {
						quote = new Quotes();
						quote.setId(rs.getInt("id"));
						quote.setContractorid(rs.getInt("contractorid"));
						quote.setClientid(rs.getInt("clientid"));
						quote.setPrice(rs.getDouble("price"));
						quote.setSchedulestart(rs.getDate("schedulestart"));
						quote.setScheduleend(rs.getDate("scheduleend"));
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return quote;
	}

	// Update quote details
	public boolean updateQuote(String status, int id) {
		try {
			String query = "UPDATE Quotes SET status=? WHERE id=?";
			try (PreparedStatement ps = con.prepareStatement(query)) {
				ps.setString(1, status);

				ps.setInt(2, id);

				int rowsAffected = ps.executeUpdate();

				return rowsAffected > 0;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	// Delete a quote by id
	public boolean deleteQuoteById(int id) {
		try {
			String query = "DELETE FROM Quotes WHERE id=?";
			try (PreparedStatement ps = con.prepareStatement(query)) {
				ps.setInt(1, id);

				int rowsAffected = ps.executeUpdate();

				return rowsAffected > 0;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	// Retrieve all quotes
	public List<Quotes> getAllQuotes() {
		List<Quotes> quoteList = new ArrayList<>();
		try {
			String query = "SELECT * FROM Quotes";
			try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
				while (rs.next()) {
					Quotes quote = new Quotes();
					quote.setId(rs.getInt("id"));
					quote.setContractorid(rs.getInt("contractorid"));
					quote.setClientid(rs.getInt("clientid"));
					quote.setPrice(rs.getDouble("price"));
					quote.setSchedulestart(rs.getDate("schedulestart"));
					quote.setStatus(rs.getString("status"));
					quote.setNote(rs.getString("note"));

					quoteList.add(quote);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return quoteList;
	}

	// Retrieve quotes by user ID
	public List<Quotes> getQuotesByUserId(int userId) {
		List<Quotes> quoteList = new ArrayList<>();
		try {
			String query = "SELECT * FROM Quotes WHERE clientid=?";
			try (PreparedStatement ps = con.prepareStatement(query)) {
				ps.setInt(1, userId);

				try (ResultSet rs = ps.executeQuery()) {
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

						quoteList.add(quote);
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return quoteList;
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

}
