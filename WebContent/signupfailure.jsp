<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Sign Up Failure</title>
</head>
<% 
if(request.getAttribute("linked")==null){ %>
	<h1>Bad page access. Please try again or follow a valid link to this page.</h1>
	<form action="HomeController" method = "post">
	<button type="submit" type="button">Home</button></form>
	</body>
<%
return; }%>
<body>
	<h1>signup failure</h1>
	<form action="login.jsp">
		<button type="submit" type="button">login</button></form>
	<form action="LoginController" method ="get">
	<input type="hidden" name="action" value="signup">
		<button type="submit" type="button">Sign Up</button>
	</form>
</body>
</html>