package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mysql.cj.protocol.Resultset;

import db.util.MySqlDBConnector;
import model.BookDetailsModel;

public class BookDAO {

	public List<BookDetailsModel> getAll() {
		List<BookDetailsModel> bookList = new ArrayList<BookDetailsModel>();

		// make connection to MYSQL LOCALHOST, Schema book_store
		Connection connection = MySqlDBConnector.makeConnection();
		ResultSet rs = null;
		PreparedStatement ps = null;
		String sqlQuery = "SELECT b.id as book_id, b.name as book_name, b.description, b.category_id, c.name as category_name, b.type, b.edition_number, b.published_date, b.ISBN, b.rrp, b.sale_price, b.author_id, a.name as author_name, b.image"
				+ " FROM `book` b JOIN `category` c ON b.category_id = c.id" + " JOIN `author` a ON b.author_id = a.id";

		try {
			ps = connection.prepareStatement(sqlQuery);
			// RESULT SET / RESULT GRID
			rs = ps.executeQuery();

			// LOOP EACH ROW -> get value of all columns
			while (rs.next() == true) {
				int id = rs.getInt("book_id");
				String name = rs.getString("book_name");
				String description = rs.getString("description");
				int categoryId = rs.getInt("category_id");
				String categoryName = rs.getString("category_name");
				String type = rs.getString("type");
				int editionNumber = rs.getInt("edition_number");
				Date publishedDate = rs.getDate("published_date");
				String ISBN = rs.getString("ISBN");
				double rrp = rs.getDouble("rrp");
				double salePrice = rs.getDouble("sale_price");
				int authorId = rs.getInt("author_id");
				String authorName = rs.getString("author_name");
				String image = rs.getString("image");
				BookDetailsModel book = new BookDetailsModel(id, name, description, categoryId, categoryName, type,
						editionNumber, publishedDate, ISBN, rrp, salePrice, authorId, authorName, image);

				bookList.add(book);
			}
			;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(connection, ps, rs);
		}

		return bookList;
	}

	public BookDetailsModel getBookDetailsById(String id) {
		BookDetailsModel book = null;

		// make connection to MYSQL LOCALHOST, Schema book_store
		Connection connection = MySqlDBConnector.makeConnection();
		ResultSet rs = null;
		PreparedStatement ps = null;
		String sqlQuery = "SELECT b.id as book_id, b.name as book_name, b.description, b.category_id, c.name as category_name, b.type, b.edition_number, b.published_date, b.ISBN, b.rrp, b.sale_price, b.author_id, a.name as author_name, b.image"
				+ " FROM `book` b JOIN `category` c ON b.category_id = c.id" + " JOIN `author` a ON b.author_id = a.id"
				+ " WHERE b.id=" + id;
		try {
			ps = connection.prepareStatement(sqlQuery);
			// RESULT SET / RESULT GRID
			rs = ps.executeQuery();
			
		

			// LOOP EACH ROW -> get value of all columns
			if (rs.next() == true) {
				String name = rs.getString("book_name");
				String description = rs.getString("description");
				int categoryId = rs.getInt("category_id");
				String categoryName = rs.getString("category_name");
				String type = rs.getString("type");
				int editionNumber = rs.getInt("edition_number");
				Date publishedDate = rs.getDate("published_date");
				String ISBN = rs.getString("ISBN");
				double rrp = rs.getDouble("rrp");
				double salePrice = rs.getDouble("sale_price");
				int authorId = rs.getInt("author_id");
				String authorName = rs.getString("author_name");
				String image = rs.getString("image");
				book = new BookDetailsModel(Integer.parseInt(id), name, description, categoryId, categoryName, type,
						editionNumber, publishedDate, ISBN, rrp, salePrice, authorId, authorName, image);

			}
			;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(connection, ps, rs);
		}
		return book;
	}

	public boolean addBook(BookDetailsModel book) {
		boolean bookAdded = false;
		Connection connection = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

			connection = MySqlDBConnector.makeConnection();
			String sqlQuery = "INSERT INTO book(name, description, category_id, type, edition_number, published_date, ISBN, rrp, sale_price, author_id, image) value(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			ps = connection.prepareStatement(sqlQuery);

			ps.setString(1, book.getName());
			ps.setString(2, book.getDescription());
			ps.setInt(3, book.getCategoryId());
			ps.setString(4, book.getType());
			ps.setInt(5, book.getEditionNumber());
			ps.setString(6, formatter.format(book.getPublishedDate()));
			ps.setString(7, book.getISBN());
			ps.setDouble(8, book.getRrp());
			ps.setDouble(9, book.getSalePrice());
			ps.setInt(10, book.getAuthorId());
			ps.setString(11, book.getImage());

			int i = ps.executeUpdate();
			
			if (i == 1) {
				bookAdded = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(connection, ps, rs);
		}
		return bookAdded;
	}

	public void deleteBook(int id) {
		Connection connection = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		try {
			connection = MySqlDBConnector.makeConnection();

			String sqlQuery = "DELETE FROM book WHERE id = ?";
			ps = connection.prepareStatement(sqlQuery);
			ps.setInt(1, id);

			ps.execute();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(connection, ps, rs);
		}
	}

	private void close(Connection connection, Statement stm, ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (stm != null) {
				stm.close();
			}
			if (connection != null) {
				connection.close();
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
