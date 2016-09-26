<%@ page language="java" contentType="text/html; 	charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="cf"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
	<head>
	
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		
		<script src="${pageContext.request.contextPath}/resources/js/jquery-1.7.1.js"></script>
		<script src="${pageContext.request.contextPath}/resources/js/ui/jquery.ui.core.js"></script>
		<script src="${pageContext.request.contextPath}/resources/js/ui/jquery.ui.widget.js"></script>
		<script src="${pageContext.request.contextPath}/resources/js/ui/jquery.ui.position.js"></script>
		<script src="${pageContext.request.contextPath}/resources/js/ui/jquery.ui.autocomplete.js"></script>

		<script type="text/JavaScript">
			$(document).ready(function() {
				//add more file components if Add is clicked
				$('#addFile').click(function() {
					var fileIndex = $('#fileTable tr').children().length;
					$('#fileTable').append('<tr><td>   <input class="btn btn-primary" type="file" name="files['
			        	+ fileIndex +']" /></td></tr>');
				});
			});
			
			function changeCategory(categoryName) {
				document.getElementById("categoryChoise").value = categoryName;
				document.getElementById("current_category").innerHTML = 'Category: ' + categoryName;	
			}
			
			function changeImage(filename) {
				document.getElementById("image_path").setAttribute("value", filename);
				document.getElementById("main_img").setAttribute("src", "image?imgName=" + filename);
			}
			function showAddingImgs() {
				var div = document.getElementById('addingImgs');
				if (div.style.display == 'block') {
					div.style.display='none';
				} else {
					div.style.display='block';
				}
			}
		</script>
		
		<style>
			.error {
				background-color: #fc6363;
			}
			
			.valid {
				background-color: #81e569;
			}
			
			.dropdown-submenu {
				position: relative;
			}
			
			.dropdown-submenu .dropdown-menu {
				top: 0;
				left: 100%;
				margin-top: -1px;
			}
		</style>
		
		<title>New Product</title>
	</head>
	<body>
		<div class="container">
			<h3>Product Information</h3>
		</div>
		<div class="container">
			<img id="main_img" src="image?imgName=${product.imgPath}" height="300" />
		</div>
		<div class="container">
			<label id="current_category">Category: ${product.category.categoryName }</label>
		  	<div class="dropdown">
			<button id="categoryName" class="btn btn-default dropdown-toggle" type="button" data-toggle="dropdown">Categories
			<span class="caret"></span></button>
				<ul class="dropdown-menu">
					<c:forEach var="item" items="${categoryTree}">
						${item}
					</c:forEach>
			    </ul>
		  	</div>
		</div>
		<div class="container">
			<div class="row">
				<div class="col-sm-12 col-md-5">
					<cf:form method="POST" action="addProduct" modelAttribute="product">
						<cf:hidden path="id" />
						<cf:hidden id="image_path" path="imgPath" />
						<cf:hidden id="categoryChoise" path="category.categoryName" />
		
						<div class="form-group">
							<cf:label path="productName">Name of product</cf:label>
							<cf:errors path="productName" cssClass="error"/>
							<cf:input path="productName" class="form-control"/>
						</div>
						<div class="form-group">
							<cf:label path="description">Description</cf:label>
							<cf:errors path="description" cssClass="error" />
							<cf:textarea path="description" class="form-control"/>
						</div>
						<div class="form-group">
							<cf:label path="country">Country</cf:label>
							<cf:errors path="country" cssClass="error"/>
							<cf:input path="country" class="form-control"/>
						</div>
						<div class="form-group">
							<cf:label path="price">Price</cf:label>
							<cf:errors path="price" cssClass="error" />
							<cf:input path="price" class="form-control"/>
						</div>
						<div class="form-group">
							<cf:label path="rest">Rest</cf:label>
							<cf:errors path="rest" cssClass="error"/>
							<cf:input path="rest" class="form-control"/>
						</div>
						<input type="submit" class="btn btn-primary" value="Submit"/>
					</cf:form>
				</div>
				<c:if test="${product.id > 0}">
					<div class="col-sm-12 col-md-7">
						<c:set var="name" value="${product.id}img"/>
						<c:forEach var="filename" items="${imagesList}">
							<div class="col-sm-3" align="center">
								<a onclick="changeImage('${filename}')" title="make_main"><img src="image?imgName=${filename}" height="120" /></a>
								<a href="deleteProdImg${product.id}?imgName=${filename}" title="delete_image"><span class="glyphicon glyphicon-remove"></span></a>
							</div>
						</c:forEach>
					</div>
				</c:if>
			</div>
		</div>
		<div class="container">
			<c:if test="${product.id > 0}">
				<input class="btn btn-default" type="submit" value="Add Images" 
						onclick="showAddingImgs()"/>
				<div id="addingImgs" style="display:none">
					<cf:form method="post" action="saveProductImg${productId}"
						modelAttribute="uploadForm" enctype="multipart/form-data">
						<p>Select files to upload. Press Add button to add more file
							inputs.</p>
						<table id="fileTable">
							<tr>
								<td><input class="btn btn-primary" name="files[0]" type="file" /></td>
							</tr>
							<tr>
								<td><input class="btn btn-primary" name="files[1]" type="file" /></td>
							</tr>
						</table>
						<br/>
						<input class="btn btn-primary" type="submit" value="Upload Images" />
						<input class="btn btn-default" id="addFile" type="button" value="Add File" />
					</cf:form>
				</div>
			</c:if>
		</div>
	</body>
</html>