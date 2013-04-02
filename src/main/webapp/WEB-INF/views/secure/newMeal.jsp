<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<jsp:include page="../includes/header.jsp"/>
<jsp:include page="../includes/logout.jsp"/>

	Please select a food portion to add to this meal

	<br />
	<input type = "button" onClick = "window.location='../meals/select/portion'" value = "Select Food Portion"  />
	<br />
	
		<table>
		<tr>
			<td>Food Portion Name</td>
			<td>
				<c:if test="${param.id!=null}">
					<c:out value="${param.id}" />
				</c:if>
			</td>
		</tr>
		<c:forEach var = "FoodUnit" items = "${fuList}">
		<tr>
			<td>Food Unit Name</td>
			<td>
				<c:if test="${param.id!=null}">
					<c:out value="${FoodUnit.name}" />
				</c:if>
			</td>
		</tr>
		<tr>
			<td>Protein</td>
			<td>
				<c:if test="${param.id!=null}">
					<c:out value="${FoodUnit.protein}" />
				</c:if>
			</td>
		</tr>
		<tr>
			<td>Carbs</td>
			<td>
				<c:if test="${param.id!=null}">
					<c:out value="${FoodUnit.carbs}" />
				</c:if>
			</td>
		</tr>
		<tr>
			<td>Fat</td>
			<td>
				<c:if test="${param.id!=null}">
					<c:out value="${FoodUnit.fat}" />
				</c:if>
			</td>
		</tr>
		</c:forEach>
		<tr>
			<td>Name</td>
			<td><input type="text" name="name" <c:if test="${param.id==null}">disabled</c:if> /></td>
		</tr>
		<tr>
			<td>Weight</td>
			<td><input type="text" name="weight" <c:if test="${param.id==null}">disabled</c:if>/></td>
		</tr>
		
		</table>
		
	

<jsp:include page="../includes/footer.jsp"/>