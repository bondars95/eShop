<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		
		<script src="${pageContext.request.contextPath}/resources/js/jquery-1.7.1.js"></script>
		<script src="${pageContext.request.contextPath}/resources/js/ui/jquery.ui.core.js"></script>
		<script src="${pageContext.request.contextPath}/resources/js/ui/jquery.ui.widget.js"></script>
		<script src="${pageContext.request.contextPath}/resources/js/ui/jquery.ui.position.js"></script>
		<script src="${pageContext.request.contextPath}/resources/js/ui/jquery.ui.autocomplete.js"></script>
		
		<title>Order Details</title>
	</head>
	<body>
		<div class="container">
			<h3>Order Details</h3>
		</div>
		<div class="container" style="margin-bottom: 10px;">
			<button onclick="location.href = 'newOrderItem${orderId}'"class="btn btn-primary">Add New Order Item</button>
		</div>
		
		<div class="container-fluid">
			<div class="table-responsive">
				<table class="table table-hover">
					<thead>
						<tr>
							<th>Product name</th>
							<th>Line item</th>
							<th>Qty</th>
							<th>Price</th>
							<th>Total</th>
							<th>Edit</th>
							<th>Delete</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="orderItem" items="${listOfItems}">
							<tr>
								<td><a href="product${orderItem.product.id}"
									title="ProductDetails">${orderItem.product.productName}</a></td>
								<td>${orderItem.lineItem}</td>
								<td>${orderItem.qty}</td>
								<td>${orderItem.product.price}</td>
								<td><c:out value="${orderItem.qty*orderItem.product.price}" /></td>
								<td><a href="editItem${orderItem.id}" title="editItemr">Edit</a></td>
								<td><a href="deleteItem${orderItem.id}" title="deleteItem">Delete</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</body>
</html>