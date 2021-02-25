<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
    <div class="row">
        <div class="col-2">
            <%@include file="/WEB-INF/part/menu.jsp" %>
        </div>
        <div class="col-10">
            <c:import url="/WEB-INF/part/message.jsp"/>
            <ul class="nav nav-pills mb-3" id="pills-tab" role="tablist">
                <li class="nav-item" role="presentation">
                    <a class="nav-link active" id="pills-profile-tab" data-bs-toggle="pill" href="#pills-profile"
                       role="tab" aria-controls="pills-home" aria-selected="true">
                        <fmt:message key="button.user.profile" bundle="${b}"/>
                    </a>
                </li>
                <li class="nav-item" role="presentation">
                    <a class="nav-link" id="pills-changePassword-tab" data-bs-toggle="pill" href="#pills-changePassword"
                       role="tab" aria-controls="pills-changePassword" aria-selected="false">
                        <fmt:message key="button.user.change.password" bundle="${b}"/>
                    </a>
                </li>
            </ul>
            <div class="tab-content" id="pills-tabContent">
                <div class="tab-pane fade show active" id="pills-profile" role="tabpanel"
                     aria-labelledby="pills-profile-tab">
                    <div class="card-body  col-5">
                        <h5 class="card-title">${sessionScope.user.login}</h5>
                        <ul class="list-group">
                            <li class="list-group-item">
                                <img src="${pageContext.request.contextPath}/multipart?image=${sessionScope.user.image}"
                                     class="d-block w-40" alt="${sessionScope.user.image}"/>
                                <form action="${pageContext.request.contextPath}/multipart" method="post"
                                      enctype="multipart/form-data">
                                    <div class="mb-3">
                                        <label for="image" class="form-label">
                                            <fmt:message key="title.user.image.select" bundle="${b}"/>
                                        </label>
                                        <input type="file" id="image" name="image" size="300" required/>
                                    </div>
                                    <input type="hidden" name="action" value="change_user_image">
                                    <input type="hidden" name="userId" value="${sessionScope.user.id}">
                                    <button type="submit" class="btn btn-primary">
                                        <fmt:message key="button.change" bundle="${b}"/>
                                    </button>
                                </form>
                            </li>
                            <li class="list-group-item">
                                <fmt:message key="table.user.field.email" bundle="${b}"/>:
                                ${sessionScope.user.email}
                            </li>
                            <li class="list-group-item">
                                <fmt:message key="table.user.field.status" bundle="${b}"/>:
                                ${sessionScope.user.status}
                            </li>
                            <li class="list-group-item">
                                <fmt:message key="table.user.field.registrationDate" bundle="${b}"/>:
                                ${sessionScope.user.registrationDate}
                            </li>
                            <li class="list-group-item">
                                <fmt:message key="table.user.field.lastLoginDate" bundle="${b}"/>:
                                ${sessionScope.user.lastLoginDate}
                            </li>
                        </ul>
                        <br>
                    </div>
                </div>
                <div class="tab-pane fade" id="pills-changePassword" role="tabpanel"
                     aria-labelledby="pills-changePassword-tab">
                    <div class="card col-5">
                        <div class="card-body">
                            <div class="panel-body">
                                <form method="post" action="${pageContext.request.contextPath}/main">
                                    <div class="mb-3">
                                        <label for="password" class="form-label">
                                            <fmt:message key="title.password" bundle="${b}"/>
                                        </label>
                                        <input type="password" name="password" class="form-control" id="password"
                                               pattern="^[\w]{1,20}$" aria-describedby=passwordFeedBack" required>
                                        <c:if test="${not empty requestScope.errors}">
                                            <div id="passwordFeedBack" class="invalid-feedback">
                                                <fmt:message key="message.invalid.password" bundle="${b}"/>
                                            </div>
                                        </c:if>
                                    </div>
                                    <div class="mb-3">
                                        <label for="newPassword" class="form-label">
                                            <fmt:message key="title.password.new" bundle="${b}"/>
                                        </label>
                                        <input type="password" name="newPassword" class="form-control" id="newPassword"
                                               pattern="^[\w]{1,20}$" aria-describedby=newPasswordFeedBack" required>
                                        <c:if test="${not empty requestScope.errors}">
                                            <div id="newPasswordFeedBack" class="invalid-feedback">
                                                <fmt:message key="message.invalid.password" bundle="${b}"/>
                                            </div>
                                        </c:if>
                                    </div>
                                    <div class="mb-3">
                                        <label for="passwordRepeat" class="form-label">
                                            <fmt:message key="title.password.repeat" bundle="${b}"/>
                                        </label>
                                        <input type="password" name="passwordRepeat" class="form-control"
                                               id="passwordRepeat"
                                               pattern="^[\w]{1,20}$" required>
                                    </div>
                                    <input type="hidden" name="action" value="CHANGE_PASSWORD">
                                    <input type="hidden" name="login" value=${sessionScope.user.login}>
                                    <button type="submit" class="btn btn-primary">
                                        <fmt:message key="button.change" bundle="${b}"/>
                                    </button>
                                </form>

                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>
    <%@include file="/WEB-INF/part/footer.jsp" %>
    <jsp:include page="../../bootstrap/script.jsp"/>
</div>
</body>
</html>

