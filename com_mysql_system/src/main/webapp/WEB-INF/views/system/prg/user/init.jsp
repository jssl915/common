<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/views/taglibs.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body>
<form id="list" action="list">
<div class="search">
	<div class="om-panel-header">查询条件<span class="up"></span></div>
	<div class="searchDiv">
		<table class="searchTable">
		<tr>
			<td>系统登录名：</td>
			<td><input type="text" id="userName" name="userName"></td>
			<td>真实姓名：</td>
			<td><input type="text" id="realName" name="realName"></td>
			<td><button id="queryBtn" type="button" class="button">查询</button></td>
		</tr>
		<tr>
			<td>创建时间：</td>
			<td><input id="createTimeStart" name="createTimeStart" type="date" />
			至 <input id="createTimeEnd" name="createTimeEnd"  type="date"/></td>
			<td>修改时间：</td>
			<td><input id="updateTimeStart" name="updateTimeStart"  type="date" />
			至 <input id="updateTimeEnd" name="updateTimeEnd"  type="date"/></td>
			<td><button id="clearBtn" type="button" class="button">清空</button></td>
		</tr>
	   </table>
	</div>
</div>
</form>

<div class="operate">
	<div class="om-panel-header">用户管理列表</div>
	<div class="icon">
		<ul>
    		<li><a href="#" onclick="showAdd('/system/prg/user/showAdd',600,220);"><span class="menu1"></span>添加</a></li>
    		<li><a href="#" onclick="showEdit('/system/prg/user/showEdit','userId',600,220);"><span class="menu13"></span>修改</a></li>
    		<li><a href="#" onclick="removeRow('userId');"><span class="menu11"></span>删除</a></li>
    		<li><a href="#" onclick="initPwd();"><span class="menu9"></span>初始化密码</a></li>
		</ul>
	</div>
</div>
<table id="grid"></table>
</body>
<script type="text/javascript">
$(function() {
       $('#grid').omGrid({
           dataSource : 'list',
           limit:'${pageRows}',
           showIndex : false,
           singleSelect : false,
           colModel : [{header : '系统登录名',name:'userName',width:150,align:'center',sort:'clientSide'}, 
                       {header : '真实姓名',name:'realName',width:150,align:'center',sort:'clientSide'}, 
                       {header : '状态', name : 'userStatus',width:80,align:'center',sort:'clientSide',renderer:function(v) {return JSON.parse('${statusMap}')[v]}},
                       {header : '排序', name : 'userOrder',width:80,align:'center',sort:'clientSide'}, 
                       {header : '创建时间', name : 'createTime',width:200,align:'center',sort:'clientSide'},
                       {header : '修改时间', name : 'updateTime',width:200,align:'center',sort:'clientSide'}]
       });
       resizeHeight();
});
function initPwd(){
	var selections = $('#grid').omGrid('getSelections', true);
	if (selections.length == 0) {
		$.omMessageBox.alert({content : '请至少选择一行记录'});
		return false;
	}
	$.omMessageBox.confirm({
        title:'确认操作',
        content:'确定对所选数据进行初始化密码(<span style="color:red">888888</span>)？',
        onClose:function(v){
     	   if(v){
     		   var ids = [];
     		   for(var i=0;i<selections.length;i++){
     			   ids.push(selections[i].userId);
     		   }
     		   $.post('initPwd',{"ids":ids.toString()}, function(msg) {
     				if (msg.result == "success") {
     					$('#grid').omGrid('reload');
     					$.omMessageTip.show({
     						type:"success",
     						title : "操作成功",
     						content : "初始化密码成功",
     						timeout : 1500
     					});
     				} else {
     					$.omMessageBox.alert({
     						type : 'error',
     						title : '错误',
     						content : msg.message
     					});
     				}
     			}, 'json');
     	   }
        }
    });
}

</script>
</html>