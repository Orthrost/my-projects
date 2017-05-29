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
		<form:form id="changeForm" method="post" action="edit" modelAttribute="changeForm">
			
			<div class="successMsg"> 
			${successfulMessage}
			<br>
			</div>
			<form:label path="id">Id: </form:label>
			<form:input id="id" name="id" path="" readonly="true" value="${customer.getId()}" /><br>
			

			<form:label path="firstName">First name: </form:label>
			<form:input id="firstName" name="firstName" path="" value="${customer.getFirstName()}"/>
			<form:errors path="firstName" class="errors"/><br>
			
			<form:label path="lastName">Last name: </form:label>
			<form:input id="lastName" name="lastName" path="" value="${customer.getLastName()}"/>
			<form:errors path="lastName" class="errors"/><br>
			
			<form:label path="dateOfBirth">Date of birth: </form:label>
			<input type="text" class= "date" name = "dateOfBirth" value = "<fmt:formatDate value="${customer.getDateOfBirth()}" pattern="MM-dd-yyyy" />"/>
			<form:errors path="dateOfBirth" class="errors"/><br>
			
			<form:label path="username">User name: </form:label>
			<form:input id="username" name="username" path="" value="${customer.getUsername()}"/>
			<form:errors path="username" class="errors"/><br>
			
			<form:label path="password">Password: </form:label>
			<form:input id="password" name="password" path="" value="${customer.getPassword()}"/>
			<form:errors path="password" class="errors"/><br>
			
			<input class="btn btn-success btn-large" type="submit" value="Save changes" />
			
			<br>
			

			
		</form:form>
							
					<button onclick="parent.location='${baseUrl}/';" class="btn btn-default btn-large">Back
            </button>
					<button onclick="parent.location='${baseUrl}/edit?id=${customer.getId()}&delete=true';" class="btn btn-danger btn-large">Delete</button>
		</div>
		

	</body>
</html>