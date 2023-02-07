package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.util.MySqlDBConnector;

public class AuthorDAO {
	public boolean authorExisted(String authorName) {
		Connection connection = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		try {
			connection = MySqlDBConnector.makeConnection();

			String sqlQuery = "SELECT * FROM author WHERE name LIKE ?";
			ps = connection.prepareStatement(sqlQuery);
			ps.setString(1, "%" + authorName + "%");

			rs = ps.executeQuery();
			
			if (!rs.next()) {
				return false;
			} 

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(connection, ps, rs);
		}
		return true;
	}
	
	public int getAuthorIdByAuthorName(String authorName) {
		Connection connection = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		int authorId = 0;
		try {
			connection = MySqlDBConnector.makeConnection();

			String sqlQuery = "SELECT * FROM author WHERE name LIKE ?";
			ps = connection.prepareStatement(sqlQuery);
			ps.setString(1, "%" + authorName + "%");

			rs = ps.executeQuery();
			
			if (rs.next()) {
				authorId = rs.getInt("id");
			} 
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(connection, ps, rs);
		}
		return authorId;
	}
	
	public void addAuthor(String authorName) {
		Connection connection = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		try {
			connection = MySqlDBConnector.makeConnection();

			String sqlQuery = "INSERT INTO `author` (`name`) VALUES (?)";
			ps = connection.prepareStatement(sqlQuery);
			ps.setString(1, authorName);

			ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(connection, ps, rs);
		}
	}
	
	public String getAuthorNameByAuthorId(int authorId) {
		Connection connection = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		String authorName = "";
		try {
			connection = MySqlDBConnector.makeConnection();

			String sqlQuery = "SELECT * FROM author WHERE id = ?";
			ps = connection.prepareStatement(sqlQuery);
			ps.setInt(1, authorId);

			rs = ps.executeQuery();
			
			if (rs.next()) {
				authorName = rs.getString("name");
			} 
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(connection, ps, rs);
		}
		return authorName;
	}
	
	private void close(Connection connection, Statement stm, ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (stm != null) {
				stm.close();
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
