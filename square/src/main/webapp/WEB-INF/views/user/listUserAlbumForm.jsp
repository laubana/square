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
		<title>My Photo</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="resources/MemberPhoto/assets/css/main.css" />
		
	
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
	
	
	
	</head>
	<body class="is-preload">

		<!-- Header -->
			<header id="header">
				<div class="inner">
					<div align="left"><h1 style="font-size:50px;"><a href="main">Web Site Name</a></h1></div>
					<a class="image avatar"><img src="resources/image/user_image/${user.image_id}" alt="" /></a>
					<h1><strong>${user.name}</strong>님의 그룹 사진<br /></h1>
					<p>사진과 동영상을 찾아보세요!<br>
					${user.name}님과의 추억을<br>
					Web Site Name가 함께합니다!
					</p>
					<nav id="nav">
					
					</nav>
				</div>
			</header>



		<!-- Main -->
			<div id="main">

					<!-- Three -->
					<section id="three">
						<h1>그룹 선택</h1>
						<!-- Form 그룹 -->
						<form action="">
						<div>
								<ul class="features">
									<c:forEach var="group" items="${group_list}">
										<li>
											<input type="checkbox" id="gruopCheckbox${group.group_id}" name="groupCheckbox" class="check" value="${group.group_id}" group_id="${group.group_id}">
											<label for="gruopCheckbox${group.group_id}">
											<h3 align="center">${group.group_id}</h3>	
											</label>
										</li>
									</c:forEach>
								</ul>
								<div align="center">
											<input type="checkbox" id="faceCheckbox" name="faceCheckbox" class="check">
											<label for="faceCheckbox">
											<h3 align="center" style="color:maroon;">Face</h3>
											</label>
											<br>	
								</div>
						</div>
						</form>
						<!-- Form 그룹 끝 -->
					</section>

				<!-- Four -->
					<section id="four">
						<h1>アルバム編集</h1>
						<!-- Form 사진 영상 -->
						<form>
						<div id="image_album" class="row"></div>
						<%-- <c:forEach var="image" items="${event_schedule_image_list}">
							<article class="col-6 col-12-xsmall work-item">
								<input type="checkbox" id="albumCheckbox${image.event_schedule_image_id}" />
								<label for="albumCheckbox${image.event_schedule_image_id}">
								<img src="resources/image/event_schedule_image/${image.event_schedule_image_id}" alt="" />
								</label>
							</article>
						</c:forEach> --%>

					<hr><br>
					<h1>映像編集</h1><br>
					<div class="row" id="video"></div><br>	
					<div align="center"><input type="button" value="確認" onclick="setAlbumAction()"></div>		
					</form>

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
			<script src="resources/MemberPhoto/assets/js/jquery.min.js"></script>
			<script src="resources/MemberPhoto/assets/js/jquery.poptrox.min.js"></script>
			<script src="resources/MemberPhoto/assets/js/browser.min.js"></script>
			<script src="resources/MemberPhoto/assets/js/breakpoints.min.js"></script>
			<script src="resources/MemberPhoto/assets/js/util.js"></script>
			<script src="resources/MemberPhoto/assets/js/main.js"></script>

