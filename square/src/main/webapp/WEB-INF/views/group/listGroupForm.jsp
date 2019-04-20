<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
	<head>
		<title>みんな・みんな</title>	
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="resources/GroupSearch/assets/css/main.css" />
		<link rel="stylesheet" href="resources/TextA/css/style.css">
		
		<!-- 추가 -->
		<!-- <link rel='stylesheet' href='http://www.davilious.com/codepen/font-awesome/css/font-awesome.css'> -->
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
</script>
<style>

:root{
  background-color: whitesmoke;
}

.container {
  position: relative;
  width: 300px;
  height: auto;
  margin: 60px auto;
}

.wrap-input {
  position: relative;
  width: 300px;
}

.icon-search{
    position: absolute;
    color: white;
    right: 20px; top: 4px;
}

</style>

	</head>
	<body class="is-preload">

		<!-- Header -->
			<header id="header">
				<h1><a class="navbar-brand font-weight-bolder mr-3" href="main"><img src="resources/Main/assets/css/images/photoSquareLogo_done.png"></a></h1>
				<nav>
					<ul>
						<li><a href="listRecommendationForm"><span id="realTimeHashTag"></span></a></li>
						<c:if test="${sessionScope.user_id != null}">
						<li><a href="viewUserForm?user_id=${sessionScope.user_id}">${sessionScope.user_id}</a></li>
						<li><a href="createGroupForm">グループ・生成</a></li>
					<li><a href="javascript:logoutUserAction()"><strong style="color:#778899;">ログアウト</strong></a></li>
						</c:if>
						<c:if test="${sessionScope.user_id == null}">
						<li><a href="joinUserForm">会員加入</a></li>
							<li><a href="loginUserForm"><strong style="color:#778899;">ログイン</strong></a></li>
						</c:if>
					</ul>
				</nav>
			</header>

		<!-- Intro -->
			<section id="intro" class="main style1 dark fullscreen">
				<div class="content">
					<header>
						<h2>${group_category.name}</h2>
					</header>
					<div class="container">
  						<div class="wrap-input">  
  							<input type="search" id="keyword" class="input-txt" placeholder="Search" name="keyword">
  						</div>
					</div>			
					<footer>
						<a href="#work" class="button style2 down" id="show_work_button"></a>
					</footer>
				</div>
			</section>

		<!-- Work -->
			<section id="work" class="main style3 primary" style="display: none;">
			
			</section>


		<!-- Footer -->
			<footer id="footer">

				<!-- Icons -->
					<ul class="icons">
						<li><a href="https://twitter.com" class="icon fa-twitter"><span class="label">Twitter</span></a></li>
						<li><a href="https://www.facebook.com" class="icon fa-facebook"><span class="label">Facebook</span></a></li>
						<li><a href="https://www.instagram.com" class="icon fa-instagram"><span class="label">Instagram</span></a></li>
						<li><a href="https://kr.linkedin.com" class="icon fa-linkedin"><span class="label">LinkedIn</span></a></li>
						<li><a href="https://dribbble.com" class="icon fa-dribbble"><span class="label">Dribbble</span></a></li>
						<li><a href="https://co.pinterest.com" class="icon fa-pinterest"><span class="label">Pinterest</span></a></li>
					</ul>

				<!-- Menu -->
					<ul class="menu">
						<li>&copy; Untitled</li><li>Design: <a href="https://html5up.net">HTML5 UP</a></li>
					</ul>

			</footer>

		<!-- 추가 -->

		<!-- 기본 Scripts -->
		<script src="resources/Basic/assets/js/jquery-3.3.1.min.js"></script>
		<!-- Scripts -->
			<script src="resources/GroupSearch/assets/js/jquery.min.js"></script>
			<script src="resources/GroupSearch/assets/js/jquery.poptrox.min.js"></script>
			<script src="resources/GroupSearch/assets/js/jquery.scrolly.min.js"></script>
			<script src="resources/GroupSearch/assets/js/jquery.scrollex.min.js"></script>
			<script src="resources/GroupSearch/assets/js/browser.min.js"></script>
			<script src="resources/GroupSearch/assets/js/breakpoints.min.js"></script>
			<script src="resources/GroupSearch/assets/js/util.js"></script>
			<script src="resources/GroupSearch/assets/js/main.js"></script>
<%-- ${group_category.filename} --%>			
<script>
$("#intro").css("background", "url('resources/GroupSearch/assets/css/images/overlay.png'), url('resources/Main/images/thumbs/${group_category.group_category_id}.jpg')");
$("#intro").css("background-size","100% 100%");
</script>
<script>
var group_category_id = ${group_category.group_category_id};
$('input[type=search]').on({
	  'focus': function(){
	     $(this).parent().addClass('focused');
	   } 
	})

	$('.filter-btn').on('click', function(){
	   $('.filter-btn').removeClass('active');
	   $(this).addClass('active');
	})

	$('.js-close').on('click', function(e){
	  e.preventDefault();
	  $('.wrap-input').removeClass('focused');
	})

	$('input[type=radio]').on('click', function(){
	  var queryField = $('input[type=search]'),
	      queryValue = queryField.val();
	  
	  if( queryValue === '' &&
	      queryValue.search(/:/i) == -1 )
	  {
	       queryField
	        .val( $(this).val() + '' )
	         .focus()
	  }else{  
	       queryField
	         .val( queryValue + '' + $(this).val() + '' ).focus()
	   }
	})
	$("#keyword").keypress(function(event)
			{
				if(event.which == 13)
				{
					document.getElementById("show_work_button").click();
				}
			});
