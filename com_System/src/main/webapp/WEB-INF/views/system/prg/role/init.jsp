<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/views/taglibs.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body>
<form id="list" action="list">
<div class="search">
	<div class="om-panel-header">查询条件<span class="up"></span></div>
	<div class="searchDiv">
		<table class="searchTable">
		<tr>
			<td>角色名称：</td>
			<td><input type="text" id="roleName" name="roleName"></td>
			<td>修改时间：</td>
			<td><input id="updateTimeStart" name="updateTimeStart"  type="date" />
			至 <input id="updateTimeEnd" name="updateTimeEnd"  type="date"/></td>
			<td><button id="queryBtn" type="button">查询</button></td>
			<td><button id="clearBtn" type="button">清空</button></td>
		</tr>
	   </table>
	</div>
</div>
</form>
<div class="operate">
	<div class="om-panel-header">角色管理列表</div>
	<div class="icon">
		<ul>
    		<li><a href="#" onclick="showAdd('/system/prg/role/showAdd',600,240);"><span class="menu1"></span>添加</a></li>
    		<li><a href="#" onclick="showEdit('/system/prg/role/showEdit','roleId',600,270);"><span class="menu13"></span>修改</a></li>
    		<li><a href="#" onclick="removeRow('roleId');"><span class="menu11"></span>删除</a></li>
    		<li><a href="#" onclick="bindMenu()"><span class="menu4"></span>绑定权限</a></li>
    		<li><a href="#" onclick="bindUser()"><span class="menu4"></span>绑定用户</a></li>
		</ul>
	</div>
</div>

<table id="grid"></table>
</body>
<script type="text/javascript">
$(function() {
       $('#grid').omGrid({
           dataSource:'list',
           limit:'${pageRows}',
           showIndex : false,
           singleSelect : false,
           colModel : [{header : '角色名称',name:'roleName',width:150,align:'center',sort:'clientSide'}, 
                       {header : '角色描述', name : 'roleDesc',width:200,align:'center',sort:'clientSide'}, 
                       {header : '排序', name : 'roleOrder',width:200,align:'center',sort:'clientSide'}, 
                       {header : '创建时间', name : 'createTime',width:200,align:'center',sort:'clientSide'},
                       {header : '修改时间', name : 'updateTime',width:200,align:'center',sort:'clientSide'}]
       });
       resizeHeight();
});
function bindMenu(){
	menuTree = true;
	var selections = $('#grid').omGrid('getSelections', true);
	if (selections.length != 1) {
		$.omMessageBox.alert({content : '只能选择一行记录'});
		return false;
	}
	var id = selections[0].roleId;
	$.dialog.open(ctx+'system/prg/role/menuTree?roleId='+id, {
		lock: true,
		width:220,
		height:500
	});
}
function bindUser(){
	var selections = $('#grid').omGrid('getSelections', true);
	if (selections.length != 1) {
		$.omMessageBox.alert({content : '只能选择一行记录'});
		return false;
	}
	var id = selections[0].roleId;
	$.dialog.open(ctx+'system/prg/role/userDialog?roleId='+id, {
		lock: true,
		width:600,
		height:450
	});
}
</script>
</html>