package vea.uebung05.a;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CustomerDAO {

	public int count() {
		int id = -1;
		ResultSet result = null;
		String sql = "SELECT count(customerid) FROM bs_customer";
		ConnectionManager mgr = new ConnectionManager();
		Connection con = mgr.openConnection();
		try {
			Statement stmt = con.createStatement();
			result = stmt.executeQuery(sql);
			result.next();
			id = result.getInt(1);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return id;
	}
}
