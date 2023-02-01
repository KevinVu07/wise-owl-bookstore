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

	public void updateBookInCart(int id) {
		for (BookInCart book : booksInCart) {
			if (book.getId() == id) {
				int newQty = book.getQty() + 1;
				book.setQty(newQty);
				double newTotal = book.getTotal() * newQty;
				book.setTotal(newTotal);
			}
		}
	}

}
