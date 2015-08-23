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
                    <c:if test="${ctrl_result != null && ctrl_result.trim() != ''}">
                        <div class="alert alert-warning alert-dismissible" role="alert">
                            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                            <strong>Oh snap! </strong>${ctrl_result}
                        </div>
                    </c:if>
                    <div class="panel panel-default">
                        <!-- Default panel contents -->
                        <div class="panel-heading">${jsp_title}</div>
                        <!-- Table -->
                        <br/>
                        <form class="form-horizontal" name="form1" action="/admin/tag/opt" method="post">
                            <input hidden="hidden" name="id" value="${tagVo.id}">
                            <%--<input hidden="hidden" name="urlTitle" value="${postVo.urlTitle}">--%>
                            <div class="form-group">
                                <label for="displayName" class="col-xs-2 control-label">DisplayName</label>
                                <div class="col-xs-9">
                                    <input class="form-control" type="text" id="displayName" name="displayName" placeholder="DisplayName" required="required" value="${tagVo.displayName}">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="permission" class="col-xs-2 control-label">Permission</label>
                                <div class="col-xs-9">
                                    <input type="checkbox" id="permission" name="visible" value="1" <c:if test="${tagVo.visible == 1}">checked</c:if>/> Visible <br/>
                                    <input type="checkbox" name="operable" value="1" <c:if test="${tagVo.operable == 1}">checked</c:if>/> Operable <br/>
                                    <input type="checkbox" name="deleted" value="1" <c:if test="${tagVo.deleted == 1}">checked</c:if>/> Deleted
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-xs-offset-2 col-xs-9">
                                    <button type="submit" class="btn btn-default">Submit</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </article>
            </main>
        </div>
    </div>
</div>
<%@ include file="/WEB-INF/ref/footer.jsp" %>
</body>
</html>
