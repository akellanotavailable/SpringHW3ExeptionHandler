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
            <th scope="col">Exposition</th>
            <th scope="col">Status</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="item" items="${purchaseMap}">
            <tr>
                <td>${item.key}</td>
                <td>${item.value}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
