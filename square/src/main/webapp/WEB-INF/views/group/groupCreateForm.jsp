<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no" />
<link rel="stylesheet" href="resources/GroupCreate/assets/css/main.css" />
<script src=resources/Basic/assets/js/jquery-3.3.1.min.js></script>

<script>
			// Add the following code if you want the name of the file appear on select
 			$(".custom-file-input").on("change", function() {
			  var fileName = $(this).val().split("\\").pop();
			  $(this).siblings(".custom-file-label").addClass("selected").html(fileName);
			   
			 
			});	

 			function groupLogoButton()
 			{
 				document.getElementById("group_logo").click();
 			}
 			
 			function groupImageButton()
 			{
 				document.getElementById("group_image").click();
 			}
			 
	</script>

</head>
<body class="is-preload" onload="fn_onload();">
	<c:forEach var="user_id" items="${user_id}">
${user_id}
</c:forEach>
	<!-- Header -->

	<section id="header">
		<header>
			<span class="image avatar"> <input type="file"
				class="custom-file-input" id="group_logo" name="group_logo" >
				<label class="custom-file-label" for="group_logo"> <img
					src="resources/GroupCreate/images/sh_squirrel.png" alt="" />
			</label>
			</span>

			<p>
				해시해시해시<br /> 태그태그태그태그
			</p>
		</header>
		<nav id="nav">
			<ul>
				<li><a href="#one" class="main">main</a></li>
				<li><a href="#two">그룹 생성 폼</a></li>
				<li><a href="#three">앨범</a></li>
	
			</ul>
		</nav>
		<footer>
			<ul class="icons">
				<li><a href="#" class="icon fa-twitter"><span class="label">Twitter</span></a></li>
				<li><a href="#" class="icon fa-facebook"><span
						class="label">Facebook</span></a></li>
				<li><a href="#" class="icon fa-instagram"><span
						class="label">Instagram</span></a></li>
				<li><a href="#" class="icon fa-github"><span class="label">Github</span></a></li>
				<li><a href="#" class="icon fa-envelope"><span
						class="label">Email</span></a></li>
			</ul>
		</footer>
	</section>

	<!-- Wrapper -->
	<div id="wrapper">
		<form id="group_create_form" method="post" action="imim">
			<div id="main">
				<!-- One -->
				<section id="one">
				<div class="image main" data-position="center">
				<input type="file"
				class="custom-file-input" id="group_image" name="group_image" hidden>
				<label class="custom-file-label" for="group_image">
					<img align="right" src="resources/GroupCreate/images/groupImageUpload.png" style="width:300px;height:300px" />
				</label>	
					</div>
					<div class="container">
						<header class="major">
							<h1>그룹이름</h1>
							<br>
						</header>
						<p>
							<input type="text" name="group_id" id="group_id"
								placeholder="group id 입력"><br> <input type="button"
								name="groupIdButton" id="groupIdButton" value="그룹 아이디 등록">
						</p>
					</div>
				</section>

				<!-- Two -->
				<section id="two">

					<div class="container">
						<header class="major">
							<h1>그룹 소개</h1>
							<br>
						</header>
						<textarea class="groupExpress" id="content" name="content"
							placeholder="Explain about your group here!"></textarea>
						<br> <input type="button" class="groupExpressButton"
							id="groupExpressButton" value="그룹 소개 등록">
					</div>
				</section>

				<!-- Three -->
				<section id="three">

					<div class="container">
						<header class="major">
							<h1>그룹 이미지 등록</h1>
						</header>
					<div class="groupImageDiv" align="left">
							<label>
						<input type="button" class="group-image-input" id="group-image-input" 
						value="그룹 이미지 등록" onclick="groupImageButton()">	
							</label>
						</div>
					
						<h1>그룹 로고 등록</h1>
						<div class="groupLogoDiv" align="left">
								<input type="button" class="group-logo-input" id="group-logo-input"
								 value="그룹 로고 등록" onclick="groupLogoButton()">
						</div>
					</div>
				</section>

				<!-- Four-->
				<section id="Four">

					<div class="container">
						<header class="major">
							<h1>그룹 카테고리</h1>
							<br>
							<h3>select group interests</h3>
							<div class="form-check-inline">
<!-- 								<input type="radio" id="group_category_id" name="group_category" value="food">food<br>
								<input type="radio" id="group_category_id" name="group_category" value="beauty">beauty<br>
								<input type="radio" id="group_category_id" name="group_category" value="sports">sports<br> -->
							</div>
						</header>

					</div>
				</section>

				<section id="Four">
					<div class="container">
						<header class="major">
							<h1>그룹 활동지역</h1>
								<input type="text">
							<br>
						</header>

					</div>
				</section>
			</div>
		</form>



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
  <script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAMLOYZKedHAwZTuo14Q0rrlveLsKm3or0&callback=initMap"
  type="text/javascript"></script>
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
	
/* 	document.getElementById('link2').style.visibility = "visible";
	document.getElementById('link3').style.visibility = "hidden";
	document.getElementById('link4').style.visibility = "visible"; */
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