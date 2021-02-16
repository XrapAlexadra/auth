
<nav aria-label="Page navigation">
    <ul class="pagination">
        <li class="page-item">
            <c:if test="${requestScope.page == 1}">
                <a class="page-link" href="#"
                   aria-label="Previous">
                    <span aria-hidden="true">&laquo;</span>
                </a>
            </c:if>
            <c:if test="${requestScope.page != 1}">
                <a class="page-link" href="${pageContext.request.contextPath}${address}${requestScope.page-1}"
                   aria-label="Previous">
                    <span aria-hidden="true">&laquo;</span>
                </a>
            </c:if>
        </li>
        <c:forEach var="i" begin="1" end="${requestScope.pageCount}">
            <c:if test="${i == requestScope.page}">
                <li class="page-item active" aria-current="page">
                    <a class="page-link" href="#">${i}</a>
                </li>
            </c:if>
            <c:if test="${i != requestScope.page}">
                <li class="page-item">
                    <a class="page-link" href="${pageContext.request.contextPath}${address}${i}">
                            ${i}
                    </a>
                </li>
            </c:if>
        </c:forEach>

        <li class="page-item">
            <c:if test="${requestScope.page == requestScope.pageCount}">
                <a class="page-link" href="#"
                   aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                </a>
            </c:if>
            <c:if test="${requestScope.page != requestScope.pageCount}">
                <a class="page-link" href="${pageContext.request.contextPath}${address}${requestScope.page+1}"
                   aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                </a>
            </c:if>
        </li>
    </ul>
    <c:remove var="page" scope="session"/>
    <c:remove var="pageCount" scope="session"/>
</nav>