<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ShowUrlS</title>
</head>
<body>
	<table>
		<c:forEach items="${urls}" var="url">
			<tr>
				<td><c:out value="${url.id}" /></td>
				<td><c:out value="${url.url}" /></td>
			</tr>
		</c:forEach>
	</table> <br /> 
	<form action="InsertUrlForm.jsp">
		<input type="submit" value="Torna Indietro">
	</form>
</body>
</html>