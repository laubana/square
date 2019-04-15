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
				<h1>Join</h1>
			</header>

		<!-- Signup Form -->
			<form id="join-form" method="post" action="joinForm">
				<p><input type="text" name="user_id" id="user_id" placeholder="ID">

		<!-- non-display 로 ~! -->
<!-- 			<div class="inputEmailType">
				<p><input type="text" name="user_idEmailType" placeholder="email 직접 입력"></p>
			</div> -->
		</p>
				<p><input type="password" name="password" id="password" placeholder="비밀번호"></p>
				<p><input type="text" name="name" id="name" placeholder="이름"></p>
    		<div class="custom-file mb-3" align="left">
			</div>
				<p><input type="text" name="region" id="region" placeholder="지역"></p>
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