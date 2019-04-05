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
		<title>GroupCreate</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="resources/GroupCreate/assets/css/main.css" />
	<script src=resources/js/jquery-3.3.1.min.js></script>
	<script>
			// Add the following code if you want the name of the file appear on select
			$(".custom-file-input").on("change", function() {
			  var fileName = $(this).val().split("\\").pop();
			  $(this).siblings(".custom-file-label").addClass("selected").html(fileName);
			  
			  <img src="resources/GroupCreate/images/rabbit_carrot.png" alt="" />
			});
			
			$(document).ready(
					function()
					{
						$("#groupExpressButton").on("click",editGroupExpress)
					});	
			
	</script>  
	
	</head>
	<body class="is-preload" onload="fn_onload();">
<c:forEach var="user_id" items="${user_id}">
${user_id}
</c:forEach>
		<!-- Header -->
		
			<section id="header">
				<header>
				<span class="image avatar">
						<input type="file" class="custom-file-input" id="group_logo" name="group_logos">
						<label class="custom-file-label" for="group_logo">
							<img src="resources/GroupCreate/images/rabbit_carrot.png" alt="" />
						</label>
				</span>
			
					<p>해시해시해시<br />
					태그태그태그태그</p>
				</header>
				<nav id="nav">
					<ul>
						<li><a href="#one" class="main">main</a></li>
						<li><a href="#two">그룹 생성 폼</a></li>
						<li><a href="#three">앨범</a></li>
						<li><a href="#four">게시판</a></li>
						<li><a href="#five">게시판</a></li>
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

				<!-- Main -->
					<div id="main">

						<!-- One -->
							<section id="one">
								<div class="image main" data-position="center">
									<img src="resources/GroupCreate/images/banner.jpg" alt="" />
								</div>
								<div class="container">
									<header class="major">
										<h1>그룹 소개</h1><br>
									</header>
										<textarea class="groupExpress" id="content"
											name="content" placeholder="Explain about your group here!"></textarea><br>
										<input type="button" class="groupExpressButton" id="groupExpressButton" value="그룹 소개 등록">
								</div>
							</section>

						<!-- Two -->
							<section id="two">
								<div class="container">
									<h3>이벤트 활동</h3>
									<p>벤트벤트이벤트이벤트벤트벤트이벤트이벤트벤트벤트이벤트이벤트벤트벤트이벤트이벤트벤트벤트이벤트이벤트벤트벤트이벤트이벤트벤트벤트이벤트이벤트벤트벤트이벤트이벤트벤트벤트이벤트이벤트벤트벤트이벤트이벤트벤트벤트이벤트이벤트.</p>
									<ul class="feature-icons">
										<li class="fa-code">치킨ㄱㄱ? 파티(3/4)</li>
										<li class="fa-cubes">소맥 파티 구함</li>
										<li class="fa-book">코딩 팟!!</li>
										<li class="fa-coffee">삽겹살 먹구싶다 ㅠ</li>
										<li class="fa-bolt">등산 ㄱㄱ염</li>
										<li class="fa-users">에라이~</li>
									</ul>
									<input type="button" value="이벤트 관리">
								</div>
							</section>

						<!-- Three -->
							<section id="three">
								<div class="container">
									<h3>앨범</h3>
									<p>앨범앨범앨범앨범앨범앨범앨범앨범앨범앨범앨범앨범앨범앨범앨범앨범앨범앨범앨범앨범앨범앨범앨범앨범앨범앨범앨범앨범앨범앨범앨범앨범앨범앨범앨범앨범앨범앨범앨범앨범.</p>
									<div class="features">
										<article>
											<a href="groupPhoto" class="image"><img src="resources/GroupCreate/images/album/m1.jpg" alt="" /></a>
											<a href="groupPhoto" class="image"><img src="resources/GroupCreate/images/album/m2.jpg" alt="" /></a>
										</article>
											<div align="right"><a href="groupPhoto">+더보기</a></div>
									</div>
								</div>
							</section>

						<!-- Four -->	<!-- 아바타 이미지 필섹 가로 140 세로 140 -->
							<section id="four">
								<div class="container">
									<h3>회원 정보</h3>
										<div class="image avatar"><a href="myPage"><img src="resources/GroupCreate/images/member/c1.jpg" alt="" style="width: 100px; height:auto;"></a></div>
									<p>주최자</p>
										<div class="image avatar"><img src="resources/GroupCreate/images/member/m1.jpg" alt="" style="width: 100px; height:auto;"></div>
										<div class="image avatar"><img src="resources/GroupCreate/images/member/m2.jpg" alt="" style="width: 100px; height:auto;"></div>
										<div class="image avatar"><img src="resources/GroupCreate/images/member/m3.jpg" alt="" style="width: 100px; height:auto;"></div>
										<div class="image avatar"><img src="resources/GroupCreate/images/member/m4.jpg" alt="" style="width: 100px; height:auto;"></div>
										<div class="image avatar"><img src="resources/GroupCreate/images/member/m5.jpg" alt="" style="width: 100px; height:auto;"></div>
										<div align="right"><a href="javascript:doDisplay2();" id="link4" onclick="javascript:link4_onclick();">+더보기</a></div>
										<div id="myDIV2" style="display: none;">
										<div class="image avatar"><img src="resources/GroupCreate/images/member/m6.jpg" alt="" style="width: 100px; height:auto;"></div>
										</div>
										<div align="right"><a href="javascript:doDisplay2();" id="link3" onclick="javascript:link3_onclick();">+숨기기</a></div>
									<p>회원</p>
								</div>
							</section>

						<!-- Five -->
							<section id="five">
								<div class="container">
									<h3>게시판</h3>
									<div class="features">
										<article>
											<a href="#" class="image"><img src="resources/GroupCreate/images/pic01.jpg" alt="" /></a>
											<div class="inner">
												<h4>밥묵자</h4>
												<p>밥밥밥밥밥밥밥밥밥밥밥밥밥밥밥밥밥밥밥밥밥밥밥밥밥밥밥밥밥밥밥밥밥밥밥밥밥밥밥밥밥밥밥밥밥밥.</p>
											</div>
										</article>
										<article>
											<a href="#" class="image"><img src="resources/GroupCreate/images/pic02.jpg" alt="" /></a>
											<div class="inner">
												<h4>냉면묵자</h4>
												<p>냉면냉면냉면냉면냉면냉면냉면냉면냉면냉면냉면냉면냉면냉면냉면냉면냉면냉면냉면냉면냉면냉면냉면.</p>
											</div>
										</article>
										<article>
											<a href="#" class="image"><img src="resources/GroupCreate/images/pic03.jpg" alt="" /></a>
											<div class="inner">
												<h4>치킨묵자</h4>
												<p>치킨치킨치킨치킨치킨치킨치킨치킨치킨치킨치킨치킨치킨치킨치킨치킨치킨치킨치킨치킨치킨치킨치킨.</p>
											</div>
										</article>
										<hr>
										<div align="right"><a href="javascript:doDisplay1();" id="link2" onclick="javascript:link2_onclick();">+더보기</a></div>
											<div id="myDIV1" style="display: none;">
    											<article>
											<a href="#" class="image"><img src="resources/GroupCreate/images/pic01.jpg" alt="" /></a>
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
											<a href="#" class="image"><img src="resources/GroupCreate/images/pic03.jpg" alt="" /></a>
											<div class="inner">
												<h4>우동묵자</h4>
												<p>치킨치킨치킨치킨치킨치킨치킨치킨치킨치킨치킨치킨치킨치킨치킨치킨치킨치킨치킨치킨치킨치킨치킨.</p>
											</div>
										</article>
											</div>
											<div align="right"><a href="javascript:doDisplay1();" id="link1" onclick="javascript:link1_onclick();">+숨기기</a></div>
										<input type="button" value="게시판 관리">
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

		<!-- Scripts -->
			<script src="resources/GroupCreate/assets/js/jquery.min.js"></script>
			<script src="resources/GroupCreate/assets/js/jquery.scrollex.min.js"></script>
			<script src="resources/GroupCreate/assets/js/jquery.scrolly.min.js"></script>
			<script src="resources/GroupCreate/assets/js/browser.min.js"></script>
			<script src="resources/GroupCreate/assets/js/breakpoints.min.js"></script>
			<script src="resources/GroupCreate/assets/js/util.js"></script>
			<script src="resources/GroupCreate/assets/js/main.js"></script>
 
	</body>
</html>