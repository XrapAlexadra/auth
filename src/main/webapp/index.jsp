<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="edu.epam.auth.model.Role" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>web</title>
    <%--    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">--%>
    <jsp:include page="bootstrap/bootsrap.jsp"/>
</head>
<body>
<%@include file="WEB-INF/part/locale.jsp" %>
<%@include file="WEB-INF/part/header.jsp" %>

<div class="container-fluid">
    <div class="row">
        <%--        <%@include file="page/part/menu.jsp" %>--%>
        <div class="col-10">
            <c:import url="WEB-INF/part/message.jsp"/>
            <h1>Hello </h1>
        </div>
    </div>
</div>
<%@include file="WEB-INF/part/footer.jsp" %>


<%--<main class="content-wrapper">--%>
<%--    <div class="container-fluid">--%>
<%--        <h1>Main Content</h1>--%>
<%--    </div>--%>
<%--</main>--%>

<%@include file="bootstrap/script.jsp" %>
</body>
</html>