/* $.each($(":checkbox[name='photo_check_box']:checked"), function(){            
	checked_event_schedule_image_id_list.push($(this).val());
});
 */
			var check_group_category_id_radio = 1;
	$("#show_work_button").on("click", listGroupAction);
	function listGroupAction()
	{		
		var map = {};
		
		map["name"] = $("#keyword").val();
		map["group_category_id"] = group_category_id;
		map["orderby"] = check_group_category_id_radio;
		
		$.ajax({
			url: "listGroupAction",
			type: "POST",
			data: JSON.stringify(map),
			dataType: "JSON",
			contentType: "application/json; charset=UTF-8",
			success: function(group_list)
			{
				var buff = "";
				
				buff += "<div class='gallery' align='right'>";
				buff += "<article class='from-left'>";
				buff += "<div class='radio' align='left'>";
				if(check_group_category_id_radio == 1)
				{
					buff += "<hr><input id='radio-1' class='check_group_category_id_radio' name='radio' type='radio' value='1' checked>";
					buff += "<label for='radio-1' class='radio-label'>メンバー数順</label><br>";
					buff += "<input id='radio-2' class='check_group_category_id_radio' name='radio' type='radio' value='2'>";
					buff += "<label for='radio-2' class='radio-label'>イベント数順</label><br>";
					buff += "<input id='radio-3' class='check_group_category_id_radio' name='radio' type='radio' value='3'>";
					buff += "<label for='radio-3' class='radio-label'>イベント・スケジュール数順</label><br>";
					buff += "<input id='radio-4' class='check_group_category_id_radio' name='radio' type='radio' value='4'>";
					buff += "<label for='radio-4' class='radio-label'>開設日</label><hr>";
				}
				else if(check_group_category_id_radio == 2)
				{
					buff += "<hr><input id='radio-1' class='check_group_category_id_radio' name='radio' type='radio' value='1'>";
					buff += "<label for='radio-1' class='radio-label'>メンバー数順</label><br>";
					buff += "<input id='radio-2' class='check_group_category_id_radio' name='radio' type='radio' value='2' checked>";
					buff += "<label for='radio-2' class='radio-label'>イベント数順</label><br>";
					buff += "<input id='radio-3' class='check_group_category_id_radio' name='radio' type='radio' value='3'>";
					buff += "<label for='radio-3' class='radio-label'>イベント・スケジュール数順</label>";
					buff += "<input id='radio-4' class='check_group_category_id_radio' name='radio' type='radio' value='4'>";
					buff += "<label for='radio-4' class='radio-label'>開設日</label><hr>";
				}
				else if(check_group_category_id_radio == 3)
				{
					buff += "<hr><input id='radio-1' class='check_group_category_id_radio' name='radio' type='radio' value='1'>";
					buff += "<label for='radio-1' class='radio-label'>メンバー数順</label><br>";
					buff += "<input id='radio-2' class='check_group_category_id_radio' name='radio' type='radio' value='2'>";
					buff += "<label for='radio-2' class='radio-label'>イベント数順</label><br>";
					buff += "<input id='radio-3' class='check_group_category_id_radio' name='radio' type='radio' value='3' checked>";
					buff += "<label for='radio-3' class='radio-label'>イベント・スケジュール数順</label><br>";
					buff += "<input id='radio-4' class='check_group_category_id_radio' name='radio' type='radio' value='4'>";
					buff += "<label for='radio-4' class='radio-label'>開設日</label><hr>";
				}
				else
				{
					buff += "<hr><input id='radio-1' class='check_group_category_id_radio' name='radio' type='radio' value='1'>";
					buff += "<label for='radio-1' class='radio-label'>メンバー数順</label><br>";
					buff += "<input id='radio-2' class='check_group_category_id_radio' name='radio' type='radio' value='2'>";
					buff += "<label for='radio-2' class='radio-label'>イベント数順</label><br>";
					buff += "<input id='radio-3' class='check_group_category_id_radio' name='radio' type='radio' value='3'>";
					buff += "<label for='radio-3' class='radio-label'>イベント・スケジュール数順</label><br>";
					buff += "<input id='radio-4' class='check_group_category_id_radio' name='radio' type='radio' value='4' checked>";
					buff += "<label for='radio-4' class='radio-label'>開設日</label><hr>";
				}		
				buff += "</div>";
				buff += "</article>";
				buff += "<article class='from-right'>";
				
				buff += "</article>";
				buff += "</div>";
				
				buff += "<div class='gallery'>";
				
				for(var i = 0; i < group_list.length; i++)
				{
					if(i % 2 == 1)
					{
						buff += "<article class='from-left'>";
						buff += "<a href='viewGroupForm?group_category_id=" + group_category_id + "&group_id=" + group_list[i].group_id + "'class='image fit'><img src='resources/image/group_logo/" + group_list[i].group_logo + "' title='" + group_list[i].name +"' alt='' /></a>";
						buff += group_list[i].name;
						buff += "</article>";
					}
					else
					{
						buff += "<article class='from-right'>";
						buff += "<a href='viewGroupForm?group_category_id=" + group_category_id + "&group_id=" + group_list[i].group_id + "'class='image fit'><img src='resources/image/group_logo/" + group_list[i].group_logo + "' title='" + group_list[i].name +"' alt='' /></a>";
						buff += group_list[i].name;
						buff += "</article>";
					}
				}

				buff += "</div>";
				
				$("#work").html(buff);
				
				$(".check_group_category_id_radio").change(function()
						{
					console.log(this.value);
					check_group_category_id_radio = parseInt(this.value);
					listGroupAction();
				});
				
			},
			error: function(error)
			{
				
			}
		});
		$("#work").css("display", "block");
	}
</script>

	</body>
</html>