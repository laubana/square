<%@page import="java.util.HashMap"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="project.ppaya.square.vo.GroupComment"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<!--
	Read Only by HTML5 UP
	html5up.net | @ajlkn
	Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->
<html>
	<head>
		<title>GroupMain</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="resources/GroupMain/assets/css/main.css" />
		<link rel="stylesheet" href="resources/TextA/css/style.css">
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
		<script>
			function logoutUserAction()
			{
				$.ajax({
					url: "logoutUserAction",
					type: "POST",
					success: function()
					{
						location.replace("<c:out value='main'/>");
					},
					error: function(error){console.log(error);}
				});
			}
			function withdrawGroupAction()
			{
				map = {};
				map["user_id"] = "${sessionScope.user_id}";
				map["group_id"] = ${group.group_id};
				
				$.ajax({
					url: "withdrawGroupAction",
					type: "POST",
					data: JSON.stringify(map),
					contentType: "application/json; charset=UTF-8",
					success: function()
					{
						location.reload();
					},
					error: function(error){console.log(error);}
				});
			}
			function joinGroupAction()
			{
				map = {};
				map["user_id"] = "${sessionScope.user_id}";
				map["group_id"] = ${group.group_id};
				
				$.ajax({
					url: "joinGroupAction",
					type: "POST",
					data: JSON.stringify(map),
					contentType: "application/json; charset=UTF-8",
					success: function()
					{
						location.reload();
					},
					error: function(error){console.log(error);}
				});
			}
			function getGroupCommentTranslation(group_comment_id)
			{
				var map = {};
				map["group_comment_id"] = group_comment_id;
				map["language"] = document.getElementById("translation_language" + group_comment_id).value;
				
				$.ajax({
					url: "getGroupCommentTranslation",
					type: "POST",
					data: JSON.stringify(map),
					dataType: "JSON",
					contentType: "application/json; charset=UTF-8",
					success: function(jsonObject)
					{
						var result = decodeURIComponent(jsonObject.result.replace(/\+/g, " "));
						
						document.getElementById("comment" + group_comment_id).innerHTML = result;
						document.getElementById("translation_button" + group_comment_id).innerHTML = '<a href="javascript:resetGroupComment(' + group_comment_id + ')">リセット</a>';
					},
					error: function(error){console.log(error);}
				});
			}
			function resetGroupComment(group_comment_id)
			{
				var map = {};
				map["group_comment_id"] = group_comment_id;
				
				$.ajax({
					url: "resetGroupComment",
					type: "POST",
					data: JSON.stringify(map),
					dataType: "JSON",
					contentType: "application/json; charset=UTF-8",
					success: function(jsonObject)
					{
						var result = decodeURIComponent(jsonObject.result.replace(/\+/g, " "));
						
						document.getElementById("comment" + group_comment_id).innerHTML = result;
						document.getElementById("translation_button" + group_comment_id).innerHTML = '<a href="javascript:getGroupCommentTranslation(' + group_comment_id +')">翻訳</a>';
					},
					error: function(error){console.log(error);}
				});
			}
		</script>
	</head>
	<body class="is-preload" onload="fn_onload();">

		<!-- Header 메인 바 -->
			<header id="header1">
				<h1><a class="navbar-brand font-weight-bolder mr-3" href="main"><img src="resources/Main/assets/css/images/photoSquareLogo_done.png"></a></h1>
				<nav>
					<ul>
						<li><a href="listRecommendationForm"></a>
						<c:if test="${sessionScope.user_id != null}">
						<li>${sessionScope.user_id}</li>
						<li><a href="createGroupForm">그룹생성</a></li>
					<li><a href="javascript:logoutUserAction()">로그아웃</a></li>
						</c:if>
						<c:if test="${sessionScope.user_id == null}">
						<li><a href="joinUserForm">회원가입</a></li>
							<li><a href="loginUserForm">로그인</a></li>
						</c:if>
					</ul>
				</nav>
			</header>

		<!-- Header -->
			<section id="header">
				<header>
					<span class="image avatar"><img src="resources/image/group_logo/${group.group_logo}" alt="" /></span>
					<h1 id="logo"><a href="#">${group.name}</a></h1>
					<p style="font-size:15px;">
						<c:forEach var="group_hashtag" items="${group_hashtag_list}">
							#${group_hashtag.hashtag}
						</c:forEach>
					</p>
					<c:if test="${sessionScope.user_id != null}">
						<c:if test="${group_attendance != null}">
							<a href="javascript:withdrawGroupAction()" class="button">탈퇴</a>
						</c:if>
						<c:if test="${group_attendance == null}">
							<a href="javascript:joinGroupAction()" class="button">참여</a>
						</c:if>
					</c:if>
				</header>
				<nav id="nav">
					<ul>
						<li><a href="#one" class="active">グループ情報</a></li>
						<li><a href="#two">メンバー</a></li>
						<li><a href="#three">コメント</a></li>
						<li><a href="#four">アルバム</a></li>
						<li><a href="#five">イベント</a></li>
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

		<!-- Wrapper -->
			<div id="wrapper">

				<!-- Main 가:1280세:480-->
					<div id="main">

						<!-- One -->
							<section id="one">
								<div class="image main" data-position="center">
									<img src="resources/image/group_image/${group.group_image}" alt="" />
								</div>
								<div class="container">
									<h3>グループ情報</h3>
									<p>${group.content}</p>
								</div>
							</section>
							<section id="one">
								<div class="container">
									<div id="map" ></div>
								</div>
							</section>
						
						<!-- Two -->
							<section id="two">
								<div class="container">
									<h3>メンバー</h3>
									<p>リーダー</p>
										<div>
											<a href="viewUserForm?user_id=${leader.user_id}" class="image avatar thumb"><img src="resources/image/user_image/${leader.image_id}" alt="" style="width: 100px; height:auto;"></a>
										</div>
										<div>
										<br>
									<p>メンバー</p>
										<c:forEach var="user" items="${user_list}">
											<a href="viewUserForm?user_id=${user.user_id}" class="image avatar thumb"><img src="resources/image/user_image/${user.image_id}" alt="" style="width: 100px; height:auto;"></a>
										</c:forEach>
										</div>${user.size()}
										<br>
									<a href="listGroupAttendanceForm?group_category_id=${group_category.group_category_id}&group_id=${group.group_id}" class="button">メンバーページへ</a>
								</div>
							</section>
						
						<!-- Three -->
							<section id="three">
								<div class="container">
									<h3>コメント</h3>
									<p class="icon fa-comment" align="right">${comment_list.size()}</p>
						<div class="comments">
						<c:forEach var="element" items="${comment_list}" end="2">
						<div class="comment-wrap">
							<div>
							<a href="viewUserForm?user_id=${element.user.user_id}" class="image avatar thumb"><img src="resources/image/user_image/${element.user.image_id}" alt="" style="width: 100px; height:auto;"></a>
							</div>
							<div class="comment-block">
								<p class="comment-text" id="comment${element.comment.group_comment_id}">${element.comment.content}</p>
									<div class="bottom-comment">
										<div class="comment-date">
										
										<%= (new SimpleDateFormat("yyyy年 MM月 dd日 HH:mm:ss")).format(new Date(((GroupComment)(((HashMap)pageContext.getAttribute("element")).get("comment"))).getInput_date()))%>
										
										</div>
											<ul class="comment-actions">
												<li class="name"><a href="viewUserForm?user_id=${element.user.user_id}">${element.user.name}</a></li>
												<li class="name" id="translation_button${element.comment.group_comment_id}"><a href="javascript:getGroupCommentTranslation(${element.comment.group_comment_id})">翻訳</a></li>
												<c:if test="${element.user.user_id == sessionScope.user_id}">
													<li class="name">Edit</li>
													<li>Delete</li>
												</c:if><br>
												<li>
													<select id="translation_language${element.comment.group_comment_id}">
														  <option value="en">英語</option>
														  <option value="ko">韓国語</option>
													</select>
												</li>
								
											</ul>
									</div>
									<br><br><br>
									<div>
									<c:forEach var="tag" items="${element.tag_list}">
										<a href="viewMindMapForm?hashtag=${tag}">#${tag}</a>
									</c:forEach>
									</div>		
							</div>
						</div>
						</c:forEach>
						<textarea class="comment-block"></textarea><br>
						<div align="right"><input type="button" value="作成"></div><br><br>
						<a href="listGroupCommentForm?group_category_id=${group_category.group_category_id}&group_id=${group.group_id}" class="button">コメントページへ</a>
						
						</div>		
								</div>
							</section>
							
						<!-- Four -->
							<section id="four">
								<div class="container">
									<h3>アルバム</h3>
						
									<div class="features" align="center">
										<article class="col-6 col-12-xsmall work-item">
											<c:forEach var="element" items="${image_list}" end="3">
												<a href="resources/image/event_schedule_image/${element.image.filename}" class="image thumb"><img src="resources/image/event_schedule_image/${element.image.filename}" alt="" /></a>
											<h3 style="width:0px;height:0px;font-size:0px;line-height:0px;position:absolute;overflow:hidden;">${element.description}</h3>
											</c:forEach>
											<br>
											<c:if test="${video_list.size() != 0}">
											<video width='640' height='auto' controls>
											<c:forEach var="video" items="${video_list}">
											<source src='resources/image/event_schedule_video/${video.filename}' type='video/mp4'>
											</c:forEach>
											</video>
											</c:if>
											<br><br>
											<a href="listGroupAlbumForm?group_category_id=${group_category.group_category_id}&group_id=${group.group_id}" class="button">アルバムページへ</a>
										</article>
									</div>
								</div>
							</section>

						<!-- Five -->
							<section id="five">
								<div class="container">
									<h3>イベント</h3>
									<c:if test="${sessionScope.user_id != null}">
						<c:if test="${group_attendance != null}">
										<p align="right"><a href="createEventForm?group_category=${group_category.group_category_id}&group_id=${group.group_id}" class="button">追加</a></p>
						</c:if>
					</c:if>
									<div class="features">
									<c:forEach var="event" items="${event_list}" end="2">
									<article>
											<a href="viewEventForm?group_category_id=${group_category.group_category_id}&group_id=${group.group_id}&event_id=${event.event_id}" class="image"><img src="resources/image/event_image/${event.image_id}" alt="" /></a>
											<div class="inner">
												<h4>${event.name}</h4>
												<p>${event.content}</p>
											</div>
										</article>
										</c:forEach>
											</div>
										<a href="listEventForm?group_category_id=${group_category.group_category_id}&group_id=${group.group_id}" class="button">イベントページへ</a>
									</div>
									
							</section>
							<section id="six">
								<div class="container">
									<h3>연합 이벤트</h3>
									<div class="features">
									<c:forEach var="event_union_event" items="${event_union_event_list}">
									<article>
											<a href="viewEventForm?group_category_id=${group_category.group_category_id}&group_id=${group.group_id}&event_id=${event_union_event.event_id}" class="image"><img src="resources/image/event_image/${event_union_event.image_id}" alt="" /></a>
											<div class="inner">
												<h4>${event_union_event.name}</h4>
												<p>${event_union_event.content}</p>
											</div>
										</article>
										</c:forEach>
											</div>
										<a href="listEventForm?group_category_id=${group_category.group_category_id}&group_id=${group.group_id}" class="button">연합 이벤트 페이지 이동</a>
									</div>
							</section>
					</div>

				<!-- Footer -->
					<section id="footer">
						<div class="container">
							<ul class="copyright">
								<li>&copy; Untitled. All rights reserved.</li><li>Design: <a href="http://html5up.net">HTML5 UP</a></li>
							</ul>
						</div>
					</section>

			</div>

