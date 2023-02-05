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

import DAO.OrderDAO;
import model.OrderListModel;

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
		
		OrderDAO orderDAO = new OrderDAO();
		
		List<OrderListModel> orderList = orderDAO.getAllOrderByUserId(userId);
		
		double subTotal = 0;
		for (OrderListModel order : orderList) {
			subTotal = subTotal + order.getOrderTotal();
			subTotal = (double)Math.round(subTotal * 100d) / 100d; 
		}
		
		System.out.println("subtotal is " + subTotal);
		
		double shippingFee = 7.50;
		if (subTotal >= 50) {
			shippingFee = 0;
		}
		
		System.out.println("shipping fee is " + shippingFee);
		
		double tax = subTotal * 0.1;
		tax = (double)Math.round(tax * 100d) / 100d;
		
		double total = subTotal + shippingFee + tax;
		
		System.out.println("total fee is " + total);
		
		session.setAttribute("orderList", orderList);
		session.setAttribute("subTotal", subTotal);
		session.setAttribute("shippingFee", shippingFee);
		session.setAttribute("total", total);
		session.setAttribute("tax", tax);
		
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
