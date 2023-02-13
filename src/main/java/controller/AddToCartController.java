package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.BookDAO;
import DAO.BookInCartDAO;
import db.util.MySqlDBConnector;
import model.BookDetailsModel;
import model.BookInCartModel;
import model.CartModel;

/**
 * Servlet implementation class CartController
 */
@WebServlet("/add-to-cart")
public class AddToCartController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddToCartController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		// add book to cart
		
		HttpSession session = request.getSession(false);
		int userId = Integer.parseInt(String.valueOf(session.getAttribute("id")));

		int bookId = Integer.parseInt(request.getParameter("bookId"));
		double salePrice = Double.parseDouble(request.getParameter("bookPrice"));

		Connection connection = MySqlDBConnector.makeConnection();

		ResultSet rs = null;
		PreparedStatement ps = null;
		String sqlQuery = "SELECT * FROM `cart` WHERE user_id = ? AND book_id = ?";

		try {
			ps = connection.prepareStatement(sqlQuery);
			ps.setInt(1, userId);
			ps.setInt(2, bookId);
			rs = ps.executeQuery();
//			System.out.println("there is already this book in cart " + rs.next());
			
			if (!rs.next()) {
				sqlQuery = "INSERT INTO `cart` (`user_id`, `book_id`, `book_qty`, `total`) VALUES (?, ?, ?, ?)";
				ps = connection.prepareStatement(sqlQuery);
				ps.setInt(1, userId);
				ps.setInt(2, bookId);
				ps.setInt(3, 1);
				ps.setDouble(4, salePrice);
				
				ps.executeUpdate();
			} else {
				BookInCartDAO bookInCartDAO = new BookInCartDAO();

				BookInCartModel book = bookInCartDAO.getBookInCartByBookIdAndUserId(bookId, userId);
				int newQty = book.getQty() + 1;
				double newTotal = book.getSalePrice() * newQty;

				sqlQuery = "UPDATE `cart` SET `book_qty` = ?, `total` = ? WHERE (`book_id` = ? AND `user_id` = ?)";
				ps = connection.prepareStatement(sqlQuery);
				ps.setInt(1, newQty);
				ps.setDouble(2, newTotal);
				ps.setInt(3, bookId);
				ps.setInt(4, userId);

				ps.executeUpdate();
			};
			
			String addBookSuccess = "Book added to cart successfully"; 
			session.setAttribute("addBookSuccess", addBookSuccess);
			
			response.sendRedirect("new-books");
			
		} catch (Exception e) {
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
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

}
}
