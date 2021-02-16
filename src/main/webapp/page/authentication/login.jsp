<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="edu.epam.auth.model.Role" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>web</title>
    <jsp:include page="../../bootstrap/bootsrap.jsp"/>
</head>
<body>
<div class="container-fluid">
    <%@include file="/WEB-INF/part/locale.jsp" %>
    <%@include file="/WEB-INF/part/header.jsp" %>
    <c:import url="/WEB-INF/part/message.jsp"/>


    <div class="card col-5">
        <h5 class="card-header"><fmt:message key="form.login" bundle="${b}"/></h5>
        <div class="card-body">
            <div class="panel-body">
                <form method="post" action="${pageContext.request.contextPath}/main">
                    <div class="mb-3">
                        <label for="login" class="form-label">
                            <fmt:message key="title.login" bundle="${b}"/>
                        </label>
                        <c:choose>
                            <c:when test="${empty requestScope.login}">
                                <input type="text" name="login" class="form-control" id="login" required>
                            </c:when>
                            <c:when test="${not empty requestScope.login}">
                                <input type="text" name="login" class="form-control" id="login"
                                       value="${requestScope.login}" required>
                            </c:when>
                        </c:choose>
                    </div>
                    <div class="mb-3">
                        <label for="password" class="form-label">
                            <fmt:message key="title.password" bundle="${b}"/>
                        </label>
                        <input type="password" name="password" class="form-control" id="password" required>
                    </div>
                    <div class="mb-3">
                        <a href="${pageContext.request.contextPath}/page/authentication/auth.jsp">
                            <fmt:message key="title.registration" bundle="${b}"/>
                        </a>
                    </div>
                    <input type="hidden" name="action" value="login">
                    <button type="submit" class="btn btn-primary">
                        <fmt:message key="button.login" bundle="${b}"/>
                    </button>
                </form>

            </div>
        </div>
    </div>

    <%@include file="/WEB-INF/part/footer.jsp" %>
    <jsp:include page="../../bootstrap/script.jsp"/>
</div>
</body>
</html>