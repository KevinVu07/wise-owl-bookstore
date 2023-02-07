<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>Add New Book</title>

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
	
	<section>
		<div class="container">
			<div class="row">
				<div class="col-md-4 offset-md-4">
					<div class="card my-4">
						<div class="card-body">
							<h4 class="text-center">Add New Book</h4>
							
							<c:if test="${not empty bookAddSuccess}">
								<p class="text-center text-success mt-2">
									${bookAddSuccess}
								</p>
								<c:remove var="bookAddSuccess" scope="session" />
							</c:if>
							
							<c:if test="${not empty bookAddFail}">
								<p class="text-center text-danger mt-2">
									${bookAddFail}
								</p>
								<c:remove var="bookAddFail" scope="session" />
							</c:if>
							
							<form action="add-new-book" method="post" enctype="multipart/form-data">
								<div class="form-group my-1">
									<label for="bookName">Book Name</label>
									<input name="bookName" type="text" class="form-control" id="bookName">
								</div>
								<div class="form-group my-1">
									<label for="bookDescription">Book Description</label>
									<input name="bookDescription" type="text" class="form-control" id="bookDescription">
								</div>
								<div class="form-group my-1">
									<label for="bookCategory">Book Category</label>
									<select id="bookCategory" name="bookCategory" class="form-control">
										<option selected>Select Category</option>
										<option value="Novel">Novel</option>
										<option value="Self Help">Self Help</option>
										<option value="Educational">Educational</option>
									</select>
								</div>
								<div class="form-group my-1">
									<label for="bookType">Book Type</label>
									<select id="bookType" name="bookType" class="form-control">
										<option selected>Select Book Type</option>
										<option value="Paperback">Paperback</option>
										<option value="Hard Cover">Hard Cover</option>
									</select>
								</div>
								<div class="form-group my-1">
									<label for="editionNumber">Edition Number</label>
									<input name="editionNumber" type="number" class="form-control" id="editionNumber">
								</div>
								<div class="form-group my-1">
									<label for="publishedDate">Published Date</label>
									<input name="publishedDate" type="date" class="form-control" id="publishedDate">
								</div>
								<div class="form-group my-1">
									<label for="ISBN">ISBN</label>
									<input name="ISBN" type="text" class="form-control" id="ISBN">
								</div>
								<div class="form-group my-1">
									<label for="rrp">RRP</label>
									<input name="rrp" type="number" step="0.01" class="form-control" id=rrp>
								</div>
								<div class="form-group my-1">
									<label for="salePrice">Sale Price</label>
									<input name="salePrice" type="number" step="0.01" class="form-control" id=salePrice>
								</div>
								<div class="form-group my-1">
									<label for="authorName">Author Name</label>
									<input name="authorName" type="text" class="form-control" id="authorName">
								</div>
								<div class="form-group my-1">
									<label for="image">Upload Photo</label>
									<input name="image" type="file" class="form-control-file" id="image">
								</div>
								
								
								<button type="submit" class="btn btn-primary mt-4 mb-2">Add Book</button>
							</form>
						</div>
					</div>
				</div> 
			</div>
		</div>
	</section>
	
	
	<jsp:include page="footer.jsp" />

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN"
		crossorigin="anonymous"></script>
</body>
</html>