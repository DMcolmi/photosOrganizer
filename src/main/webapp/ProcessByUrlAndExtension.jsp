<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>pix properties</title>
	</head>

	<body>
		<table>
			<c:forEach items="${urls}" var="url">
				<tr>
					<td><c:out value="${url}" /></td>
				</tr>
			</c:forEach>
		</table><br><br>
		
		<table>
			<c:forEach items="${extensions}" var="ext">
				<tr>
					<td><c:out value="${ext}" /></td>
				</tr>
			</c:forEach>
		</table>
		<br><br>
		
		<table>
			<c:forEach items="${makes}" var="make">
				<tr><td><c:out value="${make}" /></td></tr>
			</c:forEach>
		</table>
		<br><br>
		
		<ul>
			<c:forEach items="${photoYears}" var="year">
				<li><c:out value="${year}" /></li>
			</c:forEach>		
		</ul>
		<br><br>
		
		<form action="cleanModelMetadata">
			Selezionare i modelli corretti da tenere. i modelli non selezionati
			verranno cancellati <br />
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
			</table><br><br>
			
			<input type="reset" name="resetButton" value="cancella dati inseriti">
			<br><br>
			<input type="submit" value="Cancella i modelli non selezionati" />
			<br><br>
		</form>
		
		<form action="showPhotoByYearMonth">
			<select name = "photoYear">
				<option value="${selected}" selected>${selected}</option>
				<c:forEach items="${photoYears}" var="photoYear"> 
					<c:if test="${photoYear != selected}"> 
						<option value="${photoYear}" id="photoYear">${photoYear}</option>
					</c:if>
				</c:forEach>			
			</select>
			
			<select name="photoMonth">
			 	<option value="${selected}" selected>${selected}</option>
				<c:forEach items="${photoMonths}" var="photoMonth">
					<c:if test="${photoMonth != selected}"> 
						<option value="${photoMonth}" id="photoMonth">${photoMonth}</option>
					</c:if>
				</c:forEach>			
			</select>
			
			<br>
			<input type="submit" value="cerca foto">			
		
		</form>
		
	</body>
</html>