// global variables
var selectedCheckboxes;	// contains all selected checkboxes
var selectCount;		// count of selected checkboxes

/**
 * initial settings and function calls
 */
function init(){
	selectedCheckboxes = [];
	selectCount = 0;
	$("#showDetail").prop('disabled', true);
	
	for(var i = 0; i < searchResultBeanList.length; i++){
		addTableRow(i, searchResultBeanList[i]);
	}
	
	createMap();
}

/**
 * adds a table row with the given index for the given SearchResultBean
 */
function addTableRow(index, srb){
	var tbody = $("tbody");
	tbody.append(createTableRow(index, srb));
}

/**
 * creates a table row with the given index for the given SearchResultBean
 */
function createTableRow(index, srb){
	var tableRow = $('<tr></tr>');
	
	var checkbox = $('<td><input type="checkbox" value="'+index+'"/></td>');
	checkbox.click(function(){
		handleCheckboxSelection(checkbox.find("input:checkbox"));
	});
	tableRow.append(checkbox);
	tableRow.append(createTableData(tableRow, srb.acquisitionTime));
	tableRow.append(createTableData(tableRow, srb.campaignName));
	tableRow.append(createTableData(tableRow, srb.investigator));
	tableRow.append(createTableData(tableRow, srb.name));
	tableRow.append(createTableData(tableRow, srb.fileName));
	tableRow.append(createTableData(tableRow, srb.institute));
	
	return tableRow;
}

/**
 * creates one table data tag containing the given value
 */
function createTableData(tableRow, value){
	var tableData = $('<td></td>');
	tableData.click(function(){
		var checkbox = $(tableRow).find("input:checkbox");
		var checked = checkbox.prop("checked")
		checkbox.prop("checked", !checked);
		
		handleCheckboxSelection(checkbox);
	});
	
	tableData.append(value);
	return tableData;
}

/**
 * called when checkbox or tablerow is clicked
 */
function handleCheckboxSelection(checkbox){
	
	var checked = checkbox.prop("checked");
	if(checked) {
		selectedCheckboxes[checkbox.val()] = checkbox;
		selectCount++;
	}
	else {
		selectedCheckboxes[checkbox.val()] = undefined;
		selectCount--;
	}
	
	// disable showDetail button if no checkboxes are selected
	$("#showDetail").prop('disabled', selectCount == 0);
}

/**
 * Creates the search result map with a marker for each spectra that has a latitude and longitude.
 */
function createMap() {

	var subList = [];
	var index = 0;
	
	// get all rows that contain a latitude and longitude.
	// those will be displayed as markers on the map.
	for(var i = 0; i < searchResultBeanList.length; i++){
		var srb = searchResultBeanList[i];
		if(srb.latitude != null && srb.latitude != "" && srb.longitude != null && srb.longitude != ""){
			subList[index] = srb;
			index++;
		}
	}
	
	// set the center of the map to the first marker
	var centerLat = subList.length > 0 ? parseFloat(subList[0].latitude) : 0;
	var centerLng = subList.length > 0 ? parseFloat(subList[0].longitude) : 0;
	
	// create the map with zoom level 2
	var map = new google.maps.Map(document.getElementById('map'), {
		zoom : 2,
		center : new google.maps.LatLng(centerLat, centerLng)
	});
	
	// for each row with lat and long we create a marker and add it to the map
	// on click the detail page will be displayed for that row
	for(var i = 0; i < subList.length; i++){
		(function(srb){
			
			if(srb.latitude != null && srb.latitude != "" && srb.longitude != null && srb.longitude != ""){
				var temp = new google.maps.Marker({
				    position: new google.maps.LatLng(parseFloat(srb.latitude), parseFloat(srb.longitude)),
				    map: map,
				    title: srb.fileName
				});
				temp.addListener('click', function() {
					var selectedSearchResultBeanList = [];
					selectedSearchResultBeanList[0] = srb;
					submitForm(selectedSearchResultBeanList);
				});
			}
			
	    })(subList[i]); // pass the value of i
	}
	
}

/**
 * adds hiddenfields and submits the detailForm
 */
function submitForm(selectedSearchResultBeanList){
	var form = $("#detailForm");
	
	// replacing " with ' because of conflicts in servlet (/g = replace all ")
	var json = JSON.stringify(selectedSearchResultBeanList).replace(/"/g, "'"); 
	
	$("#srbList").remove();
	form.append('<input type="hidden" id="srbList" name="selectedSearchResultBeanList" value="'+json+'"/>');
	form.submit();
}

/**
 * called initially on page load
 */
$(document).ready(function() {
	
	init();
	
	$('#resultTable').DataTable();
	
	$("#showDetail").click(function(){
		
		var selectedSearchResultBeanList = [];
		var index = 0;

		// add all selected SearchResultBeans to a list to send it to the java backend
		for(var i = 0; i < selectedCheckboxes.length; i++){
			if(selectedCheckboxes[i] != undefined) {
				selectedSearchResultBeanList[index] = searchResultBeanList[selectedCheckboxes[i].val()];
				index++;
			}
		}
		
		submitForm(selectedSearchResultBeanList)
	});
	
});
