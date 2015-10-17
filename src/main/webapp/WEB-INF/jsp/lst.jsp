<%@ page language="java" pageEncoding="utf-8" isELIgnored="false"%>
<%@ include file="/WEB-INF/ref/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<%@ include file="/WEB-INF/ref/head.jsp"%>
<body>
<jsp:include page="/WEB-INF/ref/nav.jsp" />
<div id="roc-i-content">
    <div class="container">
        <div class="row">
            <main class="col-md-9">
                <article class="roc-c-article-post">
                    <c:forEach var="post" items="${postList}">
                        <section class="bs-docs-section">
                            <h3 class="page-header" id="${post.urlTitle}">${post.title}
                                <small>
                                    <time><fmt:formatDate value="${post.addDate}" type="both" pattern="yyyy-MM-dd" /></time>
                                </small>
                                <a class="anchorjs-link" href="/post/${post.urlTitle}">
                                    <span class="anchorjs-icon"></span>
                                </a>
                            </h3>
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
                        <h3 class="panel-title"><c:if test="${jsp_title == null || jsp_title.trim() == ''}">${applicationScope.CONFIG_MAP["ROC_TITLE"]}</c:if>${jsp_title}</h3>
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
</body>
</html>
