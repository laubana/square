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
	<script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCdC1Oa4xE2ub87g1ouqeRxqapzLLg4shg&callback=initMap">
	</script>
	

<script>
//////////////////////////////////////////////////좌표 받고 지역 넘겨주는 함수
	function codeAddress(address) {
	var result_area = "not_found";
	    geocoder.geocode(
	   		{ 'address': address }
	   		, function(results, status) {
				if (status == 'OK') {
					map.setCenter(results[0].geometry.location);
					map.setZoom(11);
					var marker = new google.maps.Marker({ 
						map: map,
						position: results[0].geometry.location
				    });
					for( m = 0; m < results[0].address_components.length; m = m +1 ){ ////3번 시작
						if( results[0].address_components[m].types[0] == "administrative_area_level_2" )
							{ 	
								console.log('2: '+ results[0].address_components[m].types[0]);								
								str1 = String( results[0].address_components[m].long_name );
								result_area = str1;
								return result_area;
							}
						
					}//3번 끝
					console.log(result_area + 'return check');								
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
<div id = "output_test">
	<input type = "button" id = "button0" value = "광주광역시 북구" onClick = "codeAddress('광주광역시 북구')">
	<input type = "button" id = "button1" value = "서울특별시 종로구" onClick = "codeAddress(서울특별시 종로구)">
	<input type = "button" id = "button2" value = "seoul" onClick = "codeAddress(seoul)">
	<input type = "button" id = "button3" value = "강원도 춘천" onClick = "codeAddress(강원도 춘천)">
</div>

</body>


<script>
	///////////본 스크립트가 div 태그들 보다 나중에 있어야 함. 위에 head에 있으면, 본 script 부터 실행되므로, body에 있는 div들을 찾지 못함. 없다고 뜸. null이래
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
	
	
	///////////////배열 중복 없애는 함수
	var coodlist = ${requestScope.coodlist};
	var coodlist_temp = new Array();
	var flag_same = false;
	var i=0;
	var j=0;
	var k=0;
		
		coodlist_temp.push(coodlist[0]);
		
		for(i = 0; i < coodlist.length; i = i + 1){
			flag_same = false;	
			for(j = 0; j < coodlist_temp.length; j = j + 1){
				if(coodlist[i]  == coodlist_temp[j]){
				console.log('i: ' + i + '   j: ' + j);
					
					flag_same = true;	
					break;
				}
				
			}
			if(!flag_same){
				coodlist_temp.push(coodlist[i]);
				
			}
		}	
		
		
var acute = "'";
var str_output = "";
var output_button = document.getElementById('output_button"');
		//////////중복이 제거된 배열 값들을 각각 꺼내와서 body에 버튼으로 채워주기
		for(k = 0; k < coodlist_temp.length; k = k + 1){
			console.log( JSON.stringify( coodlist_temp[k] ).replace(/&quot;/gi, '') + k );
			
			str_output = str_output + 
			'<input type = "button" id = "button' + k + '" value = "'+ coodlist_temp[k] + '" onClick = "codeAddress('+ acute + coodlist_temp[k]+ acute + ')">';			
		}
		console.log( str_output );		
		$('output_button').html(str_output);			
		document.getElementById('output_button').innerHTML= str_output;
	
</script>
</html>