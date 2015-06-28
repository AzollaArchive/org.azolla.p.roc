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
                                    <th>DisplayName</th>
                                    <th>UrlName</th>
                                    <th>ParentId</th>
                                    <th>ControllerName</th>
                                    <th>Group</th>
                                    <th>Sequence</th>
                                    <th>AddDate</th>
                                    <th>ModDate</th>
                                    <th>RmvDate</th>
                                    <th>Visible</th>
                                    <th>Operable</th>
                                    <th>Deleted</th>
                                    <th>Operation</th>
                                </tr>
                                <c:forEach var="categoryVo" items="${categoryVoList}">
                                    <tr>
                                        <td>${categoryVo.id}</td>
                                        <td>${categoryVo.displayName}</td>
                                        <td>${categoryVo.urlName}</td>
                                        <td>${categoryVo.parentId}</td>
                                        <td>${categoryVo.controllerName}</td>
                                        <td>${categoryVo.group}</td>
                                        <td>${categoryVo.sequence}</td>
                                        <td><fmt:formatDate value="${categoryVo.addDate}" type="both" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                                        <td><fmt:formatDate value="${categoryVo.modDate}" type="both" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                                        <td><fmt:formatDate value="${categoryVo.rmvDate}" type="both" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                                        <td><input type="checkbox" disabled <c:if test="${categoryVo.visible == 1}">checked="checked"</c:if> /></td>
                                        <td><input type="checkbox" disabled <c:if test="${categoryVo.operable == 1}">checked="checked"</c:if> /></td>
                                        <td><input type="checkbox" disabled <c:if test="${categoryVo.deleted == 1}">checked="checked"</c:if> /></td>
                                        <td><a href="/admin/category/rmv/${categoryVo.id}" />RMV</td>
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
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="/3th/jquery/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="/3th/bootstrap/js/bootstrap.min.js"></script>
<script src="/3th/bootcss/js/docs.min.js"></script>
</body>
</html>
