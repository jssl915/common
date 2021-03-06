﻿<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ include file="taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="${css}/main.css" rel="stylesheet" type="text/css"/>
<title>主页</title>
<script>
var _tabElement,_ifh;
$(function() {
	var down = '<c:url value="/static/images/accordion-collapse.gif"/>';
	var up = '<c:url value="/static/images/accordion-expand.gif"/>';
	$('body').omBorderLayout({
		panels: [{id: "north-panel",header:false,region: "north",height:'75px'}, 
		         {id: "south-panel",header:false,region: "south"}, 
		         {id: "center-panel",header:false,region: "center"}, 
		         {id: "west-panel",header:true,region:"west",width:180,collapsible:true,
					title: "&nbsp;<img style='cursor: pointer;' src='"+up+"' onclick='change(1)'><img style='cursor:pointer;' src='"+down+"' onclick='change(0)'> "
				}]
	});
	loadWestTree();
	_ifh = $('#center-panel').height() - $("#tabUL").height()-14;
	_tabElement =$('#make-tab').omTabs({height:'fit'});
	$('#indexF').height(_ifh);
 	$("#west-panel").omScrollbar({thick: 10});
 	$('.om-tabs-panels').css('overflow','hidden');
 });

function showOmtabs(){document.location.reload();}

function loadWestTree(){
	$.post(ctx+"index/tree",
	   function(data){
			var treeHtmlArray = [];
			var oneMenus = data[0].children;	
			treeHtmlArray.push('<div class="aside">');
			for(var i=0;i<oneMenus.length;i++){
				var oneMenu = oneMenus[i];
				treeHtmlArray.push('<ul class="nav"><li><a href="#" class="open"><span class="menu_icon"></span>'+oneMenu.text+'<span class="menu_icon_r"></span></a><ul class="zi_menu_bg" style="display: block;">');
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

function openTab(menuId){
	$.post(ctx+"/system/prg/menu/getMenu", {"menuId":menuId},
	   function(msg){
			var url = ctx+msg.menuUrl;
			var text = msg.menuName;
			var tabId = _tabElement.omTabs('getAlter', 'menu'+menuId);
			if(tabId){
				_tabElement.omTabs('activate', tabId);
			} else{
				_tabElement.omTabs('add',{
		            title : text, 
		            tabId :'menu'+menuId,
		            content : "<iframe id='"+menuId+"' frameBorder='no' width='100%' src='"+url+"' height='"+_ifh+"'></iframe>",
		            closable : true
		        });
			}
			$("iframe:eq(" + tabId + ")").attr("src", url);
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
 function changePasWord(){
	$.dialog.open(ctx+'/index/password', {
		lock: true,
		width:600,
		height:220
	});
 }
</script>
</head>
<body id="main">
<div id="north-panel">
	<div class="logo2">网站后台管理系统</div>
	<div class="rightbj"><img src="${img}/top_rightbj.png"/></div>
	<div class="userinfo">管理员：<span><shiro:principal></shiro:principal></span> 您好,欢迎您登录使用</div>
	<div class ="top_right">
		<a href="#" onclick="showOmtabs()">返回主页</a>&nbsp;｜&nbsp;
		<a href="#" onclick="changePasWord()">修改密码</a>&nbsp;|&nbsp;
		<a href="<c:url value='/system/logout'/>">系统注销</a>
	</div>
</div>
<div id="center-panel">
 	<div id="make-tab">
	    <ul id="tabUL"><li><a href="#indexP"><img class="homeicon" src="${img}/home.gif"/><span>&nbsp;我的主页</span></a></li></ul>
	   	<div id="indexP"><iframe id="indexF" width='100%' style="border:0;background:url(${img}/bj.jpg) 50px 0;"></iframe></div> 
 	</div> 
</div>

<div id="west-panel"><div id="menuTree"></div></div>

<div id="south-panel"><div id="footer">Copyright &copy; 2014.</div></div>
</body>
</html>