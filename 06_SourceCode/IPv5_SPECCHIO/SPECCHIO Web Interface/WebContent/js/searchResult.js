function init(){
	
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
	
	tableRow.append('<td><input type="checkbox" value="'+index+'"/></td>');
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
		var checked = $(tableRow).find("input:checkbox").prop("checked")
		$(tableRow).find("input:checkbox").prop("checked", !checked)
	});
	
	tableData.append(value);
	return tableData;
}

$(document).ready(function() {
	
	init();
	
	 $('#test').DataTable();
	
	$("#showDetail").click(function(){
		
		var selectedSearchResultBeanList = [$("tbody input:checkbox:checked").length];
		var index = 0;
		
		$("tbody input:checkbox:checked").each(function(){
			selectedSearchResultBeanList[index] = searchResultBeanList[this.value];
			index++;
		});
		
		var form = $("#detailForm");
		
		// replacing " with ' because of conflicts in servlet (/g = replace all ")
		var json = JSON.stringify(selectedSearchResultBeanList).replace(/"/g, "'"); 
		
		form.append('<input type="hidden" name="selectedSearchResultBeanList" value="'+json+'"/>');
		form.submit();
	});
	
});
