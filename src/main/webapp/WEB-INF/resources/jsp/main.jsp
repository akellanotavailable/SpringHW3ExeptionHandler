<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<%@include file="../jspf/head.jspf" %>

<body>
<div class="container">
    <%@include file="../jspf/navbar.jspf" %>
    <h2>Currently available: </h2>
    <c:if test="${expositionList.size() == 0}">
        <h5>No expositions available this week.</h5>
    </c:if>
    <c:if test="${expositionList.size() != 0}">
        <%@include file="../jspf/cardlist.jspf" %>
    </c:if>
</div>

</body>
</html>