var numberOfRows;
var reqType;
var firstRow;

function init(){
	
	numberOfRows = 0;
	reqType = "search";
	firstRow = true;
	
	if(!valid){
		var errors = ""
		for(var i = 0; i < searchRowBeanList.length; i++){
			var srb = searchRowBeanList[i];
			if(!srb.validInput1 || !srb.validInput2) 
				errors += "- " + srb.errorMessage + "<br/>";
		}
		showMessageBox("warning", "glyphicon-warning-sign", "Please correct the following input errors", errors);
	}
	else if(searchResultCount > 0){
		$("#showButton").prop('disabled', false);
		
		if(searchResultCount == 1) 
			showMessageBox("success", "glyphicon-ok-sign", "One spectrum has been found.", "Add or Modify filters to search for spectra. Press 'Show Spectra' to have a look at all found spectra.");
		else 
			showMessageBox("success", "glyphicon-ok-sign", searchResultCount + " spectra have been found.", "Add or Modify filters to search for spectra. Press 'Show Spectra' to have a look at all found spectra.");
	}
	else {
		showMessageBox("info", "glyphicon-info-sign", "No spectrum has been found.", "Add or Modify filters to search for spectra.")
	}
	
	addTitleRow();
	
	if(searchRowBeanList.length == 0) {
		addSearchRow(null);
	}
	else {
		for(var i = 0; i < searchRowBeanList.length; i++){
			addSearchRow(searchRowBeanList[i]);
		}
	}
	
}

function showMessageBox(type, glyphicon, title, msg){
	var form = $("#searchForm");
	var msgBox = $('<div class="alert alert-'+type+'"></div>');
	
	msgBox.append('<span class="glyphicon '+glyphicon+'"></span> <strong>'+title+'</strong>');
	if(msg != null){
		msgBox.append('<br/>');
		msgBox.append(msg);
	}
	form.append(msgBox);
}

function addTitleRow(){
	var form = $("#searchForm");
	form.append(createTitleRow());
}

function addSearchRow(srb){
	var form = $("#searchForm");
	form.append(createSearchRow(srb));
	numberOfRows++;
	$("#numberOfRows").val(numberOfRows);
}

function createTitleRow(){
	var titleRow = $('<div class="row row-gap"></div>');
	titleRow.append('<div class="col-xs-3"><label>Category</label></div>');
	titleRow.append('<div class="col-xs-3"><label>Attribute</label></div>');
	titleRow.append('<div class="col-xs-5"><label>Input</label></div>');
	
	return titleRow;
}

function createSearchRow(srb){

    var searchRow = $('<div class="row row-gap"></div>');
    searchRow.append(createCategorySection(srb));
    if(srb != null && srb.selectedCategory.name != "Full Text Search") 
    	searchRow.append(createAttributeSection(srb, ""));
    else searchRow.append(createAttributeSection(srb, "disabled"));
    searchRow.append(createInputSection(srb));
    
    
    if(firstRow) firstRow = false;
    else searchRow.append(createRemoveButton(searchRow));
    
    return searchRow;
}

function createRemoveButton(searchRow){
    var removeButton = $('<button type="button" title="Remove" class="btn btn-danger"><span class="glyphicon glyphicon-remove"></span></button>').click(function(){
    	searchRow.remove();
    	reqType = "search";
    	submitSearchForm();
    });
    return removeButton;
}

function createCategorySection(srb){
	var section = $('<section class="col-xs-3"></section>');
    
	var rowNr = numberOfRows;
	var select =  $('<select name="category_'+rowNr+'" class="btn btn-default dropdown-toggle"></select>');
    for(var i = 0; i < categoryList.length; i++){
    	var category = categoryList[i];
    	var selected = srb != null && srb.selectedCategory != null && category.name == srb.selectedCategory.name;
        select.append(createOption(category.name, category.name, selected));
    }
    select.change(function(){
    	clearUserInput(rowNr);
    	reqType = "reload";
    	submitSearchForm();
    });
    section.append(select);
    
    return section;
}

