<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <title>js-mindmap demo - JavaScript Mindmap</title>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
  <link rel="stylesheet" type="text/css" href="resources/MindMap/css/js-mindmap.css" />
  <link href="resources/MindMap/css/style.css" type="text/css" rel="stylesheet"/>

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
console.log(json.stringify(${list}));
</script>
</head>
<body>
  <ul>
    <li><a href="http://kenneth.kufluk.com/blog/">#Java</a>
    	<ul>
	      	<li><a href="http://kenneth.kufluk.com/blog/">1</a>
	      		<ul>
			      	<li><a href="http://kenneth.kufluk.com/blog/">#Java</a>
			      		<ul>
					      	<li><a href="http://kenneth.kufluk.com/blog/">1</a></li>
					      	<li><a href="http://kenneth.kufluk.com/blog/">2</a></li>
						</ul>
			      	</li>
			      	<li><a href="http://kenneth.kufluk.com/blog/">#C</a>
			      		<ul>
					      	<li><a href="http://kenneth.kufluk.com/blog/">1</a></li>
					      	<li><a href="http://kenneth.kufluk.com/blog/">2</a></li>
						</ul>
			      	</li>
				</ul>
			</li>
	      	<li><a href="http://kenneth.kufluk.com/blog/">2</a>
	      		<ul>
			      	<li><a href="http://kenneth.kufluk.com/blog/">#Java</a>
			      		<ul>
					      	<li><a href="http://kenneth.kufluk.com/blog/">1</a></li>
					      	<li><a href="http://kenneth.kufluk.com/blog/">2</a></li>
						</ul>
			      	</li>
				</ul>
			</li>
			<li><a href="http://kenneth.kufluk.com/blog/">3</a>
	      		<ul>
			      	<li><a href="http://kenneth.kufluk.com/blog/">#Java</a>
			      		<ul>
					      	<li><a href="http://kenneth.kufluk.com/blog/">1</a></li>
					      	<li><a href="http://kenneth.kufluk.com/blog/">2</a></li>
						</ul>
			      	</li>
			      	<li><a href="http://kenneth.kufluk.com/blog/">#C</a></li>
				</ul>
			</li>
			<li><a href="http://kenneth.kufluk.com/blog/">4</a>
	      		<ul>
			      	<li><a href="http://kenneth.kufluk.com/blog/">#Java</a></li>
			      	<li><a href="http://kenneth.kufluk.com/blog/">#C</a></li>
				</ul>
			</li>
			<li><a href="http://kenneth.kufluk.com/blog/">5</a>
	      		<ul>
			      	<li><a href="http://kenneth.kufluk.com/blog/">#Java</a></li>
			      	<li><a href="http://kenneth.kufluk.com/blog/">#C</a></li>
				</ul>
			</li>
			<li><a href="http://kenneth.kufluk.com/blog/">6</a>
	      		<ul>
			      	<li><a href="http://kenneth.kufluk.com/blog/">#Java</a></li>
			      	<li><a href="http://kenneth.kufluk.com/blog/">#C</a></li>
				</ul>
			</li>
		</ul>
    </li>
  </ul>
</body>
</html>
