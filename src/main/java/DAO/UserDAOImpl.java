package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;

import entity.User;

public class UserDAOImpl implements UserDAO {

	private Connection conn;

	public UserDAOImpl(Connection conn) {
		super();
		this.conn = conn;
	}

	@Override
	public boolean userRegister(User user) {
		
		boolean f = false;
		
		try {
			String sql = "insert into user(first_name, last_name, email, password) values(?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, user.getFirstName());
			ps.setString(2, user.getLastName());
			ps.setString(3, user.getEmail());
			ps.setString(4, user.getPassword());
			
			int i = ps.executeUpdate();
			
			if (i==1) {
				f = true;
			}
		
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return f;
	}

}
