<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>用户登录</title>
<link href="<c:url value="/static/css/login/login.css"/>" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<c:url value='/static/js/easyui1.4.1/jquery.min.js'/>"></script>
</head>
<body>
<form id="form1" method="post">
<div class="login_content">
	<div class="top_left"></div>
	<div class="top_center"></div>
	<div class="top_right"></div>
	<div class="center_left"></div>
	<div class="center_center">
		<div class="logo"></div>
		<div class="title">网站后台管理系统</div>
		<table class="loginTable">
			<tr><td>用户名：</td><td><input type="text" id="userName" name="userName" class="text" /></td></tr>
			<tr><td>密&nbsp;&nbsp;码：</td><td><input type="password" id="passWord" name="passWord" class="text" /></td></tr>
			<tr>
			</tr>
		</table>
		<div class="loginBtn">
			<button  id="loginBtn" type="button">登录</button>
			<button  id="clearBtn" type="reset">清空</button>
		</div>	
		<div id="errorMessages"></div>
	</div>
	<div class="center_right"></div>
	<div class="bottom_left"></div>
	<div class="bottom_center"></div>
	<div class="bottom_right"></div>
</div>
</form>
<script>
$(function(){
	$('#loginBtn').click(function(){
		var userName = $('#userName').val();
		var passWord = $('#passWord').val();
		if(userName.length==0 && passWord.length==0){			
			$('#errorMessages').html("请输入用户名与密码！");
			return false;
		}
		if(userName.length==0){
			$('#errorMessages').html("请输入用户名！");
			return false;
		}
		if(passWord.length==0){
			$('#errorMessages').html("请输入密码！");
			return false;
		}
		$.post("login", {"userName":userName,"passWord":passWord},
		   function(msg){
			if(msg.toString()=="true") {
				window.location.href = 'main';
			}else{
				$('#errorMessages').html(msg);
			}
		   }, "json");
	});
});
</script>
</body>
</html>
