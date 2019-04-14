<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Recommend Group Event</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<link rel="stylesheet" href="resources/GroupPhoto/assets/css/listRecommendation.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
<script src=resources/Basic/assets/js/jquery-3.3.1.min.js></script>
<script>
	function openNav() {
	  document.getElementById("mySidenav").style.width = "300px";
	}
	
	function closeNav() {
	  document.getElementById("mySidenav").style.width = "0";
	}
</script>
</head>

<body>

	<div id="mySidenav" class="sidenav">
	  <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
	  <a href="#">봄</a>
	  <a href="#">일본 취업</a>
	  <a href="#">쿠우쿠우쏜다</a>
	  <a href="#">무릎뿌가아작쿠낫타라</a>
	  <a href="#">아야야시마스요</a>
	</div>

	<h2 align="center">급상승 키워드로 당신의 그룹을 찾아보세요!</h2>
	<p><span id="thumbsUp" class="glyphicon glyphicon-thumbs-up" onclick="openNav()"> 급상승 키워드</span></p> 
	
		
			<!-- 그룹 해시태그 뿌려줘야하는데 아직 컨트롤러 불안정
				#${group_hashtag.hashtag} -->

		
	
  <!-- 			<div class="overlay">그룹이름</div> -->
	
		
<div class="container">
   <img src="resources/GroupPhoto/images/img/1.jpg" class="image" style=width:100%>
  <div class="overlay">그룹이름</div>
</div>
		
		
		<!-- Photo Grid -->
		<div class="row"> 
		  <div class="column">
		  
		  
		    <img src="resources/GroupPhoto/images/img/1.jpg"  style=width:100%>
		    <img src="resources/GroupPhoto/images/img/2.jpg"  style=width:100%>
		    <img src="resources/GroupPhoto/images/img/3.jpg"  style=width:100%>
		    <img src="resources/GroupPhoto/images/img/4.jpg"  style=width:100%>
		    <img src="resources/GroupPhoto/images/img/5.jpg"  style=width:100%>
		    <img src="resources/GroupPhoto/images/img/6.jpg"  style=width:100%>
		    <img src="resources/GroupPhoto/images/img/7.jpg"  style=width:100%>         
		  </div>
		  <div class="column">
		    <img src="resources/GroupPhoto/images/img/8.jpg" style=width:100%>
		    <img src="resources/GroupPhoto/images/img/9.jpg" style=width:100%>
		    <img src="resources/GroupPhoto/images/img/15.jpg" style=width:100%>
		    <img src="resources/GroupPhoto/images/img/10.jpg" style=width:100%>
		    <img src="resources/GroupPhoto/images/img/3.jpg" style=width:100%>
		    <img src="resources/GroupPhoto/images/img/4.jpg" style=width:100%>
		    <img src="resources/GroupPhoto/images/img/14.jpg" style=width:100%>         
		  </div>
		  <div class="column">
		    <img src="resources/GroupPhoto/images/img/1.jpg" style=width:100%>
		    <img src="resources/GroupPhoto/images/img/10.jpg" style=width:100%>
		    <img src="resources/GroupPhoto/images/img/8.jpg" style=width:100%>
		    <img src="resources/GroupPhoto/images/img/2.jpg" style=width:100%>
		    <img src="resources/GroupPhoto/images/img/9.jpg" style=width:100%>
		    <img src="resources/GroupPhoto/images/img/14.jpg" style=width:100%>   
		    <img src="resources/GroupPhoto/images/img/7.jpg" style=width:100%>         
		  </div>
		  <div class="column">
		    <img src="resources/GroupPhoto/images/img/1.jpg" style=width:100%>
		    <img src="resources/GroupPhoto/images/img/10.jpg" style=width:100%>
		    <img src="resources/GroupPhoto/images/img/11.jpg" style=width:100%>
		    <img src="resources/GroupPhoto/images/img/12.jpg" style=width:100%>
			<img src="resources/GroupPhoto/images/img/3.jpg" style=width:100%>
			<img src="resources/GroupPhoto/images/img/4.jpg" style=width:100%>
			<img src="resources/GroupPhoto/images/img/5.jpg" style=width:100%>
		  </div>
		</div>   
   	</div>
</body>
</html> 
