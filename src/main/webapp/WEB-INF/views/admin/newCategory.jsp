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

		<title>New Category</title>
	</head>
	<body>
		<div class="container">
			<h3>New Category</h3>
		</div>
		<div class="container">
			<div class="row">
				<div class="col-sm-12 col-md-5">
					<cf:form method="POST" action="addCategory" modelAttribute="category">
						<cf:hidden path="id" />
						<cf:hidden path="parentId" />
						
						<div class="form-group">
							<cf:label path="parentCategory.categoryName">Parent Name</cf:label>
							<cf:errors path="parentCategory.categoryName" cssClass="error" />
							<cf:input path="parentCategory.categoryName" class="form-control"/>
						</div>
						<div class="form-group">	
							<cf:label path="categoryName">CategoryName</cf:label>
							<cf:errors path="categoryName" cssClass="error" />
							<cf:input path="categoryName" class="form-control"/>
						</div>
						<input type="submit" class="btn btn-primary" value="Add Category"/>
					</cf:form>
				</div>
			</div>
		</div>
	</body>
</html>