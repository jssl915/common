<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ include file="taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>主页</title>
<link href="${css}/main.css" rel="stylesheet" type="text/css"/>
</head>
<body class="easyui-layout">
<div id="north-panel" data-options="region:'north',border:false" style="height:75px;">
	<div class="logo2">网站后台管理系统</div>
	<div class="rightbj"><img src="${img}/top_rightbj.png"/></div>
	<div class="userinfo">管理员：<span><shiro:principal/></span> 您好,欢迎您登录使用</div>
	<div class ="top_right">
		<a href="#" onclick="showOmtabs()">返回主页</a>&nbsp;｜&nbsp;
		<a href="#" onclick="changePasWord()">修改密码</a>&nbsp;|&nbsp;
		<a href="<c:url value='/system/logout'/>">系统注销</a>
	</div>
</div>
<div data-options="region:'west'" title="&nbsp;<img style='cursor: pointer;' src='${img}/accordion-expand.gif' onclick='change(1)'>&nbsp;<img style='cursor:pointer;' src='${img}/accordion-collapse.gif' onclick='change(0)'>" style="width:182px;"><div id="menuTree"></div></div>

<div id="center-panel" data-options="region:'center'">
	<div id="eTab" class="easyui-tabs" data-options="border:false" style="width:100%;">
		<div title="我的主页">
			<iframe id="indexF" width='100%' style="border:0;background:url(${img}/bj.jpg) 50px 0;"></iframe>
		</div> 
	</div>
</div>
<div data-options="region:'south'" style="height:20px;"><div id="footer">Copyright &copy; 2014.</div></div>
<script>
var tabH="";
$(function(){
	loadWestTree();
	$('#eTab').height($('#center-panel').height());
	tabH = $('#eTab').height() - $(".tabs").height();
	$('#indexF').height(tabH);
	$('.panel-body').css('overflow','hidden')
});
function openTab(menuId){
	$.post(ctx+"/system/prg/menu/getMenu", {"menuId":menuId},
	   function(msg){
			var url = ctx+msg.menuUrl;
			var text = msg.menuName;
			var opts = {
	            title : text, 
	            tabId :'menu'+menuId,
	            content : "<iframe id='"+menuId+"' frameBorder='no' width='100%' height='"+tabH+"' src='"+url+"' ></iframe>",
	            closable : true
	        };
			var tabs = $('#eTab');
			tabs.tabs('exists', text)?tabs.tabs('select', text):tabs.tabs('add', opts);
	   }, "json");
}
function loadWestTree(){
	$.post(ctx+"index/tree",
	   function(data){
			var treeHtmlArray = [];
			var oneMenus = data[0].children;	
			treeHtmlArray.push('<div class="aside">');
			for(var i=0;i<oneMenus.length;i++){
				var oneMenu = oneMenus[i];
				treeHtmlArray.push('<ul class="nav"><li><a href="#" class="open"><span class="menu_icon"></span>'+oneMenu.text+'<span class="menu_icon_r"></span></a><ul>');
				var twoMenus = oneMenu.children;
				if(twoMenus!=undefined){
					for(var j=0;j<twoMenus.length;j++){
						var twoMenu = twoMenus[j];
						treeHtmlArray.push('<li><a href="#" onclick="openTab('+twoMenu.id+')"><span class="zi_menu_icon"></span>'+twoMenu.text+'</a><span class="menu_line"></span></li>');
					}
				}
				treeHtmlArray.push('</ul></li></ul>');
			}
			treeHtmlArray.push('</div>');
			$('#menuTree').html(treeHtmlArray.join(''));
			var allLi = $('.aside').find('li');
			$('li a').click(function(e) {
				var $a = $(this);
				if($a.hasClass('open')) {
					$a.removeClass('open').next().slideUp();
					$a.addClass('close');
					return
				}
				if($a.hasClass('close')) {
					$a.removeClass('close').next().slideUp();
					$a.addClass('open').next().slideDown();
					return
				}
				allLi.filter('.selected').removeClass('selected');
				$a.parent().addClass('selected');
			});
	   }, "json");
}
function change(status) {
	if(1 == status) {
		var aa = $("ul.nav").find('li a.close');
		aa.removeClass('close').next().slideUp();
		aa.addClass('open').next().slideDown();
 	} else {
 		var aa = $("ul.nav").find('li a.open');
	  	aa.removeClass('open').next().slideUp();
	  	aa.addClass('close');
 	}
 }
</script>
</body>
</html>