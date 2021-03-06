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
		
		<script type="text/javascript">
			function doAjax(id) {
				var productid = id;
				$.ajax({
					url : 'addToCart', //call method to save to basket
					type: 'GET',
					/* dataType: 'json',
					contentType: 'application/json',
				    mimeType: 'application/json', */
					data : ({
						productId: id
					}),
					success: function () { // everything works but can't reach success
						var firstHalf = "cartButton";
						var name = firstHalf.concat(productid);
						var anchor = document.getElementById(name);
						anchor.text = "Added";
						anchor.href = "javascript:void(0)";
					}
				});
			} 
		</script>
		
		<title>Goods</title>
	</head>
	<body>
		<div class="container">
			<h1>Bath and Health</h1>
		</div>
		
		<div class="container" style="margin-bottom: 15px;">
			<div class="row">
				<div class="col-sm-1">
					<label>Sort by</label>
				</div>
				<div class="col-sm-2">
					<select onchange="location = this.value;" class="form-control" style="width: 200px">
						<c:forEach var="approach" items="${sortParams}">
							<option value="sortGoods?sortParam=${approach.name()}" 
									${approach.name() == sortParam ? 'selected="selected"' : ''}>
								<c:out value="${approach.getDescription()}"/>
							</option>
						</c:forEach>
					</select>
				</div>
			</div>
		</div>
			
		<div id="partOfProducts" class="container-fluid">
			
		<c:forEach var="product" items="${productList}">
				<div class="col-sm-12 col-md-4 col-lg-3">
					<div class="panel panel-default" >
				      <div class="panel-heading" style='padding: 0px 0px;'>
				      	<a href="product${product.id}"> 
							<img alt="${product.productName}" src="admin/image?imgName=${product.imgPath}" 
									class="img-responsive" style="width:100%"/>
						</a>
					</div>
					<div class="panel-body">
						<a href="product${product.id}">
							${product.productName}
							<br>
							${product.price}
						</a>
					</div>
				      	<div class="panel-body">
				      		<a id="cartButton${product.id}"
								href="javascript:doAjax(${product.id})"> Add to cart</a>
						</div>
	   				</div>
	   				
				</div>
			</c:forEach>
		</div>
		<br>
	
		<div class="container">
			<ul class="pagination">
				<c:if test="${currentPage != 1}">
					<li><a href="goods?currentPage=${currentPage - 1}&category=${category}">&laquo;</a></li>
				</c:if>
				<c:if test="${noOfPages > 1}">
					<c:forEach begin="1" end="${noOfPages}" var="i">
						<li ${currentPage eq i ? "class='active'" : ''}>
							<a href="goods?currentPage=${i}&category=${category}">${i}</a>
						</li>
					</c:forEach>
				</c:if>
				<c:if test="${currentPage lt noOfPages}">
					<li><a href="goods?currentPage=${currentPage + 1}&category=${category}">&raquo;</a></li>
				</c:if>
			</ul>
		</div>
	</body>
</html>