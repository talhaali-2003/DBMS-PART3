package Servlete;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Helper.ConnectionProvider;

@WebServlet("/RootUserInterfaceServlet")
public class RootUserInterfaceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String action = request.getParameter("action");

		// Check if the provided username and password are correct
//		if ("root".equals(username) && "pass1234".equals(password)) {

			if (action.equals("deleteTables")) {
//		 
				deleteTables();

			}

			if (action.equals("createTables")) {
//		 
				createTables();
				System.out.println("creat");
			}

			if (action.equals("insertData")) {
//		 
				insertData();
				System.out.println("inser");
			}

			// Provide a response to the user
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println("<html><body>");
			out.println("<h2>Actions Completed Successfully!</h2>");
			out.println("</body></html>");

		}

//		else {
//			// If credentials are incorrect, show an error message
//			response.sendRedirect("root.jsp?error=true");
//		}
//	}
	
	

	private void deleteTables() {
		try (Connection connection = ConnectionProvider.getConnection();
				Statement statement = connection.createStatement()) {

			// Delete existing tables
			statement.executeUpdate("DROP TABLE IF EXISTS `users`, `quotes`, `orders`, `bills`;");

			// You can add additional logic or messages if needed
			System.out.println("Tables deleted successfully.");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void createTables() {
		try (Connection connection = ConnectionProvider.getConnection();
				Statement statement = connection.createStatement()) {

			// Create Users table
			statement.executeUpdate("CREATE TABLE `users` (" + "`id` int(11) NOT NULL AUTO_INCREMENT,"
					+ "`firstname` varchar(50) DEFAULT NULL," + "`lastname` varchar(50) DEFAULT NULL,"
					+ "`creditcard` char(16) DEFAULT NULL," + "`email` varchar(50) DEFAULT NULL,"
					+ "`password` varchar(33) NOT NULL," + "PRIMARY KEY (`id`)," + "UNIQUE KEY `email` (`email`)"
					+ ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;");

			// Create Quotes table
			statement.executeUpdate("CREATE TABLE `quotes` (" + "`id` int(11) NOT NULL AUTO_INCREMENT,"
					+ "`contractorid` int(11) DEFAULT NULL," + "`clientid` int(11) DEFAULT NULL,"
					+ "`price` double DEFAULT NULL," + "`schedulestart` datetime DEFAULT NULL,"
					+ "`scheduleend` datetime DEFAULT NULL," + "`note` varchar(44) DEFAULT 'note ',"
					+ "`status` varchar(22) NOT NULL DEFAULT 'pending'," + "PRIMARY KEY (`id`),"
					+ "FOREIGN KEY (`contractorid`) REFERENCES `users`(`id`),"
					+ "FOREIGN KEY (`clientid`) REFERENCES `users`(`id`)" + ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;");

			// Create Orders table
			statement.executeUpdate("CREATE TABLE `orders` (" + "`id` int(11) NOT NULL AUTO_INCREMENT,"
					+ "`quoteid` int(11) DEFAULT NULL," + "`price` double DEFAULT NULL,"
					+ "`schedulestart` datetime DEFAULT NULL," + "`scheduleend` datetime DEFAULT NULL,"
					+ "PRIMARY KEY (`id`)," + "FOREIGN KEY (`quoteid`) REFERENCES `quotes`(`id`)"
					+ ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;");

			// Create Bills table
			statement.executeUpdate("CREATE TABLE `bills` (" + "`id` int(11) NOT NULL AUTO_INCREMENT,"
					+ "`orderid` int(11) DEFAULT NULL," + "`price` double DEFAULT NULL,"
					+ "`discount` double DEFAULT NULL," + "`balance` double DEFAULT NULL,"
					+ "`status` varchar(20) DEFAULT NULL," + "PRIMARY KEY (`id`),"
					+ "FOREIGN KEY (`orderid`) REFERENCES `orders`(`id`)" + ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;");

			// Create Users2 table
			statement.executeUpdate("CREATE TABLE `users2` (" + "`id` int(11) NOT NULL AUTO_INCREMENT,"
					+ "`username` varchar(50) NOT NULL," + "`userrole` varchar(50) NOT NULL,"
					+ "`email` varchar(50) NOT NULL," + "`password` varchar(50) NOT NULL," + "PRIMARY KEY (`id`),"
					+ "UNIQUE KEY `email` (`email`)" + ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;");

			// You can add additional logic or messages if needed
			System.out.println("Tables created successfully.");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void insertData() {
		try (Connection connection = ConnectionProvider.getConnection();
				Statement statement = connection.createStatement()) {

			// Insert at least 10 realistic tuples into each table
			for (int i = 1; i <= 10; i++) {
//				 Insert data into the Users table
				statement.executeUpdate(
						"INSERT INTO users (firstname, lastname, creditcard, email, password) VALUES ('FirstName" + i
								+ "', 'LastName" + i + "', '12345dd67890123456', 'email" + i
								+ "@examp34dd2le6.com', 'password" + i + "');");

				// Insert data into the Quotes table
				statement.executeUpdate(
						"INSERT INTO `quotes` (`contractorid`, `clientid`, `price`, `schedulestart`, `scheduleend`, `note`, `status`) VALUES\r\n"
								+ "(1, 1, 33, '2023-11-22 00:00:00', '2023-11-22 00:00:00', 'note ', 'accept');" + "");
//
			}

			// Insert data into the Orders table
			for (int i = 1; i <= 10; i++) {
				statement.executeUpdate(
						"INSERT INTO `orders` (`quoteid`, `price`, `schedulestart`, `scheduleend`) VALUES "
								+ "(1, 50.0, '2023-11-22 00:00:00', '2023-11-22 00:00:00');");
			}

			// Insert data into the Bills table
			for (int i = 1; i <= 10; i++) {
				statement.executeUpdate(
						"INSERT INTO `bills` (`orderid`, `price`, `discount`, `balance`, `status`) VALUES "
								+ "(1, 100.0, 10.0, 90.0, 'paid');");
			}

			// You can add additional logic or messages if needed
			System.out.println("Data inserted successfully.");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Handle GET requests here (if needed)
		System.out.println("hddh");
	}
}
