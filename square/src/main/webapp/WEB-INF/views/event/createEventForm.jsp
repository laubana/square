<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<!--
192 233 행 주석 처리 해놓음
	Read Only by HTML5 UP
	html5up.net | @ajlkn
	Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->
<html>
	<head>
		<title>createEventForm</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="resources/EventView/assets/css/main.css" />
		<link rel="stylesheet" href="resources/GroupMain/assets/css/main.css" />
		<link rel="stylesheet" href="resources/TextA/css/style.css">
		<script src=resources/Basic/assets/js/jquery-3.3.1.min.js></script>
	</head>

	<script>
	function createEventImage()
	{
		document.getElementById("file").click();
	}
	
	function uploadFirstEventImage()
	{
		var x = document.createElement("INPUT");
		x.setAttribute("type", "file");
		document.getElementById("album").appendChild(x);
	}
	</script>
	/
	<!-- 구글 맵스 위한 style 태그. 다른 요소 적용할 style이 있다면 style 태그를 별도로 만들어주기 바람 -->
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

	<!-- *세현: 구글 맵스 위한 스크립트. 다른 기능을 위한 함수를 추가하려면 태그를 따로 마련해주기 바람. -->
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

	
<%-- 	
		function codeAddress() {
		맵 필수 변수 말고, 내가 쓸려고 만든 변수들
		var arealist = []; 맵스에 좌표 보내주면 지역 이름 넘겨줄 건데, 그 지역 이름들을 모아서 넣어둘 배열
		var areacnt = []; 그 지역 이름이 몇 번 나왔나 빈도수를 저장할 배열 (list와 배열 첨자가 같아야 함,주의)
		var index_list = 0; 지역 이름 목록의 첨자 변수
		var index_cnt = 0; 지역 이름들의 빈도수를 저장한 배열의 첨자 변수
		var max_cnt = 0; 빈도수 중에 가장 자주 나온 지역 이름의 위치를 기억할 첨자 변수
		var output = document.getElementById('output');  출력해줄 div의 객체 접근 변수
		var output1 = document.getElementById('output1'); 그냥 연습용 출력 div
		var flag = false; 맵스가 반환해준 지역 이름이, 이미 목록에 있는지 여부를 확인하는 flag
		var str1 = "HelloBigWorld!"; 맵스에게 좌표를 보내주면 지역 이름을 반환해주는데, 그 지역 이름을 임시로 저장할 string
		var totalflag = false; for구문 전체가 다 돌았는지 확인할 flag
					(이거는 javascript가 쓰레드 방식으로 작동해서, for구문이 다 끝날 때까지 기다렸다 실행해야 함.
					그래서 flag 세워놓고 setinterval로 계속 반복해서 확인 함. flag 세워질 때까지
	    var address = document.getElementById('address').value; 사용자가 검색하기 위해 입력해 놓은 검색어를 받아오기 위한 객체 접근 변수
	    var smallflag = false; 맵스가 반환해주는 요소 중에 administrative_area_level_2 요소가 있는지 여부를 확인할 flag
	    
	    좌표 목록. 무료 키는 5개 밖에 안됨
		var coodlist = [
			{lat: -37.774785, lng: 145.137978},
			{lat: -37.819616, lng: 144.968119},
			{lat: -38.330766, lng: 144.695692},
			{lat: -39.927193, lng: 175.053218},
			{lat: -41.330162, lng: 174.865694}
		];
	    
		/* 변수들 초기화 */
		arealist[0] = str1;
		areacnt[0] = 0;
		var latlng = {lat: -37.774785, lng: 145.137978};

		
		
		/* 좌표들의 목록들을 하나씩 꺼내오면서 아래의 함수 실행 */
		for( i = 0; i < coodlist.length; i = i + 1) {
			latlng.lat = coodlist[i].lat;
			latlng.lng = coodlist[i].lng;
			console.log('forcheck'+ i + ': ' + console.log('check'+ i + JSON.stringify(latlng).replace(/&quot;/gi, '')) );

			/* 구글맵 지원 기능. 좌표를 보내면 행정 구역 반환 */
			geocoder.geocode({'location': latlng }
		   		, function(results, status) {
		   			
					if (status == 'OK') {
							flag = false;
						smallflag = false;
						for( m = 0; m < results[0].address_components.length; m = m +1 ){
						console.log('1-'+ i + ': ' + console.log('check'+ i + JSON.stringify(latlng).replace(/&quot;/gi, '')) );
							
							/* 이 부분은 받아올 행정 구역의 규모를 설정함. 이 부분은 우리가 정하는 게 아니라,
							구글에서 자체적으로 정해 놓은 변수를 써야 하기 때문에, 구글 맵스 api 페이지나 
							검색 결과인 results의 sample을 보고 결정하길 바람. */
									console.log('2-'+ i + ': '+ results[0].address_components[m].types[0]);								
							if( results[0].address_components[m].types[0] == "administrative_area_level_2" )
								{ 	
									str1 = String( results[0].address_components[m].long_name );
									/* 검색 결과로 반환해 준 행정구역이, 이 전 검색 결과들 중에 이미 있는지 확인 */
									smallflag = true;
									break; }
								}
							/* 이 부분은 그냥 디버깅용 output. */
							output1.innerHTML = arealist[0];
							/* 이 전의 검색 결과에 없는 새로운 행정 구역이라면, 검색 결과 기록에 담아 주기 */
							if(smallflag){
								for( j = 0; j <= arealist.length; j = j + 1 ){
									if( str1 == arealist[j] ) {
										
										console.log('3-'+ i + ': ' + arealist[j]);
										index_list = j;
										areacnt[j] = areacnt[j] + 1;
										flag = true;
										break;
									}
								}
								if(!flag){
									console.log('4-'+ i + ': '+str1);
									arealist.push(str1);
									areacnt.push(1);
								}
							}
		   			} else {
		   				alert('Geocode was not successful for the following reason: ' + status);
		   			}
		   		}
		   );
			/* 이 작업이 순차적으로 진행되는 게 아니라 스레드를 통해 병행 처리 되기 때문에, 실행이 제대로 안 됨.
			따라서 앞의 처리들이 완료 됐는지 확인해 줄 flag */
			totalflag = true;
		}
--%>

function codeAddress() {
	var arealist = []; 
	var areacnt = []; 
	var index_list = 0; 
	var index_cnt = 0; 
	var max_cnt = 0; 
	var output = document.getElementById('output');  
	var output1 = document.getElementById('output1');
	var flag = false; 
	var str1 = "HelloBigWorld!";
	var totalflag = false;
    var address = document.getElementById('address').value; 
    var smallflag = false; 
    
	var coodlist = [
		{lat: -37.774785, lng: 145.137978},
		{lat: -37.819616, lng: 144.968119},
		{lat: -38.330766, lng: 144.695692},
		{lat: -39.927193, lng: 175.053218},
		{lat: -41.330162, lng: 174.865694}
	];
    
	/* 변수들 초기화 */
	arealist[0] = str1;
	areacnt[0] = 0;
	var latlng = {lat: -37.774785, lng: 145.137978};
	
	
		/* 이 부분은 유환이가 짜 준 코드. 잘 모르겠으면 유환이한테 다시 한 번 물어볼 것. */
		var temp_cnt;
		var temp_list;
		var interval = setInterval(function()
				{
					if(totalflag)
					{
						for(k = 0; k < areacnt.length-1; k = k + 1){
							/* if( max_cnt <= areacnt[k] ){
								max_cnt = areacnt[k];
								index_cnt = k;
							} */
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
						output.innerHTML = arealist[index_cnt];
						clearInterval(interval);
					}
				}, 100);
 
		var intervalcnt = 0;
		var interval = setInterval(function(){
			console.log('forcheck'+ intervalcnt + ': ' + console.log('check'+ intervalcnt + JSON.stringify(latlng).replace(/&quot;/gi, '')) );
			
						
			intervalcnt = intervalcnt + 1;
			if (intervalcnt > 4){
				totalflag = true;
				clearInterval(interval2);

			}
			
		}, 100);

		}	
		
		
		
		
		var temp_cnt;
		var temp_list;
		var interval = setInterval(function()
				{
					if(totalflag)
					{
						for(k = 0; k < areacnt.length-1; k = k + 1){
							/* if( max_cnt <= areacnt[k] ){
								max_cnt = areacnt[k];
								index_cnt = k;
							} */
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
						output.innerHTML = arealist[index_cnt];
						clearInterval(interval);
					}
				}, 100);


		
</script>
	
	
	
	
	
	
	
	
	
	<body class="is-preload">

		<!-- Header 메인 바 -->
			<header id="header1">
				<h1><a href="main">2조</a></h1>
				<nav>
					<ul>
						<li><a href="#">회원가입</a></li>
						<li><a href="#">그룹생성</a></li>
						<li><a href="login">로그인</a></li>
					</ul>
				</nav>
			</header>

		<!-- Header -->
			<section id="header">
				<header>
					<%-- <span class="image avatar"><img src="resources/image/group_logo/${group.group_logo}" alt="" /></span> --%>
					<h1 id="logo"><a href="viewGroupForm?group_id=${group.group_id}">${group.name}</a></h1>
					<p style="font-size:15px;">
					<c:forEach var="group_hashtag" items="${group_hashtag_list}">
							#${group_hashtag.hashtag}
						</c:forEach>
					</p>
				</header>
				<nav id="nav">
					
				</nav>
				<footer>
					<ul class="icons">
						<li><a href="#" class="icon fa-twitter"><span class="label">Twitter</span></a></li>
						<li><a href="#" class="icon fa-facebook"><span class="label">Facebook</span></a></li>
						<li><a href="#" class="icon fa-instagram"><span class="label">Instagram</span></a></li>
						<li><a href="#" class="icon fa-github"><span class="label">Github</span></a></li>
						<li><a href="#" class="icon fa-envelope"><span class="label">Email</span></a></li>
					</ul>
				</footer>
			</section>

		<!-- Wrapper -->
			<div id="wrapper">

				<!-- Main -->
					<div id="main">

						<!-- 그룹의 설립일 등 기본 정보 -->
							<section id="five">
								<article class="post">
								<header>
								<!-- 타이틀 생성 -->
									<div class="title">
									<input type="text" class="Event_title "id="Event_title" placeholder="groupTitle">
									</div>
									<div class="meta">
								<!-- 그룹 설립일 자동입력 -데이터 베이스에 sysdate 오면 session으로 받아올 예정-->
										<time class="published" datetime="2019-04-08">DB에서 sysdate 찍어줌</time> 
										<a href="viewUserForm?user_id=${leader.user_id}" class="author"><small>리더 아이디 자리</small></a>
										<span class="name">${leader.name}</span><p><small>리더 이름 자리</small></p>
										<%-- 	<p><small>리더 이미지 자리</small></p><img src="resources/image/user_image/${leader.image_id}" alt="" /> --%>
									</div>
								</header>
								<!-- 그룹 대표 이미지 업로드 -->
								<div align="right"><footer>
									<label><input type="file" id="file"></label>
									<input type="button" class="createEventImage" id="createEventImage" value="그룹 이미지 등록" onclick="createEventImage()">		
								</footer></div>
							</article>
							</section>
					</div>		

		
							
				<!-- google maps-->
							<section id="section_map">
								<article class="post">
								<p>여기는 p태그
								</p>
									<div id="map" >
									</div>
									<div align="right">
										<div id = "place_output"></div>
									</div>
									<input type = "text" id = "address" value = "seoul">
									<input type = "button" id = "button_mapsearch" value = "검색" onClick = "codeAddress()">
									<div align="right">
									<div id = "output"></div>
									<div id = "output1"></div>
									<footer>
										오른쪽 아래에 버튼
									</footer>
								</div>
							</article>
							</section>

						<!-- 그룹의 설립일 등 기본 정보 -->
							<div id="album">
							<section id="six">
								<footer><div id="album" class="container" >
									<h3>앨범</h3>
									<p>우리 그룹을 표현할 첫 사진을 넣어보세요</p>
																			
									<button onclick="uploadFirstEventImage()">사진 더 추가</button>		
								</div></footer>
							</section>
						</div><br><br>
								
				<!-- Footer -->
					<section id="footer">
						<div class="container">
							<ul class="copyright">
								<li>&copy; Untitled. All rights reserved.</li><li>Design: <a href="http://html5up.net">HTML5 UP</a></li>
							</ul>
						</div>
					</section>

		</div>
		<!-- 기본 Scripts -->
		<script src="resources/Basic/assets/js/jquery-3.3.1.min.js"></script>
		<!-- 추가 Scripts -->
			<script src="resources/MyPage/assets/js/jquery.poptrox.min.js"></script>
			<script src="resources/MyPage/assets/js/browser.min.js"></script>
			<script src="resources/MyPage/assets/js/breakpoints.min.js"></script>	
			<script src="resources/GroupMain/assets/js/main2.js"></script>
		<!-- Scripts -->
			<script src="resources/GroupMain/assets/js/jquery.min.js"></script>
			<script src="resources/GroupMain/assets/js/jquery.scrollex.min.js"></script>
			<script src="resources/GroupMain/assets/js/jquery.scrolly.min.js"></script>
			<script src="resources/GroupMain/assets/js/browser.min.js"></script>
			<script src="resources/GroupMain/assets/js/breakpoints.min.js"></script>
			<script src="resources/GroupMain/assets/js/util.js"></script>
			<script src="resources/GroupMain/assets/js/main.js"></script>

	</body>
</html>