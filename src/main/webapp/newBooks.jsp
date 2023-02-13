<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>Book List Page</title>

<!-- Bootstrap, CSS, and Fontawesome plug in -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css" />
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD"
	crossorigin="anonymous" />
<script src="https://kit.fontawesome.com/7ee7b60095.js"
	crossorigin="anonymous"></script>

<!-- Favicon plugin -->
<link rel="apple-touch-icon" sizes="180x180"
	href="${pageContext.request.contextPath}/assets/images/favicon/apple-touch-icon.png" />
<link rel="icon" type="image/png" sizes="32x32"
	href="${pageContext.request.contextPath}/assets/images/favicon/favicon-32x32.png" />
<link rel="icon" type="image/png" sizes="16x16"
	href="${pageContext.request.contextPath}/assets/images/favicon/favicon-16x16.png" />
<link rel="manifest"
	href="${pageContext.request.contextPath}/assets/images/favicon/site.webmanifest" />
<link rel="mask-icon"
	href="${pageContext.request.contextPath}/assets/images/favicon/safari-pinned-tab.svg"
	color="#5bbad5" />
<link rel="shortcut icon"
	href="${pageContext.request.contextPath}/assets/images/favicon/favicon.ico" />
<meta name="msapplication-TileColor" content="#da532c" />
<meta name="msapplication-config"
	content="${pageContext.request.contextPath}/assets/images/favicon/browserconfig.xml" />
<meta name="theme-color" content="#ffffff" />
</head>
<body>
	<section>
		<jsp:include page="navbar.jsp" />

		
	</section>

	<section>
		<div class="book_list">
			<c:if test="${addBookSuccess != null}">
						<p class="mt-2 text-center text-success">${addBookSuccess}</p>
						<c:remove var="addBookSuccess" scope="session" />
					</c:if>
			<h1>Book List</h1>
			<div>
				<form class="d-flex mb-2 justify-content-center align-items-center">
					<i class="mx-2 fas fa-search"></i>
					<input class="w-50" type="text" name="" id="search_book" placeholder="Search books" onkeyup="searchBook()">
				</form>
			</div>
			
			<div class="book_list_box">
				<c:forEach var="book" items="${bookList}">
					<div class="book_card d-flex flex-column justify-content-between">
						<div class="book_image">
							<img
								src="${pageContext.request.contextPath}/assets/images/books/${book.image}" />
						</div>
						<div class="book_tag">
							<h4>${book.name}</h4>
							<p class="writer">${book.authorName}</p>
							<div class="categories">${book.categoryName}</div>
							<p class="book_price">
								$${book.salePrice}<sub><del>$${book.rrp}</del></sub>
							</p>
						</div>
						<div id="purchase" class="d-flex flex-column justify-content-between">
							<div>
								<a id="bookDetailsLink" href="book?id=${book.id}" class="book_btn mb-2">Details</a>
							</div>
							<div class="d-flex justify-content-center">
								<form class="mt-3 mx-1" action="add-to-cart" method="get">
									<div>
										<input type="hidden" name="bookId" value="${book.id}" /> 
										<input type="hidden" name="bookImage" value="${book.image}" /> 
										<input type="hidden" name="bookName" value="${book.name}" /> 
										<input type="hidden" name="bookType" value="${book.type}" /> 
										<input type="hidden" name="bookDescription" value="${book.description}" />
										<input type="hidden" name="bookPrice" value="${book.salePrice}" />
										<button type="submit" class="btn btn-success">Add To Cart</button>		
									</div>
								</form>	
								<form class="mt-3 mx-1" action="order-now" method="post">
									<div>
										<input type="hidden" name="bookId" value="${book.id}" /> 
										<input type="hidden" name="bookPrice" value="${book.salePrice}" />
										<button class="btn btn-success">Buy Now</button>
									</div>	
								</form>	
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
			
		</div>
	</section>

	<jsp:include page="footer.jsp" />


	<script type="text/javascript" src="${pageContext.request.contextPath}/js/searchBook.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN"
		crossorigin="anonymous"></script>
</body>
</html>
