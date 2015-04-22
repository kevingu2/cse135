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
	<%if(session.getAttribute("name")!=null){ %>
			<h1>Hello <%=session.getAttribute("name") %></h1>
		<form action="login.jsp">
			<input type="hidden" value="signout" name="action">
			<button type="submit" type="button">Sign Out</button>
		</form>
		<%if(session.getAttribute("role").equals(Constants.OWNER)){%>
				<form action="CategoryController" method="get">
					<button type="submit" type="button">Categories</button>
					<input type="hidden" name="action" value="select">
				</form>
		
				<form action="ProductController" method="get">
				<button type="submit" type="button">Products</button>
				<input type="hidden" name="action" value="select">
				</form>
		<%}%>
	<%}else{ %>
		<h1>No user logged in</h1>
		<form action="LoginController" method="get">
		<input type="hidden" value="signout" name="action">
		<button type="submit" type="button">Login</button></form>
	<%} %>
</body>

</html>

<!-- This is from Kevin Gu -->
<!-- This is from Gil Olaes -->