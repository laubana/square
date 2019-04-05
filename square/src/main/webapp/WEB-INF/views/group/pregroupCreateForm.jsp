<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>Onassis - Bootstrap Agecy Template</title>
<meta content="width=device-width, initial-scale=1.0" name="viewport">
<meta content="" name="keywords">
<meta content="" name="description">

<!-- Favicons -->
<link href="img/favicon.png" rel="icon">
<link href="img/apple-touch-icon.png" rel="apple-touch-icon">

<!-- Google Fonts -->
<link
	href="https://fonts.googleapis.com/css?family=Lato:300,400,700,300italic,400italic|Raleway:400,300,700"
	rel="stylesheet">

<!-- Bootstrap CSS File -->
<link href="resources/CreateGroup/lib/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">

<!-- Libraries CSS Files -->
<link
	href="resources/CreateGroup/lib/font-awesome/css/font-awesome.min.css"
	rel="stylesheet">

<!-- Main Stylesheet File -->
<link href="resources/GroupCreate/css/style.css" rel="stylesheet">

<!-- =======================================================
    Template Name: Onassis
    Template URL: https://templatemag.com/onassis-bootstrap-agency-template/
    Author: TemplateMag.com
    License: https://templatemag.com/license/
  ======================================================= -->
<script>
	// Add the following code if you want the name of the file appear on select
	$(".custom-file-input").on(
			"change",
			function() {
				var fileName = $(this).val().split("\\").pop();
				$(this).siblings(".custom-file-label").addClass("selected")
						.html(fileName);
			});
</script>
</head>

<body>

	<!-- Menu -->
	<nav class="menu" id="theMenu">
		<div class="menu-wrap">
			<h1 class="logo">
				<a href="index.html#home">PPAYA</a>
			</h1>
			<i class="fa fa-times-circle menu-close"></i> <a href="main"
				class="smoothscroll">main</a> <a href="grouplist"
				class="smoothscroll">search group</a> <a href="#about"
				class="smoothscroll">About</a> <a href="#contact"
				class="smoothscroll">Contact</a> <a href="#"><i
				class="fa fa-facebook"></i></a> <a href="#"><i class="fa fa-twitter"></i></a>
			<a href="#"><i class="fa fa-dribbble"></i></a> <a href="#"><i
				class="fa fa-envelope"></i></a>
		</div>

		<!-- Menu button -->
		<div id="menuToggle">
			<i class="fa fa-bars"></i>
		</div>
	</nav>



	<!-- ========== HEADER SECTION ========== -->
	<section id="home" name="home"></section>
	<div id="headerwrap">
		<div class="container">
			<br>
			<h1>PPAYA</h1>
			<h2>Fancy Group Sharing Services</h2>
			<div class="row">
				<br> <br> <br>
				<div class="col-lg-6 col-lg-offset-3"></div>
			</div>
		</div>
		<!-- /container -->
	</div>
	<!-- /headerwrap -->


	<!-- ========== WHITE SECTION ========== -->
	<div id="w">
		<div class="container">
			<div class="row">
				<div class="col-lg-8 col-lg-offset-2">
					<h3>
						<bold>Welcom to PPAYA!</bold>
						<br>
						<board>Welcome to Our Fancy Group Sharing Services!</bold> <br>
						반갑습니당당당^^ <bold>아래 가이드를 따라 그룹을 생성해주쎄용</bold>. <br />
					</h3>
				</div>
			</div>
		</div>
		<!-- /w -->


		<!-- 3rd Step : 그룹 정보 입력-->
		<!-- ========== SERVICES - GREY SECTION ========== -->
		<section id="services" name="services"></section>
		<div class="container">
			<h3>Create your Group!</h3>
			<br>
			<form>
				<h3>Group Title!</h3>
				<input type="text" name="groupTitle" placeholder="group Title..">
			<br>
			<div class="container mt-3">
				<h3>Group Image!</h3>
				<p>Express your group with fancy image!</p>
					<h3>Upload Group Logo:</h3>
					<div class="custom-file mb-3" align="center">
						<input type="file" class="custom-file-input" id="customFile"
							name="filename"> 
					</div>
					<h3>Upload Group Image:</h3>
					<div align="center">
						<input type="file" id="myFile" name="filename2">				
					</div>
					<div class="container">
					<h3>select group interests</h3>
						<div class="form-check-inline">
						 	<input type="radio" class="form-check-input" id="radio1" name="optradio" value="food">food
						</div>
						<div class="form-check-inline">
							<input type="radio" class="form-check-input" id="radio2" name="optradio" value="beauty">beauty
						</div>
						<div class="form-check-inline">
							 <input type="radio" class="form-check-input" id="radio3" name="optradio" value="sports">sports
						</div>
						<h3>express about your group!</h3>
						<textarea class="groupExpress" id="groupExpress"
							name="groupExpress" placeholder="Explain about your group here!"></textarea>
			</div>
			</form>
			<input type="button" value="submit">
		</div>

		<!-- /container -->







		<!-- ========== WHITE SECTION ========== -->
		<div id="w">
			<div class="container">
				<div class="row">
					<div class="col-lg-8 col-lg-offset-2">
						<h3>
							WE WORK HARD TO DELIVER A
							<bold>HIGH QUALITY SERVICE</bold>
							. OUR AIM IS YOUR COMPLETE
							<bold>SATISFACTION</bold>
							.
						</h3>
					</div>
				</div>
			</div>
			<!-- /container -->
		</div>
		<!-- /w -->

		<div id="copyrights">
			<div class="container">
				<p>
					&copy; Copyrights <strong>Onassis</strong>. All Rights Reserved
				</p>
				<div class="credits">
					<!--
          You are NOT allowed to delete the credit link to TemplateMag with free version.
          You can delete the credit link only if you bought the pro version.
          Buy the pro version with working PHP/AJAX contact form: https://templatemag.com/onassis-bootstrap-agency-template/
          Licensing information: https://templatemag.com/license/
        -->
					Created with Onassis template by <a href="https://templatemag.com/">TemplateMag</a>
				</div>
			</div>
		</div>

		<!-- JavaScript Libraries -->
		<script src="resources/GroupCreate/lib/jquery/jquery.min.js"></script>
		<script src="resources/GroupCreate/lib/bootstrap/js/bootstrap.min.js"></script>
		<script src="resources/GroupCreate/lib/php-mail-form/validate.js"></script>
		<script src="resources/GroupCreate/lib/chart/chart.js"></script>
		<script src="resources/GroupCreate/lib/easing/easing.min.js"></script>

		<!-- Template Main Javascript File -->
		<script src="resources/GroupCreate/js/main.js"></script>
</body>
</html>
