<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
	<head>
		<title>Join</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="resources/Join/assets/css/main.css" />
	<script>
	// Add the following code if you want the name of the file appear on select
	$(".image_id").on(
			"change",
			function() {
				var fileName = $(this).val().split("\\").pop();
				$(this).siblings(".custom-file-label").addClass("selected")
						.html(fileName);
			});
	</script>	
	
	</head>
	<body class="is-preload">

		<!-- Header -->
			<header id="header">
				<h1>회원가입</h1>
				<p>회원가입 좀 해주세요.<br>
				<a href="main">main 페이지 이동</a>.</p>
			</header>

		<!-- Signup Form -->
			<form id="join-form" method="post" action="joinForm2">
				<p><input type="text" name="user_id" id="user_id" placeholder="id 입력">

		<!-- non-display 로 ~! -->
<!-- 			<div class="inputEmailType">
				<p><input type="text" name="user_idEmailType" placeholder="email 직접 입력"></p>
			</div> -->
		
					<select name = "user_id2">
						<option>선택</option>
						<option>yahoo.com</option>
						<option>naver.com</option>
						<option>gmail.com</option>
						<option>daum.net</option>
						<option>hanmail.net</option>
						<option>직접입력</option>
					</select></p>
				<p><input type="password" name="password" id="password" placeholder="password 입력"></p>
				<p><input type="text" name="name" id="name" placeholder="name 입력"></p>
    		<div class="custom-file mb-3" align="left">
				<p><input type="file" class="image_id" id="personal image" name="filename"> 
			</div>
				<p><input type="text" name="region" id="region"></p>
				<p><input type="submit" value="Join"></p><br>
			</form>
		
		<!-- Footer -->
			<footer id="footer">
				<ul class="icons">
					<li><a href="#" class="icon fa-twitter"><span class="label">Twitter</span></a></li>
					<li><a href="#" class="icon fa-instagram"><span class="label">Instagram</span></a></li>
					<li><a href="#" class="icon fa-github"><span class="label">GitHub</span></a></li>
					<li><a href="#" class="icon fa-envelope-o"><span class="label">Email</span></a></li>
				</ul>
				<ul class="copyright">
					<li>&copy; Untitled.</li><li>Credits: <a href="http://html5up.net">HTML5 UP</a></li>
				</ul>
			</footer>
			
			
		<!-- 기본 Scripts -->
		<script src="resources/Basic/assets/js/jquery-3.3.1.min.js"></script>
		
		<!-- Scripts -->
			<script src="resources/Join/assets/js/main.js"></script>

	</body>
</html>