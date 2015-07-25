<%@ page language="java" pageEncoding="utf-8" isELIgnored="false"%>
<%@ include file="/WEB-INF/ref/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<%@ include file="/WEB-INF/ref/head.jsp"%>
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
                        <div class="panel-heading">
                            ${jsp_title} <span class="label label-default"><a href="/admin/category/opt" style="color: #FFFFFF;text-shadow: none">ADD</a></span>
                        </div>
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
                                        <td><a href="/admin/category/opt/${categoryVo.id}">MOD</a> <a href="/admin/category/rmv/${categoryVo.id}">RMV</a></td>
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
