<%@ page import="ecommerce.model.Category"%>
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
<title>Product Browsing Page</title>
</head>
<body>Products Browsing Page
</body>

<%if(session.getAttribute("role")==null){ %>
	<h1>No user logged in</h1>
	<form action="LoginController" method="get">
	<input type="hidden" value="signout" name="action">
	<button type="submit" type="button">Login</button></form>
	</body>
<%}else{ %>
<body>
	<form action="ProductController" method="get">
	<input type="hidden" value="home" name="action">
	<button type="submit" type="button">Home</button></form>
	
	<form action="ShoppingCartController" method="get">
	<input type="hidden" value="cart" name="action">
	<button type="submit" type="button">View Cart</button></form>
	
	<form action="LoginController">
	<input type="hidden" value="signout" name="action">
	<button type="submit" type="button">Sign Out</button></form>

	<br>
	
<%	if(request.getAttribute("insert success") != null)
	{
		%><h2>Product successfully inserted into the cart</h2>
		<br>
	<%}else if(request.getAttribute("insert failure") != null){
		%><h2>Error while inserting product into cart.  Please try again.</h2>
		<br>
	<% } %>
	<table border="1">
		<tr>
			<th>Category</th>
			<th>Name</th>
			<th>Search</th>
		</tr>
		<tr>
			<form action="ProductBrowsingController" method="get">
				<input type="hidden" value="search" name="action">
				<th>
					<select name="Category Name" id="Category Name">
					<%
						ArrayList<Category> catList = (ArrayList<Category>) request.getAttribute("cresult");
					%>
						<option value="ALL" >ALL</option>
					<%
						if(catList==null) return;
						for(Category c: catList){%>
							<option value="<%=c.getName() %>"><%=c.getName() %></option>
						<% } %>
					</select>
				</th>
				<th><input value="" name="Name" size="50"></th>
				<th><input type="submit" value="Search"></th>
			</form>
		</tr>
	</table>
	
	<br>
	
	<table border="1">
		<tr>
			<th>Search</th>
			<th>Category</th>
			<th>Description</th>
		</tr>
		<tr>
			<form action="ProductBrowsingController" method="get">
				<input type="hidden" value="choose" name="action">
				<td><button type="submit" type="button">Search</button></td>
				<td><input value="ALL" name="Category Name" size="50" readonly></td>
				<td><textarea name="Description" size="10" maxlength="500"
						cols="50" rows="5" readonly>View all products in the database</textarea></td>
			</form>
		</tr>
		<%
            ArrayList<Category> crs = (ArrayList<Category>) request.getAttribute("cresult");
		    if(crs == null) return;
            for(int i = 0; i < crs.size(); i++)
            { 
               Category c = crs.get(i);
            %>
		<tr>
			<form action="ProductBrowsingController" method="get">
				<input type="hidden" value="choose" name="action">
				<td><button type="submit" type="button">Search</button></td>
				<%-- Get the CATEGORY NAME --%>
				<td><input value="<%= c.getName() %>" name="Category Name"
					size="50" readonly></td>
				<%-- Get the DESCRIPTION --%>
				<td><textarea name="Description"
					size="10" maxlength = "500" cols = "50" rows = "5" readonly><%= c.getDescription() %></textarea></td>
			</form>
		</tr>
		<%
             }
        %>

	</table>
	
	<br>

	<%-- Add an HTML table header row to format the results --%>
	<table border="1">
		<%	if(request.getAttribute("result") != null)
			{ 
		%>
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
			<form action="ProductOrderController" method="get">
				<input type="hidden" value="select" name="action">

				<%-- Get the PRODUCT SKU --%>
				<td><input value="<%= p.getSku() %>" name="SKU"
					size="50" readonly></td>
					<%-- Get the NAME --%>
				<td><input value="<%= p.getName() %>" name="Name"
					size="50" readonly></td>
				<%-- Get the CATEGORY NAME --%>
				<td><input value="<%= p.getCategory_name() %>" name="Category Name"
					size="50" readonly></td>
					<%-- Get the PRICE --%>
				<td><input value="<%= p.getPrice() %>" name="Price"
					size="15" readonly></td>
				<td><input type="submit" value="Order"></td>
			</form>
		</tr>
		<%             
            }}
        %>
	</table>
</body>
<% } %>

</html>