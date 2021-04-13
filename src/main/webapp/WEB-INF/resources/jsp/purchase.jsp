<%@ page import="com.epam.expositions.entity.User" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<%@include file="../jspf/head.jspf" %>

<body>
<div class="container">
    <%@include file="../jspf/navbar.jspf" %>
    <br>
    <form action="/purchase" method="post">
        <h3>Your ticket:</h3>
        <h6>Username: ${username}</h6>
        <h6>Exposition: ${exposition.topic}</h6>
        <h6>Price: ${exposition.price}</h6>
        <div class="form-check">
            <input class="form-check-input" type="checkbox" value="confirm" id="flexCheckDefault" required>
            <label class="form-check-label" for="flexCheckDefault">
                I agree with <a href="/policy">service policy</a>.
            </label>
        </div>
        <button type="submit" class="btn">Purchase</button>
    </form>
</div>
</body>
</html>