package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.BookInCart;
import model.Cart;

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

		// add book to cart
		String id = request.getParameter("bookId");
		String image = request.getParameter("bookImage");
		String name = request.getParameter("bookName");
		String type = request.getParameter("bookType");
		String description = request.getParameter("bookDescription");
		String salePrice = request.getParameter("bookPrice");
		int qty = 1;
		Double total = 50.00;
		BookInCart book = new BookInCart(Integer.parseInt(id), image, name, type, description, Double.parseDouble(salePrice), qty, total);
		List<BookInCart> booksInCart = new ArrayList<BookInCart>();
		booksInCart.add(book);
		
		HttpSession session = request.getSession();
		
		
		if (session.getAttribute("cart") == null) {
			Cart cart = new Cart();
			cart.setBooksInCart(booksInCart);
			session.setAttribute("cart", cart);
		} else {
			Cart cart = (Cart) session.getAttribute("cart");
			cart.addBook(book);
		}
//		cart.getBooks().add(book);
		
		
		
		
		
		// redirect to book details page
		
		response.sendRedirect("cart.jsp");
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
