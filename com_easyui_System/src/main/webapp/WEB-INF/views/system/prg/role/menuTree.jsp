<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/views/taglibs.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body>
<div class="dialogPage">
	<div class="om-panel-header">绑定菜单</div>
	<div class="treeDiv" style="width:218px;border:1px solid #86a3c4;">
		<ul id="navtree"></ul>
	</div>
	<form id="form1" action="bindMenu">
	<div class="editBtn" style="position:absolute;bottom:0px;margin:10px auto;">
		<input type="hidden" name="roleId" value="${roleId}" />
		<input type="hidden" name="menuIds" id="menuIds"/>
		<button type="button" class="button" onclick="doSubmit()">保存</button>
		<button type="button" class="button" onclick="javascript:art.dialog.close();">关闭</button>
	</div>
	</form>
</div>
<script>
$(function(){
  	$(".treeDiv").css("height","420px");  	
    $('#navtree').tree({   
	    url:ctx+'/system/prg/menu/tree',
	    checkbox:true,
	    onLoadSuccess:function(){
	    	var sRoleMenuJson = ${SRoleMenuJson};
	    	for(var i=0;i<sRoleMenuJson.length;i++){
	    		var n = $("#navtree").tree('find',sRoleMenuJson[i].menuId);
	            if(n){
	                $("#navtree").tree('check',n.target);
	            }
	    	}
	    }
	}); 
});
function doSubmit(){
	var nodes = $('#navtree').tree('getChecked');
	var menuIds=[];
    for(var i=0;i <nodes.length; i++){
    	menuIds.push(nodes[i].id);
	}
	$("#menuIds").val(menuIds.toString());
	$("#form1").submit();
}
</script>
</body>
</html>