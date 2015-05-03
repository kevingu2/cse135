<%@ page import="ecommerce.model.Product"%>
<%@ page import="ecommerce.model.User" %>
<%@ page import="java.util.ArrayList"%>
<%@ page import="ecommerce.model.UserShoppingCart" %>
<%@ page import="ecommerce.util.Constants" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Product Order Page</title>
</head>

<%if(request.getSession().getAttribute("role") == null){ %>
	<h1>No user logged in</h1>
	<form action="LoginController" method="get">
	<input type="hidden" value="signout" name="action">
	<button type="submit" type="button">Login</button></form>
	</body>
<%}else if(request.getAttribute("toProductOrder")==null){ %>
	<h1>Bad page access. Please try again or follow a valid link to this page.</h1>
	<form action="HomeController" method="post">
	<button type="submit" type="button">Home</button></form>
	</body>
<%}else{%>
<h1>Hello <%=session.getAttribute("name") %></h1>
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
	
	<br>
	
	<table border="1">
		<tr><th colspan="5">Your Cart</th></tr>
		<tr>
			<th>Product Name</th>
			<th>Product SKU</th>
			<th>Quantity</th>
			<th>Price</th>
			<th>Sub-Total</th>
		</tr>

		<%
            ArrayList<UserShoppingCart> crs = (ArrayList<UserShoppingCart>) request.getAttribute("cresult");
		    ArrayList<Product> prs = (ArrayList<Product>) request.getAttribute("presult");
			if(crs == null)
			{
				System.out.println("No crs");
				return;
			}
			if(prs == null)
			{
				System.out.println("No prs");
				return;
			}
			
			double total = 0.0;
            for(int i = 0; i < crs.size(); i++)
            { 
               UserShoppingCart u = crs.get(i);
               Product p = prs.get(i);
            %>
		<tr>
			<form action="ProductBrowsingController" method="get">
				<input type="hidden" value="choose" name="action">
				<%-- Get the CATEGORY NAME --%>
				<td><input value="<%= p.getName() %>" name="name"
					size="50" readonly></td>
				<td><input value="<%= p.getSku() %>" name="sku"
					size="50" readonly></td>
				<td><input value="<%= u.getQuantity() %>" name="quantity"
					size="50" readonly></td>
				<td><input value="<%= p.getPrice() %>" name="price"
					size="50" readonly></td>
				<td><input value="<%= p.getPrice() * u.getQuantity() %>" name="Sub-Total"
					size="50" readonly></td>
				<%-- Get the DESCRIPTION --%>
				
				<%-- Add product sub-total to total --%>
				<% total += p.getPrice() * u.getQuantity(); %>
			</form>
		</tr>
		<%
            }
            request.getSession().setAttribute("clist", crs);
            request.getSession().setAttribute("plist", prs);
        %>
        <%if(crs.size() > 0){ %>
        <tr>
        	<td></td>
        	<td></td>
        	<td></td>
        	<td></td>
        	<td></td>
        </tr>
        <tr>
        	<td></td>
        	<td></td>
        	<td></td>
        	<th>Total</th>
        	<td><input value="<%= total %>" name="total"
				size="50" readonly></td>
        </tr>
        <%} %>

	</table>
	
	<br>
	
	<table border="1">
		<tr>
			<th>SKU</th>
			<th>Name</th>
			<th>Category</th>
			<th>Price</th>
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
<%} %>
</html>