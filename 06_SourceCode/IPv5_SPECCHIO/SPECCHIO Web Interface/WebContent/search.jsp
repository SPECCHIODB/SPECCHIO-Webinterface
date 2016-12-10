<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html class="full">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link rel="icon" href="../../favicon.ico">
		
		<title>Search</title>
		
		<!-- Bootstrap core CSS -->
    	<link href="css/bootstrap.min.css" rel="stylesheet">
    
    	<!-- Custom styles for this template -->
    	<link href="css/search.css" rel="stylesheet">
    
	</head>
	<body>
	
		<%@ include file="nav.html" %>
	
		<div class="container">
		
			<div class="jumbotron">
			
				<h1>SPECCHIO</h1>
				<p>Spectral Library</p>	
				
				<br/>			
			
				<form id="searchForm" method="post" action="search">
					<!-- jQuery adds searchRows dynamically here. -->
				</form>
				
				<input type="button" id="addButton" value="Add Filter" title="Add Filter" class="btn btn-lg btn-primary btnMoreWidth"/>
				<input type="button" id="searchButton" value="Search" title="Search" class="btn btn-lg btn-success btnMoreWidth"/>
				<input type="button" id="showButton" value="Show Result" title="Show Result"  disabled class="btn btn-lg btn-success btnMoreWidth"/>
				
			</div>
			
		</div>
		
		<!-- js imports -->
		<script src="js/jquery-2.1.4.min.js"></script>
        <script src="js/search.js"></script>
        <script src="js/bootstrap.min.js"></script>
        
		<script type="text/javascript">
			var searchResultCount = ${searchResultCount};
			var searchRowBeanList = ${searchRowBeanList};
 			var categoryList = ${categoryList};
		</script> 
		
		<%@ include file="footer.html" %>
	</body>
</html>