<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/views/taglibs.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body>
<div id="toolbar" >	
	<form id="list" action="list">
	<div id="search" class="easyui-panel" title="查询条件" data-options="fit:true,collapsible:true,border:0"> 
	  <input type="hidden" id="menuId" name="menuId">
	  <table class="searchTable">
		<tr>
			<td>菜单名：</td>
			<td><input type="text" id="menuName" name="menuName"></td>
			<td>菜单级别：</td>
			<td><input type="text" id="menuLevel" name="menuLevel"></td>
			<td><button id="queryBtn" type="button" class="button">查询</button></td>
		</tr>
		<tr>
			<td>状态：</td>
			<td><input type="text" id="menuName" name="menuName"></td>
			<td>修改时间：</td>
			<td><input id="updateTimeStart" name="updateTimeStart" type="date" class="easyui-datebox"></input>
				至 <input id="updateTimeEnd" name="updateTimeEnd" type="date" class="easyui-datebox"/>
			</td>  
			<td><button id="clearBtn" type="button" class="button">清空</button></td>
		</tr>
				
	   </table>
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
</div>
<table id="grid" data-options="fit:true,border:false"></table>

</body>
<script type="text/javascript">
$(function() {	
	 refleshTree=true
     $('#grid').datagrid({   
 	    url:'list', 
 	    pageSize :10,
 		pageList : [10, 20, 30, 40, 50, 100, 200, 300, 400, 500, 1000 ],
 		striped : true,
 		rownumbers : true,
 		pagination : true,
 		toolbar : '#toolbar',
 	    columns : [[ {width : '50', field : 'ck',checkbox:true},
 	                 {width : '100',title : '菜单名',field : 'menuName'},
 	                 {width : '100',title : '菜单URL',field : 'menuUrl'},
 	                 {width : '100',title : '菜单级别',field : 'menuOrder'},
 	                 {width : '100',title : '排序',field : 'menuLevel'},
 					 {width : '100',title : '状态',field : 'menuStatus',formatter:function(v,r){return JSON.parse('${statusMap}')[v]}},
 					 {width : '200',title : '修改时间',field : 'updateTime'}]
 	    		]
 	}); 
    //加载下拉树
    loadTree();   
});
function loadTree(){
	 $('.panel.datagrid.easyui-fluid').css({position:'absolute',left:'200px'});  
	 $('body').append('<div class="treeDiv"><ul id="navtree"></ul></div>');
	 $('.panel-tool').css('margin-right','200px');
	 $('.operate').css('margin-right','200px');
	 $('#navtree').tree({   
	    url:'tree',
	    onClick: function(node){
	    	$("#menuId").val(node.id);
      		$("#queryBtn").click();
		}
	});  
}

function showMenuAdd(){
	var menuPid = $("#menuId").val();
	if(menuPid==''){		
		$.messager.alert('提示:','请在左边节点树上选择父节点！'); 
		return
	}
	$.post("getMenu", {"menuId":menuPid},
	   function(msg){
		if(msg.menuLevel==2){			
			$.messager.alert('提示:','请不要选择第三级菜单！'); 
		}else{
			showAdd('/system/prg/menu/showAdd?menuPid='+menuPid,600,220);
		}
   }, "json");
}
</script>
</html>