package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.UserDAOLogin;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dp = request.getRequestDispatcher("login.jsp");
		dp.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();  // get the current session from the browser
		
		String email = request.getParameter("email");  			// set values to email from parameter sent with post method from login.jsp
		String password = request.getParameter("password");		// set values to password from parameter sent with post method from login.jsp

		UserDAOLogin userDAOLogin = new UserDAOLogin();
		boolean result = userDAOLogin.isUserValid(email, password);

		if (result) {

			session.setAttribute("email", email);
			response.sendRedirect("home");

		} else {
			String loginError = "The email address or password entered was incorrect. Please try again.";
			request.setAttribute("loginError", loginError);
			RequestDispatcher dp = request.getRequestDispatcher("login");
			dp.forward(request, response);
		}
	}

}
