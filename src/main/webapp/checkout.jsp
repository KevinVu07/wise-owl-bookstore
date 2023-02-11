<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Checkout Page</title>
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

	<section class="container my-4 h-auto d-flex justify-content-between">
		<div class="container-fluid w-75 mx-4">
			<table class="table">
				<thead>
					<tr>
						<th scope="col">Books In Your Order</th>
						<th scope="col">Qty</th>
						<th scope="col">Total</th>
						<th scope="col">Order Date</th>
					</tr>
				</thead>
				<tbody class="table-responsive">
					<c:forEach var="orderItem" items="${orderItemList}">
						<tr>
							<td class="d-flex w-auto">
								<div id="cartBookImage">
									<img
										src="${pageContext.request.contextPath}/assets/images/books/${orderItem.bookImage}" />
								</div>

								<div id="cartBookDetails">
									<h4>${orderItem.bookName}</h4>
								</div>
							</td>
							<td>${orderItem.orderQty}</td>
							<td>$${orderItem.orderTotal}</td>
							<td>${orderItem.orderDate}</td>
						</tr>
					</c:forEach>

				</tbody>
			</table>
			<div id="cartItem"></div>
		</div>

		<div id="orderSummary" class="mx-4 w-25">
			<h4>Order Summary</h4>
			<hr>
			<div id="subTotal" class="d-flex justify-content-between">
				<p>Sub Total</p>
				<p>$${sessionScope.subTotal}</p>
			</div>
			<div id="shipping" class="d-flex justify-content-between">
				<p>Shipping</p>
				<p>$${sessionScope.shippingFee}</p>
			</div>
			<div id="tax" class="d-flex justify-content-between">
				<p>Tax</p>
				<p>$${sessionScope.tax}</p>
			</div>
			<div id="total" class="d-flex justify-content-between">
				<strong><p>Total</p></strong>
				<p>$${sessionScope.orderTotal}</p>
			</div>
			<div class="d-flex justify-content-between align-items-center flex-column">
				<form class="w-100" action="authorize-payment" method="post">	
					<button style="background-color: #FFC33A; color: #003087" class="btn btn-primary" type="submit" value="payment"> 
					<i class="fa-brands fa-paypal"></i>   Pay with Paypal</button>
				</form>
				<br>
				<form class="w-100" action="stripe-payment" method="get">	
					<input type="hidden" name="orderTotal" value="${sessionScope.orderTotal}" /> 
					<button style="background-color: black" class="btn btn-success" type="submit" value="payment">
					<i class="fa-brands fa-cc-visa"></i>
					<i class="fa-brands fa-cc-mastercard"></i>
					<i class="fa-brands fa-cc-amex"></i>
					   Pay By Credit Card
					</button>
				</form>
			</div>
		</div>
	</section>


	<!-- Footer -->

	<jsp:include page="footer.jsp" />

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN"
		crossorigin="anonymous"></script>
</body>
</html>
