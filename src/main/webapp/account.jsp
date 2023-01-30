<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Settings</title>
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

	<section
		class="container h-auto d-flex justify-content-center align-items-center">



		<form action="account-update" method="post" class="w-75">
			<c:if test="${accountUpdated != null}">
				<p class="my-2 text-center text-success">${accountUpdated}</p>
				<c:remove var="updateSettingsSuccess" scope="session" />
			</c:if>

			<h1 class="mt-4">Account Details</h1>
			<div class="mb-2">
				<label for="firstName" class="form-label">First name</label> <input
					type="text" class="form-control" id="firstName"
					aria-describedby="firstName" required="required" name="firstName"
					placeholder="${sessionScope.firstName}" />
			</div>
			<div class="mb-2">
				<label for="lastName" class="form-label">Last name</label> <input
					type="text" class="form-control" id="lastName"
					aria-describedby="lastName" name="lastName"
					placeholder="${sessionScope.lastName}" />
			</div>
			<div class="mb-2 mt-2">
				<label for="email" class="form-label">Email address</label> <input
					type="email" class="form-control" id="email"
					aria-describedby="email" disabled name="email"
					placeholder="${sessionScope.email}" />
			</div>
			<div class="mb-2">
				<label for="address" class="form-label">Address</label> <input
					type="text" class="form-control" id="address" name="address"
					placeholder="${sessionScope.address}" />
			</div>
			<div class="mb-2">
				<label for="city" class="form-label">City</label> <input type="text"
					class="form-control" id="city" name="city"
					placeholder="${sessionScope.city}" />
			</div>
			<div class="mb-2">
				<label for="state" class="form-label">State</label><br> <input
					type="radio" id="NSW" name="state" value="NSW"> <label
					for="NSW">NSW</label><br> <input type="radio" id="Victoria"
					name="state" value="Victoria"> <label for="Victoria">Victoria</label><br>
				<input type="radio" id="Queensland" name="state" value="Queensland">
				<label for="Queensland">Queensland</label><br> <input
					type="radio" id="WesternAustralia" name="state"
					value="WesternAustralia"> <label for="WesternAustralia">Western
					Australia</label><br> <input type="radio" id="SouthAustralia"
					name="state" value="SouthAustralia"> <label
					for="SouthAustralia">South Australia</label><br> <input
					type="radio" id="Tasmania" name="state" value="Tasmania"> <label
					for="Tasmania">Tasmania</label><br> <input type="radio"
					id="NorthernTerritory" name="state" value="NorthernTerritory">
				<label for="NorthernTerritory">Northern Territory</label><br> <input
					type="radio" id="ACT" name="state" value="ACT"> <label
					for="ACT">ACT</label><br>
			</div>
			<div class="mb-2">
				<label for="postcode" class="form-label">Postcode</label> <input
					type="text" class="form-control" id="postcode" name="postcode"
					placeholder="${sessionScope.postcode}" />
			</div>
			<button type="submit" class="my-4 btn btn-primary">Update
				account details</button>
		</form>
	</section>



	<!-- Footer -->

	<jsp:include page="footer.jsp" />

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN"
		crossorigin="anonymous"></script>
</body>
</html>