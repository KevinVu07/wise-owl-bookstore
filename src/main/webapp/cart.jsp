<%@page import="model.BookDetailsModel"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cart</title>
<!-- Bootstrap, CSS, and Fontawesome plug in -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/style.css" />
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
	
	<c:choose>
		<c:when test="${booksInCart.isEmpty()}">
			<div class="mt-4 d-flex justify-content-center">
				<img id="cartEmptyImg" src="${pageContext.request.contextPath}/assets/images/emptyCart.png" alt=""/>
			</div>
		</c:when>
		
		<c:otherwise>
			<section class="container my-4 h-auto d-flex justify-content-between">
				<div id="cartBox" class="container-fluid w-75 mx-4">
					<c:if test="${addBookSuccess != null}">
						<p class="mt-2 text-center text-success">${addBookSuccess}</p>
						<c:remove var="addBookSuccess" scope="session" />
					</c:if>
					<table class="table">
						<thead>
							<tr>
								<th scope="col">Your Cart</th>
								<th scope="col">Each</th>
								<th scope="col">Qty</th>
								<th scope="col">Total</th>
							</tr>
						</thead>
						<tbody class="table-responsive">
							<c:forEach var="book" items="${booksInCart}">
								<tr>
									<td class="d-flex w-auto">
										<div id="cartBookImage">
											<img
												src="${pageContext.request.contextPath}/assets/images/books/${book.image}" />
										</div>
		
										<div id="cartBookDetails">
											<h4>${book.name}</h4>
											<p id="cartBookDescription">${book.description}</p>
											<p>${book.type}</p>
										</div>
									</td>
									<td>$${book.salePrice}</td>
									</form>
									<td>
										<form action="" method="get">
											<input type="hidden" name="bookId" value="${book.id}" />
											<div class="form-group d-flex justify-content-between">
												<a class="btn btn-sm btn-decre"
													href="cart-dec-inc?action=dec&id=${book.id}"><i
													class="fas fa-minus-square"></i></a> ${book.qty} <a
													class="btn btn-sm btn-incre"
													href="cart-dec-inc?action=inc&id=${book.id}"><i
													class="fas fa-plus-square"></i></a>
											</div>
										</form>
									</td>
									<td>$${book.total}</td>
		
								</tr>
							</c:forEach>
		
						</tbody>
					</table>
					<div id="cartItem"></div>
				</div>
	
				<div id="orderSummary" class="mx-4 w-25">
					<h4>Your Cart Total Value</h4>
					<hr>
					<div id="subTotal" class="d-flex justify-content-between">
						<p>Sub Total</p>
						<p>$${sessionScope.cartTotal}</p>
					</div>
					<div id="shipping" class="d-flex justify-content-between">
						<p>Shipping</p>
						<p>Calculated at checkout</p>
					</div>
					<form action="checkout" method="post">
						<button class="w-100 btn btn-success" type="submit" value="checkOut">Checkout</button>
					</form>
				</div>
			</section>
		</c:otherwise>
	</c:choose>
	
	
	<!-- Footer -->

	<jsp:include page="footer.jsp" />

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN"
		crossorigin="anonymous"></script>
</body>
</html>
