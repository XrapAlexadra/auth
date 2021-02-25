<nav class="navbar navbar-expand-lg navbar-light" style="background-color:  #e3f2fd;">
    <div class="container-fluid">
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
                           href="${pageContext.request.contextPath}/main?action=user_administration">
                            <fmt:message key="button.user.table" bundle="${b}"/>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link"
                           href="${pageContext.request.contextPath}/main?action=category_administration">
                            <fmt:message key="button.category.administration" bundle="${b}"/>
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
            <c:if test="${empty sessionScope.user}">
                <form class="d-flex" method="get"
                      action="${pageContext.request.contextPath}/page/authentication/login.jsp">
                    <button class="btn btn-primary" type="submit">
                        <fmt:message key="button.login" bundle="${b}"/>
                    </button>
                </form>
            </c:if>

            <ul class="nav justify-content-end mb-2 mb-lg-0">
                <c:if test="${ not empty sessionScope.user}">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="user" role="button" data-bs-toggle="dropdown"
                       aria-expanded="false">
                            ${sessionScope.user.login}
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="user">
                        <li>
                            <a class="dropdown-item"
                               href="${pageContext.request.contextPath}/page/user/userProfile.jsp">
                                <fmt:message key="button.user.profile" bundle="${b}"/>
                            </a>
                        </li>
                        <li>
                            <a class="dropdown-item" href="${pageContext.request.contextPath}/main?action=exit">
                                <fmt:message key="button.exit" bundle="${b}"/>
                            </a>
                        </li>
                    </ul>
                </li>
                </c:if>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="language" role="button" data-bs-toggle="dropdown"
                       aria-expanded="false">
                        <fmt:message key="title.locale.current" bundle="${b}"/>
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="language">
                        <li><a href="${pageContext.request.contextPath}/main?language=en_EN">EN</a></li>
                        <li><a href="${pageContext.request.contextPath}/main?language=ru_RU">Ru</a></li>
                    </ul>
                </li>

        </div>
    </div>
</nav>