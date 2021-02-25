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

            <c:if test="${not empty requestScope.category}">
                <div class="card col-5">
                    <div class="card-body">
                        <div class="panel-body">
                            <form method="post" action="${pageContext.request.contextPath}/main">
                                <div class="mb-3">
                                    <label for="categoryName" class="form-label">
                                        <fmt:message key="title.category.name" bundle="${b}"/>
                                    </label>
                                    <input type="text" name="categoryName" class="form-control" id="categoryName"
                                           value="${requestScope.category.name}" required>
                                </div>
                                <input type="hidden" name="action" value="change_category">
                                <input type="hidden" name="categoryId" value="${requestScope.category.id}">
                                <button type="submit" class="btn btn-primary">
                                    <fmt:message key="button.change" bundle="${b}"/>
                                </button>
                            </form>
                            <form method="post" action="${pageContext.request.contextPath}/main">
                                <input type="hidden" name="action" value="delete_category">
                                <input type="hidden" name="categoryId" value="${requestScope.category.id}">
                                <button type="submit" class="btn btn-primary">
                                    <fmt:message key="button.delete" bundle="${b}"/>
                                </button>
                            </form>
                        </div>
                    </div>
                </div>

            </c:if>
        </div>
    </div>
    <%@include file="/WEB-INF/part/footer.jsp" %>
    <jsp:include page="../../bootstrap/script.jsp"/>
</div>
</body>
</html>
