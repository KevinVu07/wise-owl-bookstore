package model;

import java.text.SimpleDateFormat;  

public class OrderModel {
	private int id;
	private int userId;
	private int bookId;
	private double bookPrice;
	private int orderQty;
	private double orderTotal;
	private String orderDate;
	
	public OrderModel() {};
	
	public OrderModel(int id, int userId, int bookId, double bookPrice, int orderQty, double orderTotal, String orderDate) {
		super();
		this.id = id;
		this.userId = userId;
		this.bookId = bookId;
		this.bookPrice = bookPrice;
		this.orderQty = orderQty;
		this.orderTotal = orderTotal;
		this.orderDate = orderDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public int getOrderQty() {
		return orderQty;
	}

	public void setOrderQty(int orderQty) {
		this.orderQty = orderQty;
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

	public double getBookPrice() {
		return bookPrice;
	}

	public void setBookPrice(double bookPrice) {
		this.bookPrice = bookPrice;
	}
	
	
	
}
