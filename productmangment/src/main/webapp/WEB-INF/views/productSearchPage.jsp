<%@page import="com.te.empwebapp.beans.ProductInfoBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	String errMsg = (String) request.getAttribute("errMsg");
ProductInfoBean productInfoBean = (ProductInfoBean) request.getAttribute("productData");
%>

<%@ include file="header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Search Product</title>
</head>
<body>

	<fieldset>
		<legend>Search Product</legend>
		<form action="./search">
			<table>
				<tr>
					<td>Product ID</td>
					<td>:</td>
					<td><input type="number" name="pid"></td>
				</tr>
				<tr>
					<td><input type="submit" value="submit"></td>
				</tr>
			</table>
		</form>
	</fieldset>

	<%
		if (productInfoBean != null) {
	%>
	<h3>
		PName :
		<%=productInfoBean.getPname()%> </h3>
	<h3>
		PID :
		<%=productInfoBean.getPid()%></h3>
	
	<%
		}
	%>
	
	<%
		if (errMsg != null && !errMsg.isEmpty()) {
	%>
	<h2>
		<%=errMsg%></h2>
	<%
		}
	%>

</body>
</html>