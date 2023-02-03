package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.BookInCartDAO;
import db.util.MySqlDBConnector;
import model.BookInCartModel;
import model.Cart;

/**
 * Servlet implementation class CartDecIncController
 */
@WebServlet("/cartDecInc")
public class CartDecIncController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CartDecIncController() {
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

	
		String action = request.getParameter("action");
		int bookId = Integer.parseInt(request.getParameter("id"));
		
		HttpSession session = request.getSession(false);
		int userId = Integer.parseInt(String.valueOf(session.getAttribute("id")));
		
		
		BookInCartDAO bookInCartDAO = new BookInCartDAO();

		BookInCartModel book = bookInCartDAO.getBookInCartByBookIdAndUserId(bookId, userId);
		
		Connection connection = MySqlDBConnector.makeConnection();
		
		int newQty = 0;
		if (action.equals("dec") && book.getQty() > 0) {
			newQty = book.getQty() - 1;
		} else if (action.equals("inc")) {
			newQty = book.getQty() + 1;
		}
		
		if (newQty == 0) {
			PreparedStatement ps = null;
			String sqlQuery = "DELETE FROM `cart` WHERE (`book_id` = ? AND `user_id` = ?)";
			
			try {
				ps = connection.prepareStatement(sqlQuery);
				ps.setInt(1, bookId);
				ps.setDouble(2, userId);

				ps.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
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
		} else {
		
		double newTotal = book.getSalePrice() * newQty;
		
		PreparedStatement ps = null;
		String sqlQuery = "UPDATE `cart` SET `book_qty` = ?, `total` = ? WHERE (`book_id` = ? AND `user_id` = ?)";
		
		try {
			ps = connection.prepareStatement(sqlQuery);
			ps.setInt(1, newQty);
			ps.setDouble(2, newTotal);
			ps.setInt(3, bookId);
			ps.setInt(4, userId);

			ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			try {
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
		
		response.sendRedirect("cart");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
