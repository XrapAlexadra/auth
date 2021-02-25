<head>
    <meta charset="UTF-8">
    <title>web</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <%@include file="../../bootstrap/bootsrap.jsp" %>
</head>
<body>
<%@include file="locale.jsp" %>

<nav class="navbar navbar-expand-lg navbar-light fixed-top " style="background-color: #e3f2fd;">

    <div class="collapse navbar-collapse">
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
            <li class="nav-item">
                <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/">
                    <fmt:message key="button.home" bundle="${b}"/>
                </a>
            </li>
            <c:if test="${sessionScope.user.role == Role.ADMIN }">
                <li class="nav-item">
                    <a class="nav-link"
                       href="${pageContext.request.contextPath}/main?action=USER_ADMINISTRATION">
                        <fmt:message key="button.user.table" bundle="${b}"/>
                    </a>
                </li>
            </c:if>
            <c:if test="${sessionScope.user.role == Role.USER }">
                <li class="nav-item">
                    <a class="nav-link"
                       href="${pageContext.request.contextPath}/page/user/creatingAd.jsp">
                        <fmt:message key="form.ad.create" bundle="${b}"/>
                    </a>
                </li>
            </c:if>

        </ul>
    </div>
    <div class="collapse navbar-collapse" id="navbarCollapse">
        <c:if test="${empty sessionScope.user}">
        <div class="nav justify-content-end mb-2 mb-lg-0">
            <form class="d-flex" method="get" action="${pageContext.request.contextPath}/page/authentification/login.jsp">
                <button class="btn btn-primary" type="submit">
                    <fmt:message key="button.login" bundle="${b}"/>
                </button>
            </form>
        </div>
        </c:if>
        <c:if test="${ not empty sessionScope.user}">
        <ul class="nav justify-content-end mb-2 mb-lg-0">
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="user" role="button" data-bs-toggle="dropdown"
                   aria-expanded="false">
                        ${sessionScope.user.login}
                </a>
                <ul class="dropdown-menu" aria-labelledby="user">
                    <li>
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/page/user/userProfile.jsp">
                            <fmt:message key="button.user.profile" bundle="${b}"/>
                        </a>
                    </li>
                    <li>
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/main?action=EXIT">
                            <fmt:message key="button.exit" bundle="${b}"/>
                        </a>
                    </li>
                </ul>
            </li>
        </ul>
        </c:if>

    <%@include file="menu.jsp" %>
</nav>