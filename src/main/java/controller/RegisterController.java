package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.UserDAOImpl;
import db.util.MySqlDBConnector;
import entity.User;

/**
 * Servlet implementation class Register
 */
@WebServlet("/register")
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterController() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dp = request.getRequestDispatcher("register.jsp");
		dp.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		try {
			
				String firstName = request.getParameter("firstName");
				String lastName = request.getParameter("lastName");
				String email = request.getParameter("email");
				String password = request.getParameter("password");
				
				System.out.println(firstName + " " + lastName + " " + email + " " + password );
				
				User user = new User();
				user.setFirstName(firstName);
				user.setLastName(lastName);
				user.setEmail(email);
				user.setPassword(password);
				
				UserDAOImpl dao = new UserDAOImpl(MySqlDBConnector.makeConnection());
				boolean f = dao.userRegister(user);
				if (f) {
					
					session.setAttribute("successMsg", "User Registration Successfully");
					response.sendRedirect("register.jsp");
					
					
				} else {
					session.setAttribute("failMsg", "Something wrong on the server");
					response.sendRedirect("register.jsp");
				}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

}
