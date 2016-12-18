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
    	
    	<!-- Custom fonts for this template -->
    	<link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,700" rel="stylesheet">
    
	</head>
	<body>
	
		<%@ include file="nav.html" %>
	
		<div class="container">
		
			<div class="jumbotron">
			
				<h1>SPECCHIO</h1>
				<h3>Spectral Library</h3>	
				
				<br/>			
			
				<form id="searchForm" method="post" action="search">
					<input type="hidden" id="numberOfRows" name="numberOfRows" value="0"/>
					<!-- jQuery adds searchRows dynamically here. -->
				</form>
				
				<input type="button" id="addButton" value="Add Filter" title="Add Filter" class="btn btn-md btn-primary btnMoreWidth"/>
				<input type="button" id="searchButton" value="Search" title="Search" class="btn btn-md btn-success btnMoreWidth"/>
				<input type="button" id="showButton" value="Show Spectra" title="Show Spectra"  disabled class="btn btn-md btn-success btnMoreWidth"/>
				
			</div>
			
		</div>
		
		<!-- js imports -->
		<script src="js/jquery-2.1.4.min.js"></script>
        <script src="js/search.js"></script>
        <script src="js/bootstrap.min.js"></script>
        
		<script type="text/javascript">
			// default values
			var searchResultCount = 0;
			var searchRowBeanList = [];
			var categoryList = [];
			var valid = true;
		
			// get values from request attributes if available
			<c:if test="${not empty searchRowBeanList}" > searchRowBeanList = ${searchRowBeanList}; </c:if>
			<c:if test="${not empty searchResultCount}" > searchResultCount = ${searchResultCount}; </c:if>
			<c:if test="${not empty categoryList}" > categoryList = ${categoryList}; </c:if>
			<c:if test="${not empty valid}" > valid = ${valid}; </c:if>
		</script> 
		
		<%@ include file="footer.html" %>
		
	</body>
</html>