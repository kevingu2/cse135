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
<title>Product Order Page</title>
</head>
<body>
	<form action="ProductController" method="get">
	<input type="hidden" value="home" name="action">
	<button type="submit" type="button">Home</button></form>
	
		<form action="ProductBrowsingController" method="get">
	<input type="hidden" value="select" name="action">
	<button type="submit" type="button">Product Browsing Page</button></form>
	
	<form action="LoginController">
	<input type="hidden" value="signout" name="action">
	<button type="submit" type="button">Sign Out</button></form>
	
	<table border="1">
		<tr>
			<th>SKU</th>
			<th>Name</th>
			<th>Price</th>
			<th>Category</th>
			<th>Quantity</th>
		</tr>
		
		<% Product p = (Product) request.getAttribute("item"); %>
		<tr>
			<form action="ShoppingCartController" method="post">
				<input type="hidden" value="insert" name="action">

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
				<td><input value="1" name="Quantity"
					size="15"></td>
				<td><input type="submit" value="Order"></td>
			</form>
		</tr>
	</table>
</body>
</html>