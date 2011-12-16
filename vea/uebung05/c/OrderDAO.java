package vea.uebung05.c;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.rowset.CachedRowSet;
import com.sun.rowset.CachedRowSetImpl;
import vea.uebung05.a.ConnectionManager;

public class OrderDAO {

	public CachedRowSet selectOrderByMonth(int month) {
		ResultSet result = null;
		ConnectionManager mgr = new ConnectionManager();
		Connection con = mgr.openConnection();
		CachedRowSetImpl crs = null;

		try {
			crs = new CachedRowSetImpl();
			Statement stmt = con.createStatement();
			result = stmt
					.executeQuery("SELECT * FROM bs_order WHERE MONTH(orderdate) = '"
							+ month + "';");
			crs.populate(result);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return crs;
	}

}
