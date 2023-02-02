package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import db.util.MySqlDBConnector;
import model.BookDetailsModel;
import model.BookInCartModel;

public class BookInCartDAO {
	public List<BookInCartModel> getAll() {
		List<BookInCartModel> booksInCart = new ArrayList<BookInCartModel>();

		// make connection to MYSQL LOCALHOST, Schema book_store
		Connection connection = MySqlDBConnector.makeConnection();
		ResultSet rs = null;
		PreparedStatement ps = null;
		String sqlQuery = "SELECT c.id, c.user_id, c.book_id, b.image, b.name, b.description, b.type, b.sale_price, c.book_qty, c.total"
				+ " FROM `book` b JOIN `cart` c ON b.id = c.book_id";
				
		try {
			ps = connection.prepareStatement(sqlQuery);
			// RESULT SET / RESULT GRID
			rs = ps.executeQuery();

			// LOOP EACH ROW -> get value of all columns
			while (rs.next() == true) {
				int id = rs.getInt("book_id");
				String image = rs.getString("image");
				String name = rs.getString("name");
				String type = rs.getString("type");
				String description = rs.getString("description");
				double salePrice = rs.getDouble("sale_price");
				int qty = rs.getInt("book_qty");
				double total = rs.getDouble("total");
				
				BookInCartModel book = new BookInCartModel(id, image, name, type, description, salePrice, qty, total);

				booksInCart.add(book);
			}
			;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (connection != null) {
					connection.close();
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return booksInCart;
	}
	
	public BookInCartModel getBookInCartByBookIdAndUserId(int bookId, int userId) {
		BookInCartModel book = null;

		// make connection to MYSQL LOCALHOST, Schema book_store
		Connection connection = MySqlDBConnector.makeConnection();
		ResultSet rs = null;
		PreparedStatement ps = null;
		String sqlQuery = "SELECT c.id, c.user_id, c.book_id, b.image, b.name, b.description, b.type, b.sale_price, c.book_qty, c.total"
				+ " FROM `book` b JOIN `cart` c ON b.id = c.book_id"
				+ " WHERE book_id = ? AND user_id = ?";
		try { 
			ps = connection.prepareStatement(sqlQuery);
			ps.setInt(1, bookId);
			ps.setDouble(2, userId);
			// RESULT SET / RESULT GRID
			rs = ps.executeQuery();

			// LOOP EACH ROW -> get value of all columns
			if (rs.next() == true) {
				String image = rs.getString("image");
				String name = rs.getString("name");
				String type = rs.getString("type");
				String description = rs.getString("description");
				double salePrice = rs.getDouble("sale_price");
				int qty = rs.getInt("book_qty");
				double total = rs.getDouble("total");
				
				book = new BookInCartModel(bookId, image, name, type, description, salePrice, qty, total);
			}
			;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (connection != null) {
					connection.close();
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return book;
	}
}