<script>
function setAlbumAction()
{
	var map = {};
	var checked_event_schedule_image_id_list = [];
	var unchecked_event_schedule_image_id_list = [];
	var checked_event_schedule_video_id_list = [];
	var unchecked_event_schedule_video_id_list = [];
	
	$.each($(":checkbox[name='photo_check_box']:checked"), function(){            
		checked_event_schedule_image_id_list.push($(this).val());
    });
	$.each($(":checkbox[name='photo_check_box']:not(:checked)"), function(){            
		unchecked_event_schedule_image_id_list.push($(this).val());
    });
	$.each($(":checkbox[name='video_check_box']:checked"), function(){            
		checked_event_schedule_video_id_list.push($(this).val());
    });
	$.each($(":checkbox[name='video_check_box']:not(:checked)"), function(){            
		unchecked_event_schedule_video_id_list.push($(this).val());
    });
	
	map["user_id"] = "${user.user_id}";
	map["checked_event_schedule_image_id_list"] = checked_event_schedule_image_id_list;
	map["unchecked_event_schedule_image_id_list"] = unchecked_event_schedule_image_id_list;
	map["checked_event_schedule_video_id_list"] = checked_event_schedule_video_id_list;
	map["unchecked_event_schedule_video_id_list"] = unchecked_event_schedule_video_id_list;
	
	$.ajax({
		url: "setAlbumAction",
		type: "POST",
		data: JSON.stringify(map),
		contentType: "application/json; charset=UTF-8",
		success: function()
		{			
			location.replace("viewUserForm?user_id=${user.user_id}");
		},
		error: function(error){console.log(error);}
	});
}
var appearance_list = [];
$(document).ready(function () {
	
	$(".check").change(function()
	{
		var map = {};
		var group_id_list = [];
		var self = $(":checkbox[name='faceCheckbox']").prop("checked");
		
		$.each($(":checkbox[name='groupCheckbox']:checked"), function(){            
			group_id_list.push($(this).val());
        });
		map["user_id"] = "${user.user_id}";
		map["group_id_list"] = group_id_list;
		map["self"] = self;
		
		$.ajax({
			url: "refreshAlbumAction",
			type: "POST",
			data: JSON.stringify(map),
			dataType: "JSON",
			contentType: "application/json; charset=UTF-8",
			success: function(result)
			{			
				if(result.event_schedule_image_list.length != 0)
				{
					var image_buff = "";
					
					for(var i = 0; i < result.event_schedule_image_list.length; i++)
					{
						image_buff += "<article class='col-6 col-12-xsmall work-item'>";
						if(result.event_schedule_image_list[i].blind == 1)
						{
							image_buff += "<input type='checkbox' id='photo_check_box" + result.event_schedule_image_list[i].event_schedule_image_id + "' name='photo_check_box' value='" + result.event_schedule_image_list[i].event_schedule_image_id + "'>";
						}
						else
						{
							image_buff += "<input type='checkbox' id='photo_check_box" + result.event_schedule_image_list[i].event_schedule_image_id + "' name='photo_check_box' value='" + result.event_schedule_image_list[i].event_schedule_image_id + "' checked>";
						}
						image_buff += "<label for='photo_check_box" + result.event_schedule_image_list[i].event_schedule_image_id + "'/>";
						image_buff += "<img src='resources/image/event_schedule_image/" + result.event_schedule_image_list[i].filename + "'/>";
						image_buff += "</label>";
						image_buff += "</article>";
					}
					
					
					document.getElementById("image_album").innerHTML = image_buff;
				}
				else
				{
					document.getElementById("image_album").innerHTML = "";
				}
				if(result.event_schedule_video_list.length != 0)
				{
					var video_list = result.video_list;
									
					var video_buff = "";
					
					for(var = i; i < video_list.length; i++)
					{
						appearance_list.push(video_list[i].appearance_list);
						video_buff += "<video width='320' height='240' controls>";
						video_buff += "<source src='resources/image/event_schedule_video/" + video_list[i].video.filename +"' type='video/mp4'>";
						video_buff += "</video>";
						video_buff += "<span>";
						for(var j = 0; j < video_list[i].appearance_list.length; j++)
						{
							video_buff += "<p>" + video_list[i].appearance_list[j].start_date + "~" + video_list[i].appearance_list[j].end_date + "</p>";
						}
						video_buff += "</span>";
					}
					/* for(var i = 0; i < result.event_schedule_video_list.length; i++)
					{
						
						video_buff += "<video width='320' height='240' controls>";
						video_buff += "<source src='resources/image/event_schedule_video/" + result.event_schedule_video_list[i].filename +"' type='video/mp4'>";
						video_buff += "</video>";
						video_buff += "<br>";
						console.log(result.event_schedule_video_list[i]);
						if(result.event_schedule_video_list[i].blind == 1)
						{
							video_buff += "<input type='checkbox' id='video_check_box" + result.event_schedule_video_list[i].event_schedule_video_id + "' name='video_check_box' value='" + result.event_schedule_video_list[i].event_schedule_video_id + "'>";
						}
						else
						{
							video_buff += "<input type='checkbox' id='video_check_box" + result.event_schedule_video_list[i].event_schedule_video_id + "' name='video_check_box' value='" + result.event_schedule_video_list[i].event_schedule_video_id + "' checked>";
						}
						video_buff += "<label for='video_check_box" + result.event_schedule_video_list[i].event_schedule_video_id + "'/>";
						video_buff += "<br><h2>Select</h2>";
						video_buff += "</label>";
					} */
					
					
					document.getElementById("video").innerHTML = video_buff;
				}
				else
				{
					document.getElementById("video").innerHTML = "";
				}
			},
			error: function(error){console.log(error);}
		});
    });

})

</script>

	</body>
</html>