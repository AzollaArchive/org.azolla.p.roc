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
    <title><c:if test="${sidebar_title!='Index'}">${sidebar_title} - </c:if>${applicationScope.CONFIG_MAP["ROC_CONFIG_KEY_ROCTITLE"]}</title>
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
<jsp:include page="/WEB-INF/ref/nav.jsp" />
<div id="roc-i-content">
    <div class="container">
        <div class="row">
            <main class="col-md-9">
                <article class="roc-c-article-post">
                    <c:forEach var="post" items="${postList}">
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
                            <c:forEach var="post" items="${postList}">
                                <li><a href="#${post.urlTitle}">${post.title}</a></li>
                            </c:forEach>
                        </ul>
                    </div>
                </div>
            </aside>
        </div>
    </div>
</div>

<%@ include file="/WEB-INF/ref/footer.jsp" %>
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="/3th/jquery/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="/3th/bootstrap/js/bootstrap.min.js"></script>
<script src="/3th/bootcss/js/docs.min.js"></script>
</body>
</html>