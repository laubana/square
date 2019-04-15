<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


	<style>
		#map {
			width: 500px;
			height: 350px;
			position: relative !important; /* changing this to fixed makes the map dissapear */
			top: 0; 
			bottom: 0; 
			left: 0; 
			right: 0; 
			z-index: 0;
	     }
		html,body {height: 100%; margin: 0; padding: 0;}
	</style>
	<script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCdC1Oa4xE2ub87g1ouqeRxqapzLLg4shg&callback=initMap">
	</script>


</head>
<body>

	<div id = "map"></div>



</body>



<script>
function initMap() {
    var latlng = new google.maps.LatLng(37.5729503, 126.97935789999997);
    var mapOptions = {
    	      zoom: 15,
    	      center: latlng
    	    }
    var map = new google.maps.Map(document.getElementById('map'), mapOptions);

	var geocoder = new google.maps.Geocoder();
 	var address = "서울특별시 종로구";
 	   geocoder.geocode(
	   		{ 'address': address }
	   		, function(results, status) {
				if (status == 'OK') {
					latlng = results[0].geometry.location;
					
				} else {
	   				alert('Geocode was not successful for the following reason: ' + status);
	   			}
	   		}
	   );
    
		map.setCenter(latlng);
		var marker = new google.maps.Marker({ 
			map: map,
			position: latlng
			});
		map.setZoom(15);
}
</script>
</html>