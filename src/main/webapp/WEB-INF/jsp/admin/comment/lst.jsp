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
    <%@ include file="/WEB-INF/ref/admin/head.jsp"%>
</head>
<body>
<jsp:include page="/WEB-INF/ref/admin/nav.jsp" />
<div id="roc-i-content">
    <div class="container">
        <div class="row">
            <%@ include file="/WEB-INF/ref/admin/aside.jsp"%>
            <main class="col-md-9">
                <article class="roc-c-article-post">
                    <div class="panel panel-default">
                        <!-- Default panel contents -->
                        <div class="panel-heading">${jsp_title}</div>
                        <!-- Table -->
                        <div class="table-responsive">
                            <table class="table table-bordered table-hover table-condensed" style="border-bottom: solid 1px #ddd">
                                <tr>
                                    <th>#</th>
                                    <th>Username</th>
                                    <th>Email</th>
                                    <th>Content</th>
                                    <th>Post Title</th>
                                    <th>AddDate</th>
                                    <th>ModDate</th>
                                    <th>RmvDate</th>
                                    <th>Visible</th>
                                    <th>Operable</th>
                                    <th>Deleted</th>
                                    <th>Operation</th>
                                </tr>
                                <c:forEach var="commentVo" items="${commentVoList}">
                                    <tr>
                                        <td>${commentVo.id}</td>
                                        <td>${commentVo.username}</td>
                                        <td>${commentVo.email}</td>
                                        <td>${commentVo.content}</td>
                                        <td>${commentVo.postVo.title}</td>
                                        <td><fmt:formatDate value="${commentVo.addDate}" type="both" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                                        <td><fmt:formatDate value="${commentVo.modDate}" type="both" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                                        <td><fmt:formatDate value="${commentVo.rmvDate}" type="both" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                                        <td><input type="checkbox" disabled <c:if test="${commentVo.visible == 1}">checked="checked"</c:if> /></td>
                                        <td><input type="checkbox" disabled <c:if test="${commentVo.operable == 1}">checked="checked"</c:if> /></td>
                                        <td><input type="checkbox" disabled <c:if test="${commentVo.deleted == 1}">checked="checked"</c:if> /></td>
                                        <td><a href="/admin/comment/rmv/${commentVo.id}" />RMV</td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </div>
                        <nav>
                            <ul class="pager">
                                <li><a href="/${current_request}/${current_page-1}">Previous</a></li>
                                <li><a href="/${current_request}/${current_page+1}">Next</a></li>
                            </ul>
                        </nav>
                    </div>
                </article>
            </main>
        </div>
    </div>
</div>
<%@ include file="/WEB-INF/ref/footer.jsp" %>
</body>
</html>
