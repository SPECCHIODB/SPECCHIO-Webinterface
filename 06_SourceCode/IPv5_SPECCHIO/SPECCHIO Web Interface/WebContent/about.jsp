<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html class="full">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link rel="icon" href="../../favicon.ico">
		
		<title>About</title>
		
		<!-- Bootstrap core CSS -->
    	<link href="css/bootstrap.min.css" rel="stylesheet">
    
    	<!-- Custom styles for this template -->
    	<link href="css/about.css" rel="stylesheet">
    	
    	<!-- Custom fonts for this template -->
    	<link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,700" rel="stylesheet">
    
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
					<p><a href="http://www.fhnw.ch/technik/imvs/ausbildung/studierendenprojekte">More</a></p>
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
        <script src="js/search.js"></script>
        <script src="js/bootstrap.min.js"></script>
		

		
		
	</body>
</html>