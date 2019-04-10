<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
	<head>
		<title>test1</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="resources/Main/assets/css/main.css" />
<%-- 		<script>
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
			function loginUserForm()
			{
				location.replace("<c:out value='loginUserForm'/>");
			}
			
			/*//실시간 검색어로 바뀌게 
			setTimeout(function() {
				if (realTimeKeyword) //realTimeKeyword에 값 저장 {
					$Element("da_iframe_time").attr("lve.keyword", );	 
					var welMinime = $Element("minime");
					if (welMinime) {
						welMinime.attr("lve.keyword", );
					}
					$Element("da_iframe_rolling").attr("lve.keyword", );
					$Element("cnsv_shbx").attr("lve.keyword", );
					$Element("da_iframe_below").attr("lve.keyword", );
				}
			}, 0);
			 */
			
			
		</script>
	</head>
	<body class="is-preload">

		<!-- Header -->
			<header id="header">
				<h1><a href="main">2조</a></h1>
				<nav>
					<ul>
					<li>
						<div class="realTimeList">
						<a href="listRecommendationForm">급상승 키워드</a>
							<ul class="realTimeElement">
							<li class="realTime_item">
							<a href="#" class="rt_a" data-click="lve.keyword">		<!-- data-*으로 타입 정할 수 있음. -->
							<span class="rt_k">아이마이</span>
							</a>
							<li class="realTime_item">
							<a href="#" class="rt_a" data-click="lve.keyword">
							<span class="rt_k">미마인</span>
							</a>
							</li>
							</ul>
						</div>
					</li>
						<c:if test="${sessionScope.user_id != null}">
						<li><a href="createGroupForm">그룹생성</a></li>
					<li><a href="javascript:logoutUserAction()">로그아웃</a></li>
						</c:if>
						<c:if test="${sessionScope.user_id == null}">
						<li><a href="joinUserForm">회원가입</a></li>
							<li><a href="javascript:loginUserForm()">로그인</a></li>
						</c:if>
					</ul>
				</nav>
			</header>

		<!-- Intro -->
			<section id="intro" class="main style1 dark fullscreen">
				<div class="content">
					<header>
						<h2>2조.</h2>
					</header>
					<p>Welcome to <strong>2조</strong> 참조하실 분은 <a href="https://www.meetup.com/ko-KR/">meetup</a><br />
					그리고 단어가 궁금하면 <a href="https://ja.dict.naver.com/">네이버 일본어 사전</a>.</p>
					<footer>
						<a href="#work" class="button style2 down">More</a>
					</footer>
				</div>
			</section>

		<!-- Work -->
			<section id="work" class="main style3 primary">
				<div class="content">
					<header>
						<h2>분야별 그룹 탐색</h2>
						<p>탐색을 합시당.
						탐색탐색,
						탐탐탐탐탐.</p>
					</header>

					<!-- Gallery  -->
						<div class="gallery">
							<c:forEach var="group_category" items="#{group_category_list}" varStatus="status">
								<c:if test="${status.index % 2 == 1}">
									<article class="from-right">
										<a href="listGroupForm?group_category_id=${group_category.group_category_id}" class="image fit thumb1"><img src="resources/Main/images/thumbs/${group_category.group_category_id}.jpg" alt="" /></a>
									</article>
								</c:if>
								<c:if test="${status.index % 2 == 0}">
									<article class="from-left">
										<a href="listGroupForm?group_category_id=${group_category.group_category_id}" class="image fit thumb1"><img src="resources/Main/images/thumbs/${group_category.group_category_id}.jpg" alt="" /></a>
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
					<p>회원 가입회원 가입회원 가입회원 가입회원 가입회원 가입회원 가입회원 가입회원 가입회원 가입회원 가입회원 가입회원 가입회원 가입.
					회원 가입회원 가입회원 가입회원 가입회원 가입회원 가입회원 가입회원 가입회원 가입회원 가입회원 가입회원 가입회원 가입회원 가입회원 가입,
					회원 가입회원 가입회원 가입회원 가입회원 가입회원 가입회원 가입회원 가입회원 가입회원 가입.</p>
					<div class="actions special">
						<input type="button" value="회원 가입" id="mjoin">
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
					<p>그룹 생성그룹 생성그룹 생성그룹 생성그룹 생성그룹 생성그룹 생성그룹 생성그룹 생성그룹 생성그룹 생성그룹 생성.
					그룹 생성그룹 생성그룹 생성그룹 생성그룹 생성그룹 생성그룹 생성그룹 생성그룹 생성그룹 생성그룹 생성그룹 생성그룹 생성그룹 생성,
					그룹 생성그룹 생성그룹 생성그룹 생성그룹 생성그룹 생성그룹 생성그룹 생성그룹 생성그룹 생성그룹 생성.</p>
					<div class="actions special">
						<input type="button" value="그룹 생성" id="gcreate">
					</div>
				</div>
				<a href="#intro" class="button style2 down anchored">Next</a>
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
 --%>
	</body>
</html>