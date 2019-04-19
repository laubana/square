<%@ page import = "java.util.Date"%>
<%@ page import = "java.text.SimpleDateFormat"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language = "java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<!--
	Read Only by HTML5 UP
	html5up.net | @ajlkn
	Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->
<html>
	<head>
		<title>createEventScheduleForm</title>
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
			width: 750px;
			height: 500px;
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
				<h1><a class="navbar-brand font-weight-bolder mr-3" href="main"><img src="resources/Main/assets/css/images/photoSquareLogo_done.png"></a></h1>
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

						<!-- 정보 -->
							<section id="one">
								<article class="post">
								<header>
								<!-- 스케줄 타이틀 생성 -->
									<div class="title">
									<h2>스케줄 제목</h2>
									<input type="text" class="Event_title "id="Event_title" placeholder="schedule_Title" autocomplete="off">
									</div>
									<div class="meta">
								<!-- 날짜, 회원 이름, 회원 사진 -->
										<br><br><br><a href="viewUserForm?user_id=${user.user_id}" class="author"><span class="name">${user.name}</span><img src="resources/image/user_image/${user.image_id}" alt="" /></a>
									</div>
								</header>
								
									<!-- google maps-->
									<div align ="center">
									<div id="map" ></div>
									</div>
									<br>
									    <input id="address" type="text" value="東京　京橋駅"  autocomplete="off">
										<br>
									    <input type = "button" id = "button_mapsearch" value="検索" onclick="codeAddress()">
										<input type = "text" value = "" id = "lat">
										<input type = "text" value = "" id = "lng">
										<input type = "text" value = "" id = "region">
									<br><br>
									<!-- 내용 -->
									<h1>스케줄 내용</h1>
									
<input type="datetime-local" id="start_date" value="<%= (new SimpleDateFormat("yyyy-MM-dd'T'HH:mm")).format(new Date())%>">
<input type="datetime-local" id="end_date" value="<%= (new SimpleDateFormat("yyyy-MM-dd'T'HH:mm")).format(new Date(new Date().getTime() + 3600*1000))%>">
									<textarea class="comment-block" id="event_schedule_content" placeholder="내용을 작성해주세요..."></textarea><br>
									<br>
									<div align="center"><input type="button" value="이벤트 올리기" onclick="createEventScheduleAction()"></div>
								</article>
							</section>
					</div>		

						<!-- 그룹의 설립일 등 기본 정보 -->
							<!-- <div id="album">
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
																	
									<button onclick="uploadFirstEventImage()" class="button">Image More</button>		
								</div></footer>
							</section>
						</div><br><br> -->
								
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

<script>
function createEventScheduleAction()
{
	var map = {};
	map["name"] = $("#Event_title").val();
	map["content"] = $("#event_schedule_content").val();
	map["group_id"] = ${group.group_id};
	map["event_id"] = ${event.event_id};
	map["region"] = "R";
	map["latitude"] = "A";
	map["longitude"] = "L";
	map["start_date"] = $("#start_date").val();
	map["end_date"] = $("#end_date").val();
	
	var interval = setInterval(function()
			{
				if(typeof(map.region) != "undefined" && typeof(map.latitude) != "undefined" && typeof(map.latitude) != "undefined")
				{
					$.ajax({
		    			url: "createEventScheduleAction",
		    			type: "POST",
		    			data: JSON.stringify(map),
		    			contentType: "application/json; charset=UTF-8",
		    			success: function(result)
		    			{
		    				location.href("viewGroupForm?group_category=${group_category.group_category_id}&group_id=${group.group_id}}");
		    			},
		    			error: function(){}
		    				});
					clearInterval(interval);
				}
			},100);
	}
</script>
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
					map.setZoom(15);
					var marker = new google.maps.Marker({ 
						map: map,
						position: results[0].geometry.location
				    });
					$('#lat').val( results[0].geometry.location.lat );
					$('#lng').val( results[0].geometry.location.lng );
					for( m = 0; m < results[0].address_components.length; m = m +1 ){ ////3번 시작
						if( results[0].address_components[m].types[0] == "sublocality" )
							{ 	
								result_area = results[0].address_components[m].long_name;
								$('#region').val(result_area);
								console.log('return check4: ' + result_area);								
								return;
							}
					}//3번 끝
					for( m = 0; m < results[0].address_components.length; m = m +1 ){ ////4번 시작
						if( results[0].address_components[m].types[0] == "administrative_area_level_3" )
							{ 	
								result_area = results[0].address_components[m].long_name;
								$('#region').val(result_area);
								console.log('return check3: ' + result_area);								
								return;
							}
					}//4번 끝
					for( m = 0; m < results[0].address_components.length; m = m +1 ){ ////5번 시작
						if( results[0].address_components[m].types[0] == "administrative_area_level_2" )
							{ 	
								result_area = results[0].address_components[m].long_name;
								$('#region').val(result_area);
								console.log('return check2: ' + result_area);								
								return;
							}
					}//5번 끝
					for( m = 0; m < results[0].address_components.length; m = m +1 ){ ////6번 시작
						if( results[0].address_components[m].types[0] == "administrative_area_level_1" )
							{ 	
								result_area = results[0].address_components[m].long_name;
								$('#region').val(result_area);
								console.log('return check1: ' + result_area);								
								return;
							}
					}//6번 끝
					for( m = 0; m < results[0].address_components.length; m = m +1 ){ ////6번 시작
						if( results[0].address_components[m].types[0] == "country" )
							{ 	
								result_area = results[0].address_components[m].long_name;
								$('#region').val(result_area);
								console.log('return check0: ' + result_area);								
								return;
							}
					}//6번 끝
					return result_area;
				} else {
	   				alert('Geocode was not successful for the following reason: ' + status);
	   			}
	   		}
	   );
	 } 
	
</script>
</html>