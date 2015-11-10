<%@ page language="java" pageEncoding="utf-8" isELIgnored="false" %>
<%@ include file="/WEB-INF/ref/taglib.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN">
<%@ include file="/WEB-INF/ref/head_editor.jsp" %>
<body>
<jsp:include page="/WEB-INF/ref/a/nav.jsp"/>
<div id="roc-i-content">
  <div class="container">
    <div class="row">
      <%@ include file="/WEB-INF/ref/a/aside.jsp" %>
      <main class="col-md-9">
        <article class="roc-c-article-post">
          <c:if test="${ctrl_result != null && ctrl_result.trim() != ''}">
            <div class="alert alert-warning alert-dismissible" role="alert">
              <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span
                aria-hidden="true">&times;</span></button>
              <strong>Oh snap! </strong>${ctrl_result}
            </div>
          </c:if>
          <div class="panel panel-default">
            <!-- Default panel contents -->
            <div class="panel-heading">${jsp_title}</div>
            <!-- Table -->
            <br/>

            <form class="form-horizontal" name="form1" action="/a/p/m" method="post">
              <input hidden="hidden" name="id" value="${postVo.id}">
              <%--<input hidden="hidden" name="urlTitle" value="${postVo.urlTitle}">--%>
              <div class="form-group">
                <label for="title" class="col-xs-2 control-label">Title</label>

                <div class="col-xs-9">
                  <input class="form-control" type="text" id="title" name="title" placeholder="Title"
                         required="required" value="${postVo.title}">
                </div>
              </div>
              <div class="form-group">
                <label for="category" class="col-xs-2 control-label">Category</label>

                <div class="col-xs-9">
                  <select class="form-control js-example-placeholder-single" id="category" name="category">
                    <c:forEach var="leftCategory" items="${applicationScope.LEFT_CATEGORY_LST }">
                      <c:choose>
                        <c:when
                          test="${(leftCategory.subCategoryVoList)!= null && fn:length(leftCategory.subCategoryVoList) > 0}">
                          <%--<option value="${leftCategory.id}" disabled>${leftCategory.displayName}</option>--%>
                          <optgroup label="${leftCategory.displayName}"/>
                          <c:forEach var="subLeftCategory" items="${leftCategory.subCategoryVoList}">
                            <c:choose>
                              <c:when test="${subLeftCategory.grouped == 1}">
                                <%--<option value="${subLeftCategory.id}" disabled>&nbsp;&nbsp;&nbsp;&nbsp;${subLeftCategory.displayName}</option>--%>
                                <optgroup label="&nbsp;&nbsp;&nbsp;&nbsp;${subLeftCategory.displayName}"/>
                                <c:forEach var="lastLeftCategory" items="${subLeftCategory.subCategoryVoList}">
                                  <option value="${lastLeftCategory.id}" <c:if
                                    test="${lastLeftCategory.id == postVo.categoryId}">selected="selected"</c:if>>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${lastLeftCategory.displayName}</option>
                                </c:forEach>
                              </c:when>
                              <c:otherwise>
                                <option value="${subLeftCategory.id}" <c:if
                                  test="${subLeftCategory.id == postVo.categoryId}">selected="selected"</c:if>>&nbsp;&nbsp;&nbsp;&nbsp;${subLeftCategory.displayName}</option>
                              </c:otherwise>
                            </c:choose>
                          </c:forEach>
                        </c:when>
                        <c:otherwise>
                          <option value="${leftCategory.id}" <c:if
                            test="${leftCategory.id == postVo.categoryId}">selected="selected"</c:if>>${leftCategory.displayName}</option>
                        </c:otherwise>
                      </c:choose>
                    </c:forEach>
                    <c:forEach var="rightCategory" items="${applicationScope.RIGHT_CATEGORY_LST }">
                      <c:choose>
                        <c:when
                          test="${(rightCategory.subCategoryVoList)!= null && fn:length(rightCategory.subCategoryVoList) > 0}">
                          <%--<option value="${rightCategory.id}" disabled>${rightCategory.displayName}</option>--%>
                          <optgroup label="${rightCategory.displayName}"/>
                          <c:forEach var="subRightCategory" items="${rightCategory.subCategoryVoList}">
                            <c:choose>
                              <c:when test="${subRightCategory.grouped == 1}">
                                <%--<option value="${subRightCategory.id}" disabled>&nbsp;&nbsp;&nbsp;&nbsp;${subRightCategory.displayName}</option>--%>
                                <optgroup label="&nbsp;&nbsp;&nbsp;&nbsp;${subRightCategory.displayName}"/>
                                <c:forEach var="lastRightCategory" items="${subRightCategory.subCategoryVoList}">
                                  <option value="${lastRightCategory.id}" <c:if
                                    test="${lastRightCategory.id == postVo.categoryId}">selected="selected"</c:if>>
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${lastRightCategory.displayName}</option>
                                </c:forEach>
                              </c:when>
                              <c:otherwise>
                                <option value="${subRightCategory.id}" <c:if
                                  test="${subRightCategory.id == postVo.categoryId}">selected="selected"</c:if>>&nbsp;&nbsp;&nbsp;&nbsp;${subRightCategory.displayName}</option>
                              </c:otherwise>
                            </c:choose>
                          </c:forEach>
                        </c:when>
                        <c:otherwise>
                          <option value="${rightCategory.id}" <c:if
                            test="${rightCategory.id == postVo.categoryId}">selected="selected"</c:if>>${rightCategory.displayName}</option>
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
                      <c:set value="0" var="cacheTagInPost" scope="page"/>
                      <c:forEach var="postTag" items="${postVo.tagVoList}">
                        <c:if test="${cacheTag.id == postTag.id}">
                          <option value="${cacheTag.id}" selected>${cacheTag.displayName}</option>
                          <c:set value="1" var="cacheTagInPost" scope="page"/>
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
                  <textarea class="form-control" id="content" name="content" autofocus>${postVo.content}</textarea>
                </div>
              </div>
              <div class="form-group">
                <label for="permission" class="col-xs-2 control-label">Permission</label>

                <div class="col-xs-9">
                  <input type="checkbox" id="permission" name="visible" value="1"
                         <c:if test="${postVo.visible == 1}">checked</c:if>/> Visible <br/>
                  <input type="checkbox" name="operable" value="1" <c:if test="${postVo.operable == 1}">checked</c:if>/>
                  Operable <br/>
                  <input type="checkbox" name="deleted" value="1" <c:if test="${postVo.deleted == 1}">checked</c:if>/>
                  Deleted
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
  var simditor = new Simditor({
    textarea: $('#content'),
    placeholder: 'Please input...',
    toolbar: ['bold', 'italic', 'underline', 'strikethrough', 'color', 'ol', 'ul', 'blockquote', 'code', 'table', 'link', 'image', 'hr', 'emoji', 'html', 'mark', 'kbd', 'coded', 'small', 'more'],
    defaultImage: '/img/favicon.png',
    upload: {
      url: '/a/simditor',
      params: null,
      fileKey: 'upload_file',
      connectionCount: 3,
      leaveConfirm: 'Uploading...'
    },
    pasteImage: true,
    emoji: {imagePath: '/3th/simditor/images/emoji/'},
    autosave: 'editor-content'
  });

  $(document).ready(function () {
    $(".js-example-placeholder-single").select2({
//            allowClear: true,
      placeholder: "Please select"
    });

    $(".js-example-tokenizer").select2({
      tags: true,
      tokenSeparators: [',', ' ']
    });
  });
</script>
</body>
</html>
