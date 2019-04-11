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
		<title>CreateGroup</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="resources/GroupMain/assets/css/main.css" />
		<link rel="stylesheet" href="resources/TextA/css/style.css">
	
<style type="text/css">
$color1: #f4f4f4;
$color2: #3197EE;

body {
  display: flex;
  align-items: center;
  justify-content: center;
  min-height: 100vh;
}

.radio {
  margin: 0.5rem;
  input[type="radio"] {
    position: absolute;
    opacity: 0;
    + .radio-label {
      &:before {
        content: '';
        background: $color1;
        border-radius: 100%;
        border: 1px solid darken($color1, 25%);
        display: inline-block;
        width: 1.4em;
        height: 1.4em;
        position: relative;
        top: -0.2em;
        margin-right: 1em; 
        vertical-align: top;
        cursor: pointer;
        text-align: center;
        transition: all 250ms ease;
      }
    }
    &:checked {
      + .radio-label {
        &:before {
          background-color: $color2;
          box-shadow: inset 0 0 0 4px $color1;
        }
      }
    }
    &:focus {
      + .radio-label {
        &:before {
          outline: none;
          border-color: $color2;
        }
      }
    }
    &:disabled {
      + .radio-label {
        &:before {
          box-shadow: inset 0 0 0 4px $color1;
          border-color: darken($color1, 25%);
          background: darken($color1, 25%);
        }
      }
    }
    + .radio-label {
      &:empty {
        &:before {
          margin-right: 0;
        }
      }
    }
  }
}
</style>

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

	</head>
	<body class="is-preload">

		<!-- Header 메인 바 -->
			<header id="header1">
				<h1><a href="main">2조</a></h1>
				<nav>
					<ul>
						<li><a href="#">회원가입</a></li>
						<li><a href="#">그룹생성</a></li>
						<li><a href="login">로그인</a></li>
					</ul>
				</nav>
			</header>

		<!-- Header -->
			<section id="header">
				<header>
					<!-- 그룹 로고 이미지 -->
					<span class="image avatar"><img id="foo2" src="resources/Main/images/bb.jpg" /></span>
					<div id="live-text"><h1 id="logo"><a>Group Name</a></h1></div>
					<div id="live-text1"><p style="font-size:15px;">[tag1]</p></div>
					<div id="live-text2"><p style="font-size:15px;">[tag2]</p></div><div id="live-text3"><p style="font-size:15px;">[tag3]</p></div><div id="live-text4"><p style="font-size:15px;">[tag4]</p></div><div id="live-text5"><p style="font-size:15px;">[tag5]</p></div>
				</header>
				<nav id="nav">
					<ul>
						<li><a href="#one" class="active">그룹 생성</a></li>
					</ul>
				</nav>
				<footer>
					<ul class="icons">
						<li><a href="#" class="icon fa-twitter"><span class="label">Twitter</span></a></li>
						<li><a href="#" class="icon fa-facebook"><span class="label">Facebook</span></a></li>
						<li><a href="#" class="icon fa-instagram"><span class="label">Instagram</span></a></li>
						<li><a href="#" class="icon fa-github"><span class="label">Github</span></a></li>
						<li><a href="#" class="icon fa-envelope"><span class="label">Email</span></a></li>
					</ul>
				</footer>
			</section>

		<!-- Wrapper -->
	<div id="wrapper">

			<div id="main">
				<!-- One -->
				<section id="one">
					<div class="image main" data-position="center">
					<!-- 그룹 대표 이미지 -->
					<span class="image main" data-position="center"><img id="foo" src="resources/Main/images/bb.jpg" /></span>	
					</div>
					
					<div class="container">
						
						<!-- 메인 해더 -->
						<header class="major">
							<h1>그룹 이름</h1>
							<br>
							<!-- 폼 -->
							<form action="" method="post">
							<input type="text" name="group_id" id="input" placeholder="그룹 이름">
							<br>
							<h1>그룹 소개</h1>
							<br>
							<div class="comment-wrap">
								<div class="comment-block">
										<textarea name="" id="" cols="30" rows="3" placeholder="자유롭게 작성해 주세요..."></textarea>
								</div>
							</div>
						
							<br>
							<h1>그룹 로고 이미지</h1>
							<div class="filebox">
							<label for="imgInp2">Logo Upload</label> 
							<input type='file' id="imgInp2" /></div>
							<br>
							<h1>그룹 메인 이미지</h1>
							<div class="filebox">
							<label for="imgInp">Main Upload</label>
							<input type='file' id="imgInp" /></div>
						
							<br>
							<h1>그룹 카테고리</h1>
							<div class="container">
  								<div class="radio">
    								<input id="radio-1" name="radio" type="radio" checked>
    								<label for="radio-1" class="radio-label">IT</label>
    								<input id="radio-2" name="radio" type="radio">
    								<label  for="radio-2" class="radio-label">펫</label>
    								<input id="radio-3" name="radio" type="radio">
    								<label for="radio-3" class="radio-label">레져</label>
    								<input id="radio-4" name="radio" type="radio">
    								<label for="radio-4" class="radio-label">가족</label>
    								<input id="radio-5" name="radio" type="radio">
    								<label for="radio-5" class="radio-label">요리</label>
    								<input id="radio-6" name="radio" type="radio">
    								<label for="radio-6" class="radio-label">음악</label>
  								</div>
							</div>
							<h1>그룹 Tag</h1>
							<input type="text" placeholder="그룹 tag1" class="input1">
							<input type="text" placeholder="그룹 tag2" class="input2">
							<input type="text" placeholder="그룹 tag3" class="input3">
							<input type="text" placeholder="그룹 tag4" class="input4">
							<input type="text" placeholder="그룹 tag5" class="input5">
							<br>
							<h1>그룹 활동 지역</h1>
							<input type="text" placeholder="그룹 활동 지역">
							<br>
							<div align="center"><input type="submit" value="생성"></div>
							</form>
						</header>	
					</div>
				</section>
	
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

