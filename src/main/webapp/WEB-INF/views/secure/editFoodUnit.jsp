<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<jsp:include page="../includes/header.jsp"/>
<jsp:include page="../includes/logout.jsp"/>

	<h1>Edit food unit...</h1>
	<form:form action="saveEdit" method="post" commandName="fu">
		<table>
		
		<tr>
			<td>Food Unit Name</td>
			<td><form:input path="name"/>
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
		<input type = "hidden" name="id" value="${fu.id}" />
	</form:form>
	
<jsp:include page="../includes/footer.jsp"/>
