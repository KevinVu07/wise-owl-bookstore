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
<title>Book Store Website</title>

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
<link rel="manifest" href="${pageContext.request.contextPath}/assets/images/favicon/site.webmanifest" />
<link rel="mask-icon" href="${pageContext.request.contextPath}/assets/images/favicon/safari-pinned-tab.svg"
	color="#5bbad5" />
<link rel="shortcut icon" href="${pageContext.request.contextPath}/assets/images/favicon/favicon.ico" />
<meta name="msapplication-TileColor" content="#da532c" />
<meta name="msapplication-config"
	content="${pageContext.request.contextPath}/assets/images/favicon/browserconfig.xml" />
<meta name="theme-color" content="#ffffff" />
</head>
<body>
	<section>
		<nav class="navbar navbar-expand-lg bg-light">
			<div class="container-fluid">
				<a class="navbar-brand logo" href="home"> <img
					src="${pageContext.request.contextPath}/assets/images/WiseOwlBookstoreCopy.png" />
				</a>
				<button class="navbar-toggler" type="button"
					data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
					aria-controls="navbarSupportedContent" aria-expanded="false"
					aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>
				<div class="collapse navbar-collapse" id="navbarSupportedContent">
					<ul class="navbar-nav me-auto mb-2 mb-lg-0">
						<li class="nav-item"><a class="nav-link active"
							aria-current="page" href="#">Home</a></li>
						<li class="nav-item"><a class="nav-link" href="#">Featured</a>
						</li>
						<li class="nav-item"><a class="nav-link" href="new-books">New
								Books</a></li>
						<li class="nav-item"><a class="nav-link" href="#">Old
								Books</a></li>
					</ul>
					<div>
						<!-- <div class="nav-item dropdown">
							<a class="nav-link dropdown-toggle" href="#" role="button"
								data-bs-toggle="dropdown" aria-expanded="false"> User </a>
							<ul class="dropdown-menu dropdown-menu-end">
								<li><a class="dropdown-item" href="#">Settings</a></li>
								<li><a class="dropdown-item" href="#">Cart</a></li>
								<li><hr class="dropdown-divider" /></li>
								<li><a class="dropdown-item" href="#">Logout</a></li>
							</ul>
						</div> -->
						
						<div class="nav-item dropdown">
							<a class="nav-link dropdown-toggle" href="#" role="button"
								data-bs-toggle="dropdown" aria-expanded="false">
								Login/Register </a>
							<ul class="dropdown-menu dropdown-menu-end">
								<li><a class="dropdown-item" href="login">Login</a></li>
								<li><a class="dropdown-item" href="register">Register</a></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</nav>

		<div>
			<form class="d-flex" role="search">
				<input class="form-control me-2" type="search" placeholder="Search"
					aria-label="Search" />
				<button class="btn btn-outline-success" type="submit">Search</button>
			</form>
		</div>
	</section>
	<section>
		<div class="main">
			<div class="main_tag">
				<h1>
					WELCOME TO<br /> <span>WISE OWL BOOKSTORE</span>
				</h1>

				<p>Oh hello there! Wise Owl here. We have a lovely collection of
					books here, so why don't you come in and explore our store? Who
					knows, you may even find some rare gems to take home!</p>
				<a href="#" class="main_btn">Learn More</a>
			</div>

			<div class="main_img">
				<img src="${pageContext.request.contextPath}/assets/images/wiseOwlOnBooks.png" />
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
				<p>Call us anytime in need!</p>
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
			<img src="${pageContext.request.contextPath}/assets/images/openBook.png" />
		</div>

		<div class="about_tag">
			<h1>About Us</h1>
			<p>A bookstore filled with jazz music where you can relax and
				enjoy finding the books that you love</p>
			<a href="#" class="about_btn">Learn More</a>
		</div>
	</div>

	<!-- Books -->
	<div class="featured_books">
		<h1>Featured Books</h1>
		<div class="featured_book_box">
			<div class="featured_book_card">
				<div class="featured_book_img">
					<img src="${pageContext.request.contextPath}/assets/images/books/atomicHabits.png" />
				</div>
				<div class="featured_book_tag">
					<h2>Atomic Habits</h2>
					<p class="writer">James Clear</p>
					<div class="categories">Self Help</div>
					<p class="book_price">
						$25.50<sub><del>$36.00</del></sub>
					</p>
					<a href="#" class="f_btn">Details</a>
				</div>
			</div>

			
		</div>
	</div>

	<!-- Footer -->

	<footer>
		<div class="footer_main">
			<div class="tag">
				<img src="${pageContext.request.contextPath}/assets/images/WiseOwlBookstoreCopy.png" alt="" />
				<p>Created by Kevin Vu - 2023</p>
			</div>

			<div class="tag">
				<h1>Quick Link</h1>
				<a href="#">Home</a> <a href="#">Featured</a> <a href="#">New
					Books</a> <a href="#">Old Books</a>
			</div>

			<div class="tag">
				<h1>Contact Info</h1>
				<a href="#"><i class="fa-solid fa-phone"></i>+61 3 7010 4321</a> <a
					href="#"><i class="fa-solid fa-envelope"></i>wiseowlbookstore@gmail.com</a>
			</div>
		</div>
	</footer>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN"
		crossorigin="anonymous"></script>
</body>
</html>
