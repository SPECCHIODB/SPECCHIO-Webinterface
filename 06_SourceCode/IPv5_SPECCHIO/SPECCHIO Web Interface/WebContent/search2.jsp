<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Search</title>
		
		<link rel="stylesheet" type="text/css" href="css/search.css" />
	</head>
	<body>

		<!-- js imports -->
		<script src="js/jquery-2.1.4.min.js"></script>
        <script src="js/search.js"></script>
        
		<script type="text/javascript">
			
			var categoryList = ${categoryList};
			var attributeList = ${attributeList};
			var selectedCategory = ${selectedCategory};
			var selectedAttribute = ${selectedAttribute};
			var inputDropdownValues = ${inputDropdownValues};

		</script> 
		<!--  
		<form id="searchForm" action="search">
		
			<section id="inputSection0">
			
				<article>
					<p><b>Category</b></p>
					<select id="category" name="category">
						<c:forEach items="${categoryList}" var="category">  
							<option value="${category.name}" ${category.name == selectedCategory.name ? 'selected="selected"' : ''}>
								${category.name}
							</option>
						</c:forEach> 
					</select>
				</article>
				
				<article>
					<p><b>Attribute</b></p>
					<select id="attribute" name="attribute">
						<c:forEach items="${attributeList}" var="attribute">  
							<option value="${attribute.name}" ${attribute.name == selectedAttribute.name ? 'selected="selected"' : ''}>
								${attribute.name}
							</option>
						</c:forEach> 
					</select>
				</article>
				
				<article>
					<p><b>Input</b></p>
					<c:choose>
					    <c:when test="${selectedAttribute.defaultStorageField == 'drop_down' || selectedAttribute.defaultStorageField == 'taxonomy_id'}">
					    	<select name="inputValue"> 
						    	<c:forEach items="${inputDropdownValues}" var="pair">
									<option value="${pair.first}">
										${pair.second}
									</option>
								</c:forEach> 
							</select>
					    </c:when> 
					    <c:when test="${selectedAttribute.defaultStorageField == 'string_val'}">
					    	<input type="text" name="inputText"/>
					    </c:when> 
					    <c:when test="${selectedAttribute.defaultStorageField == 'int_val' || selectedAttribute.defaultStorageField == 'double_val' }">
					    	<input type="text" name="inputFrom"/> - <input type="text" name="inputTo"/>
					    </c:when>   
					    <c:when test="${selectedAttribute.defaultStorageField == 'datetime_val' }">
					    	<input type="date" name="inputDate"/>
					    </c:when>   
					    <c:otherwise>
					        ${selectedAttribute.defaultStorageField}
					    </c:otherwise>
				</c:choose>
				</article>
				
			</section>
			
		</form>
		-->
		<input type="button" id="addButton" value="Add" title="Add"/>
		
		<input type="button" id="searchButton" value="Search" title="Search"/>
		
		<form id="searchResultForm" action="searchResult">
			<input type="hidden" id="hCategory" name="category" />
			<input type="hidden" id="hAttribute" name="attribute" />
			<input type="hidden" id="hInput" name="input" />	
		</form>
	
	</body>
</html>