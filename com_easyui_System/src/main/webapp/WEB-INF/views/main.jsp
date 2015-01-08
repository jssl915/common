<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>主页</title>
<script type="text/javascript">
	function showcontent(language){
		$('#content').html('Introduction to ' + language + ' language');
	}
	$(function(){
		showcontent('java');
	});
</script>
</head>
<body id="main">
<div class="easyui-layout" style="width:400px;height:200px;">
		<div region="west" split="true" title="Navigator" style="width:150px;">
			<p style="padding:5px;margin:0;">Select language:</p>
			<ul>
				<li><a href="javascript:void(0)" onclick="showcontent('java')">Java</a></li>
				<li><a href="javascript:void(0)" onclick="showcontent('cshape')">C#</a></li>
				<li><a href="javascript:void(0)" onclick="showcontent('vb')">VB</a></li>
				<li><a href="javascript:void(0)" onclick="showcontent('erlang')">Erlang</a></li>
			</ul>
		</div>
		<div id="content" region="center" title="Language" style="padding:5px;">
		</div>
	</div>
</body>
</html>