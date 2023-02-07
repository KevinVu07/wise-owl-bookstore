<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<nav class="navbar navbar-expand-lg bg-light">
	<div class="container-fluid">
		<a class="navbar-brand logo" href="home"> <img
			src="${pageContext.request.contextPath}/assets/images/WiseOwlBookstoreCopy.png" />
		</a>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav me-auto mb-2 mb-lg-0">
				<li class="nav-item"><a class="nav-link active"
					aria-current="page" href="home">Home</a></li>
				<li class="nav-item"><a class="nav-link" href="new-books">New
						Books</a></li>
				<li class="nav-item"><a class="nav-link" href="#">Old Books</a></li>
				<c:if test="${admin == 1}">
					<li class="nav-item"><a class="nav-link" href="add-new-book">Add New Book</a></li>
				</c:if>
			</ul>
			<div>
				<c:choose>
					<c:when test="${sessionScope.firstName == null}">
						<div class="nav-item dropdown">
							<a class="nav-link dropdown-toggle" href="#" role="button"
								data-bs-toggle="dropdown" aria-expanded="false">
								Login/Register </a>
							<ul class="dropdown-menu dropdown-menu-end">
								<li><a class="dropdown-item" href="login">Login</a></li>
								<li><a class="dropdown-item" href="register">Register</a></li>
							</ul>
						</div>
					</c:when>
					<c:otherwise>
						<div class="d-flex">
							<a id="cartIconNav" class="mx-1" href="order-summary"><i class="fa-solid fa-money-check-dollar"></i></a>
							<a id="cartIconNav" class="mx-1" href="cart"><i class="fas fa-shopping-cart mx-2" href="cart"></i></a>
							<div class="nav-item dropdown mx-1">
								<a class="nav-link dropdown-toggle" href="#" role="button"
									data-bs-toggle="dropdown" aria-expanded="false"> ${sessionScope.firstName} </a>
								<ul class="dropdown-menu dropdown-menu-end">
									<li><a class="dropdown-item" href="account-update">Account</a></li>
									<li><a class="dropdown-item" href="cart">Cart</a></li>
									<li><a class="dropdown-item" href="order-summary">Checkout</a></li>
									<li><a class="dropdown-item" href="order-history">Order History</a></li>
									<li><hr class="dropdown-divider" /></li>
									<li><a class="dropdown-item" href="logout">Logout</a></li>
								</ul>
							</div>
						</div>
						
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</div>
</nav>
