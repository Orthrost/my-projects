<%@include file="include.jsp"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html>
<head>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    
	</head>
	
	<body>
	

	
	
	
	<div class="container">
		<h1>Customer list</h1>
	            <button onclick="parent.location='${baseUrl}/add';" class="btn btn-success btn-large">Add
                new customer
            </button>
        <table class="table table-striped table-hover">
            <thead>
            <tr>
                <th>Id</th>
                <th>First name</th>
                <th>Last name</th>
                <th>Date of birth</th>
                <th>User name</th>
                <th>Password</th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="customer" items="${customers}">
                <tr>
                	<td><c:out value="${customer.getId()}"/></td>
                    <td><c:out value="${customer.getFirstName()}"/></td>
                    <td><c:out value="${customer.getLastName()}"/></td>
                    <td><c:out value="${customer.getDateOfBirth()}"/></td>
                    <td><c:out value="${customer.getUsername()}"/></td>
                    <td><c:out value="${customer.getPassword()}"/></td>
                    <td><button onclick="parent.location='${baseUrl}/edit?id=${customer.getId()}';" class="btn btn-default btn-large">Edit</button>
                    
                    
                    </td>
                            
                </tr>
            </c:forEach>
            </tbody>
        </table>

		</div>
	</body>
</html>