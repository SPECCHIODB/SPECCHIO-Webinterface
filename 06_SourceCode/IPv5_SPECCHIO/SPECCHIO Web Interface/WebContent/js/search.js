var numberOfRows;
var reqType;
var firstRow;

function init(){
	
	numberOfRows = 0;
	reqType = "init";
	firstRow = true;
	
	if(searchResultCount > 0){
		$("#showButton").prop('disabled', false);
		
		if(searchResultCount == 1) 
			showMessageBox("success", "glyphicon-ok-sign", "One spectrum has been found for your search criteria.");
		else 
			showMessageBox("success", "glyphicon-ok-sign", searchResultCount+" spectra have been found for your search criteria.");
	}
	else {
		showMessageBox("info", "glyphicon-info-sign", "Set or modify your search criteria to search for spectra.")
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

function showMessageBox(type, glyphicon, msg){
	var form = $("#searchForm");
	var msgBox = $('<div class="alert alert-'+type+'"></div>');
	msgBox.append('<span class="glyphicon '+glyphicon+'"></span> ');
	msgBox.append(msg);
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
}

function createTitleRow(){
	var titleRow = $('<div class="row"></div>');
	titleRow.append('<div class="col-lg-3"><label>Category</label></div>');
	titleRow.append('<div class="col-lg-3"><label>Attribute</label></div>');
	titleRow.append('<div class="col-lg-5"><label>Input</label></div>');
	
	return titleRow;
}

function createSearchRow(srb){

    var searchRow = $('<div class="row"></div>');
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
    	submitSearchForm();
    });
    return removeButton;
}

function createCategorySection(srb){
	var section = $('<section class="col-lg-3"></section>');
    
	var rowNr = numberOfRows;
	var select =  $('<select name="category_'+rowNr+'" class="btn btn-default dropdown-toggle"></select>');
    for(var i = 0; i < categoryList.length; i++){
    	var category = categoryList[i];
    	var selected = srb != null && srb.selectedCategory != null && category.name == srb.selectedCategory.name;
        select.append(createOption(category.name, category.name, selected));
    }
    select.change(function(){
    	clearUserInput(rowNr);
    	submitSearchForm();
    });
    section.append(select);
    
    return section;
}

function createAttributeSection(srb, disabled){
	var section = $('<section class="col-lg-3"></section>');
	
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
	    	submitSearchForm();
	    });
    }
    section.append(select);
    
    return section;
}

function createInputSection(srb){
	var section = $('<section id="inputSection_'+numberOfRows+'" class="col-lg-5"></section>');
	
	var rowNr = numberOfRows;
	var dsf = srb != null && srb.selectedAttribute != null ? srb.selectedAttribute.defaultStorageField : 'init';
	var userInput1 = srb != null && srb.userInput1 != undefined ? srb.userInput1 : "";
	var userInput2 = srb != null && srb.userInput2 != undefined ? srb.userInput2 : "";
	
	if(dsf == 'init'){
		section.append('<input type="text" name="userInput1_'+rowNr+'" class="form-control" />');
	}
	else if(dsf == 'string_val'){
		section.append('<input type="text" name="userInput1_'+rowNr+'" value="'+userInput1+'" class="form-control" />');
	}
	else if(dsf == 'int_val' || dsf == 'double_val'){
		section.append('<input type="text" name="userInput1_'+rowNr+'" value="'+userInput1+'" placeholder="from" class="form-control inputFrom"/><input type="text" name="userInput2_'+rowNr+'" value="'+userInput2+'" placeholder="to" class="form-control inputTo"/>');
	}
	else if(dsf == 'datetime_val'){
		section.append('<input type="date" name="userInput1_'+rowNr+'" value="'+userInput1+'" class="form-control" />');
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
	form.append('<input type="hidden" name="numberOfRows" value="'+numberOfRows+'"/>');
	form.append('<input type="hidden" name="reqType" value="'+reqType+'"/>');
}


function submitSearchForm(){
	addHiddenfields();
	$("#searchForm").submit();
}

$(document).ready(function() {
	
	init();

	$("#searchButton").click(function(){
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
	
});
