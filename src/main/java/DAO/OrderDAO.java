package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.util.MySqlDBConnector;
import model.CompletedOrderModel;
import model.OrderItemModel;

public class OrderDAO {
	public boolean insertCompletedOrder(CompletedOrderModel order) {

		boolean result = false;

		Connection connection = MySqlDBConnector.makeConnection();
		ResultSet rs = null;
		PreparedStatement ps = null;
		String sqlQuery = "INSERT INTO `order` (order_reference, user_id, order_total, order_date) VALUES (?, ?, ?, ?)";

		try {
			ps = connection.prepareStatement(sqlQuery);
			ps.setString(1, order.getOrderRef());
			ps.setInt(2, order.getUserId());
			ps.setDouble(3, order.getOrderTotal());
			ps.setString(4, order.getOrderDate());

			ps.executeUpdate();

			result = true;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(connection, ps, rs);
		}

		return result;
	}

	public List<CompletedOrderModel> getAllOrderByUserId(int userId) {
		List<CompletedOrderModel> orderList = new ArrayList<CompletedOrderModel>();

		Connection connection = MySqlDBConnector.makeConnection();
		ResultSet rs = null;
		PreparedStatement ps = null;
		String sqlQuery = "SELECT *" + " FROM `order` o" + " WHERE user_id = ?";

		try {
			ps = connection.prepareStatement(sqlQuery);
			ps.setInt(1, userId);
			rs = ps.executeQuery();

			// LOOP EACH ROW -> get value of all columns
			while (rs.next() == true) {
				String orderRef = rs.getString("order_reference");
				double orderTotal = rs.getDouble("order_total");
				String orderDate = rs.getString("order_date");

				CompletedOrderModel order = new CompletedOrderModel(orderRef, userId, orderTotal, orderDate);

				orderList.add(order);
			}
			;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(connection, ps, rs);
		}

		return orderList;
	}

	public CompletedOrderModel getOrderByUserIdAndOrderRef(int userId, String orderRef) {

		CompletedOrderModel order = new CompletedOrderModel();

		Connection connection = MySqlDBConnector.makeConnection();
		ResultSet rs = null;
		PreparedStatement ps = null;
		String sqlQuery = "SELECT *" + " FROM `order` o" + " WHERE user_id = ? AND order_reference = ?";

		try {
			ps = connection.prepareStatement(sqlQuery);
			ps.setInt(1, userId);
			ps.setString(2, orderRef);

			rs = ps.executeQuery();

			if (rs.next() == true) {
				double orderTotal = rs.getDouble("order_total");
				String orderDate = rs.getString("order_date");

				order.setOrderRef(orderRef);
				order.setUserId(userId);
				order.setOrderTotal(orderTotal);
				order.setOrderDate(orderDate);

			}
			;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(connection, ps, rs);
		}

		return order;
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
