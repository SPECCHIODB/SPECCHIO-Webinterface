function init(){
	
	for(var i = 0; i < metaDataBeanList.length; i++){
		addTableRow(i, metaDataBeanList[i]);
	}
	
}

function addTableRow(index, mdb){
	var tbody = $("tbody");
	tbody.append(createTableRow(index, mdb));
}

function createTableRow(index, mdb){
	var tableRow = $('<tr></tr>');
	
	tableRow.append('<td><input type="checkbox" value="'+index+'"/></td>');
	tableRow.append(createTableData(tableRow, mdb.acquisitionTime));
	tableRow.append(createTableData(tableRow, mdb.campaignName));
	tableRow.append(createTableData(tableRow, mdb.investigator));
	tableRow.append(createTableData(tableRow, mdb.common));
	tableRow.append(createTableData(tableRow, mdb.fileName));
	tableRow.append(createTableData(tableRow, mdb.institute));
	
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
	
	$("#showDetail").click(function(){
		
		var selectedMetaDataBeanList = [$("tbody input:checkbox:checked").length];
		var index = 0;
		
		$("tbody input:checkbox:checked").each(function(){
			selectedMetaDataBeanList[index] = metaDataBeanList[this.value];
			index++;
		});
		
		var form = $("#detailForm");
		
		// replacing " with ' because of conflicts in servlet (/g = replace all ")
		var json = JSON.stringify(selectedMetaDataBeanList).replace(/"/g, "'"); 
		
		form.append('<input type="hidden" name="selectedMetaDataBeanList" value="'+json+'"/>');
		form.submit();
	});
	
});
