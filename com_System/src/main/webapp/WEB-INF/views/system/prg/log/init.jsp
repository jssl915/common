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
			<td>访问人：</td>
			<td><input type="text" id="userName" name="userName"></td>
			<td>访问IP：</td>
			<td><input type="text" id="userIp" name="userIp"></td>
			<td>访问时间：</td>
			<td colspan="3"><input id="createTimeStart" name="createTimeStart" type="date" />
			至 <input id="createTimeEnd" name="createTimeEnd"  type="date"/></td>
			<td><button id="queryBtn" type="button" class="button">查询</button></td>
			<td><button id="clearBtn" type="button" class="button">清空</button></td>
	   </tr>
	   </table>
	</div>
</div>
</form>

<div class="operate">
	<div class="om-panel-header">系统日志管理列表</div>
	<div class="icon">
		<ul>
			<li><a href="#" onclick="exportExcel();"><span class="menu2"></span>导出</a></li>
		</ul>
	</div>
</div>

<table id="grid"></table>
</body>
<script type="text/javascript">
$(function() {
   $('#grid').omGrid({
       dataSource : 'list',
       limit:'${pageRows}',
       showIndex : false,
       singleSelect : false,
       colModel : [{header : '访问人',name:'userName',width:150,align:'center',sort:'clientSide'}, 
                   {header : '访问URL',name:'actionUrl',width:200,align:'center',sort:'clientSide'}, 
                   {header : '访问IP', name : 'userIp',width:150,align:'center',sort:'clientSide'}, 
                   {header : '访问时间', name : 'logTime',width:200,align:'center',sort:'clientSide'} 
                   ]
   });
   resizeHeight();
});
function exportExcel(){
	window.location.href = ctx + '/system/prg/log/exportExcel';
}

</script>
</html>