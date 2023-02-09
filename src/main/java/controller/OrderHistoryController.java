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
import DAO.OrderItemDAO;
import model.CompletedOrderModel;
import model.OrderItemModel;
import model.OrderModel;

/**
 * Servlet implementation class OrderHistoryController
 */
@WebServlet("/order-history")
public class OrderHistoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public OrderHistoryController() {
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

		OrderDAO orderDAO = new OrderDAO();
		
		List<CompletedOrderModel> orderList = orderDAO.getAllOrderByUserId(userId);
		session.setAttribute("orderList", orderList);
		
//		for (String orderRef : orderRefList) {
//			double total = 0;
//			List<OrderListModel> orderList = orderDAO.getAllOrderByUserIdAndOrderRef(userId, orderRef);
//			for (OrderListModel order : orderList) {
//				total = total
//			}
//		}

		RequestDispatcher dp = request.getRequestDispatcher("orderHistory.jsp");
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
