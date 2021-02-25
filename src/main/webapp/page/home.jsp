
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="edu.epam.auth.model.Role" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>web</title>
    <jsp:include page="../bootstrap/bootsrap.jsp"/>
</head>
<body>
<div class="container-fluid">
    <%@include file="/WEB-INF/part/locale.jsp"%>
    <%@include file="/WEB-INF/part/header.jsp"%>


    <div class="container-fluid">
        <div class="row">
            <div class="col-2">
                    <%@include file="/WEB-INF/part/menu.jsp" %>
            </div>
            <div class="col-10">
                <c:import url="/WEB-INF/part/message.jsp"/>


            </div>
        </div>
    </div>

    <%@include file="/WEB-INF/part/footer.jsp" %>
    <jsp:include page="../bootstrap/script.jsp"/>
</div>
</body>
</html>