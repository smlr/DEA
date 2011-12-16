package vea.uebung05.c;

import java.sql.SQLException;

import javax.sql.rowset.CachedRowSet;

public class Test {

	public static void main(String[] args) {

		OrderDAO o = new OrderDAO();
		try {
			CachedRowSet crs = o.selectOrderByMonth(5);

			while (crs.next()) {
				int orderid = crs.getInt("orderid");
				String orderdate = crs.getString("orderdate");

				System.out.println("orderid[" + orderid + "] -> " + orderdate);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
