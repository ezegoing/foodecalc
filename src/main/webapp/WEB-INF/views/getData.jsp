<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<body>
	<h1>Welcome to get Data Page...</h1>
	<h2>Get all meal names</h2>
	<table border=1>
	<tr><th>Meal Id</th><th>Meal Name</th><th>User Id</th></tr>
	
	
	<c:forEach var="food_unit" items="${fuList}">
	<tr>
		<td>${food_unit.id}</td>
		<td>${food_unit.name}</td>
		<td>${food_unit.user_id}</td>
	</tr>
	</c:forEach>
	
	</table>
	
	<h2>Get User 1 meals</h2>
	<table border=1>
	<tr><th>Meal Id</th><th>Meal Name</th><th>User Id</th></tr>
	
	<tr>
	<c:forEach var="food_unit" items="${userList}">
		<td>${food_unit.id}</td>
		<td>${food_unit.name}</td>
		<td>${food_unit.user_id}</td>
	</c:forEach>
	</tr>
	</table>
	
	<form action="addMeal" method="get">
	Enter meal name:<input type = "text" name = "name" /><br/>
	Enter user ID:<input type = "text" name = "user_id" />
	<input type = "submit" value = "Submit" />
	</form>
</body>
</html>