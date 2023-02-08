package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.OrderDAO;
import model.OrderListModel;

/**
 * Servlet implementation class PastOrderDetailsController
 */
@WebServlet("/past-order-details")
public class PastOrderDetailsController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PastOrderDetailsController() {
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

		String orderRef = request.getParameter("orderRef");

		OrderDAO orderDAO = new OrderDAO();
		List<OrderListModel> orderList = orderDAO.getAllOrderByUserIdAndOrderRef(userId, orderRef);
		double orderTotal = 0;
		for (OrderListModel order : orderList) {
			orderTotal = orderTotal + order.getOrderTotal();
		}
		session.setAttribute("orderTotal", orderTotal);
		session.setAttribute("orderRef", orderRef);
		session.setAttribute("orderList", orderList);
		

		RequestDispatcher dp = request.getRequestDispatcher("pastOrderDetails.jsp");
		dp.forward(request, response);
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
