<%@page import="java.util.HashMap"%>
<%@page import="project.ppaya.square.vo.EventComment"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
	<head>
		<title>みんなみんな</title>
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
						<li><a href="listRecommendationForm"><span id="realTimeHashTag"></span></a></li>
						<c:if test="${sessionScope.user_id != null}">
						<li><a href="viewUserForm?user_id=${sessionScope.user_id}">${sessionScope.user_id}</a></li>
						<li><a href="createGroupForm">グループ開設</a></li>
					<li><a href="javascript:logoutUserAction()"><strong style="color:#778899;">ログアウト</strong></a></li>
						</c:if>
						<c:if test="${sessionScope.user_id == null}">
						<li><a href="joinUserForm">ユーザー登録</a></li>
							<li><a href="loginUserForm"><strong style="color:#778899;">ログイン</strong></a></li>
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
							<a href="viewMindMapForm?hashtag=${group_hashtag.hashtag}">#${group_hashtag.hashtag}</a>
						</c:forEach>
					</p>
				</header>
				<nav id="nav">
					<ul>
						<li><a href="#three" class="active">コメントリスト</a></li>
					</ul>
				</nav>
				<footer>
					<ul class="icons">
						<li><a href="https://twitter.com" class="icon fa-twitter"><span class="label">Twitter</span></a></li>
						<li><a href="https://www.facebook.com" class="icon fa-facebook"><span class="label">Facebook</span></a></li>
						<li><a href="https://www.instagram.com" class="icon fa-instagram"><span class="label">Instagram</span></a></li>
						<li><a href="https://kr.linkedin.com" class="icon fa-linkedin"><span class="label">LinkedIn</span></a></li>
						<li><a href="https://dribbble.com" class="icon fa-dribbble"><span class="label">Dribbble</span></a></li>
						<li><a href="https://co.pinterest.com" class="icon fa-pinterest"><span class="label">Pinterest</span></a></li>
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
									<h3>コメントリスト</h3>
						<div class="comments">

							<c:forEach var="element" items="${comment_list}" end="2">
						<div class="comment-wrap">
							<div>
							<a href="viewUserForm?user_id=${element.user.user_id}" class="image avatar thumb"><img src="resources/image/user_image/${element.user.image_id}" alt="" style="width: 100px; height:auto;"></a>
							</div>
							<div class="comment-block">
							<div id="comment_content_div${element.comment.event_comment_id}">
								<p class="comment-text" id="comment${element.comment.event_comment_id}">${element.comment.content}</p>
								</div>
									<div class="bottom-comment">
										<div class="comment-date">
										
										<%= (new SimpleDateFormat("yyyy年 MM月 dd日 HH:mm:ss")).format(new Date(((EventComment)(((HashMap)pageContext.getAttribute("element")).get("comment"))).getInput_date()))%>
										
										</div>
											<ul class="comment-actions">
												<li class="name"><a href="viewUserForm?user_id=${element.user.user_id}">${element.user.name}</a></li>
												<li class="name" id="translation_button${element.comment.event_comment_id}"><a href="javascript:getEventCommentTranslation(${element.comment.event_comment_id})">翻訳</a></li>
											<c:if test="${element.user.user_id == sessionScope.user_id}">
												<li class="name" id="update_comment_button${element.comment.event_comment_id}"><a href="javascript:showUpdateCommentForm(${element.comment.event_comment_id})">Edit</a></li>
												<li class="name" id="delete_comment_button${element.comment.event_comment_id}"><a href="javascript:deleteCommentAction(${element.comment.event_comment_id})">Delete</a></li>
											</c:if>
													<select id="translation_language${element.comment.event_comment_id}">
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
	function getEventCommentTranslation(event_comment_id)
	{
		var map = {};
		map["event_comment_id"] = event_comment_id;
		map["language"] = document.getElementById("translation_language" + event_comment_id).value; 
		
		$.ajax({
			url: "getEventCommentTranslation",
			type: "POST",
			data: JSON.stringify(map),
			dataType: "JSON",
			contentType: "application/json; charset=UTF-8",
			success: function(jsonObject)
			{
				var result = decodeURIComponent(jsonObject.result.replace(/\+/g, " "));
				
				document.getElementById("comment" + event_comment_id).innerHTML = result;
				document.getElementById("translation_button" + event_comment_id).innerHTML = '<a href="javascript:resetEventComment(' + event_comment_id + ')">リセット</a>';
			},
			error: function(error){console.log(error);}
		});
	}
	function resetEventComment(event_comment_id)
	{
		var map = {};
		map["event_comment_id"] = event_comment_id;
		
		$.ajax({
			url: "resetEventComment",
			type: "POST",
			data: JSON.stringify(map),
			dataType: "JSON",
			contentType: "application/json; charset=UTF-8",
			success: function(jsonObject)
			{
				var result = decodeURIComponent(jsonObject.result.replace(/\+/g, " "));
				
				document.getElementById("comment" + event_comment_id).innerHTML = result;
				document.getElementById("translation_button" + event_comment_id).innerHTML = '<a href="javascript:getEventCommentTranslation(' + event_comment_id +')">翻訳</a>';
			},
			error: function(error){console.log(error);}
		});
	}
	function showUpdateCommentForm(comment_id)
	{
		var content = document.getElementById("comment" + comment_id).innerHTML;
		var content_div = document.getElementById("comment_content_div" + comment_id);
		var button_li = document.getElementById("update_comment_button" + comment_id);
		
		content_div.innerHTML = "<textarea id='update_comment_textarea" + comment_id + "' class='comment-block'>" + content + "</textarea>";
		button_li.innerHTML = "<a href='javascript:updateCommentAction(" + comment_id + ")'>Submit</a>";				
	}
	function updateCommentAction(comment_id)
	{
		var map = {};
		map["event_comment_id"] = comment_id;
		map["content"] = document.getElementById("update_comment_textarea" + comment_id).value;
		
		$.ajax({
			url: "updateEventCommentAction",
			type: "POST",
			data: JSON.stringify(map),
			dataType: "text",
			contentType: "application/json; charset=UTF-8",
			success: function(result)
			{
				if(result == "success")
				{
					location.reload();
				}
			},
			error: function(error){console.log(error);}
		});
	}
	function deleteCommentAction(comment_id)
	{
		if(confirm("削除？"))
		{
			var map = {};
			map["event_comment_id"] = comment_id;
			
			$.ajax({
				url: "deleteEventCommentAction",
				type: "POST",
				data: JSON.stringify(map),
				dataType: "text",
				contentType: "application/json; charset=UTF-8",
				success: function(result)
				{
					if(result == "success")
					{
						location.reload();
					}
				},
				error: function(error){console.log(error);}
			});
		}
	}
	</script>
</html>