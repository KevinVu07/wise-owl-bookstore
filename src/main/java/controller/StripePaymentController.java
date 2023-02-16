package controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;

import DAO.OrderDAO;
import DAO.OrderItemDAO;
import model.CompletedOrderModel;
import model.OrderItemModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class StripePaymentController
 */
@WebServlet("/stripe-payment")
public class StripePaymentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String STRIPE_API_KEY = "sk_test_51MZRusGz2QGichbmqD1cOeHTnd36EsQRNuVdcpxZ9a753NrrESPSJz9HPWttLcnGk0dumrGSh1RqfNCiP1P7quhn00F804bj79";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StripePaymentController() {
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
		
		String orderName = "Wise Owl Bookstore Books Purchase";
		BigDecimal a = new BigDecimal(request.getParameter("orderTotal"));
		System.out.println("a is " + a);
		BigDecimal b = new BigDecimal("100.00");
		System.out.println("b is " + b);
		BigDecimal orderTotal = a.multiply(b);
		System.out.println(orderTotal);

		try {
			System.out.println();
			Stripe.apiKey = STRIPE_API_KEY;
			String YOUR_DOMAIN = "http://www.wiseowlbookshop.com";
			
			
			SessionCreateParams params = SessionCreateParams.builder()
					.setMode(SessionCreateParams.Mode.PAYMENT)
					.addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
					.setSuccessUrl(YOUR_DOMAIN + "/payment-success")
					.setCancelUrl(YOUR_DOMAIN + "/paymentCancel.jsp")
					.setAutomaticTax(
				              SessionCreateParams.AutomaticTax.builder()
				                .setEnabled(true)
				                .build())
					.addLineItem(SessionCreateParams.LineItem.builder().setQuantity(1L)
							// Provide the exact Price ID (for example, pr_1234) of the product you want to
							// sell
							 .setPriceData(
						              SessionCreateParams.LineItem.PriceData.builder()
						                .setCurrency("aud")
						                .setUnitAmountDecimal(orderTotal)    
						                .setTaxBehavior(
						                        SessionCreateParams.LineItem.PriceData.TaxBehavior.INCLUSIVE)
						                .setProductData(
						                  SessionCreateParams.LineItem.PriceData.ProductData.builder()
						                  	.setDescription("books")
						                    .setName(orderName)
						                    .build())
						                .build())
						            .build())
						          .build();
			Session session = Session.create(params);
			
			// If payment has been successful, update order table database with completed order
			
			

			response.sendRedirect(session.getUrl());

		} catch (StripeException e) {
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
