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
		<input type="hidden" id="userId" name="userId" value="${sUser.userId}">
		<table class="editTable">
		<tr>
			<td><span class="required">*</span>登录名：</td>
			<td><input type="text" id="userName" name="userName" value="${sUser.userName}"></td>
			<td>真实姓名：</td>
			<td><input type="text" id="realName" name="realName" value="${sUser.realName}"></td>
		</tr>
		<tr>
			<td>排序：</td>
			<td><input type="text" id="userOrder" name="userOrder" value="${sUser.userOrder}"></td>
			<td>用户状态：</td>
			<td>
			<select id="userStatus" name="userStatus">
				<c:forEach items="${statusMap}" var="v">
					<option value="${v.key}" <c:if test="${v.key==sUser.userStatus}">selected="selected"</c:if>>${v.value}</option>
				</c:forEach>
			</select>
			</td>
		</tr>
	   </table>
	   <div class="editBtn">
			<button type="submit" class="button" >&nbsp;保存&nbsp;</button>
			<button type="button" class="button" onclick="javascript:art.dialog.close();">&nbsp;关闭&nbsp;</button>
		</div>
	</div>
</div>
</form>
</body>
<script type="text/javascript">
$(function(){
	$('#userOrder').omNumberField({
	    allowDecimals : false,
	    allowNegative : false
	});
	$("#form1").validate({
	    rules:{
	    	userName:{required:true,maxlength:32,remote:{
	    		url:"checkUserName",
    			data:{userName:function(){return $("#userName").val()},userId:'${sUser.userId}'}
        	}},
	        realName:{maxlength:32}
	    },
	    errorPlacement:function(error, element) {errorPlacement(error,element);}, 
	    showErrors: function(errorMap, errorList){showErrors(errorMap,errorList,this);}
	});	
})

</script>
</html>