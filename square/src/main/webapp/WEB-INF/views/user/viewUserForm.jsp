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
					<a href="#" class="image avatar"><img src="resources/image/user_image/${user.image_id}" alt="" /></a>
					<h1><strong>${user.name}</strong>님의 마이페이지<br /></h1>
					<p>${user.content}</p>
					<nav id="nav">
					<ul>
						<li><a href="#one" class="active">정보</a></li>
						<li><a href="#two" class="active">관심 분야</a></li>
						<li><a href="#three" class="active">그룹 활동</a></li>
						<li><a href="#four" class="active">앨범</a></li>
					</ul>
				</nav>
				</div>
			</header>



		<!-- Main -->
			<div id="main">

				<!-- One -->
					<section id="one">
						<header class="major">
							<h2>간략 정보</h2>
						</header>
						<p>마이페이지 바꿈.</p>
						<p>ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ
						ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ</p>
						<ul class="actions">
							<li><a href="#" class="button">정보 편집</a></li>
						</ul>
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
						<ul class="actions">
							<li><a href="#" class="button">관심 분야 편집</a></li>
						</ul>
					</section>

					<!-- Three -->
					<section id="three">
						<h1>그룹 활동</h1>

<c:forEach var="g" items="${glist }">
	${g.content}
</c:forEach>
${glist[0].content }
						<div class="row">
							<ul class="features">
									<li>
										<span><img src="resources/GroupMain/images/logo.jpg" alt="" /></span>
										<h3>A그룹</h3>
										<p>빠야빠야빠야 빵야 빠야빠야빠야 빵야 빠야빠야빠야 빵야.</p>
										<a href="groupMain" class="button">이동</a>
									</li>
									<li>
										<span><img src="resources/GroupMain/images/logo.jpg" alt="" /></span>
										<h3>B그룹</h3>
										<p>빠야빠야빠야 빵야 빠야빠야빠야 빵야 빠야빠야빠야 빵야.</p>
										<a href="groupMain" class="button">이동</a>
									</li>
									<li>
										<span><img src="resources/GroupMain/images/logo.jpg" alt="" /></span>
										<h3>C그룹</h3>
										<p>빠야빠야빠야 빵야 빠야빠야빠야 빵야 빠야빠야빠야 빵야.</p>
										<a href="groupMain" class="button">이동</a>
									</li>
								</ul>
						</div>
					</section>

				<!-- Four -->
					<section id="four">
						<h1>개인 앨범</h1>
						<div class="row">
							<article class="col-6 col-12-xsmall work-item">
								<a href="resources/MyPage/images/fulls/01.jpg" class="image fit thumb"><img src="resources/MyPage/images/thumbs/01.jpg" alt="" /></a>
								<h3>Magna sed consequat tempus</h3>
								<p>Lorem ipsum dolor sit amet nisl sed nullam feugiat.</p>
							</article>
							<article class="col-6 col-12-xsmall work-item">
								<a href="resources/MyPage/images/fulls/02.jpg" class="image fit thumb"><img src="resources/MyPage/images/thumbs/02.jpg" alt="" /></a>
								<h3>Ultricies lacinia interdum</h3>
								<p>Lorem ipsum dolor sit amet nisl sed nullam feugiat.</p>
							</article>
						</div>
						<ul class="actions">
							<li><a href="listUserAlbumForm?user_id=${user.user_id}" class="button">앨범 편집</a></li>
						</ul>
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