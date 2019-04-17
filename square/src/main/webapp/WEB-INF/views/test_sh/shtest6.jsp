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
    <script>

      function initMap() {

        var map = new google.maps.Map(document.getElementById('map'), {
          zoom: 3,
          center: {lat: -28.024, lng: 140.887}
        });

        var labels = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ';
        var markers = locations.map(function(location, i) {
          return new google.maps.Marker({
            position: location,
            label: labels[i % labels.length]
          });
        });

        var markerCluster = new MarkerClusterer(map, markers,
            {imagePath: 'https://developers.google.com/maps/documentation/javascript/examples/markerclusterer/m'});
      }
      var locations = [
        {lat: -31.563910, lng: 147.154312},
        {lat: -33.718234, lng: 150.363181},
        {lat: -33.727111, lng: 150.371124},
        {lat: -33.848588, lng: 151.209834},
        {lat: -33.851702, lng: 151.216968},
        {lat: -34.671264, lng: 150.863657},
        {lat: -35.304724, lng: 148.662905},
        {lat: -36.817685, lng: 175.699196},
        {lat: -36.828611, lng: 175.790222},
        {lat: -37.750000, lng: 145.116667},
        {lat: -37.759859, lng: 145.128708},
        {lat: -37.765015, lng: 145.133858},
        {lat: -37.770104, lng: 145.143299},
        {lat: -37.773700, lng: 145.145187},
        {lat: -37.774785, lng: 145.137978},
        {lat: -37.819616, lng: 144.968119},
        {lat: -38.330766, lng: 144.695692},
        {lat: -39.927193, lng: 175.053218},
        {lat: -41.330162, lng: 174.865694},
        {lat: -42.734358, lng: 147.439506},
        {lat: -42.734358, lng: 147.501315},
        {lat: -42.735258, lng: 147.438000},
        {lat: -43.999792, lng: 170.463352}
      ]
    </script>
    
    <!-- 맵 띄우는 스크립트 -->

<!-- 東京　京橋駅 : { 35.6766907 , 139.77003390000004 } -->
<script>
function initMap() {
    var latlng = new google.maps.LatLng(35.6715003, 139.764913);
    var mapOptions = {
    	      zoom: 15,
    	      center: latlng
    	}
    var map = new google.maps.Map(document.getElementById('map'), mapOptions);
	var geocoder = new google.maps.Geocoder();
	

	/* 서버에서 리스트 받아서 올띄우기 */
 	/* var address = ${requestScope.event_schedule_list}; */
 	
 	   /* /////////////여기부터 다음 */
	/* 	console.log( JSON.Stringify(${requestScope.event_schedule_list[0].latitude}) ); 
		
 	[{"lat":35.6693907,"lng":139.76803390000003,"region":"東京"},
 		{"lat":35.66676907,"lng":139.757390000004,"region":"東京"},
 		{"lat":35.6685256,"lng":139.7679124,"region":"東京"},
 		{"lat":35.67016907,"lng":76203390000004,"region":"東京"},
 		{"lat":35.67002907,"lng":139.7685339000003,"region":"東京"},
 		{"lat":35.67106907,"lng":139.762133900004,"region":"東京"},
 		{"lat":35.6759907,"lng":139.7707339000004,"region":"東京"},
 		{"lat":35.6766907,"lng":77013380004,"region":"東京"},
 		{"lat":67556907,"lng":139.7699033257,"region":"東京"},
 		{"lat":35.67606907,"lng":139.77113941000005,"region":"東京"},
 		{"lat":35.67506907,"lng":139.77044100004,"region":"東京"},
 		{"lat":35.67526907,"lng":139.76835000004,"region":"東京"},
 		{"lat":35.6681907,"lng":139.7601033333004,"region":"東京"},
 		{"lat":35.66726907,"lng":139.7598539004,"region":"東京"},
 		{"lat":35.66956907,"lng":139.76103390000003,"region":"東京"},
 		{"lat":35.6691329,"lng":139.7693181,"region":"東京"}]
 	
		*/
	 	var locations = [];
 	
		<c:forEach items = "${requestScope.event_schedule_list}" var = "list">
			locations.push({ lat: ${list.latitude}, lng: ${list.longitude}, region: "${list.region}", name: "${list.name}", event_schedule_id: ${list.event_schedule_id}  });
		</c:forEach>
	 		///////////라벨링은 schedule_id 받아서 넣고 window 내용에 name 넣기

		var labels = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ';
		
		/* 좌표 각각들에 대해 마커 객체를 생성해서 맵에 띄우는 함수  */
		var markers = locations.map( function(location, i) {
			return new google.maps.Marker({	
					position: new google.maps.LatLng(locations[i].lat, locations[i].lng),
					/* label: locations[i].name */
				});
			});		
		
		
		/* 마커 배열로 클러스터 찍어주는 함수  */
       var markerCluster = new MarkerClusterer(map, markers,
           {imagePath: 'https://developers.google.com/maps/documentation/javascript/examples/markerclusterer/m'});
       
       
       /* 마커 클릭했을 때 위에 툴팁 띄워주는 함수 
       var infowindow = new google.maps.InfoWindow({
	          content: 'test',
	          maxWidth: 200
	        });
       
     	 마커에 클릭 이벤트 걸어주는 함수      
       marker.addListener('click', function() {
           infowindow.open(map, marker);
         }); */
       
		/* 마커 배열에 있는 마커 객체들 하나하나 꺼내서 클릭 이벤트 걸어주는 함수 */     
		markers.map( function(marker, i) {
			
			var infowindow = new google.maps.InfoWindow({
		          content:  '<img src = "resources/images/clustering/samplepng/' + i + '.png">' +'<br>' +  locations[i].name,
		          maxWidth: 250
		        });
			  marker.addListener('click', function() {
					infowindow.open(map, marker);
				});
			});
       
		
       
}

</script>
<script src="https://developers.google.com/maps/documentation/javascript/examples/markerclusterer/markerclusterer.js">
    </script>
<script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCdC1Oa4xE2ub87g1ouqeRxqapzLLg4shg&callback=initMap&language=ja&region=JP">
    </script>
  </body>
</html>