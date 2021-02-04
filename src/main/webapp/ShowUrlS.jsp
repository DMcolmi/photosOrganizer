<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
				<td><form action="InsertPhotosFromUrl">
					<input type="text" name="url" value="${url.url}">
					<input type="submit" value="Submit" />
				</form></td>
			</tr>
		</c:forEach>
	</table>
	<br />
	<form action="InsertUrlForm">
		<input type="submit" value="Torna Indietro">
	</form>
</body>
</html>