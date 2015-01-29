<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/views/taglibs.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<style>
body{background:#fff;height:auto;}
</style>
<link href="${js}/photowall/photowall.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${js}/photowall/photowall.js"></script>
</head>
<body>
<div style="position:absolute;top:20px;left:20px;"><input id="file_upload" name="file_upload" type="file" /></div>
<div id="imgDiv"></div>
<script>
$(function(){
	var obj = {id:'imgDiv',	aImg:[]};
	$('#file_upload').omFileUpload({
	    action : 'uploadDo',
	    multi: true,
	    queueSizeLimit:12,
	    autoUpload: true,
	    swf : '${js}/operamasks/2.0/swf/om-fileupload.swf',
	    onComplete : function(event,ID,fileObj,response,data){
	    	var data = JSON.parse(fileObj);
	    	obj.aImg.push('${ctx}/static/img/'+data);
	    },
	    onAllComplete:function(data,event){
	    	photowall(obj);
	    }
    });
})
</script>
</body>
</html>