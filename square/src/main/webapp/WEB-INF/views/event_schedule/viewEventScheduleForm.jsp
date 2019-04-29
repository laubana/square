<%@page import="project.ppaya.square.vo.EventSchedule"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.HashMap"%>
<%@page import="project.ppaya.square.vo.EventScheduleComment"%>
<%@page import="java.text.SimpleDateFormat"%>
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
		<title>みんなみんな</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="resources/EventView/assets/css/main.css" />
		<link rel="stylesheet" href="resources/GroupMain/assets/css/main.css" />
		<link rel="stylesheet" href="resources/TextA/css/style.css">
		<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
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
							[ json_event_schedule_user_schedule_list_list[i].user.user_id, '', 'Tomato', new Date(json_event_schedule_user_schedule_list_list[i].list[j].start_date), new Date(json_event_schedule_user_schedule_list_list[i].list[j].end_date) ]
							]);
					}
					else
					{
						dataTable.addRows([
							[ json_event_schedule_user_schedule_list_list[i].user.user_id, '', 'MediumSeaGreen', new Date(json_event_schedule_user_schedule_list_list[i].list[j].start_date), new Date(json_event_schedule_user_schedule_list_list[i].list[j].end_date) ]
							]);
					}
					var option = {
							width : (json_event_schedule_user_schedule_list_list[i].list[json_event_schedule_user_schedule_list_list[i].list.length - 1].end_date - json_event_schedule_user_schedule_list_list[i].list[0].start_date) / 50000,
							height: 250
							};
					chart.draw(dataTable, option);
        
					document.getElementById('timeline_image' + json_event_schedule_user_schedule_list_list[i].user.user_id).innerHTML = "<a href='viewUserForm?user_id=" + json_event_schedule_user_schedule_list_list[i].user.user_id + "' class='image avatar thumb'><img src='resources/image/user_image/" + json_event_schedule_user_schedule_list_list[i].user.image_id + "' alt='' style='width: 100px; height:auto;'></a>";
				}
			}
		}
		
     	google.charts.setOnLoadCallback(drawChart1);
		function drawChart1()
		{
			var container = document.getElementById('timeline');
			var chart = new google.visualization.Timeline(container);
			var dataTable = new google.visualization.DataTable();
        
			dataTable.addColumn({ type: 'string', id: 'President' });
			dataTable.addColumn({ type: 'string', id: 'Name' });
			dataTable.addColumn({ type: 'string', id: 'style', role: 'style' });
			dataTable.addColumn({ type: 'string', role: 'tooltip', p: {'html': true}});
			dataTable.addColumn({ type: 'date', id: 'Start' });
			dataTable.addColumn({ type: 'date', id: 'End' });
			
			var attendance_count = ${event_schedule_attendace_count_list_map.attendance_count};
			var count;
			var rate;
			
			<c:forEach var="event_schedule_attendace_count" items="${event_schedule_attendace_count_list_map.list}">
				count = ${event_schedule_attendace_count.typeof};
				rate = Math.floor(((count / attendance_count) * 10) / 2) * 10;				
			
				dataTable.addRows([
						[ '参加者の最大人数', '', hsl2hex(9, 100, 80 - rate), "<div style='padding: 10px 10px 10px 10px;'>${event_schedule_attendace_count.typeof}</div>", new Date(${event_schedule_attendace_count.start_date}), new Date(${event_schedule_attendace_count.end_date}) ]
						]);
			</c:forEach>
        	
        	var option = {
					//width : (list[list.length - 1].end_date - list[0].start_date) / 50000,
					height: 250,
					tooltip: {isHtml: true}
				};
				chart.draw(dataTable, option);
        }
    </script>
	<script>
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
				location.reload();
			},
			error: function(error){console.log(error);}
		});
	}
	function joinEventScheduleAction()
	{
		location.href = "joinEventScheduleForm?${group_category.group_category_id}?group_id=${group.group_id}&event_id=${event.event_id}&event_schedule_id=${event_schedule.event_schedule_id}";
	}	
	function hsl2hex(h, s, l) {
	    var m1, m2, hue;
	    var r, g, b
	    s /=100;
	    l /= 100;
	    if (s == 0)
	        r = g = b = (l * 255);
	    else {
	        if (l <= 0.5)
	            m2 = l * (s + 1);
	        else
	            m2 = l + s - l * s;
	        m1 = l * 2 - m2;
	        hue = h / 360;
	        r = Math.round(HueToRgb(m1, m2, hue + 1/3));
	        g = Math.round(HueToRgb(m1, m2, hue));
	        b = Math.round(HueToRgb(m1, m2, hue - 1/3));
	    }
	    return "#" + componentToHex(r) + componentToHex(g) + componentToHex(b);
	}
	function HueToRgb(m1, m2, hue) {
	    var v;
	    if (hue < 0)
	        hue += 1;
	    else if (hue > 1)
	        hue -= 1;

	    if (6 * hue < 1)
	        v = m1 + (m2 - m1) * hue * 6;
	    else if (2 * hue < 1)
	        v = m2;
	    else if (3 * hue < 2)
	        v = m1 + (m2 - m1) * (2/3 - hue) * 6;
	    else
	        v = m1;

	    return 255 * v;
	}
	function componentToHex(c) {
	    var hex = c.toString(16);
	    return hex.length == 1 ? "0" + hex : hex;
	}
	function clickUploadEventScheduleImageFile()
	{
		document.getElementById("insert_event_schedule_image_file").click();
	}
	function insertEventScheduleImageAction()
	{
		var file = document.getElementById("insert_event_schedule_image_file").files[0];
		
		if(file.type == "image/jpg" || file.type == "image/JPG" ||
				file.type == "image/png" || file.type == "image/PNG" ||
				file.type == "image/jpeg" || file.type == "image/JPEG" || 
				file.type == "image/bmp" || file.type == "image/BMP" ||
				file.type == "image/gif" || file.type == "image/GIF")
		{
			var map = {};
			map["filename"] = file.name;
			map["group_category_id"] = ${group_category.group_category_id};
			map["group_id"] = ${group.group_id};
			map["event_id"] = ${event.event_id};
			map["event_schedule_id"] = ${event_schedule.event_schedule_id};
			
			var image = new FileReader();
			image.readAsDataURL(file);
			image.onload = function()
			{
	            map["image"] = image.result.substring(image.result.indexOf(",") + 1);
	        }

			var interval = setInterval(function()
					{
						if(typeof(map.image) != "undefined")
						{								
							$.ajax({
				    			url: "insertEventScheduleImageAction",
				    			type: "POST",
				    			data: JSON.stringify(map),
				    			dataType: "text",
				    			contentType: "application/json; charset=UTF-8",
				    			success: function(result)
				    			{
				    				if(result == success)
				    				{
				    					location.reload();
				    				}
				    			},
				    			error: function(){}
				    				});
							clearInterval(interval);
						}
					},100);
		}
		else
		{
			alert("イメージのファイルではありません。");
		}
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
		map["event_schedule_comment_id"] = comment_id;
		map["content"] = document.getElementById("update_comment_textarea" + comment_id).value;
		
		$.ajax({
			url: "updateEventScheduleCommentAction",
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
	function deleteCommentAction(comment_id)
	{
		if(confirm("削除？"))
		{
			var map = {};
			map["event_schedule_comment_id"] = comment_id;
			
			$.ajax({
				url: "deleteEventScheduleCommentAction",
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
	}
	function writeEventScheduleCommentAction()
	{
		var map = {};
		map["event_schedule_id"] = ${event_schedule.event_schedule_id};
		map["content"] = $("#write_comment_content").val();
	
		$.ajax({
			url: "writeEventScheduleCommentAction",
			type: "POST",
			data: JSON.stringify(map),
			contentType: "application/json; charset=UTF-8",
			success: function(){location.reload();},
			error: function(error){console.log(error);}
		});
	}
	</script>
	<!-- 맵 띄우는 스크립트 -->
	<style>
		#map {
			width: 750px;
			height: 500px;
			position: relative !important; /* changing this to fixed makes the map dissapear */
			top: 0; 
			bottom: 0; 
			left: 0; 
			right: 0; 
			z-index: 0;
	     }
		html,body {height: 100%; margin: 0; padding: 0;}
	</style>
	</head>
	<body class="is-preload">

		<!-- Header 메인 바 -->
			<header id="header1">
				<h1><a class="navbar-brand font-weight-bolder mr-3" href="main"><img src="resources/Main/assets/css/images/photoSquareLogo_done.png"></a></h1>
				<nav>
					<ul>
						<li><a href="listRecommendationForm"></a>
						<c:if test="${sessionScope.user_id != null}">
						<li>${sessionScope.user_id}</li>
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
					<h1 id="logo"><a href="viewGroupForm?group_category_id=${group_category.group_category_id}&group_id=${group.group_id}">${group.name}</a></h1>
					<p style="font-size:15px;">
					<c:forEach var="group_hashtag" items="${group_hashtag_list}">
							<a href="viewMindMapForm?hashtag=${group_hashtag.hashtag}">#${group_hashtag.hashtag}</a>
						</c:forEach>
					</p>
					<c:if test="${sessionScope.user_id != null}">
						<c:if test="${group_attendance != null}">
							<c:if test="${event_attendance != null}">
								<c:if test="${event_schedule_attendance != null}">
									<a href="javascript:withdrawEventScheduleAction()" class="button">脱退</a>
								</c:if>
								<c:if test="${event_schedule_attendance == null}">
									<a href="javascript:joinEventScheduleAction()" class="button">参加</a>
								</c:if>
							</c:if>
						</c:if>
					</c:if>
				</header>
				<nav id="nav">
					<ul>
						<li><a href="#one" class="active">イベント・スケジュール情報</a></li>
						<li><a href="#two">メンバー</a></li>
						<li><a href="#three">コメント</a></li>
						<li><a href="#four">アルバム</a></li>
						<li><a href="#five">メンバー ・スケジュール</a></li>
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

						<!-- Five -->
							<section id="one">
								<article class="post">
								<header>
									<div class="title">
										<h2><a href="">${event_schedule.name}</a></h2>
									<c:if test="${event_schedule.start_date != 0 && event_schedule.end_date != 0}">
										<h6 style="display: inline;"><%= (new SimpleDateFormat("yyyy年 MM月 dd日")).format(new Date(((EventSchedule)(request.getAttribute("event_schedule"))).getStart_date()))%></h6>
										<h6 style="display: inline;"> ~ </h6>
										<h6 style="display: inline;"><%= (new SimpleDateFormat("yyyy年 MM月 dd日")).format(new Date(((EventSchedule)(request.getAttribute("event_schedule"))).getEnd_date()))%></h6>
									</c:if>
									<c:if test="${event_schedule.start_date == 0 || event_schedule.end_date == 0}">
										<h6>未定</h6>
									</c:if>
									</div>
									<div class="meta">
										<time class="published"><%= (new SimpleDateFormat("yyyy年 MM月 dd日")).format(new Date(((EventSchedule)(request.getAttribute("event_schedule"))).getInput_date()))%></time>
										<a href="viewUserForm?user_id=${leader.user_id}" class="author"><span class="name">${leader.name}</span><img src="resources/image/user_image/${leader.image_id}" alt="" /></a>
									</div>
								</header>
								<p>
									${event_schedule.content}
								</p>
								<hr>
								<!-- 맵 -->
								<h3>イベント・スケジュール場所</h3>
								<div align ="center">
									<div id = "map"></div>
									<br>
									<a style="font-size: 25px;"><strong>場所:</strong> ${requestScope.event_schedule.address}</a>
								</div>
								
								
								<div align="right"><footer>			
								</footer></div>
							</article>
						</section>			
					</div>
					
				<div id="main">
					<!-- Two -->
							<section id="two">
								<div class="container">
									<h3>メンバー</h3>
										<div>
									<p>リーダー</p>
											<a href="viewUserForm?user_id=${leader.user_id}" class="image avatar thumb"><img src="resources/image/user_image/${leader.image_id}" alt="" style="width: 100px; height:auto;"></a>
										</div>
										<div>
										<br>
									<p>メンバー</p>
										<c:forEach var="user" items="${user_list}">
											<a href="viewUserForm?user_id=${user.user_id}" class="image avatar thumb"><img src="resources/image/user_image/${user.image_id}" alt="" style="width: 100px; height:auto;"></a>
										</c:forEach>
										</div>
										<br>
									<a href="listEventScheduleAttendanceForm?group_category_id=${group_category.group_category_id}&group_id=${group.group_id}&event_id=${event.event_id}&event_schedule_id=${event_schedule.event_schedule_id}" class="button">メンバーリストページへ</a>
								</div>
							</section>
					<!-- Three -->
							<section id="three">
								<div class="container">
									<h3>コメント</h3>
						<div class="comments">
						
						<c:forEach var="element" items="${comment_list}">
						<div class="comment-wrap">
							<div>
							<a href="viewUserForm?user_id=${element.user.user_id}" class="image avatar thumb"><img src="resources/image/user_image/${element.user.image_id}" alt="" style="width: 100px; height:auto;"></a>
							</div>
							<div class="comment-block">
							<div id="comment_content_div${element.comment.event_schedule_comment_id}">
								<p class="comment-text" id="comment${element.comment.event_schedule_comment_id}">${element.comment.content}</p>
								</div>
									<div class="bottom-comment">
										<div class="comment-date">
										
										<%= (new SimpleDateFormat("yyyy年 MM月 dd日 HH:mm:ss")).format(new Date(((EventScheduleComment)(((HashMap)pageContext.getAttribute("element")).get("comment"))).getInput_date()))%>
										
										</div>
											<ul class="comment-actions">
												<li class="name"><a href="viewUserForm?user_id=${element.user.user_id}">${element.user.name}</a></li>
												<li class="name" id="translation_button${element.comment.event_schedule_comment_id}"><a href="javascript:getEventScheduleCommentTranslation(${element.comment.event_schedule_comment_id})">翻訳</a></li>
											<c:if test="${element.user.user_id == sessionScope.user_id}">
												<li class="name" id="update_comment_button${element.comment.event_schedule_comment_id}"><a href="javascript:showUpdateCommentForm(${element.comment.event_schedule_comment_id})">Edit</a></li>
												<li class="name" id="delete_comment_button${element.comment.event_schedule_comment_id}"><a href="javascript:deleteCommentAction(${element.comment.event_schedule_comment_id})">Delete</a></li>
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
						<c:if test="${sessionScope.user_id != null}">
						<c:if test="${group_attendance != null}">
							<c:if test="${event_attendance != null}">
								<c:if test="${event_schedule_attendance != null}">
									<textarea class="comment-block" id="write_comment_content"></textarea><br>
						<div align="right"><input type="button" id="write_comment_button" onclick="writeEventScheduleCommentAction()" value="作成"></div><br><br>
								</c:if>
							</c:if>
						</c:if>
					</c:if>
						
						<a href="listEventScheduleCommentForm?group_category_id=${group_category.group_category_id}&group_id${group.group_id}&event_id=${event.event_id}&event_schedule_id=${event_schedule.event_schedule_id}" class="button">コメントリストページへ</a>		
								</div>
							</section>

					<!-- Four -->
							<section id="four" >
								<div class="container">
									<h3>アルバム</h3>
					<c:if test="${sessionScope.user_id != null}">
						<c:if test="${group_attendance != null}">
							<c:if test="${event_attendance != null}">
								<c:if test="${event_schedule_attendance != null}">
								<div class="features" align="right">
								<input type="file" id="insert_event_schedule_image_file" style="display:none;" onchange="insertEventScheduleImageAction()">
									<a href="javascript:clickUploadEventScheduleImageFile()" class="button">追加</a><br><br><br>
									</div>
								</c:if>
							</c:if>
						</c:if>
					</c:if>
					<h4>写真</h4>
									<div class="features" align="center">
										<article class="col-6 col-12-xsmall work-item">
											<c:forEach var="element" items="${image_list}">
												<a href="resources/image/event_schedule_image/${element.image.event_schedule_image_id}" class="image thumb"><img src="resources/image/event_schedule_image/${element.image.event_schedule_image_id}" alt="" /></a>
											<h3 style="width:0px;height:0px;font-size:0px;line-height:0px;position:absolute;overflow:hidden;">${element.image.description}</h3>
											</c:forEach>
											<br>
									</div>
										<h4>映像</h4>
									<div class="features" align="center">
											<c:if test="${video_list.size() != 0}">
											<video width='640' height='auto' controls>
											<c:forEach var="video" items="${video_list}">
											<source src='resources/image/event_schedule_video/${video.filename}' type='video/mp4'>
											</c:forEach>
											</video>
											</c:if>
											<br>
											<div class="features" align="left">
											<a href="listEventScheduleAlbumForm?group_category_id=${group_category.group_category_id}&group_id=${group.group_id}&event_id=${event.event_id}&event_schedule_id=${event_schedule.event_schedule_id}" class="button">アルバムページへ</a>
											</div>
										
									</div>
								</div>
							</section>
							
							<!-- 관리 -->
							<section id="five">
							<c:if test="${sessionScope.user_id == leader.user_id}">
							
								<div class="container">	
									<h3>メンバー・スケジュール</h3>
									<c:forEach var="event_schedule_user_schedule_list" items="${event_schedule_user_schedule_list_list}">
									<div id="timeline_image${event_schedule_user_schedule_list.user.user_id}">
										</div>
										<div id="timeline${event_schedule_user_schedule_list.user.user_id}" style="display: block; overflow-x: scroll; overflow-y: hidden; height: auto; width: 100%">
										</div>
									</c:forEach>
									<div id="timeline" style="display: block; overflow-x: scroll; overflow-y: hidden; height: auto; width: 100%">
									</div>
									</div>
							</c:if>
							</section>
				</div>
				
		</div>		
				<!-- Footer -->
					<section id="footer">
						<div class="container">
							<ul class="copyright">
								<li>&copy; Untitled. All rights reserved.</li><li>Design: <a href="http://html5up.net">HTML5 UP</a></li>
							</ul>
						</div>
					</section>

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
<!-- 맵 띄우는 스크립트 -->
<script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCdC1Oa4xE2ub87g1ouqeRxqapzLLg4shg&callback=initMap&language=ja&region=JP"">
</script>
<!-- 서버에서 주소 받아다가, 검색해서 좌표 받고, 그 좌표로 맵 중앙을 바꾸고 마커 띄우기 -->
<script>
function initMap() {
    var latlng = new google.maps.LatLng( ${requestScope.event_schedule.latitude}, ${requestScope.event_schedule.longitude});
    var mapOptions = {
    	      zoom: 15,
    	      center: latlng
    	    }
    var map = new google.maps.Map(document.getElementById('map'), mapOptions);
	var marker = new google.maps.Marker({ 
		map: map,
		position: latlng
		});
}
</script>


	</body>
</html>