function createAttributeSection(srb, disabled){
	var section = $('<section class="col-xs-3"></section>');
	
	var rowNr = numberOfRows;
    var select = $('<select name="attribute_'+rowNr+'" class="btn btn-default dropdown-toggle '+ disabled +'" '+ disabled +'></select>');
    if(disabled == "") {
	    for(var i = 0; i < srb.attributeList.length; i++){
	    	var attribute = srb.attributeList[i];
	    	var selected = srb != null && srb.selectedAttribute != null && attribute.name == srb.selectedAttribute.name;
	    	select.append(createOption(attribute.name, attribute.name, selected));
	    }
	    select.change(function(){
	    	clearUserInput(rowNr);
	    	reqType = "reload";
	    	submitSearchForm();
	    });
    }
    section.append(select);
    
    return section;
}

function createInputSection(srb){
	var section = $('<section id="inputSection_'+numberOfRows+'" class="col-xs-5"></section>');
	
	var rowNr = numberOfRows;
	var dsf = srb != null && srb.selectedAttribute != null ? srb.selectedAttribute.defaultStorageField : 'string_val';
	var userInput1 = srb != null && srb.userInput1 != undefined ? srb.userInput1 : "";
	var userInput2 = srb != null && srb.userInput2 != undefined ? srb.userInput2 : "";
	
	if(srb != null) {
		var validStyleInput1 = srb.validInput1 ? "" : "border-color: red;";
		var validStyleInput2 = srb.validInput2 ? "" : "border-color: red;";
	}
	
	if(dsf == 'string_val'){
		section.append('<input type="text" name="userInput1_'+rowNr+'" value="'+userInput1+'" placeholder="text" class="form-control" style="'+validStyleInput1+'"/>');
	}
	else if(dsf == 'int_val' || dsf == 'double_val'){
		section.append('<input type="text" name="userInput1_'+rowNr+'" value="'+userInput1+'" placeholder="from" class="form-control inputFrom" style="'+validStyleInput1+'"/><input type="text" name="userInput2_'+rowNr+'" value="'+userInput2+'" placeholder="to" class="form-control inputTo" style="'+validStyleInput2+'"/>');
	}
	else if(dsf == 'datetime_val'){
		section.append('<input type="date" name="userInput1_'+rowNr+'" value="'+userInput1+'" placeholder="yyyy-mm-dd" class="form-control" style="'+validStyleInput1+'"/>');
	}
	else if(dsf == 'drop_down' || dsf == 'taxonomy_id'){
		var select = $('<select name="userInput1_'+rowNr+'" class="btn btn-default dropdown-toggle"></select>');
		for(var i = 0; i < srb.dropdownPairList.length; i++){
			var pair = srb.dropdownPairList[i];
			select.append(createOption(pair.first, pair.second, pair.first == userInput1));
		}
		section.append(select);
	}
	else{
		section.append(dsf);
	}
    
    return section;
}

function createOption(value, text, selected){
	var sel = selected ? "selected" : "";
	return '<option value="'+value+'" '+sel+'>'+text+'</option>';
}

function clearUserInput(rowNr){
	$("#inputSection_"+rowNr).find('input').val("");
	$("#inputSection_"+rowNr).find('select').val("");
}

function addHiddenfields(){
	var form = $("#searchForm");
	form.append('<input type="hidden" name="reqType" value="'+reqType+'"/>');
	form.append('<input type="hidden" name="searchResultCount" value="'+searchResultCount+'"/>');
}


function submitSearchForm(){
	addHiddenfields();
	$("#searchForm").submit();
}

$(document).ready(function() {
	
	init();

	$("#searchButton").click(function(){
		reqType = "search";
		submitSearchForm();
	});
	
	$("#showButton").click(function(){
		reqType = "show";
		submitSearchForm();
	});
	
	$("#addButton").click(function(){
		addSearchRow(null);
	});
	
	$("input").change(function(){
		$("#showButton").prop('disabled', true);
	});
	
	$("input").keypress(function (e) {
	  if (e.which == 13) {
		submitSearchForm();
	    return false;    //<---- Add this line
	  }
	});
	
});
