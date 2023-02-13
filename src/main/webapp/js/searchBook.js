const searchBook = () => {
	const searchbox = document.getElementById("search_book").value.toLowerCase();
	console.log(searchbox);
	const book = document.querySelectorAll(".book_card");
	console.log(book);

	book.forEach(book => {
		const isVisible = book.innerText.toLowerCase().includes(searchbox);
		book.classList.toggle("d-none", !isVisible)
		}) 
}