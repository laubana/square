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
		<title>みんな・みんな</title>
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
				<h1><a class="navbar-brand font-weight-bolder mr-3" href="main"><img src="resources/Main/assets/css/images/photoSquareLogo_done.png"></a></h1>
				<nav>
					<ul>
						<li><a href="listRecommendationForm"></a>
						<c:if test="${sessionScope.user_id != null}">
						<li>${sessionScope.user_id}</li>
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

		<!-- Header -->
			<section id="header">
				<header>
					<!-- 그룹 로고 이미지 -->
					<span class="image avatar" id="foo2_span"><img src="resources/Main/images/bb.jpg" id="foo2"/></span>
					<div id="live-text"><h1 id="logo"><a>グループ・名前</a></h1></div>
					<div id="live-text1"><p style="font-size:15px;">[#タグ_ 1]</p></div>
					<div id="live-text2"><p style="font-size:15px;">[#タグ _2]</p></div><div id="live-text3"><p style="font-size:15px;">[#タグ _3]</p></div><div id="live-text4"><p style="font-size:15px;">[#タグ _4]</p></div><div id="live-text5"><p style="font-size:15px;">[#タグ _5]</p></div>
				</header>
				<nav id="nav">
					<ul>
						<li><a href="#one" class="active">グループ・生成</a></li>
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
							<h1>グループ・名前</h1>
							<br>
							<!-- 폼 -->
							<form action="" method="post">
							<input type="text" name="group_id" id="input" placeholder="グループ・名前" autocomplete="off">
							<br>
							<h1>グループ・紹介</h1>
							<br>
							<div class="comment-wrap">
								<div class="comment-block">
										<textarea name="" id="group_content" cols="30" rows="3" placeholder="ご自由に記入してください。"></textarea>
								</div>
							</div>
						
							<br>
							<h1>グループ・ロゴ・イメージ</h1>
							<div class="filebox">
							<label for="imgInp2">ロゴ・アップロード</label> 
							<input type='file' id="imgInp2" /></div>
							<br>
							<h1>グループ・メイン・イメージ</h1>
							<div class="filebox">
							<label for="imgInp">メイン・アップロード</label>
							<input type='file' id="imgInp" /></div>
						
							<br>
							<h1>グループ・カテゴリー</h1>
							<div class="container">
  								<div class="radio">
    								<input id="radio-1" name="radio" type="radio" value="1" checked>
    								<label for="radio-1" class="radio-label">IT</label>
    								<input id="radio-2" name="radio" type="radio" value="2">
    								<label  for="radio-2" class="radio-label">ペット</label>
    								<input id="radio-3" name="radio" type="radio" value="3">
    								<label for="radio-3" class="radio-label">レジャー</label>
    								<input id="radio-4" name="radio" type="radio" value="4">
    								<label for="radio-4" class="radio-label">家族</label>
    								<input id="radio-5" name="radio" type="radio" value="5">
    								<label for="radio-5" class="radio-label">料理</label>
    								<input id="radio-6" name="radio" type="radio" value="6">
    								<label for="radio-6" class="radio-label">音樂</label>
  								</div>
							</div>
							<h1>グループ・タグ</h1>
							<input type="text" placeholder="#グループ・タグ 1" class="input1" name="group_hashtag" autocomplete="off">
							<input type="text" placeholder="#グループ・タグ 2" class="input2" name="group_hashtag" autocomplete="off">
							<input type="text" placeholder="#グループ・タグ 3" class="input3" name="group_hashtag" autocomplete="off">
							<input type="text" placeholder="#グループ・タグ 4" class="input4" name="group_hashtag" autocomplete="off">
							<input type="text" placeholder="#グループ・タグ 5" class="input5" name="group_hashtag" autocomplete="off">
							<br>
							<h1>グループ・活動地域</h1>
							<input type="text" placeholder="グループ・活動地域" id="region">
							<br>
							<div align="center"><input type="button" value="生成" onclick="createGroupAction()"></div>
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
		<!-- 자동완성검색기능 -->
		<script type="text/javascript" src="resources/autocomplete/jquery/lib/jquery.js"></script>
		<script type='text/javascript' src='resources/autocomplete/jquery/lib/jquery.bgiframe.min.js'></script>
		<script type='text/javascript' src='resources/autocomplete/jquery/lib/jquery.ajaxQueue.js'></script>
		<script type='text/javascript' src='resources/autocomplete/jquery/jquery.autocomplete.js'></script>

<script>
	var availableTags = [
							'北海道','滋賀県','青森県','京都府','秋田県','大阪府','岩手県',
							'奈良県','山形県','和歌山県','宮城県','兵庫県','福島県','鳥取県',
							'茨城県','島根縣','栃木県','岡山県','群馬県','広島県','埼玉県','山口県',
							'千葉県','香川県','東京都','徳島県','神奈川県','愛媛県','新潟県','高知県',
							'富山県','福岡県','石川県','佐賀県','福井県','長崎県','山梨県','大分県','長野県',
							'熊本県','静岡県','宮崎県','愛知県','鹿児島県','岐阜県','沖縄県','三重県'
						];
</script>

<script>
	$(document).ready(function() {
	    $("#region").autocomplete(availableTags,{ 
	        matchContains: true,
	        selectFirst: false
	    });
	});
</script>
		
<script>
	function createGroupAction()
	{
		var map = {};
		map["name"] = $("#input").val();
		map["content"] = $("#group_content").val();
		var radio_list = document.getElementsByName("radio");
		for(var i = 0; i < radio_list.length; i++)
		{
			if(radio_list[i].checked == true)
			{
				map["group_category_id"] = radio_list[i].value;
				break;
			}
		}
		
		var group_hashtag_list = document.getElementsByName("group_hashtag");
		map["group_hashtag_list"] = [];
		for(var i = 0; i < group_hashtag_list.length; i++)
		{
			map["group_hashtag_list"].push(group_hashtag_list[i].value);
		}
		
		map["region"] = document.getElementById("region").value;
		
		var group_logo = new FileReader();
		group_logo.readAsDataURL(document.getElementById("imgInp2").files[0]);
		group_logo.onload = function()
		{
            map["group_logo"] = group_logo.result.substring(group_logo.result.indexOf(",") + 1);
        }
		var group_image = new FileReader();
		group_image.readAsDataURL(document.getElementById("imgInp").files[0]);
		group_image.onload = function()
		{
            map["group_image"] = group_image.result.substring(group_image.result.indexOf(",") + 1);
        }

		var interval = setInterval(function()
				{
					if(typeof(map.group_logo) != "undefined" && typeof(map.group_image) != "undefined")
					{
						$.ajax({
			    			url: "createGroupAction",
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
$("#foo2_span").click(function()
		{
	document.getElementById("imgInp2").click();
});
$("#foo").click(function()
		{
	document.getElementById("imgInp").click();
});
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