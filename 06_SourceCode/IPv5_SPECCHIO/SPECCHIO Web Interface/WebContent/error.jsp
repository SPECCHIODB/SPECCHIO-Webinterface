<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link rel="icon" href="../../favicon.ico">
		
		<title>Error</title>
		
		<!-- Bootstrap core CSS -->
    	<link href="css/bootstrap.min.css" rel="stylesheet">
    
		<!-- Custom styles for this template -->
    	<link href="css/errorpage.css" rel="stylesheet">
    
    	<!-- Custom fonts for this template -->
    	<link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,700" rel="stylesheet">
    
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
        
	</body>
</html>