</script>

<script>
(function(container, liveText, minFontSize, maxFontSize){
	  $.fn.getTextWidth = function(){
	  var self = $(this),
	      children = self.children(),
	      calculator = $('<span style="display: inline-block;">'),
	      width;

	  children.wrap(calculator);
	  width = children.parent().width();
	  return width;
	};
	  
	$('#input').keyup(function(e) {
	  let text = $(this).val(),
	      container = $("#container"),
	      liveText = $('#live-text'), 
	      spacesCount,
	      textWidth,
	      size;  
	        
	  liveText.html('<h1>'+text+'</h1>');
	  
	});

	
	})(30,60);
</script>

<script type="text/javascript">
  (function(container, liveText, minFontSize, maxFontSize){
	  $.fn.getTextWidth = function(){
	  var self = $(this),
	      children = self.children(),
	      calculator = $('<span style="display: inline-block;">'),
	      width;

	  children.wrap(calculator);
	  width = children.parent().width();
	  return width;
	};
	  
	$('.input1').keyup(function(e) {
	  let text = $(this).val(),
	      liveText = $('#live-text1'), 
	      spacesCount,
	      textWidth,
	      size;  
	        
	  liveText.html('<p style="font-size:15px;">'+text+'</p>');
	  
	});

	
	})(30,60);
</script>

<script type="text/javascript">
  (function(container, liveText, minFontSize, maxFontSize){
	  $.fn.getTextWidth = function(){
	  var self = $(this),
	      children = self.children(),
	      calculator = $('<span style="display: inline-block;">'),
	      width;

	  children.wrap(calculator);
	  width = children.parent().width();
	  return width;
	};
	  
	$('.input2').keyup(function(e) {
	  let text = $(this).val(),
	      liveText = $('#live-text2'), 
	      spacesCount,
	      textWidth,
	      size;  
	        
	  liveText.html('<p style="font-size:15px;">'+text+'</p>');
	  
	});

	
	})(30,60);
</script>

<script type="text/javascript">
  (function(container, liveText, minFontSize, maxFontSize){
	  $.fn.getTextWidth = function(){
	  var self = $(this),
	      children = self.children(),
	      calculator = $('<span style="display: inline-block;">'),
	      width;

	  children.wrap(calculator);
	  width = children.parent().width();
	  return width;
	};
	  
	$('.input3').keyup(function(e) {
	  let text = $(this).val(),
	      liveText = $('#live-text3'), 
	      spacesCount,
	      textWidth,
	      size;  
	        
	  liveText.html('<p style="font-size:15px;">'+text+'</p>');
	  
	});

	
	})(30,60);
</script>

<script type="text/javascript">
  (function(container, liveText, minFontSize, maxFontSize){
	  $.fn.getTextWidth = function(){
	  var self = $(this),
	      children = self.children(),
	      calculator = $('<span style="display: inline-block;">'),
	      width;

	  children.wrap(calculator);
	  width = children.parent().width();
	  return width;
	};
	  
	$('.input4').keyup(function(e) {
	  let text = $(this).val(),
	      liveText = $('#live-text4'), 
	      spacesCount,
	      textWidth,
	      size;  
	        
	  liveText.html('<p style="font-size:15px;">'+text+'</p>');
	  
	});

	
	})(30,60);
</script>

<script type="text/javascript">
  (function(container, liveText, minFontSize, maxFontSize){
	  $.fn.getTextWidth = function(){
	  var self = $(this),
	      children = self.children(),
	      calculator = $('<span style="display: inline-block;">'),
	      width;

	  children.wrap(calculator);
	  width = children.parent().width();
	  return width;
	};
	  
	$('.input5').keyup(function(e) {
	  let text = $(this).val(),
	      liveText = $('#live-text5'), 
	      spacesCount,
	      textWidth,
	      size;  
	        
	  liveText.html('<p style="font-size:15px;">'+text+'</p>');
	  
	});

	
	})(30,60);
</script>


	</body>
</html>