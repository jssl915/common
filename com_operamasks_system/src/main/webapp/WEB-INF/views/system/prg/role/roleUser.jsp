<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/views/taglibs.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<style>
.editDiv{height:420px;}
.roleTable{margin-top:10px;}
.roleTable td{width:100px;height:30px;text-align:center;}
</style>
</head>
<body>
<form id="form1" action="bindUser" >
<div class="dialogPage">
	<div class="om-panel-header">绑定用户</div>
	<div class="editDiv">
		<input type="hidden" name="roleId" value="${roleId}"/>
		<input type="hidden" name="userIds" id="userIds"/>
		<table class="roleTable">
		<tr>
	     <c:forEach items="${sUserList}" var="sUser" varStatus="s">
				<td>
				    <input type="checkbox" name="userId" value="${sUser.userId}" 
				   <c:forEach items="${sUserRoleList}" var="item">
				    <c:if test="${item.userId==sUser.userId}">checked="checked"</c:if> 
				  	</c:forEach>/>		
				  	${sUser.userName}		
				</td>
				<c:if test="${(s.index+1)%5==0}"></tr><tr></c:if>
		</c:forEach>		     
		</tr>				
		</table>		
	   <div class="editBtn" style="position:absolute;left:0;right:0;bottom:10px;margin:10px auto;">
			<button type="button" class="button" onclick="doSubmit()">保存</button>
			<button type="button" class="button" onclick="javascript:art.dialog.close();">关闭</button>
		</div>
  </div>
</div>
</form>
</body>
<script type="text/javascript">
function doSubmit(){
	var userIds="";
	$('input[name="userId"]:checked').each(function(){ 
		 userIds+=$(this).val()+','; 
	}); 
	$("#userIds").val(userIds);	
	$("#form1").submit();
}
</script>
</html>