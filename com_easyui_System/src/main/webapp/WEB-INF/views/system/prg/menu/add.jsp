<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/views/taglibs.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body>
<form id="form1" action="insert">
<div class="dialogPage">
	<div class="om-panel-header">新增</div>
	<div class="editDiv">
		<input type="hidden" id="menuPid" name="menuPid" value="${sMenuP.menuId}">
		<input type="hidden" id="menuLevel" name="menuLevel" value="${sMenuP.menuLevel}">
		<table class="editTable">
		<tr>
			<td>父菜单：</td>
			<td>${sMenuP.menuName}</td>
		</tr>
		<tr>
			<td><span class="required">*</span>菜单名字：</td>
			<td><input type="text" id="menuName" name="menuName"></td>
			<td>菜单顺序：</td>
			<td><input type="text" id="menuOrder" name="menuOrder"></td>
			
		</tr>
		<tr>
			<td>菜单URL：</td>
			<td colspan="3"><input type="text" id="menuUrl" name="menuUrl" style="width:435px;"></td>
		</tr>
	   </table>
	   <div class="editBtn">
			<button id ="btnSubmit" type="button" class="button">&nbsp;保存&nbsp;</button>
			<button type="button" class="button" onclick="javascript:art.dialog.close();">&nbsp;关闭&nbsp;</button>
		</div>
	</div>
</div>
</form>
</body>
<script type="text/javascript">
$(function(){
	$('#menuName').validatebox({required:true}); 
	$('#btnSubmit').click(function(){
		if($('#form1').form('validate')){
			$('#form1').submit();
		}
	})
});
</script>
</html>