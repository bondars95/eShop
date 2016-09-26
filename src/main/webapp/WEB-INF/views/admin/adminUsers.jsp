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
		
		<title>Insert title here</title>
	</head>
	<body>
		<div class="container">
			<h3>Users info</h3>
		</div>
		<div class="container" style="margin-bottom: 10px;">
			<button onclick="location.href = 'newUser'"class="btn btn-primary">Create New User</button>
		</div>
		<div class="container-fluid">
			<div class="table-responsive">
				<table class="table table-hover">
					<thead>
						<tr>
							<th>ID</th>
							<th>First Name</th>
							<th>Last Name</th>
							<th>Email</th>
							<th>Birth Date</th>
							<th>City</th>
							<th>Sex</th>
							<th>Phone</th>
							<th>Role</th>
							<th>Status</th>
							<th>Edit</th>
							<th>Delete</th>
							<th>Orders</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="user" items="${listOfUsers}">
							<tr>
								<td>${user.id}</td>
								<td>${user.fName}</td>
								<td>${user.lName}</td>
								<td>${user.email}</td>
								<td>${user.birthdate}</td>
								<td>${user.city}</td>
								<td>${user.sex}</td>
								<td>${user.phone}</td>
								<td>${user.role}</td>
								<td><c:set var="role" scope="session" value="${user.role}" />
									<c:if test="${role>0}">
										<a href="changeUserStatus${user.id}">active</a>
									</c:if> <c:if test="${role<0}">
										<a href="changeUserStatus${user.id}">blocked</a>
									</c:if></td>
								<td><a href="user&${user.id}?action=edit" title="editUser">Edit</a></td>
								<td><a href="user&${user.id}?action=remove" title="deleteUser">Delete</a></td>
								<td><a href="ordersUser${user.id}" title="userOrders">Orders</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<div class="container">
			<ul class="pagination">
				<c:if test="${currentPage != 1}">
					<li><a href="users?currentPage=${currentPage - 1}">&laquo;</a></li>
				</c:if>
				<c:if test="${noOfPages > 1}">
					<c:forEach begin="1" end="${noOfPages}" var="i">
						<li ${currentPage eq i ? "class='active'" : ''}>
							<a href="users?currentPage=${i}">${i}</a>
						</li>
					</c:forEach>
				</c:if>
				<c:if test="${currentPage lt noOfPages}">
					<li><a href="users?currentPage=${currentPage + 1}">&raquo;</a></li>
				</c:if>
			</ul>
		</div>
	</body>
</html>