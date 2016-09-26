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
		
		<title>Articles</title>
	</head>
	<body>
		<div class="container">
			<h1>Bath and Health</h1>
			<h3>Articles</h3>
		</div>
		<div class="container">
			<c:forEach var="article" items="${articleList}">
				<div class="col-sm-6 col-md-4">
					<div class="panel panel-default" >
				    	<div class="panel-heading" style='padding: 0px 0px; border: 0px'>
							<!-- add link (wrapper tag) to the good -->
							<a href="/eShop/articles&${article.id}"><img alt="${article.header}"
								border="0" src="image?imgName=${article.imgPath}"
								class="img-responsive" style="max-height: 100%"></a>
						</div>
						<div class="panel-body" style="height: 100px">
							<div class="article_header">
								<h4><a href="/eShop/articles&${article.id}">
									<c:out value="${article.header}" />
								</a></h4>
							</div>
							<!-- think about keeping this information on this page -->
							<div class="article_body">
								<p style="display: inline; float: left" ><a href="article&${article.id}?action=edit">Edit</a></p>
								<p style="display: inline; float: right"><a href="article&${article.id}?action=remove">Remove</a></p>
							</div>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</body>
</html>