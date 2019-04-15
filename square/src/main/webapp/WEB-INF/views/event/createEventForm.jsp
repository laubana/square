<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<!--
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


	<body class="is-preload">

		<!-- Header 메인 바 -->
			<header id="header1">
				<h1><a href="main">2조</a></h1>
				<nav>
					<ul>
						<li><a href="joinUserForm">회원가입</a></li>
						<li><a href="createGroupForm">그룹생성</a></li>
						<li><a href="loginUserForm">로그인</a></li>
					</ul>
				</nav>
			</header>

		<!-- Header -->
			<section id="header">
				<header>
					<span class="image avatar"><img src="resources/image/group_logo/${group.group_logo}" alt="" /></span>
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
										<p><small>리더 이미지 자리</small></p><img src="resources/image/user_image/${leader.image_id}" alt="" />
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
									<div id="map" ></div>
									<div align="right">
										<div id = "place_output"></div>
									</div>
									<div id = "output_button"></div>
										<input type = "text" id = "search_addr" value = "東京　京橋駅">
										<input type = "button" id = "button_mapsearch" value = "検索" onClick = "codeAddress()">
									<div align="right">
									<footer>
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
	<script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCdC1Oa4xE2ub87g1ouqeRxqapzLLg4shg&callback=initMap&language=ja&region=JP">
	</script>
	
	
	<script>
	var map;
	var geocoder;

	function initMap() {
		console.log( 'test1' );

	    var latlng = new google.maps.LatLng(35.6766907, 139.77003390000004);
	    var mapOptions = {
	    	      zoom: 15,
	    	      center: latlng
	    	    }
		map = new google.maps.Map(document.getElementById('map'), mapOptions);
	    console.log( 'test2' );
		geocoder = new google.maps.Geocoder();
	 	var address = '東京　京橋駅';
		geocoder.geocode(
		   		{ 'address': address }
		   		, function(results, status) {
					if (status == 'OK') {
						console.log( 'test3' );
						latlng = results[0].geometry.location;
						map.setCenter(latlng);
						console.log( 'test4' );	    
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
	
	////////////주소 던져주면 맵 중앙에 띄우는 함수
	function setAddress(address) {
	var result_area = "not_found";
	    geocoder.geocode(
	   		{ 'address': address }
	   		, function(results, status) {
				if (status == 'OK') {
					map.setCenter(results[0].geometry.location);
					var marker = new google.maps.Marker({ 
						map: map,
						position: results[0].geometry.location
						});
					///결과 값들 중에 지역 이름 찾는 함수
					for( m = 0; m < results[0].address_components.length; m = m +1 ){ ////3번 시작
						if( results[0].address_components[m].types[0] == "administrative_area_level_2" )
							{ 	
								console.log('2: '+ results[0].address_components[m].types[0]);								
								str1 = String( results[0].address_components[m].long_name );
								result_area = str1;
								map.setZoom(15);
								return result_area;
							}
						
					}//3번 끝
					console.log(result_area + 'return check');								
					map.setZoom(15);
					return result_area;
				} else {
	   				alert('Geocode was not successful for the following reason: ' + status);
	   			}
	   		}
	   );
	 } 
	
	
	///////////////주소로 검색
	function codeAddress() {
		var result_area = "not_found";
		var address = document.getElementById('search_addr').value;
		console.log(address);
		    geocoder.geocode(
		   		{ 'address': address }
		   		, function(results, status) {
					if (status == 'OK') {
						map.setCenter(results[0].geometry.location);
						var marker = new google.maps.Marker({ 
							map: map,
							position: results[0].geometry.location
							});
						map.setZoom(15);
						return result_area;
					} else {
		   				alert('Geocode was not successful for the following reason: ' + status);
		   			}
		   		}
		   );
	} 
	
	
	
	
	///////////////배열 중복 없애는 함수
	var arealist = ${requestScope.place_list};
	var arealist_temp = new Array();
	var flag_same = false;
	var i=0;
	var j=0;
	var k=0;
		
		arealist_temp.push(arealist[0]);
		
		for(i = 0; i < arealist.length; i = i + 1){
			flag_same = false;	
			for(j = 0; j < arealist_temp.length; j = j + 1){
				if(arealist[i]  == arealist_temp[j]){
				console.log('i: ' + i + '   j: ' + j);
					
					flag_same = true;	
					break;
				}
				
			}
			if(!flag_same){
				arealist_temp.push(arealist[i]);
				
			}
		}	
		
		
var acute = "'";
var str_output = "";
var output_button = document.getElementById('output_button"');
		//////////중복이 제거된 배열 값들을 각각 꺼내와서 body에 버튼으로 채워주기
		for(k = 0; k < arealist_temp.length; k = k + 1){
			console.log( JSON.stringify( arealist_temp[k] ).replace(/&quot;/gi, '') + k );
			
			str_output = str_output + 
			'<input type = "button" id = "button' + k + '" value = "'+ arealist_temp[k] + '" onClick = "setAddress('+ acute + arealist_temp[k]+ acute + ')">';			
		}
		console.log( str_output );		
		$('output_button').html(str_output);			
		document.getElementById('output_button').innerHTML= str_output;
	
</script>
</html>