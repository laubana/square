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
		<title>Schedule</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="resources/EventView/assets/css/main.css" />
		<link rel="stylesheet" href="resources/GroupMain/assets/css/main.css" />
		<link rel="stylesheet" href="resources/TextA/css/style.css">
		
	<script>
	var CLIENT_ID = '823134128365-5e3gpcpbt5nvqc4mfgsbess1v9d8kj9g.apps.googleusercontent.com';
    var API_KEY = 'AIzaSyAujlCmx3gpGvD5ZHDN3Vqwp1hG0h-J3cc';
    var DISCOVERY_DOCS = ["https://www.googleapis.com/discovery/v1/apis/calendar/v3/rest"];
    var SCOPES = "https://www.googleapis.com/auth/calendar.readonly";
    
    function handleClientLoad() {
        gapi.load('client:auth2', initClient);
      }
    function initClient() {
        gapi.client.init({
          apiKey: API_KEY,
          clientId: CLIENT_ID,
          discoveryDocs: DISCOVERY_DOCS,
          scope: SCOPES
        }).then(function () {
        }, function(error) {
        	console.log(error);
        });
      }
	function withdrawEventScheduleAction()
	{
		map = {};
		map["user_id"] = "${sessionScope.user_id}";
		map["event_schedule_id"] = ${event_schedule.event_schedule_id};
		
		$.ajax({
			url: "withdrawEventScheduleAction",
			type: "POST",
			data: JSON.stringify(map),
			contentType: "application/json; charset=UTF-8",
			success: function()
			{
				//location.reload();
			},
			error: function(error){console.log(error);}
		});
	}
	function listUpcomingEvents() {
        gapi.client.calendar.events.list({
          'calendarId': 'primary',
          'timeMin': (new Date()).toISOString(),
          'showDeleted': false,
          'singleEvents': true,
          'maxResults': 10,
          'orderBy': 'startTime'
        }).then(function(response) {
        	for(var i = 0; i < response.result.items.length; i++)
        	{
        		google_user_schedule_list.push({start_date: response.result.items[i].start.dateTime,
        			end_date: response.result.items[i].end.dateTime});
        	}
         
        });
      }

	var google_user_schedule_list = [];
	function joinEventScheduleAction()
	{
		if(gapi.auth2.getAuthInstance().isSignedIn.get() == true)
		{			
			map = {};
			
			map["user_id"] = "${sessionScope.user_id}";
			map["event_schedule_id"] = ${event_schedule.event_schedule_id};
			listUpcomingEvents();
			var interval = setInterval(function()
					{
				if(google_user_schedule_list.length != 0)
						{
					map["google_user_schedule_list"] = google_user_schedule_list;
							clearInterval(interval);
						}
					},100);
			
			console.log(map);
			
			$.ajax({
				url: "joinEventScheduleAction",
				type: "POST",
				data: {map:map},
				contentType: "application/json; charset=UTF-8",
				success: function()
				{
					//location.reload();
				},
				error: function(error){console.log(error);}
			});
		}
		else
		{
			gapi.auth2.getAuthInstance().signIn();
			gapi.auth2.getAuthInstance().isSignedIn.listen(function()
					{
				
				listUpcomingEvents();
					
					map = {};
			map["user_id"] = "${sessionScope.user_id}";
			map["event_schedule_id"] = ${event_schedule.event_schedule_id};
			
			$.ajax({
				url: "joinEventScheduleAction",
				type: "POST",
				data: JSON.stringify(map),
				contentType: "application/json; charset=UTF-8",
				success: function()
				{
					location.reload();
				},
				error: function(error){console.log(error);}
			});
		});
		}
	}	
	</script>
		
		<script async defer src="https://apis.google.com/js/api.js"
      onload="this.onload=function(){};handleClientLoad()"
      onreadystatechange="if (this.readyState === 'complete') this.onload()">
    </script>
	</head>
	<body class="is-preload">

		<!-- Header 메인 바 -->
			<header id="header1">
				<h1><a href="main">2조</a></h1>
				<nav>
					<ul>
						<li><a href="listRecommendationForm"></a>
						<c:if test="${sessionScope.user_id != null}">
						<li><a href="createGroupForm">그룹생성</a></li>
					<li><a href="javascript:logoutUserAction()">로그아웃</a></li>
						</c:if>
						<c:if test="${sessionScope.user_id == null}">
						<li><a href="joinUserForm">회원가입</a></li>
							<li><a href="loginUserForm">로그인</a></li>
						</c:if>
					</ul>
				</nav>
			</header>

		<!-- Header -->
			<section id="header">
				<header>
					<span class="image avatar"><img src="resources/image/group_logo/${group.group_logo}" alt="" /></span>
					<h1 id="logo"><a href="viewGroupForm?group_id=${group.group_id}">${group.name}</a></h1>
					<p style="font-size:15px;">
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
						<li><a href="#two">회원</a></li>
						<li><a href="#three">코멘트</a></li>
						<li><a href="#four">앨범</a></li>
					</ul>
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

				<!-- Main 가:1280세:480-->
					<div id="main">

						<!-- Five -->
							<section id="one">
								<article class="post">
								<header>
									<div class="title">
										<h2><a href="">${event.name}</a></h2>
									</div>
									<div class="meta">
										<time class="published" datetime="2019-04-08">2019년 4월 8일</time>
										<a href="viewUserForm?user_id=${leader.user_id}" class="author"><span class="name">${leader.name}</span><img src="resources/image/user_image/${leader.image_id}" alt="" /></a>
									</div>
								</header>
								<p>
								${event_schedule.content}
								</p>
								<div align="right"><footer>
										<a href="#" class="icon fa-heart">28</a>&nbsp;&nbsp;&nbsp;&nbsp;
										<a href="#" class="icon fa-comment">128</a>&nbsp;&nbsp;				
								</footer></div>
							</article>
							</section>

					</div>
					

					<div id="main">

					<!-- 여기두 가능 -->
					<section>
					<div class="container">
					<h1>여기두</h1>
					<h2>암거나</h2>
					<h3>스케줄</h3>
					<h4>스카줄</h4>
					</div>
					</section>
					

					<!-- Two -->
							<section id="two">
								<div class="container">
									<h3>회원 정보</h3>
										<div>
											<a href="viewUserForm?user_id=${leader.user_id}" class="image avatar thumb"><img src="resources/image/user_image/${leader.image_id}" alt="" style="width: 100px; height:auto;"></a>
										</div>
										<div>
									<p>주최자</p>
										<c:forEach var="user" items="${user_list}">
											<a href="viewUserForm?user_id=${user.user_id}" class="image avatar thumb"><img src="resources/image/user_image/${user.image_id}" alt="" style="width: 100px; height:auto;"></a>
										</c:forEach>
										</div>
									<p>회원</p>
									<a href="listEventScheduleAttendanceForm?group_category_id=${group_category.group_category_id}&group_id=${group.group_id}&event_id=${event.event_id}&event_schedule_id=${event_schedule.event_schedule_id}" class="button">회원 페이지 이동</a>
								</div>
							</section>
					<!-- Three -->
							<section id="three">
								<div class="container">
									<h3>코멘트</h3>
						<div class="comments">
						
						<c:forEach var="event_schedule_comment" items="${event_schedule_comment_list}">
						<div class="comment-wrap">
							<div>
							<a href="viewUserForm?user_id=${event_schedule_comment.user.user_id}" class="image avatar thumb"><img src="resources/image/user_image/${event_schedule_comment.user.image_id}" alt="" style="width: 100px; height:auto;"></a>
							</div>
							<div class="comment-block">
								<p class="comment-text">${event_schedule_comment.content}</p>
									<div class="bottom-comment">
										<div class="comment-date">${event_schedule_comment.input_date}</div>
											<ul class="comment-actions">
												<li class="name"><a href="viewUserForm?user_id=${event_schedule_comment.user.user_id}">${event_schedule_comment.user.name}</a></li>
											</ul>
									</div>
							</div>
						</div>
						</c:forEach>
						<a href="listEventScheduleCommentForm?group_category_id=${group_category.group_category_id}&group_id${group.group_id}&event_id=${event.event_id}&event_schedule_id=${event_schedule.event_schedule_id}" class="button">코멘트 페이지 이동</a>
						
						</div>		
								</div>
							</section>

					<!-- Four -->
							<section id="four">
								<div class="container">
									<h3>앨범</h3>
						
									<div class="features">
										<article class="col-6 col-12-xsmall work-item">
											<c:forEach var="event_schedule_image" items="${event_schedule_image_list}">
												<a href="resources/image/event_schedule_image/${event_schedule_image.filename}" class="image thumb"><img src="resources/image/event_schedule_image/${event_schedule_image.filename}" alt="" /></a>
											<h3 style="width:0px;height:0px;font-size:0px;line-height:0px;position:absolute;overflow:hidden;">${event_schedule_image.event_schedule_id}</h3>
											</c:forEach>
											<br>
											<a href="listGroupAlbumForm?group_id=${group.group_id}" class="button">앨범 페이지 이동</a>
										</article>
									</div>
								</div>
							</section>
							
							<!-- 스케줄 -->
							<section>
								<div class="container">	
									<h1>암거나</h1>
									<h2>스케줄</h2>
									<h3>스케줄</h3>
									<h4>스케줄</h4>
									<p>암거나암거나</p>
								</div>
							</section>
							
							<!-- 관리 -->
							<section>
								<div class="container">	
									<h1>관리</h1>
									<h2>관리</h2>
									<h3>관리</h3>
									<h4>관리</h4>
									<p>관리</p>
								</div>
							</section>
							
				</div>
				
				<!-- Footer -->
					<section id="footer">
						<div class="container">
							<ul class="copyright">
								<li>&copy; Untitled. All rights reserved.</li><li>Design: <a href="http://html5up.net">HTML5 UP</a></li>
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