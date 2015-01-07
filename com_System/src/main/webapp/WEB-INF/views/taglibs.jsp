<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set  var="ctx"  value="<%= request.getContextPath() %>" />
<c:set  var="js"   value="${ctx}/static/js"/>
<c:set  var="css"  value="${ctx}/static/css"/>
<c:set  var="img"  value="${ctx}/static/images"/>
<script type="text/javascript" src="<c:url value='/static/js/operamasks/2.0/js/jquery.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/static/js/operamasks/2.0/js/operamasks-ui.min.js'/>"></script>
<link rel="stylesheet" type="text/css" href="<c:url value='/static/js/operamasks/2.0/css/default/om-default.css'/>">
<script type="text/javascript" src="<c:url value='/static/js/artDialog/jquery.artDialog.js?skin=idialog' />"></script>
<script type="text/javascript" src="<c:url value='/static/js/artDialog/plugins/iframeTools.js' />"></script>
<link href="<c:url value="/static/css/init.css"/>" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<c:url value='/static/js/common.js'/>"></script>
<script>
$(function(){ctx= <c:url value = "/" />})
</script>

