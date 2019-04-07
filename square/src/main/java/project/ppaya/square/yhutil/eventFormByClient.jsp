<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.Calendar"%>
<!DOCTYPE html>
<html>
<head>
	<title>Home</title>
	<script src="resources/js/jquery-3.3.1.min.js"></script>
	<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
	<script src="resources/js/eventFormJS.js"></script>
	<script>
		var event_num = ${requestScope.event.event_num};
		var attendee_list = [];
		<c:forEach var="attendee" items="${requestScope.attendee_list}">
			attendee_list.push("${attendee.id}");
		</c:forEach>
		var start_period;
		var end_period;
		var integrate_view = [];
		var integrate_finder = [];
		var multiple_integrate_schedule_list;
		var multiple_integrate_view = [];
		var term = 0;
		function parseDateToString(date)
		{
			return date.getFullYear() + "-" + setZero((date.getMonth() + 1), 2) + "-" + setZero(date.getDate(), 2) + "T" + setZero(date.getHours(), 2) + ":" + setZero(date.getMinutes(), 2);
		}
		$(document).ready(function()
				{
					var start = new Date();
					var end = new Date(start.getTime() + (86400 * 1000 * 7));
					$("#start_period").val(parseDateToString(new Date()));
					$("#end_period").val(parseDateToString(new Date((new Date()).getTime() + (86400 * 1000 * 7))));
					
					$(":checkbox[name='essential_list']").change(function()
							{
								setIntegrateView();
								setIntegrateFinder();
								setScrollIntegrateView();
							});
					$("#term").change(function()
							{
								term = $("#term").val();
								$("#term_value").html(parseInt(term / 60) + ":" + term % 60);
							});
					$("#start_period_input").change(function()
							{
								setStartPeriod();
								recommendDateAction();
							});
					$("#end_period_input").change(function()
							{
								setEndPeriod();
								recommendDateAction();
							});
					getMultipleIntegrateScheduleMapListAction(event_num, attendee_list, $("#start_period").val(), $("#end_period").val());
					//console.log(multiple_integrate_schedule_map_list);
					/* for(var i = 0; i < multiple_integrate_schedule_map_list.length; i++)
					{
						for(var j = 0; j < multiple_integrate_schedule_map_list[i].length; j++)
						{
							console.log(multiple_integrate_schedule_map_list[i]);
						}
					} */
				}				
		);	
		function createSingleTimeLine(id, map_list)
		{
			google.charts.load('current', {'packages':['timeline']});
			google.charts.setOnLoadCallback(drawChart);
			
			function drawChart()
			{
				var container = document.getElementById(id);
				var chart = new google.visualization.Timeline(container);
				var dataTable = new google.visualization.DataTable();

				dataTable.addColumn({ type: 'string', id: 'President' });
				dataTable.addColumn({ type: 'string', id: 'Name' });
				dataTable.addColumn({ type: 'string', id: 'style', role: 'style' });
				dataTable.addColumn({ type: 'date', id: 'Start' });
				dataTable.addColumn({ type: 'date', id: 'End' });
		  
				for(var i = 0; i < map_list.length; i++)
				{
					console.log(map_list[i].category);
					if(map_list[i].category == "category")
					{
						dataTable.addRows([
							['single', '', 'Tomato', new Date(map_list[i].start_millidate), new Date(map_list[i].end_millidate)]
						]);
					}
					else
					{
						dataTable.addRows([
							['single', '', 'MediumSeaGreen', new Date(map_list[i].start_millidate), new Date(map_list[i].end_millidate)]
						]);
					}
				}
				
				var option = {
					width : (map_list[map_list.length - 1].end_millidate - map_list[0].start_millidate) / 50000,
					height: 100,
					timeline: {showRowLabels: false}, 
				};
				chart.draw(dataTable, option);
			}
		}
		function createTimeLine(id, map_list)
		{
			google.charts.load('current', {'packages':['timeline']});
			google.charts.setOnLoadCallback(drawChart);
			function drawChart()
			{
				var container = document.getElementById(id);
				var chart = new google.visualization.Timeline(container);
				var dataTable = new google.visualization.DataTable();

				dataTable.addColumn({ type: 'string', id: 'President' });
				dataTable.addColumn({ type: 'string', id: 'label' });
				dataTable.addColumn({ type: 'string', id: 'style', role: 'style' });
				dataTable.addColumn({ type: 'string', role: 'tooltip', p: {html: true}});
				dataTable.addColumn({ type: 'date', id: 'Start' });
				dataTable.addColumn({ type: 'date', id: 'End' });
		  
				for(var i = 0; i < map_list.length; i++)
				{
					if(id == "timeline5"){console.log("length : " + map_list[i].length);}
					for(var j = 0; j < map_list[i].length; j++)
					{
						if(id == "timeline5"){console.log("i : " + i + "j : " + j);}
						if(map_list[i][j].category == "schedule")
						{
							dataTable.addRows([
								[map_list[i][j].id, '', 'Tomato', (new Date(map_list[i][j].start_millidate)).toString() + "<br> ~ <br>" + (new Date(map_list[i][j].end_millidate)).toString() + "<br><br><br>" + map_list[i][j].description, new Date(map_list[i][j].start_millidate), new Date(map_list[i][j].end_millidate)]
							]);
						}
						else
						{
							dataTable.addRows([
								[map_list[i][j].id, '', 'MediumSeaGreen', (new Date(map_list[i][j].start_millidate)).toString() + "<br> ~ <br>" + (new Date(map_list[i][j].end_millidate)).toString(), new Date(map_list[i][j].start_millidate), new Date(map_list[i][j].end_millidate)]
							]);
						}
					}
				}
				var option = {
						width: 2737800000 / 50000, 
						//(map_list[map_list.length - 1].end_millidate - map_list[0].start_millidate) / 50000,
						tooltip: {isHtml: true},
						height: map_list.length * 50,
					};
				 
				chart.draw(dataTable, option);
			}
		}
		function setIntegrateView()
		{
			var essential_list_checkbox = $(":checkbox[name='essential_list']");
			var integrate_essential_map_list = [];
			
			for(var i = 0; i < essential_list_checkbox.length; i++)
			{
				if($(essential_list_checkbox[i]).prop("checked") == true)
				{
					integrate_essential_map_list = integrate_essential_map_list.concat(separate_maplist_list[i]);
				}
			}
			
			integrate_essential_map_list = integrateMapList(integrate_essential_map_list);
			integrate_view = parseMapListToView(integrate_essential_map_list, start_period, end_period);
			
			setView("integrate_view_canvas", integrate_view, start_period, end_period, term);
		}
		function setIntegrateFinder()
		{
			integrate_finder = parseViewToFinder(integrate_view, term);			
			setView("integrate_finder_canvas", integrate_finder, start_period, end_period, 0);
		}
		function getMultipleIntegrateScheduleMapListAction(event_num, attendee_list, start_period, end_period)
		{		
			$.ajax({
				url: "getMultipleIntegrateScheduleMapListAction",
				type: "POST",
				data: {event_num: event_num, attendee_list: attendee_list.toString(), start_period: start_period, end_period: end_period},
				dateType: "JSON",
				success: function(map)
				{
					var temp_attendee = ["test1", "test3", "test4", "test6", "test7"];
					multiple_integrate_schedule_list = map.multiple_integrate_schedule_list;
					createTimeLine("timeline", map.multiple_integrate_schedule_list);
					createTimeLine("timeline2", map.multiple_separate_schedule_list);
					for(var i = 0; i < map.multiple_integrate_schedule_list.length; i++)
					{
						multiple_integrate_view.push(parseScheduleListToView(map.multiple_integrate_schedule_list[i] , 1551565800000, 1554303600000, temp_attendee[i]));
					}
					console.log(map.multiple_integrate_schedule_list);
					console.log(multiple_integrate_view);
					createTimeLine("timeline5", multiple_integrate_view);
					createSingleTimeLine("timeline3", parseScheduleListToView(integrateMultipleIntegrateScheduleList(map.multiple_integrate_schedule_list), 1551565800000, 1551625200000, 'single'));
					createSingleTimeLine("timeline4", parseScheduleListToView(integrateMultipleIntegrateScheduleList(map.multiple_integrate_schedule_list), 1551565800000, 1554303600000, 'single'));
					
					//console.log(integrateMultipleIntegrateScheduleList(map.multiple_integrate_schedule_list));
					//1554303600000
					//1551625200000
					var test_view = [];
					for(var i = (new Date(2019, 2, 4, 0, 0, 0)).getTime(); i < (new Date(2019, 2, 5, 0, 0, 0)).getTime(); i += 60000)
					{
						test_view.push({start_millidate : i, end_millidate: i + 60000});
					}
					//createSingleTimeLine("timeline3", test_view);
				},
				error: function(error)
				{
					console.log(error);
				}
			});
		}
	</script>
</head>
<body>
	<p>${requestScope.event.host}</p>
	<p>${requestScope.attendee_list.size()}</p>
	<div id="timeline" style="display: block; overflow-x: scroll; overflow-y: hidden; height: auto; width: 80%"></div>
	<div id="timeline2" style="display: block; overflow-x: scroll; overflow-y: hidden; height: auto; width: 80%"></div>
	<div id="timeline5" style="display: block; overflow-x: scroll; overflow-y: hidden; height: auto; width: 80%"></div>
	<div id="timeline3" style="display: block; overflow-x: scroll; overflow-y: hidden; height: auto; width: 80%"></div>
	<div id="timeline4" style="display: block; overflow-x: scroll; overflow-y: hidden; height: auto; width: 80%"></div>
	<form id="recommend_date_form">
		<c:forEach var="attendee" items="${requestScope.attendee_list}">
			<p>
				<label>
					${attendee.id}<input type="checkbox" name="essential_list" value="${attendee.id}">
				</label>
			</p>
		</c:forEach>
		<input type="datetime-local" id="start_period"><input type="datetime-local" id="end_period">
		<br>
		<input type="range" id="term" name="term" min="0" max="300" value="0"><p id="term_value">0</p>
	</form>
</body>
</html>
