package model;

import model.BookInCart;

public class BookInCart {
	private int id;
	private String image;
	private String name;
	private String type;
	private String description;
	private double salePrice;
	private int qty;
	private double total;
	
	public BookInCart(int id, String image, String name, String type, String description, double salePrice, int qty, double total) {
		super();
		this.id = id;
		this.image = image;
		this.name = name;
		this.type = type;
		this.description = description;
		this.salePrice = salePrice;
		this.qty = qty;
		this.total = total;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(double salePrice) {
		this.salePrice = salePrice;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public boolean equals(Object o) {
		BookInCart book = (BookInCart) o;
		if (this.id == book.getId()) {
			return true;
		}
		return false;
	}
	
	
}
