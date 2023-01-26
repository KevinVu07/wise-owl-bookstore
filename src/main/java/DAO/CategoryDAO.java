package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.util.MySqlDBConnector;
import entity.Category;

public class CategoryDAO {

	public List<Category> getAllCategory() {
		List<Category> categoryList = new ArrayList<Category>();

		// make connection to MYSQL LOCALHOST, Schema book_store
		Connection connection = MySqlDBConnector.makeConnection();
		ResultSet rs = null;
		PreparedStatement ps = null;
		
		// run SQL QUERRY "SELECT FROM category"
		// RESULT SET / RESULT GRID
		String sqlQuery = "SELECT * FROM `category`";
		try {
			ps = connection.prepareStatement(sqlQuery);

			rs = ps.executeQuery();

			// LOOP EACH ROW -> get value of all columns
			while (rs.next() == true) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				// Add data to CATEGORY object -> insert object to categoryList
				Category category = new Category(id, name);

				categoryList.add(category);
			}
			;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		finally {
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
		
		return categoryList;
	}

}
