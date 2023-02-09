package model;

public class CompletedOrderModel {
	private String orderRef;
	private int userId;
	private double orderTotal;
	private String orderDate;
	
	public CompletedOrderModel() {
		super();
	}

	public CompletedOrderModel(String orderRef, int userId, double orderTotal, String orderDate) {
		super();
		this.orderRef = orderRef;
		this.userId = userId;
		this.orderTotal = orderTotal;
		this.orderDate = orderDate;
	}
	
	

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getOrderRef() {
		return orderRef;
	}

	public void setOrderRef(String orderRef) {
		this.orderRef = orderRef;
	}

	public double getOrderTotal() {
		return orderTotal;
	}

	public void setOrderTotal(double orderTotal) {
		this.orderTotal = orderTotal;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	
	
	
}
