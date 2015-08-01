<%@ page language="java" pageEncoding="utf-8" isELIgnored="false"%>
<%@ include file="/WEB-INF/ref/taglib.jsp"%>
<!-- Fixed navbar -->
<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container">
        <div class="row">
            <div class="col-xs-12">
                <div class="navbar-header">
                    <!-- Brand and toggle get grouped for better mobile display -->
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="/">${applicationScope.CONFIG_MAP["ROC_TITLE"]}</a>
                </div>
                <!-- Collect the nav links, forms, and other content for toggling -->
                <div id="navbar" class="navbar-collapse collapse">
                    <ul class="nav navbar-nav">
                        <c:forEach var="leftCategory" items="${applicationScope.LEFT_CATEGORY_LST }">
                            <c:choose>
                                <c:when test="${(leftCategory.subCategoryVoList)!= null && fn:length(leftCategory.subCategoryVoList) > 0}">
                                    <li class="dropdown">
                                        <a href="/${leftCategory.controllerName}/${leftCategory.urlName}" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
                                            ${leftCategory.displayName}
                                            <span class="caret"></span>
                                        </a>
                                        <ul class="dropdown-menu" role="menu">
                                            <c:forEach var="subLeftCategory" items="${leftCategory.subCategoryVoList}">
                                                <c:choose>
                                                    <c:when test="${subLeftCategory.group == 1}">
                                                        <li class="divider"></li>
                                                        <li class="dropdown-header">${subLeftCategory.displayName}</li>
                                                        <c:forEach var="lastLeftCategory" items="${subLeftCategory.subCategoryVoList}">
                                                            <li><a href="/${lastLeftCategory.controllerName}/${lastLeftCategory.urlName}">${lastLeftCategory.displayName}</a></li>
                                                        </c:forEach>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <li><a href="/${subLeftCategory.controllerName}/${subLeftCategory.urlName}">${subLeftCategory.displayName}</a></li>
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:forEach>
                                        </ul>
                                    </li>
                                </c:when>
                                <c:otherwise>
                                    <li><a href="/${leftCategory.controllerName}/${leftCategory.urlName}">${leftCategory.displayName}</a></li>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </ul>
                    <form class="navbar-form navbar-right" role="search" method="post" action="/search">
                        <div class="input-group">
                            <input type="text" class="form-control" id="search" name="search" placeholder="${applicationScope.CONFIG_MAP["ROC_DESC"]}" required>
                            <span class="input-group-btn">
                                <button type="submit" class="btn btn-default">Search</button>
                            </span>
                        </div>
                    </form>
                    <ul class="nav navbar-nav navbar-right">
                        <c:forEach var="rightCategory" items="${applicationScope.RIGHT_CATEGORY_LST}">
                            <c:choose>
                                <c:when test="${(rightCategory.subCategoryVoList)!= null && fn:length(rightCategory.subCategoryVoList) > 0}">
                                    <li class="dropdown">
                                        <a href="/${rightCategory.controllerName}/${rightCategory.urlName}" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
                                            ${rightCategory.displayName}
                                            <span class="caret"></span>
                                        </a>
                                        <ul class="dropdown-menu" role="menu">
                                            <c:forEach var="subRightCategory" items="${rightCategory.subCategoryVoList}">
                                                <c:choose>
                                                    <c:when test="${subRightCategory.group == 1}">
                                                        <li class="divider"></li>
                                                        <li class="dropdown-header">${subRightCategory.displayName}</li>
                                                        <c:forEach var="lastRightCategory" items="${subRightCategory.subCategoryVoList}">
                                                            <li><a href="/${lastRightCategory.controllerName}/${lastRightCategory.urlName}">${lastRightCategory.displayName}</a></li>
                                                        </c:forEach>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <li><a href="/${subRightCategory.controllerName}/${subRightCategory.urlName}">${subRightCategory.displayName}</a></li>
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:forEach>
                                        </ul>
                                    </li>
                                </c:when>
                                <c:otherwise>
                                    <li><a href="/${rightCategory.controllerName}/${rightCategory.urlName}">${rightCategory.displayName}</a></li>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </ul>
                </div>
                <!--/.nav-collapse -->
            </div>
        </div>
    </div>
</nav>