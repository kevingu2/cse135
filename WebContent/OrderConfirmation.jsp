<%@ page import="ecommerce.model.Category"%>
<%@ page import="ecommerce.model.Product"%>
<%@ page import="ecommerce.model.User" %>
<%@ page import="ecommerce.model.UserShoppingCart" %>
<%@ page import="java.util.ArrayList"%>
<%@ page import="ecommerce.util.Constants" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Order Confirmation</title>
</head>
<body>Order Confirmation Page
</body>

<br>

<body>
<%if(request.getAttribute("status")==null || !request.getAttribute("status").equals("complete")){%>
	<h1>Invalid access of Order Confirmation Page</h1>
	<form action="HomeController" method = "post">
	<button type="submit" type="button">Home</button></form>
<%}else{ %>
	<h1>Hello <%=session.getAttribute("name") %></h1>
	<form action="ProductController" method="post">
	<input type="hidden" value="home" name="action">
	<button type="submit" type="button">Home</button></form>
	
	<form action="ProductBrowsingController" method="get">
		<button type="submit" type="button">Product Browsing</button>
		<input type="hidden" name="action" value="select">
	</form>
	
	<form action="LoginController">
	<input type="hidden" value="signout" name="action">
	<button type="submit" type="button">Sign Out</button></form>

	<br>

	<table border="1">
		<tr><th colspan="5">Items Purchased</th></tr>
		<tr>
			<th>Product Name</th>
			<th>Product SKU</th>
			<th>Quantity</th>
			<th>Price</th>
			<th>Sub-Total</th>
		</tr>

		<%
            ArrayList<UserShoppingCart> crs = (ArrayList<UserShoppingCart>) request.getAttribute("clist");
		    ArrayList<Product> prs = (ArrayList<Product>) request.getAttribute("plist");
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

	</table>
<%} %>
</body>

</html>