<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="cf"%>
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
		
		<link rel="stylesheet" href="//code.jquery.com/ui/1.12.0/themes/base/jquery-ui.css">
		
		<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
		<script src="https://code.jquery.com/ui/1.12.0/jquery-ui.js"></script>
		<script  type="text/javascript">
		$( function() {
		    $( "#datepicker" ).datepicker({ dateFormat: "yy-mm-dd" });
		  } );
		</script>
		
		<style>
		.error {
			color: red;
			font-weight: bold;
		}
		</style>
		
		<title>New Order</title>
	</head>
	<body>
		<div class="container">
			<h3>New Order</h3>
		</div>
		
		<div class="container">
			<div class="row">
				<div class="col-sm-12 col-md-5">
					<cf:form method="POST" action="addOrder" modelAttribute="order">
						<cf:hidden path="id" />
		
						<div class="form-group">
							<cf:label path="user.id">User ID</cf:label>
							<cf:errors path="user.id" cssClass="error" />
							<cf:input  path="user.id" class="form-control"/>
						</div>
						<div class="form-group">
							<cf:label path="orderDate" >Order Date</cf:label>
							<cf:errors path="orderDate" cssClass="error" />
							<cf:input path="orderDate"  id="datepicker" class="form-control"/>
						</div>
						<input type="submit" class="btn btn-primary" value="Submit"/>
					</cf:form>
				</div>
			</div>
		</div>
	</body>
</html>