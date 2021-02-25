<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="edu.epam.auth.model.Role" %>
<%@ page import="edu.epam.auth.model.UserStatus" %>

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
            <c:if test="${not empty requestScope.userPage}">
                <c:set var="address" value="/main?action=user_administration&page=" scope="page"/>
                <div>
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th></th>
                            <th scope="col"><fmt:message key="table.user.field.id" bundle="${b}"/></th>
                            <th scope="col"><fmt:message key="table.user.field.login" bundle="${b}"/></th>
                            <th scope="col"><fmt:message key="table.user.field.email" bundle="${b}"/></th>
                            <th scope="col"><fmt:message key="table.user.field.registrationDate"
                                                         bundle="${b}"/></th>
                            <th scope="col"><fmt:message key="table.user.field.lastLoginDate" bundle="${b}"/></th>
                            <th scope="col"><fmt:message key="table.user.field.status" bundle="${b}"/></th>
                            <th scope="col"><fmt:message key="table.user.field.role" bundle="${b}"/></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${requestScope.userPage}" var="user">
                            <tr>
                                <td>
                                    <img src="${pageContext.request.contextPath}/multipart?image=${user.image}"
                                         width="45"
                                         class="d-block" alt="${user.image}"/>
                                </td>
                                <td scope="row">${user.id}</td>
                                <td>${user.login}</td>
                                <td>${user.email}</td>
                                <td>${user.registrationDate}</td>
                                <td>${user.lastLoginDate}</td>
                                <form method="post" action="${pageContext.request.contextPath}/main">
                                    <input type="hidden" name="action" value="change_role_status">
                                    <td>
                                        <label>
                                            <select name="newStatus">
                                                <c:choose>
                                                    <c:when test="${user.status==UserStatus.ACTIVE}">
                                                        <option selected value="active">ACTIVE</option>
                                                        <option value="block">BLOCK</option>
                                                        <option value="delete">DELETE</option>
                                                        <option disabled>INACTIVE</option>
                                                    </c:when>
                                                    <c:when test="${user.status==UserStatus.BLOCK}">
                                                        <option value="active">ACTIVE</option>
                                                        <option selected value="block">BLOCK</option>
                                                        <option value="delete">DELETE</option>
                                                        <option disabled>INACTIVE</option>
                                                    </c:when>
                                                    <c:when test="${user.status==UserStatus.DELETE}">
                                                        <option value="active">ACTIVE</option>
                                                        <option value="block">BLOCK</option>
                                                        <option selected value="delete">DELETE</option>
                                                        <option disabled>INACTIVE</option>
                                                    </c:when>
                                                    <c:when test="${user.status==UserStatus.INACTIVE}">
                                                        <option value="active">ACTIVE</option>
                                                        <option value="block">BLOCK</option>
                                                        <option value="delete">DELETE</option>
                                                        <option selected value="inactive">INACTIVE</option>
                                                    </c:when>
                                                </c:choose>
                                            </select>
                                        </label>
                                    </td>
                                    <th>
                                        <label>
                                            <select name="newRole">
                                                <c:choose>
                                                    <c:when test="${user.role==Role.USER}">
                                                        <option selected value="user">USER</option>
                                                        <option value="admin">ADMIN</option>
                                                    </c:when>
                                                    <c:when test="${user.role==Role.ADMIN}">
                                                        <option value="user">USER</option>
                                                        <option selected value="admin">ADMIN</option>
                                                    </c:when>
                                                </c:choose>
                                            </select>
                                        </label>
                                    </th>
                                    <th scope="col">
                                        <button class="btn btn-primary" type="submit" name="userId"
                                                value="${user.id}">
                                            <fmt:message key="button.change" bundle="${b}"/>
                                        </button>
                                    </th>
                                </form>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <form method="post" action="${pageContext.request.contextPath}/main">
                        <input type="hidden" name="action" value="user_cleaning">
                        <button type="submit" class="btn btn-danger">
                            <fmt:message key="button.clean.users" bundle="${b}"/>
                        </button>
                    </form>
                </div>

                <%@include file="/WEB-INF/part/pagination.jsp" %>
            </c:if>
        </div>
    </div>
    <%@include file="/WEB-INF/part/footer.jsp" %>
    <jsp:include page="../../bootstrap/script.jsp"/>
</div>
</body>
</html>