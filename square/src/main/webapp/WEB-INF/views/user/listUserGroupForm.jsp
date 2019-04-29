<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<!--
	Strata by HTML5 UP
	html5up.net | @ajlkn
	Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->
<html>
	<head>
		<title>みんなみんな</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="resources/MyPage/assets/css/main.css" />

<style type="text/css">
input[type="checkbox"][id^="myCheckbox"] {
  display: none;
}

label {
  border: #fff;
  padding: 10px;
  display: block;
  position: relative;
  margin: 10px;
  cursor: pointer;
}

label:before {
  background-color: white;
  color: white;
  content: " ";
  display: block;
  border-radius: 50%;
  border: 1px solid grey;
  position: absolute;
  top: -5px;
  left: -5px;
  width: 25px;
  height: 25px;
  text-align: center;
  line-height: 28px;
  transition-duration: 0.4s;
  transform: scale(0);
}

label img {
  height: 100%;
  width: 100%;
  transition-duration: 0.2s;
  transform-origin: 50% 50%;
}

:checked + label {
  border-color: #ddd;
}

:checked + label:before {
  content: "✓";
  background-color: grey;
  transform: scale(1);
}

:checked + label img {
  transform: scale(0.9);
  /* box-shadow: 0 0 5px #333; */
  z-index: -1;
}
</style>		
		
	</head>
	<body class="is-preload">

		<!-- Header -->
			<header id="header">
				<div class="inner">
					<div align="left"><h1 style="font-size:50px;"><a href="main">みんな・みんな</a></h1></div>
					<c:if test="${user.image_id != null}">
					
					<a class="image avatar"><img src="resources/image/user_image/${user.image_id}" alt="" /></a>
					</c:if>
					<c:if test="${user.image_id == null}">
					
					<a class="image avatar"><img src="resources/Main/images/bb.jpg" alt="" /></a>
					</c:if>
					<h1><strong>${user.name}</strong>のマイページ<br /></h1>
			
					<nav id="nav">
					
					</nav>
				</div>
			</header>



		<!-- Main -->
			<div id="main">

					<!-- Three -->
					<section id="three">
						<h1> グループ公開 / グループ ひ公開</h1>
						<form action="" method="post">
						<div class="row" align="center">
							<ul class="features">
								<c:forEach var="group" items="${group_list}">
									<li>
									<c:if test="${group.blind != 0}">
										<input type="checkbox" id="checkbox${group.group_id}" name="group_checkbox" value="${group.group_id}" />
										</c:if>
									<c:if test="${group.blind == 0}">
										<input type="checkbox" id="checkbox${group.group_id}" checked name="group_checkbox" value="${group.group_id}" />
									</c:if>
										<label for="checkbox${group.group_id}">
										<span><img src="resources/image/group_logo/${group.group_logo}" class="image fit tumb" alt="" /></span>
										</label>
										<h3>${group.name}</h3>
									</li>
								</c:forEach>					
								</ul>									
						</div>
						<div align="center"><input type="button" value="確認" onclick="javascript:setGroupAlbumAction()"></div>
						</form>
					</section>

				

			</div>

		<!-- Footer -->
			<footer id="footer">
				<div class="inner">
					<ul class="icons">
						<li><a href="https://twitter.com" class="icon fa-twitter"><span class="label">Twitter</span></a></li>
						<li><a href="https://www.facebook.com" class="icon fa-facebook"><span class="label">Facebook</span></a></li>
						<li><a href="https://www.instagram.com" class="icon fa-instagram"><span class="label">Instagram</span></a></li>
						<li><a href="https://kr.linkedin.com" class="icon fa-linkedin"><span class="label">LinkedIn</span></a></li>
						<li><a href="https://dribbble.com" class="icon fa-dribbble"><span class="label">Dribbble</span></a></li>
						<li><a href="https://co.pinterest.com" class="icon fa-pinterest"><span class="label">Pinterest</span></a></li>
					</ul>
					<ul class="copyright">
						<li>&copy; Untitled</li><li>Design: <a href="http://html5up.net">HTML5 UP</a></li>
					</ul>
				</div>
			</footer>

		<!-- 기본 Scripts -->
		<script src="resources/Basic/assets/js/jquery-3.3.1.min.js"></script>

		<!-- Scripts -->
			<script src="resources/MyPage/assets/js/jquery.min.js"></script>
			<script src="resources/MyPage/assets/js/jquery.poptrox.min.js"></script>
			<script src="resources/MyPage/assets/js/browser.min.js"></script>
			<script src="resources/MyPage/assets/js/breakpoints.min.js"></script>
			<script src="resources/MyPage/assets/js/util.js"></script>
			<script src="resources/MyPage/assets/js/main.js"></script>
<script>
function setGroupAlbumAction()
{
	var map = {};
	var checked_group_id_list = [];
	var unchecked_group_id_list = [];
	
	$.each($(":checkbox[name='group_checkbox']:checked"), function(){            
		checked_group_id_list.push($(this).val());
    });
	$.each($(":checkbox[name='group_checkbox']:not(:checked)"), function(){            
		unchecked_group_id_list.push($(this).val());
    });
	
	map["user_id"] = "${user.user_id}";
	map["checked_group_id_list"] = checked_group_id_list;
	map["unchecked_group_id_list"] = unchecked_group_id_list;
	
	$.ajax({
		url: "setGroupAlbumAction",
		type: "POST",
		data: JSON.stringify(map),
		contentType: "application/json; charset=UTF-8",
		success: function()
		{			
			location.replace("viewUserForm?user_id=${user.user_id}");
		},
		error: function(error){console.log(error);}
	});
}
</script>
	</body>
</html>