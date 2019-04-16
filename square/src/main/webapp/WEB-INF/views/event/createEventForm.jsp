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



<style>
.filebox label { 
display: inline-block; 
padding: .5em .75em; 
color: #999; 
font-size: inherit; 
line-height: normal; 
vertical-align: middle; 
background-color: #fdfdfd; 
cursor: pointer; 
border: 1px solid #ebebeb; 
border-bottom-color: #e2e2e2; 
border-radius: .25em; 
} 

.filebox input[type="file"] { /* 파일 필드 숨기기 */ 
position: absolute; 
width: 1px; 
height: 1px; 
padding: 0; 
margin: -1px; 
overflow: hidden; 
clip:rect(0,0,0,0); 
border: 0; 
}
</style>
	
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
				<span class="image avatar"><img src="resources/Main/images/logo/01.jpg" alt="" /></span>
					<h1 id="logo"><a href="groupMain">Group name</a></h1>
					<p style="font-size:15px;">#tag#tag</p>
					<span class="image avatar"><img src="resources/image/group_logo/${group.group_logo}" alt="" /></span>	
				</header>
				<nav id="nav">
					<ul>
						<li><a href="#one" class="active">이벤트 생성</a></li>
					</ul>
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
		
<!-- 폼 -->			
<form action="" method="post">
		<!-- Wrapper -->
			<div id="wrapper">

				<!-- Main -->
					<div id="main">

						<!-- 그룹의 설립일 등 기본 정보 -->
							<section id="one">
								<article class="post">
								<header>
								<!-- 타이틀 생성 -->
									<div class="title">
									<h2>이벤트 제목</h2>
									<input type="text" class="Event_title "id="Event_title" placeholder="event_Title">
									</div>
									<div class="meta">
								<!-- 그룹 설립일 자동입력 -데이터 베이스에 sysdate 오면 session으로 받아올 예정-->
										<time class="published" datetime="2019-04-08">2019년 4월 8일</time>
										<a href="viewUserForm?user_id=${leader.user_id}" class="author"><span class="name">Harry</span><img src="resources/GroupMain/images/member/c1.jpg" alt="" /></a>
									</div>
								</header>
								<span class="image featured"><img src="resources/GroupMain/images/bb.jpg" id="foo"></span>
								<!-- 그룹 대표 이미지 업로드 -->
									<div class="filebox">
									<label for="imgInp">Main Image Upload</label>
									<input type='file' id="imgInp" /></div>
									<br>
									<!-- 내용 -->
									<h1>이벤트 내용</h1>
									<textarea class="comment-block" placeholder="내용을 작성해주세요..."></textarea><br>
									<!-- google maps-->
									<div id="map" ></div>
									<div align="right">
										<div id = "place_output"></div>
									</div>
									<br>
									<div id = "output_button"></div>
										<input type = "text" id = "search_addr" value = "東京　京橋駅">
										<br>
										<input type = "button" id = "button_mapsearch" value = "検索" onClick = "codeAddress()">
									<div align="right">
									<footer>
									</footer>
								</div>
								</article>
							</section>
					</div>		

						<!-- 그룹의 설립일 등 기본 정보 -->
							<div id="album">
							<section id="six">
								<footer><div id="album" class="container" >
									<h3>추가 이벤트 사진</h3>
									<p>추가 이벤트 이미지를 넣어보세요</p>

									<div class="features">
										<article>
											<img src="resources/GroupMain/images/bb.jpg" class="image" id="foo2">
											<div class="inner">
												<h4>File_1</h4>
												<div class="filebox">
												<label for="imgInp2">Image Upload1</label>
												<input type='file' id="imgInp2" /></div>
											</div>
										</article>
										<article>
											<img src="resources/GroupMain/images/bb.jpg" class="image" id="foo3">
											<div class="inner">
												<h4>File_2</h4>
												<div class="filebox">
												<label for="imgInp3">Image Upload2</label>
												<input type='file' id="imgInp3" /></div>
											</div>
										</article>
										<article>
											<img src="resources/GroupMain/images/bb.jpg" class="image" id="foo4">
											<div class="inner">
												<h4>File_3</h4>
												<div class="filebox">
												<label for="imgInp4">Image Upload3</label>
												<input type='file' id="imgInp4" /></div>
											</div>
										</article>
										<article>
											<img src="resources/GroupMain/images/bb.jpg" class="image" id="foo5">
											<div class="inner">
												<h4>File_4</h4>
												<div class="filebox">
												<label for="imgInp5">Image Upload4</label>
												<input type='file' id="imgInp5" /></div>
											</div>
										</article>
											</div>
									
										<div align="center"><input type="submit" value="이벤트 올리기"></div>
																	
									<!-- <button onclick="uploadFirstEventImage()" class="button">Image More</button> -->		
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
</form>
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


<script type="text/javascript">
//파일 미리보기 메인
function readURL(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = function(e) {
            $('#foo').attr('src', e.target.result);
        }
        reader.readAsDataURL(input.files[0]);
    }
}

$("#imgInp").change(function() {
    readURL(this);
});

function readURL2(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = function(e) {
            $('#foo2').attr('src', e.target.result);
        }
        reader.readAsDataURL(input.files[0]);
    }
}

$("#imgInp2").change(function() {
    readURL2(this);
});

function readURL3(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = function(e) {
            $('#foo3').attr('src', e.target.result);
        }
        reader.readAsDataURL(input.files[0]);
    }
}

$("#imgInp3").change(function() {
    readURL3(this);
});

function readURL4(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = function(e) {
            $('#foo4').attr('src', e.target.result);
        }
        reader.readAsDataURL(input.files[0]);
    }
}

$("#imgInp4").change(function() {
    readURL4(this);
});

function readURL5(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = function(e) {
            $('#foo5').attr('src', e.target.result);
        }
        reader.readAsDataURL(input.files[0]);
    }
}

$("#imgInp5").change(function() {
    readURL5(this);
});
</script>
	
	
	
	<!-- 맵 띄우기 위한 스크립트 -->
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
								map.setZoom(14);
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