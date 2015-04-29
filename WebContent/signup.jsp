<%@ page import="ecommerce.util.Constants" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Sign Up</title>

</head>
<body>
	<form action="LoginController" method="post">
	Name:<br>
	<input type="text" name="name">
	<br>
	Role:<br>
	<select name = "role" id ="role">
	<option value = "<%=Constants.OWNER %>">Owner</option>
	<option value = "<%=Constants.CUSTOMER %>">Customer</option></select>
	<br>
	Age:<br>
		<input type="text" name="age">
	<br>
	State:<br>
	<select name = "state" id ="state">
	<option value = "AL">AL</option>
	<option value = "AK">AK</option>
	<option value = "AZ">AZ</option>
	<option value = "AR">AR</option>
	<option value = "CA">CA</option>
	<option value = "CO">CO</option>
	<option value = "CT">CT</option>
	<option value = "DE">DE</option>
	<option value = "FL">FL</option>
	<option value = "GA">GA</option>
	<option value = "HI">HI</option>
	<option value = "ID">ID</option>
	<option value = "IL">IL</option>
	<option value = "IN">IN</option>
	<option value = "IA">IA</option>
	<option value = "KS">KS</option>
	<option value = "KY">KY</option>
	<option value = "LA">LA</option>
	<option value = "ME">ME</option>
	<option value = "MD">MD</option>
	<option value = "MA">MA</option>
	<option value = "MI">MI</option>
	<option value = "MN">MN</option>
	<option value = "MS">MS</option>
	<option value = "MO">MO</option>
	<option value = "MT">MT</option>
	<option value = "NE">NE</option>
	<option value = "NV">NV</option>
	<option value = "NH">NH</option>
	<option value = "NJ">NJ</option>
	<option value = "NM">NM</option>
	<option value = "NY">NY</option>
	<option value = "NC">NC</option>
	<option value = "ND">ND</option>
	<option value = "OH">OH</option>
	<option value = "OK">OK</option>
	<option value = "OR">OR</option>
	<option value = "PA">PA</option>
	<option value = "RI">RI</option>
	<option value = "SC">SC</option>
	<option value = "SD">SD</option>
	<option value = "TN">TN</option>
	<option value = "TX">TX</option>
	<option value = "UT">UT</option>
	<option value = "VT">VT</option>
	<option value = "VA">VA</option>
	<option value = "WA">WA</option>
	<option value = "WV">WV</option>
	<option value = "WI">WI</option>
	<option value = "WY">WY</option>
	</select>
	<br>
	<button type="submit" type="button">Submit</button>
	</form>
	<form action="login.jsp">
		<button type="submit" type="button">Cancel</button></form>
</body>
</html>