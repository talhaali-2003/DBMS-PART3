package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Model.Trees;
import Helper.ConnectionProvider;

public class TreesDAO {

	private Connection con;

	public TreesDAO(Connection con) {
		this.con = con;
	}
//
	// Create a new tree entry
	public boolean createTree(Trees tree) {
        try (Connection con = ConnectionProvider.getConnection();
             PreparedStatement ps = con.prepareStatement("INSERT INTO Trees (quoteid, size, height, distanceFromHouse) VALUES (?, ?, ?, ?)",
                     Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, tree.getQuoteid());
            ps.setDouble(2, tree.getSize());
            ps.setDouble(3, tree.getHeight());
            ps.setDouble(4, tree.getDistanceFromHouse());

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        tree.setId(rs.getInt(1));
                    }
                }
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

	//
	
	// Retrieve a tree by id
	public Trees getTreeById(int id) {
		Trees tree = null;
		try {
			String query = "SELECT * FROM Trees WHERE id=?";
			try (PreparedStatement ps = con.prepareStatement(query)) {
				ps.setInt(1, id);

				try (ResultSet rs = ps.executeQuery()) {
					if (rs.next()) {
						tree = new Trees();
						tree.setId(rs.getInt("id"));
						tree.setQuoteid(rs.getInt("quoteid"));
						tree.setSize(rs.getDouble("size"));
						tree.setHeight(rs.getDouble("height"));
						tree.setDistanceFromHouse(rs.getDouble("distanceFromHouse"));
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tree;
	}

	// Update tree details
	public boolean updateTree(Trees tree) {
		try {
			String query = "UPDATE Trees SET quoteid=?, size=?, height=?, distanceFromHouse=? WHERE id=?";
			try (PreparedStatement ps = con.prepareStatement(query)) {
				ps.setInt(1, tree.getQuoteid());
				ps.setDouble(2, tree.getSize());
				ps.setDouble(3, tree.getHeight());
				ps.setDouble(4, tree.getDistanceFromHouse());
				ps.setInt(5, tree.getId());

				int rowsAffected = ps.executeUpdate();

				return rowsAffected > 0;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	// Delete a tree by id
	public boolean deleteTreeById(int id) {
		try {
			String query = "DELETE FROM Trees WHERE id=?";
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

	// Retrieve all trees for a specific quote
	public List<Trees> getAllTreesForQuote(int quoteid) {
		List<Trees> treeList = new ArrayList<>();
		try {
			String query = "SELECT * FROM Trees WHERE quoteid=?";
			try (PreparedStatement ps = con.prepareStatement(query)) {
				ps.setInt(1, quoteid);

				try (ResultSet rs = ps.executeQuery()) {
					while (rs.next()) {
						Trees tree = new Trees();
						tree.setId(rs.getInt("id"));
						tree.setQuoteid(rs.getInt("quoteid"));
						tree.setSize(rs.getDouble("size"));
						tree.setHeight(rs.getDouble("height"));
						tree.setDistanceFromHouse(rs.getDouble("distanceFromHouse"));

						treeList.add(tree);
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return treeList;
	}
}
