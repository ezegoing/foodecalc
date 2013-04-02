<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<jsp:include page="../includes/header.jsp"/>
<jsp:include page="../includes/logout.jsp"/>

	<h1>Edit food portion...</h1>
	<form action="saveEdit" method="post">
		<table>
		<c:forEach var="foodPortion" items="${fpList}">
		<tr>
			<td>Food Unit Id</td>
			<td>${foodPortion.food_unit_id}</td>
			
		</tr>
		<tr>
			<td>User Id</td>
			<td>${foodPortion.user_id}</td>
			
		</tr>
		<tr>
			<td>Name</td>
			<td><input type="text" name="name" value="${foodPortion.name}" /></td>
		</tr>
		<tr>
			<td>Weight</td>
			<td><input type="text" name="weight" value="${foodPortion.weight}" /></td>
		</tr>
		<tr>
			<td><input type="submit" value="Submit" /></td>
		</tr>
		<input type = "hidden" name="id" value="${foodPortion.id}" />
		<input type = "hidden" name = "food_unit_id" value = "${foodPortion.food_unit_id}" />
		<input type = "hidden" name = "user_id" value = "${foodPortion.user_id}" />
		</c:forEach>
		</table>
		
	</form>

<jsp:include page="../includes/footer.jsp"/>