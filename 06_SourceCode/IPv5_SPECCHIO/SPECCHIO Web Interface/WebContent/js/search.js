// global variables
var numberOfRows;	// current number of search rows
var reqType; 		// used to differentiate between requests
var firstRow;		// used to find out whether the current row is the first row

/**
 * initial settings and function calls
 */
function init(){
	
	numberOfRows = 0;
	reqType = "search";
	firstRow = true;
	
	// if there were validation errors found in the backend 
	// display a warning message box with all error messages
	if(!valid){ 
		var errors = ""
		for(var i = 0; i < searchRowBeanList.length; i++){
			var srb = searchRowBeanList[i];
			if(!srb.validInput1 || !srb.validInput2) 
				errors += "- " + srb.errorMessage + "<br/>";
		}
		showMessageBox("warning", "glyphicon-warning-sign", "Please correct the following input errors", errors);
	}
	// if there were no validation errors, display the amount of spectra that have been found
	else if(searchResultCount > 0){
		$("#showButton").prop('disabled', false);
		
		if(searchResultCount == 1) 
			showMessageBox("success", "glyphicon-ok-sign", "One spectrum has been found.", "Add or Modify filters to search for spectra. Press 'Show Spectra' to have a look at all found spectra.");
		else 
			showMessageBox("success", "glyphicon-ok-sign", searchResultCount + " spectra have been found.", "Add or Modify filters to search for spectra. Press 'Show Spectra' to have a look at all found spectra.");
	}
	else {
		showMessageBox("info", "glyphicon-info-sign", "No spectrum has been found.", "Add or Modify filters, then press 'Search' to search for spectra.")
	}
	
	addTitleRow(); // create and append a title row
	
	// if the searchRowBeanList is empty (initial call),
	// we display one empty fulltextsearch row
	if(searchRowBeanList.length == 0) {
		addSearchRow(null);
	}
	// display all rows inside the searchRowBeanList
	else {
		for(var i = 0; i < searchRowBeanList.length; i++){
			addSearchRow(searchRowBeanList[i]);
		}
	}
	
}

/**
 * creates a message box of the given alert type, with the given icon 
 * and title and displaying the given message. 
 */
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

/**
 * creates the title row and appends it to the searchForm
 */
function addTitleRow(){
	var form = $("#searchForm");
	form.append(createTitleRow());
}

/**
 * creates a search row and appends it to the searchForm
 */
function addSearchRow(srb){
	var form = $("#searchForm");
	form.append(createSearchRow(srb));
	 // update number of rows and write it to hiddenfield
	numberOfRows++;
	$("#numberOfRows").val(numberOfRows);
}

/**
 * Creates and returns a titleRow containing 'Category', 'Attribute' and 'Input'
 */
function createTitleRow(){
	var titleRow = $('<div class="row row-gap"></div>');
	titleRow.append('<div class="col-xs-3"><label>Category</label></div>');
	titleRow.append('<div class="col-xs-3"><label>Attribute</label></div>');
	titleRow.append('<div class="col-xs-5"><label>Input</label></div>');
	
	return titleRow;
}

/**
 * Creates and returns a search row containing the Category-Section, 
 * the Attribute-Section and the Input-Section
 */
function createSearchRow(srb){

    var searchRow = $('<div class="row row-gap"></div>');
    searchRow.append(createCategorySection(srb));
    // for Full Text Search there are no attributes so we disable the attribte-dropdown
    if(srb != null && srb.selectedCategory.name != "Full Text Search") 
    	searchRow.append(createAttributeSection(srb, ""));
    else searchRow.append(createAttributeSection(srb, "disabled"));
    searchRow.append(createInputSection(srb));
    
    // all rows except the first one will have a remove row button
    if(firstRow) firstRow = false;
    else searchRow.append(createRemoveButton(searchRow));
    
    return searchRow;
}

/**
 * creates a button for a searchRow used to remove that row 
 */
function createRemoveButton(searchRow){
    var removeButton = $('<button type="button" title="Remove" class="btn btn-danger"><span class="glyphicon glyphicon-remove"></span></button>').click(function(){
    	searchRow.remove();
    	reqType = "search";
    	submitSearchForm();
    });
    return removeButton;
}

/**
 * creates the category section with the category dropdown
 */
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

/**
 * Creates the attribute section with the attribute dropdown.
 * @param disabled : should attribute dropdown be disabled?
 */
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

/**
 * Creates the user input section for the given SearchRowBean.
 */
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
	
	// for string values we add one input text field
	if(dsf == 'string_val'){
		section.append('<input type="text" name="userInput1_'+rowNr+'" value="'+userInput1+'" placeholder="text" class="form-control" style="'+validStyleInput1+'"/>');
	}
	// for int and double values we add a from and a to input text field
	else if(dsf == 'int_val' || dsf == 'double_val'){
		section.append('<input type="text" name="userInput1_'+rowNr+'" value="'+userInput1+'" placeholder="from" class="form-control inputFrom" style="'+validStyleInput1+'"/>');
		section.append('<input type="text" name="userInput2_'+rowNr+'" value="'+userInput2+'" placeholder="to" class="form-control inputTo" style="'+validStyleInput2+'"/>');
	}
	// for datetime values we add a from and a to input date field
	else if(dsf == 'datetime_val'){
		section.append('<input type="date" name="userInput1_'+rowNr+'" value="'+userInput1+'" placeholder="from (yyyy-mm-dd)" class="form-control inputFrom" style="'+validStyleInput1+'"/>');
		section.append('<input type="date" name="userInput2_'+rowNr+'" value="'+userInput2+'" placeholder="to (yyyy-mm-dd)" class="form-control inputTo" style="'+validStyleInput2+'"/>');
	}
	// for taxonomy and dropdown values we display an input dropdown
	else if(dsf == 'drop_down' || dsf == 'taxonomy_id'){
		var select = $('<select name="userInput1_'+rowNr+'" class="btn btn-default dropdown-toggle"></select>');
		for(var i = 0; i < srb.dropdownPairList.length; i++){
			var pair = srb.dropdownPairList[i];
			select.append(createOption(pair.first, pair.second, pair.first == userInput1));
		}
		section.append(select);
	}
    
    return section;
}

/**
 * creates an option tag with the given value and text
 * @param selected : should the dropdown be selected?
 */
function createOption(value, text, selected){
	var sel = selected ? "selected" : "";
	return '<option value="'+value+'" '+sel+'>'+text+'</option>';
}

/**
 * removes all user input for the given row
 */
function clearUserInput(rowNr){
	$("#inputSection_"+rowNr).find('input').val("");
	$("#inputSection_"+rowNr).find('select').val("");
}

/**
 * appens hiddenfields to the searchForm (called before submitting)
 */
function addHiddenfields(){
	var form = $("#searchForm");
	form.append('<input type="hidden" name="reqType" value="'+reqType+'"/>');
	form.append('<input type="hidden" name="searchResultCount" value="'+searchResultCount+'"/>');
}

/**
 * adds hiddenfields and submits the searchForm
 */
function submitSearchForm(){
	addHiddenfields();
	$("#searchForm").submit();
}

/**
 * called initially on page load
 */
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
	
	$("select").change(function(){
		$("#showButton").prop('disabled', true);
	});
	
	$("input").keypress(function (e) {
	  // Submit Form when pressing Enter in an input field
	  if (e.which == 13) {
		submitSearchForm();
	    return false;
	  }
	});
	
});
