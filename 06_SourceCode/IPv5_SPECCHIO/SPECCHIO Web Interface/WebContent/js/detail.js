function init(){
	
	var container = $("#container");
	container.append(createNavTab(spaceDetailBeanList.length));
	container.append(createTabContent(spaceDetailBeanList));
	createAllSpectralCharts(spaceDetailBeanList);
	
}

function createNavTab(tabCount){
	var navTab = $('<ul class="nav nav-tabs"></ul>');
		
	for(var i=0; i < tabCount; i++){
		var active = i==0 ? "active" : "";
		navTab.append('<li class="'+active+'"><a data-toggle="tab" href="#tab'+i+'">Space '+i+'</a></li>');
	}
	
	return navTab;
}

function createTabContent(spaceDetailBeanList){
	var tabContent = $('<div class="tab-content"></div>');
	
	for(var i=0; i < spaceDetailBeanList.length; i++){
		var sdb = spaceDetailBeanList[i];
		var active = i == 0 ? "in active" : "";
		var temp = $('<div id="tab'+i+'" class="tab-pane fade '+active+'">');
		
		temp.append('<div id="chart'+i+'"></div>');
		temp.append(createMetaDataDiv(sdb.categoryAttributeMap));
		
		tabContent.append(temp);
	}
	
	return tabContent;
}

function createAllSpectralCharts(spaceDetailBeanList) {
	for(var i = 0; i < spaceDetailBeanList.length; i++){
		var sdb = spaceDetailBeanList[i];
		createSpectralChart("#chart"+i, sdb.wavelength, sdb.vectors);
	}
}

function createSpectralChart(chartId, wavelength, vectors){

	var columnsArray = [vectors.length + 1];
	
	columnsArray[0] = createChartArray(wavelength.text, wavelength.values);
	
	for(var i = 0; i < vectors.length; i++){
		columnsArray[i+1] = createChartArray(vectors[i].text, vectors[i].values);
	}
	
	c3.generate({
		bindto: chartId,
		data: {
	        x: 'wavelength',
	        columns: columnsArray
	    },
	    zoom: {
	        enabled: true
	    },
	    tooltip: {
	    	grouped: false
	    }
	    
	});
}

function createChartArray(text, values){
	var array = [values.length + 1];
	array[0] = text;
	for(var i = 0; i < values.length; i++){
		array[i+1] = values[i];
	}
	return array;
}

function createMetaDataDiv(categoryAttributeMap){
	var categories = $('<div></div>');
	var categoryCount = 0;
	$.each(categoryAttributeMap, function(category, attributeList){
		
		var cssClass = categoryCount % 2 == 0 ? "container-left" : "container-right";
		var container = $('<div class="'+cssClass+'"></div>');
		
		//container.append('<a href="#category'+categoryCount+'" class="btn btn-default btnCollapse collapsed" data-toggle="collapse">'+category+'</a>');
		container.append('<h1>'+category+'</h1>');
		
		//var categoryDiv = $('<div id="category'+categoryCount+'" class="collapse"></div>');
		var categoryDiv = $('<div id="category'+categoryCount+'"></div>');
		
		var table = $('<table class="table"></table>');
		var tbody = $('<tbody></tbody>');
		
		for(var i = 0; i < attributeList.length; i++){
			var attribute = attributeList[i];
			var displayName = attribute.first;
			var value = attribute.second;
			
			if(category == "Data Links" && value != "Multiple Values"){
				tbody.append('<tr><td>'+displayName+':</td><td><a href="#" onclick="showLinkedSpectrum('+value+');">Show Linked Spectrum</a></td></tr>');
			}
			else {
				tbody.append('<tr><td>'+displayName+':</td><td>'+value+'</td></tr>');
			}
			
		}
		
		table.append(tbody);
		categoryDiv.append(table);
		categoryDiv.append('<hr/>')
		container.append(categoryDiv);
		categories.append(container);
		categoryCount++;
	});
	return categories;
}

function showLinkedSpectrum(spectrumId){
	var form = $("#detailForm");
	form.append('<input type="hidden" name="linkedSpectrumId" value="'+spectrumId+'"/>');
	form.submit();
}

$(document).ready(function() {
	init();
});
