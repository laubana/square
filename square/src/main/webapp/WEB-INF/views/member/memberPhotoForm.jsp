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
					<a href="#" class="image avatar"><img src="resources/GroupMain/images/member/c1.jpg" alt="" /></a>
					<h1><strong>Harry</strong>님의 그룹 사진<br /></h1>
					<p>편집 할려면 하든가ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ
					ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ</p>
					<nav id="nav">
					<ul>
						<li></li><li></li><li></li><li></li><li></li><li></li>
						<li></li><li></li><li></li><li></li><li></li><li></li>
						<li></li><li></li><li></li><li></li>
						<li><a href="#three" class="active">그룹 선택</a></li>
						<li><a href="#four" class="active">앨범 편집</a></li>
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
									<li>
										<input type="checkbox" id="gruopCheckbox1" />
										<label for="gruopCheckbox1">
										<h3 align="center">A그룹</h3>	
										</label>															
									</li>
									<li>
										<input type="checkbox" id="gruopCheckbox2" />
										<label for="gruopCheckbox2">
										<h3 align="center">B그룹</h3>	
										</label>	
									</li>
									<li>
										<input type="checkbox" id="gruopCheckbox3" />
										<label for="gruopCheckbox3">
										<h3 align="center">C그룹</h3>	
										</label>		
									</li>
									<li>
										<input type="checkbox" id="gruopCheckbox4" />
										<label for="gruopCheckbox4">
										<h3 align="center">D그룹</h3>	
										</label>															
									</li>
									<li>
										<input type="checkbox" id="gruopCheckbox5" />
										<label for="gruopCheckbox5">
										<h3 align="center">E그룹</h3>	
										</label>	
									</li>
									<li>
										<input type="checkbox" id="gruopCheckbox6" />
										<label for="gruopCheckbox6">
										<h3 align="center">F그룹</h3>	
										</label>		
									</li>
									<li>
										<input type="checkbox" id="gruopCheckbox7" />
										<label for="gruopCheckbox7">
										<h3 align="center">G그룹</h3>	
										</label>		
									</li>
									<li>
										<input type="checkbox" id="gruopCheckbox8" />
										<label for="gruopCheckbox8">
										<h3 align="center">H그룹</h3>	
										</label>		
									</li>
									<li>
										<input type="checkbox" id="gruopCheckbox9" />
										<label for="gruopCheckbox9">
										<h3 align="center">I그룹</h3>	
										</label>		
									</li>
									<li>
										<input type="checkbox" id="gruopCheckbox10" />
										<label for="gruopCheckbox10">
										<h3 align="center">K그룹</h3>	
										</label>		
									</li>
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
						
							<article class="col-6 col-12-xsmall work-item">
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
							</article>
							
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