<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>Register</title>

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

	<section
		class="container h-auto d-flex justify-content-center align-items-center">



		<form action="register" method="post" class="w-75">
			<c:if test="${not empty successMsg }">
				<p class="mt-2 text-center text-success">${successMsg}</p>
				<c:remove var="successMsg" scope="session" />
			</c:if>

			<c:if test="${not empty failMsg }">
				<p class="mt-2 text-center text-danger">${failMsg}</p>
				<c:remove var="failMsg" scope="session" />
			</c:if>
			<h1 class="mt-4">Register</h1>
			<div class="mb-2">
				<label for="firstName" class="form-label">First name</label> <input
					type="text" class="form-control" id="firstName"
					aria-describedby="firstName" required="required" name="firstName" />
			</div>
			<div class="mb-2">
				<label for="lastName" class="form-label">Last name</label> <input
					type="text" class="form-control" id="lastName"
					aria-describedby="lastName" required="required" name="lastName" />
			</div>
			<div class="mb-2 mt-2">
				<label for="email" class="form-label">Email address</label> <input
					type="email" class="form-control" id="email"
					aria-describedby="email" required="required" name="email" />
			</div>
			<div class="mb-2">
				<label for="password" class="form-label">Password</label> <input
					type="password" class="form-control" id="password"
					required="required" name="password" />
			</div>
			<button type="submit" class="btn btn-primary">Register</button>
			<div class="mt-4">
				<p>
					Already registered? <a href="login">Login here</a>
				</p>
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
