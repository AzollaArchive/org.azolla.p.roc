<%@ page language="java" pageEncoding="utf-8" isELIgnored="false"%>
<%@ include file="/WEB-INF/ref/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<%@ include file="/WEB-INF/ref/head.jsp"%>
<body>
<!-- Fixed navbar -->
<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container">
        <div class="row">
            <div class="col-xs-12">
                <div class="navbar-header">
                    <!-- Brand and toggle get grouped for better mobile display -->
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="/">${applicationScope.CONFIG_MAP["ROC_CONFIG_KEY_ROCTITLE"]}</a>
                </div>
                <!-- Collect the nav links, forms, and other content for toggling -->
                <!--/.nav-collapse -->
            </div>
        </div>
    </div>
</nav>
<div id="roc-i-content">
    <div class="container">
        <div class="jumbotron">
            <h1>500</h1>
            <p>maybe the server have some mistake!</p>
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