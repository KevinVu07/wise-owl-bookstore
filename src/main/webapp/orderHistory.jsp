<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Order History Page</title>
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
						<th scope="col">Your Past Orders</th>
						<th scope="col">Total</th>
						<th scope="col">Order Date</th>
					</tr>
				</thead>
				<tbody class="table-responsive">
					<tr>
						<td><a class="dropdown-toggle" href="#" id="dropdownMenuLink"
							data-bs-toggle="dropdown" aria-expanded="false"> Order
								Reference 100054 </a> <!--  <ul class="dropdown-menu" aria-labelledby="dropdownMenuLink">
					    <li><a class="dropdown-item" href="#">Action</a></li>
					    <li><a class="dropdown-item" href="#">Another action</a></li>
					    <li><a class="dropdown-item" href="#">Something else here</a></li>
					  </ul> -->
							<div class="dropdown">
								<table class="table dropdown-menu"
									aria-labelledby="dropdownMenuLink">
									<thead>
										<tr>
											<th scope="col">Your Books</th>
											<th scope="col">Qty</th>
											<th scope="col">Total</th>
										</tr>
									</thead>
									<tbody class="table-responsive">
										<c:forEach var="order" items="${orderList}">
											<tr>
												<td class="d-flex w-auto">
													<div id="cartBookImage">
														<img
															src="${pageContext.request.contextPath}/assets/images/books/${order.bookImage}" />
													</div>

													<div id="cartBookDetails">
														<h4>${order.bookName}</h4>
													</div>
												</td>
												<td>${order.orderQty}</td>
												<td>$${order.orderTotal}</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div></td>

						<td>$100</td>
						<td>5/2/2023</td>
					</tr>

				</tbody>
			</table>
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