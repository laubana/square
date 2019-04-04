<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>

body {
	line-height: 1;
    background-color: #efeff5;
    text-align: center;
}

th
{
	width: 100px;
	text-align: right;
	padding: 30px 250px;
}
td
{	

	padding: 10px;
	text-align: left;
}
input
{
	width: 200px;
 	padding: 3px 3px;
 	margin: 20px 0;
 	box-sizing: border-box;
 	
}

input[type="submit"]
{
	text-align: center;
}

.jointable
{
	text-align: center;
}
</style>
</head>
<body>
						<!-- Contact -->
								<h2>join</h2>
								 <img src="resources/Join/images/pic03.jpg" alt="" />
									<form>
									<table class="joinTable">
										<tr>
											<th>email</th>
												<td>
													<input type = "text" name = "email">
														<select name = "email">
														<option>선택</option>
														<option>yahoo.com</option>
														<option>naver.com</option>
														<option>gmail.com</option>
														<option>daum.net</option>
														<option>hanmail.net</option>
														<option>직접입력</option>
												</td>	
													</select>
										</tr>
										<tr>
											<th>password</th>
											<td>
												<input type = "password" name = "password" id="password">		
											</td>
										</tr>	
										<tr>
											<th>password double check</th>
											<td>
												<input type = "password" name = "pwDoubleCheck" id="pwDoubleCheck">		
											</td>
										</tr>	
										<tr>
											<th>Phone</th>
											<td width = "1000" height = "50">
												<select name = "com">
													<option>선택<option>
													<option>010<option>
													<option>801<option>
													<option>직접입력<option>
												</select>
													-<input type = "text" name = "phone_middle">
													-<input type = "text" name = "phone_last">
											</td>
										</tr>
									</table>
									<input type="submit" value="회원가입">
								</form>
									<!-- id(중복), pw(중복), name, address, e-mail  -->
										

								

	<script src="resources/Join/assets/js/jquery.min.js"></script>
</body>
</html>