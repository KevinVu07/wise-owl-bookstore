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

import DAO.OrderDAO;
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

		try (PrintWriter out = response.getWriter()) {
			HttpSession session = request.getSession(false);
			int userId = Integer.parseInt(String.valueOf(session.getAttribute("id")));
			int bookId = Integer.parseInt(request.getParameter("bookId"));
			int orderQty = 1;
			double bookPrice = Double.parseDouble(request.getParameter("bookPrice"));
			double orderTotal = bookPrice * orderQty;
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
			Date date = new Date();

			OrderModel order = new OrderModel();

			order.setUserId(userId);
			order.setBookId(bookId);
			order.setBookPrice(bookPrice);
			order.setOrderQty(orderQty);
			order.setOrderTotal(orderTotal);
			order.setOrderDate(formatter.format(date));

			OrderDAO orderDAO = new OrderDAO();

			Connection connection = MySqlDBConnector.makeConnection();

			ResultSet rs = null;
			PreparedStatement ps = null;
			String sqlQuery = "SELECT * FROM `order` WHERE user_id = ? AND book_id = ?";

			try {
				ps = connection.prepareStatement(sqlQuery);
				ps.setInt(1, userId);
				ps.setInt(2, bookId);
				rs = ps.executeQuery();

				// if this book is not inside the order list yet
				if (!rs.next()) {

					boolean result = orderDAO.insertOrder(order);

					if (result) {
						response.sendRedirect("orders.jsp");
					} else {
						out.print("order failed...");
					}

				} else {

					int newOrderQty = order.getOrderQty() + 1;
					double newOrderTotal = order.getBookPrice() * newOrderQty;

					sqlQuery = "UPDATE `order` SET `order_qty` = ?, `order_total` = ? WHERE (`book_id` = ? AND `user_id` = ?)";
					ps = connection.prepareStatement(sqlQuery);
					ps.setInt(1, newOrderQty);
					ps.setDouble(2, newOrderTotal);
					ps.setInt(3, bookId);
					ps.setInt(4, userId);

					ps.executeUpdate();
				}
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
		} catch (Exception e) {
			e.printStackTrace();
		}
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
