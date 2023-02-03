<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>Book Details Page</title>

<!-- Bootstrap, CSS, and Fontawesome plug in -->
<link rel="stylesheet" href="assets/css/style.css" />
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD"
	crossorigin="anonymous" />
<script src="https://kit.fontawesome.com/7ee7b60095.js"
	crossorigin="anonymous"></script>

<!-- Favicon plugin -->
<link rel="apple-touch-icon" sizes="180x180"
	href="assets/images/favicon/apple-touch-icon.png" />
<link rel="icon" type="image/png" sizes="32x32"
	href="assets/images/favicon/favicon-32x32.png" />
<link rel="icon" type="image/png" sizes="16x16"
	href="assets/images/favicon/favicon-16x16.png" />
<link rel="manifest" href="assets/images/favicon/site.webmanifest" />
<link rel="mask-icon" href="assets/images/favicon/safari-pinned-tab.svg"
	color="#5bbad5" />
<link rel="shortcut icon" href="assets/images/favicon/favicon.ico" />
<meta name="msapplication-TileColor" content="#da532c" />
<meta name="msapplication-config"
	content="assets/images/favicon/browserconfig.xml" />
<meta name="theme-color" content="#ffffff" />
</head>
<body>
	<section>
		<jsp:include page="navbar.jsp" />
	</section>

	<section class="container my-4 h-auto" id="top_book_section">
		<form action="add-to-cart" method="get">
			<div class="d-flex" id="book_details_box">
				<input type="hidden" name="bookId" value="${book.id}" /> 
				<input
					type="hidden" name="bookImage" value="${book.image}" /> <input
					type="hidden" name="bookName" value="${book.name}" /> <input
					type="hidden" name="bookType" value="${book.type}" /> <input
					type="hidden" name="bookDescription" value="${book.description}" />
				<input type="hidden" name="bookPrice" value="${book.salePrice}" />
				<img
					src="${pageContext.request.contextPath}/assets/images/books/${book.image}" />
				<div id="book_details" class="w-75">
					<h2>${book.name}</h2>
					<p class="writer my-2">by ${book.authorName}</p>
					<p class="categories my-2">Category: ${book.categoryName}</p>
					<div class="d-flex">
						<div>
							<i class="fa-solid fa-star"></i> <i class="fa-solid fa-star"></i>
							<i class="fa-solid fa-star"></i> <i class="fa-solid fa-star"></i>
							<i class="fa-solid fa-star-half-stroke"></i>
						</div>
						<p class="mx-2">(10 reviews)</p>
					</div>
					<p class="mb-0">
						<span id="product_version_title"> ${book.type} </span> <br /> <span
							class="label">Edition Number:</span> ${book.editionNumber}<br />
						<span class="label"> Published: </span> ${book.publishedDate}<br />
						<span class="details_isbn"><span class="label">ISBN:
						</span>${book.ISBN}<br /></span>
					</p>
					<p class="mb-0">Book description: ${book.description}</p>
				</div>
				<div id="book_price">
					<h4>${book.type}</h4>
					<hr />
					<p>
						<del>RRP $${book.rrp}</del>
						<br /> <strong>$${book.salePrice}</strong>
					</p>
					<div class="buy_options">
						<button type="submit">Add to cart</button>
						<p class="my-4">
							Also available in <a href="#">old book</a> from $12.50
						</p>
					</div>
				</div>
			</div>
		</form>


	</section>

	<jsp:include page="footer.jsp" />

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN"
		crossorigin="anonymous"></script>
</body>
</html>
