package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.BookInCartDAO;
import DAO.OrderDAO;
import db.util.MySqlDBConnector;
import model.BookInCartModel;
import model.OrderModel;

/**
 * Servlet implementation class CheckOutController
 */
@WebServlet("/checkout")
public class CheckOutController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CheckOutController() {
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

		Connection connection = MySqlDBConnector.makeConnection();

		HttpSession session = request.getSession(false);
		int userId = Integer.parseInt(String.valueOf(session.getAttribute("id")));
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		Date date = new Date();

		BookInCartDAO bookInCartDAO = new BookInCartDAO();

		List<BookInCartModel> booksInCart = bookInCartDAO.getAll();

		for (BookInCartModel book : booksInCart) {
			int bookId = book.getId();
			int orderQty = book.getQty();
			
			double bookPrice = book.getSalePrice();
			double orderTotal = bookPrice * orderQty;

			OrderModel order = new OrderModel();

			order.setUserId(userId);
			order.setBookId(bookId);
			order.setBookPrice(bookPrice);
			order.setOrderQty(orderQty);
			order.setOrderTotal(orderTotal);
			order.setOrderDate(formatter.format(date));

			OrderDAO orderDAO = new OrderDAO();

			ResultSet rs = null;
			PreparedStatement ps = null;
			String sqlQuery = "SELECT * FROM `order_items` WHERE (user_id = ? AND book_id = ? AND order_reference IS NULL)";
			try {
				ps = connection.prepareStatement(sqlQuery);
				ps.setInt(1, userId);
				ps.setInt(2, bookId);
				rs = ps.executeQuery();

				// if this book is not inside the order list yet
				if (!rs.next()) {

					boolean result = orderDAO.insertOrder(order);

					if (result) {
						System.out.println("this book has been added to order list...");
						// clear book out of cart
					} else {
						System.out.println("order failed...");
					}

				} else {
					
					System.out.println("Book is already in order list, updating qty...");

					int currentOrderQty = rs.getInt("order_qty");
					int newOrderQty = currentOrderQty + order.getOrderQty();
					double newOrderTotal = order.getBookPrice() * newOrderQty;

					sqlQuery = "UPDATE `order_items` SET `order_qty` = ?, `order_total` = ? WHERE (`book_id` = ? AND `user_id` = ? AND order_reference IS NULL)";

					ps = connection.prepareStatement(sqlQuery);
					ps.setInt(1, newOrderQty);
					ps.setDouble(2, newOrderTotal);
					ps.setInt(3, bookId);
					ps.setInt(4, userId);

					ps.executeUpdate();

				}
				
				bookInCartDAO.removeBookInCartByBookIdAndUserId(bookId, userId);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		response.sendRedirect("order-summary");
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
