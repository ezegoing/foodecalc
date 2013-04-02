<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Data Get page! 
</h1>

<P>  The time on the server is ${serverTime}. </P>

<P>  The allRecords var is ${allRecords}  </P>

<table border = "1">

<tr>
<td>Id</td>
<td>Name</td>
<td>User Id</td>
</tr>

            <c:forEach items="${allRecords}" var="value" varStatus="loop">
            <tr>
                <td class="center">${value.id}</td>
                <td class="center">${value.name}</td>
                <td class="center">${value.user_id}</td>
            </tr>
        </c:forEach>
</table>        

</body>
</html>