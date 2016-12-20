var defaultDisplayedAttributes = 5;

function init(){
	
	var space = $("#space");
	space.append(createNavTab(spaceDetailBeanList));
	space.append(createTabContent(spaceDetailBeanList));
	createSpectralChart("chart", spaceDetailBeanList[0]);
	//createAllSpectralCharts(spaceDetailBeanList);
	
}

function createNavTab(spaceDetailBeanList){
	var navTab = $('<ul class="nav nav-tabs"></ul>');
		
	for(var i=0; i < spaceDetailBeanList.length; i++){
		var sdb = spaceDetailBeanList[i];
		var active = i==0 ? "active" : "";
		var li = $('<li class="'+active+'"><a data-toggle="tab" href="#tab'+i+'" class="tab" id="'+i+'">'+sdb.spaceTypeName+'</a></li>');
		navTab.append(li);
	}
	
	return navTab;
}

function createTabContent(spaceDetailBeanList){
	var tabContent = $('<div class="tab-content"></div>');
	tabContent.append('<div id="chart" class="spectralChart" style="margin: 0 auto; width: 80%;"></div>');
	
	for(var i=0; i < spaceDetailBeanList.length; i++){
		var sdb = spaceDetailBeanList[i];
		var active = i == 0 ? "in active" : "";
		var temp = $('<div id="tab'+i+'" class="tab-pane fade '+active+'"></div>');
		
		temp.append(createMetaDataDiv(sdb.categoryAttributeMap));
		
		tabContent.append(temp);
	}
	
	return tabContent;
}

function createAllSpectralCharts(spaceDetailBeanList) {
	for(var i = 0; i < spaceDetailBeanList.length; i++){
		var sdb = spaceDetailBeanList[i];
		createSpectralChart("chart"+i, sdb);
	}
}

function createSpectralChart(chartId, sdb){
	$(function () { 
	    var myChart = Highcharts.chart(chartId, {
	        chart: {
	            type: 'line',
	            zoomType: 'xy'
	        },
	        title: {
	            text: 'Spectrum Plot'
	        },
	        xAxis: {
	            categories: sdb.wavelength.data
	        },
	        yAxis: {
	            title: {
	                text: sdb.measurementUnit
	            },
	            max: sdb.maxY
	        },
	        series: sdb.vectors
	    });
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
		
		container.append('<h1>'+category+'</h1>');
		
		var categoryDiv = $('<div id="category'+categoryCount+'"></div>');
		
		var table = $('<table class="table"></table>');
		var tbody = $('<tbody></tbody>');
		var hiddentbody = $('<tbody hidden></tbody>');
		
		for(var i = 0; i < attributeList.length; i++){
			var attribute = attributeList[i];
			var displayName = attribute.first;
			var value = attribute.second;
			
			
			if(i < defaultDisplayedAttributes){
				tbody.append(createAttributeTR(category, displayName, value));
			}
			else{
				hiddentbody.append(createAttributeTR(category, displayName, value));
			}
		}
		
		
		table.append(tbody);
		if(attributeList.length > defaultDisplayedAttributes) {
			table.append(hiddentbody);
			var showAll = $('<input type="button" class="btn btn-xs btn-default" style="margin-left: 50%; margin-right; 50%;"  value="Show All" />');
			showAll.click(function(){
				if(hiddentbody.prop('hidden')) 
					showAll.val('Show Less');
				else showAll.val('Show All');
				
				hiddentbody.prop('hidden', !hiddentbody.prop('hidden'));
			});
			table.append(showAll);
		}
		categoryDiv.append(table);
//		if(category =="Location") {
//			categoryDiv.append('<div id="map"></div>');
//			categoryDiv.append('<script type="text/javascript" src="http://maps.googleapis.com/maps/api/js?libraries=places&v=3&callback=initMap"></script>');
//		}
		categoryDiv.append('<hr/>')
		container.append(categoryDiv);
		categories.append(container);
		categoryCount++;
	});
	return categories;
}

function initMap(){
	
	var myLatLng = {lat: -25.363, lng: 131.044};

	var map = new google.maps.Map(document.getElementById('map'), {
		zoom: 4,
		center: myLatLng
	});
	
	var marker = new google.maps.Marker({
		position: myLatLng,
	    map: map,
	    title: 'Hello World!'
	});
}

function createAttributeTR(category, displayName, value){
	var tr = $('<tr></tr>');
	if(category == "Data Links" && value != "Multiple Values"){
		tr.append('<td>'+displayName+':</td><td><a href="#" onclick="showLinkedSpectrum('+value+');">Show Linked Spectrum</a></td>');
	}
	else {
		tr.append('<td>'+displayName+':</td><td>'+value+'</td>');
	}
	return tr;
}

function showLinkedSpectrum(spectrumId){
	var form = $("#detailForm");
	form.append('<input type="hidden" name="linkedSpectrumId" value="'+spectrumId+'"/>');
	form.submit();
}

$(document).ready(function() {
	
	init();
	
	$("#export").click(function(){
		
		var form = $("#exportForm");
		
		// replacing " with ' because of conflicts in servlet (/g = replace all ")
		var json = JSON.stringify(spaceDetailBeanList).replace(/"/g, "'"); 
		
		form.append('<input type="hidden" name="spaceDetailBeanList" value="'+json+'"/>');
		form.append('<input type="hidden" name="doExport" value="true"/>');
		form.submit();
	});
	
	$("#pdf").click(function(){
		window.print();
	});
	
	$(".tab").click(function(){
		createSpectralChart("chart", spaceDetailBeanList[this.id]);
	});
	
});
