<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
	<head>
	
		<%@ include file="head.html" %>
		
		<title>Error</title>
		
		<!-- Custom styles for this template -->
    	<link href="css/errorpage.css" rel="stylesheet">
    
	</head>
	<body>
	
		<%@ include file="nav.html" %>
	
		<div class="container">
			
			<div class="gap-l"></div>
								
			<h1 class="text-center">${ errorTitle }</h1>
			<div class="gap-m"></div>
			<p class="text-center lead">${ errorMessage }</p>
			<p class="text-center lead">Back to <a href="search">SPECCHIO Web Interface</a></p>
		</div>
		
		<%@ include file="footer.html" %>
		
		<!-- js imports -->
        <script src="js/bootstrap.min.js"></script>	
        
        <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    	<script src="js/ie10-viewport-bug-workaround.js"></script>
        
	</body>
</html>