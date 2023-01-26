package DAO;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.util.MySqlDBConnector;

public class UserDAOLogin {

	public boolean isUserValid(String email, String password) {
		boolean result = false;

		Connection connection = MySqlDBConnector.makeConnection();
		ResultSet rs = null;
		PreparedStatement ps = null;

		// run SQL QUERRY "SELECT FROM category"
		// RESULT SET / RESULT GRID
		String sqlQuery = "SELECT * FROM `user` WHERE email = ? AND password = ?";
		try {
			ps = connection.prepareStatement(sqlQuery);
			ps.setString(1, email);
			ps.setString(2, password);

			rs = ps.executeQuery();

			// LOOP EACH ROW -> get value of all columns
			if (rs.next() == true) {
				result = true;
			}
			;

		} catch (SQLException e) {
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

		return result;
	}

}


