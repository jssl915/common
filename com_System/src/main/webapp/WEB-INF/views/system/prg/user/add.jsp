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
		<table class="editTable">
		<tr>
			<td><span class="required">*</span>登录名：</td>
			<td><input type="text" id="userName" name="userName"></td>
			<td><span class="required">*</span>初始密码：</td>
			<td><input type="text" id="userPwd" name="userPwd" value="${initPwd}" readonly="readonly"></td>
		</tr>
		<tr>
			<td>真实姓名：</td>
			<td><input type="text" id="realName" name="realName"></td>
			<td>排序：</td>
			<td><input type="text" id="userOrder" name="userOrder"></td>
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
	$('#userOrder').omNumberField({allowDecimals:false,allowNegative:false});
    $("#form1").validate({
        rules:{
        	userName:{required:true,maxlength:32,remote:{
    			url:"checkUserName",
    			data:{userName:function(){return $("#userName").val();}}
        	}},
        	userPwd:{required:true,minlength:6,maxlength:32},
            realName:{maxlength:32}
        },
        errorPlacement:function(error, element) {errorPlacement(error,element);}, 
        showErrors: function(errorMap, errorList){showErrors(errorMap,errorList,this);}
    });
})

</script>
</html>