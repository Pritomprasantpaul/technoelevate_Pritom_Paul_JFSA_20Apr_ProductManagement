<%@page import="com.te.empwebapp.beans.EmployeeInfoBean"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	String name = (String) request.getAttribute("name");
List<EmployeeInfoBean> infoBeans = (List) request.getAttribute("infos");
%>
<%@ include file="header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Product Home</title>
</head>
<body>
	<fieldset>
		<h1 style="color: maroon; text-decoration: underline;">
			Welcome
			<%=name%></h1>
	</fieldset>

	<%
		if (infoBeans != null) {
	%>
	<fieldset>
		<legend>All Records</legend>
		<table  border="1" style="width:100%">
			<tr>
				<th>ID</th>
				<th>Name</th>
			</tr>
			<%
				for (EmployeeInfoBean infoBean : infoBeans) {
			%>
			<tr>
				<td><%=infoBean.getId()%></td>
				<td><%=infoBean.getName()%></td>
			</tr>
			<%
				}
			%>
		</table>

	</fieldset>
	<%
		}
	%>
</body>
</html>