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
    <title>${applicationScope.CONFIG_MAP["ROC_CONFIG_KEY_ROCTITLE"]}<c:if test="${sidebar_title!='Index'}"> - ${sidebar_title}</c:if></title>
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
                <div id="navbar" class="navbar-collapse collapse">
                    <ul class="nav navbar-nav">
                        <c:forEach var="leftCategory" items="${applicationScope.LEFT_CATEGORY_LST }">
                            <c:choose>
                                <c:when test="${(leftCategory.subCategoryVoList)!= null && fn:length(leftCategory.subCategoryVoList) > 0}">
                                    <li class="dropdown">
                                        <a href="/${leftCategory.controllerName}/${leftCategory.urlName}" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
                                            ${leftCategory.displayName}
                                            <span class="caret"></span>
                                        </a>
                                        <ul class="dropdown-menu" role="menu">
                                            <c:forEach var="subLeftCategory" items="${leftCategory.subCategoryVoList}">
                                                <c:choose>
                                                    <c:when test="${subLeftCategory.group == 1}">
                                                        <li class="divider"></li>
                                                        <li class="dropdown-header">${subLeftCategory.displayName}</li>
                                                        <c:forEach var="lastLeftCategory" items="${subLeftCategory.subCategoryVoList}">
                                                            <li><a href="/${lastLeftCategory.controllerName}/${lastLeftCategory.urlName}">${lastLeftCategory.displayName}</a></li>
                                                        </c:forEach>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <li><a href="/${subLeftCategory.controllerName}/${subLeftCategory.urlName}">${subLeftCategory.displayName}</a></li>
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:forEach>
                                        </ul>
                                    </li>
                                </c:when>
                                <c:otherwise>
                                    <li><a href="/${leftCategory.controllerName}/${leftCategory.urlName}">${leftCategory.displayName}</a></li>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </ul>
                    <form class="navbar-form navbar-right" role="search">
                        <div class="form-group">
                            <input type="text" class="form-control" placeholder="有梦才有精彩！" required>
                        </div>
                        <button type="submit" class="btn btn-default">Search</button>
                    </form>
                    <ul class="nav navbar-nav navbar-right">
                        <c:forEach var="rightCategory" items="${applicationScope.RIGHT_CATEGORY_LST}">
                            <c:choose>
                                <c:when test="${(rightCategory.subCategoryVoList)!= null && fn:length(rightCategory.subCategoryVoList) > 0}">
                                    <li class="dropdown">
                                        <a href="/${rightCategory.controllerName}/${rightCategory.urlName}" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
                                                ${rightCategory.displayName}
                                            <span class="caret"></span>
                                        </a>
                                        <ul class="dropdown-menu" role="menu">
                                            <c:forEach var="subRightCategory" items="${rightCategory.subCategoryVoList}">
                                                <c:choose>
                                                    <c:when test="${subRightCategory.group == 1}">
                                                        <li class="divider"></li>
                                                        <li class="dropdown-header">${subRightCategory.displayName}</li>
                                                        <c:forEach var="lastRightCategory" items="${subRightCategory.subCategoryVoList}">
                                                            <li><a href="/${lastRightCategory.controllerName}/${lastRightCategory.urlName}">${lastRightCategory.displayName}</a></li>
                                                        </c:forEach>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <li><a href="/${subRightCategory.controllerName}/${subRightCategory.urlName}">${subRightCategory.displayName}</a></li>
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:forEach>
                                        </ul>
                                    </li>
                                </c:when>
                                <c:otherwise>
                                    <li><a href="/${rightCategory.controllerName}/${rightCategory.urlName}">${rightCategory.displayName}</a></li>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </ul>
                </div>
                <!--/.nav-collapse -->
            </div>
        </div>
    </div>
</nav>
<div id="roc-i-content">
    <div class="container">
        <div class="row">
            <main class="col-md-9">
                <article class="roc-c-article-post">
                    <c:forEach var="post" items="${postLst}">
                        <section class="bs-docs-section">
                            <h2 class="page-header" id="${post.urlTitle}">${post.title}
                                <small>
                                    <time><fmt:formatDate value="${post.addDate}" type="both" pattern="yyyy-MM-dd" /></time>
                                </small>
                                <a class="anchorjs-link" href="/post/${post.urlTitle}">
                                    <span class="anchorjs-icon"></span>
                                </a>
                            </h2>
                            ${post.content}
                            <p> <a href="/post/${post.urlTitle}">阅读详细 »</a></p>
                        </section>
                    </c:forEach>
                </article>
                <nav>
                    <ul class="pager">
                        <li><a href="/${current_request}/${current_page-1}">Previous</a></li>
                        <li><a href="/${current_request}/${current_page+1}">Next</a></li>
                    </ul>
                </nav>
            </main>
            <aside class="col-md-3 hidden-print hidden-xs hidden-sm">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">Tag</h3>
                    </div>
                    <div class="panel-body roc-c-div-tag">
                        <c:forEach var="tag" items="${applicationScope.TAG_LST}">
                            <a href="/tag/${tag.urlName}">${tag.displayName}</a>
                        </c:forEach>
                    </div>
                </div>
                <div class="panel panel-default bs-docs-sidebar affix-top">
                    <div class="panel-heading">
                        <h3 class="panel-title">${sidebar_title}</h3>
                    </div>
                    <div class="panel-body">
                        <ul class="nav bs-docs-sidenav">
                            <c:forEach var="post" items="${postLst}">
                                <li><a href="#${post.urlTitle}">${post.title}</a></li>
                            </c:forEach>
                        </ul>
                    </div>
                </div>
            </aside>
        </div>
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