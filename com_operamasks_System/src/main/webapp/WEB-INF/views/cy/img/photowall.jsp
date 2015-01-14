<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/views/taglibs.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<c:set var="images"  value="${js}/photowall/images"/>
<link href="${js}/photowall/photowall.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${js}/photowall/photowall.js"></script>
<style>
body{background:#fff;height:auto;}
</style>
</head>
<body>
<div id="imgDiv"></div>
<script>
window.onload = function(){
	var obj = {
		id:'imgDiv',	
		width:160,//图片宽
		height:120,//图片高
		rowNum:4,//每行个数,默认为4
		aImg:['${images}/1.jpg','${images}/2.jpg','${images}/3.jpg','${images}/4.jpg','${images}/5.jpg',
		      '${images}/6.jpg','${images}/7.jpg','${images}/8.jpg','${images}/9.jpg','${images}/10.jpg']
	}
	photowall(obj);
}
</script>
</body>
</html>