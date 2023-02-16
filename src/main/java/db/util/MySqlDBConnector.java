package db.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySqlDBConnector {
	public static Connection makeConnection() {
		try {
			// 1. load driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			// 2. create connection string with username + password to connect to mysql
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://us-cdbr-east-06.cleardb.net/heroku_12221f7f22263d2", "bbe159c6a9c2ff", "345f4688");
			return conn;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
}
