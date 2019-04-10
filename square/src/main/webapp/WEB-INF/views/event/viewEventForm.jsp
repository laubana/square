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
		<title>GroupEventView</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="resources/EventView/assets/css/main.css" />
		<link rel="stylesheet" href="resources/GroupMain/assets/css/main.css" />
		<link rel="stylesheet" href="resources/TextA/css/style.css">
		
		<!-- *세현: 구글맵 띄우기 위한 style 지정 태그 -->
		<style>
			#map {
					width: 300px;
					height: 300px;
					position: relative !important; /*changing this to fixed makes the map dissapear*/
					top: 0; 
					bottom: 0; 
					left: 0; 
					right: 0; 
					z-index: 0;
	 		     }
			html,body {height: 100%; margin: 0; padding: 0;}
		</style>
		<!-- *세현: 구글 맵 띄우기 위한 script 태그 -->
		<script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCdC1Oa4xE2ub87g1ouqeRxqapzLLg4shg&callback=initMap">
		</script>
		<script>
			var map;
			function initMap() {
			    map = new google.maps.Map(document.getElementById('map'), {
			      center: {lat: -34.397, lng: 150.644},
			      zoom: 8
			    });
			  }
		</script>
		
	</head>
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

				<!-- Main 가:1280세:480-->
					<div id="main">

						<!-- Five -->
							<section id="five">
								<article class="post">
								<header>
									<div class="title">
										<h2><a href="">${event.name}</a></h2>
									</div>
									<div class="meta">
										<time class="published" datetime="2019-04-08">2019년 4월 8일</time>
										<a href="viewUserForm?user_id=${leader.user_id}" class="author"><span class="name">${leader.name}</span><img src="resources/image/user_image/${leader.image_id}" alt="" /></a>
									</div>
								</header>
								<span class="image featured"><img src="resources/image/event_image/${event.image_id}" alt="" /></span>
								<p>
									${event.content}
								</p>
									<div id="map" ></div>
								<div id = "div_map">
									어디에 들어가나
								</div>
								<div align="right"><footer>
										<a href="#" class="icon fa-heart">28</a>&nbsp;&nbsp;&nbsp;&nbsp;
										<a href="#" class="icon fa-comment">128</a>&nbsp;&nbsp;				
								</footer></div>
							</article>
							</section>

					</div>
					
					<div id="main">

					<!-- Two -->
							<section id="two">
								<div class="container">
									<h3>회원 정보</h3>
										<div>
											<a href="viewUserForm?user_id=${leader.user_id}" class="image avatar thumb"><img src="resources/image/user_image/${leader.image_id}" alt="" style="width: 100px; height:auto;"></a>
										</div>
										<div>
									<p>주최자</p>
										<c:forEach var="user" items="${user_list}">
											<a href="viewUserForm?user_id=${user.user_id}" class="image avatar thumb"><img src="resources/image/user_image/${user.image_id}" alt="" style="width: 100px; height:auto;"></a>
										</c:forEach>
										</div>
									<p>회원</p>
									<a href="listGroupAttendanceForm?group_id=${group.group_id}" class="button">회원 페이지 이동</a>
								</div>
							</section>
					<!-- Three -->
							<section id="three">
								<div class="container">
									<h3>코멘트</h3>
						<div class="comments">
						<div class="comment-wrap">
							<div><a href="myPage" class="image avatar thumb"><img src="resources/GroupMain/images/member/m1.jpg" alt="" style="width: 100px; height:auto;"></a></div>
							<div class="comment-block">
								<p class="comment-text">부트?</p>
									<div class="bottom-comment">
										<div class="comment-date">4월 24일, 2019년 @ 10:38 AM</div>
											<ul class="comment-actions">
												<li class="name">Emma</li>
												<li class="complain">Complain</li>
											</ul>
									</div>
							</div>
						</div>

						<div class="comment-wrap">
							<div><a href="myPage" class="image avatar thumb"><img src="resources/GroupMain/images/member/c1.jpg" alt="" style="width: 100px; height:auto;"></a></div>
							<div class="comment-block">
								<p class="comment-text">ㅅㅂ!</p>
									<div class="bottom-comment">
										<div class="comment-date">4월 23일, 2019년 @ 10:32 AM</div>
											<ul class="comment-actions">
												<li class="name">Harry</li>
												<li class="complain">Complain</li>
											</ul>
										</div>
									</div>
						</div>
						<a href="listGroupCommentForm?group_id${group.group_id}" class="button">코멘트 페이지 이동</a>
						
						</div>		
								</div>
							</section>

					<!-- Four -->
							<section id="four">
								<div class="container">
									<h3>앨범</h3>
						
									<div class="features">
										<article class="col-6 col-12-xsmall work-item">
											<c:forEach var="event_schedule_image" items="${event_schedule_image_list}">
												<a href="resources/image/event_schedule_image/${event_schedule_image.filename}" class="image thumb"><img src="resources/image/event_schedule_image/${event_schedule_image.filename}" alt="" /></a>
											<h3 style="width:0px;height:0px;font-size:0px;line-height:0px;position:absolute;overflow:hidden;">${event_schedule_image.event_schedule_id}</h3>
											</c:forEach>
											<br>
											<a href="listGroupAlbumForm?group_id=${group.group_id}" class="button">앨범 페이지 이동</a>
										</article>
									</div>
								</div>
							</section>
							
							<section>
								<div class="container">	
									<h1>암거나</h1>
									<h2>스케줄</h2>
									<h3>스케줄</h3>
									<h4>스케줄</h4>
									<p>암거나암거나</p>
									<a href="viewEventScheduleForm" class="button">스케줄 페이지 이동</a>	
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
</html>