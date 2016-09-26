<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<script src="${pageContext.request.contextPath}/resources/js/jquery-1.7.1.js"></script>
		<script
			src="${pageContext.request.contextPath}/resources/js/ui/jquery.ui.core.js"></script>
		<script
			src="${pageContext.request.contextPath}/resources/js/ui/jquery.ui.widget.js"></script>
		<script
			src="${pageContext.request.contextPath}/resources/js/ui/jquery.ui.position.js"></script>
		<script
			src="${pageContext.request.contextPath}/resources/js/ui/jquery.ui.autocomplete.js"></script>

		<title>Products</title>
	</head>
	<body>
		<div class="container">
			<h3>Products info</h3>
		</div>
		<div class="container" style="margin-bottom: 10px;">
			<button onclick="location.href = 'newProduct'"class="btn btn-primary">Create New Product</button>
		</div>
		<div class="container-fluid">
			<div class="table-responsive">
				<table class="table table-hover">
					<thead>
						<tr>
							<th>ID</th>
							<th>Image</th>
							<th>Name</th>
							<th>Category name</th>
							<th>Country</th>
							<th>Price</th>
							<th>Rest</th>
							<th>Show orders</th>
							<th>Edit</th>
							<th>Delete</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="product" items="${listOfProducts}">
							<tr>
								<td>${product.id}</td>
								<td><img alt="${product.productName}" border="0"
									src="image?imgName=${product.imgPath}" height="50" /></td>
								<td>${product.productName}</td>
								<td>${product.category.categoryName}</td>
								<td>${product.country}</td>
								<td>${product.price}</td>
								<td>${product.rest}</td>
								<td><a href="productOrders&${product.id}" title="productOrders">Details</a></td>
								<td><a href="editProduct&${product.id}" title="editProduct">Edit</a></td>
								<td><a href="deleteProduct&${product.id}" title="editProduct">Delete</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</body>
</html>