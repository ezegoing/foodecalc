<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<jsp:include page="../includes/header.jsp"/>
<jsp:include page="../includes/logout.jsp"/>

	<h1>
		Food Units Portion Selection page
	</h1>
	
	
	<table>
		<tr>
			<th>Id</th>
			<th>Name</th>
			<th>Protein</th>
			<th>Carbs</th>
			<th>Fats</th>
		</tr>
		<c:forEach var="foodUnits" items="${fuList}">
		<tr>
			<td>${foodUnits.id}</td>
			<td>${foodUnits.name}</td>
			<td>${foodUnits.protein}</td>
			<td>${foodUnits.carbs}</td>
			<td>${foodUnits.fat}</td>
			<td><input type = "button" onClick="window.location='../../foodPortions/newUnit?id=${foodUnits.id}'" value="Select" /></td>
		</tr>
		</c:forEach>
	</table>

<jsp:include page="../includes/footer.jsp"/>
