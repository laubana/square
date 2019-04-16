<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <title>YHTEST1</title>
    <meta charset="utf-8" />
    <script src="resources/Basic/assets/js/jquery-3.3.1.min.js"></script>
    <script>
    	function yhtestaction3()
    	{
    		var map = {};
    		map["test"] = "test";
    		
    		var reader = new FileReader();
    		reader.readAsDataURL(document.getElementById("file").files[0]);
    		reader.onload = function()
    		{
                map["file"] = reader.result.substring(reader.result.indexOf(",") + 1);
            }
    			
    		var interval = setInterval(function()
    				{
    					if(typeof(map.file) != "undefined")
    					{
    						$.ajax({
    			    			url: "yhtestaction3",
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
  </head>
  <body>
  	<input type="file" id="file">
  	<input type="text" id="text">
  	<input type="button" onclick="yhtestaction3()">
</body>
</html>