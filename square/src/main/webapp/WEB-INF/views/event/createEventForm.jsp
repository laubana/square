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