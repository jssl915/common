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
		<input type="hidden" id="dictId" name="dictId" value="${dictId}">
		<table class="editTable">
		<tr>
			<td><span class="required">*</span>字段名称：</td>
			<td><input type="text" id="detailName" name="detailName"></td>
			<td>字段值：</td>
			<td><input type="text" id="detailValue" name="detailValue"></td>
		</tr>
		<tr>
			<td>字典描述：</td>
			<td colspan="3">
			<textarea name="detailDesc" id="detailDesc" cols="58" rows="3" maxlength="256"></textarea>
			</td>
		</tr>
	   </table>
	   <div class="editBtn">
			<button type="button" onclick="save()" class="button">&nbsp;保存&nbsp;</button>
			<button type="button" onclick="javascript:art.dialog.close();" class="button">&nbsp;关闭&nbsp;</button>
		</div>
	</div>
</div>
</form>
</body>
<script type="text/javascript">
$(function(){
   	$("#form1").validate({
   	    rules:{
   	    	detailName:{required:true,maxlength:32}
   	    },
   	    errorPlacement:function(error, element) {errorPlacement(error,element);}, 
   	    showErrors: function(errorMap, errorList){showErrors(errorMap,errorList,this);}
   	});	
});
function save(){
	 if($("#form1").validate().form()){
		 $.ajax({
				url: 'insert',
				data: $('#form1').serialize(),
				type: "POST",
				success: function(data)	{
					var win=art.dialog.open.origin; 
					win.refreshGrid('detailGrid');
					art.dialog.close();
				}
		  });
	 }
}
</script>
</html>