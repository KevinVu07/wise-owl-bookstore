package model;

import java.util.List;

import model.BookInCartModel;

// DTO (Data Transfer Object)

public class Cart {
	private List<BookInCartModel> booksInCart;

	public Cart() {
		super();
	}

	public List<BookInCartModel> getBooksInCart() {
		return booksInCart;
	}

	public void setBooksInCart(List<BookInCartModel> booksInCart) {
		this.booksInCart = booksInCart;
	}

	public boolean addBook(BookInCartModel book) {
		if (booksInCart.add(book)) {
			return true;
		}
		return false;
	}

	public void updateBookInCart(int id, int newQty) {
		for (BookInCartModel book : booksInCart) {
			if (book.getId() == id) {
				book.setQty(newQty);
				double newTotal = book.getTotal() * newQty;
				book.setTotal(newTotal);
			}
		}
	}
	

}
