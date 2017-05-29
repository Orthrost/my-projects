<%@include file="include.jsp"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html>
<head>

  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
      <style>
  	.errors {
  	color: #ff0000; font-size:16px;
  	}
	.successMsg{
	color:#33cc00; font-size:20px;"
	}

  </style>
	</head>
	
	<body>
	<div class="container">
		<form:form id="customerAddForm" method="post" action="add" modelAttribute="customerAddForm">
		
			<div class="successMsg"> 
			${successfulMessage}
			<br>
			</div>

			<form:label path="firstName">First name: </form:label>
			<form:input id="firstName" name="firstName" path="" />
			<form:errors path="firstName" class="errors"/><br>

			
			<form:label path="lastName">Last name: </form:label>
			<form:input id="lastName" name="lastName" path="" />
			<form:errors path="lastName" class="errors"/><br>
			
			<form:label path="dateOfBirth">Date of birth: </form:label>
			<input type="date" class= "date" name = "dateOfBirth" value = "<fmt:formatDate value="${cForm.dateOfBirth}" pattern="MM-dd-yyyy" />"/>
			<form:errors path="dateOfBirth" class="errors"/><br>
			
			<form:label path="username">User name: </form:label>
			<form:input id="username" name="username" path="" />
			<form:errors path="username" class="errors"/><br>
			
			<form:label path="password">Password: </form:label>
			<form:input id="password" name="password" path="" />
			<form:errors path="password" class="errors"/><br>
			
			<input class="btn btn-success btn-large" type="submit" value="Add" />
		</form:form>
		
		
		
			<button onclick="parent.location='${baseUrl}/';" class="btn btn-succes btn-large">Back
            </button>
	
		</div>
	</body>
</html>