<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/views/taglibs.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<style>
.panel{width:100%}
</style>
</head>
<body>
<div id="toolbar">
<form id="list" action="list">
<div id="p" class="easyui-panel" title="查询条件" data-options="collapsible:true"> 
  <table class="searchTable">
	<tr>
		<td>系统登录名：</td>
		<td><input type="text" id="userName" name="userName"></td>
		<td>真实姓名：</td>
		<td><input type="text" id="realName" name="realName"></td>
		<td><button id="queryBtn" type="button" class="button">查询</button></td>
		<td><button id="clearBtn" type="button" class="button">清空</button></td>
	</tr>
   </table>
</div>
</form>
<div class="operate">
	<div class="om-panel-header">用户管理列表</div>
	<div class="icon">
		<ul>
    		<li><a href="#" onclick="showAdd('/system/prg/user/showAdd',600,220);"><span class="menu1"></span>添加</a></li>
    		<li><a href="#" onclick="showEdit('/system/prg/user/showEdit','userId',600,220);"><span class="menu13"></span>修改</a></li>
    		<li><a href="#" onclick="removeRow('userId');"><span class="menu11"></span>删除</a></li>
    		<li><a href="#" onclick="initPwd();"><span class="menu9"></span>初始化密码</a></li>
		</ul>
	</div>
</div>
</div>
<table id="grid" data-options="fit:true,border:false"></table>
</body>
<script type="text/javascript">
$(function() {
	$('#grid').datagrid({   
	    url:'list', 
	    pageSize :10,
		pageList : [10, 20, 30, 40, 50, 100, 200, 300, 400, 500, 1000 ],
		striped : true,
		rownumbers : true,
		pagination : true,
		singleSelect : true,
		toolbar : '#toolbar',
	    columns : [[ {width : '150',title : '系统登录名',field : 'userName'},
	                 {width : '150',title : '真实姓名',field : 'realName'},
	                 {width : '100',title : '状态',field : 'userStatus'},
	                 {width : '150',title : '排序',field : 'userOrder'},
					 {width : '150',title : '创建时间',field : 'createTime'}]
	    		]
	}); 
	$('#p').panel({   
	    onCollapse:function(){   
	    	$('#grid').datagrid('resize');
	    },
	    onExpand:function(){
	    	$('#grid').datagrid('resize');
	    }
	});
	$(window).resize(function () {
        $('#grid').datagrid('resize');
    });   
});
</script>
</html>