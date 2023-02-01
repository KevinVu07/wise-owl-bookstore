package model;


import java.util.List;

import model.BookInCart;

// DTO (Data Transfer Object)

public class Cart {
	private List<BookInCart> booksInCart;

	public Cart() {
		super();
	}

	public List<BookInCart> getBooksInCart() {
		return booksInCart;
	}

	public void setBooksInCart(List<BookInCart> booksInCart) {
		this.booksInCart = booksInCart;
	}

	public boolean addBook(BookInCart book) {
		if (booksInCart.add(book)) {
			return true;
		}
		return false;
	}
	
	
 
}
