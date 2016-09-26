<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="cf"%>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		
		<script src="${pageContext.request.contextPath}/resources/js/jquery-1.7.1.js"></script>
		<script src="${pageContext.request.contextPath}/resources/js/ui/jquery.ui.core.js"></script>
		<script src="${pageContext.request.contextPath}/resources/js/ui/jquery.ui.widget.js"></script>
		<script src="${pageContext.request.contextPath}/resources/js/ui/jquery.ui.position.js"></script>
		<script src="${pageContext.request.contextPath}/resources/js/ui/jquery.ui.autocomplete.js"></script>

		<title>Order Item Info</title>
	</head>
	<body>
		<div class="container">
			<h3>Orders Item Info</h3>
		</div>
		<div class="container">
			<div class="row">
				<div class="col-sm-12 col-md-5">
					<cf:form method="POST" action="addOrderItem" modelAttribute="orderItems">
						<cf:hidden path="id" />
						
						<div class="form-group">
							<cf:label path="order.id">OrderId</cf:label>
							<cf:errors path="order.id" cssClass="error" />
							<cf:input path="order.id" class="form-control"/>
						</div>
						<div class="form-group">
							<cf:label path="product.id">ProductID</cf:label>
							<cf:errors path="product.id" cssClass="error" />
							<cf:input path="product.id" class="form-control"/>
						</div>
						<div class="form-group">
							<cf:label path="qty">Qty</cf:label>
							<cf:errors path="qty" cssClass="error" />
							<cf:input path="qty" class="form-control"/>
						</div>
						<div class="form-group">
							<cf:label path="lineItem">Line Item</cf:label>
							<cf:errors path="qty" cssClass="error" />
							<cf:input path="lineItem" class="form-control"/>
						</div>
						<input type="submit" class="btn btn-primary" value="Submit"/>
					</cf:form>
				</div>
			</div>
		</div>
	</body>
</html>