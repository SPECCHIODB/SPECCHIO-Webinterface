<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Search</title>
	</head>
	<body>
		
		<form action="searchResult">
			<input type="text" name="text"/>		
			<input type="submit" value="Suchen" title="Suchen"/>
		</form>
		
		at=value1 cn=value2 in=value3 ...<br/>
		<br/>
		("at", "Acquisition Time")<br/>
		("cn", "Campaign Name")<br/>
		("in", "Investigator")<br/>
		("fn", "Filename")<br/>
	
	</body>
</html>