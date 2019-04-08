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
		<title>GroupComment</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="resources/GroupMain/assets/css/main.css" />
		<link rel="stylesheet" href="resources/TextA/css/style.css">
		
	</head>
	<body class="is-preload">

		<!-- Header 메인 바 -->
			<header id="header1">
				<h1><a href="main">2조</a></h1>
				<nav>
					<ul>
						<li><a href="joinForm">회원가입</a></li>
						<li><a href="groupCreateForm">그룹생성</a></li>
						<li><a href="login">로그인</a></li>
					</ul>
				</nav>
			</header>

		<!-- Header -->
			<section id="header">
				<header>
					<span class="image avatar"><img src="resources/Main/images/logo/01.jpg" alt="" /></span>
					<h1 id="logo"><a href="#">빠야 그룹</a></h1>
					<p style="font-size:15px;">[관심분야][관심분야][관심분야][관심분야]</p>
				</header>
				<nav id="nav">
					<ul>
						<li><a href="groupMain">메인</a></li>
						<li><a href="groupMember">회원</a></li>
						<li><a href="#three" class="active">코멘트</a></li>
						<li><a href="groupPhoto">앨범</a></li>
						<li><a href="groupEventList">이벤트</a></li>
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
						
						<!-- Three -->
							<section id="three">
								<div class="container">
									<h3>코멘트</h3>
						<div class="comments">

							<div class="comment-wrap">
								<div><a href="myPage" class="image avatar thumb"><img src="resources/GroupMain/images/member/c1.jpg" alt="" style="width: 100px; height:auto;"></a></div>
								<div class="comment-block">
									<form action="">
										<textarea name="" id="" cols="30" rows="3" placeholder="Add comment..."></textarea>
									</form>
							</div>
						</div>
						<div align="right"><input type="button" value="확인" ></div>
						<br>
						
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
</html>