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
        <h5 class="card-header"><fmt:message key="form.registration" bundle="${b}"/></h5>
        <div class="card-body">
            <div class="panel-body">
                <form method="post" enctype="multipart/form-data" action="${pageContext.request.contextPath}/multipart">
                    <div class="mb-3">
                        <label for="login" class="form-label">
                            <fmt:message key="title.login" bundle="${b}"/>
                        </label>
                        <c:choose>
                            <c:when test="${empty requestScope.login}">
                                <input type="text" name="login" class="form-control" id="login"
                                       aria-describedby=loginFeedBack" pattern="^[\w]{1,20}$" required>
                                    <div id="loginFeedBack" class="valid-feedback">
                                        <fmt:message key="message.invalid.login" bundle="${b}"/>
                                    </div>
                            </c:when>
                            <c:when test="${not empty requestScope.login}">
                                <input type="text" name="login" class="form-control" id="login"
                                       aria-describedby=loginFeedBack" pattern="^[\w]{1,20}$"
                                       value="${requestScope.login}" required>
                            </c:when>
                        </c:choose>
                    </div>
                    <div class="mb-3">
                        <label for="email" class="form-label">
                            <fmt:message key="title.email" bundle="${b}"/>
                        </label>
                        <c:choose>
                            <c:when test="${empty requestScope.email}">
                                <input type="text" name="email" class="form-control" id="email"
                                       aria-describedby="emailFeedBack" pattern="^[-\w]+@[\w]+\.[A-z]{2,4}$" required>
                                <c:if test="${not empty requestScope.errors} ">
                                    <div id="emailFeedBack" class="invalid-feedback">
                                        <fmt:message key="message.invalid.email" bundle="${b}"/>
                                    </div>
                                </c:if>
                            </c:when>
                            <c:when test="${not empty requestScope.email}">
                                <input type="text" name="email" class="form-control" id="email"
                                       aria-describedby="emailFeedBack" pattern="^[-\w]+@[\w]+\.[A-z]{2,4}$"
                                       value="${requestScope.email}" required>
                            </c:when>
                        </c:choose>
                    </div>
                    <div class="mb-3">
                        <label for="password" class="form-label">
                            <fmt:message key="title.password" bundle="${b}"/>
                        </label>
                        <input type="password" name="password" class="form-control" id="password"
                               aria-describedby=passwordFeedBack" pattern="^[\w]{1,20}$" required>
                        <c:if test="${not empty requestScope.errors && empty requestScope.password}">
                            <div id="passwordFeedBack" class="invalid-feedback">
                                <fmt:message key="message.invalid.password" bundle="${b}"/>
                            </div>
                        </c:if>
                    </div>
                    <div class="mb-3">
                        <label for="passwordRepeat" class="form-label">
                            <fmt:message key="title.password.repeat" bundle="${b}"/>
                        </label>
                        <input type="password" name="passwordRepeat" class="form-control"
                               id="passwordRepeat" aria-describedby=passwordRepeatFeedBack"
                               pattern="^[\w]{1,20}$" required>
                        <c:if test="${not empty requestScope.errors && empty requestScope.password}">
                            <div id="passwordRepeatFeedBack" class="invalid-feedback">
                                <fmt:message key="message.invalid.password" bundle="${b}"/>
                            </div>
                        </c:if>
                    </div>
                    <div class="mb-3">
                        <label for="image" class="form-label">
                            <fmt:message key="title.user.image.select" bundle="${b}"/>
                        </label>
                        <input type="file" id="image" name="image" size="300"/>
                    </div>
                    <input type="hidden" name="action" value="registration">
                    <button type="submit" class="btn btn-primary">
                        <fmt:message key="button.registration" bundle="${b}"/>
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