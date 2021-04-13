<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page isELIgnored="false" %>

<%@include file="../jspf/head.jspf" %>
<body>
<div class="container">
    <%@include file="../jspf/navbar.jspf" %>
    <h4>Creating exposition.</h4>
    <h5>Step 1:</h5>
    <form:form class="input-group mb-3" id="formInput" method="post" action="/newexposition"
               modelAttribute="exposition">
        <div class="input-group-prepend">
            <span class="input-group-text" id="titleLabel">Title</span>
            <form:input type="text" class="form-control" aria-label="Title" aria-describedby="titleLabel" path="topic"
                        required="required"/>
        </div>

        <div class="input-group-prepend">
            <span class="input-group-text" id="dateStartTimeLabel">Start date</span>
            <form:input type="datetime-local" class="form-control" aria-label="Start date"
                   aria-describedby="dateStartTimeLabel" path="dateStart" required="required"/>
        </div>

        <div class="input-group-prepend">
            <span class="input-group-text" id="dateEndTimeLabel">End date</span>
            <form:input type="datetime-local" class="form-control" aria-label="End date" aria-describedby="dateEndTimeLabel"
                   path="dateEnd" required="required" />
        </div>

        <div class="input-group-prepend">
            <span class="input-group-text" id="priceLabel">Price</span>
            <form:input type="text" class="form-control" aria-label="Price" aria-describedby="priceLabel" path="price"
                   pattern="[0-9]*\.[0-9]{2}" placeholder="000.00" required="required" />
        </div>

        <div class="input-group-prepend">
            <span class="input-group-text" id="ticketLabel">Number of tickets</span>
            <form:input type="text" class="form-control" aria-label="Number of tickets" aria-describedby="ticketLabel"
                   path="capacity" pattern="[0-9]*" placeholder="000" required="required" />
        </div>

        <div class="input-group-prepend">
            <span class="input-group-text" id="imageLabel">Image link</span>
            <form:input type="text" class="form-control" aria-label="Image link" aria-describedby="imageLabel"
                   path="imagePath" pattern="(https:\/\/)*(.+)" placeholder="https://..." />
        </div>

        <div class="input-group-prepend">
            <span class="input-group-text" id="detailsLinkLabel">Event webpage</span>
            <form:input type="text" class="form-control" aria-label="Event webpage" aria-describedby="detailsLinkLabel"
                   path="detailsLink" pattern="(https:\/\/)*(.+)" placeholder="https://..." />
        </div>

        <button type="submit" class="btn btn-primary mb-3">Proceed</button>

    </form:form>
</div>
</body>
</html>