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
    <%@include file="/WEB-INF/part/locale.jsp"%>
    <%@include file="/WEB-INF/part/header.jsp"%>
    <c:import url="/WEB-INF/part/message.jsp"/>

    <div class="card col-5">
        <h5 class="card-header"><fmt:message key="form.ad.create" bundle="${b}"/></h5>
        <div class="card-body">
            <div class="panel-body">
                <form method="post" action="${pageContext.request.contextPath}/main">
                    <div class="mb-3">
                        <label for="adTitle" class="form-label">
                            <fmt:message key="title.ad.title" bundle="${b}"/>
                        </label>
                        <c:if test="${ not empty sessionScope.adTitle}">
                        <input type="text" name="adTitle" class="form-control" id="adTitle"
                         value="${sessionScope.adTitle}" required>
                            <c:remove var="adTitle" scope="session"/>
                        </c:if>
                        <c:if test="${empty sessionScope.adTitle}">
                            <input type="text" name="adTitle" class="form-control" id="adTitle"
                                   required>
                        </c:if>
                    </div>
                    <div class="mb-3">
                        <label for="adText" class="form-label">
                            <fmt:message key="title.ad.text" bundle="${b}"/>
                        </label>
                        <c:if test="${ not empty sessionScope.adTitle}">
                            <input type="text" name="adText" class="form-control" id="adText"
                                   value="${sessionScope.adText}" required>
                            <c:remove var="adText" scope="session"/>
                        </c:if>
                        <c:if test="${empty sessionScope.adTitle}">
                            <input type="text" name="adText" class="form-control" id="adText" required>
                        </c:if>
                    </div>
                    <div class="mb-3">
                        <label for="adPrice" class="form-label">
                            <fmt:message key="title.ad.price" bundle="${b}"/>
                        </label>
                        <c:if test="${ not empty sessionScope.adTitle}">
                            <input type="text" name="adPrice" class="form-control" id="adPrice"
                            value="${sessionScope.adPrice}" required>
                            <c:remove var="adPrice" scope="session"/>
                        </c:if>
                        <c:if test="${empty sessionScope.adTitle}">
                            <input type="text" name="adPrice" class="form-control" id="adPrice" required>
                        </c:if>

                    </div>
                    <div class="mb-3">
                        <label>
                            <select name="adType">
                                <option selected value="buy">Buy</option>
                                <option value="selling">Selling</option>
                                <option value="service">Service</option>
                            </select>
                        </label>
                    </div>
                    <div class="mb-3">
                        <label>
                            <select name="category">
                                <option selected value="men_clothes">Men clothes</option>
                                <option value="women_clothes">Women clothes</option>
                                <option value="mobile">Mobile</option>
                                <option value="tv">TV</option>
                                <option value="furniture">Furniture</option>
                            </select>
                        </label>
                    </div>
                    <input type="hidden" name="action" value="CREATE_AD">
                    <input type="hidden" name="authorId" value=${sessionScope.user.id}>
                    <button type="submit" class="btn btn-primary">
                        <fmt:message key="button.create" bundle="${b}"/>
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