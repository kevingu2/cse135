<%@ page import="ecommerce.model.Product"%>
<%@ page import="ecommerce.model.User" %>
<%@ page import="java.util.ArrayList"%>
<%@ page import="ecommerce.util.Constants" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Products Page</title>
</head>

<% 
if(session.getAttribute("role").equals(Constants.OWNER))
{
%>
<body>Products Page
</body>
<body>
	<form action="ProductController" method="get">
	<input type="hidden" value="home" name="action">
	<button type="submit" type="button">Home</button></form>
	
		<form action="CategoryController" method="get">
	<input type="hidden" value="select" name="action">
	<button type="submit" type="button">Categories Page</button></form>
	
	<form action="LoginController">
	<input type="hidden" value="signout" name="action">
	<button type="submit" type="button">Sign Out</button></form>

	<%-- Add an HTML table header row to format the results --%>
	<table border="1">
		<tr>
			<th>SKU</th>
			<th>Name</th>
			<th>Category</th>
			<th>Price</th>
		</tr>
		<tr>
			<form action="ProductController" method="get">
				<input type="hidden" value="insert" name="action">
				<th><input value="" name="SKU" size="50"></th>
				<th><input value="" name="Name" size="50"></th>
				<th><input value="" name="Category Name" size="50"></th>
				<th><input value="" name="Price" size="15"></th>
				<th><input type="submit" value="Insert"></th>
			</form>
		</tr>
	</table>
	<br>
	<table border="1">
		<tr>
			<th>SKU</th>
			<th>Name</th>
			<th>Category</th>
			<th>Price</th>
		</tr>

		<%
            ArrayList<Product> rs = (ArrayList<Product>) request.getAttribute("result");
		    if(rs == null) return;
            for(int i = 0; i < rs.size(); i++)
            { 
               Product p = rs.get(i);
            %>
		<tr>
			<form action="ProductController" method="get">
				<input type="hidden" value="update" name="action">

				<%-- Get the PRODUCT SKU --%>
				<td><input value="<%= p.getSku() %>" name="SKU"
					size="50" readonly></td>
					<%-- Get the NAME --%>
				<td><input value="<%= p.getName() %>" name="Name"
					size="50"></td>
				<%-- Get the CATEGORY NAME --%>
				<td><input value="<%= p.getCategory_name() %>" name="Category Name"
					size="50"></td>
					<%-- Get the PRICE --%>
				<td><input value="<%= p.getPrice() %>" name="Price"
					size="15"></td>

				<%-- Button --%>
				<td><input type="submit" value="Update"></td>
			</form>
			<form action="ProductController" method="get">
				<input type="hidden" value="delete" name="action"> <input
					type="hidden" value="<%= p.getSku() %>" name="SKU">
				<%-- Button --%>
				<td><input type="submit" value="Delete"></td>
			</form>
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
	
	<form action="LoginController">
	<input type="hidden" value="signout" name="action">
	<button type="submit" type="button">Sign Out</button></form>
	</body>
	
	this page is available to owners
	<%
}%>
</html>