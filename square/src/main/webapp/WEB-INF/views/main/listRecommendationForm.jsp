<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Recommend Group Event</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
<script src=resources/Basic/assets/js/jquery-3.3.1.min.js></script>
<style>
		/* 급상승 키워드로 이미지 받아오기*/
			body {
			  font-family: "Lato", sans-serif;
			}
			
			.sidenav {
			  height: 100%;
			  width: 0;
			  position: fixed;
			  z-index: 1;
			  top: 0;
			  left: 0;
			  background-color: #111;
			  overflow-x: hidden;
			  transition: 0.5s;
			  padding-top: 60px;
			}
			
			.sidenav a {
			  padding: 10px 10px 10px 40px;
			  text-decoration: none;
			  font-size: 15px;
			  color: #818181;
			  display: block;
			  transition: 0.3s;
			}
			
			.sidenav a:hover {
			  color: #f1f1f1;
			}
			
			.sidenav .closebtn {
			  position: absolute;
			  top: 0;
			  right: 25px;
			  font-size: 36px;
			  margin-left: 50px;
			}
			
			#thumbsUp
			{
				padding: 10px 10px 10px 40px;
				font-size:15px;
				cursor:pointer;
			}
			
			@media screen and (max-height: 450px) {
			  .sidenav {padding-top: 15px;}
			  .sidenav a {font-size: 18px;}
			}
			
			{
			  box-sizing: border-box;
			}
			
			body {
			  margin: 0;
			  font-family: Arial;
			}
			
			.header {
			  text-align: center;
			  padding: 32px;
			}
			
			.row {
			  display: -ms-flexbox; /* IE10 */
			  display: flex;
			  -ms-flex-wrap: wrap; /* IE10 */
			  flex-wrap: wrap;
			  padding: 50px 130px 10px;
			}
			
			/* Create four equal columns that sits next to each other */
			.column {
			  -ms-flex: 25%; /* IE10 */
			  flex: 25%;
			  max-width: 25%;
			  padding: 4px;
			}
			
			.column img {
			  margin-top: 8px;
			  vertical-align: middle;
			}
			
			/* Responsive layout - makes a two column-layout instead of four columns */
			@media screen and (max-width: 800px) {
			  .column {
			    -ms-flex: 50%;
			    flex: 50%;
			    max-width: 50%;
			  }
			}
			
			/* Responsive layout - makes the two columns stack on top of each other instead of next to each other */
			@media screen and (max-width: 600px) {
			  .column {
			    -ms-flex: 100%;
			    flex: 100%;
			    max-width: 100%;
			  }
			}
			
			/* 이벤트 사진 커서 올리면 나오는  */	
			* {box-sizing: border-box;}
			
			.container {
			  position: relative;
			  width: 50%;
			  max-width: 300px;
			}
			
			.image {
			  display: block;
			  width: 100%;
			  height: auto;
			}
			
			.overlay {
			  position: absolute; 
			  bottom: 0; 
			  background: rgb(0, 0, 0);
			  background: rgba(0, 0, 0, 0.5); /* Black see-through */
			  color: #f1f1f1; 
			  width: 100%;
			  transition: .5s ease;
			  opacity:0;
			  color: white;
			  font-size: 20px;
			  padding: 20px;
			  text-align: center;
			}
			
			.container:hover .overlay {
			  opacity: 1;
			}

</style>
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
	<p><span id="thumbsUp" class="glyphicon glyphicon-thumbs-up" onclick="openNav()">급상승 키워드</span></p> 
	
	
		<!-- Header -->
		<div class="header">
		  <h1>(해시태그) 그룹들</h1>
		</div>
		
		<!-- Photo Grid -->
		<div class="row"> 
		  <div class="column">
		    <img src="resources/GroupPhoto/images/img/1.jpg" style=width:100%>
		    <img src="resources/GroupPhoto/images/img/2.jpg" style=width:100%>
		    <img src="resources/GroupPhoto/images/img/3.jpg" style=width:100%>
		    <img src="resources/GroupPhoto/images/img/4.jpg" style=width:100%>
		    <img src="resources/GroupPhoto/images/img/5.jpg" style=width:100%>
		    <img src="resources/GroupPhoto/images/img/6.jpg" style=width:100%>
		    <img src="resources/GroupPhoto/images/img/7.jpg" style=width:100%>         
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
   
</body>
</html> 
