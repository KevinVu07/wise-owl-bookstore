package controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

/**
 * Servlet implementation class StripePaymentSuccess
 */
@WebServlet("/payment-success")
public class StripePaymentSuccess extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StripePaymentSuccess() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session2 = request.getSession(false);
		int userId = Integer.parseInt(String.valueOf(session2.getAttribute("id")));
		String orderRef = String.valueOf(Math.round(Math.random() * 100000));
		double orderTotal2 = Double.parseDouble(String.valueOf(session2.getAttribute("orderTotal")));
		
		OrderItemDAO orderItemDAO = new OrderItemDAO();
		orderItemDAO.updateOrderReferenceByUserId(userId, orderRef);
		
//		List<OrderItemModel> orderItemList = orderItemDAO.getAllOrderByUserIdAndOrderRef(userId, orderRef);
//		
//		double orderTotal2 = 0;
//		
//		for (OrderItemModel orderItem : orderItemList) {
//			orderTotal2 = orderTotal2 + orderItem.getOrderTotal();
//		}
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		Date date = new Date();

		
		OrderDAO orderDAO = new OrderDAO();
		CompletedOrderModel order = new CompletedOrderModel();
		
		order.setOrderRef(orderRef);
		order.setUserId(userId);
		order.setOrderTotal(orderTotal2);
		order.setOrderDate(formatter.format(date));
		
		boolean result = orderDAO.insertCompletedOrder(order);
		
		if (result) {
			System.out.println("Order completed and saved to database");
		} else {
			System.out.println("Order not saved to database");
		}
		
		RequestDispatcher dp = request.getRequestDispatcher("paymentSuccess.jsp");
		dp.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
