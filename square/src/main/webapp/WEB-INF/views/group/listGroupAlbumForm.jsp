<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<!--
	Multiverse by HTML5 UP
	html5up.net | @ajlkn
	Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->
<html>
	<head>
		<title>Multiverse by HTML5 UP</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="resources/GroupPhoto/assets/css/main.css" />
		<noscript><link rel="stylesheet" href="resources/GroupPhoto/assets/css/noscript.css" /></noscript>
	</head>
	<body class="is-preload">

		<!-- Wrapper -->
			<div id="wrapper">

				<!-- Header -->
					<header id="header">
						<h1><a href="viewGroupForm?group_category=${group_category.group_category_id}&group_id=${group.group_id}"><strong>${group.name}</strong> 앨범</a></h1>
						<nav>
							<ul>
								<li><p class="copyright">
									&copy; Untitled. Design: <a href="http://html5up.net">HTML5 UP</a>.
								</p></li>
							</ul>
						</nav>
					</header>

				<!-- Main -->
					<div id="main">
					<c:forEach var="image" items="${event_schedule_image_list}">
					<article class="thumb">
							<a href="resources/image/event_schedule_image/${image.filename}" class="image"><img src="resources/image/event_schedule_image/${image.filename}" alt="" /></a>
							
						</article>
					</c:forEach>
					</div>

				<!-- Footer -->
					
			</div>

		<!-- Scripts -->
			<script src="resources/GroupPhoto/assets/js/jquery.min.js"></script>
			<script src="resources/GroupPhoto/assets/js/jquery.poptrox.min.js"></script>
			<script src="resources/GroupPhoto/assets/js/browser.min.js"></script>
			<script src="resources/GroupPhoto/assets/js/breakpoints.min.js"></script>
			<script src="resources/GroupPhoto/assets/js/util.js"></script>
			<script src="resources/GroupPhoto/assets/js/main.js"></script>

	</body>
</html>