<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<jsp:include page="../includes/header.jsp"/>
<jsp:include page="../includes/logout.jsp"/>

	<h1>Welcome to new food portion...</h1>
	A food unit must be selected first...
	<br />
	<input type = "button" onClick = "window.location='../foodUnits/select/portion'" value = "Select Food Unit"  />
	<br />
	<form:form action="save" method="post" commandName = "fu">
		<table>
		<tr>
			<td>Food Unit Id</td>
			<td>
				<c:if test="${param.id!=null}">
					<c:out value="${param.id}" />
				</c:if>
			</td>
		</tr>
		
		<tr>
			<td>Food Unit Name</td>
			<td>
				<c:if test="${param.id!=null}">
					<c:out value="${fu.name}" />
				</c:if>
			</td>
		</tr>
		<tr>
			<td>Protein</td>
			<td>
				<c:if test="${param.id!=null}">
					<c:out value="${fu.protein}" />
				</c:if>
			</td>
		</tr>
		<tr>
			<td>Carbs</td>
			<td>
				<c:if test="${param.id!=null}">
					<c:out value="${fu.carbs}" />
				</c:if>
			</td>
		</tr>
		<tr>
			<td>Fat</td>
			<td>
				<c:if test="${param.id!=null}">
					<c:out value="${fu.fat}" />
				</c:if>
			</td>
		</tr>
		
		<tr>
			<td>Name</td>
			<td><input type="text" name="name" <c:if test="${param.id==null}">disabled</c:if> /></td>
		</tr>
		<tr>
			<td>Weight</td>
			<td><input type="text" name="weight" <c:if test="${param.id==null}">disabled</c:if>/></td>
		</tr>
		
		</table>
		<input type = "hidden" name="id" value="${param.id}" />
		<input type="submit" value="Submit" <c:if test="${param.id==null}">disabled</c:if>/>
		<input type="reset" value="Reset" <c:if test="${param.id==null}">disabled</c:if>/>
	</form:form>

<jsp:include page="../includes/footer.jsp"/>