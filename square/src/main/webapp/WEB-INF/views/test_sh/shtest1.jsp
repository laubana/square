<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<!-- 야 어떻게 할지 생각 났어.
좌표 던져주면 행정구역을 넘겨주는 함수를 따로 만들어 놓고,
위에랑 아래서 알아서 불러다 쓸 수 있도록 하기 뭔 말인지 알지? -->


	<style>
		html,body {height: 100%; margin: 0; padding: 0;}
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
	</style>

<script src=resources/Basic/assets/js/jquery-3.3.1.min.js></script>
<script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCdC1Oa4xE2ub87g1ouqeRxqapzLLg4shg&callback=initMap"></script>

<script>
$(document).ready(function() {

	var coodlist = [
		{lat: -41.330162, lng: 174.865694},
		{lat: -42.734358, lng: 147.439506},
		{lat: -42.734358, lng: 147.501315},
		{lat: -42.734358, lng: 147.501315},
		{lat: -43.999792, lng: 170.463352}
	];
	
	
	/* 이벤트 연결 코딩 예시 */
$('#button1').on('click',function() {} )
	
	
var str_output = "";
var str_temp;
for( i = 0; i < coodlist.length; i = i + 1 ){
	str_temp = '{' + String( coodlist[i].lat ) + ', '+ String( coodlist[i].lng ) + '}';
	str_output = str_output + 
	'<input type = "button" id = "button' + i + '" value = "'+ str_temp + '" onClick = "geocodeLatLng(' + coodlist[i].lat + ', ' + coodlist[0].lng + ')">';
	
}	
	
	/* 콘솔 확인 구문
		console.log(JSON.stringify(coodlist[i]).replace(/&quot;/gi, ''));
	좌표 꺼내서 문자열 변환 구문
		'{' + String( coodlist[i].lat ) + ', '+ String( coodlist[i].lng ) + '}'
	*/

$('#place_output').html(str_output);	
	
});



</script>

<script>
	var map;
	var geocoder;
	
	
	function initMap() {
	    geocoder = new google.maps.Geocoder();
	    var latlng = new google.maps.LatLng(-34.397, 150.644);
	    var mapOptions = {
	    	      zoom: 8,
	    	      center: latlng
	    	    }
	    map = new google.maps.Map(document.getElementById('map'), mapOptions);
	}
	
	function codeAddress(cood) {
	    var address = document.getElementById('address').value;
	    geocoder.geocode(
	   		{ 'address': address }
	   		, function(results, status) {
				if (status == 'OK') {
					map.setCenter(results[0].geometry.location);
					map.setZoom(12);
					var marker = new google.maps.Marker({ 
						map: map,
						position: results[0].geometry.location,
				    });
	   			} else {
	   				alert('Geocode was not successful for the following reason: ' + status);
	   			}
	   		}
	   );
	}
	
	function geocodeLatLng(lat1,lng1) {
		
		var input = String(lat1) + ', ' + lng1;
		var latlngStr = input.split(',', 2);
		var latlng = { lat: parseFloat(latlngStr[0]), lng: parseFloat(latlngStr[1]) };
		geocoder.geocode({'location': latlng}, function(results, status) {
			if (status === 'OK') {
				if (results[0]) {
					map.setZoom(11);
					var marker = new google.maps.Marker({
						position: latlng,
						map: map
					});
				} else {
					window.alert('No results found');
				}
			} else {
				window.alert('Geocoder failed due to: ' + status);
			}
		});
	}
	
</script>



</head>
<body>

<div id="map" ></div>
<div id = "place_output"></div>
	<input type = "text" id = "address">
	<input type = "button" id = "button1" value = "test1" onClick = "codeAddress()">
	<input type = "button" id = "button2" value = "test2" onClick = "test2()">
	<input type = "button" id = "button2" value = "test3" onClick = "test3()">
</body>
</html>