<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<c:if test="${ not empty sessionScope.catalog}">
    <ul class="list-group">
        <li class="list-group-item">
            <a class="nav-link" href="#">${sessionScope.catalog.category.name}</a
            <ul>
                <c:if test="${ not empty sessionScope.catalog.categoryComponents}">
                    <c:forEach items="${sessionScope.catalog.categoryComponents}" var="secondLevelCategory"
                               varStatus="index">
                        <li>
                            <a class="nav-link" href="#"> ${secondLevelCategory.category.name}</a>
                            <ul>
                                <c:forEach items="${secondLevelCategory.categoryComponents}" var="thirdLevelCategory">
                                    <li>
                                        <a class="nav-link" href="#"> ${thirdLevelCategory.category.name}</a>
                                    </li>
                                </c:forEach>
                            </ul>
                        </li>
                    </c:forEach>
                </c:if>
            </ul>
        </li>
    </ul>
</c:if>


