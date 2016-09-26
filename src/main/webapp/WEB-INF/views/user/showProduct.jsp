<%@ page language="java" contentType="text/html; 	charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="cf"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
	<head>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		
		<script
			src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
		
		<style>
		.carousel-inner>.item>img, .carousel-inner>.item>a>img {
			width: 70%;
			margin: auto;
		}
		</style>
		<title>${product.productName}INFO</title>
	</head>
	<body>
		<h1>${product.productName}</h1>
		
		
		<div class="container">
			<div class="col-sm-12 col-md-6">
				<div id="myCarousel" class="carousel slide" data-ride="carousel">
					<ol class="carousel-indicators">
						<c:forEach var="filename" items="${imagesList}">
								<c:choose>
									<c:when test="${count>0}">
										<li data-target="#myCarousel" data-slide-to="${count}" ></li>
									</c:when>
									<c:otherwise>
										<li data-target="#myCarousel" data-slide-to="${count}" class="active"></li>
									</c:otherwise>
								</c:choose>
								<c:set var="count" value="${count + 1}" scope="page" />
						</c:forEach>
					</ol>
					<div class="carousel-inner" role="listbox">
						<c:set var="count" value="0" />
						<c:forEach var="filename" items="${imagesList}">	
								<div ${(count > 0) ? 'class="item"': 'class="item active"'}>
									<img src="admin/image?imgName=${filename}" alt="${product.productName}">
								</div>
							<c:set var="count" value="${count + 1}" scope="page" />
						</c:forEach>
					</div>
			
					<a class="left carousel-control" href="#myCarousel" role="button"
						data-slide="prev"> <span
						class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
						<span class="sr-only">Previous</span>
					</a> <a class="right carousel-control" href="#myCarousel" role="button"
						data-slide="next"> <span
						class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
						<span class="sr-only">Next</span>
					</a>
				</div>
				
			</div>
			<div class="col-sm-12 col-md-5">
				<div style="margin-bottom: 15px">
					<p>Description: ${product.description}</p>
					<p>Category: ${product.category.categoryName}<p>
					<p>Price: ${product.price}</p>
				</div>
				<cf:form method="GET" action="buy${product.id}"
					modelAttribute="product">
					<input class="btn btn-default" type="submit" value="Buy" />
				</cf:form>
			</div>
		</div>
	</body>
</html>





