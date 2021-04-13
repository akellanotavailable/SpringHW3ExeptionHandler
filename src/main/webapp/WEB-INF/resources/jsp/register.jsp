<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isELIgnored="false" %>
<%@include file="../jspf/head.jspf" %>

<body>
<div class="container">
    <%@include file="../jspf/navbar.jspf" %>
    <h2>Create account</h2>
    <form:form action="register" method="post">
        <div class="mb-3 row">
            <label for="inputLogin" class="col-sm-2 col-form-label">Login</label>
            <div class="col-sm-10">
                <form:input path="login" type="text" class="form-control" id="inputLogin" required="required"
                       pattern="[A-Za-z0-9_!?]{1,16}" />
            </div>
        </div>
        <div class="mb-3 row">
            <label for="inputEmail" class="col-sm-2 col-form-label">Email</label>
            <div class="col-sm-10">
                <form:input path="email" type="email" class="form-control" id="inputEmail" required="required"
                       pattern="^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$" />
            </div>
        </div>
        <div class="mb-3 row">
            <label for="inputPassword" class="col-sm-2 col-form-label">Password</label>
            <div class="col-sm-10">
                <form:input path="password" type="password" class="form-control" id="inputPassword"/>
            </div>
        </div>
        <div class="mb-3 row">
            <label for="inputRePassword" class="col-sm-2 col-form-label">Re-enter password</label>
            <div class="col-sm-10">
                <form:input path="rePassword" type="password" class="form-control" id="inputRePassword" required="required"
                       pattern="[A-Za-z0-9_!?]{1,16}"/>
            </div>
        </div>
        <div class="mb-3 row">
            <div class="col-sm-10">
                <button type="submit" class="btn btn-primary mb-3">Register</button>
            </div>
        </div>
    </form:form>
    <p style="color: red"><c:out value="${message}"/></p>
</div>
</body>
</html>