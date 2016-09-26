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
		
		<title>Articles</title>
	</head>
	<body>
		<div class="container" style="margin-bottom: 15px;">
			<h1>Bath and Health</h1>
			<h3>
				<c:out value="${article.header}" />
			</h3>
		</div>
		<div class="container" style="margin-bottom: 15px; text-align: center;">
			<img alt="${article.header}" border="0"
				src="admin/image?imgName=${article.imgPath}" height="350px">
		</div>
		<div class="container" >
			<p><c:out value="${article.content}" escapeXml="false" /></p>
		</div>
	</body>
</html>