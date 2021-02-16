<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${not empty requestScope.message}">
    <div class="alert alert-primary" role="alert">
            ${requestScope.message}
    </div>
</c:if>


<c:if test="${not empty requestScope.error}">
    <div class="alert alert-danger" role="alert">
            ${requestScope.error}
    </div>
</c:if>

<c:if test="${not empty requestScope.errors}">
    <c:forEach items="${requestScope.errors}" var="p">
        <div class="alert alert-danger" role="alert">
                ${p}
        </div>
    </c:forEach>
</c:if>

