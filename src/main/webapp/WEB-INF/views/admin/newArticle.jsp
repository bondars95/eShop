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
		
		<script type="text/JavaScript"
		 	src="${pageContext.request.contextPath}/resources/js/tinymce/tinymce.min.js">
		</script>
		<script type="text/javascript">
			function changeImage(filename) {
				document.getElementById("image_path").setAttribute("value", filename);
				document.getElementById("main_img").setAttribute("src", "image?imgName=" + filename);
			}
		</script>
		<script type="text/javascript">
			tinymce.init({
				selector: 'textarea',
				height: 500,
				theme: 'modern',
				plugins: [
				  'advlist autolink lists link image charmap print preview hr anchor pagebreak',
				  'searchreplace wordcount visualblocks visualchars code fullscreen',
				  'insertdatetime media nonbreaking save table contextmenu directionality',
				  'emoticons template paste textcolor colorpicker textpattern imagetools'
				],
				toolbar1: 'insertfile undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link image',
				toolbar2: 'print preview media | forecolor backcolor emoticons',
				image_advtab: true,
				templates: [
				  { title: 'Test template 1', content: 'Test 1' },
				  { title: 'Test template 2', content: 'Test 2' }
				],
				content_css: [
				  '//fonts.googleapis.com/css?family=Lato:300,300i,400,400i',
				  '//www.tinymce.com/css/codepen.min.css'
				]
			 });
		</script>
		<script>
			function showAddingImgs() {
				var div = document.getElementById('addingImgs');
				if (div.style.display == 'block') {
					div.style.display='none';
				} else {
					div.style.display='block';
				}
			}
		</script>
		
		<title>New Article</title>
	</head>
	<body>
		<div class="container">
			<h3>New Article</h3>
		</div>
		<div class="container" style="margin-bottom: 15px;">
			<img id="main_img" src="image?imgName=${article.imgPath}" height="300" />
		</div>
		<div id="addingImgs" style="display:none">
			<div class="container">
				<c:if test="${article.id > 0}">
					<c:forEach var="filename" items="${imagesList}">
						<div class="col-sm-6 col-md-3" align="center">
							<a onclick="changeImage('${filename}')" title="make_main"><img src="image?imgName=${filename}" height="120" /></a>
							<a href="deleteImg${article.id}?imgName=${filename}" title="delete_image"><span class="glyphicon glyphicon-remove"></span></a>
						</div>
					</c:forEach>
				</c:if>
			</div>
			<div class="container">
				<c:if test="${article.id > 0}">
					<cf:form method="post" action="saveArticleImg${article.id}"
							modelAttribute="uploadForm" enctype="multipart/form-data">
						<p>Select file to upload.</p>
						<input class="btn btn-primary" name="files[0]" type="file" />
						<br>
						<input class="btn btn-primary" type="submit" value="Upload Images" />
					</cf:form>
				</c:if>
			</div>
		</div>
		<div class="container">
			<c:if test="${article.id > 0}">
				<input class="btn btn-default" type="submit" value="Add Image" 
						onclick="showAddingImgs()"/>
			</c:if>
		</div>
		<div class="container">
			<cf:form id="articleForm" method="post" action="addArticle" modelAttribute="article">
				<cf:hidden path="id" />
				<cf:hidden id="image_path" path="imgPath" />
	
				<div class="form-group" style="max-width: 600px;">		
					<cf:label path="header">Header</cf:label>
					<cf:input id="header" path="header" class="form-control"/>
				</div>
				<div class="form-group">		
					<cf:textarea path="content" id="content" />
				</div>
				<input type="submit" class="btn btn-primary" value="Submit"/>
			</cf:form>
		</div>
	</body>
</html>