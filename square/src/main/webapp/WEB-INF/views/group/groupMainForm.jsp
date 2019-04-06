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
		<title>GroupMain</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="resources/GroupMain/assets/css/main.css" />
		<link rel="stylesheet" href="resources/TextA/css/style.css">
		
	</head>
	<body class="is-preload" onload="fn_onload();">

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
					<span class="image avatar"><img src="resources/Main/images/logo/01.jpg" alt="" /></span>
					<h1 id="logo"><a href="#">빠야 그룹</a></h1>
					<p style="font-size:15px;">[관심분야][관심분야][관심분야][관심분야]</p>
				</header>
				<nav id="nav">
					<ul>
						<li><a href="#one" class="active">정보</a></li>
						<li><a href="#two">회원</a></li>
						<li><a href="#three">코멘트</a></li>
						<li><a href="#four">앨범</a></li>
						<li><a href="#five">이벤트</a></li>
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

						<!-- One -->
							<section id="one">
								<div class="image main" data-position="center">
									<img src="resources/GroupMain/images/banner/01.jpg" alt="" />
								</div>
								<div class="container">
									<header class="major">
										<h2>빠야빠야빠야 빵야!</h2>
										<p>빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵<br />
										빵빵빵빵빵빵 <a href="main">2조</a>.</p>
									</header>
									<p>빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵빵.</p>
								</div>
							</section>
						
						<!-- Two -->	<!-- 아바타 이미지 필섹 가로 140 세로 140 -->
							<section id="two">
								<div class="container">
									<h3>회원 정보</h3>
										<div><a href="myPage" class="image avatar thumb"><img src="resources/GroupMain/images/member/c1.jpg" alt="" style="width: 100px; height:auto;"></a></div>
									<p>주최자</p>
										<div><a href="myPage" class="image avatar thumb"><img src="resources/GroupMain/images/member/m1.jpg" alt="" style="width: 100px; height:auto;"></a>
										<a href="myPage" class="image avatar thumb"><img src="resources/GroupMain/images/member/m2.jpg" alt="" style="width: 100px; height:auto;"></a>
										<a href="myPage" class="image avatar thumb"><img src="resources/GroupMain/images/member/m3.jpg" alt="" style="width: 100px; height:auto;"></a>
										<a href="myPage" class="image avatar thumb"><img src="resources/GroupMain/images/member/m4.jpg" alt="" style="width: 100px; height:auto;"></a>
										<a href="myPage" class="image avatar thumb"><img src="resources/GroupMain/images/member/m5.jpg" alt="" style="width: 100px; height:auto;"></a></div>
										<div align="right"><a href="javascript:doDisplay2();" id="link4" onclick="javascript:link4_onclick();">+더보기</a></div>
										<div id="myDIV2" style="display: none;">
										<div>
										<a href="myPage" class="image avatar thumb"><img src="resources/GroupMain/images/member/m6.jpg" alt="" style="width: 100px; height:auto;"></a>
										</div>
								</div>
										<div align="right"><a href="javascript:doDisplay2();" id="link3" onclick="javascript:link3_onclick();">+숨기기</a></div>
									<p>회원</p>
								</div>
							</section>
						
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
							
						<!-- Four -->
							<section id="four">
								<div class="container">
									<h3>앨범</h3>
									<p>앨범앨범앨범앨범앨범앨범앨범앨범앨범앨범앨범앨범앨범앨범앨범앨범앨범앨범앨범앨범앨범앨범앨범앨범앨범앨범앨범앨범앨범앨범앨범앨범앨범앨범앨범앨범앨범앨범앨범앨범.</p>
									<div class="features">
										<article class="col-6 col-12-xsmall work-item">
											<a href="resources/GroupMain/images/album/m1.jpg" class="image thumb"><img src="resources/GroupMain/images/album/m1.jpg" alt="" /></a>
											<h3 style="width:0px;height:0px;font-size:0px;line-height:0px;position:absolute;overflow:hidden;">산이당!</h3>
											<a href="resources/GroupMain/images/album/m2.jpg" class="image thumb"><img src="resources/GroupMain/images/album/m2.jpg" alt="" /></a>
											<h3 style="width:0px;height:0px;font-size:0px;line-height:0px;position:absolute;overflow:hidden;">산산산</h3>
											<div align="right"><a href="groupPhoto">+더보기</a></div>
										</article>
									</div>
								</div>
							</section>

						<!-- Five -->
							<section id="five">
								<div class="container">
									<h3>이벤트</h3>
									<div class="features">
										<article>
											<a href="#" class="image"><img src="resources/GroupMain/images/event/01.jpg" alt="" /></a>
											<div class="inner">
												<h4>치킨 묵자</h4>
												<p>치킨치킨치킨치킨치킨치킨치킨치킨치킨치킨치킨치킨치킨치킨치킨치킨치킨치킨치킨치킨치킨치킨치킨.</p>
											</div>
										</article>
										<article>
											<a href="#" class="image"><img src="resources/GroupMain/images/event/02.jpg" alt="" /></a>
											<div class="inner">
												<h4>생선 묵자</h4>
												<p>생선생선생선생선생선생선생선생선생선생선생선생선생선생선생선생선생선생선생선생선생선생선생선.</p>
											</div>
										</article>
										<article>
											<a href="#" class="image"><img src="resources/GroupMain/images/event/03.jpg" alt="" /></a>
											<div class="inner">
												<h4>냥이냥이</h4>
												<p>냥이냥이냥이냥이냥이냥이냥이냥이냥이냥이냥이냥이냥이냥이냥이냥이냥이냥이냥이냥이냥이냥이냥이냥이.</p>
											</div>
										</article>
										<hr>
										<div align="right"><a href="javascript:doDisplay1();" id="link2" onclick="javascript:link2_onclick();">+더보기</a></div>
											<div id="myDIV1" style="display: none;">
    											<article>
											<a href="#" class="image"><img src="resources/GroupMain/images/pic01.jpg" alt="" /></a>
											<div class="inner">
												<h4>라면묵자</h4>
												<p>밥밥밥밥밥밥밥밥밥밥밥밥밥밥밥밥밥밥밥밥밥밥밥밥밥밥밥밥밥밥밥밥밥밥밥밥밥밥밥밥밥밥밥밥밥밥.</p>
											</div>
										</article>
										<article>
											<a href="#" class="image"><img src="resources/GroupMain/images/pic02.jpg" alt="" /></a>
											<div class="inner">
												<h4>김밥묵자</h4>
												<p>냉면냉면냉면냉면냉면냉면냉면냉면냉면냉면냉면냉면냉면냉면냉면냉면냉면냉면냉면냉면냉면냉면냉면.</p>
											</div>
										</article>
										<article>
											<a href="#" class="image"><img src="resources/GroupMain/images/pic03.jpg" alt="" /></a>
											<div class="inner">
												<h4>우동묵자</h4>
												<p>치킨치킨치킨치킨치킨치킨치킨치킨치킨치킨치킨치킨치킨치킨치킨치킨치킨치킨치킨치킨치킨치킨치킨.</p>
											</div>
										</article>
											</div>
											<div align="right"><a href="javascript:doDisplay1();" id="link1" onclick="javascript:link1_onclick();">+숨기기</a></div>
										<input type="button" value="이벤트 페이지 이동">
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

