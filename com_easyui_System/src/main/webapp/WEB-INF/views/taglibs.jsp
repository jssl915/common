<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set  var="ctx"  value="<%= request.getContextPath() %>" />
<c:set  var="js"   value="${ctx}/static/js"/>
<c:set  var="css"  value="${ctx}/static/css"/>
<c:set  var="img"  value="${ctx}/static/images"/>
<link href="${js}/easyui1.4.1/themes/default/easyui.css" rel="stylesheet" type="text/css" />
<link href="${js}/easyui1.4.1/themes/icon.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${js}/easyui1.4.1/jquery.min.js"></script>
<script type="text/javascript" src="${js}/easyui1.4.1/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${js}/artDialog/jquery.artDialog.js?skin=idialog"></script>
<script type="text/javascript" src="${js}/artDialog/plugins/iframeTools.js"></script>
<script>
$(function(){ctx= <c:url value = "/" />})
</script>

