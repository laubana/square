<%@page import="java.util.HashMap"%>
<%@page import="project.ppaya.square.vo.EventScheduleComment"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
	<head>
		<title>EventComment</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="resources/GroupMain/assets/css/main.css" />
		<link rel="stylesheet" href="resources/TextA/css/style.css">
	</head>
	<body class="is-preload">
		<!-- Header 메인 바 -->
			<header id="header1">
				<h1><a class="navbar-brand font-weight-bolder mr-3" href="main"><img src="resources/Main/assets/css/images/photoSquareLogo_done.png"></a></h1>
				<nav>
					<ul>
						<li><a href="joinUserForm">회원가입</a></li>
						<li><a href="createGroupForm">그룹생성</a></li>
						<li><a href="loginUserForm">로그인</a></li>
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
				</header>
				<nav id="nav">
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
						
						<!-- Three -->
							<section id="three">
								<div class="container">
									<h3>코멘트</h3>
						<div class="comments">

							<c:forEach var="element" items="${comment_list}">
						<div class="comment-wrap">
							<div>
							<a href="viewUserForm?user_id=${element.user.user_id}" class="image avatar thumb"><img src="resources/image/user_image/${element.user.image_id}" alt="" style="width: 100px; height:auto;"></a>
							</div>
							<div class="comment-block">
								<p class="comment-text" id="comment${element.comment.event_schedule_comment_id}">${element.comment.content}</p>
									<div class="bottom-comment">
										<div class="comment-date">
										
										<%= (new SimpleDateFormat("yyyy年 MM月 dd日 HH:mm:ss")).format(new Date(((EventScheduleComment)(((HashMap)pageContext.getAttribute("element")).get("comment"))).getInput_date()))%>
										
										</div>
											<ul class="comment-actions">
												<li class="name"><a href="viewUserForm?user_id=${element.user.user_id}">${element.user.name}</a></li>
												<li class="name" id="translation_button${element.comment.event_schedule_comment_id}"><a href="javascript:getEventScheduleCommentTranslation(${element.comment.event_schedule_comment_id})">翻訳</a></li>
											<c:if test="${element.user.user_id == sessionScope.user_id}">
												<li class="name">Edit</li>
												<li>Delete</li>
											</c:if>
													<select id="translation_language${element.comment.event_schedule_comment_id}">
														  <option value="en">英語</option>
														  <option value="ko">韓国語</option>
													</select>											
											</ul>
									</div>
									<br><br><br>
									<div>
									<c:forEach var="tag" items="${element.tag_list}">
										<a href="viewMindMapForm?hashtag=${tag}">#${tag}</a>
									</c:forEach>
									</div>		
							</div>
						</div>
						</c:forEach>
		
						</div>		
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
<script>
function getEventScheduleCommentTranslation(event_schedule_comment_id)
{
	var map = {};
	map["event_schedule_comment_id"] = event_schedule_comment_id;
	map["language"] = document.getElementById("translation_language" + event_schedule_comment_id).value; 
	
	$.ajax({
		url: "getEventScheduleCommentTranslation",
		type: "POST",
		data: JSON.stringify(map),
		dataType: "JSON",
		contentType: "application/json; charset=UTF-8",
		success: function(jsonObject)
		{
			var result = decodeURIComponent(jsonObject.result.replace(/\+/g, " "));
			
			document.getElementById("comment" + event_schedule_comment_id).innerHTML = result;
			document.getElementById("translation_button" + event_schedule_comment_id).innerHTML = '<a href="javascript:resetEventScheduleComment(' + event_schedule_comment_id + ')">リセット</a>';
		},
		error: function(error){console.log(error);}
	});
}
function resetEventScheduleComment(event_schedule_comment_id)
{
	var map = {};
	map["event_schedule_comment_id"] = event_schedule_comment_id;
	
	$.ajax({
		url: "resetEventScheduleComment",
		type: "POST",
		data: JSON.stringify(map),
		dataType: "JSON",
		contentType: "application/json; charset=UTF-8",
		success: function(jsonObject)
		{
			var result = decodeURIComponent(jsonObject.result.replace(/\+/g, " "));
			
			document.getElementById("comment" + event_schedule_comment_id).innerHTML = result;
			document.getElementById("translation_button" + event_schedule_comment_id).innerHTML = '<a href="javascript:getEventScheduleCommentTranslation(' + event_schedule_comment_id +')">翻訳</a>';
		},
		error: function(error){console.log(error);}
	});
}
</script>
</html>