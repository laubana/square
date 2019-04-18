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
function initMap() {
    var latlng = new google.maps.LatLng(35.6715003, 139.764913);
    var mapOptions = {
			zoom: 15,
			center: latlng
    	}
		var map = new google.maps.Map(document.getElementById('map'), mapOptions);
		var geocoder = new google.maps.Geocoder();
		var locations = [];
 	
		<c:forEach items = "${requestScope.event_schedule_list}" var = "list">
			locations.push( { lat: ${list.latitude}, lng: ${list.longitude}, region: "${list.region}", name: "${list.name}", event_schedule_id: ${list.event_schedule_id}, content: "${list.content}", start_date: "${list.start_date}" } );
		</c:forEach>

		var labels = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ';
		
		var markers = locations.map( function(location, i) {
			return new google.maps.Marker({	
					position: new google.maps.LatLng(locations[i].lat, locations[i].lng),
				});
			});		
       var markerCluster = new MarkerClusterer(map, markers,
           {imagePath: 'https://developers.google.com/maps/documentation/javascript/examples/markerclusterer/m'});
		markers.map( function(marker, i) {
			var infowindow = new google.maps.InfoWindow({
		          content: locations[i].name + '<br>場所: ' + locations[i].region + '<br>内容: '+ locations[i].content + '<div><img src = "resources/images/clustering/samplepng/' + i + '.png">',
		          maxWidth: 250
		        });
			  marker.addListener('click', function() {
					infowindow.open(map, marker);
				});
			});
}
</script>


<script>
function initMap() {
    var latlng = new google.maps.LatLng(37.5729503, 126.97935789999997);
    var mapOptions = {
    	      zoom: 15,
    	      center: latlng
    	    }
    var map = new google.maps.Map(document.getElementById('map'), mapOptions);

	var geocoder = new google.maps.Geocoder();
 	var address = '${ requestScope.place }';
 	   geocoder.geocode(
	   		{ 'address': address }
	   		, function(results, status) {
				if (status == 'OK') {
					latlng = results[0].geometry.location;
					map.setCenter(latlng);
					var marker = new google.maps.Marker({ 
						map: map,
						position: latlng
						});
					map.setZoom(15);
					
				} else {
	   				alert('Geocode was not successful for the following reason: ' + status);
	   			}
	   		}
	   );
    
}
</script>


<script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCdC1Oa4xE2ub87g1ouqeRxqapzLLg4shg&callback=initMap&language=ja&region=JP">
</script>
<script src="https://developers.google.com/maps/documentation/javascript/examples/markerclusterer/markerclusterer.js">
</script>


  
</html>