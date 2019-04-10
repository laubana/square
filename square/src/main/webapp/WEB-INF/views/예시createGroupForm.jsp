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
		<title>ex</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="resources/GroupMain/assets/css/main.css" />
		<link rel="stylesheet" href="resources/TextA/css/style.css">
	
<style type="text/css">

</style>	
		
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
					<!-- 그룹 로고 이미지 -->
					<span class="image avatar"><img src="" alt="" /></span>
					<h1 id="logo"><a href="#">빠야 그룹</a></h1>
					<p style="font-size:15px;">[관심분야][관심분야][관심분야][관심분야]</p>
				</header>
				<nav id="nav">
					<ul>
						<li><a href="#one" class="active">그룹 생성</a></li>
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

			<div id="main">
				<!-- One -->
				<section id="one">
					<div class="image main" data-position="center">
					<!-- 그룹 대표 이미지 -->
					<img src="">	
					</div>
					
					<div class="container">
						
						<!-- 메인 해더 -->
						<header class="major">
							<h1>그룹 이름</h1>
							<br>
							<!-- 폼 -->
							<form action="" method="post">
							<input type="text" name="group_id" id="group_id" placeholder="그룹 이름">
							<br>
							<h1>그룹 소개</h1>
							<br>
							<div class="comment-wrap">
								<div class="comment-block">
										<textarea name="" id="" cols="30" rows="3" placeholder="자유롭게 작성해 주세요..."></textarea>
								</div>
							</div>
						
							<br>
							<h1>그룹 로고 이미지</h1>
							<div></div>
							<input type="file">
							<br>
							<h1>그룹 메인 이미지</h1>
							<div></div>
							<input type="file">
							<br>
							<h1>그룹 카테고리</h1>
							<input type="radio" value="">IT<input type="radio" value="">가족
							<input type="radio" value="">펫<input type="radio" value="">요리
							<input type="radio" value="">레져<input type="radio" value="">음악
							<br><br>
							<h1>그룹 관심 분야</h1>
							<input type="text" placeholder="그룹 관심 분야">
							<br>
							<h1>그룹 활동 지역</h1>
							<input type="text" placeholder="그룹 활동 지역">
							<br>
							<div align="center"><input type="submit" value="생성"></div>
							</form>
						</header>	
					</div>
				</section>
	<!-- 파일 미리보기 -->			
	<form action="" method="post">
        <input type='file' id="imgInp" />
        <img id="foo"src="#" />
    </form>
    
			</div>

		<!-- Footer -->
		<section id="footer">
			<div class="container">
				<ul class="copyright">
					<li>&copy; Untitled. All rights reserved.</li>
					<li>Design: <a href="http://html5up.net">HTML5 UP</a></li>
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

<script type="text/javascript">
//파일 미리보기
function readURL(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = function(e) {
            $('#foo').attr('src', e.target.result);
        }
        reader.readAsDataURL(input.files[0]);
    }
}

$("#imgInp").change(function() {
    readURL(this);
});
</script>

	</body>
</html>