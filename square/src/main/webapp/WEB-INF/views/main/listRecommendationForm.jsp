<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>PPaya 
     </title>

    <script type="text/javascript"> (function() { var css = document.createElement('link'); css.href = 'https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css'; css.rel = 'stylesheet'; css.type = 'text/css'; document.getElementsByTagName('head')[0].appendChild(css); })(); </script>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="resources/GroupRecommend/docs/assets/css/app.css">
    <link rel="stylesheet" href="resources/GroupRecommend/docs/assets/css/theme.css">
    <script src="resources/Basic/assets/js/jquery-3.3.1.min.js"></script>
	<script>
	
	function w3_open() {
		  document.getElementById("main").style.display = "block";
		  document.getElementById("mySidebar").style.display = "block";
		}

		function w3_close() {
		  document.getElementById("main").style.display = "none";
		  document.getElementById("mySidebar").style.display = "none";
		}

	</script>	
</head>

<body>

 <nav class="navbar navbar-expand-lg navbar-light bg-white fixed-top">
    <a class="navbar-brand font-weight-bolder mr-3" href="main"><img src="resources/Main/assets/css/images/photoSquareLogo_done.png"></a>
    <button class="navbar-light navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsDefault" aria-controls="navbarsDefault" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarsDefault">

    	<ul class="navbar-nav ml-auto align-items-center">
    		<li class="nav-item">
    		<a class="nav-link active" href="main">メイン</a>
    		</li>
    		<li class="nav-item">
    		<a class="nav-link" href="joinUserForm">ユーザー登録</a>
    		</li>
    		<li class="nav-item">
    		<a class="nav-link" href="loginUserForm">ログイン</a>
    		</li>
    	</ul>
    </div>
    </nav> 
    
    <div class="w3-sidebar w3-bar-block w3-animate-left" style="display:none;z-index:5" id="main">

 		  <a href="?hashtag=Java" class="w3-bar-item w3-button">#Java</a>
		  <a href="?hashtag=CSS" class="w3-bar-item w3-button">#CSS</a>
		  <a href="?hashtag=SQL" class="w3-bar-item w3-button">#SQL</a>
		  <a href="?hashtag=Javascript" class="w3-bar-item w3-button">#Javascript</a>
</div>

<!-- Page Content -->
<div class="w3-overlay w3-animate-opacity" onclick="w3_close()" style="cursor:default" id="mySidebar"></div>

<div>
  <button class="w3-button w3-white w3-medium" onclick="w3_open()"> #ホットハッシュタグ</button>
</div>
    	
    <section class="mt-4 mb-5">
    
    <div class="container mb-4">
    	<h1 class="font-weight-bold title">#ハッシュタグ</h1>
    	<div class="row">
    		<nav class="navbar navbar-expand-lg navbar-light bg-white pl-2 pr-2">
    		<button class="navbar-light navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExplore" aria-controls="navbarsDefault" aria-expanded="false" aria-label="Toggle navigation">
    		<span class="navbar-toggler-icon"></span>
    		</button>
			
			
    		</nav>
    	</div>
    </div>
    
    
<!--1.해시태그에 해당하는 이미지 불러올 예정  -->
		  
    <div class="container-fluid">
    	<div class="row">
	    	<div class="card-columns">
    			<c:forEach var="element" items="${image_list}">
	    			<div class="card card-pin">
	    				<img class="card-img" src="resources/image/event_schedule_image/${element.image.filename}" alt="Card image">
	    				<div class="overlay">
	    					<h2 class="card-title title">${element.group.name}</h2>
	    					<div class="more">
	    						<a href="post.html">
	    						<i class="fa fa-arrow-circle-o-right" aria-hidden="true"></i> Visit Group!</a>
	    					</div>
	    				</div>
	    			</div>	    			
	    		</c:forEach>
	    	</div>
    	</div>
    </div>
    </section>
        
    </main>
	
    <script src="resources/GroupRecommend/docs/assets/js/app.js"></script>
    <script src="resources/GroupRecommend/docs/assets/js/theme.js"></script>
    
    <footer class="footer pt-5 pb-5 text-center">
        
    <div class="container">
        
          <div class="socials-media">
    
            <ul class="list-unstyled">
              <li class="d-inline-block ml-1 mr-1"><a href="#" class="text-dark"><i class="fa fa-facebook"></i></a></li>
              <li class="d-inline-block ml-1 mr-1"><a href="#" class="text-dark"><i class="fa fa-twitter"></i></a></li>
              <li class="d-inline-block ml-1 mr-1"><a href="#" class="text-dark"><i class="fa fa-instagram"></i></a></li>
              <li class="d-inline-block ml-1 mr-1"><a href="#" class="text-dark"><i class="fa fa-google-plus"></i></a></li>
              <li class="d-inline-block ml-1 mr-1"><a href="#" class="text-dark"><i class="fa fa-behance"></i></a></li>
              <li class="d-inline-block ml-1 mr-1"><a href="#" class="text-dark"><i class="fa fa-dribbble"></i></a></li>
            </ul>
    
          </div>
        
            <!--
              All the links in the footer should remain intact.
              You may remove the links only if you donate:
              https://www.wowthemes.net/freebies-license/
            -->
          <p>©  <span class="credits font-weight-bold">        
            <a target="_blank" class="text-dark" href="https://www.wowthemes.net/pintereso-free-html-bootstrap-template/"><u>Pintereso Bootstrap HTML Template</u> by WowThemes.net.</a>
          </span>
          </p>
    
    
        </div>
        
    </footer>    
</body>
    
</html>