<script type="text/javascript">
//숨기기,보이기
var bDisplay = true;
function doDisplay1(){
    var con = document.getElementById("myDIV1");
    if(con.style.display=='none'){
        con.style.display = 'block';
    }else{
        con.style.display = 'none';
    }
}

function doDisplay2(){
    var con = document.getElementById("myDIV2");
    if(con.style.display=='none'){
        con.style.display = 'block';
    }else{
        con.style.display = 'none';
    }
}

function fn_onload() {
	document.getElementById('link1').style.visibility = "hidden";
	document.getElementById('link2').style.visibility = "visible";
	document.getElementById('link3').style.visibility = "hidden";
	document.getElementById('link4').style.visibility = "visible";
}
function link1_onclick() {
	document.getElementById('link1').style.visibility = "hidden";
	document.getElementById('link2').style.visibility = "visible";
}
function link2_onclick() {
	document.getElementById('link1').style.visibility = "visible";
	document.getElementById('link2').style.visibility = "hidden";
}
function link3_onclick() {
	document.getElementById('link3').style.visibility = "hidden";
	document.getElementById('link4').style.visibility = "visible";
}
function link4_onclick() {
	document.getElementById('link3').style.visibility = "visible";
	document.getElementById('link4').style.visibility = "hidden";
}
</script>

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
			locations.push({ lat: ${list.latitude}, lng: ${list.longitude}, region: "${list.region}", name: "${list.name}", event_schedule_id: ${list.event_schedule_id}, content: "${list.content}", start_date: "${list.start_date}" });
		</c:forEach>
	 		

		var labels = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ';
		
		/* 좌표 각각들에 대해 마커 객체를 생성해서 맵에 띄우는 함수  */
		var markers = locations.map( function(location, i) {
			return new google.maps.Marker({	
					position: new google.maps.LatLng(locations[i].lat, locations[i].lng),
/* 					label: locations[i].name 			*/
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
		          content: locations[i].name + '<br>場所: ' + locations[i].region + '<br>内容: '+ locations[i].content + '<div> <img src = "resources/images/clustering/samplepng/' + i + '.png">',
		          maxWidth: 250
		        });
			  marker.addListener('click', function() {
					infowindow.open(map, marker);
				});
			});
       
		
       
}

</script>
<script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCdC1Oa4xE2ub87g1ouqeRxqapzLLg4shg&callback=initMap&language=ja&region=JP">
</script>
<script src="https://developers.google.com/maps/documentation/javascript/examples/markerclusterer/markerclusterer.js">
</script>

	</body>
</html>