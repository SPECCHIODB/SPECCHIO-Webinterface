function init(){
	
}



$(document).ready(function() {
	
	
	init();

	$("#category").change(function(){
		$("#searchForm").submit();
	});
	
	$("#attribute").change(function(){
		$("#searchForm").submit();
	});
	
	$("#searchButton").click(function(){
		$("#searchResultForm").submit();
	});
	
	$("#addButton").click(function(){
		
		$("#inputSection0").clone().attr("id","inputSection"+$("#searchForm section").length).insertAfter("#inputSection0");   
		
	});
	
});
