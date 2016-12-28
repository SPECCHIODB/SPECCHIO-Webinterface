<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html class="full">
	<head lang="en"
	
		<%@ include file="head.html" %>>
		
		<title>About</title>
		
		<meta name="description" content="Information about the project and the makers of the web interface.">
		<meta name="keywords" content="University of Zurich, UZH, University of Applied Sciences and Arts Northwestern Switzerland, FHNW">
		
    	<!-- Custom styles for this template -->
    	<link href="css/about.css" rel="stylesheet">
    
	</head>
	<body>

		<%@ include file="nav.html" %>
	
		<div class="container">
		
			<div class="jumbotron">
			
				<h1>About</h1>
				<h3>Project and institutions</h3>	

				<div class="gap-l"></div>

				<h2>Project</h2>
				<div class="gap-s"></div>
				
				<p>The Department of Geography of the University of Zurich maintains a database for spectral data and their metadata. Access to the data of this application was to date only available with a specific software. The SPECCHIO Web Interface has been developed in cooperation with the University of Applied Sciences and Arts Northwestern Switzerland to ensure that the database can be searched directly for spectral data by web browser without installing additional software.</p>
				<p><a href="http://www.specchio.ch" target="_blank">More</a></p>
				
				<div class="gap-l"></div>
				
				<h2>Institutions</h2>
				<div class="gap-s"></div>

				<div class="row">
				
				<!-- start of left -->
				<div class="col-md-6 gap-m">
				
					<!-- logotype left -->
					<div class="row">
					<div class="col-md-12">
						<img class="img-responsive img-padding" src="img/uzh.png" alt="University of Zurich"/>
					</div>
					</div>
				
					<!-- text left -->
					<div class="row">
					<div class="col-md-12">
						<h4>University of Zurich</h4>
						<p>Department of Geography<p>
						<p><a href="http://www.geo.uzh.ch/en/units/rsl" target="_blank">More</a></p>
					</div>
					</div>

				</div>
				<!-- end of left -->
				
				
				
				<!-- start of right -->
				<div class="col-md-6">
				
				<!-- logotype right -->
				<div class="row">
				<div class="col-md-12">
					<img class="img-responsive img-padding" src="img/fhnw.png" alt="University of Applied Sciences and Arts Northwestern Switzerland"/>
				</div>
				</div>

				<!-- text right -->
				<div class="row">
				<div class="col-md-12">
					<h4>University of Applied Sciences and Arts Northwestern Switzerland</h4>
					<p>School of Engineering<p>
					<p><a href="http://www.fhnw.ch/engineering/">More</a></p>
				</div>
				</div>	
							
				</div>
				<!-- end of right -->
				
				</div>
				
			</div>
		
		</div>
		
		<%@ include file="footer.html" %>
		
		<!-- js imports -->
		<script src="js/jquery-2.1.4.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        
        <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    	<script src="js/ie10-viewport-bug-workaround.js"></script>
		
	</body>
</html>