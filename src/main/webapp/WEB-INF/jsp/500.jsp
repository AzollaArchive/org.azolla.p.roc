<%@ page language="java" pageEncoding="utf-8" isELIgnored="false"%>
<%@ include file="/WEB-INF/ref/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<%@ include file="/WEB-INF/ref/head.jsp"%>
<body>
<jsp:include page="/WEB-INF/ref/nav_no_menu.jsp" />
<div id="roc-i-content">
    <div class="container">
        <div class="jumbotron">
            <h1>500</h1>
            <p>Maybe the server have some mistake!</p>
            <p><button class="btn btn-primary btn-lg" onclick="seeDetail()">See Detail</button></p>
            <p id="exception" hidden="hidden">${exception}</p>
        </div>
    </div>
</div>
<%@ include file="/WEB-INF/ref/footer.jsp" %>
<script>
    function seeDetail()
    {
        $("#exception").toggle();
    }
</script>
</body>
</html>