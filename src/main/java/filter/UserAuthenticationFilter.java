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
@WebFilter("/*")
public class UserAuthenticationFilter implements Filter {
	
	 private HttpServletRequest httpRequest;
	 
	    private static final String[] loginRequiredURLs = {
	    		"/account-update", "/cart", "/order-summary", "/add-to-cart", "/order-now"
	    };
	 
	    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
	            throws IOException, ServletException {
	        httpRequest = (HttpServletRequest) request;
	 
	        String path = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());
	 
	        if (path.startsWith("/admin/")) {
	            chain.doFilter(request, response);
	            return;
	        }
	 
	        HttpSession session = httpRequest.getSession(false);
	 
	        boolean isLoggedIn = (session != null && session.getAttribute("email") != null);
	 
	        String loginURI = httpRequest.getContextPath() + "/login";
	        boolean isLoginRequest = httpRequest.getRequestURI().equals(loginURI);
	        boolean isLoginPage = httpRequest.getRequestURI().endsWith("login.jsp");
	 
	        if (isLoggedIn && (isLoginRequest || isLoginPage)) {
	            // the user is already logged in and he's trying to login again
	            // then forward to the homepage
	            httpRequest.getRequestDispatcher("/").forward(request, response);
	 
	        } else if (!isLoggedIn && isLoginRequired()) {
	            // the user is not logged in, and the requested page requires
	            // authentication, then forward to the login page
	            RequestDispatcher dispatcher = httpRequest.getRequestDispatcher("/login.jsp");
	            dispatcher.forward(request, response);
	        } else {
	            // for other requested pages that do not require authentication
	            // or the user is already logged in, continue to the destination
	            chain.doFilter(request, response);
	        }
	    }
	 
	 
	    private boolean isLoginRequired() {
	        String requestURL = httpRequest.getRequestURL().toString();
	 
	        for (String loginRequiredURL : loginRequiredURLs) {
	            if (requestURL.contains(loginRequiredURL)) {
	                return true;
	            }
	        }
	 
	        return false;
	    }
	 
	    public UserAuthenticationFilter() {
	    }
	 
	    public void destroy() {
	    }
	 
	    public void init(FilterConfig fConfig) throws ServletException {
	    }
	 
	}