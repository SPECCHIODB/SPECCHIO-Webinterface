<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Search</title>
		
		<link rel="stylesheet" type="text/css" href="css/search.css" />
	</head>
	<body>

		<!-- js imports -->
		<script src="js/jquery-2.1.4.min.js"></script>
        <script src="js/search.js"></script>
        
		<script type="text/javascript">
			var searchRowBeanList = ${searchRowBeanList};
 			var categoryList = ${categoryList};
		</script> 
		
		<form id="searchForm" action="search">
			<!-- jQuery adds searchRows dynamically here. -->
		</form>
		
		<input type="button" id="addButton" value="Add" title="Add"/>
		
		<input type="button" id="searchButton" value="Search" title="Search"/>
		
		<form id="searchResultForm" action="searchResult">
			<input type="hidden" id="hCategory" name="category" />
			<input type="hidden" id="hAttribute" name="attribute" />
			<input type="hidden" id="hInput" name="input" />	
		</form>
	
	</body>
</html>