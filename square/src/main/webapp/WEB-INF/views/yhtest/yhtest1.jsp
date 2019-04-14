<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <title>YHTEST1</title>
    <meta charset="utf-8" />
    <script>
    var list = JSON.parse('${requestScope.json_list}');
    	console.log(list);
    </script>
  </head>
  <body>
<table>
  <c:forEach var="comment" items="${list}">
  	<tr>
  		<td>
  			${comment.group_comment.content}
  		</td>
  		<td>
  			<table>
	  		<c:forEach var="tag" items="${comment.group_comment_tag_list}">
	  			<td>
	  				${tag.group_comment_tag.tag}
	  			</td>
	  			<td>
	  				<table>
	  			<c:forEach var="group" items="${tag.group_list}">
	  			<td>
	  				${group.name}
	  			</td>	  			
	  			</c:forEach>
	  			</table>
	  			</td>
	  		</c:forEach>
	  		</table>
  		</td>
  	</tr>
  </c:forEach>
  </table>
  </body>
</html>