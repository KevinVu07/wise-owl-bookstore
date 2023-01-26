<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>Login</title>

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
		<nav class="navbar navbar-expand-lg bg-light">
			<div class="container-fluid">
				<a class="navbar-brand logo" href="#"> <img
					src="assets/images/WiseOwlBookstoreCopy.png" />
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
						<li class="nav-item"><a class="nav-link" href="#">New
								Books</a></li>
						<li class="nav-item"><a class="nav-link" href="#">Old
								Books</a></li>
					</ul>
					<div>
						<div class="nav-item dropdown">
							<a class="nav-link dropdown-toggle" href="#" role="button"
								data-bs-toggle="dropdown" aria-expanded="false">
								Login/Register </a>
							<ul class="dropdown-menu dropdown-menu-end">
								<li><a class="dropdown-item" href="login.html">Login</a></li>
								<li><a class="dropdown-item" href="register.html">Register</a></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</nav>
	</section>

	<section
		class="container h-50 d-flex justify-content-center align-items-center">
		<form action="login" method="post" class="w-75">
			<c:if test="${loginError != null}">
				<p class="text-center text-danger">${loginError}</p>
			</c:if>
			<h1 class="mt-4">Login</h1>
			<div class="mb-3 mt-4">
				<label for="email" class="form-label">Email address</label> <input
					type="email" class="form-control" id="email"
					aria-describedby="emailHelp" name="email" />
			</div>
			<div class="mb-3">
				<label for="password" class="form-label">Password</label> <input
					type="password" class="form-control" id="password" name="password" />
			</div>
			<div class="mb-3 form-check">
				<input type="checkbox" class="form-check-input" id="rememberMe"
					name="rememberMe" /> <label class="form-check-label"
					for="rememberMe">Remember me</label>
			</div>
			<button type="submit" class="btn btn-primary">Login</button>
			<div class="my-4">
				<p>
					Don't have an account yet? <a href="register.html">Register
						here</a>
				</p>
			</div>
		</form>
	</section>

	<footer>
		<div class="footer_main">
			<div class="tag">
				<img src="assets/images/WiseOwlBookstoreCopy.png" alt="" />
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
