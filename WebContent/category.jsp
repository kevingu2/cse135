<%@ page import="ecommerce.model.Category"%>
<%@ page import="ecommerce.model.User" %>
<%@ page import="java.util.ArrayList"%>
<%@ page import="ecommerce.util.Constants" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Categories Page</title>
</head>

<% 
if(session.getAttribute("role")==null)
{
	%>
	<h1>No user logged in</h1>
	<form action="LoginController" method="get">
	<input type="hidden" value="signout" name="action">
	<button type="submit" type="button">Login</button></form>
	</body>
	<% 
}

else if(request.getAttribute("linked")==null){ %>
	<h1>Bad page access. Please try again or follow a valid link to this page.</h1>
	<form action="HomeController" method="post">
	<button type="submit" type="button">Home</button></form>
	</body>
<% }
else if(session.getAttribute("role").equals(Constants.OWNER))
{
	System.out.println(session.getAttribute("name"));
		
%>
	<%if( request.getAttribute("error")!=null){ %>
		<h1>data modification error</h1>
	<%}%>

<body>
<h1>Categories Page</h1>
<h2>Hello <%=session.getAttribute("name") %></h2>
</body>

<body>
	<form action="CategoryController" method="get">
	<input type="hidden" value="home" name="action">
	<button type="submit" type="button">Home</button></form>
	
	<form action="ProductController" method="get">
	<input type="hidden" value="select" name="action">
	<button type="submit" type="button">Product Page</button></form>
	
	<form action="LoginController" method="get">
	<input type="hidden" value="signout" name="action">
	<button type="submit" type="button">Sign Out</button></form>

	<%-- Add an HTML table header row to format the results --%>
	<table border="1">
		<tr>
			<th>Category</th>
			<th>Description</th>
		</tr>
		<tr>
			<form action="CategoryController" method="get">
				<input type="hidden" value="insert" name="action">
				<th><input value="" name="Category Name" size="50" maxlength = "50"></th>
				<th><textarea name="Description" size="10" maxlength = "500" cols="50" rows ="5"></textarea></th>
				<th><input type="submit" value="Insert"></th>
			</form>
		</tr>
	</table>
	<br>
	<table border="1">
		<tr>
			<th>Category</th>
			<th>Description</th>
		</tr>

		<%
            ArrayList<Category> rs = (ArrayList<Category>) request.getAttribute("result");
		    if(rs == null) return;
            for(int i = 0; i < rs.size(); i++)
            { 
               Category c = rs.get(i);
            %>
		<tr>
			<form action="CategoryController" method="get">
				<input type="hidden" value="update" name="action">

				<%-- Get the CATEGORY NAME --%>
				<td><input value="<%= c.getName() %>" name="Category Name"
					size="50" readonly></td>
				<%-- Get the DESCRIPTION --%>
				<td><textarea name="Description" size="10" maxlength = "500" cols="50" rows ="5"><%=c.getDescription()%></textarea></td>

				<%-- Button --%>
				<td><input type="submit" value="Update"></td>
			</form>
			<% if(!c.getHasProduct()){ %>
			<form action="CategoryController" method="get">
				<input type="hidden" value="delete" name="action"> <input
					type="hidden" value="<%= c.getName() %>" name="Category Name">
				<%-- Button --%>
				<td><input type="submit" value="Delete"></td>
			</form>
			<%} %>
		</tr>
		<%
                         }
        %>

	</table>
</body>
<%
}
else
{
	%>
		<body>
		<form action="ProductController" method="get">
	<input type="hidden" value="home" name="action">
	<button type="submit" type="button">Home</button></form>
	
	<form action="LoginController" method="get">
	<input type="hidden" value="signout" name="action">
	<button type="submit" type="button">Sign Out</button></form>
	</body>
	
	this page is available to owners
	<%
}%>
</html>