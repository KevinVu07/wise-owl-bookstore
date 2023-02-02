package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.UserDAOImpl;
import DAO.UserDAOLogin;
import db.util.MySqlDBConnector;
import entity.User;

/**
 * Servlet implementation class SettingsController
 */
@WebServlet("/account-update")
public class AccountUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AccountUpdateController() {
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
		RequestDispatcher dp = request.getRequestDispatcher("account.jsp");
		dp.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		HttpSession session = request.getSession(false);
		
		

		String id = String.valueOf(session.getAttribute("id"));
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = String.valueOf(session.getAttribute("email"));
		String password = String.valueOf(session.getAttribute("password"));
		String address = request.getParameter("address");
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		String postcode = request.getParameter("postcode");

		Connection connection = MySqlDBConnector.makeConnection();
		
		ResultSet rs = null;
		PreparedStatement ps = null;
		String sqlQuery = "UPDATE `user` SET `first_name` = ?, `last_name` = ?, `address` = ?, `city` = ?, `state` = ?, `postcode` = ? WHERE (`id` = ?);";

		try {
			ps = connection.prepareStatement(sqlQuery);
			ps.setString(1, firstName);
			ps.setString(2, lastName);
			ps.setString(3, address);
			ps.setString(4, city);
			ps.setString(5, state);
			ps.setString(6, postcode);
			ps.setInt(7, Integer.parseInt(id));

			ps.executeUpdate();
			
			
			String accountUpdated = "Account details updated!";
			request.setAttribute("accountUpdated", accountUpdated);
			
			UserDAOLogin userDAOLogin = new UserDAOLogin();
			User user = userDAOLogin.getUserByEmailAndPassword(email, password);
			
			session.setAttribute("firstName", user.getFirstName());
			session.setAttribute("lastName", user.getLastName());
			session.setAttribute("address", user.getAddress());
			session.setAttribute("city", user.getCity());
			session.setAttribute("state", user.getState());
			session.setAttribute("postcode", user.getPostcode());
			
			RequestDispatcher dp = request.getRequestDispatcher("account.jsp");
			dp.forward(request, response);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (connection != null) {
					connection.close();
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
