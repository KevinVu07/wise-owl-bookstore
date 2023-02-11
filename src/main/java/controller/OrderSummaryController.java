package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

import DAO.OrderItemDAO;
import model.OrderItemModel;

/**
 * Servlet implementation class OrderSummaryController
 */
@WebServlet("/order-summary")
public class OrderSummaryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderSummaryController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession(false);
		
		
		int userId = Integer.parseInt(String.valueOf(session.getAttribute("id")));
		
		OrderItemDAO orderItemDAO = new OrderItemDAO();
		
		
		List<OrderItemModel> orderItemList = orderItemDAO.getAllOrderByUserId(userId);
		
		double subTotalItems = 0;
		for (OrderItemModel order : orderItemList) {
			subTotalItems = subTotalItems + order.getOrderTotal();
			subTotalItems = (double)Math.round(subTotalItems * 100d) / 100d; 
		}
		
		double shippingFee = 7.50;
		if (subTotalItems >= 50 || subTotalItems == 0) {
			shippingFee = 0;
		}
		
		
		
		double subTotal = subTotalItems / 1.1;
		
		double tax = subTotalItems * 0.1;
		tax = (double)Math.round(tax * 100d) / 100d;
		
		double orderTotal = subTotal + shippingFee + tax;
		
		
		session.setAttribute("orderItemList", orderItemList);
		session.setAttribute("subTotal", String.format("%.2f",subTotal));
		session.setAttribute("shippingFee", String.format("%.2f",shippingFee));
//		session.setAttribute("total", String.format("%.2f",total));
		session.setAttribute("orderTotal", String.format("%.2f",orderTotal));
		session.setAttribute("tax", String.format("%.2f",tax));
		
		RequestDispatcher dp = request.getRequestDispatcher("checkout.jsp");
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
