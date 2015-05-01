<%@ page language="java" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>${applicationScope.CONFIG_MAP["ROC_CONFIG_KEY_ROCTITLE"]}</title>
    <meta name="keywords" content="${applicationScope.TAG4KEYWORD}"/>
    <meta name="Description" content="${applicationScope.CONFIG_MAP["ROC_CONFIG_KEY_ROCDESC"]}"/>
    <meta name="Author" content="${applicationScope.CONFIG_MAP["ROC_CONFIG_KEY_ROCEMAIL"]}"/>
    <link rel="shortcut icon" href="/img/favicon.png"/>
    <!-- Bootstrap -->
    <link href="/3th/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="/3th/bootcss/css/docs.min.css" rel="stylesheet">
    <link href="/css/roc.css" rel="stylesheet">
    <!--[if lt IE 9]>
    <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
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
        <form class="form-signin">
            <h2 class="form-signin-heading">Please sign in</h2>
            <label for="inputEmail" class="sr-only">Email address</label>
            <input type="email" id="inputEmail" class="form-control" placeholder="Email address" required="required" autofocus="autofocus">
            <label for="inputPassword" class="sr-only">Password</label>
            <input type="password" id="inputPassword" class="form-control" placeholder="Password" required="required">
            <div class="checkbox">
                <label>
                    <input type="checkbox" value="remember-me"> Remember me
                </label>
            </div>
            <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
        </form>
    </div>
</div>

<c:import url="/WEB-INF/html/footer.html" charEncoding="utf-8"/>
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="/3th/jquery/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="/3th/bootstrap/js/bootstrap.min.js"></script>
<script src="/3th/bootcss/js/docs.min.js"></script>
</body>
</html>