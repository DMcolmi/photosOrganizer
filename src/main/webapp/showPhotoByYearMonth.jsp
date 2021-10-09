<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Photos by month</title>
</head>
<body>
	<table>
		<tr> <th>Data</th> <th>Nome</th><th>Formato</th><th>Path</th><th>Fotocamera</th><th>Produttore Fotocamera</th> </tr>
		<c:forEach items="${photos}" var="photo">
			<tr> 
				<td><fmt:formatDate value="${photo.date}" type="date"/></td>
				<td>${photo.originalName}</td>
				<td>${photo.fileExtension}</td>
				<td>${photo.originalUrlLocation}</td>
				<td>${photo.model}</td>
				<td>${photo.make}</td>
			</tr>
		</c:forEach>
	</table>
	<br> 
	<br>
	<form action="InsertUrlForm">
		<input type="submit" value="torna alla pagina iniziale" >
	</form>

</body>
</html>