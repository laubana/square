<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
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

	
var list_cnt = 0;
function codeAddress() { ///1번 시작 괄호
	
	var arealist = []; 
	var areacnt = []; 
	var index_list = 0; 
	var index_cnt = 0; 
	var max_cnt = 0; 
	var list_flag = false; 
    var smallflag = false; 
	var totalflag = false;
	var str1 = "HelloBigWorld!";
	var result_geo = { value: 'HelloWorld!', flag: false };
	var output = document.getElementById('output');  
	var coodlist = [
		{lat: -37.774785, lng: 145.137978},
		{lat: -37.819616, lng: 144.968119},
		{lat: -38.330766, lng: 144.695692},
		{lat: -39.927193, lng: 175.053218},
		{lat: -41.330162, lng: 174.865694}
	];
	
		console.log('before:'+ result_geo.value);
		result_geo = geo_getarea(coodlist[0]);
		result_geo.flag = false;
		console.log('after:'+ result_geo.value);
		
			/* 변수들 초기화 */
			arealist[0] = str1;
			areacnt[0] = 0;
			var whileflag = true;
			var interval = setInterval(function() //2번 시작 괄호
				console.log('before1' + list_cnt);
				if(whileflag = true){ result_geo = geo_getarea(coodlist[list_cnt]); whileflag = false; }
				console.log('after1' + list_cnt);
				
					//이거 자꾸 쓰레드로 진행해서 flag로 
					if(result_geo.flag){ ///3번 시작 괄호
						//보내준 결과 중에, 이미 나온 결과랑 있는지 확인
						console.log('length:' + arealist.length);												
						for( j = 0; j <= arealist.length; j = j + 1 ){
							if( result_geo.value == arealist[j] ) {
								
								console.log('3-'+ arealist[j]);
								index_list = j;
								areacnt[j] = areacnt[j] + 1;
								
								//list_cnt = list_cnt + 1;
								list_flag = true;
								result_geo.flag = false;
								whileflag = true;
								break;
							}
						}
						if(!list_flag){
							console.log('3-+-'+ result_geo.value);

							arealist.push(result_geo.value);
							areacnt.push(1);
							
							//list_cnt = list_cnt + 1;
							result_geo.flag = false;
							list_flag = true;
							whileflag = true;
						}
					}/////////3번 끝 괄호
				
				if(list_cnt > 4){ ////4번 시작 괄호
				
					totalflag = true;					
					break;
				}///4번 끝 괄호
			
			
			}, 200);//2번 끝 괄호
			
	var interval = setInterval(function() while(true){////6번 시작 괄호
			if(totalflag){
				for(k = 0; k < areacnt.length-1; k = k + 1){
					/* 선택정렬 알고리즘 */
					if(areacnt[k] > areacnt[k+1]){
						temp_cnt = areacnt[k];
						areacnt[k] = areacnt[k+1];
						areacnt[k+1] = temp_cnt;
						
						temp_list = arealist[k];
						arealist[k] = arealist[k+1];
						arealist[k+1] = temp_list; 
					}
						console.log('5:' + arealist[k]);
				}
				/* 그래서 최종적으로 나온 행정구역들 중, 가장 자주 나온(cnt가 높은) 행정구역을 띄워 줌. */
				output.innerHTML = arealist[0];
				break;
			}
	}, 200);/////6번 끝 괄호	
}///1번 끝 괄호
//////////////////////////////////////////////////멤버 메소드

function geo_getarea(latlng_temp){
	var result_geo = { value: 'HelloWorld!', flag: false };
	/* 
	var latlng = {lat: -37.774785, lng: 145.137978};
			latlng.lat = latlng_temp.lat;
			latlng.lng = latlng_temp.lng;			
	 */
		console.log('forcheck: ' + JSON.stringify(latlng_temp).replace(/&quot;/gi, ''));
			geocoder.geocode({'location': latlng_temp }
		   		, function(results, status) {
					if (status == 'OK') {
						console.log('1: '+ JSON.stringify(latlng).replace(/&quot;/gi, ''));
						for( m = 0; m < results[0].address_components.length; m = m +1 ){
							if( results[0].address_components[m].types[0] == "administrative_area_level_2" )
								{ 	
									console.log('2: '+ results[0].address_components[m].types[0]);								
									str1 = String( results[0].address_components[m].long_name );
									result_geo.value = str1;
									result_geo.flag = true;
									break;
								}
							
						}
						list_cnt = list_cnt + 1;
					}
					else {
		   				alert('Geocode was not successful for the following reason: ' + status);
		   			}
		   		});
			
			
	return result_geo;
}



</script>


</head>
<body>
<div id = "map"></div>
<div><input type = "button" value = "test실행"  onClick = "codeAddress()">  </div>
<div id = "output"></div>
</body>
</html>