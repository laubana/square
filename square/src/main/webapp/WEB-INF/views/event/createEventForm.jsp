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
		<script src=resources/Basic/assets/js/jquery-3.3.1.min.js></script>
	</head>

<script>
	function createEventImage()
	{
		document.getElementById("file").click();
	}
	
	function uploadFirstEventImage()
	{
		var x = document.createElement("INPUT");
		x.setAttribute("type", "file");
		document.getElementById("album").appendChild(x);
	}
	
</script>



<style>
.filebox label { 
display: inline-block; 
padding: .5em .75em; 
color: #999; 
font-size: inherit; 
line-height: normal; 
vertical-align: middle; 
background-color: #fdfdfd; 
cursor: pointer; 
border: 1px solid #ebebeb; 
border-bottom-color: #e2e2e2; 
border-radius: .25em; 
} 

.filebox input[type="file"] { /* 파일 필드 숨기기 */ 
position: absolute; 
width: 1px; 
height: 1px; 
padding: 0; 
margin: -1px; 
overflow: hidden; 
clip:rect(0,0,0,0); 
border: 0; 
}
</style>
	
	<!-- 구글 맵스 위한 style 태그. 다른 요소 적용할 style이 있다면 style 태그를 별도로 만들어주기 바람 -->
	<style>
		#map {
			width: 500px;
			height: 350px;
			position: relative !important; /* changing this to fixed makes the map dissapear */
			top: 0; 
			bottom: 0; 
			left: 0; 
			right: 0; 
			z-index: 0;
	     }
		html,body {height: 100%; margin: 0; padding: 0;}
	</style>


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
					<h1 id="logo"><a href="viewGroupForm?group_id=${group.group_id}">${group.name}</a></h1>
					<p style="font-size:15px;">
					<c:forEach var="group_hashtag" items="${group_hashtag_list}">
							<a href="viewMindMapForm?hashtag=${group_hashtag.hashtag}">#${group_hashtag.hashtag}</a>
						</c:forEach>
					</p>
				</header>
				<nav id="nav">
					<ul>
						<li><a href="#one" class="active">イベント開設</a></li>
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
		
