<%@ page import="ecommerce.model.User" %>
<%@ page import="ecommerce.util.Constants" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
<form action="login.jsp">
	<button type="submit" type="button">Sign Out</button>
</form>

<form action="HomeController" method="Get">
	<button type="submit" type="button">Categories</button>
</form>

<%if(session.getAttribute("role").equals(Constants.OWNER)){%>
		<form action="HomeController" method="Post">
		<button type="submit" type="button">Products</button>
		</form>
	<%}%>

</html>

<!-- This is from Kevin Gu -->
<!-- This is from Gil Olaes -->