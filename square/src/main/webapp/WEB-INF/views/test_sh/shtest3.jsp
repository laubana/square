<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
	<script src=resources/Basic/assets/js/jquery-3.3.1.min.js></script>
	

<!-- 맵 띄우기 위한 스크립트 -->
<script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCdC1Oa4xE2ub87g1ouqeRxqapzLLg4shg&callback=initMap&language=ja&region=JP">
</script>
<script>
	var map;
	var geocoder;

	function initMap() {
	    var latlng = new google.maps.LatLng(35.6766907, 139.77003390000004);
	    var mapOptions = {
	    	      zoom: 15,
	    	      center: latlng
	    	    }
		map = new google.maps.Map(document.getElementById('map'), mapOptions);
		geocoder = new google.maps.Geocoder();
	}
	
	
	///////////////주소로 검색
	function codeAddress(address) {
	var result_area = "not_found";
    var address = document.getElementById('address').value;
	geocoder.geocode(
		{ 'address': address }
	   		, function(results, status) {
				if (status == 'OK') {
					map.setCenter(results[0].geometry.location);
					map.setZoom(12);
					var marker = new google.maps.Marker({ 
						map: map,
						position: results[0].geometry.location
				    });
					$('#lat').val( results[0].geometry.location.lat );
					$('#lng').val( results[0].geometry.location.lng );
					for( m = 0; m < results[0].address_components.length; m = m +1 ){ ////3번 시작
						if( results[0].address_components[m].types[0] == "administrative_area_level_2" )
							{ 	
								result_area = results[0].address_components[m].long_name;
								$('#region').val(result_area);
								console.log('return check: ' + result_area);								
								return;
							}
					}//3번 끝
					for( m = 0; m < results[0].address_components.length; m = m +1 ){ ////4번 시작
						if( results[0].address_components[m].types[0] == "administrative_area_level_1" )
							{ 	
								result_area = results[0].address_components[m].long_name;
								$('#region').val(result_area);
								console.log('return check: ' + result_area);								
								return;
							}
					}//4번 끝
					return result_area;
				} else {
	   				alert('Geocode was not successful for the following reason: ' + status);
	   			}
	   		}
	   );
	 } 
	
</script>
	
	</head>
<body>
<div id="map" ></div>
<div id = "output_button"></div>
 <div>
    <input id="address" type="text" value="東京">
    <input type="button" value="検索" onclick="codeAddress()">
  </div>
   <input type = "text" value = "" id = "lat">
    <input type = "text" value = "" id = "lng">
    <input type = "text" value = "" id = "region">
</body>
</html>