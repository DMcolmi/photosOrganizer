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
	<br />
	<table>
		<c:forEach items="${extensions}" var="ext">
			<tr>
				<td><c:out value="${ext}" /></td>
			</tr>
		</c:forEach>
	</table>
	<br />
	<br />
	<table>
		<c:forEach items="${makes}" var="make">
			<tr>
				<td><c:out value="${make}" /></td>
			</tr>
		</c:forEach>
	</table>
	<br />
	<br />
	<form action="cleanModelMetadata">
	Selezionare i modelli corretti da tenere. i modelli non selezionati verranno cancellati <br /> 
		<table>
			<c:forEach items="${models}" var="model">
				<tr>
					<td>
						<div>
							<input type="checkbox" id="model" name="model" value="${model}">
							<label for="model">${model}</label>
						</div>
					</td>
				</tr>
			</c:forEach>
		</table>
		<input type="submit" value="Cancella i modelli non selezionati"/>
	</form>
</body>
</html>