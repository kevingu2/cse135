<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
<body>
	<%if(request.getAttribute("login")!=null){ %>
		<h1>The provided name <%=request.getAttribute("login")%> is not known and Please complete the name textbox</h1>
	<%}%>
	<form action="LoginController" method="get">
		Enter name : <input type="text" name="name"> <BR>
		<input type="submit" />
		<input type="hidden" name="action" value="login">
	</form>
</body>
	<form action="LoginController" method ="get">
	<input type="hidden" name="action" value="signup">
		<button type="submit" type="button">Sign Up</button>
	</form>
	
</head>
<body>

</body>
</html>