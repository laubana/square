<%@page import="project.ppaya.square.vo.EventSchedule"%>
<%@page import="project.ppaya.square.vo.Event"%>
<%@page import="project.ppaya.square.vo.EventComment"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
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
		<title>みんなみんな</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="resources/EventView/assets/css/main.css" />
		<link rel="stylesheet" href="resources/GroupMain/assets/css/main.css" />
		<link rel="stylesheet" href="resources/TextA/css/style.css">
		
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
		
		<script>
			function withdrawEventAction()
			{
				map = {};
				map["user_id"] = "${sessionScope.user_id}";
				map["event_id"] = ${event.event_id};
				
				$.ajax({
					url: "withdrawEventAction",
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
			function joinEventAction()
			{
				map = {};
				map["user_id"] = "${sessionScope.user_id}";
				map["event_id"] = ${event.event_id};
				
				$.ajax({
					url: "joinEventAction",
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
			
			/* <div class="comment-wrap">
			<div>
			<a href="viewGroupForm?group_categoryid=${group_union.group_category_id}&group_id=${group_union.group_id}" class="image avatar thumb"><img src="resources/image/group_logo/${group_union.group_logo}" alt="" style="width: 100px; height:auto;"></a>
			</div>
		</div> */
			function searchGroupAction()
			{
				map = {};
				map["group_id"] = ${group.group_id};
				map["event_id"] = ${event.event_id};
				map["keyword"] = $("#keyword").val();
				
				$.ajax({
					url: "searchGroupAction",
					type: "POST",
					data: JSON.stringify(map),
					dataType: "JSON",
					contentType: "application/json; charset=UTF-8",
					success: function(result)
					{
						var buff = "";
						
						for(var i = 0; i < result.length; i++)
						{
							buff += "<div class='comment-wrap'>";
							buff += "<div style='display: inline;'>";
							buff += "<a href='viewGroupForm?group_category_id=" + result[i].group_category_id +"&group_id=" + result[i].group_id + "' class='image avatar thumb'><img src='resources/image/group_logo/"+ result[i].group_logo + "' alt='' style='width: 100px; height:auto;'></a>";
							buff += "</div>";
							buff += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
							buff += "<input type='button' onclick='javascript:unifyEventAction(" + result[i].group_id + ")' value='連合'>";
							buff += "<br><a> " + result[i].name + "</a><hr>";
							buff += "</div>";
						}
						
						document.getElementById("group_list").innerHTML = buff;
					},
					error: function(error){console.log(error);}
				});
			}
			function unifyEventAction(group_id)
			{
				map = {};
				map["event_id"] = ${event.event_id};
				map["current_group_id"] = ${group.group_id};
				map["group_id"] = group_id;
				
				$.ajax({
					url: "unifyEventAction",
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
		</script>
		
	</head>
	<body class="is-preload">
		<!-- Header 메인 바 -->
			<header id="header1">
				<h1><a class="navbar-brand font-weight-bolder mr-3" href="main"><img src="resources/Main/assets/css/images/photoSquareLogo_done.png"></a></h1>
				<nav>
					<ul>
						<li><a href="listRecommendationForm"></a>
						<c:if test="${sessionScope.user_id != null}">
						<li>${sessionScope.user_id}</li>
						<li><a href="createGroupForm">グループ開設</a></li>
					<li><a href="javascript:logoutUserAction()"><strong style="color:#778899;">ログアウト</strong></a></li>
						</c:if>
						<c:if test="${sessionScope.user_id == null}">
						<li><a href="joinUserForm">ユーザー登録</a></li>
							<li><a href="loginUserForm"><strong style="color:#778899;">ログイン</strong></a></li>
						</c:if>
					</ul>
				</nav>
			</header>

		<!-- Header -->
			<section id="header">
				<header>
					<span class="image avatar"><img src="resources/image/group_logo/${group.group_logo}" alt="" /></span>
					<h1 id="logo"><a href="viewGroupForm?group_category_id=${group_category.group_category_id}&group_id=${group.group_id}">${group.name}</a></h1>
					<p style="font-size:15px;">
					<c:forEach var="group_hashtag" items="${group_hashtag_list}">
							<a href="viewMindMapForm?hashtag=${group_hashtag.hashtag}">#${group_hashtag.hashtag}</a>
						</c:forEach>
					</p>
					<c:if test="${sessionScope.user_id != null}">
						<c:if test="${group_attendance != null}">
							<c:if test="${event_attendance != null}">
								<a href="javascript:withdrawEventAction()" class="button">脱退</a>
							</c:if>
							<c:if test="${event_attendance == null}">
								<a href="javascript:joinEventAction()" class="button">参加</a>
							</c:if>
						</c:if>
					</c:if>
				</header>
				<nav id="nav">
					<ul>
						<li><a href="#one" class="active">イベント情報</a></li>
						<li><a href="#two">メンバー</a></li>
						<li><a href="#three">コメント</a></li>
						<li><a href="#four">アルバム</a></li>
						<li><a href="#five">イベント・スケジュール</a></li>
					</ul>
				</nav>
				<footer>
					<ul class="icons">
						<li><a href="https://twitter.com" class="icon fa-twitter"><span class="label">Twitter</span></a></li>
						<li><a href="https://www.facebook.com" class="icon fa-facebook"><span class="label">Facebook</span></a></li>
						<li><a href="https://www.instagram.com" class="icon fa-instagram"><span class="label">Instagram</span></a></li>
						<li><a href="https://kr.linkedin.com" class="icon fa-linkedin"><span class="label">LinkedIn</span></a></li>
						<li><a href="https://dribbble.com" class="icon fa-dribbble"><span class="label">Dribbble</span></a></li>
						<li><a href="https://co.pinterest.com" class="icon fa-pinterest"><span class="label">Pinterest</span></a></li>
					</ul>
				</footer>
			</section>

		<!-- Wrapper -->
			<div id="wrapper">

				<!-- Main 가:1280세:480-->
					<div id="main">

						<!-- Five -->
							<section id="one">
								<article class="post">
								<header>
									<div class="title">
										<h2><a>${event.name}</a></h2>								
									</div>
									<div class="meta">
										<time class="published"><%= (new SimpleDateFormat("yyyy年 MM月 dd日")).format(new Date(((Event)(request.getAttribute("event"))).getInput_date()))%></time>
										<a href="viewUserForm?user_id=${leader.user_id}" class="author"><span class="name">${leader.name}</span><img src="resources/image/user_image/${leader.image_id}" alt="" /></a>
										<div align="right"><a class="icon fa-heart">28</a></div>
									</div>
								</header>
								<span class="image featured"><img src="resources/image/event_image/${event.image_id}" ></span>
								
								<p>
									${event.content}
								</p>
								<!-- <div align="right"><footer>
										<a class="icon fa-heart">28</a>&nbsp;&nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;				
								</footer></div> -->
							</article>
							</section>
							<section id="">
								<div class="container">
								<h3>イベント・スケジュール場所</h3>
									<div align ="center">
										<div id="map" ></div>
									</div>
								</div>
							</section>
					</div>
					
					<div id="main">

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
										</div>
										<br>
									<a href="listEventAttendanceForm?group_category_id=${group_category_id}&group_id=${group.group_id}&event_id=${event.event_id}" class="button">メンバーリストページへ</a>
								</div>
							</section>
					<!-- Three -->
							<section id="three">
								<div class="container">
									<h3 style="display: inline;">コメント</h3>
						<p align="right" class="icon fa-comment">${comment_list.size()}</p>
						<div class="comments">
						<c:forEach var="element" items="${comment_list}" end="2">
						<div class="comment-wrap">
							<div>
							<a href="viewUserForm?user_id=${element.user.user_id}" class="image avatar thumb"><img src="resources/image/user_image/${element.user.image_id}" alt="" style="width: 100px; height:auto;"></a>
							</div>
							<div class="comment-block">
								<p class="comment-text" id="comment${element.comment.event_comment_id}">${element.comment.content}</p>
									<div class="bottom-comment">
										<div class="comment-date">
										
										<%= (new SimpleDateFormat("yyyy年 MM月 dd日 HH:mm:ss")).format(new Date(((EventComment)(((HashMap)pageContext.getAttribute("element")).get("comment"))).getInput_date()))%>
										
										</div>
											<ul class="comment-actions">
												<li class="name"><a href="viewUserForm?user_id=${element.user.user_id}">${element.user.name}</a></li>
												<li class="name" id="translation_button${element.comment.event_comment_id}"><a href="javascript:getEventCommentTranslation(${element.comment.event_comment_id})">翻訳</a></li>
											<c:if test="${element.user.user_id == sessionScope.user_id}">
												<li class="name">Edit</li>
												<li>Delete</li>
											</c:if>
													<select id="translation_language${element.comment.event_comment_id}">
														  <option value="en">英語</option>
														  <option value="ko">韓国語</option>
													</select>
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
						<a href="listEventCommentForm?group_category_id=${group_category_id}&group_id=${group.group_id}&event_id=${event.event_id}" class="button">コメントリストページへ</a>
						
						</div>		
								</div>
							</section>

					<!-- Four -->
							<section id="four">
								<div class="container">
									<h3>アルバム</h3>
									<h4>写真</h4>
									<div class="features" align="center">
										<article class="col-6 col-12-xsmall work-item">
											<c:forEach var="event_schedule_image" items="${event_schedule_image_list}" end="3">
												<a href="resources/image/event_schedule_image/${event_schedule_image.filename}" class="image thumb"><img src="resources/image/event_schedule_image/${event_schedule_image.filename}" alt="" /></a>
											<h3 style="width:0px;height:0px;font-size:0px;line-height:0px;position:absolute;overflow:hidden;">${event_schedule_image.event_schedule_id}</h3>
											</c:forEach>
											<br>
											</article>
									</div>
									<h4>映像</h4>
									<div class="features" align="center">
											<c:if test="${video_list.size() != 0}">
											<video width='365' height='300' style="background-color: black;" controls>
											<c:forEach var="video" items="${video_list}">
											<source src='resources/image/event_schedule_video/${video.filename}' type='video/mp4'>
											</c:forEach>
											</video>
											</c:if>
											<br><br>
											<div class="features" align="left">
											<a href="listEventAlbumForm?group_category_id=${group_category.group_category_id}&group_id=${group.group_id}&event_id=${event.event_id}" class="button">イベント・アルバムページへ</a>
											</div>
									</div>	
								</div>
							</section>
							
							<section id="five">
								<div class="container">
									<h3>イベント・スケジュール</h3>
									<div align="right"><a href="createEventScheduleForm?group_category=${group_category.group_category_id}&group_id=${group.group_id}&event_id=${event.event_id}" class="button">開設</a></div>
									<div class="features">
									<c:forEach var="event_schedule" items="${event_schedule_list}" end="2">
									<article>
										
											<div class="inner">
											<h4><a href="viewEventScheduleForm?group_category_id=${group_category.group_category_id}&group_id=${group.group_id}&event_id=${event.event_id}&event_schedule_id=${event_schedule.event_schedule_id}">${event_schedule.name}</a></h4>
										
											</div>
										</article>
										<c:if test="${event_schedule.start_date != 0 && event_schedule.end_date != 0}">
										<h6 style="display: inline;"><%= (new SimpleDateFormat("yyyy年 MM月 dd日")).format(new Date(((EventSchedule)(pageContext.getAttribute("event_schedule"))).getStart_date()))%></h6>
										<h6 style="display: inline;"> ~ </h6>
										<h6 style="display: inline;"><%= (new SimpleDateFormat("yyyy年 MM月 dd日")).format(new Date(((EventSchedule)(pageContext.getAttribute("event_schedule"))).getEnd_date()))%></h6>
									</c:if>
									<c:if test="${event_schedule.start_date == 0 || event_schedule.end_date == 0}">
										<h6>未定</h6>
									</c:if>
										</c:forEach>
											</div>
											
										<a href="listEventScheduleForm?group_category_id=${group_category.group_category_id}&group_id=${group.group_id}&event_id=${event.event_id}" class="button">イベント・スケジュールリストページへ</a>
									</div>
							</section>
							<section id="six">
								<div class="container">
									<h3>参加グループ</h3>
									<div class="features">
									<c:forEach var="group_union" items="${group_union_list}">
						<div class="comment-wrap">
							<div>
							<a href="viewGroupForm?group_categoryid=${group_union.group_category_id}&group_id=${group_union.group_id}" class="image avatar thumb"><img src="resources/image/group_logo/${group_union.group_logo}" alt="" style="width: 100px; height:auto;"></a>
							</div>
						</div>
						</c:forEach>
						<c:if test="${sessionScope.user_id == leader.user_id}">
							<input type="text" id="keyword" style="width:300px; display:inline;" autocomplete="off">
							<input type="button" onclick="javascript:searchGroupAction()" value="グループ・サーチ"><br><br><br>
							<div id="group_list" class="features"></div>
						</c:if>
						
									</div>
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
function getEventCommentTranslation(event_comment_id)
{
	var map = {};
	map["event_comment_id"] = event_comment_id;
	map["language"] = document.getElementById("translation_language" + event_comment_id).value; 
	
	$.ajax({
		url: "getEventCommentTranslation",
		type: "POST",
		data: JSON.stringify(map),
		dataType: "JSON",
		contentType: "application/json; charset=UTF-8",
		success: function(jsonObject)
		{
			var result = decodeURIComponent(jsonObject.result.replace(/\+/g, " "));
			
			document.getElementById("comment" + event_comment_id).innerHTML = result;
			document.getElementById("translation_button" + event_comment_id).innerHTML = '<a href="javascript:resetEventComment(' + event_comment_id + ')">リセット</a>';
		},
		error: function(error){console.log(error);}
	});
}
function resetEventComment(event_comment_id)
{
	var map = {};
	map["event_comment_id"] = event_comment_id;
	
	$.ajax({
		url: "resetEventComment",
		type: "POST",
		data: JSON.stringify(map),
		dataType: "JSON",
		contentType: "application/json; charset=UTF-8",
		success: function(jsonObject)
		{
			var result = decodeURIComponent(jsonObject.result.replace(/\+/g, " "));
			
			document.getElementById("comment" + event_comment_id).innerHTML = result;
			document.getElementById("translation_button" + event_comment_id).innerHTML = '<a href="javascript:getEventCommentTranslation(' + event_comment_id +')">翻訳</a>';
		},
		error: function(error){console.log(error);}
	});
}
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
			locations.push({ lat: ${list.latitude}, lng: ${list.longitude}, region: "${list.region}", name: "${list.name}", event_schedule_id: ${list.event_schedule_id}, content: "${list.content}", start_date: "${list.start_date}" });
		</c:forEach>

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
       
		/* 마커 배열에 있는 마커 객체들 하나하나 꺼내서 클릭 이벤트 + infowindow 띄우기 걸어주는 함수 */     
		markers.map( function(marker, i) {
			
			var infowindow = new google.maps.InfoWindow({
		          content: locations[i].name + '<br>場所: ' + locations[i].region + '<br>内容: '+ locations[i].content + '<div><img width="210px" height="140px" src = "resources/image/sample/it/' + i + '.jpg">',
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
</html>