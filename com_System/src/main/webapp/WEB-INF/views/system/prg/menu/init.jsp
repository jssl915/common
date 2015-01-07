<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/views/taglibs.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body>
<div class="treeDiv"><ul id="navtree"></ul></div>
<div style="margin-left: 200px">
<form id="list" action="list">
<div class="search">
	<div class="om-panel-header">查询条件<span class="up"></span></div>
	<div class="searchDiv">
		<input type="hidden" id="menuId" name="menuId">
		<table class="searchTable">
		<tr>
			<td>菜单名：</td>
			<td><input type="text" id="menuName" name="menuName"></td>
			<td>菜单级别：</td>
			<td><input type="text" id="menuLevel" name="menuLevel"></td>
			<td><button id="queryBtn" type="button">查询</button></td>
		</tr>
		<tr>
			<td>状态：</td>
			<td><input type="combo" id="menuStatus" name="menuStatus"></td>
			<td>修改时间：</td>
			<td><input id="updateTimeStart" name="updateTimeStart"  type="date" />
			至 <input id="updateTimeEnd" name="updateTimeEnd"  type="date"/></td>
			<td><button id="clearBtn" type="button">清空</button></td>
		</tr>
	   </table>
	</div>
</div>
</form>
<div class="operate">
	<div class="om-panel-header">菜单管理列表</div>
	<div class="icon">
		<ul>
    		<li><a href="#" onclick="showMenuAdd();"><span class="menu1"></span>添加</a></li>
    		<li><a href="#" onclick="showEdit('/system/prg/menu/showEdit','menuId',600,220);"><span class="menu13"></span>修改</a></li>
    		<li><a href="#" onclick="removeRow('menuId');"><span class="menu11"></span>删除</a></li>
		</ul>
	</div>
</div>

<table id="grid"></table>
</div>
</body>
<script type="text/javascript">
$(function() {
	 refleshTree=true,menuTree=true;
	 var contentHeight = $(window).height() - $('#header').height() - $('#footer').height()-3;
   	 $(".treeDiv").css("height",contentHeight+"px");
   	 $(".treeDiv").omScrollbar({thick: 10});
   	 $("#navtree").omTree({
            dataSource:'tree',
            onSelect: function(nodedata){
           		 $("#menuId").val(nodedata.id);
           		 $("#queryBtn").click();
            }
        });
       $('#grid').omGrid({
           dataSource:'list',
           limit:'${pageRows}',
           showIndex : false,
           singleSelect : false,
           colModel : [{header : '菜单名',name:'menuName',width:100,align:'center',sort:'clientSide'}, 
                       {header : '菜单URL',name:'menuUrl',width:200,align:'center',sort:'clientSide'}, 
                       {header : '菜单级别',name:'menuLevel',width:80,align:'center',sort:'clientSide'}, 
                       {header : '排序',name:'menuOrder',width:60,align:'center',sort:'clientSide'}, 
                       {header : '状态', name : 'menuStatus',width:60,align:'center',sort:'clientSide',renderer:function(v) {return JSON.parse('${statusMap}')[v]}}, 
                       {header : '修改时间', name : 'updateTime',width:180,align:'center',sort:'clientSide'}]
       });
       resizeHeight();
       $('#menuLevel').omNumberField({
           allowDecimals : false,
           allowNegative : false
       });
       $('#menuStatus').omCombo({
           dataSource:JSON.parse('${statusCombo}'),
       	   width:220,
		   editable:false
       });
});

function showMenuAdd(){
	var menuPid = $("#menuId").val();
	if(menuPid==''){
		$.omMessageBox.alert({content:'请在左边节点树上选择父节点！' });
		return
	}
	showAdd('/system/prg/menu/showAdd?menuPid='+menuPid,600,220);
}
</script>
</html>