<%@ page language="java" pageEncoding="utf-8" isELIgnored="false" %>
<%@ include file="/WEB-INF/ref/taglib.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN">
<%@ include file="/WEB-INF/ref/head.jsp" %>
<body>
<jsp:include page="/WEB-INF/ref/a/nav.jsp"/>
<div id="roc-i-content">
  <div class="container">
    <div class="row">
      <%@ include file="/WEB-INF/ref/a/aside.jsp" %>
      <main class="col-md-9">
        <article class="roc-c-article-post">
          <div class="panel panel-default">
            <!-- Default panel contents -->
            <div class="panel-heading">
              ${jsp_title} <span class="label label-default"><a href="/a/t/m" style="color: #FFFFFF;text-shadow: none">ADD</a></span>
            </div>
            <!-- Table -->
            <div class="table-responsive">
              <table class="table table-bordered table-hover table-condensed" style="border-bottom: solid 1px #ddd">
                <tr>
                  <th>#</th>
                  <th nowrap="nowrap">DisplayName</th>
                  <th nowrap="nowrap">UrlName</th>
                  <th nowrap="nowrap">AddDate</th>
                  <th nowrap="nowrap">ModDate</th>
                  <th nowrap="nowrap">Visible</th>
                  <th nowrap="nowrap">Operable</th>
                  <th nowrap="nowrap">Deleted</th>
                  <th nowrap="nowrap">Operation</th>
                </tr>
                <c:forEach var="tagVo" items="${tagVoList}">
                  <tr>
                    <td>${tagVo.id}</td>
                    <td>${tagVo.displayName}</td>
                    <td>${tagVo.urlName}</td>
                    <td><fmt:formatDate value="${tagVo.addDate}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                    <td><fmt:formatDate value="${tagVo.modDate}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                    <td><input type="checkbox" disabled
                               <c:if test="${tagVo.visible == 1}">checked="checked"</c:if> /></td>
                    <td><input type="checkbox" disabled
                               <c:if test="${tagVo.operable == 1}">checked="checked"</c:if> /></td>
                    <td><input type="checkbox" disabled
                               <c:if test="${tagVo.deleted == 1}">checked="checked"</c:if> /></td>
                    <td><a href="/a/t/m/${tagVo.urlName}">MOD</a></td>
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
