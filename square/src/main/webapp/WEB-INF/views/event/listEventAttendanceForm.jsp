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
		<link rel="stylesheet" href="resources/GroupMain/assets/css/main.css" />
		<link rel="stylesheet" href="resources/TextA/css/style.css">
		
	</head>
	<body class="is-preload">

		<!-- Header 메인 바 -->
			<header id="header1">
				<h1><a class="navbar-brand font-weight-bolder mr-3" href="main"><img src="resources/Main/assets/css/images/photoSquareLogo_done.png"></a></h1>
				<nav>
					<ul>
						<li><a href="listRecommendationForm"><span id="realTimeHashTag"></span></a></li>
						<c:if test="${sessionScope.user_id != null}">
						<li><a href="viewUserForm?user_id=${sessionScope.user_id}">${sessionScope.user_id}</a></li>
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
					<h1 id="logo"><a href="viewGroupForm?group_id=${group.group_id}">${group.name}</a></h1>
					<p style="font-size:15px;">
					<c:forEach var="group_hashtag" items="${group_hashtag_list}">
							<a href="viewMindMapForm?hashtag=${group_hashtag.hashtag}">#${group_hashtag.hashtag}</a>
						</c:forEach>
					</p>
				</header>
				<nav id="nav">
					<ul>
						<li><a href="#five" class="active">メンバーリスト</a></li>
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
							<section id="five">
								<div class="container">
									<h3>メンバーリスト</h3>
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
</script>
	</body>
</html>