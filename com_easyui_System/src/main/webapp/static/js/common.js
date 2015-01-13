var refleshTree=false;
$(function(){
	$('#toolbar').css('padding',0);//除掉查询条件的padding
	$('.easyui-panel').panel({   //查询条件展开收缩方法
	    onCollapse:function(){   
	    	$('#grid').datagrid('resize');
	    },
	    onExpand:function(){
	    	$('#grid').datagrid('resize');
	    },
	    onBeforeExpand:function(){
	    	$('#search').height('auto');
	    }
	}); 
	$('#queryBtn').click(function(){
		$('#grid').datagrid('load',getFormData(document.forms[0]));
	});
	$('#clearBtn').click(function(){
		clearForm(document.forms[0]);
	});	
	setTimeout(function(){
		$('.pagination-info').css('float','left');
	},5);
});


function getFormData(form) {
	var obj = {};
	//文本框
	$("#"+form.id+" :input[type='text']").each(function() {
		obj[$(this).attr("name")] = $(this).val();
	});
	// 隐藏框
	$("#"+form.id+" :input[type='hidden']").each(function() {
		obj[$(this).attr("name")] = $(this).val();
	});
	$("#" + form.id + " :input[type='date']").each(function() {
		obj[$(this).attr("name")] = $(this).val();
	});
	return obj;
}
function clearForm(form) {
	for(var i=0;i<form.elements.length;i++){
		var field=form.elements[i];
		var fieldType=form.elements[i].type.toLowerCase();
		if(fieldType!="submit" && fieldType!="reset" && fieldType!="button" && fieldType!="hidden"){  
			if(fieldType=="radio" || fieldType=="checkbox"){
				field.checked=false;
			}else if(fieldType=="select"){
				field.selected=false;
			}else{
				field.value="";
			}
		}
		var dataName = form.elements[i].name;
		if(dataName=='createTimeStart'||dataName=='createTimeEnd'||dataName=='updateTimeStart'||dataName=='updateTimeEnd'){
			field.value="";
		}
		
	}
}

function showTip() {	
	$("#grid").datagrid('reload');
	refleshTree&&($("#navtree").tree('reload'));
	$.messager.show({ 
		title:'温馨提示:', 
		msg:'操作成功!', 
		timeout:1500, 
		showType:'slide'
	}); 
}

function showAdd(url,w,h){
	$.dialog.open(ctx+url, {
		lock: true,
		width:w,
		height:h
	});
}
function showEdit(url,updateId,w,h){
	var selections = $('#grid').datagrid('getSelections');
	if (selections.length != 1) {		
		$.messager.alert('提示:','只能选择一行记录!'); 
		return false;
	}
	var id = selections[0][updateId];
	$.dialog.open(ctx+url+'?'+updateId+'='+id, {
		lock: true,
		width:w,
		height:h
	});
}
function removeRow(deleteId){
	var selections = $('#grid').datagrid('getSelections');
	if (selections.length == 0) {
		$.messager.alert('提示:','请至少选择一行记录'); 
		return false;
	}
	$.messager.confirm('提示:','你确认要删除吗?',function(e){ 
		if(e){ 
		   var ids = [];
  		   for(var i=0;i<selections.length;i++){ids.push(selections[i][deleteId]);}
  		   $.post('delete',{"ids":ids.toString()}, function(msg) {
  				if (msg.result == "success") {
  					$("#grid").datagrid('reload');
  					refleshTree&&($("#navtree").tree('reload'));
  					$.messager.show({ 
  						title:'温馨提示:', 
  						msg:'删除数据成功!', 
  						timeout:1500, 
  						showType:'slide'
  					}); 
  				} else {
  					$.messager.alert('提示:',msg.message,'warning'); 
  				}
  			}, 'json');
		}
	}); 
}

function refreshGrid(id) {
	$('#'+id).datagrid('reload');// 刷新当前页数据
	$.messager.show({ 
		title:'温馨提示:', 
		msg:'操作成功!', 
		timeout:1500, 
		showType:'slide'
	}); 
}


$('input.easyui-validatebox').validatebox({
	tipOptions: {
	showEvent: 'mouseenter',
	hideEvent: 'mouseleave',
	showDelay: 0,
	hideDelay: 0,
	zIndex: '',
	onShow: function(){
		if(!$(this).hasClass('validatebox-invalid')){
			if ($(this).tooltip('options').prompt){
				$(this).tooltip('update', $(this).tooltip('options').prompt);
			 }else {
				$(this).tooltip('tip').hide();
			 }
		  }else {
			$(this).tooltip('tip').css({
				color: '#000',
				borderColor: '#CC9933',
				backgroundColor: '#FFFFCC'
			});
		  }
		},
	onHide: function(){
		if(!$(this).tooltip('options').prompt){
			$(this).tooltip('destroy');
		}
	  }
	}}).tooltip({
		position: 'right',
		content: function(){
			var opts = $(this).validatebox('options');
			return opts.prompt;
		},
		onShow: function(){
			$(this).tooltip('tip').css({
				color: '#000',
				borderColor: '#CC9933',
				backgroundColor: '#FFFFCC'
			});
		}
});
