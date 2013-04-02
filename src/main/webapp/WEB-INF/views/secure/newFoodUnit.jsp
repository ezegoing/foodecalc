<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<jsp:include page="../includes/header.jsp"/>
<jsp:include page="../includes/logout.jsp"/>

	<h1>Welcome to new food unit...</h1>
	<form:form commandName="foodUnit" method="post">
		<table>
		<tr>
			<td>Food Unit Name</td>
			<td><form:input path="name" /></td>
		</tr>
		<tr>
			<td>Protein</td>
			<td><form:input path="protein" /></td>
		</tr>
		<tr>
			<td>Carbs</td>
			<td><form:input path="carbs" /></td>
		</tr>
		<tr>
			<td>Fat</td>
			<td><form:input path="fat" /></td>
		</tr>
		<tr>
			<td><input type="submit" value="Submit" /></td>
		</tr>
		</table>
	</form:form>
	
<jsp:include page="../includes/footer.jsp"/>
