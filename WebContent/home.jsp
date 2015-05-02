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
		<form action="LoginController" method="get">
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
				<form action="ProductBrowsingController" method="get">
				<button type="submit" type="button">Product Browsing</button>
				<input type="hidden" name="action" value="select">
				</form>
				
				<!-- Added these buttons under the assumption that
						owners had access to all customer pages -->
				<form action="ShoppingCartController" method="get">
				<input type="hidden" value="cart" name="action">
				<button type="submit" type="button">View Cart</button></form>
		
				<form action="ProductBrowsingController" method="get">
				<button type="submit" type="button">Product Browsing</button>
				<input type="hidden" name="action" value="select">
				</form>
		<%}%>
		<%if(session.getAttribute("role").equals(Constants.CUSTOMER)){ %>
		<%	if(request.getAttribute("ShoppingCartError")!=null){%>
				<h1>Error retrieving shopping cart. Please try again</h1>
		<%	} %>
			<form action="ShoppingCartController" method="get">
			<input type="hidden" value="cart" name="action">
			<button type="submit" type="button">View Cart</button></form>
		
			<form action="ProductBrowsingController" method="get">
				<button type="submit" type="button">Product Browsing</button>
				<input type="hidden" name="action" value="select">
			</form>
		<% } %>
	<%}else{ %>
		<h1>No user logged in</h1>
		<form action="LoginController" method="get">
		<input type="hidden" value="signout" name="action">
		<button type="submit" type="button">Login</button></form>
	<%} %>
</body>
</html>

