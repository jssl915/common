<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/views/taglibs.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body>
<form id="form1" action="update">
<div class="dialogPage">
	<div class="om-panel-header">编辑</div>
	<div class="editDiv">
		<input type="hidden" id="menuId" name="menuId" value="${sMenu.menuId}">
		<table class="editTable">
		<tr>
			<td>父菜单：</td>
			<td>${sMenuP.menuName}</td>
			<td><span class="required">*</span>菜单名字：</td>
			<td><input type="text" id="menuName" name="menuName" value="${sMenu.menuName}"></td>
		</tr>
		<tr>
			<td>菜单顺序：</td>
			<td><input type="text" id="menuOrder" name="menuOrder" value="${sMenu.menuOrder}"></td>
			<td>菜单状态：</td>
			<td>
			<select id="menuStatus" name="menuStatus">
				<c:forEach items="${statusMap}" var="v">
					<option value="${v.key}" <c:if test="${v.key==sMenu.menuStatus}">selected="selected"</c:if>>${v.value}</option>
				</c:forEach>
			</select>
			</td>
		</tr>
		<tr>
			<td>菜单URL：</td>
			<td colspan="3"><input type="text" id="menuUrl" name="menuUrl" style="width:435px;" value="${sMenu.menuUrl}"></td>
		</tr>
	   </table>
	   <div class="editBtn">
			<button type="submit">&nbsp;保存&nbsp;</button>
			<button type="button" onclick="javascript:art.dialog.close();">&nbsp;关闭&nbsp;</button>
		</div>
	</div>
</div>
</form>
</body>
<script type="text/javascript">
$(function(){
    $('#menuOrder').omNumberField({
        allowDecimals : false,
        allowNegative : false
    });
   	$("#form1").validate({
   	    rules:{
   	    	menuName:{required:true,maxlength:32,remote:{
	    		url:"checkMenuName",
    			data:{menuName:function(){return $("#menuName").val()},menuId:'${sMenu.menuId}'}
        	}}
   	    },
   	    errorPlacement:function(error, element) {errorPlacement(error,element);}, 
   	    showErrors: function(errorMap, errorList){showErrors(errorMap,errorList,this);}
   	});	
});
</script>
</html>