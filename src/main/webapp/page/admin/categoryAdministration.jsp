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
    <c:import url="/WEB-INF/part/message.jsp"/>

    <c:if test="${not empty requestScope.catalog}">
        <div class="accordion" id="firstLevel">
            <div class="accordion-item">
                <h2 class="accordion-header" id="headingOne">
                    <button class="accordion-button" type="button" data-bs-toggle="collapse"
                            data-bs-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                            ${requestScope.catalog.category.name}
                    </button>
                </h2>
                <div id="collapseOne" class="accordion-collapse collapse" aria-labelledby="headingOne"
                     data-bs-parent="#firstLevel">
                    <div class="accordion-body">
                        <c:if test="${not empty requestScope.catalog.categoryComponents}">
                            <c:forEach items="${requestScope.catalog.categoryComponents}" var="secondLevelComponent"
                                       varStatus="index">

                                <div class="accordion" id="secondLevel">
                                    <div class="accordion-item">
                                        <h2 class="accordion-header" id="${index.count}">
                                            <button class="accordion-button" type="button" data-bs-toggle="collapse"
                                                    data-bs-target="#${secondLevelComponent.category.name}"
                                                    aria-expanded="true"
                                                    aria-controls="${secondLevelComponent.category.name}">
                                                <a href="${pageContext.request.contextPath}/main?action=category&categoryId=${secondLevelComponent.category.id}">
                                                        ${secondLevelComponent.category.name}
                                                </a>
                                            </button>
                                        </h2>
                                        <div id="${secondLevelComponent.category.name}"
                                             class="accordion-collapse collapse"
                                             aria-labelledby="${index.count}" data-bs-parent="#secondLevel">
                                            <div class="accordion-body">
                                                <c:if test="${not empty secondLevelComponent.categoryComponents}">
                                                    <c:forEach items="${secondLevelComponent.categoryComponents}"
                                                               var="thirdLevelComponent">
                                                        <div class="list-group">
                                                            <a href="${pageContext.request.contextPath}/main?action=category&categoryId=${thirdLevelComponent.category.id}"
                                                               class="list-group-item list-group-item-action">
                                                                    ${thirdLevelComponent.category.name}
                                                            </a>
                                                        </div>
                                                    </c:forEach>
                                                </c:if>

                                                <form action="${pageContext.request.contextPath}/main" method="post">
                                                    <label class="form-label">
                                                        <fmt:message key="title.category.name" bundle="${b}"/>
                                                        <input type="text" name="categoryName" pattern="^[\w]{1,20}$"
                                                               required>
                                                    </label>
                                                    <input type="hidden" name="upCategoryId"
                                                           value="${secondLevelComponent.category.id}">
                                                    <input type="hidden" name="action" value="add_category">
                                                    <button type="submit" class="btn btn-primary">
                                                        <fmt:message key="button.create" bundle="${b}"/>
                                                    </button>
                                                </form>
                                            </div>
                                        </div>

                                    </div>

                                </div>

                            </c:forEach>
                        </c:if>
                        <form action="${pageContext.request.contextPath}/main" method="post">
                            <label for="categoryName" class="form-label">
                                <fmt:message key="title.category.name" bundle="${b}"/>
                            </label>
                            <input type="text" name="categoryName" id="categoryName" pattern="^[\w]{1,20}$" required>
                            <input type="hidden" name="upCategoryId" value="${requestScope.catalog.category.id}">
                            <input type="hidden" name="action" value="add_category">
                            <button type="submit" class="btn btn-primary">
                                <fmt:message key="button.create" bundle="${b}"/>
                            </button>
                        </form>
                    </div>
                </div>
            </div>

        </div>
    </c:if>
    <%@include file="/WEB-INF/part/footer.jsp" %>
    <jsp:include page="../../bootstrap/script.jsp"/>
</div>
</body>
</html>