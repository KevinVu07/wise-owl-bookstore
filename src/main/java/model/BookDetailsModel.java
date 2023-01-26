package model;

import java.util.Date;

public class BookDetailsModel {
	private int id;
	private String name;
	private String description;
	private int categoryId;
	private String categoryName;
	private String type;
	private int editionNumber;
	private Date publishedDate;
	private String ISBN;
	private double rrp;
	private double salePrice;
	private double rating;
	private int authorId;
	private String authorName;
	private String image;
	
	
	
	public BookDetailsModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BookDetailsModel(int id, String name, String description, int categoryId, String categoryName, String type,
			int editionNumber, Date publishedDate, String iSBN, double rrp, double salePrice, double rating,
			int authorId, String authorName, String image) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.type = type;
		this.editionNumber = editionNumber;
		this.publishedDate = publishedDate;
		ISBN = iSBN;
		this.rrp = rrp;
		this.salePrice = salePrice;
		this.rating = rating;
		this.authorId = authorId;
		this.authorName = authorName;
		this.image = image;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getEditionNumber() {
		return editionNumber;
	}

	public void setEditionNumber(int editionNumber) {
		this.editionNumber = editionNumber;
	}

	public Date getPublishedDate() {
		return publishedDate;
	}

	public void setPublishedDate(Date publishedDate) {
		this.publishedDate = publishedDate;
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public double getRrp() {
		return rrp;
	}

	public void setRrp(double rrp) {
		this.rrp = rrp;
	}

	public double getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(double salePrice) {
		this.salePrice = salePrice;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public int getAuthorId() {
		return authorId;
	}

	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	
	
	
	
}
