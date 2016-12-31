var defaultDisplayedAttributes = 5;

function init(){
	
	var space = $("#space");
	space.append(createNavTab(spaceDetailBeanList));
	space.append(createTabContent(spaceDetailBeanList));
	createSpectralChart("chart", spaceDetailBeanList[0]);
	createMap(spaceDetailBeanList[0]);
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
	tabContent.append('<div class="row"><div class="col-md-2"></div><div id="chart" class="col-md-8"></div><div class="col-md-2"></div></div>');
	tabContent.append('<div class="row"><div class="col-md-2"></div><div id="map" class="col-md-8"></div><div class="col-md-2"></div></div>');
	
	for(var i=0; i < spaceDetailBeanList.length; i++){
		var sdb = spaceDetailBeanList[i];
		var active = i == 0 ? "in active" : "";
		var temp = $('<div id="tab'+i+'" class="tab-pane fade '+active+'"></div>');
		
		temp.append(createMetaDataDiv(sdb.categoryAttributeMap));
		
		tabContent.append(temp);
	}
	
	return tabContent;
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
	var categories = $('<article></article>');
	var categoryCount = 0;
	$.each(categoryAttributeMap, function(category, attributeList){
		
		var container = $('<section></section>');
		
		container.append('<h2>'+category+'</h2>');
		
		var categoryDiv = $('<div id="category'+categoryCount+'"></div>');
		
		var table = $('<table class="table"></table>');
		var tbody = $('<tbody></tbody>');
		var hiddentbody = $('<tbody hidden></tbody>');
		
		for(var i = 0; i < attributeList.length; i++){
			var attribute = attributeList[i];
			var displayName = attribute.first;
			var value = attribute.second;
			
			if("PDFs" == category)
				value = '<a href="'+value+'" target="_blank"> Download PDF </a>';
			else if("Pictures" == category)
				value = '<a href="'+value+'" target="_blank">' +
						'<img class="img-responsive" src="'+value+'" alt="Download Image">' +
						'</a>';
			
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
			var showAll = $('<input type="button" class="btn btn-sm btn-default" style="margin-left: 50%; margin-right; 50%;"  value="Show All" />');
			showAll.click(function(){
				if(hiddentbody.prop('hidden')) 
					showAll.val('Show Less');
				else showAll.val('Show All');
				
				hiddentbody.prop('hidden', !hiddentbody.prop('hidden'));
			});
			table.append(showAll);
		}
		categoryDiv.append(table);
		container.append(categoryDiv);
		categories.append(container);
		categoryCount++;
	});
	return categories;
}

function createMap(sdb) {
	
	if(sdb.latLongList != undefined && sdb.latLongList.length > 0){
	
		var zoom = sdb.latLongList.length == 1 ? 4 : 2;
	
		var map = new google.maps.Map(document.getElementById('map'), {
			zoom : zoom,
			center : new google.maps.LatLng(sdb.latLongList[0].first, sdb.latLongList[0].second)
		});
		
		for(var i = 0; i < sdb.latLongList.length; i++){
			var latLong = sdb.latLongList[i];
			
			var temp = new google.maps.Marker({
			    position: new google.maps.LatLng(latLong.first, latLong.second),
			    map: map
			});
		}
	}
	else $("#map").hide();
}

function createAttributeTR(category, displayName, value){
	var tr = $('<tr></tr>');
	if(category == "Data Links" && value != "Multiple Values"){
		tr.append('<td>'+displayName+':</td><td style="word-break:break-all;word-wrap:break-word;"><a href="#" onclick="showLinkedSpectrum('+value+');">Show Linked Spectrum</a></td>');
	}
	else {
		tr.append('<td>'+displayName+':</td><td style="word-break:break-all;word-wrap:break-word;">'+value+'</td>');
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
		createMap(spaceDetailBeanList[this.id]);
	});
	
});
