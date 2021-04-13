<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<%@include file="../jspf/head.jspf" %>

<body>
<div class="container">
    <%@include file="../jspf/navbar.jspf" %>    </nav>
    <h2>Sign-in</h2>
    <form action="login" method="post">
        <div class="mb-3 row">
            <label for="inputLogin" class="col-sm-2 col-form-label">Login</label>
            <div class="col-sm-10">
                <input name="login" type="text" required="required" class="form-control" id="inputLogin"
                       pattern="[A-Za-z0-9_]{1,16}">
            </div>
        </div>
        <div class="mb-3 row">
            <label for="inputPassword" class="col-sm-2 col-form-label">Password</label>
            <div class="col-sm-10">
                <input name="password" type="password" class="form-control" id="inputPassword" required="required"
                       pattern="[A-Za-z0-9_!?]{1,16}">
            </div>
        </div>
        <div id="errorMessage">
        </div>
        <div class="mb-3 row">
            <div class="col-sm-10">
                <button type="submit" class="btn btn-primary mb-3">Sign in</button>
            </div>
        </div>
    </form>
    <div class="mb-3 row">
        <div class="col-sm-10">
            <a href="/register">Create account</a>
        </div>
    </div>
</div>
</body>
</html>