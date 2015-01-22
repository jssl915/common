<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/views/taglibs.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<style>
a{text-decoration:underline;}
a:hover{text-decoration:underline;color:red;cursor:pointer}
#detailTitle{color:red}
</style>
</head>
<body>
<div style="width:100%;position:relative">
	<div style="width:50%;position:absolute;top:0;left:0;">
		<form id="list" action="list">
		<div class="search">
			<div class="om-panel-header">查询条件</div>
			<div class="searchDiv">
				<table class="searchTable">
				<tr>
					<td>字典名称：</td>
					<td><input type="text" id="dictName" name="dictName"></td>
					<td><button id="queryBtn" type="button" class="button">查询</button></td>
					<td><button id="clearBtn" type="button" class="button">清空</button></td>
				</tr>
			   </table>
			</div>
		</div>
		</form>
		
		<div class="operate">
			<div class="om-panel-header">字典管理列表</div>
			<div class="icon">
				<ul>
		    		<li><a href="#" onclick="showAdd('/system/prg/dict/showAdd',600,230);"><span class="menu1"></span>添加</a></li>
		    		<li><a href="#" onclick="showEdit('/system/prg/dict/showEdit','dictId',600,230);"><span class="menu13"></span>修改</a></li>
		    		<li><a href="#" onclick="removeRow('dictId');"><span class="menu11"></span>删除</a></li>
				</ul>
			</div>
		</div>
		<table id="grid"></table>
	</div>
	<div id="searchDetail" style="width:50%;position:absolute;top:0;left:50%;display:none">
		<form id="detailForm">
		<div class="search">
			<div class="om-panel-header">查询条件</div>
			<div class="searchDiv">
				<table class="searchTable">
				<tr>
					<td>字段名称：</td>
					<td><input type="text" id="detailName" name="detailName"></td>
					<td><button id="queryDetailBtn" type="button">查询</button></td>
					<td><button id="clearDetailBtn" type="button">清空</button></td>
				</tr>
			   </table>
			</div>
		</div>
		</form>
		
		<div class="operate">
			<div class="om-panel-header">字典[<span id="detailTitle"></span>]的明细管理列表</div>
			<div class="icon">
				<ul>
		    		<li><a href="#" onclick="showDetailAdd();"><span class="menu1"></span>添加</a></li>
		    		<li><a href="#" onclick="showDetailEdit();"><span class="menu13"></span>修改</a></li>
		    		<li><a href="#" onclick="removeDetailRow('detailId');"><span class="menu11"></span>删除</a></li>
				</ul>
			</div>
		</div>
		<table id="detailGrid"></table>
	</div>
</div>
</body>
<script type="text/javascript">
var dictId;
$(function() {
   $('#grid').omGrid({
       dataSource : 'list',
       limit:'${pageRows}',
       showIndex : false,
       singleSelect : false,
       colModel : [{header : '字典名称',name:'dictName',width:80,align:'center',sort:'clientSide'}, 
                   {header : '字典描述',name:'dictDesc',width:100,align:'center',sort:'clientSide'}, 
                   {header : '状态', name : 'dictStatus',width:60,align:'center',sort:'clientSide',renderer:function(v) {return JSON.parse('${statusMap}')[v]}}, 
                   {header : '修改时间', name : 'updateTime',width:135,align:'center',sort:'clientSide'},
                   {header : '明细', name : 'dictId', width : 80, align : 'center', renderer:function(value,rowData,rowIndex){ 
                   	return '<a onclick="showDetail('+value+',\''+rowData.dictName+'\')">查看明细</a>';
                 	}}
                   ]
   });
   resizeHeight();
});

function showDetail(id,dictName){
	dictId = id;
	$('#searchDetail').show();
	$('#detailTitle').html(dictName);
	$('#detailGrid').omGrid({
       	method : 'POST',
       	width:'100%',
       	limit:'${pageRows}',
        dataSource : ctx+'/system/prg/detail/list?dictId='+dictId,
        singleSelect : false,
        showIndex : false,
        colModel : [
                    {header : '字段名称', name : 'detailName', width : 100, align : 'center', sort:'clientSide'},
                    {header : '字段值', name : 'detailValue', width : 100, align : 'center', sort:'clientSide'},
                    {header : '字段描述', name : 'detailDesc', width : 100, align : 'center', sort:'clientSide'},
                    {header : '状态', name : 'detailStatus',width:60,align:'center',sort:'clientSide',renderer:function(v) {return JSON.parse('${statusMap}')[v]}}, 
                    {header : '修改时间', name : 'updateTime',width:135,align:'center',sort:'clientSide'}
                   ]
       }); 
	   resizeHeight();
	   $('#queryDetailBtn').click(function(){
		   var form = document.getElementById('detailForm');
		   console.log(form.id,getFormData(form));
			$("#detailGrid").omGrid("setData", ctx+'/system/prg/detail/list?dictId='+dictId+getFormData(form));
		});
	   $('#clearDetailBtn').click(function(){
			var form = document.getElementById('detailForm');
			clearForm(form);
		});
}
function showDetailAdd(){
	$.dialog.open(ctx+'/system/prg/detail/showAdd?dictId='+dictId, {
		lock: true,
		width:600,
		height:230
	});
} 
function showDetailEdit(){
	var selections = $('#detailGrid').omGrid('getSelections', true);
	if (selections.length != 1) {
		$.omMessageBox.alert({content : '只能选择一行记录'});
		return false;
	}
	var id = selections[0].detailId;
	$.dialog.open(ctx+'/system/prg/detail/showEdit?detailId='+id, {
		lock: true,
		width:600,
		height:260
	});
} 

function removeDetailRow(deleteId){
	var selections = $('#detailGrid').omGrid('getSelections', true);
	if (selections.length == 0) {
		$.omMessageBox.alert({content : '请至少选择一行记录'});
		return false;
	}
	$.omMessageBox.confirm({
        title:'确认操作',
        content:'确定删除所选数据？',
        onClose:function(v){
     	   if(v){
     		   var ids = [];
     		   for(var i=0;i<selections.length;i++){
     			   ids.push(selections[i][deleteId]);
     		   }
     		   $.post(ctx+'/system/prg/detail/delete',{"ids":ids.toString()}, function(msg) {
     				if (msg.result == "success") {
     					$('#detailGrid').omGrid('reload');
     					$.omMessageTip.show({
     						type:"success",
     						title : "操作成功",
     						content : "删除数据成功",
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