package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.util.MySqlDBConnector;
import model.OrderListModel;
import model.OrderModel;

public class OrderDAO {

	public boolean insertOrder(OrderModel order) {

		boolean result = false;

		Connection connection = MySqlDBConnector.makeConnection();
		PreparedStatement ps = null;
		String sqlQuery = "INSERT INTO `order_items` (user_id, book_id, order_qty, book_price, order_total, order_date) VALUES (?, ?, ?, ?, ?, ?)";

		try {
			ps = connection.prepareStatement(sqlQuery);
			ps.setInt(1, order.getUserId());
			ps.setInt(2, order.getBookId());
			ps.setInt(3, order.getOrderQty());
			ps.setDouble(4, order.getBookPrice());
			ps.setDouble(5, order.getOrderTotal());
			ps.setString(6, order.getOrderDate());

			ps.executeUpdate();

			result = true;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;

	}

	public OrderModel getOrderByBookIdAndUserId(int bookId, int userId) {
		OrderModel order = null;

		// make connection to MYSQL LOCALHOST, Schema book_store
		Connection connection = MySqlDBConnector.makeConnection();
		ResultSet rs = null;
		PreparedStatement ps = null;
		String sqlQuery = "SELECT * FROM `order_items` WHERE (book_id = ? AND user_id = ? AND order_reference IS NULL)";
		try {
			ps = connection.prepareStatement(sqlQuery);
			ps.setInt(1, bookId);
			ps.setDouble(2, userId);
			// RESULT SET / RESULT GRID
			rs = ps.executeQuery();

			// LOOP EACH ROW -> get value of all columns
			if (rs.next() == true) {
				int orderId = rs.getInt("id");
				double bookPrice = rs.getDouble("book_price");
				int orderQty = rs.getInt("order_qty");
				double orderTotal = rs.getDouble("order_total");
				String orderDate = rs.getString("order_date");

				order = new OrderModel(orderId, userId, bookId, bookPrice, orderQty, orderTotal, orderDate);
			}
			;

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
		return order;
	}

	public List<OrderListModel> getAllOrderByUserId(int userId) {
		List<OrderListModel> orderList = new ArrayList<OrderListModel>();

		// make connection to MYSQL LOCALHOST, Schema book_store
		Connection connection = MySqlDBConnector.makeConnection();
		ResultSet rs = null;
		PreparedStatement ps = null;
		String sqlQuery = "SELECT o.id as order_id, o.user_id, o.book_id, b.name as book_name, b.image, o.book_price, o.order_qty, o.order_total, o.order_date, o.order_reference"
				+ " FROM `order_items` o JOIN `book` b ON b.id = o.book_id" 
				+ " WHERE user_id = ? AND order_reference IS NULL"; 

		try {
			ps = connection.prepareStatement(sqlQuery);
			ps.setInt(1, userId);
			// RESULT SET / RESULT GRID
			rs = ps.executeQuery();

			// LOOP EACH ROW -> get value of all columns
			while (rs.next() == true) {
				int orderId = rs.getInt("order_id");
				int bookId = rs.getInt("book_id");
				String bookName = rs.getString("book_name");
				String bookImage = rs.getString("image");
				double bookPrice = rs.getDouble("book_price");
				int orderQty = rs.getInt("order_qty");
				double orderTotal = rs.getDouble("order_total");
				String orderDate = rs.getString("order_date");

				OrderListModel order = new OrderListModel(orderId, userId, bookId, bookName, bookImage, bookPrice,
						orderQty, orderTotal, orderDate);

				orderList.add(order);
			}
			;

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

		return orderList;
	}
	
	public List<OrderListModel> getAllOrderByUserIdAndOrderRef(int userId, String orderRef) {
		List<OrderListModel> orderList = new ArrayList<OrderListModel>();

		// make connection to MYSQL LOCALHOST, Schema book_store
		Connection connection = MySqlDBConnector.makeConnection();
		ResultSet rs = null;
		PreparedStatement ps = null;
		String sqlQuery = "SELECT o.id as order_id, o.user_id, o.book_id, b.name as book_name, b.image, o.book_price, o.order_qty, o.order_total, o.order_date, o.order_reference"
				+ " FROM `order_items` o JOIN `book` b ON b.id = o.book_id" 
				+ " WHERE user_id = ? AND order_reference = ?"; 

		try {
			ps = connection.prepareStatement(sqlQuery);
			ps.setInt(1, userId);
			ps.setString(2, orderRef);
			// RESULT SET / RESULT GRID
			rs = ps.executeQuery();

			// LOOP EACH ROW -> get value of all columns
			while (rs.next() == true) {
				int orderId = rs.getInt("order_id");
				int bookId = rs.getInt("book_id");
				String bookName = rs.getString("book_name");
				String bookImage = rs.getString("image");
				double bookPrice = rs.getDouble("book_price");
				int orderQty = rs.getInt("order_qty");
				double orderTotal = rs.getDouble("order_total");
				String orderDate = rs.getString("order_date");

				OrderListModel order = new OrderListModel(orderId, userId, bookId, bookName, bookImage, bookPrice,
						orderQty, orderTotal, orderDate);

				orderList.add(order);
			}
			;

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

		return orderList;
	}


	public void updateOrderReferenceByUserId(int userId, String orderRef) {

		// make connection to MYSQL LOCALHOST, Schema book_store
		Connection connection = MySqlDBConnector.makeConnection();
		PreparedStatement ps = null;
		String sqlQuery = "UPDATE `order_items` SET `order_reference` = ? WHERE (`user_id` = ? AND order_reference IS NULL)";

		try {
			ps = connection.prepareStatement(sqlQuery);
			ps.setString(1, orderRef);
			ps.setInt(2, userId);
			// RESULT SET / RESULT GRID
			ps.executeUpdate();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
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
	
	public List<String> getOrderRefListByUserId(int userId) {
		
		List<String> orderRefList = new ArrayList<String>();
		
		
		// make connection to MYSQL LOCALHOST, Schema wise-owl-bookstore
		Connection connection = MySqlDBConnector.makeConnection();
		ResultSet rs = null;
		PreparedStatement ps = null;
		String sqlQuery = "SELECT COUNT(o.user_id), o.order_reference"
				+ " FROM `order_items` as o" 
				+ " WHERE user_id = ? AND order_reference IS NOT NULL"
				+ " GROUP BY order_reference";

		try {
			ps = connection.prepareStatement(sqlQuery);
			ps.setInt(1, userId);
			// RESULT SET / RESULT GRID
			rs = ps.executeQuery();

			// LOOP EACH ROW -> get value of all columns
			while (rs.next() == true) {
				String orderRef = rs.getString("order_reference");
				orderRefList.add(orderRef);
			}
			;

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

		return orderRefList;
	}
	


}
