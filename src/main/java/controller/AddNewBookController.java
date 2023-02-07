package controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;  
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import DAO.AuthorDAO;
import DAO.BookDAO;
import model.BookDetailsModel;

/**
 * Servlet implementation class BookBOController
 */
@WebServlet("/add-new-book")
@MultipartConfig
public class AddNewBookController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddNewBookController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dp = request.getRequestDispatcher("addNewBook.jsp");
		dp.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			System.out.println("I am here");
			String bookName = request.getParameter("bookName");
			System.out.println(bookName);
			String bookDescription = request.getParameter("bookDescription");
			System.out.println(bookDescription);
			String bookCategory = request.getParameter("bookCategory");
			System.out.println(bookCategory);
			
			int categoryId = 0;
			switch (bookCategory) {
			case "Novel":
				categoryId = 1;
				break;
			case "Self Help":
				categoryId = 2;
				break;
			case "Educational":
				categoryId = 3;
				break;
			default:
				break;
			}
			
			System.out.println(categoryId);
			
			String bookType = request.getParameter("bookType");
			int editionNumber = Integer.parseInt(request.getParameter("editionNumber"));
			String date = request.getParameter("publishedDate");
			Date publishedDate= new SimpleDateFormat("dd-MM-yyyy").parse(date);  
			String ISBN = request.getParameter("ISBN");
			double rrp = Double.parseDouble(request.getParameter("rrp"));
			double salePrice = Double.parseDouble(request.getParameter("salePrice"));
			
			
			String authorName = request.getParameter("authorName");
			AuthorDAO authorDAO = new AuthorDAO();
			boolean authorExisted = authorDAO.authorExisted(authorName);
			int authorId = 0;
			if (authorExisted) {
				authorId = authorDAO.getAuthorIdByAuthorName(authorName);
				authorName = authorDAO.getAuthorNameByAuthorId(authorId);
			} else {
				authorDAO.addAuthor(authorName);
				authorId = authorDAO.getAuthorIdByAuthorName(authorName);
			}
			
			System.out.println("I am here 2");
			
			Part part = request.getPart("image");
			String fileName = part.getSubmittedFileName();
			
			BookDetailsModel book = new BookDetailsModel();
			
			
			book.setName(bookName);
			book.setDescription(bookDescription);
			book.setCategoryId(categoryId);
			book.setCategoryName(bookCategory);
			book.setType(bookType);
			book.setEditionNumber(editionNumber);
			book.setPublishedDate(publishedDate);
			book.setISBN(ISBN);
			book.setRrp(rrp);
			book.setSalePrice(salePrice);
			book.setAuthorId(authorId);
			book.setAuthorName(authorName);
			book.setImage(fileName);
			
			BookDAO bookDAO = new BookDAO();
			boolean bookAdded = bookDAO.addBook(book);
			
			HttpSession session = request.getSession(false);
			
			if (bookAdded) {
				String path = getServletContext().getRealPath("") + "assets/images/books";
				System.out.println(path);
				
				File file = new File(path);
				
				part.write(path + File.separator + fileName);
				
				String bookAddSuccess = "New book has been added successfully";
				session.setAttribute("bookAddSuccess", bookAddSuccess);
				response.sendRedirect("addNewBook.jsp");
			} else {
				String bookAddFail = "Book add failed. Something wrong on server";
				session.setAttribute("bookAddFail", bookAddFail);
				response.sendRedirect("addNewBook.jsp");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

}
