<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isELIgnored="false" %>
<%@include file="../jspf/head.jspf" %>

<body>
<div class="container">
    <%@include file="../jspf/navbar.jspf" %>
    <table class="table">
        <thead>
        <tr>
            <th scope="col">Topic</th>
            <th scope="col">Tickets left</th>
            <th scope="col">Status</th>
            <th scope="col"></th>
            <th scope="col"></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="item" items="${expositionList}">
            <tr>
                <td>${item.topic}</td>
                <td>${item.capacity}</td>
                <td>${item.statusName}</td>
                <td>
                    <form:form action="expositionstats" method="get">
                        <input name="expositionid" type="hidden" value="${item.id}"/>
                        <button type="submit" class="btn btn-primary mb-3">Statistics</button>
                    </form:form>
                </td>
                <td>
                    <a href="${pageContext.request.contextPath}/expositionlistdelete?expositionid=${item.id}">Cancel</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</body>
</html>
