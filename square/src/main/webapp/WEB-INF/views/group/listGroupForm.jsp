<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
	<head>
		<title>GroupSearch</title>	
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="resources/GroupSearch/assets/css/main.css" />
		<link rel="stylesheet" href="resources/TextA/css/style.css">
		
		<!-- 추가 -->
		<link rel='stylesheet' href='http://www.davilious.com/codepen/font-awesome/css/font-awesome.css'>

<style>
input::placeholder {
  color: white;
 
}
/* @keyframes showQuery {
  to {
    width: 300px;
    border-color: white;

  }
} */
@keyframes showControls {
  to {
    transform: translateY(10px);
    opacity: 1;
  }
}

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

.wrap-input.focused .input-txt {
  animation: showQuery .2s forwards 1 ease;
}
.wrap-input.focused .filters > .filter-btn {
  animation: showControls .3s forwards 1;
}
.wrap-input.focused .filters > .filter-btn:nth-child(1) {
  animation-delay: .15s;
}
.wrap-input.focused .filters > .filter-btn:nth-child(2) {
  animation-delay: .25s;
}
.wrap-input.focused .filters > .filter-btn:nth-child(3) {
  animation-delay: .35s;
}
.wrap-input.focused .filters > .filter-btn:nth-child(4) {
  animation-delay: .45s;
}
.wrap-input.focused .filters > .filter-btn:nth-child(5) {
  animation-delay: .55s;
}
.wrap-input.focused .filters > .filter-btn:nth-child(6) {
  animation-delay: .65s;
}
.wrap-input.focused .filters > .filter-btn:nth-child(7) {
  animation-delay: .75s;
}
.wrap-input.focused .filters > .filter-btn:nth-child(8) {
  animation-delay: .85s;
}
.wrap-input.focused .js-close {
  animation: showControls .4s .8s forwards 1;
  opacity: .8;
}

.input-txt {
  position: relative;
  z-index: 10;
  padding: 5px;
  border: 1px solid whitesmoke;
  width: 150px;
  height: 25px;
  line-height: 15px;
  background: white;
}

.filters {
  width: 300px;
}
.filters .js-close {
  text-decoration: none;
  color: white;
  float: right;
  opacity: 0;
  transition: opacity 1.2s;
  transform: translateY(-25px);
}

.filter-btn {
  position: relative;
  display: inline-block;
  color: black;
  text-align: center;
  line-height: 25px;
  text-decoration: none;
  display: inline-block;
  transform: translateY(-25px);
  opacity: 0;
}
.filter-btn > input[type=radio] {
  position: absolute;
  width: 100px;
  height: 26px;
  overflow: hidden;
  margin: 0;
  padding: 0;
  border: 0;
  outline: 0;
  opacity: 0;
}
.filter-btn > input[type=radio] + label {
  display: inline-block;
  width: auto;
  padding: 2px 10px;
  font-size: .9rem;
  height: 20px;
  /*3px at the top due to background 20 + 2 of padding - 25 of parent = 3*/
  line-height: 20px;
  cursor: pointer;
  vertical-align: middle;
  
}
.filter-btn:hover {
  background: #e0e0e0;
}
.filter-btn.active {
  
  color: white;
}
</style>
	</head>
	<body class="is-preload">

		<!-- Header -->
			<header id="header">
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

		<!-- Intro -->
			<section id="intro" class="main style1 dark fullscreen">
				<div class="content">
					<header>
						<h2>${group_category.name}</h2>
					</header>
					<div class="container">
  						<div class="wrap-input">  
  							<input type="search" id="keyword" class="input-txt" placeholder="Search" name="keyword">
  						<div class="filters">
    						<div class="filter-btn active">
      							<input type="radio" value="">
      							<label>All</label>
    						</div>
    					<div class="filter-btn">
       						<input type="radio" value="">
       							<label>회원수</label>
    						</div>
    					<div class="filter-btn">
      						<input type="radio" value="">
     					<label>5km</label>
    						</div>
    					<div class="filter-btn">
      						<input type="radio" value="">
     					<label>10km</label>
    						</div>
    						<div class="filter-btn">
      						<input type="radio" value="">
     					<label>25km</label>
    						</div>
    						<div class="filter-btn">
      						<input type="radio" value="">
     					<label>50km</label>
    						</div>
    						<div class="filter-btn">
      						<input type="radio" value="">
     					<label>100km</label>
    						</div>
    						<div class="filter-btn">
      						<input type="radio" value="">
     					<label>150km</label>
    						</div>
    					<div class="filter-btn" style="display:none">
      						<input type="radio" value="Subject:">
      					<label>Subject:</label>
    					</div>
    					<a href="#" class="js-close">
      						<small class="icon-angle-up"></small>
    					</a>
  						</div>
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
						<li><a href="#" class="icon fa-twitter"><span class="label">Twitter</span></a></li>
						<li><a href="#" class="icon fa-facebook"><span class="label">Facebook</span></a></li>
						<li><a href="#" class="icon fa-instagram"><span class="label">Instagram</span></a></li>
						<li><a href="#" class="icon fa-linkedin"><span class="label">LinkedIn</span></a></li>
						<li><a href="#" class="icon fa-dribbble"><span class="label">Dribbble</span></a></li>
						<li><a href="#" class="icon fa-pinterest"><span class="label">Pinterest</span></a></li>
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
	$("#show_work_button").on("click", function()
			{
				var map = {};
				
				map["keyword"] = $("#keyword").val();
				map["group_category_id"] = group_category_id;
				
				$.ajax({
					url: "listGroupAction",
					type: "POST",
					data: JSON.stringify(map),
					dataType: "JSON",
					contentType: "application/json; charset=UTF-8",
					success: function(group_list)
					{
						var buff = "";
						
						for(var i = 0; i < group_list.length; i++)
						{
							if(i % 2 == 1)
							{
								buff += "<div class='gallery'>"
								buff += "<article class='from-left'>";
								buff += "<a href='viewGroupForm?group_category_id=" + group_category_id + "&group_id=" + group_list[i].group_id + "'class='image fit'><img src='resources/image/group_logo/" + group_list[i].group_logo + "' title='" + group_list[i].name +"' alt='' /></a>";
								buff += "</article>"
								buff += "</div>"
							}
							else
							{
								buff += "<div class='gallery'>"
								buff += "<article class='from-right'>";
								buff += "<a href='viewGroupForm?group_category_id=" + group_category_id + "&group_id=" + group_list[i].group_id + "'class='image fit'><img src='resources/image/group_logo/" + group_list[i].group_logo + "' title='" + group_list[i].name +"' alt='' /></a>";
								buff += "</article>"
								buff += "</div>"
							}
						}
						$("#work").html(buff);
					},
					error: function(error)
					{
						
					}
				});
				$("#work").css("display", "block");
			})
</script>

	</body>
</html>