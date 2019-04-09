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
					<ul>
						<li><a href="groupMain">메인</a></li>
						<li><a href="groupMember">회원</a></li>
						<li><a href="groupComment">코멘트</a></li>
						<li><a href="groupPhoto">앨범</a></li>
						<li><a href="#five" class="active">이벤트</a></li>
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
								<div align="right"><footer>
										<a href="#" class="icon fa-heart">28</a>&nbsp;&nbsp;&nbsp;&nbsp;
										<a href="#" class="icon fa-comment">128</a>&nbsp;&nbsp;
										<a href="Eventschedule" class="button">스케줄 페이지 이동</a>					
								</footer></div>
							</article>
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
</html>s