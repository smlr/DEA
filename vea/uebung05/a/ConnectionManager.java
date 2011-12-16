package vea.uebung05.a;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
	private String connectionString = "jdbc:mysql://bgm-abi08.de:3306/ulilicht_bookshop_uni?"
			+ "user=gondolin&password=tumladen";

	public Connection openConnection() {
		Connection conn = null;
		System.out.println("Try to open connection");
		try {
			Class.forName("com.mysql.jdbc.Driver");
			try {
				conn = DriverManager.getConnection(connectionString);
				System.out.println("connection opened");
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				 if (conn != null) {
//					 try {
//						 conn.close();
//					 } catch (SQLException e) {
//						 e.printStackTrace();
//					 }
				 }
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return conn;
	}

	public void closeConnection(Connection conn) {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
