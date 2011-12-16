package vea.uebung05.a;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BookDAO {

	public int generateBookId() {
		int id = -1;
		ResultSet result = null;
		String sql = "SELECT MAX(bookid) FROM bs_book";
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
		return id + 1;
	}

	public void save(BookBean book) {
		String sql = "INSERT INTO bs_book(bookid, name, isbn, price) "
				+ "VALUES('" + this.generateBookId() + "','" + book.getName()
				+ "','" + book.getIsbn() + "','" + book.getPrice() + "');";
		System.out.println(sql);
		ConnectionManager mgr = new ConnectionManager();
		Connection con = mgr.openConnection();
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public BookBean load(int id) {
		BookBean book = new BookBean();
		ResultSet result = null;
		String sql = "SELECT * FROM bs_book WHERE bookid='" + id + "' ;";
		System.out.println(sql);
		ConnectionManager mgr = new ConnectionManager();
		Connection con = mgr.openConnection();
		try {
			Statement stmt = con.createStatement();
			result = stmt.executeQuery(sql);

			if (result.next() == true) {
				book.setBookid(result.getInt("bookid"));
				book.setIsbn(result.getString("isbn"));
				book.setName(result.getString("name"));
				book.setPrice(result.getDouble("price"));
			} else {
				book = null;
			}

			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return book;
	}
}
