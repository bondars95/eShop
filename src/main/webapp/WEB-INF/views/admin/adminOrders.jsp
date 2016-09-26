<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

	<script
		src="${pageContext.request.contextPath}/resources/js/jquery-1.7.1.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/ui/jquery.ui.core.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/ui/jquery.ui.widget.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/ui/jquery.ui.position.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/ui/jquery.ui.autocomplete.js"></script>
	
	<title>Orders</title>
	</head>
	<body>
		<div class="container">
			<h3>Orders info</h3>
		</div>
		<div class="container" style="margin-bottom: 10px;">
			<button onclick="location.href = 'newOrder'"class="btn btn-primary">Create New Order</button>
		</div>
		<div class="container-fluid">
			<div class="table-responsive">
				<table class="table table-hover">
					<thead>
						<tr>
							<th>ID</th>
							<th>UserName</th>
							<th>OrderDate</th>
							<th>Status</th>
							<th>Edit</th>
							<th>Delete</th>
							<th>Details</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="ordersDetails" items="${listOfOrders}">
							<tr>
								<td>${ordersDetails.id}</td>
								<td><a href="user${ordersDetails.user.id}"
										title="UserDetails"><c:out
											value="${ordersDetails.user.fName.concat(' ').concat(ordersDetails.user.lName)}" /></a></td>
								<td>${ordersDetails.orderDate}</td>
								<td><c:set var="status" scope="session"
										value="${ordersDetails.status}" /> <c:if test="${status>0}">
										<a href="changeOrderStatus&${ordersDetails.id}">Undone</a>
									</c:if> <c:if test="${status<0}">
										<a href="changeOrderStatus&${ordersDetails.id}">Done</a>
									</c:if></td>
								<td><a href="editOrder&${ordersDetails.id}"
										title="editOrder">Edit</a></td>
								<td><a href="deleteOrder&${ordersDetails.id}"
										title="deleteOrder">Delete</a></td>
								<td><a href="orderDetails&${ordersDetails.id}"
										title="details">Details</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</body>
</html>