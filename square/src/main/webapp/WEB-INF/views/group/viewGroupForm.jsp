<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="project.ppaya.square.vo.GroupComment"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<!--
	Read Only by HTML5 UP
	html5up.net | @ajlkn
	Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->
<html>
	<head>
		<title>GroupMain</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="resources/GroupMain/assets/css/main.css" />
		<link rel="stylesheet" href="resources/TextA/css/style.css">
		<script>
			function logoutUserAction()
			{
				$.ajax({
					url: "logoutUserAction",
					type: "POST",
					success: function()
					{
						location.replace("<c:out value='main'/>");
					},
					error: function(error){console.log(error);}
				});
			}
			function withdrawGroupAction()
			{
				map = {};
				map["user_id"] = "${sessionScope.user_id}";
				map["group_id"] = ${group.group_id};
				
				$.ajax({
					url: "withdrawGroupAction",
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
			function joinGroupAction()
			{
				map = {};
				map["user_id"] = "${sessionScope.user_id}";
				map["group_id"] = ${group.group_id};
				
				$.ajax({
					url: "joinGroupAction",
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
		</script>
	</head>
	<body class="is-preload" onload="fn_onload();">

		<!-- Header 메인 바 -->
			<header id="header1">
				<h1><a href="main">2조</a></h1>
				<nav>
					<ul>
						<li><a href="listRecommendationForm"></a>
						<c:if test="${sessionScope.user_id != null}">
						<li>${sessionScope.user_id}</li>
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
					<h1 id="logo"><a href="#">${group.name}</a></h1>
					<p style="font-size:15px;">
						<c:forEach var="group_hashtag" items="${group_hashtag_list}">
							#${group_hashtag.hashtag}
						</c:forEach>
					</p>
					<c:if test="${sessionScope.user_id != null}">
						<c:if test="${group_attendance != null}">
							<a href="javascript:withdrawGroupAction()" class="button">탈퇴</a>
						</c:if>
						<c:if test="${group_attendance == null}">
							<a href="javascript:joinGroupAction()" class="button">참여</a>
						</c:if>
					</c:if>
				</header>
				<nav id="nav">
					<ul>
						<li><a href="#one" class="active">グループ情報</a></li>
						<li><a href="#two">メンバー</a></li>
						<li><a href="#three">コメント</a></li>
						<li><a href="#four">アルバム</a></li>
						<li><a href="#five">イベント</a></li>
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

						<!-- One -->
							<section id="one">
								<div class="image main" data-position="center">
									<img src="resources/image/group_image/${group.group_image}" alt="" />
								</div>
								<div class="container">
									<p>${group.content}</p>
								</div>
							</section>
						
						<!-- Two -->
							<section id="two">
								<div class="container">
									<h3>メンバー</h3>
									<p>リーダー</p>
										<div>
											<a href="viewUserForm?user_id=${leader.user_id}" class="image avatar thumb"><img src="resources/image/user_image/${leader.image_id}" alt="" style="width: 100px; height:auto;"></a>
										</div>
										<div>
									<p>メンバー</p>
										<c:forEach var="user" items="${user_list}">
											<a href="viewUserForm?user_id=${user.user_id}" class="image avatar thumb"><img src="resources/image/user_image/${user.image_id}" alt="" style="width: 100px; height:auto;"></a>
										</c:forEach>
										</div>${user.size()}
									<a href="listGroupAttendanceForm?group_category_id=${group_category.group_category_id}&group_id=${group.group_id}" class="button">メンバーページへ</a>
								</div>
							</section>
						
						<!-- Three -->
							<section id="three">
								<div class="container">
									<h3>コメント</h3>
						<div class="comments">
						<c:forEach var="group_comment" items="${group_comment_list}">
						<div class="comment-wrap">
							<div>
							<a href="viewUserForm?user_id=${group_comment.user.user_id}" class="image avatar thumb"><img src="resources/image/user_image/${group_comment.user.image_id}" alt="" style="width: 100px; height:auto;"></a>
							</div>
							<div class="comment-block">
								<p class="comment-text">${group_comment.content}</p>
									<div class="bottom-comment">
										<div class="comment-date">
										
										<%= (new SimpleDateFormat("yyyy年 MM月 dd日 HH:mm:ss")).format(new Date(((GroupComment)pageContext.getAttribute("group_comment")).getInput_date()))%>
										
										</div>
											<ul class="comment-actions">
												<li class="name"><a href="viewUserForm?user_id=${group_comment.user.user_id}">${group_comment.user.name}</a></li>
												<c:if test="${group_comment.user.user_id == sessionScope.user_id}">
													<li class="name">Edit</li>
													<li>Delete</li>
												</c:if>
											</ul>
									</div>
									<br><br><br>
									<div>
									<c:forEach var="group_comment_tag" items="${group_comment.group_comment_tag_list}">
										<a href="">#${group_comment_tag}</a>
									</c:forEach>
									</div>		
							</div>
						</div>
						</c:forEach>
						<textarea class="comment-block"></textarea><br>
						<input type="button" value="作成"><br><br><br>
						<a href="listGroupCommentForm?group_category_id=${group_category.group_category_id}&group_id=${group.group_id}" class="button">コメントページへ</a>
						
						</div>		
								</div>
							</section>
							
						<!-- Four -->
							<section id="four">
								<div class="container">
									<h3>アルバム</h3>
						
									<div class="features">
										<article class="col-6 col-12-xsmall work-item">
											<c:forEach var="event_schedule_image" items="${event_schedule_image_list}">
												<a href="resources/image/event_schedule_image/${event_schedule_image.filename}" class="image thumb"><img src="resources/image/event_schedule_image/${event_schedule_image.filename}" alt="" /></a>
											<h3 style="width:0px;height:0px;font-size:0px;line-height:0px;position:absolute;overflow:hidden;">${event_schedule_image.event_schedule_id}</h3>
											</c:forEach>
											<br>
											<video width='auto' height='auto' controls>
											<c:forEach var="video" items="${video_list}">
											<source src='resources/image/event_schedule_video/${video.filename}' type='video/mp4'>
											</c:forEach>
											</video>
											<br>
											<a href="listGroupAlbumForm?group_category_id=${group_category.group_category_id}&group_id=${group.group_id}" class="button">アルバムページへ</a>
										</article>
									</div>
								</div>
							</section>

						<!-- Five -->
							<section id="five">
								<div class="container">
									<h3>イベント</h3>
											<input type="button" value="追加">
									<div class="features">
									<c:forEach var="event" items="${event_list}">
									<article>
											<a href="viewEventForm?group_category_id=${group_category.group_category_id}&group_id=${group.group_id}&event_id=${event.event_id}" class="image"><img src="resources/image/event_image/${event.image_id}" alt="" /></a>
											<div class="inner">
												<h4>${event.name}</h4>
												<p>${event.content}</p>
											</div>
										</article>
										</c:forEach>
											</div>
										<a href="listEventForm?group_category_id=${group_category.group_category_id}&group_id=${group.group_id}" class="button">イベントページへ</a>
									</div>
									
							</section>
							<section id="six">
								<div class="container">
									<h3>연합 이벤트</h3>
									<div class="features">
									<c:forEach var="event_union_event" items="${event_union_event_list}">
									<article>
											<a href="viewEventForm?group_category_id=${group_category.group_category_id}&group_id=${group.group_id}&event_id=${event_union_event.event_id}" class="image"><img src="resources/image/event_image/${event_union_event.image_id}" alt="" /></a>
											<div class="inner">
												<h4>${event_union_event.name}</h4>
												<p>${event_union_event.content}</p>
											</div>
										</article>
										</c:forEach>
											</div>
										<a href="listEventForm?group_category_id=${group_category.group_category_id}&group_id=${group.group_id}" class="button">연합 이벤트 페이지 이동</a>
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
	document.getElementById('link1').style.visibility = "hidden";
	document.getElementById('link2').style.visibility = "visible";
	document.getElementById('link3').style.visibility = "hidden";
	document.getElementById('link4').style.visibility = "visible";
}
function link1_onclick() {
	document.getElementById('link1').style.visibility = "hidden";
	document.getElementById('link2').style.visibility = "visible";
}
function link2_onclick() {
	document.getElementById('link1').style.visibility = "visible";
	document.getElementById('link2').style.visibility = "hidden";
}
function link3_onclick() {
	document.getElementById('link3').style.visibility = "hidden";
	document.getElementById('link4').style.visibility = "visible";
}
function link4_onclick() {
	document.getElementById('link3').style.visibility = "visible";
	document.getElementById('link4').style.visibility = "hidden";
}
</script>

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