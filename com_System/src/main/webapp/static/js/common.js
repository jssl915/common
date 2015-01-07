var refleshTree=false;menuTree=false;
$(function(){
	$('#queryBtn').click(function(){
		var form = document.forms[0];
		var url= form.action;
		$("#grid").omGrid("setData", url+'?'+getFormData(form));
	});
	$('#clearBtn').click(function(){
		var form = document.forms[0];
		clearForm(form);
	});
	
	$('.search .om-panel-header').click(function(){
		var oDiv = $('.searchDiv');
		if(oDiv.length!=1){return;}
		var oSpan = $(this).find('span');
		if(oDiv.is(":hidden")){
			oDiv.show();oSpan.attr('class','up');
		}else{
			oDiv.hide();oSpan.attr('class','down');
		}
		resizeHeight();
	});
	
	$('#createTimeStart').omCalendar({readOnly : true});
	$('#createTimeEnd').omCalendar({readOnly : true});
	$('#updateTimeStart').omCalendar({readOnly : true});
	$('#updateTimeEnd').omCalendar({readOnly : true});
	
});

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
	}
}

function getFormData(form) {
	var paramStr="";
	//文本框
	$("#"+form.id+" :input[type='text']").each(function() {
		if($(this).attr("name")!=undefined&&$(this).val()!=''&& $(this).val()!=null){
			paramStr+= "&"+$(this).attr("name")+"="+ $(this).val();
		}
	});
	// 隐藏框
	$("#"+form.id+" :input[type='hidden']").each(function() {
		if($(this).attr("name")!=undefined&&$(this).val()!=''&& $(this).val()!=null){
			paramStr+= "&"+$(this).attr("name")+"="+ $(this).val();
		}
	});
	// 日历控件
	$("#" + form.id + " :input[type='date']").each(function() {
		if($(this).attr("name")!=undefined&&$(this).val()!=''&& $(this).val()!=null){
			paramStr += "&" + $(this).attr("name") + "=" + $(this).val();
		}
	});
	//combo控件
	$("#" + form.id + " :input[type='combo']").each(function() {
		if($(this).attr("name")!=undefined&&$(this).val()!=''&& $(this).val()!=null){
			paramStr += "&" + $(this).attr("name") + "=" + $(this).omCombo("value");
		}
	});
	return encodeURI(paramStr);
}

function resizeHeight(){
    var contentHeight = $(window).height() - $('#header').height() - $('#footer').height()-2;
    var tableHeight = contentHeight-$('.search').height()-$('.operate').height();
    var bDivHeight = $('.titleDiv').height()+$('.hDiv.om-state-default').height()+$('.pDiv.om-state-default').height(); 
 	$('.om-grid.om-widget.om-widget-content:last').css("height",tableHeight);
 	$('.bDiv').css("height",tableHeight-bDivHeight);
}

function showTip() {
	$('#grid').omGrid('reload');
	refleshTree&&($("#navtree").omTree('refresh'));
	menuTree&&(window.parent.loadWestTree());
	$.omMessageTip.show({
		type:"success",
		title : "操作成功",
		content : "成功",
		timeout : 1500
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
	var selections = $('#grid').omGrid('getSelections', true);
	if (selections.length != 1) {
		$.omMessageBox.alert({content : '只能选择一行记录'});
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
	var selections = $('#grid').omGrid('getSelections', true);
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
     		   $.post('delete',{"ids":ids.toString()}, function(msg) {
     				if (msg.result == "success") {
     					refleshTree&&($("#navtree").omTree('refresh'));
     					menuTree&&(window.parent.loadWestTree());
     					$('#grid').omGrid('reload');
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

function refreshGrid(id) {
	$('#'+id).omGrid('reload');// 刷新当前页数据
	$.omMessageTip.show({
		type:"success",
		title : "操作成功",
		content :"操作成功",
		timeout : 1500
	});
}

//显示校验信息的容器，本示例使用<span class="errorMsg" />做为容器，建议使用容器来避免和其它组件的dom元素交叉的问题
//比如使用omCombo的时候如果不使用容器将会导致样式错乱，根本原因是combo是在input外面包裹一层span再添加样式组成，而校验
//框架默认会再input后面加label标签从而导致combo组件样式混乱。
function errorPlacement(error, element) { 
	if(error.html()){
	    $(element).parents().map(function(){
	        if(this.tagName.toLowerCase()=='td'){ 
	        	if($(this).children().eq(1).length==0){
	        		$(this).append('<span class="errorMsg"></span>');
	        	}
	        	var attentionElement = $(this).children().eq(1);
	            attentionElement.html(error);
	            attentionElement.css('display','none'); //覆盖默认显示方法，先隐藏消息，等鼠标移动上去再显示
	            attentionElement.prev().addClass("error-border");
	            attentionElement.prev().children("input").addClass("x-form-invalid");
	            if(attentionElement.prev().children().length <= 0){
	                attentionElement.prev().addClass("x-form-invalid");
	            }
	        }
	    });
	}
	//给输入框绑定鼠标经过事件，鼠标移动过去才显示校验信息
    $('.errorMsg').prev().bind('mouseover',function(e){
        //要有错误才显示
        if($(this).next().html().length > 0 && $(this).next().find("label").html().length > 0){
        	$(this).next().css('display','inline').css({'top':e.pageY+10 , 'left':e.pageX-10});
        	if($(this).next().find("label").css('display') == 'none'){
        	    $(this).next().hide();
        	}
        }
    }).bind('mouseout',function(){
        $(this).next().css('display','none');
    });
}
//控制错误显示隐藏的方法，当自定义了显示方式之后一定要在这里做处理。
function showErrors(errorMap, errorList, validObj) {
	if(errorList && errorList.length > 0){
	    $.each(errorList,function(index,obj){
	        var msg = this.message;
	        $(obj.element).parents().map(function(){
	            if(this.tagName.toLowerCase()=='td'){
	                var attentionElement =  $(this).children().eq(1);
	                attentionElement.show();
	                attentionElement.html(msg);
	            }
	        });
	    });
	}else{
	    $(validObj.currentElements).parents().map(function(){
	        if(this.tagName.toLowerCase()=='td'){
	            $(this).children().eq(0).removeClass("error-border");
	        }else{
	            $(this).removeClass("error-border");
	        }
	        $(this).children().eq(0).removeClass("x-form-invalid");
	    });
	}
	validObj.defaultShowErrors();
}

$.validator.messages={
    required: "此字段不能为空",
	remote: "此字段已经存在",
	email: "邮箱非法",
	url: "网址非法",
	date: "日期非法",
	number: "请输入合法的数字",
	digits: "只能输入整数",
	equalTo: "输入不一致",
	accept: "请输入拥有合法后缀名的字符串",
	maxlength: jQuery.validator.format("请输入一个长度最多是 {0} 的字符串"),
	minlength: jQuery.validator.format("请输入一个长度最少是 {0} 的字符串"),
	rangelength: jQuery.validator.format("请输入一个长度介于 {0} 和 {1} 之间的字符串"),
	range: jQuery.validator.format("请输入一个介于 {0} 和 {1} 之间的值"),
	max: jQuery.validator.format("最大为 {0}"),
	min: jQuery.validator.format("最小为{0}"),
	maxCnLength:jQuery.validator.format("请输入一个长度最多是 {0} 的字符串,一个汉字3个字符")
}