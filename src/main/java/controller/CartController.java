package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.BookInCartDAO;
import db.util.MySqlDBConnector;
import model.BookInCartModel;
import model.CartModel;
import model.OrderListModel;

/**
 * Servlet implementation class CartController
 */
@WebServlet("/cart")
public class CartController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CartController() {
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
			
		BookInCartDAO bookInCartDAO = new BookInCartDAO();
		
		List<BookInCartModel> booksInCart = bookInCartDAO.getAll();
		
		request.setAttribute("booksInCart", booksInCart);
		
		double cartTotal = 0;
		for (BookInCartModel book : booksInCart) {
			cartTotal = cartTotal + book.getTotal();
			cartTotal = (double)Math.round(cartTotal * 100d) / 100d; 
		}
		
		session.setAttribute("cartTotal", cartTotal);
		
		RequestDispatcher dp = request.getRequestDispatcher("cart.jsp");
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
