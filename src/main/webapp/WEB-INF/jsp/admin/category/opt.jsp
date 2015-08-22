<%@ page language="java" pageEncoding="utf-8" isELIgnored="false"%>
<%@ include file="/WEB-INF/ref/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<%@ include file="/WEB-INF/ref/head_editor.jsp"%>
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
                        <form class="form-horizontal" name="form1" action="/admin/category/opt" method="post">
                            <input hidden="hidden" name="id" value="${categoryVo.id}">
                            <%--<input hidden="hidden" name="urlTitle" value="${postVo.urlTitle}">--%>
                            <div class="form-group">
                                <label for="displayName" class="col-xs-2 control-label">DisplayName</label>
                                <div class="col-xs-9">
                                    <input class="form-control" type="text" id="displayName" name="displayName" placeholder="DisplayName" required="required" value="${categoryVo.displayName}">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="parentId" class="col-xs-2 control-label">ParentId</label>
                                <div class="col-xs-9">
                                    <select class="form-control js-example-placeholder-single" id="parentId" name="parentId">
                                        <c:forEach var="leftCategory" items="${applicationScope.LEFT_CATEGORY_LST }">
                                            <c:choose>
                                                <c:when test="${(leftCategory.subCategoryVoList)!= null && fn:length(leftCategory.subCategoryVoList) > 0}">
                                                    <option value="${leftCategory.id}" <c:if test="${leftCategory.id == categoryVo.parentId}">selected="selected"</c:if>>${leftCategory.displayName}</option>
                                                    <c:forEach var="subLeftCategory" items="${leftCategory.subCategoryVoList}">
                                                        <option value="${subLeftCategory.id}" <c:if test="${subLeftCategory.id == categoryVo.parentId}">selected="selected"</c:if>>&nbsp;&nbsp;&nbsp;&nbsp;${subLeftCategory.displayName}</option>
                                                        <c:if test="${subLeftCategory.grouped == 1}">
                                                            <c:forEach var="lastLeftCategory" items="${subLeftCategory.subCategoryVoList}">
                                                                <option value="${lastLeftCategory.id}" <c:if test="${lastLeftCategory.id == categoryVo.parentId}">selected="selected"</c:if>>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${lastLeftCategory.displayName}</option>
                                                            </c:forEach>
                                                        </c:if>
                                                    </c:forEach>
                                                </c:when>
                                                <c:otherwise>
                                                    <option value="${leftCategory.id}" <c:if test="${leftCategory.id == categoryVo.parentId}">selected="selected"</c:if>>${leftCategory.displayName}</option>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                        <c:forEach var="rightCategory" items="${applicationScope.RIGHT_CATEGORY_LST }">
                                            <c:choose>
                                                <c:when test="${(rightCategory.subCategoryVoList)!= null && fn:length(rightCategory.subCategoryVoList) > 0}">
                                                    <option value="${rightCategory.id}" <c:if test="${rightCategory.id == categoryVo.parentId}">selected="selected"</c:if>>${rightCategory.displayName}</option>
                                                    <c:forEach var="subRightCategory" items="${rightCategory.subCategoryVoList}">
                                                        <option value="${subRightCategory.id}" <c:if test="${subRightCategory.id == categoryVo.parentId}">selected="selected"</c:if>>&nbsp;&nbsp;&nbsp;&nbsp;${subRightCategory.displayName}</option>
                                                        <c:if test="${subRightCategory.grouped == 1}">
                                                            <c:forEach var="lastRightCategory" items="${subRightCategory.subCategoryVoList}">
                                                                <option value="${lastRightCategory.id}" <c:if test="${lastRightCategory.id == categoryVo.parentId}">selected="selected"</c:if>>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${lastRightCategory.displayName}</option>
                                                            </c:forEach>
                                                        </c:if>
                                                    </c:forEach>
                                                </c:when>
                                                <c:otherwise>
                                                    <option value="${rightCategory.id}" <c:if test="${rightCategory.id == categoryVo.parentId}">selected="selected"</c:if>>${rightCategory.displayName}</option>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="controllerName" class="col-xs-2 control-label">ControllerName</label>
                                <div class="col-xs-9">
                                    <input class="form-control" type="text" id="controllerName" name="controllerName" placeholder="ControllerName" required="required" value="${categoryVo.controllerName}">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="grouped" class="col-xs-2 control-label">Group</label>
                                <div class="col-xs-9">
                                    <input type="checkbox" id="grouped" name="grouped" value="1" <c:if test="${categoryVo.grouped == 1}">checked</c:if>/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="seq" class="col-xs-2 control-label">Sequence</label>
                                <div class="col-xs-9">
                                    <input class="form-control" type="text" id="seq" name="seq" placeholder="Sequence" required="required" value="${categoryVo.seq}">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="permission" class="col-xs-2 control-label">Permission</label>
                                <div class="col-xs-9">
                                    <input type="checkbox" id="permission" name="visible" value="1" <c:if test="${categoryVo.visible == 1}">checked</c:if>/> Visible <br/>
                                    <input type="checkbox" name="operable" value="1" <c:if test="${categoryVo.operable == 1}">checked</c:if>/> Operable
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
<%@ include file="/WEB-INF/ref/footer_editor.jsp" %>
<script>
    $(document).ready(function() {
        $(".js-example-placeholder-single").select2({
//            allowClear: true,
            placeholder: "Please select"
        });
    });
</script>
</body>
</html>
