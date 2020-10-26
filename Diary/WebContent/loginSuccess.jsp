<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h3>Diary</h3>
	<form action="DiaryServlet" method="post">
		<textarea name="diaryText" rows="20" cols="100">Add content to diary...</textarea>
		<br><br> <input type="submit" value=Save>
	</form> <br>
	
	<form action="LogoutServlet" method="post">
		<input type="submit" value="Logout">
	</form>
</body>
</html>