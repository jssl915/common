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
	<div class="treeDiv" style="width:218px;">
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
  	$(".treeDiv").omScrollbar({thick: 10});
    $("#navtree").omTree({
    	dataSource :${resourceTree},
        showCheckbox: true
    });
    var sRoleMenuJson = ${SRoleMenuJson};
    for(var i=0;i<sRoleMenuJson.length;i++){
    	node=$('#navtree').omTree('findNode',"id",sRoleMenuJson[i].menuId);
    	var childrens=node.children;
       	if(!childrens||childrens.length==0){
          	$('#navtree').omTree('check',node);
        }
    }
});
function doSubmit(){
	var nodes=$('#navtree').omTree('getChecked',true,true);
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