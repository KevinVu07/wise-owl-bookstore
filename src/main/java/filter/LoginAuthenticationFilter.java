package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import DAO.UserDAOLogin;

/**
 * Servlet Filter implementation class LoginAuthenticationFilter
 */
@WebFilter(urlPatterns = {"/new-books", "/book" })
public class LoginAuthenticationFilter extends HttpFilter implements Filter {
	
	/**
	 * @see HttpFilter#HttpFilter()
	 */
	public LoginAuthenticationFilter() {
		super();
		// TODO Auto-generated constructor stub
	}
	


	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession(false);

		boolean isLoggedIn = (session != null && session.getAttribute("email") != null);
		System.out.println(session.getAttribute("email"));

		String loginURI = httpRequest.getContextPath() + "/login.jsp";

		boolean isLoginRequest = httpRequest.getRequestURI().equals(loginURI);

		boolean isLoginPage = httpRequest.getRequestURI().endsWith("login.jsp");
		
		System.out.println(isLoggedIn);
		System.out.println(isLoginRequest);
		System.out.println(isLoginPage);

		if (isLoggedIn && (isLoginRequest || isLoginPage)) {
			// the user is already logged in and they are trying to login again
			// then forward to the homepage
			RequestDispatcher dispatcher = request.getRequestDispatcher("/home");
			dispatcher.forward(request, response);
		} else if (isLoggedIn || isLoginRequest) {
			// continue the filter chain
			// allow the request to reach the destination
			chain.doFilter(request, response);
		} else {
			// the user is not logged in, so authentication is required
			// forward to the login page
			RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
			dispatcher.forward(request, response);
		}

		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
