package controller;

import java.io.IOException;
import java.util.List;



import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HomeController
 */
@WebServlet("/home")
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HomeController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// BOOK
		// create new object bookDAO from class BookDAO -> so it can call function getAll() from BookDAO
//		BookDAO bookDAO = new BookDAO();
//		
//		// get the book list by calling function getAll()
//		List<Book> bookList = bookDAO.getAll();
//		
//	
//		// CATEGORY
//		CategoryDAO categoryDAO = new CategoryDAO();
//		List<Category> categoryList = categoryDAO.getAllCategory();
//		
//		
//		request.setAttribute("bookList", bookList);
//		request.setAttribute("categoryList", categoryList);
		
		RequestDispatcher dp = request.getRequestDispatcher("home.jsp");
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
