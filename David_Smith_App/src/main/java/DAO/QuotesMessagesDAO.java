package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Model.Quotes;
import Model.QuotesMessages;
import Helper.ConnectionProvider;

public class QuotesMessagesDAO {

	private Connection con;

	public QuotesMessagesDAO(Connection con) {
		this.con = con;
	}

	// Create a new quotes message entry
	public boolean createQuotesMessage(QuotesMessages quotesMessage) {
		try {
			String query = "INSERT INTO QuotesMessages (userid, quoteid, msgtime, price, schedulestart, scheduleend, note) VALUES (?, ?, ?, ?, ?, ?, ?)";
			try (PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
				ps.setInt(1, quotesMessage.getUserid());
				ps.setInt(2, quotesMessage.getQuoteid());
				ps.setTimestamp(3, new java.sql.Timestamp(quotesMessage.getMsgtime().getTime()));
				ps.setDouble(4, quotesMessage.getPrice());
				ps.setTimestamp(5, new java.sql.Timestamp(quotesMessage.getSchedulestart().getTime()));
				ps.setTimestamp(6, new java.sql.Timestamp(quotesMessage.getScheduleend().getTime()));
				ps.setString(7, quotesMessage.getNote());

				int rowsAffected = ps.executeUpdate();

				if (rowsAffected > 0) {
					// Retrieve the auto-generated key (quotes message id)
					ResultSet rs = ps.getGeneratedKeys();
					if (rs.next()) {
						quotesMessage.setId(rs.getInt(1));
					}
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	
	// Retrieve a quotes message by id
	public QuotesMessages getQuotesMessageById(int id) {
		QuotesMessages quotesMessage = null;
		try {
			String query = "SELECT * FROM QuotesMessages WHERE id=?";
			try (PreparedStatement ps = con.prepareStatement(query)) {
				ps.setInt(1, id);

				try (ResultSet rs = ps.executeQuery()) {
					if (rs.next()) {
						quotesMessage = new QuotesMessages();
						quotesMessage.setId(rs.getInt("id"));
						quotesMessage.setUserid(rs.getInt("userid"));
						quotesMessage.setQuoteid(rs.getInt("quoteid"));
						quotesMessage.setMsgtime(rs.getTimestamp("msgtime"));
						quotesMessage.setPrice(rs.getDouble("price"));
						quotesMessage.setSchedulestart(rs.getTimestamp("schedulestart"));
						quotesMessage.setScheduleend(rs.getTimestamp("scheduleend"));
						quotesMessage.setNote(rs.getString("note"));
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return quotesMessage;
	}

	// Update quotes message details
	public boolean updateQuotesMessage(QuotesMessages quotesMessage) {
		try {
			String query = "UPDATE QuotesMessages SET userid=?, quoteid=?, msgtime=?, price=?, schedulestart=?, scheduleend=?, note=? WHERE id=?";
			try (PreparedStatement ps = con.prepareStatement(query)) {
				ps.setInt(1, quotesMessage.getUserid());
				ps.setInt(2, quotesMessage.getQuoteid());
				ps.setTimestamp(3, new java.sql.Timestamp(quotesMessage.getMsgtime().getTime()));
				ps.setDouble(4, quotesMessage.getPrice());
				ps.setTimestamp(5, new java.sql.Timestamp(quotesMessage.getSchedulestart().getTime()));
				ps.setTimestamp(6, new java.sql.Timestamp(quotesMessage.getScheduleend().getTime()));
				ps.setString(7, quotesMessage.getNote());
				ps.setInt(8, quotesMessage.getId());

				int rowsAffected = ps.executeUpdate();

				return rowsAffected > 0;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	// Delete a quotes message by id
	public boolean deleteQuotesMessageById(int id) {
		try {
			String query = "DELETE FROM QuotesMessages WHERE id=?";
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

	// Retrieve all quotes messages for a specific quote
	public List<QuotesMessages> getAllQuotesMessagesForQuote(int quoteid) {
		List<QuotesMessages> quotesMessageList = new ArrayList<>();
		try {
			String query = "SELECT * FROM QuotesMessages WHERE quoteid=?";
			try (PreparedStatement ps = con.prepareStatement(query)) {
				ps.setInt(1, quoteid);

				try (ResultSet rs = ps.executeQuery()) {
					while (rs.next()) {
						QuotesMessages quotesMessage = new QuotesMessages();
						quotesMessage.setId(rs.getInt("id"));
						quotesMessage.setUserid(rs.getInt("userid"));
						quotesMessage.setQuoteid(rs.getInt("quoteid"));
						quotesMessage.setMsgtime(rs.getTimestamp("msgtime"));
						quotesMessage.setPrice(rs.getDouble("price"));
						quotesMessage.setSchedulestart(rs.getTimestamp("schedulestart"));
						quotesMessage.setScheduleend(rs.getTimestamp("scheduleend"));
						quotesMessage.setNote(rs.getString("note"));

						quotesMessageList.add(quotesMessage);
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return quotesMessageList;
	}
}
