<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table>
		<c:forEach items="${urls}" var="url">
			<tr>
				<td><c:out value="${url}" /></td>	
			</tr>
		</c:forEach>
</table>
<br />
<table>
		<c:forEach items="${extensions}" var="ext">
			<tr>
				<td><c:out value="${ext}" /></td>
			</tr>
		</c:forEach>
</table>
</body>
</html>