<script type="text/javascript">
//숨기기,보이기
var bDisplay = true;
function doDisplay1(){
    var con = document.getElementById("myDIV1");
    if(con.style.display=='none'){
        con.style.display = 'block';
    }else{
        con.style.display = 'none';
    }
}

function doDisplay2(){
    var con = document.getElementById("myDIV2");
    if(con.style.display=='none'){
        con.style.display = 'block';
    }else{
        con.style.display = 'none';
    }
}

function fn_onload() {
	document.getElementById('link1').style.visibility = "hidden";
	document.getElementById('link2').style.visibility = "visible";
	document.getElementById('link3').style.visibility = "hidden";
	document.getElementById('link4').style.visibility = "visible";
}
function link1_onclick() {
	document.getElementById('link1').style.visibility = "hidden";
	document.getElementById('link2').style.visibility = "visible";
}
function link2_onclick() {
	document.getElementById('link1').style.visibility = "visible";
	document.getElementById('link2').style.visibility = "hidden";
}
function link3_onclick() {
	document.getElementById('link3').style.visibility = "hidden";
	document.getElementById('link4').style.visibility = "visible";
}
function link4_onclick() {
	document.getElementById('link3').style.visibility = "visible";
	document.getElementById('link4').style.visibility = "hidden";
}
</script>

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