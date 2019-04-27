<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
	<head>
		<title>みんなみんな</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="resources/Login/assets/css/main.css" />
		<script>
			function isEmail(string)
			{
			    var regular_expression = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
			    
			    return regular_expression.test(string);
			}
		
			function loginUserAction()
			{
				if(isEmail(document.getElementById("user_id").value) == false)
				{
					alert("IDを確認してください。");
					return;
				}
				
				var map = {};
				map["user_id"] = document.getElementById("user_id").value;
				map["password"] = document.getElementById("password").value; 
				
				$.ajax({
					url: "loginUserAction",
					type: "POST",
					data: JSON.stringify(map),
					contentType: "application/json; charset=UTF-8",
					success: function(result)
					{
						if(result == "true")
						{
							location.replace("main");
						}
						else
						{
							alert("ログイン失敗");
						}
					},
					error: function(error){console.log(error);}
				});
			}
		</script>
	</head>
	<body class="is-preload">

		<!-- Header -->
			<header id="header">
				<h1>Login</h1>
			</header>

		<!-- Signup Form -->
				<br>
				<p><input type="text" name="user_id" id="user_id" placeholder="Email Address"></p><br>
				<p><input type="password" name="password" id="password" placeholder="Password"></p><br>
				<p><input type="submit" value="Login" onclick="loginUserAction()"></p>
			

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
			<script src="resources/Login/assets/js/main.js"></script>

	</body>
</html>