<%@page import="db.util.MySqlDBConnector"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>Wise Owl Bookshop</title>

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

	<section>
		<c:if test="${loginSuccess != null}">
			<p class="mt-2 text-center text-success">${loginSuccess}</p>
			<c:remove var="loginSuccess" scope="session" />
		</c:if>
		<div class="main">
			<div class="main_tag">
				<h1>
					WELCOME TO<br /> <span>WISE OWL BOOKSHOP</span>
				</h1>

				<p>Oh hello there! Wise Owl here. We have a lovely collection of
					books here, so why don't you come in and explore our store? Who
					knows, you may even find some rare gems to take home!</p>
			</div>

			<div class="main_img">
				<img
					src="${pageContext.request.contextPath}/assets/images/wiseOwlOnBooks.png" />
			</div>
		</div>
	</section>

	<!-- Services -->

	<div class="services">
		<div class="services_box">
			<div class="services_card">
				<i class="fa-solid fa-truck-fast"></i>
				<h3>Fast Delivery</h3>
				<p>Free for order over $50!</p>
			</div>
			<div class="services_card">
				<i class="fa-solid fa-tag"></i>
				<h3>Best Deal</h3>
				<p>We will beat any lower price you can find!</p>
			</div>
			<div class="services_card">
				<i class="fa-solid fa-headset"></i>
				<h3>24/7 Customer Support</h3>
				<p>Call us any time in need!</p>
			</div>
			<div class="services_card">
				<i class="fa-solid fa-lock"></i>
				<h3>Secured Payment</h3>
				<p>Purchase your books with a piece of mind!</p>
			</div>
		</div>
	</div>

	<!-- About -->

	<div class="about">
		<div class="about_image">
			<img
				src="${pageContext.request.contextPath}/assets/images/openBook.png" />
		</div>

		<div class="about_tag">
			<h1>About Us</h1>
			<p>A book shop where book lovers can relax and
				finding the joy in looking for the books you love </p>
		</div>
	</div>


	<!-- Footer -->

	<jsp:include page="footer.jsp" />

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN"
		crossorigin="anonymous"></script>
</body>
</html>
