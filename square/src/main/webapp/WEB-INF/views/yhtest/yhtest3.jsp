<%-- <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
</html> --%>
<!DOCTYPE html>
<html>
  <head>
    <title>Place Autocomplete Address Form</title>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no">
    <meta charset="utf-8">
    <!-- <style>
      /* Always set the map height explicitly to define the size of the div
       * element that contains the map. */
      #map {
        height: 100%;
      }
      /* Optional: Makes the sample page fill the window. */
      html, body {
        height: 100%;
        margin: 0;
        padding: 0;
      }
    </style>
    <link type="text/css" rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500">
    <style>
      #locationField, #controls {
        position: relative;
        width: 480px;
      }
      #autocomplete {
        position: absolute;
        top: 0px;
        left: 0px;
        width: 99%;
      }
      .label {
        text-align: right;
        font-weight: bold;
        width: 100px;
        color: #303030;
        font-family: "Roboto";
      }
      #address {
        border: 1px solid #000090;
        background-color: #f0f9ff;
        width: 480px;
        padding-right: 2px;
      }
      #address td {
        font-size: 10pt;
      }
      .field {
        width: 99%;
      }
      .slimField {
        width: 80px;
      }
      .wideField {
        width: 200px;
      }
      #locationField {
        height: 20px;
        margin-bottom: 2px;
      }
    </style> -->
  </head>

<body>
	<div id="locationField">
		<input id="autocomplete" placeholder="Enter your address" type="text"/>
    </div>
	<input type="text" id="administrative_area_level_1" readonly>
	<input type="text" id="locality" readonly>
    <script>
	var autocomplete;

	function initAutocomplete()
	{
		autocomplete = new google.maps.places.Autocomplete(document.getElementById('autocomplete'), {types: ['geocode']});
		autocomplete.setFields(['address_component']);
		autocomplete.addListener('place_changed', fillInAddress);
	}
	function fillInAddress()
	{
		var place = autocomplete.getPlace();
  
		console.log(place);
		
		for (var i = 0; i < place.address_components.length; i++)
		{
			if(place.address_components[i].types[0] == "administrative_area_level_1")
			{
				document.getElementById("administrative_area_level_1").value = place.address_components[i].long_name;
			}
			if(place.address_components[i].types[0] == "locality")
			{
				document.getElementById("locality").value = place.address_components[i].long_name;
			}
		}
	}
    </script>
	<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAujlCmx3gpGvD5ZHDN3Vqwp1hG0h-J3cc&libraries=places&callback=initAutocomplete" async defer></script>
	</body>
</html>