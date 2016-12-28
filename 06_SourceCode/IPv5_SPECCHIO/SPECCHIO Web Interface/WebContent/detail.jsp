<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html class="full">
	<head lang="en">
	
		<%@ include file="head.html" %>
		
		<title>Detail</title>
    
    	<!-- Custom styles for this template -->
    	<link href="css/detail.css" rel="stylesheet">
    	<link href="css/print.css" rel="stylesheet" media="print">
    	
    	<!-- c3.css for line chart -->
		<link href="css/c3.css" rel="stylesheet" type="text/css">
		
	</head>
	<body>
		<%@ include file="nav.html" %>
		
		<div id="detail" class="container">
				<form id="exportForm" method="post" action="detail">
					<input id="export" value="Download ZIP" type="button" class="btn btn-primary" style="float:right;"/>
					<input id="pdf" value="Print PDF" type="button" class="btn btn-primary" style="float:right; margin-right: 5px;"/>
					<!-- jQuery adds hiddenfield here -->
				</form>
				<div id="space">
					<!-- jQuery adds spectral curve and meta data here -->
				</div>
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
		<script src="http://code.highcharts.com/highcharts.js"></script>
        
        <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    	<script src="js/ie10-viewport-bug-workaround.js"></script>
        
		<script type="text/javascript">
			var spaceDetailBeanList = ${spaceDetailBeanList};
		</script> 
	</body>
</html>