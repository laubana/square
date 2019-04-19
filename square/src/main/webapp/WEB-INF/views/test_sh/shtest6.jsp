<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no">
    <meta charset="utf-8">
    <title>Marker Clustering</title>
    <style>
      /* Always set the map height explicitly to define the size of the div
       * element that contains the map. */
      #map {
        height: 100%;
      }
      /* Optional: Makes the sample page fill the window. */
      html, body {
        height: 100%;
        margin: 0;
        padding: 0;
      }
    </style>
  </head>
  <body>
    <div id="map"></div>
    
  </body>


 <!-- 맵 띄우는 스크립트 -->

<script>
var locations = ${requestScope.event_schedule_list};

var region_cnt = [];
var region_list = [];
var i = 0;
var j = 0;
var flag = false;
for( i = 0; i < locations.length; i = i + 1 ){
	flag = false;
	for( j = 0; j < region_list.length; j = j + 1 ){
		console.log('for check j: ' + j + ' / i :' + i);
		if( region_list[j] == locations[i] ){
			region_cnt[j] = region_cnt[j] + 1;
			console.log('if check1: ' + region_list[j]);
			flag = true;
			break;
		}
	}
	if(!flag){
		region_list.push( locations[i] );
		region_cnt.push(1);
		console.log('if check2: ' + locations[i]);
	}
}
console.log( 'return check: region_list:' + JSON.stringify(region_list) );


var max = 0;
var index_region = 0;
for( i = 0; i < region_cnt.length; i = i + 1){
	if( region_cnt[i] >= max ) {
		max = region_cnt[i];
		index_region = i;
	}
}
console.log( 'return check: ' + region_list[index_region] );

</script>

<script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCdC1Oa4xE2ub87g1ouqeRxqapzLLg4shg&callback=initMap&language=ja&region=JP">
</script>
<script src="https://developers.google.com/maps/documentation/javascript/examples/markerclusterer/markerclusterer.js">
</script>


  
</html>