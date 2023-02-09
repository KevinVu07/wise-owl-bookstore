package model;

public class OrderItemModel {
	private int id;
	private int userId;
	private int bookId;
	private String bookName;
	private String bookImage;
	private double bookPrice;
	private int orderQty;
	private double orderTotal;
	private String orderDate;
	
	public OrderItemModel() {};
	
	public OrderItemModel(int id, int userId, int bookId, String bookName, String bookImage, double bookPrice, int orderQty, double orderTotal, String orderDate) {
		super();
		this.id = id;
		this.userId = userId;
		this.bookId = bookId;
		this.bookName = bookName;
		this.bookImage = bookImage;
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

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getBookImage() {
		return bookImage;
	}

	public void setBookImage(String bookImage) {
		this.bookImage = bookImage;
	}
	
	
	
}
