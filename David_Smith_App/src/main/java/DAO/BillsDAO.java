package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Model.Bills;
import Helper.ConnectionProvider;

public class BillsDAO {

    private Connection con;

    
    private OrdersDAO ordersDAO; // Add this line

    public BillsDAO(Connection con) {
        this.con = con;
        this.ordersDAO = new OrdersDAO(con); // Add this line
    }

    // Create a new bill entry
    public boolean createBill(Bills bill) {
        try {
            String query = "INSERT INTO Bills (orderid, price, discount, balance, status) VALUES (?, ?, ?, ?, ?)";
            try ( PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, bill.getOrderid());
                ps.setDouble(2, bill.getPrice());
                ps.setDouble(3, bill.getDiscount());
                ps.setDouble(4, bill.getBalance());
                ps.setString(5, bill.getStatus());

                int rowsAffected = ps.executeUpdate();

                if (rowsAffected > 0) {
                    // Retrieve the auto-generated key (bill id)
                    ResultSet rs = ps.getGeneratedKeys();
                    if (rs.next()) {
                        bill.setId(rs.getInt(1));
                    }
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Retrieve a bill by id
    public Bills getBillById(int id) {
        Bills bill = null;
        try {
            String query = "SELECT * FROM Bills WHERE id=?";
            try ( PreparedStatement ps = con.prepareStatement(query)) {
                ps.setInt(1, id);

                try ( ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        bill = new Bills();
                        bill.setId(rs.getInt("id"));
                        bill.setOrderid(rs.getInt("orderid"));
                        bill.setPrice(rs.getDouble("price"));
                        bill.setDiscount(rs.getDouble("discount"));
                        bill.setBalance(rs.getDouble("balance"));
                        bill.setStatus(rs.getString("status"));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bill;
    }
    // Retrieve a bill by id
    public Bills getBill( ) {
        Bills bill = null;
        try {
            String query = "SELECT * FROM Bills ";
            try ( PreparedStatement ps = con.prepareStatement(query)) {
//                ps.setInt(1, id);

                try ( ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        bill = new Bills();
                        bill.setId(rs.getInt("id"));
                        bill.setOrderid(rs.getInt("orderid"));
                        bill.setPrice(rs.getDouble("price"));
                        bill.setDiscount(rs.getDouble("discount"));
                        bill.setBalance(rs.getDouble("balance"));
                        bill.setStatus(rs.getString("status"));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bill;
    }

    // Update bill details
    public boolean updateBill(Bills bill) {
        try {
            String query = "UPDATE Bills SET orderid=?, price=?, discount=?, balance=?, status=? WHERE id=?";
            try ( PreparedStatement ps = con.prepareStatement(query)) {
                ps.setInt(1, bill.getOrderid());
                ps.setDouble(2, bill.getPrice());
                ps.setDouble(3, bill.getDiscount());
                ps.setDouble(4, bill.getBalance());
                ps.setString(5, bill.getStatus());
                ps.setInt(6, bill.getId());

                int rowsAffected = ps.executeUpdate();

                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Delete a bill by id
    public boolean deleteBillById(int id) {
        try {
            String query = "DELETE FROM Bills WHERE id=?";
            try ( PreparedStatement ps = con.prepareStatement(query)) {
                ps.setInt(1, id);

                int rowsAffected = ps.executeUpdate();

                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Retrieve all bills for a specific order
    public List<Bills> getAllBillsForOrder(int orderid) {
        List<Bills> billList = new ArrayList<>();
        try {
            String query = "SELECT * FROM Bills WHERE orderid=?";
            try ( PreparedStatement ps = con.prepareStatement(query)) {
                ps.setInt(1, orderid);

                try ( ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        Bills bill = new Bills();
                        bill.setId(rs.getInt("id"));
                        bill.setOrderid(rs.getInt("orderid"));
                        bill.setPrice(rs.getDouble("price"));
                        bill.setDiscount(rs.getDouble("discount"));
                        bill.setBalance(rs.getDouble("balance"));
                        bill.setStatus(rs.getString("status"));

                        billList.add(bill);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return billList;
    }
    public List<Bills> getAllBills() {
        List<Bills> billList = new ArrayList<>();
        try {
            String query = "SELECT * FROM Bills  ";
            try ( PreparedStatement ps = con.prepareStatement(query)) {

                try ( ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        Bills bill = new Bills();
                        bill.setId(rs.getInt("id"));
                        bill.setOrderid(rs.getInt("orderid"));
                        bill.setPrice(rs.getDouble("price"));
                        bill.setDiscount(rs.getDouble("discount"));
                        bill.setBalance(rs.getDouble("balance"));
                        bill.setStatus(rs.getString("status"));

                        billList.add(bill);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return billList;
    }

    public Bills getMostRecentBillFromDatabase() {
        try {
            String query = "SELECT * FROM Bills ORDER BY id DESC LIMIT 1";
            try ( Statement stmt = con.createStatement();  ResultSet rs = stmt.executeQuery(query)) {
                if (rs.next()) {
                    Bills recentBill = new Bills();
                    recentBill.setId(rs.getInt("id"));
                    recentBill.setOrderid(rs.getInt("orderid"));
                    recentBill.setPrice(rs.getDouble("price"));
                    recentBill.setDiscount(rs.getDouble("discount"));
                    recentBill.setBalance(rs.getDouble("balance"));
                    recentBill.setStatus(rs.getString("status"));
                    return recentBill;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Add a new method to retrieve overdue bills
    public List<Bills> getOverdueBills() {
        List<Bills> overdueBills = new ArrayList<>();

        try {
            String query = "SELECT * FROM Bills WHERE status = 'overdue'";
            try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
                while (rs.next()) {
                    Bills bill = new Bills();
                    bill.setId(rs.getInt("id"));
                    bill.setOrderid(rs.getInt("orderid"));
                    bill.setPrice(rs.getDouble("price"));
                    bill.setDiscount(rs.getDouble("discount"));
                    bill.setBalance(rs.getDouble("balance"));
                    bill.setStatus(rs.getString("status"));

                    // Assuming there is an 'OrdersDAO' class with a method 'getOrderById(int orderId)'
                    bill.setOrder(ordersDAO.getOrderById(bill.getOrderid()));

                    overdueBills.add(bill);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return overdueBills;
    }
}
