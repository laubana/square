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
		
		
		
	</head>
	<body class="is-preload">

		<!-- Header -->
			<header id="header">
				<div class="inner">
					<div align="left"><h1 style="font-size:30px;"><a href="main">みんなみんな</a></h1></div>
					<a class="image avatar"><img src="resources/image/user_image/${user.image_id}" alt="" /></a>
					<h1><strong>${user.name}</strong>のマイページ<br></h1>
					<nav id="nav">
					
					</nav>
				</div>
			</header>



		<!-- Main -->
			<div id="main">
				<!-- One -->
					<section id="one">
						<header class="major">
							<h2>紹介</h2>
						</header>
						<div id="content_div">
						<p>
							${user.content}
						</p>
						</div>
						<c:if test="${sessionScope.user_id == user.user_id}">
						<div align="center" id="update_content_button_div"><a class="button" onclick="updateContent()">紹介編集</a></div>
						</c:if>
					</section>
					
					<!-- Two -->
					<%-- <section id="two">
						<header class="major">
							<h2>関心分野</h2>
						</header>
						<p>
							<c:forEach var="user_hashtag" items="${user_hashtag_list}">
								#${user_hashtag.hashtag}
							</c:forEach>
						</p>
						<ul class="tag">
										<li><a href="#">${ hlist[0].name }</a></li>
										<li><a href="#">${ hlist[1].name }</a></li>
										<li><a href="#">${ hlist[2].name }</a></li>
										<li><a href="#">${ hlist[3].name }</a></li>
										<li><a href="#">${ hlist[4].name }</a></li>
						</ul>
						<c:if test="${sessionScope.user_id == user.user_id}">
						<div align="center"><a href="#" class="button">関心分野編集</a></div>
						</c:if>
					</section> --%>
					<!-- Three -->
					<section id="three">
						<h1>グループ</h1>
						<div class="row" align="center">
							<ul class="features">
							<c:forEach var="group" items="${group_list}">
								<li>
										<a href="viewGroupForm?group_id=${group.group_id}">
										<span><img src="resources/image/group_logo/${group.group_logo}" class="image fit tumb" alt="" /></span>
										<h3>${group.name}</h3></a>									
								</li>
							</c:forEach>
								</ul>
						</div>
						<c:if test="${sessionScope.user_id == user.user_id}">
								<div align="center"><a href="listUserGroupForm?user_id=${user.user_id}" class="button">グループ編集</a></div>
								</c:if>
					</section>

				<!-- Four -->
					<section id="four">
						<h1>アルバム</h1>
						<h2>写真</h2>
						<div class="row">
						<c:forEach var="event_schedule_image" items="${event_schedule_image_list}">
						
							<article class="col-6 col-12-xsmall work-item">
								<a href="resources/image/event_schedule_image/${event_schedule_image.event_schedule_image_id}" class="image fit thumb"><img src="resources/image/event_schedule_image/${event_schedule_image.event_schedule_image_id}" alt="" /></a>
								<h3 style="width:0px;height:0px;font-size:0px;line-height:0px;position:absolute;overflow:hidden;">파일 이름</h3>
							</article>
						</c:forEach>
						</div>
						<hr style="width:380px;"><br>
						<h2>映像</h2>
						<div class="" align="center">
						<c:if test="${video_list.size() != 0}">
							<c:forEach var="element" items="${video_list}">
							
						<video width='365' height='300' style="background-color: black;" controls>
								<source src='resources/image/event_schedule_video/${element.video.filename}' type='video/mp4'>		
						</video>
							
							</c:forEach>
						</c:if>
						</div>
						<br><br>
						<c:if test="${sessionScope.user_id == user.user_id}">
								<div align="center"><a href="listUserAlbumForm?user_id=${user.user_id}" class="button">アルバム編集</a></div>
								</c:if>
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

	</body>
	<script>
	function updateContent()
	{
		var buff = "<textarea id='update_content_textarea'>${user.content}</textarea><br>";
		
		document.getElementById("content_div").innerHTML = buff;
		
		buff = "<a class='button' onclick='updateContentAction()'>確認</a>";
		
		document.getElementById("update_content_button_div").innerHTML = buff;
	}
	function updateContentAction()
	{
		map = {};
		map["content"] = $("#update_content_textarea").val();
		
		$.ajax({
			url: "updateContentAction",
			type: "POST",
			data: JSON.stringify(map),
			contentType: "application/json; charset=UTF-8",
			success: function()
			{
				location.reload();
			},
			error: function(error){console.log(error);}
		});
	}
	/* var video = document.getElementById("video");
	var video_interval;

	var video_appearance_list_list = JSON.parse('${json_temp_video_list}')[0].video_appearance_list_list;
	var user_list = JSON.parse('${json_temp_video_list}')[0].user_list;
	
	video.onplaying = function() {
		video_interval = setInterval(function()
				{
					var time = video.currentTime * 1000;
					var list = [];
					
					for(var i = 0; i < video_appearance_list_list.length; i++)
					{
						for(var j = 0; j < video_appearance_list_list[i].length; j++)
						{
							if(video_appearance_list_list[i][j].start_time <= time && time <= video_appearance_list_list[i][j].end_time)
							{
								list.push(i);
								break;
							}
						}
					}
					
					var buff = "";
					
					for(var i = 0; i < list.length; i++)
					{
						buff += "<a class='image avatar'><img src='resources/image/user_image/" + user_list[i].image_id + "' alt='' /></a>"
					}	
					
					document.getElementById("video_info").innerHTML = buff;
				}, 10);
	};
	video.onpause = function()
	{
		clearInterval(video_interval);
	};
	console.log(JSON.parse('${json_temp_video_list}')); */
	</script>
</html>