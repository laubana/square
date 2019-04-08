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
  color: #4b6a77;
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
						<li><a href="joinForm">회원가입</a></li>
						<li><a href="groupCreateForm">그룹생성</a></li>
						<li><a href="login">로그인</a></li>
					</ul>
				</nav>
			</header>

		<!-- Intro -->
			<section id="intro" class="main style1 dark fullscreen">
				<div class="content">
					<header>
						<h2>2조.</h2>
					</header>
					<p>Welcome to <strong>2조</strong> 참조하실 분은 <a href="https://www.meetup.com/ko-KR/">meetup</a><br />
					그리고 단어가 궁금하면 <a href="https://ja.dict.naver.com/">네이버 일본어 사전</a>.</p>
	
					<div class="container">
  						<div class="wrap-input">  
  							<input type="search" class="input-txt" placeholder="Search" onsubmit="searchKeywordFunction()">
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
						<a href="#work" class="button style2 down"></a>
					</footer>
				</div>
			</section>

		<!-- Work -->
			<section id="work" class="main style3 primary">
				<div class="content">
					<header>
						<h2>분야별 그룹 탐색</h2>
						<p>검색해 주세요.</p>
					</header>

					<!-- Gallery  -->
						<div class="gallery">
							<article class="from-left">
								<a href="groupMain" class="image fit"><img src="resources/GroupSearch/images/logo/01.jpg" title="The Anonymous Red" alt="" /></a>
							</article>
							<article class="from-right">
								<a href="groupMain" class="image fit"><img src="resources/GroupSearch/images/logo/02.jpg" title="Airchitecture II" alt="" /></a>
							</article>
						</div>

				</div>
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
			
<script>
$("#intro").css("background", "url('resources/GroupSearch/assets/css/images/overlay.png'), url('resources/GroupSearch/images/${requestScope.image}.jpg')");
$("#intro").css("background-size","256px 256px, cover");
</script>
<script>
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
	
	
</script>

<!-- 키워드로 그룹 서치 ajax  -->
<!-- <script>
function searchKeywordFunction()
{
		$.ajax({
				url:'groupSearchByKeyword',
				type:'GET',
				data: {str : 'tempData'},
				dataType:'json',
				success:function(array)
				{
					$('#input-txt').val(array);
					$('array.Group')
				},
				error:function(e)
				{
					alert('저장되지 않음');
				}	
		});
		
}

</script> -->

	</body>
</html>