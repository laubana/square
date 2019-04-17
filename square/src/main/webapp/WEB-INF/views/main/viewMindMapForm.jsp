<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" >

<head>
  <title>js-mindmap demo - JavaScript Mindmap</title>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
  <link href="https://fonts.googleapis.com/css?family=Montserrat:300,400,500" rel="stylesheet">
  <link rel="stylesheet" href="resources/MyPage/assets/css/main.css" />
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
  <link rel="stylesheet" href="resources/undermenu/css/style.css">
  <link rel="stylesheet" type="text/css" href="resources/MindMap/css/js-mindmap.css" />
  <link href="resources/MindMap/css/style.css" type="text/css" rel="stylesheet"/>
  
<style type="text/css">
body{
background: url('resources/MindMap/image/3.jpg'); 
  -webkit-background-size: cover;
  -moz-background-size: cover;
  -o-background-size: cover;
  background-size: cover;
}
</style>
  
</head>

<body>

<ul>
	<li><a href="">#${root}</a>
		<ul>
			<c:forEach var="element1" items="${list}">
				<li><a href="">${element1.node.group_id}</a>
					<ul>
						<c:forEach var="element2" items="${element1.list}">
							<li><a href="">#${element2.node}</a>
								<ul>
									<c:forEach var="element3" items="${element2.list}">
										<li><a href="">${element3.node.group_id}</a>
											<ul>
												<c:forEach var="element4" items="${element3.list}">
													<li><a href="">#${element4.node}</a>
													</li>
												</c:forEach>
											</ul>
										</li>
									</c:forEach>
								</ul>							
							</li>
						</c:forEach>
					</ul>
				</li>
			</c:forEach>				
		</ul>
	</li>
</ul>

<article id="slider">
  
  <div id="thumbnail" class="thumbnail left">
    <ul class="thumbnail-list">
      <li class="">
        <div align="center"><span><a href="main">Group Name</a></span></div>
        <br>
        <hr>
        <p align="center"><a href="main">#tag</a></p>
        <hr>
        <p align="center"><a href="main">#tag</a></p>
        <hr>
        <p align="center"><a href="main">#tag</a></p>
        <p align="center"><a href="main">#tag</a></p>
        <p align="center"><a href="main">#tag</a></p>
        <p align="center"><a href="main">#tag</a></p>
        <p align="center"><a href="main">#tag</a></p>
        <p align="center"><a href="main">#tag</a></p>
        <p align="center"><a href="main">#tag</a></p>
        <p align="center"><a href="main">#tag</a></p>
        <p align="center"><a href="main">#tag</a></p>
        <p align="center"><a href="main">#tag</a></p>
        <p align="center"><a href="main">#tag</a></p>
        <p align="center"><a href="main">#tag</a></p>
        <p align="center"><a href="main">#tag</a></p>
        <p align="center"><a href="main">#tag</a></p>
        <p align="center"><a href="main">#tag</a></p>
        <p align="center"><a href="main">#tag</a></p>
        <p align="center"><a href="main">#tag</a></p>
        <p align="center"><a href="main">#tag</a></p>
        <p align="center"><a href="main">#tag</a></p>
      </li>
      <!-- <li class="">
        <div align="center"><span><a href="main">list 2</a></span></div>
        <br>
        <p align="center"><a href="main">#tag</a></p>
      </li> -->
      <li class="marker"></li>
    </ul>
  </div>
</article>

<script src="resources/Basic/assets/js/jquery-3.3.1.min.js"></script>
<script src="resources/undermenu/js/index.js"></script>

  <!-- jQuery -->
  <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.6.2/jquery.min.js" type="text/javascript"></script>
  <!-- UI, for draggable nodes -->
  <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.15/jquery-ui.min.js"></script>

  <!-- Raphael for SVG support (won't work on android) -->
  <script type="text/javascript" src="resources/MindMap/js/raphael-min.js"></script>

  <!-- Mindmap -->
  <script type="text/javascript" src="resources/MindMap/js/js-mindmap.js"></script>

  <!-- Kick everything off -->
  <script src="resources/MindMap/js/script.js" type="text/javascript"></script>
<script>
//////////////////////////////////////////////////////////////////////////////
console.log(JSON.parse('${json_list}'));
</script>



</body>

</html>
