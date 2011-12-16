package vea.uebung05.b;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import vea.uebung05.a.ConnectionManager;

public class BackupManager {

	public void backupOrder(String destination) {
		File f = new File(destination + "/order.sql");
		ArrayList<String> orders = new ArrayList<String>();

		ResultSet result = null;
		String sql = "SELECT * FROM bs_order;";
		ConnectionManager mgr = new ConnectionManager();
		Connection con = mgr.openConnection();
		try {
			Statement stmt = con.createStatement();
			result = stmt.executeQuery(sql);

			while (result.next() == true) {
				orders.add("INSERT INTO bs_order(orderid, orderdate, customerid) VALUES('"
						+ result.getInt(1)
						+ "','"
						+ result.getString(2)
						+ "','" + result.getInt(3) + "');");
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		FileOutputStream fos;
		try {
			fos = new FileOutputStream(f);
			for (String order : orders) {
				System.out.println(order);
				fos.write(new String(order + "\n").getBytes());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void restore(String sqlFile) {
		File f = new File(sqlFile);

		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(
					new FileInputStream(f)));
			String line;

			ConnectionManager mgr = new ConnectionManager();
			Connection con = mgr.openConnection();
			con.setAutoCommit(false);
			Statement stmt = con.createStatement();

			while ((line = in.readLine()) != null) {
				stmt.execute(line);
			}

			con.commit();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

		}

	}
}
