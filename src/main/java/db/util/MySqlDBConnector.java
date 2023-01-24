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
					"jdbc:mysql://localhost:3306/wise-owl-bookstore", "wiseowl_admin", "Wiseowl2023");
			System.out.println(conn);
			return conn;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
}
