<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<!--
	Strata by HTML5 UP
	html5up.net | @ajlkn
	Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->
<html>
	<head>
		<title>MyPage</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="resources/MyPage/assets/css/main.css" />
		
		
		
	</head>
	<body class="is-preload">

		<!-- Header -->
			<header id="header">
				<div class="inner">
					<div align="left"><h1 style="font-size:50px;"><a href="main">2조</a></h1></div>
					<a class="image avatar"><img src="resources/image/user_image/${user.image_id}" alt="" /></a>
					<h1><strong>${user.name}</strong>님의 마이페이지<br /></h1>
					<nav id="nav">
					
					</nav>
				</div>
			</header>



		<!-- Main -->
			<div id="main">

				<!-- One -->
					<section id="one">
						<header class="major">
							<h2>소개</h2>
						</header>
						<p>
							${user.content}
						</p>
						<div align="center"><a href="#" class="button">정보 편집</a></div>
					</section>
					
					<!-- Two -->
					<section id="two">
						<header class="major">
							<h2>관심 분야</h2>
						</header>
						<p>
							<c:forEach var="user_hashtag" items="${user_hashtag_list}">
								#${user_hashtag.hashtag}
							</c:forEach>
						</p>
						<ul class="tag">
										<li><a href="#">${ hlist[0].name }</a></li>
										<li><a href="#">${ hlist[1].name }</a></li>
										<li><a href="#">${ hlist[2].name }</a></li>
										<li><a href="#">${ hlist[3].name }</a></li>
										<li><a href="#">${ hlist[4].name }</a></li>
						</ul>
						<div align="center"><a href="#" class="button">관심 분야 편집</a></div>
					</section>

					<!-- Three -->
					<section id="three">
						<h1>그룹 활동</h1>
						<div class="row" align="center">
							<ul class="features">
							<c:forEach var="group" items="${group_list}">
								<li>
										<a href="viewGroupForm?group_id=${group.group_id}">
										<span><img src="resources/image/group_logo/${group.group_logo}" class="image fit tumb" alt="" /></span>
										<h3>${group.name}</h3></a>									
								</li>
							</c:forEach>
								</ul>
						</div>
								<div align="center"><a href="#" class="button">공개 / 비공개</a></div>
					</section>

				<!-- Four -->
					<section id="four">
						<h1>개인 앨범</h1>
						<div class="row">
							<article class="col-6 col-12-xsmall work-item">
								<a href="resources/MyPage/images/fulls/01.jpg" class="image fit thumb"><img src="resources/MyPage/images/thumbs/01.jpg" alt="" /></a>
								<h3 style="width:0px;height:0px;font-size:0px;line-height:0px;position:absolute;overflow:hidden;">파일 이름</h3>
							</article>
							<article class="col-6 col-12-xsmall work-item">
								<a href="resources/MyPage/images/fulls/02.jpg" class="image fit thumb"><img src="resources/MyPage/images/thumbs/02.jpg" alt="" /></a>
								<h3 style="width:0px;height:0px;font-size:0px;line-height:0px;position:absolute;overflow:hidden;">파일 이름</h3>
							</article>
							<article class="col-6 col-12-xsmall work-item">
								<a href="resources/MyPage/images/fulls/02.jpg" class="image fit thumb"><img src="resources/MyPage/images/thumbs/02.jpg" alt="" /></a>
								<h3 style="width:0px;height:0px;font-size:0px;line-height:0px;position:absolute;overflow:hidden;">파일 이름</h3>
							</article>
							<article class="col-6 col-12-xsmall work-item">
								<a href="resources/MyPage/images/fulls/02.jpg" class="image fit thumb"><img src="resources/MyPage/images/thumbs/02.jpg" alt="" /></a>
								<h3 style="width:0px;height:0px;font-size:0px;line-height:0px;position:absolute;overflow:hidden;">파일 이름</h3>
							</article>
						</div>
								<div align="center"><a href="listUserAlbumForm?user_id=${user.user_id}" class="button">앨범 편집</a></div>
					</section>

			</div>

		<!-- Footer -->
			<footer id="footer">
				<div class="inner">
					<ul class="icons">
						<li><a href="#" class="icon fa-twitter"><span class="label">Twitter</span></a></li>
						<li><a href="#" class="icon fa-github"><span class="label">Github</span></a></li>
						<li><a href="#" class="icon fa-dribbble"><span class="label">Dribbble</span></a></li>
						<li><a href="#" class="icon fa-envelope-o"><span class="label">Email</span></a></li>
					</ul>
					<ul class="copyright">
						<li>&copy; Untitled</li><li>Design: <a href="http://html5up.net">HTML5 UP</a></li>
					</ul>
				</div>
			</footer>

		<!-- 기본 Scripts -->
		<script src="resources/Basic/assets/js/jquery-3.3.1.min.js"></script>

		<!-- Scripts -->
			<script src="resources/MyPage/assets/js/jquery.min.js"></script>
			<script src="resources/MyPage/assets/js/jquery.poptrox.min.js"></script>
			<script src="resources/MyPage/assets/js/browser.min.js"></script>
			<script src="resources/MyPage/assets/js/breakpoints.min.js"></script>
			<script src="resources/MyPage/assets/js/util.js"></script>
			<script src="resources/MyPage/assets/js/main.js"></script>

	</body>
</html>