<!-- 폼 -->			
<form action="" method="post">
		<!-- Wrapper -->
			<div id="wrapper">

				<!-- Main -->
					<div id="main">

						<!-- 정보 -->
							<section id="one">
								<article class="post">
								<header>
								<!-- 타이틀 생성 -->
									<div class="title">
									<h2>タイトル</h2>
									<input type="text" class="Event_title "id="Event_title" placeholder="タイトル" autocomplete="off">
									</div>
									<div class="meta">
								<!-- 날짜, 회원 이름, 회원 사진-->
								<br><br><br>
										<a href="viewUserForm?user_id=${user.user_id}" class="author"><span class="name">${user.name}</span><img src="resources/image/user_image/${user.image_id}" alt="" /></a>
									</div>
								</header>
								<span class="image featured"><img src="" id="foo"></span>
								<!-- 그룹 대표 이미지 업로드 -->
								<h1>イメージ</h1>
									<div class="filebox">
									<label for="imgInp">アップロード</label>
									<input type='file' id="imgInp" /></div>
									<br>
									<!-- 내용 -->
									<h1>紹介</h1>
									<textarea class="comment-block" id="event_content" placeholder="紹介"></textarea><br>
									<br>
									<div align="center"><input type="button" value="開設" onclick="createEventAction()"></div>
									<!-- google maps-->
									<!--  <div id="map" ></div>
									<div align="right">
										<div id = "place_output"></div>
									</div>
									<br>
									<div id = "output_button"></div>
										<input type = "text" id = "search_addr" value = "東京　京橋駅">
										<br>
										<input type = "button" id = "button_mapsearch" value = "検索" onClick = "codeAddress()">
									<div align="right">
									<footer>
									</footer>
								</div> -->
								</article>
							</section>
					</div>		

						<!-- 그룹의 설립일 등 기본 정보 -->
							<!-- <div id="album">
							<section id="six">
								<footer><div id="album" class="container" >
									<h3>추가 이벤트 사진</h3>
									<p>추가 이벤트 이미지를 넣어보세요</p>

									<div class="features">
										<article>
											<img src="resources/GroupMain/images/bb.jpg" class="image" id="foo2">
											<div class="inner">
												<h4>File_1</h4>
												<div class="filebox">
												<label for="imgInp2">Image Upload1</label>
												<input type='file' id="imgInp2" /></div>
											</div>
										</article>
										<article>
											<img src="resources/GroupMain/images/bb.jpg" class="image" id="foo3">
											<div class="inner">
												<h4>File_2</h4>
												<div class="filebox">
												<label for="imgInp3">Image Upload2</label>
												<input type='file' id="imgInp3" /></div>
											</div>
										</article>
										<article>
											<img src="resources/GroupMain/images/bb.jpg" class="image" id="foo4">
											<div class="inner">
												<h4>File_3</h4>
												<div class="filebox">
												<label for="imgInp4">Image Upload3</label>
												<input type='file' id="imgInp4" /></div>
											</div>
										</article>
										<article>
											<img src="resources/GroupMain/images/bb.jpg" class="image" id="foo5">
											<div class="inner">
												<h4>File_4</h4>
												<div class="filebox">
												<label for="imgInp5">Image Upload4</label>
												<input type='file' id="imgInp5" /></div>
											</div>
										</article>
											</div>
									
										<div align="center"><input type="submit" value="이벤트 올리기"></div>
																	
									<button onclick="uploadFirstEventImage()" class="button">Image More</button>		
								</div></footer>
							</section>
						</div><br><br> -->		
				<!-- Footer -->
					<section id="footer">
						<div class="container">
							<ul class="copyright">
								<li>&copy; Untitled. All rights reserved.</li><li>Design: <a href="http://html5up.net">HTML5 UP</a></li>
							</ul>
						</div>
					</section>
				</div>
		
</form>

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
function createEventAction()
{
	var map = {};
	map["name"] = $("#Event_title").val();
	map["content"] = $("#event_content").val();
	map["group_category_id"] = ${group_category.group_category_id};
	map["group_id"] = ${group.group_id};
	
	var event_image = new FileReader();
	event_image.readAsDataURL(document.getElementById("imgInp").files[0]);
	event_image.onload = function()
	{
        map["event_image"] = event_image.result.substring(event_image.result.indexOf(",") + 1);
    }

	var interval = setInterval(function()
			{
				if(typeof(map.event_image) != "undefined")
				{
					$.ajax({
		    			url: "createEventAction",
		    			type: "POST",
		    			data: JSON.stringify(map),
		    			contentType: "application/json; charset=UTF-8",
		    			success: function(result){},
		    			error: function(){}
		    				});
					clearInterval(interval);
				}
			},100);
}
</script>
<script type="text/javascript">
//파일 미리보기 메인
function readURL(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = function(e) {
            $('#foo').attr('src', e.target.result);
        }
        reader.readAsDataURL(input.files[0]);
    }
}

$("#imgInp").change(function() {
    readURL(this);
});

function readURL2(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = function(e) {
            $('#foo2').attr('src', e.target.result);
        }
        reader.readAsDataURL(input.files[0]);
    }
}

$("#imgInp2").change(function() {
    readURL2(this);
});

function readURL3(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = function(e) {
            $('#foo3').attr('src', e.target.result);
        }
        reader.readAsDataURL(input.files[0]);
    }
}

$("#imgInp3").change(function() {
    readURL3(this);
});

function readURL4(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = function(e) {
            $('#foo4').attr('src', e.target.result);
        }
        reader.readAsDataURL(input.files[0]);
    }
}

$("#imgInp4").change(function() {
    readURL4(this);
});

function readURL5(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = function(e) {
            $('#foo5').attr('src', e.target.result);
        }
        reader.readAsDataURL(input.files[0]);
    }
}

$("#imgInp5").change(function() {
    readURL5(this);
});
</script>
</html>