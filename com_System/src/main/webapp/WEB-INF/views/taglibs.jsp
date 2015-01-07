<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set  var="ctx"  value="<%= request.getContextPath() %>" />
<c:set  var="js"   value="${ctx}/static/js"/>
<c:set  var="css"  value="${ctx}/static/css/css${v}"/>
<script type="text/javascript" src="${js}/operamasks/2.0/js/jquery.min.js"></script>
<script type="text/javascript" src="${js}/operamasks/2.0/js/operamasks-ui.min.js"></script>
<script type="text/javascript" src="${js}/artDialog/jquery.artDialog.js?skin=idialog"></script>
<script type="text/javascript" src="${js}/artDialog/plugins/iframeTools.js"></script>

<link href="${css}/operamasks.css" rel="stylesheet" type="text/css" />
<link href="${css}/init.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${js}/common.js"></script>
<script>
$(function(){ctx= <c:url value = "/" />})
</script>

