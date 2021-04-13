<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<%@include file="../jspf/head.jspf" %>

<body>
<div class="container">
    <%@include file="../jspf/navbar.jspf" %>
<table class="table">
    <thead>
    <tr>
        <th scope="col">Login</th>
        <th scope="col">Email</th>
        <th scope="col">Role</th>
        <th scope="col"></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="item" items="${userList}">
        <tr>
            <td>${item.login}</td>
            <td>${item.email}</td>
            <td>${item.role.name}</td>
            <td>
                <c:if test="${!item.role.equals(\"admin\")}">
                    <form action="userlist" method="post">
                        <input name="userid" type="hidden" value="${item.id}"/>
                        <button type="submit" class="btn btn-primary mb-3">Delete</button>
                    </form>
                </c:if>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
