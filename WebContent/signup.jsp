<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Sign Up</title>

</head>
<body>
	<form action="LoginController" method="post">
	Name:<br>
	<input type="text" name="name">
	<br>
	Role:<br>
	<input type="text" name="role">
	<br>
	Age:<br>
	<input type="text" name="age">
	<br>
	State:<br>
	<input type="text" name="state">
	<br>
	<button type="submit" type="button">Submit</button>
	</form>
</body>
</html>