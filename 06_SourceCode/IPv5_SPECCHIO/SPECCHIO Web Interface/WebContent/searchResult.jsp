<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Search Result List</title>
	</head>
	<body>
		<table style="width:100%" border="1">
			<tr>
				<th>acquisitionTime</th>
				<th>campaignName</th>
			  	<th>owner</th>
			  	<th>name</th>
			  	<th>filename</th>
			  	<th>institute</th>
			</tr>
			
			<c:forEach items="${metaDOs}" var="metaDO">  
			<tr>
				<td>${metaDO.acquisitionTime}</td>
				<td>${metaDO.campaignName}</td>
				<td></td>
				<td></td>
				<td>${metaDO.filename}</td>
				<td></td>
			</tr>
			</c:forEach> 
		</table> 
		

	
	</body>
</html>