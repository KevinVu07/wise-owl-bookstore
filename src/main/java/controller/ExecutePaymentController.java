package controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
 
import com.paypal.api.payments.*;
import com.paypal.base.rest.PayPalRESTException;

import DAO.OrderDAO;
import DAO.OrderItemDAO;
import model.CompletedOrderModel;
import model.OrderItemModel;
import model.OrderModel;
import payment.util.PaymentServices;
/**
 * Servlet implementation class ExecutePaymentController
 */
@WebServlet("/execute-payment")
public class ExecutePaymentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExecutePaymentController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 	String paymentId = request.getParameter("paymentId");
	        String payerId = request.getParameter("PayerID");
	        

	 
	        try {
	            PaymentServices paymentServices = new PaymentServices();
	            Payment payment = paymentServices.executePayment(paymentId, payerId);
	            
	            HttpSession session = request.getSession(false);
	    		int userId = Integer.parseInt(String.valueOf(session.getAttribute("id")));
	    		double orderTotal = Double.parseDouble(String.valueOf(session.getAttribute("orderTotal")));
	    		String orderRef = String.valueOf(Math.round(Math.random() * 100000));
	    		
	    		OrderItemDAO orderItemDAO = new OrderItemDAO();
	    		orderItemDAO.updateOrderReferenceByUserId(userId, orderRef);
	    		
	    		
	    		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
	    		Date date = new Date();

	    		
	    		OrderDAO orderDAO = new OrderDAO();
	    		CompletedOrderModel order = new CompletedOrderModel();
	    		
	    		order.setOrderRef(orderRef);
	    		order.setUserId(userId);
	    		order.setOrderTotal(orderTotal);
	    		order.setOrderDate(formatter.format(date));
	    		
	    		boolean result = orderDAO.insertCompletedOrder(order);
	    		
	    		if (result) {
	    			System.out.println("Order completed and saved to database");
	    		} else {
	    			System.out.println("Order not saved to database");
	    		}

	            PayerInfo payerInfo = payment.getPayer().getPayerInfo();
	            Transaction transaction = payment.getTransactions().get(0);
	             
	            request.setAttribute("payer", payerInfo);
	            request.setAttribute("transaction", transaction);          
	 
	            request.getRequestDispatcher("receipt.jsp").forward(request, response);
	            
	             
	        } catch (PayPalRESTException ex) {
	            request.setAttribute("errorMessage", ex.getMessage());
	            ex.printStackTrace();
	            request.getRequestDispatcher("paymentError.jsp").forward(request, response);
	        }
	    }
	}


