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
				<th>Acquisition Time</th>
				<th>Campaign Name</th>
			  	<th>Investigator</th>
			  	<th>Name</th>
			  	<th>File Name</th>
			  	<th>Institute</th>
			</tr>
			
			<c:forEach items="${mdbList}" var="mdb">  
			<tr>
				<td>${mdb.acquisitionTime}</td>
				<td>${mdb.campaignName}</td>
				<td>${mdb.investigator}</td>
				<td>${mdb.name}</td>
				<td>${mdb.fileName}</td>
				<td>${mdb.institute}</td>
			</tr>
			</c:forEach> 
		</table> 
		

	
	</body>
</html>