<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		 
		<!-- Latest compiled and minified CSS -->
		<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
		<!-- jQuery library -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
		<!-- Latest compiled JavaScript -->
		<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
		
		<script type="text/javascript">
			 	$(document).ready(function() {
			 $('.dropdown-submenu a.test').on("click", function(e) {
			 $(this).next('ul').toggle();
			 e.stopPropagation();
			 e.preventDefault();
			 });
			 });
		</script>
		
		<title>eShop</title>
	</head>
	<body>
		<div>
			<div>
				<tiles:insertAttribute name="header" />
			</div>
			<div>
				<tiles:insertAttribute name="menu" />
			</div>
			<div>
				<tiles:insertAttribute name="body" />
			</div>
			<div>
				<tiles:insertAttribute name="footer" />
			</div>
		</div>
	</body>
</html>
