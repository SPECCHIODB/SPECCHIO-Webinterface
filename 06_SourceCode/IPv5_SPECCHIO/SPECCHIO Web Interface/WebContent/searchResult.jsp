<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html class="full">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		
		<title>Search Result</title>
		
		<!-- Bootstrap core CSS -->
    	<link href="css/bootstrap.min.css" rel="stylesheet">
    	<link href="css/dataTables.bootstrap.min.css" rel="stylesheet">
    	
    	<!-- Custom styles for this template -->
    	<link href="css/searchResult.css" rel="stylesheet">
    
	</head>
	<body>
		<%@ include file="nav.html" %>
	
		<div class="container">
			<div class="jumbotron">
		
				<table id="resultTable" style="width:100%" border="1" class="table table-striped table-hover">
					<thead>
						<tr>
							<th><!-- <input type="checkbox" id="selectAll" /> --></th>
							<th>Acquisition Time</th>
							<th>Campaign Name</th>
						  	<th>Investigator</th>
						  	<th>Name</th>
						  	<th>File Name</th>
						  	<th>Institute</th>
						</tr>
					</thead>
					<tbody>
						<!-- jQuery adds tableRows dynamically here. -->
					</tbody>
				</table> 
				
				<input type="button" id="showDetail" value="Show Detail" title="Show Detail" class="btn btn-lg btn-success btnMoreWidth"/>
			
			</div>
		</div>
		

		<form id="detailForm"  method="post" action="detail"> 
			<!-- jQuery adds hiddenfield here -->
		</form>
		
		<!-- js imports -->
		<script src="js/jquery-2.1.4.min.js"></script>
        <script src="js/searchResult.js"></script>
        <script src="js/bootstrap.min.js"></script>
        
        <script src="js/jquery.dataTables.min.js"></script>
        <script src="js/dataTables.bootstrap.min.js"></script>
         
        
        
		<script type="text/javascript">
			var searchResultBeanList = ${srbList};
			//var spectrumIdList = ${idList};
		</script> 

		<%@ include file="footer.html" %>
	</body>
</html>