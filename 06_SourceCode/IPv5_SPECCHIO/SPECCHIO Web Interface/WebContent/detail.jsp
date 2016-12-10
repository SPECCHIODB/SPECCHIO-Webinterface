<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link rel="icon" href="../../favicon.ico">
		
		<title>Detail</title>
		
		<!-- Bootstrap core CSS -->
    	<link href="css/bootstrap.min.css" rel="stylesheet">
    
    	<!-- Custom styles for this template -->
    	<link href="css/detail.css" rel="stylesheet">
    	
    	<!-- c3.css for line chart -->
		<link href="css/c3.css" rel="stylesheet" type="text/css">
    
	</head>
	<body>
		<%@ include file="nav.html" %>
	
		<div id="container" class="container">
		
		
		</div>
		
		<form id="detailForm"  method="post" action="detail"> 
			<!-- jQuery adds hiddenfield here -->
		</form>
	
		
		<%@ include file="footer.html" %>
	
		<!-- js imports -->
		<script src="js/jquery-2.1.4.min.js"></script>
        <script src="js/detail.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="http://d3js.org/d3.v3.min.js" charset="utf-8"></script>
		<script src="js/c3.js"></script>
        
		<script type="text/javascript">
			var spaceDetailBeanList = ${spaceDetailBeanList};
		</script> 
	</body>
</html>