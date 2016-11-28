var numberOfRows = 0;
var doSearch = false;

function init(){
	
	if(searchRowBeanList.length == 0) {
		addSearchRow(null);
	}
	else {
		for(var i = 0; i < searchRowBeanList.length; i++){
			addSearchRow(searchRowBeanList[i]);
		}
	}
	
}

function addSearchRow(srb){
	var form = $("#searchForm");
	form.append(createSearchRow(srb));
	numberOfRows++;
}

function createSearchRow(srb){

    var searchRow = $('<div class="searchRow"></div>');
    searchRow.append(createCategorySection(srb));
    if(srb != null && srb.selectedCategory.name != "Full Text Search") 
    	searchRow.append(createAttributeSection(srb));
    searchRow.append(createInputSection(srb));
    var removeButton = $('<input type="button" value="x" title="Remove" />').click(function(){
    	searchRow.remove();
    	submitSearchForm();
    });
    searchRow.append(removeButton);
    
    return searchRow;
}

function createCategorySection(srb){
	var section = $('<section></section>');
	var label = 'Category<br/>';
	section.append(label);
    
	var rowNr = numberOfRows;
	var select =  $('<select name="category_'+rowNr+'"></select>');
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

function createAttributeSection(srb){
	var section = $('<section></section>');
	var label = 'Attribute<br/>';
	section.append(label);
	
	var rowNr = numberOfRows;
    var select = $('<select name="attribute_'+rowNr+'"></select>');
    for(var i = 0; i < srb.attributeList.length; i++){
    	var attribute = srb.attributeList[i];
    	var selected = srb != null && srb.selectedAttribute != null && attribute.name == srb.selectedAttribute.name;
    	select.append(createOption(attribute.name, attribute.name, selected));
    }
    select.change(function(){
    	clearUserInput(rowNr);
    	submitSearchForm();
    });
    section.append(select);
    
    return section;
}

function createInputSection(srb){
	var section = $('<section id="inputSection_'+numberOfRows+'"></section>');
	var label = 'Input<br/>';
	section.append(label);
	
	var rowNr = numberOfRows;
	var dsf = srb != null && srb.selectedAttribute != null ? srb.selectedAttribute.defaultStorageField : 'init';
	var userInput1 = srb != null && srb.userInput1 != undefined ? srb.userInput1 : "";
	var userInput2 = srb != null && srb.userInput2 != undefined ? srb.userInput2 : "";
	
	if(dsf == 'init'){
		section.append('<input type="text" name="userInput1_'+rowNr+'" />');
	}
	else if(dsf == 'string_val'){
		section.append('<input type="text" name="userInput1_'+rowNr+'" value="'+userInput1+'"/>');
	}
	else if(dsf == 'int_val' || dsf == 'double_val'){
		section.append('<input type="text" name="userInput1_'+rowNr+'" value="'+userInput1+'"/> - <input type="text" name="userInput2_'+rowNr+'" value="'+userInput2+'"/>');
	}
	else if(dsf == 'datetime_val'){
		section.append('<input type="date" name="userInput1_'+rowNr+'" value="'+userInput1+'"/>');
	}
	else if(dsf == 'drop_down' || dsf == 'taxonomy_id'){
		var select = $('<select name="userInput1_'+rowNr+'"></select>');
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
	form.append('<input type="hidden" name="doSearch" value="'+doSearch+'"/>');
}


function submitSearchForm(){
	addHiddenfields();
	$("#searchForm").submit();
}

$(document).ready(function() {
	
	init();
	
	$("#searchButton").click(function(){
		doSearch = true;
		submitSearchForm();
	});
	
	$("#addButton").click(function(){
		addSearchRow(null);
	});
	
});
