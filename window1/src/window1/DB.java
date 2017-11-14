package window1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DB {
	private static String DB_ID = "memory";
	private static String DB_PASSWORD = "0238";
	private static String DB_NAME = "memory";
	private static String DB_IP = "localhost";
	private static int DB_PORT = 3306;

	private static String DB_Connect = "jdbc:mysql://" + DB_IP + ":" + DB_PORT + "/" + DB_NAME;

	Connection conn;
	Statement stmt;

	public DB() {
		connectDB();
	}

	protected void connectDB() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_Connect, DB_ID, DB_PASSWORD);
			stmt = conn.createStatement();
		} catch (ClassNotFoundException e) {
			System.err.println("Error : DB Connect - Class Not Found");
		} catch (SQLException e) {
			System.err.println("Error : DB Connect - Driver Manager");
		}
	}

	protected boolean insertMember(String id, String passwd, String email, String name, int question, String answer) {
		boolean isSuccess = false;
		try {
			String sql = "insert into member values(" + id + ", " + passwd + ", " + email + ", " + name + ", "
					+ question + ", " + answer + ");";

			isSuccess = stmt.execute(sql);
		} catch (SQLException e) {
			System.err.println("Error : Insert Member");
		}
		return isSuccess;
	}

	protected void closeDB() {
		try {
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			System.err.println("Error : DB Close");
		}
	}
}
