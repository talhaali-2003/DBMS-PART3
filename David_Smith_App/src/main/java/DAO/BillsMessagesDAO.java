package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import Model.BillsMessages;
import Helper.ConnectionProvider;

public class BillsMessagesDAO {

	private Connection con;

	public BillsMessagesDAO(Connection con) {
		this.con = con;
	}

	// Create a new bills message entry
	public boolean createBillsMessage(BillsMessages billsMessage) {
		try {
			String query = "INSERT INTO BillsMessages (userid, billid, msgtime, price, schedulestart, scheduleend, note) VALUES (?, ?, ?, ?, ?, ?, ?)";
			try (PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
				ps.setInt(1, billsMessage.getUserid());
				ps.setInt(2, billsMessage.getBillid());
				ps.setTimestamp(3, new java.sql.Timestamp(billsMessage.getMsgtime().getTime()));
				ps.setDouble(4, billsMessage.getPrice());
				ps.setTimestamp(5, new java.sql.Timestamp(billsMessage.getSchedulestart().getTime()));
				ps.setTimestamp(6, new java.sql.Timestamp(billsMessage.getScheduleend().getTime()));
				ps.setString(7, billsMessage.getNote());

				int rowsAffected = ps.executeUpdate();

				if (rowsAffected > 0) {
					// Retrieve the auto-generated key (bills message id)
					ResultSet rs = ps.getGeneratedKeys();
					if (rs.next()) {
						billsMessage.setId(rs.getInt(1));
					}
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	// Retrieve a bills message by id
	public BillsMessages getBillsMessageById(int id) {
		BillsMessages billsMessage = null;
		try {
			String query = "SELECT * FROM BillsMessages WHERE id=?";
			try (PreparedStatement ps = con.prepareStatement(query)) {
				ps.setInt(1, id);

				try (ResultSet rs = ps.executeQuery()) {
					if (rs.next()) {
						billsMessage = new BillsMessages();
						billsMessage.setId(rs.getInt("id"));
						billsMessage.setUserid(rs.getInt("userid"));
						billsMessage.setBillid(rs.getInt("billid"));
						billsMessage.setMsgtime(rs.getTimestamp("msgtime"));
						billsMessage.setPrice(rs.getDouble("price"));
						billsMessage.setSchedulestart(rs.getTimestamp("schedulestart"));
						billsMessage.setScheduleend(rs.getTimestamp("scheduleend"));
						billsMessage.setNote(rs.getString("note"));
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return billsMessage;
	}

	// Update bills message details
	public boolean updateBillsMessage(BillsMessages billsMessage) {
		try {
			String query = "UPDATE BillsMessages SET userid=?, billid=?, msgtime=?, price=?, schedulestart=?, scheduleend=?, note=? WHERE id=?";
			try (PreparedStatement ps = con.prepareStatement(query)) {
				ps.setInt(1, billsMessage.getUserid());
				ps.setInt(2, billsMessage.getBillid());
				ps.setTimestamp(3, new java.sql.Timestamp(billsMessage.getMsgtime().getTime()));
				ps.setDouble(4, billsMessage.getPrice());
				ps.setTimestamp(5, new java.sql.Timestamp(billsMessage.getSchedulestart().getTime()));
				ps.setTimestamp(6, new java.sql.Timestamp(billsMessage.getScheduleend().getTime()));
				ps.setString(7, billsMessage.getNote());
				ps.setInt(8, billsMessage.getId());

				int rowsAffected = ps.executeUpdate();

				return rowsAffected > 0;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	// Delete a bills message by id
	public boolean deleteBillsMessageById(int id) {
		try {
			String query = "DELETE FROM BillsMessages WHERE id=?";
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

	// Retrieve all bills messages for a specific bill
	public List<BillsMessages> getAllBillsMessagesForBill(int billid) {
		List<BillsMessages> billsMessageList = new ArrayList<>();
		try {
			String query = "SELECT * FROM BillsMessages WHERE billid=?";
			try (PreparedStatement ps = con.prepareStatement(query)) {
				ps.setInt(1, billid);

				try (ResultSet rs = ps.executeQuery()) {
					while (rs.next()) {
						BillsMessages billsMessage = new BillsMessages();
						billsMessage.setId(rs.getInt("id"));
						billsMessage.setUserid(rs.getInt("userid"));
						billsMessage.setBillid(rs.getInt("billid"));
						billsMessage.setMsgtime(rs.getTimestamp("msgtime"));
						billsMessage.setPrice(rs.getDouble("price"));
						billsMessage.setSchedulestart(rs.getTimestamp("schedulestart"));
						billsMessage.setScheduleend(rs.getTimestamp("scheduleend"));
						billsMessage.setNote(rs.getString("note"));

						billsMessageList.add(billsMessage);
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return billsMessageList;
	}
}
