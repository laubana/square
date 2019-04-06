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
					<div align="left"><h1 style="font-size:50px;"><a href="main">2조</a></h1></div>
					<a href="#" class="image avatar"><img src="resources/GroupMain/images/member/c1.jpg" alt="" /></a>
					<h1><strong>Harry</strong>님의 그룹 사진<br /></h1>
					<p>편집 할려면 하든가ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ
					ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ</p>
					<nav id="nav">
					<ul>
						<li></li><li></li><li></li><li></li><li></li>
						<li><a href="#three" class="active">그룹 선택</a></li>
						<li><a href="#four" class="active">앨범 편집</a></li>
						<li><a href="#video" class="active">영상 편집</a></li>
					</ul>
				</nav>
				</div>
			</header>



		<!-- Main -->
			<div id="main">

					<!-- Three -->
					<section id="three">
						<h1>그룹 선택</h1>

						<div class="row">
								<ul class="features">
									<c:forEach var="group" items="${group_list}">
										<li>
											<input type="checkbox" id="gruopCheckbox${group.name}" name="gruopCheckbox" />
											<label for="gruopCheckbox${group.name}">
											<h3 align="center">${group.name}</h3>	
										</label>
										</li>
									</c:forEach>
								</ul>
								
								
								
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<a href="#" class="button" >선택 확인</a>
						</div>
					</section>

				<!-- Four -->
					<section id="four">
						<h1>앨범 편집</h1>
						<div class="row">
							<ul class="features">
									<li>
									<input type="checkbox" id="faceCheckbox" />
									<label for="faceCheckbox">
									<h3 align="center" style="color:maroon;">얼굴인식</h3>	
									</li>
								</ul>
			
						<c:forEach var="image" items="${event_schedule_image_list}">
							<article class="col-6 col-12-xsmall work-item">
								<input type="checkbox" id="albumCheckbox${image.event_schedule_image_id}" />
								<label for="albumCheckbox${image.event_schedule_image_id}">
								<img src="resources/image/${image.event_schedule_image_id}" alt="" />
								</label>
								<h3>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								TOTAL</h3>
								<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								Lorem ipsum dolor sit amet nisl sed nullam feugiat.</p>
							</article>
						</c:forEach>
						
						<c:forEach var="image" items="${self_event_schedule_image_list}">
							<article class="col-6 col-12-xsmall work-item">
								<input type="checkbox" id="self_albumCheckbox${image.event_schedule_image_id}" />
								<label for="self_albumCheckbox${image.event_schedule_image_id}">
								<img src="resources/image/${image.event_schedule_image_id}" alt="" />
								</label>
								<h3>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								SELF</h3>
								<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								Lorem ipsum dolor sit amet nisl sed nullam feugiat.</p>
							</article>
						</c:forEach>
							<!-- <article class="col-6 col-12-xsmall work-item">
								<input type="checkbox" id="albumCheckbox1" />
								<label for="albumCheckbox1">
								<img src="resources/MemberPhoto/images/thumbs/01.jpg" alt="" />
								</label>
								<h3>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								Magna sed consequat tempus</h3>
								<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								Lorem ipsum dolor sit amet nisl sed nullam feugiat.</p>
							</article>
							<article class="col-6 col-12-xsmall work-item">
								<input type="checkbox" id="albumCheckbox2" />
								<label for="albumCheckbox2">
								<img src="resources/MyPage/images/thumbs/02.jpg" alt="" />
								</label>
								<h3>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								Ultricies lacinia interdum</h3>
								<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								Lorem ipsum dolor sit amet nisl sed nullam feugiat.</p>
							</article>
							<article class="col-6 col-12-xsmall work-item">
								<input type="checkbox" id="albumCheckbox3" />
								<label for="albumCheckbox3">
								<img src="resources/MemberPhoto/images/thumbs/01.jpg" alt="" />
								</label>
								<h3>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								Magna sed consequat tempus</h3>
								<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								Lorem ipsum dolor sit amet nisl sed nullam feugiat.</p>
							</article>
							<article class="col-6 col-12-xsmall work-item">
								<input type="checkbox" id="albumCheckbox4" />
								<label for="albumCheckbox4">
								<img src="resources/MyPage/images/thumbs/02.jpg" alt="" />
								</label>
								<h3>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								Ultricies lacinia interdum</h3>
								<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								Lorem ipsum dolor sit amet nisl sed nullam feugiat.</p>
							</article> -->
							
						</div>
						<ul class="actions">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<li><a href="myPage" class="button">편집 확인</a></li>
						</ul>
					</section>

		<!-- 영상 -->
			<section id="video">
				<h1>영상 편집</h1>
				<p>1 퍄퍄</p>
				<p>2 파파</p>
				<p>3 포포</p>
				<p>4 표표</p>
				<a href="myPage" class="button">편집 확인</a>
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

	</body>
</html>