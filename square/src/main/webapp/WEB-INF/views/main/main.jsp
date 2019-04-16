<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
	<head>
		<title>Main</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="resources/Main/assets/css/main.css" />
		<link rel="stylesheet" href="resources/TextA/css/style.css">
		<script>
			var texts = ["#급상승 해시태그", "SWDO 1st graduates", "유환,세현,용빈,수연", "Asian Beauty Awards 노수연", "就職できるかな", "QooQoo Buffet"];
			var count = 0;
			function changeText() {
			    $("#realTimeHashTag").text(texts[count]);
			    count < texts.length ? count++ : count = 0;
			}
			setInterval(changeText, 1000);
		
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
		</script>
	</head>
	<body class="is-preload">

		<!-- Header -->
			<header id="header">
			<h1><a class="navbar-brand font-weight-bolder mr-3" href="main"><img src="resources/Main/assets/css/images/photoSquareLogo_done.png"></a></h1>
				<nav>
					<ul>
						<li><a href="listRecommendationForm"><span id="realTimeHashTag"> #급상승 해시태그</span></a></li>
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

		<!-- Intro -->
			<section id="intro" class="main style1 dark fullscreen">
				<div class="content">
				<header>
					<h2>SQUARE</h2>
				</header>
					<footer>
						<a href="#work" class="button style2 down">More</a>
					</footer>
				</div>
			</section>

		<!-- Work -->
			<section id="work" class="main style3 primary">
				<div class="content">
					<header>
						<h2>카테고리</h2>
					</header>

					<!-- Gallery  -->
						<div class="gallery">
							<c:forEach var="group_category" items="#{group_category_list}" varStatus="status">
								<c:if test="${status.index % 2 == 1}">
									<article class="from-right">
										<a href="listGroupForm?group_category_id=${group_category.group_category_id}" class="image fit thumb${group_category.group_category_id}"><img src="resources/Main/images/thumbs/${group_category.group_category_id}.jpg" alt="" /></a>
									</article>
								</c:if>
								<c:if test="${status.index % 2 == 0}">
									<article class="from-left">
										<a href="listGroupForm?group_category_id=${group_category.group_category_id}" class="image fit thumb${group_category.group_category_id}"><img src="resources/Main/images/thumbs/${group_category.group_category_id}.jpg" alt="" /></a>
									</article>
								</c:if>
							</c:forEach>
						</div>
				</div>
			</section>

		<!-- One -->
		<c:if test="${sessionScope.user_id == null}">
			<section id="one" class="main style2 right dark fullscreen">
				<div class="content box style2">
					<header>
						<h2>회원 가입</h2>
					</header>
					<p>2조에 가입하여 사람들과 좋아하는 일을 함께하고 새로운 경험을 찾아보세요.</p>
					<div class="actions special">
						<a href="joinUserForm" class="button" style="color:white;">회원 가입</a>
					</div>
				</div>
				<a href="#two" class="button style2 down anchored">Next</a>
			</section>

		<!-- Two -->
			<section id="two" class="main style2 left dark fullscreen">
				<div class="content box style2">
					<header>
						<h2>그룹 생성</h2>
					</header>
					<p>자신의  관심사를 토대로 이벤트를 운영하는 그룹을 만들세요. 그룹을 만들고 열정을 가진 사람들을 모아 보세요.</p>
					<div class="actions special">
						<a href="createGroupForm" class="button" style="color:white;">그룹 생성</a>
					</div>
				</div>
			</section>
</c:if>
		<!-- Footer -->
			<footer id="footer">

				<!-- Icons -->
					<ul class="icons">
						<li><a href="#" class="icon fa-twitter"><span class="label">Twitter</span></a></li>
						<li><a href="#" class="icon fa-facebook"><span class="label">Facebook</span></a></li>
						<li><a href="#" class="icon fa-instagram"><span class="label">Instagram</span></a></li>
						<li><a href="#" class="icon fa-linkedin"><span class="label">LinkedIn</span></a></li>
						<li><a href="#" class="icon fa-dribbble"><span class="label">Dribbble</span></a></li>
						<li><a href="#" class="icon fa-pinterest"><span class="label">Pinterest</span></a></li>
					</ul>

				<!-- Menu -->
					<ul class="menu">
						<li><a href="main">2조</a></li>
						<li>&copy; Untitled</li><li>Design: <a href="https://html5up.net">HTML5 UP</a></li>
					</ul>

			</footer>



		<!-- 기본 Scripts -->
		<script src="resources/Basic/assets/js/jquery-3.3.1.min.js"></script>

		<!-- Scripts -->
			<script src="resources/Main/assets/js/jquery.min.js"></script>
			<script src="resources/Main/assets/js/jquery.poptrox.min.js"></script>
			<script src="resources/Main/assets/js/jquery.scrolly.min.js"></script>
			<script src="resources/Main/assets/js/jquery.scrollex.min.js"></script>
			<script src="resources/Main/assets/js/browser.min.js"></script>
			<script src="resources/Main/assets/js/breakpoints.min.js"></script>
			<script src="resources/Main/assets/js/util.js"></script>
			<script src="resources/Main/assets/js/main.js"></script>

<!-- 페이지 이동 버튼 -->
<script>
$(document).ready(function () {
	$('#mlogin').on('click', loginMove)
	$('#mjoin').on('click', joinMove)
	$('#gcreate').on('click', gcreateMove)
})

function loginMove() {
	location.href="login";
}

function joinMove() {
	location.href="";
}

function gcreateMove() {
	location.href="";
}
</script>

	</body>
</html>