// amount of attributes always displayed per category
// if there are more attributes a show all button will be added
var defaultDisplayedAttributes = 5; 

/**
 * initial settings and function calls
 */
function init(){
	var space = $("#space");
	space.append(createNavTab(spaceDetailBeanList));
	space.append(createTabContent(spaceDetailBeanList));
	createSpectralChart("chart", spaceDetailBeanList[0]);
	createMap(spaceDetailBeanList[0]);
}

/**
 * Creates the navigation with a tab for each spectrum.
 */
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

/**
 * Creates the content of each tab.
 * The content of the selected tab will be displayed.
 */
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

/**
 * Creates the highchart for all spectra of the given space detail bean.
 */
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
	            title: {
	                text: 'Wavelength [nm]'
	            },
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

/**
 * Creates the div containing all Meta Data information of that space detail bean.
 */
function createMetaDataDiv(categoryAttributeMap){
	var categories = $('<article></article>');
	var categoryCount = 0;
	
	// iterating over each category with its attributes
	$.each(categoryAttributeMap, function(category, attributeList){
		
		var container = $('<section></section>');
		
		container.append('<h2>'+category+'</h2>'); // add title for category
		
		var categoryDiv = $('<div id="category'+categoryCount+'"></div>');
		
		var table = $('<table class="table"></table>');
		var tbody = $('<tbody></tbody>');
		var hiddentbody = $('<tbody hidden></tbody>');
		
		for(var i = 0; i < attributeList.length; i++){ // iterate over attributes
			var attribute = attributeList[i];
			var displayName = attribute.first; 	// attribute name
			var value = attribute.second;		// attribute value
			
			if("PDFs" == category) // for pdfs we display a download link
				value = '<a href="'+value+'" target="_blank"> Download PDF </a>';
			else if("Pictures" == category) // for pictures we display the picture and add a download link to it
				value = '<a href="'+value+'" target="_blank">' +
						'<img class="img-responsive" src="'+value+'" alt="Download Image">' +
						'</a>';
			
			// change the format of dates to a nicer display format
			if("Acquisition Time" == displayName || "Loading Time" == displayName || "Sample Collection Date" == displayName){
				value = changeDateFormat(value);
			}
			
			// display first x attributes
			if(i < defaultDisplayedAttributes){
				tbody.append(createAttributeTR(category, displayName, value));
			}
			else{ // other attributes will be hidden until the show all button is pressed
				hiddentbody.append(createAttributeTR(category, displayName, value));
			}
		}
		
		table.append(tbody);
		// add show all button if this category has a lot of attributes
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

/**
 * changes the format "2000-07-14T13:15:15:111Z" to "2000-07-14 13:15:15".
 */
function changeDateFormat(value){
	// try to change dateformat from "2000-07-14T13:15:15:111Z" to "2000-07-14 13:15:15"
	var indexT = value.indexOf("T");
	var indexZ = value.indexOf(".000Z")
	var date = value.substring(0, indexT);
	var time = value.substring(indexT+1, indexZ);
	if(date == "" || time == "") return value;
	else return date+" "+time;
}

/**
 * Creates the search result map with a marker for each spectra, of the current space,
 * that has a latitude and longitude.
 */
function createMap(sdb) {
	
	if(sdb.latLongList != undefined && sdb.latLongList.length > 0){
	
		// if there is only one marker we zoom in further
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
	else $("#map").hide(); // hide the map div if there are no spectra with lat and long
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

/**
 * creates a hiddenfield with the given spectrum id and submits the detailform.
 * the page will be reloaded with the information of the given spectrum.
 */
function showLinkedSpectrum(spectrumId){
	var form = $("#detailForm");
	form.append('<input type="hidden" name="linkedSpectrumId" value="'+spectrumId+'"/>');
	form.submit();
}

/**
 * called initially on page load
 */
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
