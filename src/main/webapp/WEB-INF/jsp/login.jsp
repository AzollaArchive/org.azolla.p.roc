<%@ page language="java" pageEncoding="utf-8" isELIgnored="false"%>
<%@ include file="/WEB-INF/ref/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<%@ include file="/WEB-INF/ref/head.jsp"%>
<body>
<jsp:include page="/WEB-INF/ref/nav_no_menu.jsp" />
<div id="roc-i-content">
    <div class="container">
        <form class="form-signin" action="/j_spring_security_check" method="post">
            <h2 class="form-signin-heading">Please sign in</h2>
            <label for="inputUsername" class="sr-only">Username</label>
            <input type="text" id="inputUsername" class="form-control" placeholder="Username" required="required" autofocus="autofocus" name="username">
            <label for="inputPassword" class="sr-only">Password</label>
            <input type="password" id="inputPassword" class="form-control" placeholder="Password" required="required" name="password">
            <%--<div class="checkbox">--%>
                <%--<label>--%>
                    <%--<input type="checkbox" value="remember-me" name="rememberMe"> Remember me--%>
                <%--</label>--%>
            <%--</div>--%>
            <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
            <%--<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>--%>
        </form>
    </div>
</div>
<%@ include file="/WEB-INF/ref/footer.jsp" %>
</body>
</html>