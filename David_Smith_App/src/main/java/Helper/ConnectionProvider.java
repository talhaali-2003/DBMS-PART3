package Helper;

//import java.applet.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

 
public class ConnectionProvider {

    private static Connection con;
    
    Connection connection = null;
	Statement statement = null;

    public static Connection getConnection() throws SQLException {
        try {
            if (con == null) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/project?" + "allowPublicKeyRetrieval=true&useSSL=false&user=root&password=LilPlug2323!");
            }
        } catch (Exception e) {
            throw new SQLException("Failed to obtain a database connection: " + e.getMessage(), e);
        }
        return con;
    }
}