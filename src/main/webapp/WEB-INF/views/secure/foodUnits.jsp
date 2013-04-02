<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<jsp:include page="../includes/header.jsp"/>
<jsp:include page="../includes/logout.jsp"/>
<style>
	
	tr:nth-child(even) {
    background-color: #ffdb66;
}
</style>
	<h1>
		Food Units Page  
	</h1>
	
	<h2>
		User Id - ${userid}
	</h2>
	<a href = "new">New Food Unit</a>
		
	<table>
		<tr>
			<th>Id</th>
			<th>Name</th>
			<th>Protein</th>
			<th>Carbs</th>
			<th>Fats</th>
			<th>Calories</th>
		</tr>
		<c:forEach var="foodUnits" items="${fuList}">
		<tr>
			<td>${foodUnits.id}</td>
			<td>${foodUnits.name}</td>
			<td>${foodUnits.protein}</td>
			<td>${foodUnits.carbs}</td>
			<td>${foodUnits.fat}</td>
			<td>${foodUnits.calories }</td>
			<td><a href="edit?id=${foodUnits.id }">Edit</a></td>
		</tr>
		</c:forEach>
	</table>
<jsp:include page="../includes/footer.jsp"/>