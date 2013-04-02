<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<jsp:include page="../includes/header.jsp"/>
<jsp:include page="../includes/logout.jsp"/>
	
	<h1>Get all meals</h1>
	
	<input type = "button" value = "New Meal" onclick="window.location='new'" />
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
		
<jsp:include page="../includes/footer.jsp"/>
