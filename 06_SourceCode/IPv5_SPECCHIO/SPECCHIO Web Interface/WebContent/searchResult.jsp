<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html class="full" lang="en">
	<head>
		<%@ include file="head.html" %>
		
		<title>Search Result</title>
		
		<!-- DataTables CSS -->
    	<link href="css/dataTables.bootstrap.min.css" rel="stylesheet">
    	
    	<!-- Custom styles for this template -->
    	<link href="css/searchResult.css" rel="stylesheet">
    	
	</head>
	<body>
		<%@ include file="nav.html" %>
		
		<div class="container">
			<div class="jumbotron">
			
				<h2>Search Result Table</h2>	
				<table id="resultTable" style="width:100%" border="1" class="table table-striped table-hover">
					<thead>
						<tr>
							<th></th>
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
				<input type="button" id="showDetail" value="Show Detail" title="Show Detail" class="btn btn-md btn-success btnMoreWidth" disabled/>
			
				<br/><hr/>
			
				<h2>Search Result Map</h2>
				<div id="map" style="width: 100%; height: 400px;"></div>
				
			</div>
		</div>
		
		
		
		<form id="detailForm"  method="post" action="detail"> 
			<!-- jQuery adds hiddenfield here -->
		</form>
		
		<!-- js imports -->
		<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDxVl6lkT-8txoP3nRxgVNeyfoJbhR-z74"></script>
		<script src="js/jquery-2.1.4.min.js"></script>
        <script src="js/searchResult.js"></script>
        <script src="js/bootstrap.min.js"></script>
        
        <script src="js/jquery.dataTables.min.js"></script>
        <script src="js/dataTables.bootstrap.min.js"></script>
        
        <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    	<script src="js/ie10-viewport-bug-workaround.js"></script>
        
		
        
		<script type="text/javascript">
			var searchResultBeanList = ${srbList};
			//var spectrumIdList = ${idList};
		</script> 

		<%@ include file="footer.html" %>
	</body>
</html>