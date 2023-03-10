package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.OrderItemDAO;
import db.util.MySqlDBConnector;
import model.OrderModel;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Servlet implementation class Order
 */
@WebServlet("/order-now")
public class OrderNowController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public OrderNowController() {
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

		HttpSession session = request.getSession(false);
		int userId = Integer.parseInt(String.valueOf(session.getAttribute("id")));
		int bookId = Integer.parseInt(request.getParameter("bookId"));
		

		OrderItemDAO orderItemDAO = new OrderItemDAO();
		OrderModel order = orderItemDAO.getOrderByBookIdAndUserId(bookId, userId);

		System.out.println(order == null);
//			String sqlQuery = "SELECT * FROM `order` WHERE (user_id = ? AND book_id = ? AND order_reference IS NULL)";
//
//			try {
//				ps = connection.prepareStatement(sqlQuery);
//				ps.setInt(1, userId);
//				ps.setInt(2, bookId);
//				rs = ps.executeQuery();

		// if this book is not inside the order list yet
//				if (!rs.next()) {

		if (order == null) {
			int orderQty = 1;
			double bookPrice = Double.parseDouble(request.getParameter("bookPrice"));
			double orderTotal = bookPrice * orderQty;
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
			Date date = new Date();

			OrderModel newOrder = new OrderModel();

			newOrder.setUserId(userId);
			newOrder.setBookId(bookId);
			newOrder.setBookPrice(bookPrice);
			newOrder.setOrderQty(orderQty);
			newOrder.setOrderTotal(orderTotal);
			newOrder.setOrderDate(formatter.format(date));
			boolean result = orderItemDAO.insertOrder(newOrder);

			if (result) {
				System.out.println("this book has been added to order list...");

			} else {
				System.out.println("order failed...");
			}

		} else {

			int newOrderQty = order.getOrderQty() + 1;
			double newOrderTotal = order.getBookPrice() * newOrderQty;

			Connection connection = MySqlDBConnector.makeConnection();
			PreparedStatement ps = null;
			String sqlQuery = "UPDATE `order_items` SET `order_qty` = ?, `order_total` = ? WHERE (`book_id` = ? AND `user_id` = ? AND order_reference IS NULL)";

			try {
				ps = connection.prepareStatement(sqlQuery);
				ps.setInt(1, newOrderQty);
				ps.setDouble(2, newOrderTotal);
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
