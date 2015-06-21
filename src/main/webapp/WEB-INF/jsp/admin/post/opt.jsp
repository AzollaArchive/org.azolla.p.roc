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
    <link href="/3th/select2/css/select2.min.css" rel="stylesheet" />
    <%@ include file="/WEB-INF/ref/admin/head_kindeditor.jsp"%>
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
                        <br/>
                        <form class="form-horizontal" name="form1" action="/admin/post/opt" method="post">
                            <input hidden="hidden" name="id" value="${postVo.id}">
                            <%--<input hidden="hidden" name="urlTitle" value="${postVo.urlTitle}">--%>
                            <div class="form-group">
                                <label for="title" class="col-xs-2 control-label">Title</label>
                                <div class="col-xs-9">
                                    <input class="form-control" type="text" id="title" name="title" placeholder="Title" required="required" value="${postVo.title}">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="category" class="col-xs-2 control-label">Category</label>
                                <div class="col-xs-9">
                                    <select class="form-control js-example-placeholder-single" id="category" name="category">
                                        <c:forEach var="leftCategory" items="${applicationScope.LEFT_CATEGORY_LST }">
                                            <c:choose>
                                                <c:when test="${(leftCategory.subCategoryVoList)!= null && fn:length(leftCategory.subCategoryVoList) > 0}">
                                                    <%--<option value="${leftCategory.id}" disabled>${leftCategory.displayName}</option>--%>
                                                    <optgroup label="${leftCategory.displayName}" />
                                                    <c:forEach var="subLeftCategory" items="${leftCategory.subCategoryVoList}">
                                                        <c:choose>
                                                            <c:when test="${subLeftCategory.group == 1}">
                                                                <%--<option value="${subLeftCategory.id}" disabled>&nbsp;&nbsp;&nbsp;&nbsp;${subLeftCategory.displayName}</option>--%>
                                                                <optgroup label="&nbsp;&nbsp;&nbsp;&nbsp;${subLeftCategory.displayName}" />
                                                                <c:forEach var="lastLeftCategory" items="${subLeftCategory.subCategoryVoList}">
                                                                    <option value="${lastLeftCategory.id}" <c:if test="${lastLeftCategory.id == postVo.categoryId}">selected="selected"</c:if>>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${lastLeftCategory.displayName}</option>
                                                                </c:forEach>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <option value="${subLeftCategory.id}" <c:if test="${subLeftCategory.id == postVo.categoryId}">selected="selected"</c:if>>&nbsp;&nbsp;&nbsp;&nbsp;${subLeftCategory.displayName}</option>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </c:forEach>
                                                </c:when>
                                                <c:otherwise>
                                                    <option value="${leftCategory.id}" <c:if test="${leftCategory.id == postVo.categoryId}">selected="selected"</c:if>>${leftCategory.displayName}</option>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                        <c:forEach var="rightCategory" items="${applicationScope.RIGHT_CATEGORY_LST }">
                                            <c:choose>
                                                <c:when test="${(rightCategory.subCategoryVoList)!= null && fn:length(rightCategory.subCategoryVoList) > 0}">
                                                    <%--<option value="${rightCategory.id}" disabled>${rightCategory.displayName}</option>--%>
                                                    <optgroup label="${rightCategory.displayName}" />
                                                    <c:forEach var="subRightCategory" items="${rightCategory.subCategoryVoList}">
                                                        <c:choose>
                                                            <c:when test="${subRightCategory.group == 1}">
                                                                <%--<option value="${subRightCategory.id}" disabled>&nbsp;&nbsp;&nbsp;&nbsp;${subRightCategory.displayName}</option>--%>
                                                                <optgroup label="&nbsp;&nbsp;&nbsp;&nbsp;${subRightCategory.displayName}" />
                                                                <c:forEach var="lastRightCategory" items="${subRightCategory.subCategoryVoList}">
                                                                    <option value="${lastRightCategory.id}" <c:if test="${lastRightCategory.id == postVo.categoryId}">selected="selected"</c:if>>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${lastRightCategory.displayName}</option>
                                                                </c:forEach>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <option value="${subRightCategory.id}" <c:if test="${subRightCategory.id == postVo.categoryId}">selected="selected"</c:if>>&nbsp;&nbsp;&nbsp;&nbsp;${subRightCategory.displayName}</option>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </c:forEach>
                                                </c:when>
                                                <c:otherwise>
                                                    <option value="${rightCategory.id}" <c:if test="${rightCategory.id == postVo.categoryId}">selected="selected"</c:if>>${rightCategory.displayName}</option>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="tag" class="col-xs-2 control-label">Tag</label>
                                <div class="col-xs-9">
                                    <select class="form-control js-example-tokenizer" id="tag" name="tag" multiple="multiple">
                                        <c:forEach var="cacheTag" items="${applicationScope.TAG_LST}">
                                            <c:set value="0" var="cacheTagInPost" scope="page" />
                                            <c:forEach var="postTag" items="${postVo.tagVoList}">
                                                <c:if test="${cacheTag.id == postTag.id}">
                                                    <option value="${cacheTag.id}" selected>${cacheTag.displayName}</option>
                                                    <c:set value="1" var="cacheTagInPost" scope="page" />
                                                </c:if>
                                            </c:forEach>
                                            <c:if test="${cacheTagInPost == 0}">
                                                <option value="${cacheTag.id}">${cacheTag.displayName}</option>
                                            </c:if>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="content" class="col-xs-2 control-label">Content</label>
                                <div class="col-xs-9">
                                    <textarea class="form-control" cols="100" rows="8" id="content" name="content" style="visibility:hidden;">${postVo.content}</textarea>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="permission" class="col-xs-2 control-label">Permission</label>
                                <div class="col-xs-9">
                                    <input type="checkbox" id="permission" name="visible" value="1" <c:if test="${postVo.visible == 1}">checked</c:if>/> Visible <br/>
                                    <input type="checkbox" name="operable" value="1" <c:if test="${postVo.operable == 1}">checked</c:if>/> Operable
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
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="/3th/jquery/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="/3th/bootstrap/js/bootstrap.min.js"></script>
<script src="/3th/bootcss/js/docs.min.js"></script>
<script src="/3th/select2/js/select2.full.min.js"></script>
<script>
    KindEditor.ready(function(K) {
        var editor1 = K.create('textarea[name="content"]', {
            cssPath : '/3th/kindeditor/plugins/code/prettify.css',
            uploadJson : '/kindeditor',
            fileManagerJson : '/3th/kindeditor/jsp/file_manager_json.jsp',
            allowFileManager : true,
            afterCreate : function() {
                var self = this;
                K.ctrl(document, 13, function() {
                    self.sync();
                    document.forms['form1'].submit();
                });
                K.ctrl(self.edit.doc, 13, function() {
                    self.sync();
                    document.forms['form1'].submit();
                });
            },
            items : [
                'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
                'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
                'insertunorderedlist', '|', 'emoticons', 'image', 'link','unlink','source','about']

        });
        prettyPrint();
    });

    $(document).ready(function() {
        $(".js-example-placeholder-single").select2({
//            allowClear: true,
            placeholder: "Please select"
        });

        $(".js-example-tokenizer").select2({
            tags: true,
            tokenSeparators: [',', ' ']
        })
    });
</script>
</body>
</html>
