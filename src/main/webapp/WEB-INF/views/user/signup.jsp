<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
		
		<link rel="stylesheet" href="//code.jquery.com/ui/1.12.0/themes/base/jquery-ui.css">
		<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
		<script src="https://code.jquery.com/ui/1.12.0/jquery-ui.js"></script>
		<script  type="text/javascript">
			$( function() {
			    $( "#datepicker" ).datepicker({ dateFormat: "yy-mm-dd" });
			  } );
		</script>
		<style>
			.error {
				color: red;
				font-weight: bold;
			}
		</style>
		
		<title>Registration Page</title>
	</head>
	<body>
		<div class="container">
			<h3>Registration form</h3>
			<br>
			<p>Enter your data and click "Submit"</p>
			<br>
			<p style="color: red">${errorMessage}</p>
			<p style="color: green">${doneMessage}</p>
		</div>
		<div class="container">
			<div class="row">
				<div class="col-sm-12 col-md-5">
					<form:form id="signupForm" method="post" action="signup"
						modelAttribute="user">
						
						<form:hidden path="id" />
						<form:hidden path="role" />
						
						<div class="form-group">
							<form:label path="email">Email</form:label>
							<form:input id="email" name="email" path="email" class="form-control"/>
							<form:errors path="email" cssClass="error" />
						</div>
						<div class="form-group">
							<form:label path="fName">First name</form:label>
							<form:errors path="fName" cssClass="error" />
							<form:input id="fName" path="fName" class="form-control"/>
						</div>
						<div class="form-group">
							<form:label path="lName">Last name</form:label>
							<form:errors path="lName" cssClass="error" />
							<form:input id="lName" path="lName" class="form-control"/>
						</div>
						<div class="form-group">	
							<form:label path="birthdate">Birthdate</form:label>
							<form:errors path="birthdate" cssClass="error" />
							<form:input id="datepicker" class="form-control" path="birthdate" /> 
						</div>	
						<div class="form-group">
							<form:label path="city">City</form:label>
							<form:errors path="city" cssClass="error" />
							<form:input id="city" path="city" class="form-control"/>
						</div>
						<div class="form-group">	
							<form:label path="sex">Sex</form:label>
							<form:select path="sex" class="form-control">
								<option value="F" ${user.sex.equals("F") ? 'selected="selected"' : ''}>Female</option>
								<option value="M" ${user.sex.equals("M") ? 'selected="selected"' : ''}>Male</option>
							</form:select>
						</div>
						<div class="form-group">		
							<form:label path="phone">Phone number</form:label>
							<form:errors path="phone" cssClass="error" />
							<form:input id="phone" path="phone" class="form-control"/>
						</div>	
						<div class="form-group">
							<form:label path="password">Password</form:label>
							<form:errors path="password" cssClass="error" />
							<form:password id="password" path="password" class="form-control"/>
						</div>
						<input type="submit" class="btn btn-primary" value="Submit"/>
					</form:form>
				</div>
			</div>
		</div>
	</body>
</html>
