<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
	<head>
		<title>みんなみんな</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="resources/Join/assets/css/main.css" />
	<script>
	// Add the following code if you want the name of the file appear on select
	/* $(".image_id").on(
			"change",
			function() {
				var fileName = $(this).val().split("\\").pop();
				$(this).siblings(".custom-file-label").addClass("selected")
						.html(fileName);
			}); */
	function verifyUserIdAction()
	{
		if(isEmail(document.getElementById("user_id").value) == false)
		{
			alert("IDを確認してください。");
			return;
		}
				
		var map = {};
		map["user_id"] = document.getElementById("user_id").value;
		
		$.ajax({
			url: "verifyUserIdAction",
			type: "POST",
			data: JSON.stringify(map),
			contentType: "application/json; charset=UTF-8",
			success: function(result)
			{			
				if(result == "true")
				{
					flag = true;
					alert("利用可能");
				}
				else
				{
					alert("利用不可");
				}
			},
			error: function(error){console.log(error);}
		});
	}
	function setSwitch()
	{
		flag = false;
	}
	var flag = false;
	function joinUserAction()
	{
		if(document.getElementById("user_id").value.length == 0)
		{
			alert("IDを入力してください。");
			return;
		}
		if(document.getElementById("password").value.length == 0)
		{
			alert("Passwordを入力してください。");
			return;
		}
		if(document.getElementById("name").value.length == 0)
		{
			alert("Nameを入力してください。");
			return;
		}
		if(document.getElementById("region").value.length == 0)
		{
			alert("Regionを入力してください。");
			return;
		}
		
		if(flag != false)
		{
			var map = {};
			map["user_id"] = document.getElementById("user_id").value;
			map["password"] = document.getElementById("password").value;
			map["name"] = document.getElementById("name").value;
			map["region"] = document.getElementById("region").value;
			
			$.ajax({
				url: "joinUserAction",
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
						alert("登録失敗");
					}
				},
				error: function(error){console.log(error);}
			});
		}
		else
		{
			alert("IDを確認してください。");
		}
	}
	function isEmail(string)
	{
	    var regular_expression = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
	    
	    return regular_expression.test(string);
	}
	</script>	
	
	</head>
	<body class="is-preload">

		<!-- Header -->
			<header id="header">
				<h1>Join</h1>
			</header>
					<a class="image avatar">
					<input type="file" id="image_file" style="display: none;" onchange="setUserImage()">
					<label for="image_file"><img id="user_image" src="resources/Main/images/bb.jpg" alt="" /></label>
					</a>
		<!-- Signup Form -->
				<p><input type="text" name="user_id" id="user_id" placeholder="ID" autocomplete="off" onchange="setSwitch()">
				<input type="button" value="検索" onclick="verifyUserIdAction()">
		<!-- non-display 로 ~! -->
<!-- 			<div class="inputEmailType">
				<p><input type="text" name="user_idEmailType" placeholder="email 직접 입력"></p>
			</div> -->
		</p>
				<p><input type="password" name="password" id="password" placeholder="Password" autocomplete="off"></p>
				<p><input type="text" name="name" id="name" placeholder="Name" autocomplete="off"></p>
    		<div class="custom-file mb-3" align="left">
			</div>
				<p><input type="text" name="region" id="region" placeholder="Area" autocomplete="off"></p>
				<p><input type="button" value="Join" onclick="joinUserAction()"></p><br>
			
		
		<!-- Footer -->
			<footer id="footer">
				<ul class="icons">
					<li><a href="https://twitter.com" class="icon fa-twitter"><span class="label">Twitter</span></a></li>
						<li><a href="https://www.facebook.com" class="icon fa-facebook"><span class="label">Facebook</span></a></li>
						<li><a href="https://www.instagram.com" class="icon fa-instagram"><span class="label">Instagram</span></a></li>
						<li><a href="https://kr.linkedin.com" class="icon fa-linkedin"><span class="label">LinkedIn</span></a></li>
						<li><a href="https://dribbble.com" class="icon fa-dribbble"><span class="label">Dribbble</span></a></li>
						<li><a href="https://co.pinterest.com" class="icon fa-pinterest"><span class="label">Pinterest</span></a></li>
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