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
			<td><span class="required">*</span>字典名称：</td>
			<td><input type="text" id="dictName" name="dictName"></td>
		</tr>
		<tr>
			<td>字典描述：</td>
			<td colspan="3">
			<textarea name="dictDesc" id="dictDesc" cols="58" rows="3" maxlength="256"></textarea>
			</td>
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
   	$("#form1").validate({
   	    rules:{
   	    	dictName:{required:true,maxlength:32,remote:{
    			url:"checkDictName",
    			data:{dictName:function(){return $("#dictName").val();}}
        	}},
   	    },
   	    errorPlacement:function(error, element) {errorPlacement(error,element);}, 
   	    showErrors: function(errorMap, errorList){showErrors(errorMap,errorList,this);}
   	});	
});
</script>
</html>