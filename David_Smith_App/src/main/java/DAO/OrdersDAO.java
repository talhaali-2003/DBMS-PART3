package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import Model.Orders;
import Helper.ConnectionProvider;

public class OrdersDAO {

	private Connection con;

	public OrdersDAO(Connection con) {
		this.con = con;
	}

	// Create a new order entry
	public boolean createOrder(Orders order) {
		try {
			String query = "INSERT INTO Orders (quoteid, price, schedulestart, scheduleend) VALUES (?, ?, ?, ?)";
			try (PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
				ps.setInt(1, order.getQuoteid());
				ps.setDouble(2, order.getPrice());
				ps.setTimestamp(3, new java.sql.Timestamp(order.getSchedulestart().getTime()));
				ps.setTimestamp(4, new java.sql.Timestamp(order.getScheduleend().getTime()));

				int rowsAffected = ps.executeUpdate();

				if (rowsAffected > 0) {
					// Retrieve the auto-generated key (order id)
					ResultSet rs = ps.getGeneratedKeys();
					if (rs.next()) {
						order.setId(rs.getInt(1));
					}
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	// Retrieve an order by id
	public Orders getOrderById(int id) {
		Orders order = null;
		try {
			String query = "SELECT * FROM Orders WHERE id=?";
			try (PreparedStatement ps = con.prepareStatement(query)) {
				ps.setInt(1, id);

				try (ResultSet rs = ps.executeQuery()) {
					if (rs.next()) {
						order = new Orders();
						order.setId(rs.getInt("id"));
						order.setQuoteid(rs.getInt("quoteid"));
						order.setPrice(rs.getDouble("price"));
						order.setSchedulestart(rs.getTimestamp("schedulestart"));
						order.setScheduleend(rs.getTimestamp("scheduleend"));
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return order;
	}

	// Update order details
	public boolean updateOrder(Orders order) {
		try {
			String query = "UPDATE Orders SET quoteid=?, price=?, schedulestart=?, scheduleend=? WHERE id=?";
			try (PreparedStatement ps = con.prepareStatement(query)) {
				ps.setInt(1, order.getQuoteid());
				ps.setDouble(2, order.getPrice());
				ps.setTimestamp(3, new java.sql.Timestamp(order.getSchedulestart().getTime()));
				ps.setTimestamp(4, new java.sql.Timestamp(order.getScheduleend().getTime()));
				ps.setInt(5, order.getId());

				int rowsAffected = ps.executeUpdate();

				return rowsAffected > 0;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	// Delete an order by id
	public boolean deleteOrderById(int id) {
		try {
			String query = "DELETE FROM Orders WHERE id=?";
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

	// Retrieve all orders for a specific quote
	public List<Orders> getAllOrdersForQuote(int quoteid) {
		List<Orders> orderList = new ArrayList<>();
		try {
			String query = "SELECT * FROM Orders WHERE quoteid=?";
			try (PreparedStatement ps = con.prepareStatement(query)) {
				ps.setInt(1, quoteid);

				try (ResultSet rs = ps.executeQuery()) {
					while (rs.next()) {
						Orders order = new Orders();
						order.setId(rs.getInt("id"));
						order.setQuoteid(rs.getInt("quoteid"));
						order.setPrice(rs.getDouble("price"));
						order.setSchedulestart(rs.getTimestamp("schedulestart"));
						order.setScheduleend(rs.getTimestamp("scheduleend"));

						orderList.add(order);
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orderList;
	}
	
	// Retrieve all orders
	public List<Orders> getAllOrders() {
	    List<Orders> orderList = new ArrayList<>();
	    try {
	        String query = "SELECT * FROM Orders";
	        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
	            while (rs.next()) {
	                Orders order = new Orders();
	                order.setId(rs.getInt("id"));
	                order.setQuoteid(rs.getInt("quoteid"));
	                order.setPrice(rs.getDouble("price"));
	                order.setSchedulestart(rs.getTimestamp("schedulestart"));
	                order.setScheduleend(rs.getTimestamp("scheduleend"));

	                orderList.add(order);
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return orderList;
	}

}
