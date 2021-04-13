<%@ page import="com.epam.expositions.entity.User" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<%@include file="../jspf/head.jspf" %>

<body>
<div class="container">
    <%@include file="../jspf/navbar.jspf" %>
    <p style="color: green"><c:out value="${login}"/> signed-in successfully.</p>
    <form action="logout" method="get">
        <button type="submit" class="btn btn-primary mb-3">Sign out</button>
    </form>
    <br>
    <c:if test="${role.equals(\"admin\")}">
        <a href="/userlist">User list</a>
    </c:if>
    <c:if test="${role.equals(\"user\") || role.equals(\"admin\") || role.equals(\"client\")}">
        <table class="table">
            <tbody>
            <tr>
                <td scope="row">Login</td>
                <td>${userData.login}</td>
            </tr>
            <tr>
                <td scope="row">Password</td>
                <td scope="row">
                    <form action="changepassword" method="post">
                        <div class="mb-3 row">
                            <label for="oldPassword" class="col-sm-2 col-form-label">Current password</label>
                            <div class="col-sm-10">
                                <input name="password" type="password" class="form-control" id="oldPassword"
                                       required="required" pattern="[A-Za-z0-9_!?]{1,16}">
                            </div>
                        </div>
                        <div class="mb-3 row">
                            <label for="newPassword" class="col-sm-2 col-form-label">New password</label>
                            <div class="col-sm-10">
                                <input name="newPassword" type="password" class="form-control" id="newPassword"
                                       required="required" pattern="[A-Za-z0-9_!?]{1,16}">
                            </div>
                        </div>
                        <div class="mb-3 row">
                            <label for="rePassword" class="col-sm-2 col-form-label">Re-enter new password</label>
                            <div class="col-sm-10">
                                <input name="newRePassword" type="password" class="form-control" id="rePassword"
                                       required="required" pattern="[A-Za-z0-9_!?]{4,16}">
                            </div>
                        </div>
                        <div id="errorMessage">

                        </div>
                        <div class="mb-3 row">
                            <div class="col-sm-10">
                                <button type="submit" class="btn btn-primary mb-3">Change password</button>
                            </div>
                        </div>
                    </form>

                </td>
            </tr>
            <tr>
                <td scope="row">Email</td>
                <td>${userData.email}</td>
            </tr>
            <tr>
                <td>Your ticket history</td>
                <td><a href="/history">View here.</a></td>
            </tr>
            <tr>
                <c:if test="${role.equals(\"user\")}">
                    <form action="/newexposition">
                        <h6>Want your own exposition? Create it now!</h6>
                        <button type="submit" class="btn btn-primary mb-3">Create exposition</button>
                    </form>
                </c:if>
                <c:if test="${role.equals(\"client\")}">
                    <form action="/expositionlist">
                        <td>Your expositions</td>
                        <td>
                            <button type="submit" class="btn btn-primary mb-3">Expositions</button>
                        </td>
                    </form>
                </c:if>
            </tr>
            </tbody>
        </table>
    </c:if>

</div>
</body>
</html>