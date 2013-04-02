<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<jsp:include page="../includes/header.jsp"/>
<jsp:include page="../includes/logout.jsp"/>

	<h1>
		Food Portions Page  
	</h1>
	<a href="new">New Food Portion</a>
	
	<table>
		<tr>
			<th>Id</th>
			<th>Food Unit Id</th>
			<th>User Id</th>
			<th>Portion Name</th>
			<th>Weight</th>
			<th>Calories</th>
		</tr>
		<c:forEach var="foodPortions" items="${fpList}">
		<tr>
			<td>${foodPortions.id}</td>
			<td>${foodPortions.food_unit_id}</td>
			<td>${foodPortions.user_id}</td>
			<td>${foodPortions.name}</td>
			<td>${foodPortions.weight}</td>
			<td>${foodPortions.calories }</td>
			<td><a href="edit?id=${foodPortions.id }">Edit</a>
		</tr>
		</c:forEach>
	</table>

<jsp:include page="../includes/footer.jsp"/>
