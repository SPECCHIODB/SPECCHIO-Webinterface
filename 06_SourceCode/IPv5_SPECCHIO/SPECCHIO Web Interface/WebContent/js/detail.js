function init(){
	
	if(metaDataBeanList.length == 1){
		displaySingleAttributeDetail(metaDataBeanList[0]);
	}
	
	
	
	
}

function displaySingleAttributeDetail(mdb){
	var categories = $("#categories");
	
	var categoryCount = 0;
	$.each(categoryAttributesMap, function(category, attributeList){
		
		var cssClass = categoryCount % 2 == 0 ? "container-left" : "container-right";
		var container = $('<div class="'+cssClass+'"></div>');
		container.append('<a href="#category'+categoryCount+'" class="btn btn-default btnCollapse collapsed" data-toggle="collapse">'+category+'</a>');
		var categoryDiv = $('<div id="category'+categoryCount+'" class="collapse"></div>');
		var table = $('<table class="table"></table>');
		var tbody = $('<tbody></tbody>');
		
		var attributeCount = 0;
		for(var i = 0; i < attributeList.length; i++){
			var attribute = attributeList[i];
			var displayName = attribute.first;
			var fieldName = attribute.second;
			var value = mdb[fieldName];
			
			if(value != undefined && value != ""){
				tbody.append('<tr><td>'+displayName+':</td><td>'+value+'</td></tr>');
				attributeCount++;
			}
		}

		if(attributeCount > 0){
			table.append(tbody);
			categoryDiv.append(table);
			categoryDiv.append('<hr/>')
			container.append(categoryDiv);
			categories.append(container);
		}
		
		categoryCount++;
	});
}

$(document).ready(function() {
	
	init();
	
});
