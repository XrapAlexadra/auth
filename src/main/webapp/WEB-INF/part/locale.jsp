<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="language" value="${not empty sessionScope.language ?
          sessionScope.language : pageContext.request.locale}" scope="session" />

<fmt:setLocale value="${language}" />

<fmt:setBundle basename="messages" var="b"/>