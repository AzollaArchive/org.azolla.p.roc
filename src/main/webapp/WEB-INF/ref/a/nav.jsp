<%@ page language="java" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!-- Fixed navbar -->
<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container">
        <div class="row">
            <div class="col-xs-12">
                <div class="navbar-header">
                    <!-- Brand and toggle get grouped for better mobile display -->
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                            aria-expanded="false" aria-controls="navbar">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="/">${applicationScope.CONFIG_MAP["ROC_TITLE"]}</a>
                </div>
                <!-- Collect the nav links, forms, and other content for toggling -->
                <div id="navbar" class="navbar-collapse collapse">
                    <form class="navbar-form navbar-right" role="search">
                        <div class="input-group">
                            <!-- Single button -->
                            <button type="button" class="btn btn-default"><sec:authentication property="principal.username"/></button>
                            <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown"
                                    aria-expanded="false">
                                <span class="caret"></span>
                                <span class="sr-only">Toggle Dropdown</span>
                            </button>
                            <ul class="dropdown-menu" role="menu">
                                <li><a href="/a/u/m">修改密码</a></li>
                                <li class="divider"></li>
                                <li><a href="/j_spring_security_logout">Logout</a></li>
                            </ul>
                        </div>
                    </form>
                </div>
                <!--/.nav-collapse -->
            </div>
        </div>
    </div>
</nav>