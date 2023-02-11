package controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.paypal.base.rest.PayPalRESTException;

import model.CheckoutDetail;
import model.OrderItemModel;
import model.OrderModel;
import payment.util.PaymentServices;

/**
 * Servlet implementation class AuthorizePaymentController
 */
@WebServlet("/authorize-payment")
public class AuthorizePaymentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AuthorizePaymentController() {
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
		
			HttpSession session = request.getSession(false);
		
			List<OrderItemModel> orderItemList = (List<OrderItemModel>) session.getAttribute("orderItemList");
			
	        String subtotal = String.valueOf(session.getAttribute("subTotal"));
	        String shippingFee = String.valueOf(session.getAttribute("shippingFee"));
	        String tax = String.valueOf(session.getAttribute("tax"));
	        String total = String.valueOf(session.getAttribute("orderTotal"));
	        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
	        Date checkoutDate = new Date();
	        
	        CheckoutDetail checkoutDetail = new CheckoutDetail();
	        
	        checkoutDetail.setOrderList(orderItemList);
	        checkoutDetail.setSubTotal(Double.parseDouble(subtotal));
	        checkoutDetail.setShippingFee(Double.parseDouble(shippingFee));
	        checkoutDetail.setTax(Double.parseDouble(tax));
	        checkoutDetail.setTotal(Double.parseDouble(total));
	        checkoutDetail.setCheckOutDate(checkoutDate);
	         
	 
	        try {
	        	String firstName = String.valueOf(session.getAttribute("firstName"));
	        	String lastName = String.valueOf(session.getAttribute("lastName"));
	        	String email = String.valueOf(session.getAttribute("email"));
	        	
	            PaymentServices paymentServices = new PaymentServices();
	            String approvalLink = paymentServices.authorizePayment(checkoutDetail, firstName, lastName, email);
	 
	            response.sendRedirect(approvalLink);
	             
	        } catch (PayPalRESTException ex) {
	            request.setAttribute("errorMessage", ex.getMessage());
	            ex.printStackTrace();
	            request.getRequestDispatcher("paymentError.jsp").forward(request, response);
	        }
	    }
	}


