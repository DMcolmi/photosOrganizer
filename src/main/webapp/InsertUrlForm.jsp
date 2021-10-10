<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="InsertUrl">
		Inserire il path delle foto: <br /> 
		<input type="text" name="url"> <br /> 
		<input type="radio" name="dirType" value="src">Path origine foto <br>
		<input type="radio" name="dirType" value="trg">Path destinazione foto <br>
		<input type="submit" value="Submit" />
	</form>
	
	<br /> 
	<form action="ShowUrls">
		<input type="submit" value="Mostra Url-s inseriti">
	</form>
	<form action="FetchUrlAndExt">
		<input type="submit" value="Show Saved Data">
	</form>
</body>
</html>