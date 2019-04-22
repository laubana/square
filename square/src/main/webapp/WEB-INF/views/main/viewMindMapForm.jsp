<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" >

<head>
  <title>みんな・みんな</title>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
  <link href="https://fonts.googleapis.com/css?family=Montserrat:300,400,500" rel="stylesheet">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
  <link rel="stylesheet" href="resources/star1/css/style.css">
  <link rel="stylesheet" href="resources/starback4/css/style.css">
  <link rel="stylesheet" href="resources/MyPage/assets/css/main.css" />
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
  <link rel="stylesheet" href="resources/undermenu/css/style.css">
  <link rel="stylesheet" type="text/css" href="resources/MindMap/css/js-mindmap.css" />
  <link href="resources/MindMap/css/style.css" type="text/css" rel="stylesheet"/>
  
<!-- <style type="text/css">
body{
background: url('resources/MindMap/image/3.jpg'); 
  -webkit-background-size: cover;
  -moz-background-size: cover;
  -o-background-size: cover;
  background-size: cover;
}
</style> -->
  
</head>

<body>
<canvas id="canvas"></canvas>
<ul>
	<li><a href="">#${root}</a>
		<ul>
			<c:forEach var="element1" items="${list}">
				<li><a href="viewGroupForm?group_category_id=${element1.node.group_category_id}&group_id=${element1.node.group_id}">${element1.node.group_id}</a>
					<ul>
						<c:forEach var="element2" items="${element1.list}">
							<li><a href="#">#${element2.node}</a>
								<ul>
									<c:forEach var="element3" items="${element2.list}">
										<li><a href="viewGroupForm?group_category_id=${element3.node.group_category_id}&group_id=${element3.node.group_id}">${element3.node.group_id}</a>
											<ul>
												<c:forEach var="element4" items="${element3.list}">
													<li><a href="#">#${element4.node}</a>
													</li>
												</c:forEach>
											</ul>
										</li>
									</c:forEach>
								</ul>							
							</li>
						</c:forEach>
					</ul>
				</li>
			</c:forEach>				
		</ul>
	</li>
</ul>

<article id="slider">
  
  <div id="thumbnail" class="thumbnail left">
    <ul class="thumbnail-list">
      <li class="">
<!-- 맨 우측 좌측 상단 별 -->      
<svg class="star" x="0px" y="0px" width="96px" height="96px" viewBox="0 0 66 66" enable-background="new 0 0 66 66">
  <defs>
    <path id="loader-star" d="M31.75,30.5L33,0.5l1.25,30l1.25,1.25l30,1.25l-30,1.25l-1.25,1.25L33,65.5l-1.25-30l-1.25-1.25L0.5,33l30-1.25L31.75,30.5z
"/>
  </defs>
  <g class="large">
    <g class="large-1"><use xlink:href="#loader-star"/></g>
    <g class="large-2"><use xlink:href="#loader-star"/></g>
  </g>
  <g class="small">
    <g class="small-1"><use xlink:href="#loader-star"/></g>
  </g>
</svg>

		<!-- 우측  그룹 이름 및 태그 출력-->
		
        <div align="center" id="group_name" style="font-size:20px; ">グループ名前</div>
        <br>
        <hr><br><div align="center"><p style="font-size:15px;">ハッシュタグ</p></div><br> 
        <div id="hashtag_list">
        <p align="center">#タグ</p>
        </div><br>
        <hr><br><div align="center">↕</div>
        <br><hr><br><div align="center"><p style="font-size:15px;">イベント・スケジュール</p></div><br>
        <div id="event_schedule_list"></div>
		<br><hr>
		
		<%-- <c:forEach var="element1" items="${list}">
        <div align="center"><span><a href="main">${element1.node.name }</a></span></div>
        <br>
        	<c:forEach var="element2" items="${element1.list}">
        		<hr>
        			<p align="center"><a href="main">${element2.node }</a></p>
        		<hr>
        	</c:forEach>
        </c:forEach> --%>
        
      </li>
     
    </ul>
  </div>
</article>

<script src="resources/Basic/assets/js/jquery-3.3.1.min.js"></script>
<script src="resources/undermenu/js/index.js"></script>
<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
<script src="resources/starback4/js/index.js"></script>
  <!-- jQuery -->
  <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.6.2/jquery.min.js" type="text/javascript"></script>
  <!-- UI, for draggable nodes -->
  <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.15/jquery-ui.min.js"></script>

  <!-- Raphael for SVG support (won't work on android) -->
  <script type="text/javascript" src="resources/MindMap/js/raphael-min.js"></script>

  <!-- Mindmap -->
  <script type="text/javascript" src="resources/MindMap/js/js-mindmap.js"></script>

  <!-- Kick everything off -->
  <script src="resources/MindMap/js/script.js" type="text/javascript"></script>
<script>
//////////////////////////////////////////////////////////////////////////////
console.log(JSON.parse('${json_list}'));
</script>

<script>
function setGroupAction(group_id)
{
	var map = {};
	map["group_id"] = group_id;
	
	$.ajax({
		url: "setGroupAction",
		type: "POST",
		data: JSON.stringify(map),
		contentType: "application/json; charset=UTF-8",
		success: function(result)
		{
			document.getElementById("group_name").innerHTML = result.group.name;
						
			var buff = "";
			for(var i = 0; i < result.group_hashtag_list.length; i++)
			{
				buff += "<p>#" + result.group_hashtag_list[i].hashtag + "</p>";
			}
			
			document.getElementById("hashtag_list").innerHTML = buff;
			
			buff = "";
			
			for(var i = 0; i < 5; i++)
			{
				if(typeof(result.event_schedule_list[i]) == 'undefined')
				{
					break;
				}
				buff += "<p>" + result.event_schedule_list[i].name + "</p>";
			}
			
			document.getElementById("event_schedule_list").innerHTML = buff;
		},
		error: function(){}
			});
}

</script>

</body>

</html>
