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
<script type="text/javascript"
	src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript">
	var current_date = new Date(); 
	var from_date = (new Date(current_date.getFullYear(), current_date.getMonth(), current_date.getDate(), current_date.getHours(), current_date.getMinutes())).getTime();
	var to_date = new Date(from_date + 602000000).getTime();

	console.log("test : " + new Date(from_date) + ", " + new Date(to_date));
	
	google.charts.load('current', {'packages':['timeline']});
	function setChart(schedule_list)
	{
		google.charts.setOnLoadCallback(drawChart(schedule_list));				
	}
	function handleClientLoad()
	{
		gapi.load('client:auth2', initClient);
	}
	function initClient()
	{
		gapi.client.init({
			apiKey : "AIzaSyAujlCmx3gpGvD5ZHDN3Vqwp1hG0h-J3cc",
			clientId : "823134128365-5e3gpcpbt5nvqc4mfgsbess1v9d8kj9g.apps.googleusercontent.com",
			discoveryDocs : ["https://www.googleapis.com/discovery/v1/apis/calendar/v3/rest"],
			scope : "https://www.googleapis.com/auth/calendar.readonly"
		}).then(function()
				{
			getScheduleList();
		},
		function(error)
		{
			console.log(error);
		});
	}
	function drawChart(schedule_list)
	{
		var buff = "";
		
		for(var i = 0; i < schedule_list.length; i++)
		{
			buff += "<div class='container' style='display: block; overflow-x: scroll; overflow-y: hidden; height: auto; width: 100%;'>";
			buff += "<span id='timeline" + i + "'></span>";
			buff += "<span>";
			buff += "<input type='checkbox' id='schedule_checkbox" + i + "' value='" + i + "'class='check'>";
			buff += "<input type='hidden' id='start_date" + i + "' value='" + schedule_list[i].start_date + "'>";
			buff += "<input type='hidden' id='end_date" + i + "' value='" + schedule_list[i].end_date + "'>";
			buff += "<label for='schedule_checkbox" + i + "'>選択</label></span>";
			buff += "</div>"; 
		}
		document.getElementById("two").innerHTML = buff;
		
		for(var i = 0; i < schedule_list.length; i++)
		{
			var container = document.getElementById('timeline' + i);
			var chart = new google.visualization.Timeline(container);
			var dataTable = new google.visualization.DataTable();
        
			dataTable.addColumn({ type: 'string', id: 'President' });
			dataTable.addColumn({ type: 'string', id: 'Name' });
			dataTable.addColumn({ type: 'string', id: 'style', role: 'style' });
			dataTable.addColumn({ type: 'date', id: 'Start' });
			dataTable.addColumn({ type: 'date', id: 'End' });
			
			var start_date;
			var end_date;
			
			//console.log("1 : " + new Date(schedule_list[i].start_date) + ", " + new Date(schedule_list[i].end_date));
			
			if(from_date < schedule_list[i].start_date)
			{
				start_date = schedule_list[i].start_date;
			}
			else
			{
				start_date = from_date;
			}
			if(schedule_list[i].end_date < to_date)
			{
				end_date = schedule_list[i].end_date;
			}
			else
			{
				end_date = to_date;
			}
			//console.log("2 : " + new Date(start_date) + ", " + new Date(end_date));
			if(from_date == start_date && to_date != end_date)
			{
				dataTable.addRows([
					[ '', '', 'Tomato', new Date(start_date), new Date(end_date) ],
					[ '', '', 'MediumSeaGreen', new Date(end_date), new Date(to_date) ]
					]);
			}
			else if(from_date == start_date && to_date == end_date)
			{
				dataTable.addRows([
					[ '', '', 'Tomato', new Date(start_date), new Date(end_date) ]
					]);
			}
			else if(from_date != start_date && to_date == end_date)
			{
				dataTable.addRows([
					[ '', '', 'MediumSeaGreen', new Date(from_date), new Date(start_date) ],
					[ '', '', 'Tomato', new Date(start_date), new Date(end_date) ]
					]);
			}
			else
			{
				dataTable.addRows([
					[ '', '', 'MediumSeaGreen', new Date(from_date), new Date(start_date) ],
					[ '', '', 'Tomato', new Date(start_date), new Date(end_date) ],
					[ '', '', 'MediumSeaGreen', new Date(end_date), new Date(to_date) ]
					]);
			}
			
			
			var option =
			{
				width : (to_date - from_date) / 100000,
				height: 250,
			};
			chart.draw(dataTable, option);
		}		
	}
	function joinEventScheduleAction()
	{
		var map = {};
		map["event_schedule_id"] = ${event_schedule.event_schedule_id};
		
		var schedule_id_list = [];
		
		$.each($(":checkbox:checked"), function(){            
			schedule_id_list.push($(this).val());
	    });
		
		var checked_schedule_list = [];
		
		for(var i = 0; i < schedule_id_list.length; i++)
		{
			checked_schedule_list.push({start_date: Number($("#start_date" + i).val()), end_date: Number($("#end_date" + i).val())});
		}
		
		map["schedule_list"] = checked_schedule_list;
		
		$.ajax({
			url: "joinEventScheduleAction",
			type: "POST",
			data: JSON.stringify(map),
			contentType: "application/json; charset=UTF-8",
			success: function()
			{
				location.href = "viewEventScheduleForm?group_category_id=${group_category.group_category_id}&group_id=${group.group_id}&event_id=${event.event_id}";
			},
			error: function(error){console.log(error);}
		});
	}
	function getScheduleList()
	{
		var schedule_list = new Array();
		
		if(gapi.auth2.getAuthInstance().isSignedIn.get() == true)
		{			
			var flag;
			
			flag = 0;
			gapi.client.calendar.events.list({
		          'calendarId': 'primary',
		          'timeMin': (new Date(from_date)).toISOString(),
		          'timeMax': (new Date(to_date)).toISOString(),
		          'showDeleted': false,
		          'singleEvents': true,
		          'orderBy': 'startTime'
		        }).then(function(response)
		        		{
		        			var interval = setInterval(function()
							{
								if(flag == 1){clearInterval(interval);setChart(schedule_list);}
							}, 100);
		        			for(var i = 0; i < response.result.items.length; i++)
		        			{
		        				var map = {};
		        				
		        				var time;		        				
		        				var year;
		        				var month;
		        				var date;
		        				var hour;
		        				var minute;
		        				
		        				time = response.result.items[i].start.dateTime;		        				
		        				if(time.charAt(0) == '0')
		        				{
		        					if(time.chartAt(1) == '0')
		        					{
		        						if(time.charAt(2) == '0')
		        						{
		        							if(time.charAt(3) == '0')
		        							{
		        								year = 0;
		        							}
		        							else
		        							{
		        								year = parseInt(time.substr(3, 1));
		        							}
		        						}
		        						else
		        						{
		        							year = parseInt(time.substr(2, 2));
		        						}	
		        					}
		        					else
		        					{
		        						year = parseInt(time.substr(1, 3));
		        					}
		        				}
		        				else
		        				{
		        					year = parseInt(time.substr(0, 4));
		        				}		        				
		        				if(time.charAt(5) == '0')
		        				{
		        					month = parseInt(time.substr(6, 1));
		        				}
		        				else
		        				{
		        					month = parseInt(time.substr(5, 2));
		        				}		        				
		        				if(time.charAt(8) == '0')
		        				{
		        					date = parseInt(time.substr(9, 1));
		        				}
		        				else
		        				{
		        					date = parseInt(time.substr(8, 2));
		        				}		        				
		        				if(time.charAt(11) == '0')
		        				{
		        					hour = parseInt(time.substr(12, 1));
		        				}
		        				else
		        				{
		        					hour = parseInt(time.substr(11, 2));
		        				}		        				
		        				if(time.charAt(14) == '0')
		        				{
		        					minute = parseInt(time.substr(15, 1));
		        				}
		        				else
		        				{
		        					minute = parseInt(time.substr(14, 2));
		        				}
		        				map["start_date"] = (new Date(year, month - 1, date, hour, minute)).getTime();
		        					        	
		        				time = response.result.items[i].end.dateTime;
		        				if(time.charAt(0) == '0')
		        				{
		        					if(time.chartAt(1) == '0')
		        					{
		        						if(time.charAt(2) == '0')
		        						{
		        							if(time.charAt(3) == '0')
		        							{
		        								year = 0;
		        							}
		        							else
		        							{
		        								year = parseInt(time.substr(3, 1));
		        							}
		        						}
		        						else
		        						{
		        							year = parseInt(time.substr(2, 2));
		        						}	
		        					}
		        					else
		        					{
		        						year = parseInt(time.substr(1, 3));
		        					}
		        				}
		        				else
		        				{
		        					year = parseInt(time.substr(0, 4));
		        				}		        				
		        				if(time.charAt(5) == '0')
		        				{
		        					month = parseInt(time.substr(6, 1));
		        				}
		        				else
		        				{
		        					month = parseInt(time.substr(5, 2));
		        				}		        				
		        				if(time.charAt(8) == '0')
		        				{
		        					date = parseInt(time.substr(9, 1));
		        				}
		        				else
		        				{
		        					date = parseInt(time.substr(8, 2));
		        				}		        				
		        				if(time.charAt(11) == '0')
		        				{
		        					hour = parseInt(time.substr(12, 1));
		        				}
		        				else
		        				{
		        					hour = parseInt(time.substr(11, 2));
		        				}		        				
		        				if(time.charAt(14) == '0')
		        				{
		        					minute = parseInt(time.substr(15, 1));
		        				}
		        				else
		        				{
		        					minute = parseInt(time.substr(14, 2));
		        				}		        				
		        				map["end_date"] = (new Date(year, month - 1, date, hour, minute)).getTime();
		        				
		        				//console.log(new Date(map.start_date) + ", " + new Date(map.end_date));
		        				
		        				schedule_list.push(map);
							}
		         			flag = 1;
		        });		 
		}
		else
		{
			gapi.auth2.getAuthInstance().signIn();
			gapi.auth2.getAuthInstance().isSignedIn.listen(function()
					{
						var flag;
						
						flag = 0;
						gapi.client.calendar.events.list({
					          'calendarId': 'primary',
					          'timeMin': (new Date()).toISOString(),
					          'showDeleted': false,
					          'singleEvents': true,
					          'maxResults': 10,
					          'orderBy': 'startTime'
					        }).then(function(response)
					        		{
					        			for(var i = 0; i < response.result.items.length; i++)
					        			{
					        				var map = {};
					        				map["start_date"] = response.result.items[i].start.dateTime;
					        				map["end_date"] = response.result.items[i].end.dateTime;
					        				schedule_list.push(map);
										}
					         			flag = 1;
					        });
						var interval = setInterval(function()
								{
									if(flag == 1){clearInterval(interval);}
								}, 1000);
					});
		}
	}
			
			/* 
							
							for(var i = 0; i < google_user_schedule_list.length; i++)
							{
								map.google_user_schedule_list.push({start_date: google_user_schedule_list[i].start_date, end_date: google_user_schedule_list[i].end_date});
							}
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
							clearInterval(interval);
						}
					},100);
			
		}
		else
		{
			gapi.auth2.getAuthInstance().signIn();
			gapi.auth2.getAuthInstance().isSignedIn.listen(function()
					{
				
				var map = {};
				
				map["user_id"] = "${sessionScope.user_id}";
				map["event_schedule_id"] = ${event_schedule.event_schedule_id};
				map["google_user_schedule_list"] = [];
				listUpcomingEvents();
				var interval = setInterval(function()
						{
					if(flag != 0)
							{
								for(var i = 0; i < google_user_schedule_list.length; i++)
								{
									map.google_user_schedule_list.push({start_date: google_user_schedule_list[i].start_date, end_date: google_user_schedule_list[i].end_date});
								}
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
								clearInterval(interval);
							}
						},100);
		});
		}
	} */
</script>
	<script async defer src="https://apis.google.com/js/api.js"
      onload="this.onload=function(){};handleClientLoad()"
      onreadystatechange="if (this.readyState === 'complete') this.onload()">
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
			<a href="javascript:joinEventScheduleAction()" class="button">참여</a>
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
						<footer></footer>
					</div>
				</article>
			</section>
			<section id="two">
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