<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/js/themes/base/jquery.ui.all.css">


	<script src="${pageContext.request.contextPath}/resources/js/jquery-1.7.1.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/ui/jquery.ui.core.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/ui/jquery.ui.widget.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/ui/jquery.ui.position.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/ui/jquery.ui.autocomplete.js"></script>
	<%-- <link href="<c:url value="/resources/css/style.css" />"rel="stylesheet"> --%>
	<script type="text/javascript">
		$(function() {
			var projects = (function() {
				var projects = null;
				var pathTo = "${pageContext.request.contextPath}";
				var pathTo2 = "/resources/productList.json";
				pathTo2 = pathTo.concat(pathTo2);
				$.ajax({
					'async' : false,
					'global' : false,
					'url' : pathTo2,
					'dataType' : "json",
					'success' : function(data) {
						projects = data;
					}
				});
				return projects;
			})();

			$("#project").autocomplete({
				minLength : 0,
				source : projects,
				focus : function(event, ui) {
					return false;
				},
				select : function(event, ui) {
					location.href = 'product' + ui.item.value;
				}
			}).data("autocomplete")._renderItem = function(ul, item) {
				return $("<li></li>").data("item.autocomplete", item).append(
						"<a><b>" + item.label + "</b> " + item.desc + "</a>")
						.appendTo(ul);
			};
		});
	</script>
	
	
	<nav class="navbar navbar-default">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
		        <span class="icon-bar"></span>
		        <span class="icon-bar"></span>
		        <span class="icon-bar"></span> 
	     	 </button>
			<a class="navbar-brand" href="/eShop">Bath&Health</a>
		</div>
		<div class="collapse navbar-collapse" id="myNavbar">
		<ul class="nav navbar-nav">
			 <li class="dropdown"> 
			 	<a href="/eShop/goods.html" style="display: inline-block;">Goods</a><a class="dropdown-toggle" data-toggle="dropdown" href="#" style="display: inline-block;"><span class="caret"></span></a>
				<ul class="dropdown-menu">
					<c:forEach var="item" items="${categoryTreeMain}">
						${item}
					</c:forEach>
				</ul>
			</li>
			<li class="dropdown"> 
				<a class="dropdown-toggle" data-toggle="dropdown" href="#">Info<span class="caret"></span></a>
				<ul class="dropdown-menu">
					<li><a href="/eShop/articles.html">Articles</a></li>
					<li><a href="">Delivery</a></li>
					<li><a href="">Contacts</a></li>
				</ul>
			</li>
			<li>
				<input id="project" type="text" class="form-control"
						role="textbox" aria-autocomplete="list" aria-haspopup="true" style="width: 250px; margin: 10px;"
    					placeholder="Type what you are looking for...">
			</li>
		</ul>
		<ul class="nav navbar-nav navbar-right">
			<c:if test="${user.role eq 2}">
				<li class="dropdown"> 
					<a class="dropdown-toggle" data-toggle="dropdown" href="#">Admin<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li class="dropdown-submenu">
							<a class="test" href="#">Product<span class="caret"></span></a>
							<ul class="dropdown-menu">
								<li><a href="/eShop/admin/products.html">Admin Products</a></li>
								<li><a href="/eShop/admin/newProduct.html">New Product</a></li>
							</ul>
							<a class="test" href="#">Orders<span class="caret"></span></a>
							<ul class="dropdown-menu">
								<li><a href="/eShop/admin/orders.html">Orders</a></li>
								<li><a href="/eShop/admin/newOrder.html">New order</a></li>
							</ul>
							<a class="test" href="#">Users<span class="caret"></span></a>
							<ul class="dropdown-menu">
								<li><a href="/eShop/admin/users.html">Users</a></li>
								<li><a href="/eShop/admin/newUser.html">New User</a></li>
							</ul>
							<a class="test" href="#">News<span class="caret"></span></a>
							<ul class="dropdown-menu">
								<li><a href="/eShop/admin/articles.html">Admin Articles</a></li>
								<li><a href="/eShop/admin/newArticle.html">New Article</a></li>
							</ul>
							<a class="test" href="#">Categories<span class="caret"></span></a>
							<ul class="dropdown-menu">
								<li><a href="/eShop/admin/tree.html">Tree of categories</a></li>
								<li><a href="/eShop/admin/createCategory">Add category</a></li>
							</ul>
						</li>
					</ul>
				</li>
			</c:if>
				<li class="dropdown"> 
					<c:if test="${user.id==0 || user == null}">
						<a class="dropdown-toggle" data-toggle="dropdown" href="#">Account<span class="glyphicon glyphicon-user"></span></a>
						<ul class="dropdown-menu">
							<li>
								<div class="container-fluid">
								<form:form class="" id="loginForm" method="post" action="/eShop/login"
								modelAttribute="user" style="margin-bottom: 0px">
								<p style="color: red">${errorMessage}</p>
								<form:label path="email">Email</form:label>
								<form:input name="username" path="email" class="form-control" id="usr"/>
								<br>
								<form:label path="password">Password</form:label>
								<form:password name="password" path="password" class="form-control" id="pwd"/>
								<input type="submit" value="Log in" class="btn btn-default" style="margin: 10px;"/>
								</form:form>
								<p style="text-align: center">Or</p>
							
								<div class="container-fluid" style="">
									<button type="button" onclick="location.href = '/eShop/signup'" 
										class="btn btn-primary" style="width: 100%">Sign up</button>
									</div>
								</div>
							</li>
						</ul>
					</c:if>
					<c:if test="${user.id!=0}">
						<a class="dropdown-toggle" data-toggle="dropdown" href="#">Hi, ${user.fName}!  <span class="glyphicon glyphicon-user"></span></a>
						<ul class="dropdown-menu">
							<li><a href="/eShop/signup">Personal Data</a></li>
							<li><a href="/eShop/logout"><b>Logout</b></a></li>
						</ul>
					</c:if>
				</li>
				<li>
					<a href="/eShop/basket.html">Cart</a>
				</li>
			</ul>
		</div>
	</div>
			

</nav>
