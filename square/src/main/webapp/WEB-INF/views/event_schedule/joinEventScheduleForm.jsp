<%@page import="project.ppaya.square.vo.EventSchedule"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.HashMap"%>
<%@page import="project.ppaya.square.vo.EventScheduleComment"%>
<%@page import="java.text.SimpleDateFormat"%>
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
<title>Schedule</title>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no" />
<link rel="stylesheet" href="resources/EventView/assets/css/main.css" />
<link rel="stylesheet" href="resources/GroupMain/assets/css/main.css" />
<link rel="stylesheet" href="resources/TextA/css/style.css">
<script type="text/javascript"
	src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript">
	google.charts.load('current', {'packages':['timeline']});
	google.charts.setOnLoadCallback(drawChart);
	function drawChart()
	{
		var json_event_schedule_user_schedule_list_list = JSON.parse('${json_event_schedule_user_schedule_list_list}');
    	  
		for(var i = 0; i < json_event_schedule_user_schedule_list_list.length; i++)
		{
			var container = document.getElementById('timeline' + json_event_schedule_user_schedule_list_list[i].user.user_id);
			var chart = new google.visualization.Timeline(container);
			var dataTable = new google.visualization.DataTable();
        
			dataTable.addColumn({ type: 'string', id: 'President' });
			dataTable.addColumn({ type: 'string', id: 'Name' });
			dataTable.addColumn({ type: 'string', id: 'style', role: 'style' });
			dataTable.addColumn({ type: 'date', id: 'Start' });
			dataTable.addColumn({ type: 'date', id: 'End' });
			for(var j = 0; j < json_event_schedule_user_schedule_list_list[i].list.length; j++)
			{
				if(json_event_schedule_user_schedule_list_list[i].list[j]["typeof"] == 1)
				{
					dataTable.addRows([
						[ json_event_schedule_user_schedule_list_list[i].user.user_id, '', 'Tomato', new Date(json_event_schedule_user_schedule_list_list[i].list[j].start_date), new Date(json_event_schedule_user_schedule_list_list[i].list[j].end_date) ]]);
			}
			else
			{
				dataTable.addRows([
						[ json_event_schedule_user_schedule_list_list[i].user.user_id, '', 'MediumSeaGreen', new Date(json_event_schedule_user_schedule_list_list[i].list[j].start_date), new Date(json_event_schedule_user_schedule_list_list[i].list[j].end_date) ]]);
			}
			var option =
			{
				width : (json_event_schedule_user_schedule_list_list[i].list[json_event_schedule_user_schedule_list_list[i].list.length - 1].end_date - json_event_schedule_user_schedule_list_list[i].list[0].start_date) / 50000,
				height: 250,
			};
			chart.draw(dataTable, option);
        
			document.getElementById('timeline_image' + json_event_schedule_user_schedule_list_list[i].user.user_id).innerHTML = "<a href='viewUserForm?user_id=" + json_event_schedule_user_schedule_list_list[i].user.user_id + "' class='image avatar thumb'><img src='resources/image/user_image/" + json_event_schedule_user_schedule_list_list[i].user.image_id + "' alt='' style='width: 100px; height:auto;'></a>";
			}
		}
	}
</script>
<style>
#map {
	width: 500px;
	height: 350px;
	position: relative !important;
	/* changing this to fixed makes the map dissapear */
	top: 0;
	bottom: 0;
	left: 0;
	right: 0;
	z-index: 0;
}

html, body {
	height: 100%;
	margin: 0;
	padding: 0;
}
</style>
</head>
<body class="is-preload">
	<!-- Header 메인 바 -->
	<header id="header1">
		<h1>
			<a class="navbar-brand font-weight-bolder mr-3" href="main"><img
				src="resources/Main/assets/css/images/photoSquareLogo_done.png"></a>
		</h1>
		<nav>
			<ul>
				<li><a href="listRecommendationForm"></a> <c:if
						test="${sessionScope.user_id != null}">
						<li><a href="createGroupForm">그룹생성</a></li>
						<li><a href="javascript:logoutUserAction()">로그아웃</a></li>
					</c:if> <c:if test="${sessionScope.user_id == null}">
						<li><a href="joinUserForm">회원가입</a></li>
						<li><a href="loginUserForm">로그인</a></li>
					</c:if>
			</ul>
		</nav>
	</header>

	<!-- Header -->
	<section id="header">
		<header>
			<span class="image avatar"><img
				src="resources/image/group_logo/${group.group_logo}" alt="" /></span>
			<h1 id="logo">
				<a
					href="viewGroupForm?group_category_id=${group_category.group_category_id}&group_id=${group.group_id}">${group.name}</a>
			</h1>
			<p style="font-size: 15px;">
				<c:forEach var="group_hashtag" items="${group_hashtag_list}">
							#${group_hashtag.hashtag}
						</c:forEach>
			</p>
			<c:if test="${sessionScope.user_id != null}">
				<c:if test="${group_attendance != null}">
					<c:if test="${event_attendance != null}">
						<c:if test="${event_schedule_attendance != null}">
							<a href="javascript:withdrawEventScheduleAction()" class="button">탈퇴</a>
						</c:if>
						<c:if test="${event_schedule_attendance == null}">
							<a href="javascript:joinEventScheduleAction()" class="button">참여</a>
						</c:if>
					</c:if>
				</c:if>
			</c:if>
		</header>
		<nav id="nav">
			<ul>
				<ul>
					<li><a href="#one" class="active">정보</a></li>
				</ul>
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
		<!-- Main 가:1280세:480-->
		<div id="main">
			<!-- Five -->
			<section id="one">
				<article class="post">
					<header>
						<div class="title">
							<h2>
								<a href="">${event_schedule.name}</a>
							</h2>
							<c:if
								test="${event_schedule.start_date != 0 && event_schedule.end_date != 0}">
								<h6 style="display: inline;"><%= (new SimpleDateFormat("yyyy年 MM月 dd日")).format(new Date(((EventSchedule)(request.getAttribute("event_schedule"))).getStart_date()))%></h6>
								<h6 style="display: inline;">~</h6>
								<h6 style="display: inline;"><%= (new SimpleDateFormat("yyyy年 MM月 dd日")).format(new Date(((EventSchedule)(request.getAttribute("event_schedule"))).getEnd_date()))%></h6>
							</c:if>
							<c:if
								test="${event_schedule.start_date == 0 || event_schedule.end_date == 0}">
								<h6>未定</h6>
							</c:if>
						</div>
						<div class="meta">
							<time class="published"><%= (new SimpleDateFormat("yyyy年 MM月 dd日")).format(new Date(((EventSchedule)(request.getAttribute("event_schedule"))).getInput_date()))%></time>
							<a href="viewUserForm?user_id=${leader.user_id}" class="author"><span
								class="name">${leader.name}</span><img
								src="resources/image/user_image/${leader.image_id}" alt="" /></a>
						</div>
					</header>
					<div align="right">
						<footer> </footer>
					</div>
				</article>
			</section>
		</div>
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
</body>
</html>