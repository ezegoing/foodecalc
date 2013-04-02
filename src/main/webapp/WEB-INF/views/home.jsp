<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Welcome! Please Login or <a href = "newuser">Register</a>
</h1>

<div>
	<c:if test="${not empty error}">
		<div class="errorblock">
			Your login attempt was not successful, try again.<br /> Caused :
			${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
		</div>
	</c:if>
 
	<form name='f' action="<c:url value='j_spring_security_check' />"
		method='POST'>
 
		<table>
			<tr>
				<td>User:</td>
				<td><input type='text' name='j_username' value=''>
				</td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input type='password' name='j_password' />
				</td>
			</tr>
			<tr>
				<td colspan='2'><input name="submit" type="submit"
					value="submit" />
				</td>
			</tr>
			<tr>
				<td colspan='2'><input name="reset" type="reset" />
				</td>
			</tr>
		</table>
 
	</form>
</div>

<input type = "button" value = "Food Units" onClick="window.location='foodUnits/viewAll'" name="foodUnits" />
<input type = "button" value = "Food Portions" onClick="window.location='foodPortions/viewAll'" name="foodPortions" />
<input type = "button" value = "Meals" onClick="window.location='meals/viewAll'" name="meals" />
<input type = "button" value = "New User" onClick="window.location='newuser'" name="newuser" />
</body>
</html>
