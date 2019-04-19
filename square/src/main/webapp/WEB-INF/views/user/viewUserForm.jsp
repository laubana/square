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
		<title>MyPage</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="resources/MyPage/assets/css/main.css" />
		
		
		
	</head>
	<body class="is-preload">

		<!-- Header -->
			<header id="header">
				<div class="inner">
					<div align="left"><h1 style="font-size:30px;"><a href="main">みんな・みんな</a></h1></div>
					<a class="image avatar"><img src="resources/image/user_image/${user.image_id}" alt="" /></a>
					<h1><strong>${user.name}</strong>님의 마이페이지<br /></h1>
					<nav id="nav">
					
					</nav>
				</div>
			</header>



		<!-- Main -->
			<div id="main">

				<!-- One -->
					<section id="one">
						<header class="major">
							<h2>소개</h2>
						</header>
						<p>
							${user.content}
						</p>
						<div align="center"><a href="#" class="button">정보 편집</a></div>
					</section>
					
					<!-- Two -->
					<section id="two">
						<header class="major">
							<h2>관심 분야</h2>
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
						<div align="center"><a href="#" class="button">관심 분야 편집</a></div>
					</section>

					<!-- Three -->
					<section id="three">
						<h1>그룹 활동</h1>
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
								<div align="center"><a href="listUserGroupForm?user_id=${user.user_id}" class="button">グループリスト編集</a></div>
					</section>

				<!-- Four -->
					<section id="four">
						<h1>개인 앨범</h1><br>
						<div class="row">
						<c:forEach var="event_schedule_image" items="${event_schedule_image_list}">
						
							<article class="col-6 col-12-xsmall work-item">
								<a href="resources/image/event_schedule_image/${event_schedule_image.event_schedule_image_id}" class="image fit thumb"><img src="resources/image/event_schedule_image/${event_schedule_image.event_schedule_image_id}" alt="" /></a>
								<h3 style="width:0px;height:0px;font-size:0px;line-height:0px;position:absolute;overflow:hidden;">파일 이름</h3>
							</article>
						</c:forEach>
						</div>
						<hr style="width:380px;"><br>
						<h1>映像</h1><br>
						<div class="">
						<c:if test="${video_list.size() != 0}">
							<c:forEach var="element" items="${video_list}">
							
						<video width='360' height='300' style="background-color: black;" controls>
								<source src='resources/image/event_schedule_video/${element.video.filename}' type='video/mp4'>		
						</video>
							
							</c:forEach>
						</c:if>
						</div>
						<br><br>
						
								<div align="center"><a href="listUserAlbumForm?user_id=${user.user_id}" class="button">앨범 편집</a></div>
					</section>

			</div>

		<!-- Footer -->
			<footer id="footer">
				<div class="inner">
					<ul class="icons">
						<li><a href="#" class="icon fa-twitter"><span class="label">Twitter</span></a></li>
						<li><a href="#" class="icon fa-github"><span class="label">Github</span></a></li>
						<li><a href="#" class="icon fa-dribbble"><span class="label">Dribbble</span></a></li>
						<li><a href="#" class="icon fa-envelope-o"><span class="label">Email</span></a></li>
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