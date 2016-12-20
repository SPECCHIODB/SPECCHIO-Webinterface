var selectedCheckboxes;
var selectCount;
function init(){
	
	selectedCheckboxes = [];
	selectCount = 0;
	$("#showDetail").prop('disabled', true);
	
	for(var i = 0; i < searchResultBeanList.length; i++){
		addTableRow(i, searchResultBeanList[i]);
	}
	
}

function addTableRow(index, srb){
	var tbody = $("tbody");
	tbody.append(createTableRow(index, srb));
}

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
	
	$("#showDetail").prop('disabled', selectCount == 0);
}

$(document).ready(function() {
	
	init();
	
	$('#resultTable').DataTable();
	
	$("#showDetail").click(function(){
		
		var selectedSearchResultBeanList = [];
		var index = 0;

		for(var i = 0; i < selectedCheckboxes.length; i++){
			if(selectedCheckboxes[i] != undefined) {
				selectedSearchResultBeanList[index] = searchResultBeanList[selectedCheckboxes[i].val()];
				index++;
			}
		}
		
		var form = $("#detailForm");
		
		// replacing " with ' because of conflicts in servlet (/g = replace all ")
		var json = JSON.stringify(selectedSearchResultBeanList).replace(/"/g, "'"); 
		
		form.append('<input type="hidden" name="selectedSearchResultBeanList" value="'+json+'"/>');
		form.submit();
	});
	
});
