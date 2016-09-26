<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		
		<script src="${pageContext.request.contextPath}/resources/js/jquery-1.7.1.js"></script>
		<script src="${pageContext.request.contextPath}/resources/js/ui/jquery.ui.core.js"></script>
		<script src="${pageContext.request.contextPath}/resources/js/ui/jquery.ui.widget.js"></script>
		<script src="${pageContext.request.contextPath}/resources/js/ui/jquery.ui.position.js"></script>
		<script src="${pageContext.request.contextPath}/resources/js/ui/jquery.ui.autocomplete.js"></script>
		
		<script type="text/javascript">
			$(document).ready(function(){
			 var myTextareaVal = $('#message-textarea').val();
			 var myLineBreak = myTextareaVal.replace(/([^>\r\n]?)(\r\n|\n\r|\r|\n)/g, '\n');
			 
			 $.ajax({
			  url : 'sendEmail',
			  type: 'GET',
			  data : {
			   adminMail: '${adminMail}',
			  userMail: '${userMail}',
			   theme: '${theme}',
			   message: myLineBreak,
			  }
			 })
			})
		</script>
		
		<title>Payment successful!</title>
	</head>
	<body>
		<input type="hidden" id = "message-textarea" value = "${message}" >
		SUCCESS!!!
	</body>
</html>