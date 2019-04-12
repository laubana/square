<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script>

/* 
 서버로부터 좌표 5개 받아서 똑같으면 제끼고 각각 한 번만 들어가도록 배열 새로 만드는 코딩
coodlist.add("{lat: -37.774785, lng: 145.137978}");
coodlist.add("{lat: -37.819616, lng: 144.968119}");
coodlist.add("{lat: -37.774785, lng: 145.137978}");
coodlist.add("{lat: -39.927193, lng: 175.053218}");
coodlist.add("{lat: -41.330162, lng: 174.865694}");
*/
var coodlist = ${requestScope.coodlist};
var coodlist_temp = new Array();
var flag_same = false;
var i;
var j;
var k;
	
	coodlist_temp.push(coodlist[0]);
	
	for(i = 0; i < coodlist.length; i = i + 1){
		flag_same = false;	
		for(j = 0; j < coodlist_temp.length; j = j + 1){
			if(coodlist[i].lat == coodlist_temp[j].lat && coodlist[i].lng == coodlist_temp[j].lng){
			console.log('i: ' + i + '   j: ' + j);
				
				flag_same = true;	
				break;
			}
			
		}
		if(!flag_same){
			coodlist_temp.push(coodlist[i]);
			
		}
	}	
	


	for(k = 0; k < coodlist_temp.length; k = k + 1){
		
		console.log( JSON.stringify( coodlist_temp[k] ).replace(/&quot;/gi, '') + k );
	}


</script>



</head>
<body>




</body>
</html>